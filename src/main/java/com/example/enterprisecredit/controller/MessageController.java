package com.example.enterprisecredit.controller;


import com.alibaba.fastjson.JSON;
import com.example.enterprisecredit.entity.Dto.MessageDto;
import com.example.enterprisecredit.entity.Message;
import com.example.enterprisecredit.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
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
    //根据前端发送的请求，从数据库中获取最新的20条消息，并将其发送到前端
    @RequestMapping(value="/queryMessage")
    public String queryMessage(){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            List<Message> resultList  = messageService.queryMessage();
            result.put("status",200);
            result.put("data",resultList);
            result.put("msg", "成功查询");
        }catch (Exception ex){
            result.put("status",500);
            result.put("data", null);
            result.put("msg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }
    //根据前端发送的请求和用户名，从数据中筛选出所有用户名符合的消息，并将其发送到前端
    @GetMapping("/queryMessageByName")
    public String queryMessageByName(@RequestParam String name) {
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            List<MessageDto> resultList  = messageService.queryMessageByName(name);
            result.put("status",200);
            result.put("data",resultList);
            result.put("number", resultList.size());
            result.put("msg", "成功查询");
        }catch (Exception ex){
            result.put("status",500);
            result.put("data", null);
            result.put("msg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }
}
