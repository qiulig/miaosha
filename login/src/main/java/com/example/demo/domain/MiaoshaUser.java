package com.example.demo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class MiaoshaUser {
    private String id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
}
