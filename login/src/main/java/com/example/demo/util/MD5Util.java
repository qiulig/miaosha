package com.example.demo.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }
    //随机来个盐值,与静态static/js/common.js对应
    private static  String salt = "1a2b3c4d";
    //第一次MD5 = 明文+固定salt;
    public  static  String inputPassToFormPass(String inputPass){
        String str = ""+salt.charAt(0)+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }
    //第二次md5 = 用户输入+ 随机盐值
    public  static  String formPassToDBPass(String formPass,String salt){
        String str =  ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }
    //两个联立起来
    public static String inputPassToDbPass(String input,String saltDB){
        String formPass  = inputPassToFormPass(input);
        String dbPass = formPassToDBPass(formPass,saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(inputPassToFormPass("111111"));//d018506bc314a32b93eb214102399a63
        System.out.println(formPassToDBPass(inputPassToFormPass("111111"), "1a2b3c4d"));
        //该值存入数据库的password字段，即第二次MD5结果，数据库中的salt存入1a2b3c4d
        System.out.println(inputPassToDbPass("111111", "1a2b3c4d"));//e5d22cfc746c7da8da84e0a996e0fffa
    }
}
