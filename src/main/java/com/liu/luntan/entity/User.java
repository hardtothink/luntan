package com.liu.luntan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String salt; // 盐
    private String email;
    private int type; // 0：普通用户 1：超级管理员 2：版主
    private int status; // 0: 未激活 1：已激活
    private String activationCode; // 激活码
    private String headerUrl; // 用户头像url地址
    private Date createTime; // 用户注册时间

}
