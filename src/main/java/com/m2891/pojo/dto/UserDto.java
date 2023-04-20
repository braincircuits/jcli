package com.m2891.pojo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto
{
    private Integer userId;
    private String nickName;
    private String email;
    private Integer sex;
    private String avatar;
    private String loginIp;
    private Date loginDate;
    private boolean emailVerified;
}
