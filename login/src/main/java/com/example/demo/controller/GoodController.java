package com.example.demo.controller;

import com.example.demo.domain.MiaoshaUser;
import com.example.demo.redis.RedisService;
import com.example.demo.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/goods")
public class GoodController {
    @Autowired
    MiaoshaUserService userService;
    @Autowired
    RedisService redisService;

    @RequestMapping("/to_list")
    public String list(  Model model,
//                       HttpServletResponse response,
//                       @CookieValue(value = MiaoshaUserService.COOKI_NAME_TOKEN,required = false)String cookieToken,
//                       @RequestParam(value = MiaoshaUserService.COOKI_NAME_TOKEN ,required =  false)String paramToken
                         MiaoshaUser user){
//        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
//            return "goods_list";
//        }
//        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
//        MiaoshaUser user = userService.getByToken(response,token);
        model.addAttribute("user",user);
        return "goods_list";
    }
}
