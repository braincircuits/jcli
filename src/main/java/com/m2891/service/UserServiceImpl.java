package com.m2891.service;

import com.m2891.dao.UserDao;
import com.m2891.pojo.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDao userDao;
    @Override
    public SysUser findUserByEmail(String email)
    {
        return userDao.findUserByEmail(email);
    }


    @Override
    public SysUser findUserByNickName(String nickName)
    {
        return userDao.findUserByEmail(nickName);
    }

    @Override
    public List<SysUser> byScore()
    {
        return userDao.byScore();
    }
}
