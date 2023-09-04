package com.example.enterprisecredit.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.example.enterprisecredit.entity.User;



public interface IUserService extends IService<User> {
    public int login(User user);
    public int register(User user);

}
