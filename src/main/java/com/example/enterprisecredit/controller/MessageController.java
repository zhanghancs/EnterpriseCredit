package com.example.enterprisecredit.controller;


import com.alibaba.fastjson.JSON;
import com.example.enterprisecredit.entity.Message;
import com.example.enterprisecredit.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhufu
 * @since 2023-09-01
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageServiceImpl messageService;
    @RequestMapping(value="/get")
    public String getEnterpriseMax(){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            List<Message> resultList  = messageService.get();
            result.put("status",200);

            result.put("data",resultList);

        }catch (Exception ex){
            result.put("status",500);
            result.put("errorMsg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }
}
