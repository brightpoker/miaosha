package com.poke.miaosha.controller;

import com.poke.miaosha.domain.MiaoshaUser;
import com.poke.miaosha.result.Result;
import com.poke.miaosha.service.GoodsService;
import com.poke.miaosha.service.MiaoshaUserService;
import com.poke.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName GoodsController
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/1 3:19 上午
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    MiaoshaUserService userService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/info")
    @ResponseBody
    public Result info(Model model, MiaoshaUser user) {
        model.addAttribute("user", user);
        return Result.success(user);
    }

}
