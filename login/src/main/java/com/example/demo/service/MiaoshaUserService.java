package com.example.demo.service;

import com.example.demo.dao.MiaoshaUserDao;
import com.example.demo.domain.MiaoshaUser;
import com.example.demo.exception.GlobalException;
import com.example.demo.redis.MiaoshaUserKey;
import com.example.demo.redis.RedisService;
import com.example.demo.result.CodeMsg;
import com.example.demo.util.MD5Util;
import com.example.demo.util.UUIDUtil;
import com.example.demo.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class MiaoshaUserService {
    @Autowired
    MiaoshaUserDao miaoshaUserDao;
    @Autowired
    RedisService redisService;

    public static final String COOKI_NAME_TOKEN = "token";

    public MiaoshaUser getById(long id) {
        return miaoshaUserDao.getById(id);
    }

    public boolean login(HttpServletResponse response, LoginVo loginVo) { //根据前端login.html可知：LoginVo为电话号码和第一次MD5的值
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();  //从前端得到的手机号码
        String formPass = loginVo.getPassword();  //前端得到的第一次MD5的值
        //判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));  //通过手机号码查找数据库数据
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();  //得到数据库中该手机的密码（第二次MD5的值）
        String saltDB = user.getSalt();   // 得到数据库中该手机的盐值
        //
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB); //第二次MD5得到的值
        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }

        //登陆成功后给用户生成一个id【UUID】,如token,来标识用户，然后写到cookie中传递给客户端，
        // 客户端在随后的访问中都在cookie中上传这个token,服务端拿到token之后利用这个token来取到用户对应的信息
        //生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response,token,user);
        return true;
    }

    private void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
        //将信息存放到第三方缓存中
        redisService.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());//设置过期时间
        //设置网站的根目录,然后写到response,故需要HTTPServletResponse,写在方法里面
        cookie.setPath("/");
        //将cookie存放到客户端
        response.addCookie(cookie);
    }
    public MiaoshaUser getByToken(HttpServletResponse response,String token) {
        if(StringUtils.isEmpty(token)){
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoshaUserKey.token,token,MiaoshaUser.class);
        //延长有效期
        if(user != null) {
            addCookie(response, token, user);
        }
        return user;
    }
}