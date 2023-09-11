package com.example.enterprisecredit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.enterprisecredit.entity.User;
import com.example.enterprisecredit.mapper.UserMapper;
import com.example.enterprisecredit.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 登录
     * @param user 用户对象
     * @return 1 表示成功，-1 表示用户不存在，0 表示密码错误
     */
    @Override
    public int login(User user) {
        User newUser = userMapper.selectById(user.getName());
        if (newUser == null) {
            return -1;
        } else if(user.getPassword().equals(newUser.getPassword())) {
            return 1;
        }
        return 0;
    }

    /**
     * 注册
     * @param user 用户对象
     * @return 0 表示用户已存在，1 表示注册成功
     */
    @Override
    public int register(User user) {
        User oldUser = userMapper.selectById(user.getName());
        if (oldUser != null) return 0;

        return userMapper.insert(user);
    }

}
