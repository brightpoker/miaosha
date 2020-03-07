package com.poke.miaosha.controller;

import com.poke.miaosha.access.AccessLimit;
import com.poke.miaosha.domain.MiaoshaOrder;
import com.poke.miaosha.domain.MiaoshaUser;
import com.poke.miaosha.domain.OrderInfo;
import com.poke.miaosha.rabbitmq.MQSender;
import com.poke.miaosha.rabbitmq.MiaoshaMessage;
import com.poke.miaosha.redis.AccessKey;
import com.poke.miaosha.redis.GoodsKey;
import com.poke.miaosha.redis.MiaoshaKey;
import com.poke.miaosha.redis.RedisService;
import com.poke.miaosha.result.CodeMsg;
import com.poke.miaosha.result.Result;
import com.poke.miaosha.service.GoodsService;
import com.poke.miaosha.service.MiaoshaService;
import com.poke.miaosha.service.OrderService;
import com.poke.miaosha.util.MD5Util;
import com.poke.miaosha.util.UUIDUtil;
import com.poke.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MiaoshaController
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/1 10:33 下午
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController implements InitializingBean {
    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

    @Autowired
    RedisService redisService;

    @Autowired
    MQSender sender;

    private Map<Long, Boolean> localOverMap = new HashMap<>();

    @RequestMapping(value = "/{path}/do_miaosha", method = RequestMethod.POST)
    @ResponseBody
    public Result miaosha(Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId, @PathVariable("path")String path) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        model.addAttribute("user", user);
        //验证path
        boolean check = miaoshaService.checkPath(user, goodsId, path);
        if (!check) {
            return Result.error(CodeMsg.REQUEST_ILLEGAL);
        }

        boolean over = localOverMap.get(goodsId);
        if (over) {
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        long stock = redisService.decr(GoodsKey.getMiaoshaGoodsStock, "" + goodsId);
        if (stock < 0) {
            localOverMap.put(goodsId, true);
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        //判断秒杀已经达到
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            return Result.error(CodeMsg.REPEATE_MIAOSHA);
        }
        //入队
        MiaoshaMessage message = new MiaoshaMessage();
        message.setUser(user);
        message.setGoodsId(goodsId);
        sender.sendMiaoshaMessage(message);
        return Result.success(0);
    }

    /**
     * @Description 秒杀成功返回订单ID;库存失败返回-1；排队中返回0
     *
     * @param model
     * @param user
     * @param goodsId
     * @return com.poke.miaosha.result.Result
     **/
    @AccessLimit(seconds = 5, maxCount = 10, needLogin = true)
    @RequestMapping(value = "/result", method = RequestMethod.GET)
    @ResponseBody
    public Result miaoshaResult(Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }

        long result = miaoshaService.getMiaoshaResult(user.getId(), goodsId);
        return Result.success(result);
    }

    @AccessLimit(seconds = 5, maxCount = 5, needLogin = true)
    @RequestMapping(value = "/path", method = RequestMethod.GET)
    @ResponseBody
    public Result getMiaoshaPath(HttpServletRequest request, MiaoshaUser user, @RequestParam("goodsId") long goodsId, @RequestParam(value = "verifyCode", defaultValue = "0")int verifyCode) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        boolean check = miaoshaService.checkVerifyCode(user, goodsId, verifyCode);
        if (!check) {
            return Result.error(CodeMsg.REQUEST_ILLEGAL);
        }
        String path = miaoshaService.createMiaoshaPath(user, goodsId);
        return Result.success(path);

    }

    @RequestMapping(value = "/verifyCode", method = RequestMethod.GET)
    @ResponseBody
    public Result getMiaoshaVerifyCode(HttpServletRequest request, MiaoshaUser user, @RequestParam("goodsId") long goodsId, HttpServletResponse response) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        try {
            BufferedImage image  = miaoshaService.createVerifyCode(user, goodsId);
            OutputStream out = response.getOutputStream();
            ImageIO.write(image, "JPEG", out);
            out.flush();
            out.close();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(CodeMsg.MIAOSHA_FAIL);
        }

    }

    /**
     * @Description 系统初始化
     *
     * @param
     * @return void
     **/
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        if (goodsList == null) {
            return;
        }
        for (GoodsVo goods : goodsList) {
            redisService.set(GoodsKey.getMiaoshaGoodsStock, "" + goods.getId(), goods.getStockCount());
            localOverMap.put(goods.getId(), false);
        }
    }

}
