package com.poke.miaosha.controller;

import com.poke.miaosha.domain.User;
import com.poke.miaosha.rabbitmq.MQSender;
import com.poke.miaosha.redis.RedisService;
import com.poke.miaosha.redis.UserKey;
import com.poke.miaosha.result.CodeMsg;
import com.poke.miaosha.result.Result;
import com.poke.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName DemoController
 * @Description //TODO
 * @Author poke
 * @Date 2020/2/29 5:22 下午
 */
@Controller
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    MQSender sender;

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "hello world!";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public Result hello() {
        return Result.success("hello, poke");
    }

    @RequestMapping("/helloError")
    @ResponseBody
    public Result helloError() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "poke");
        return "hello";
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result dbTx() {
        userService.tx();
        return Result.success(true);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result redisGet() {
        User user = redisService.get(UserKey.getById,"" + 1, User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result redisSet() {
        User user = new User(1, "11111");
        Boolean ret = redisService.set(UserKey.getById,"" + 1, user);
        return Result.success(ret);
    }

//    @RequestMapping("/mq")
//    @ResponseBody
//    public Result mq() {
//        sender.send("hello, mq");
//        return Result.success("hello, mq");
//    }
//
//    @RequestMapping("/mq/topic")
//    @ResponseBody
//    public Result topic() {
//        sender.sendTopic("hello, mq");
//        return Result.success("hello, mq");
//    }
//
//    @RequestMapping("/mq/fanout")
//    @ResponseBody
//    public Result fanout() {
//        sender.sendFanout("hello, mq");
//        return Result.success("hello, mq");
//    }

}
