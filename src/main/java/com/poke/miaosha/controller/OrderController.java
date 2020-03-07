package com.poke.miaosha.controller;

import com.poke.miaosha.domain.MiaoshaUser;
import com.poke.miaosha.domain.OrderInfo;
import com.poke.miaosha.result.CodeMsg;
import com.poke.miaosha.result.Result;
import com.poke.miaosha.service.GoodsService;
import com.poke.miaosha.service.OrderService;
import com.poke.miaosha.vo.GoodsVo;
import com.poke.miaosha.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName OrderController
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/3 1:09 上午
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/detail")
    @ResponseBody
    public Result info(@RequestParam("orderId") long orderId, MiaoshaUser user, Model model) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if (order == null) {
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        long goodsId = order.getGoodsId();
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo vo = new OrderDetailVo();
        vo.setGoods(goods);
        vo.setOrder(order);
        return Result.success(vo);
    }
}
