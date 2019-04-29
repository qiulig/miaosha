package com.example.demo.result;

import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String msg;
    private  T data;
    //成功时候的构造函数
    public Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }
    //失败时候调用的构造函数
    public Result(CodeMsg cm) {
        if(cm == null)
            return;
        this.code = cm.getCode();
        this.msg = cm.getMsg();

    }
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }
    public static <T> Result<T> Error(CodeMsg cm){
        return new Result<T>(cm);
    }
}
