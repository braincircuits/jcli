package com.m2891.dao;

import com.m2891.pojo.entity.SysUser;

import java.util.List;

public interface UserDao
{
    /**
     * 根据邮箱查找user
     */
    SysUser findUserByEmail(String email);

    /**
     * 根据昵称查询用户
     */
    SysUser findUserByNickName(String nickName);

    /**
     * 查询积分最高的十个人
     */
    List<SysUser> byScore();
}
