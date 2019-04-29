package com.example.demo.controller;
import com.example.demo.result.Result;
import com.example.demo.service.MiaoshaUserService;
import com.example.demo.util.ValidatorUtil;
import com.example.demo.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    MiaoshaUserService miaoshaUserService;
    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response,@Valid LoginVo loginVo){
        logger.info(loginVo.toString());
        miaoshaUserService.login(response,loginVo);
        return Result.success(true);
//        //参数校验
//        String passInput = loginVo.getPassword();
//        String mobile = loginVo.getMobile();
//        if(StringUtils.isEmpty(passInput)){
//            return Result.Error(CodeMsg.PASSWORD_EMPTY);
//        }
//        if(StringUtils.isEmpty(mobile)){
//            return Result.Error(CodeMsg.MOBILE_EMPTY);
//        }
//        if(!ValidatorUtil.isMobile(mobile)){
//            return Result.Error(CodeMsg.MOBILE_ERROR);
//        }
        //登录
//        CodeMsg cm = miaoshaUserService.login(loginVo);
//        if(cm.getCode() == 0){
//            return Result.success(true);
//        }else{
//            return Result.Error(cm);
//        }

    }
}
