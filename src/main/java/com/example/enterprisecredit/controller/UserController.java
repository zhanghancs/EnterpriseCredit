package com.example.enterprisecredit.controller;

import com.alibaba.fastjson.JSON;
import com.example.enterprisecredit.entity.User;
import com.example.enterprisecredit.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            int res = userService.login(user);
            int status = 200;
            String msg = "登录成功";
            if (res == 0) {
                status = 400;
                msg = "密码错误";
            }
            if (res == -1) {
                status = 400;
                msg = "不存在该用户";
            }
            result.put("status", status);
            result.put("data", res);
            result.put("msg", msg);
        } catch (Exception ex) {
            result.put("status", 500);
            result.put("data", null);
            result.put("msg", "异常:" + ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            int res = userService.register(user);
            int status = 200;
            String msg = "注册成功";
            if (res != 1) {
                status = 400;
                msg = "用户已经注册过";
            }
            result.put("status", status);
            result.put("data", res);
            result.put("msg", msg);
        } catch (Exception ex) {
            result.put("status", 500);
            result.put("data", null);
            result.put("msg", "异常:" + ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

}