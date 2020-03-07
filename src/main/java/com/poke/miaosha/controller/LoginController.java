package com.poke.miaosha.controller;

import com.poke.miaosha.result.CodeMsg;
import com.poke.miaosha.result.Result;
import com.poke.miaosha.service.MiaoshaUserService;
import com.poke.miaosha.util.ValidatorUtil;
import com.poke.miaosha.vo.LoginVo;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @ClassName LoginController
 * @Description //TODO
 * @Author poke
 * @Date 2020/2/29 10:49 下午
 */
@Controller
@RequestMapping("/login")
@Log4j
public class LoginController {
    @Autowired
    MiaoshaUserService userService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @ResponseBody
    @RequestMapping("/do_login")
    public Result doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        String token = userService.login(response, loginVo);
        return Result.success(token);
    }
}
