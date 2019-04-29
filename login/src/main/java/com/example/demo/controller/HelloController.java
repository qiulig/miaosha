package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.redis.RedisService;
import com.example.demo.redis.UserKey;
import com.example.demo.result.CodeMsg;
import com.example.demo.result.Result;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
    @RequestMapping("/success")
    @ResponseBody
    public Result<String> success(){
        return Result.success("success,hello");
    }
    @RequestMapping("/errors")
    @ResponseBody
    public Result<String> error(){
        return Result.Error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/hello")
    public  String thymeleaf(Model model){
        model.addAttribute("name","hhhhhh");
        return "hello";
    }
    @Autowired
    UserService userService;

    @RequestMapping("/mybati")
    @ResponseBody
    public  Result<User> mybatis(){
        User user = userService.getById(1);
        return Result.success(user);
    }
    @RequestMapping("/tx")
    @ResponseBody
    public  Result<Boolean> dbTX(){
        userService.tx();
        return Result.success(true);
    }
    @Autowired
    RedisService redisService;

    @RequestMapping("/redis/get")
    @ResponseBody
    public  Result<User> getRedis(){
       User user = redisService.get(UserKey.getById,"key1",User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public  Result<Boolean> setRedis(){
        User user = new User();
        user.setId(1);
        user.setName("1111");
        redisService.set(UserKey.getById,""+1,user);
        return Result.success(true);
    }

}
