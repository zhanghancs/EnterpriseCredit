package com.example.enterprisecredit.controller;


import com.alibaba.fastjson.JSON;
import com.example.enterprisecredit.entity.Attention;
import com.example.enterprisecredit.service.impl.AttentionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhufu
 * @since 2023-08-28
 */
@RestController
@RequestMapping("/attention")
public class AttentionController {

    @Autowired
    AttentionServiceImpl attentionService;
    //接收前端Post请求发送的Attention的json数据，然后将数据插入到数据库中
    @PostMapping("/insert")
    public String insertAttention(@RequestBody Attention attention) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            int res = attentionService.insertAttention(attention);
            result.put("status", 200);
            result.put("data", res);
            result.put("msg", "关注成功");

        } catch(Exception ex) {
            result.put("status", 500);
            result.put("data", null);
            result.put("msg", "异常:" + ex.getMessage());
        }
        return JSON.toJSONString(result);

    }
    //接收前端用delete请求发送的Attenton的接送数据，然后从数据库中删除这个Attention数据
    @DeleteMapping("/delete")
    public String deleteAttention(@RequestBody Attention attention) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            int res = attentionService.deleteAttention(attention);
            if (res != 1) {
                result.put("status", 400);
                result.put("data", res);
                result.put("msg", "该用户已取消关注");
            } else {
                result.put("status", 200);
                result.put("data", res);
                result.put("msg", "取消关注成功");
            }
        }
        catch(Exception ex) {
            result.put("status", 500);
            result.put("data", null);
            result.put("msg", "异常:" + ex.getMessage());
        }
        return JSON.toJSONString(result);

    }

    //接收前端get请求的String类型用户名，然后根据这个用户名从数据库中筛选出所有符合条件的信息
    @GetMapping("/queryByUsername")
    public String queryByUsername(@RequestParam String username) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            List<Attention> attentionList = attentionService.queryByUsername(username);
            result.put("status", 200);
            result.put("data", attentionList);
            result.put("msg", "成功查询");
        }
        catch(Exception ex) {
            result.put("status", 500);
            result.put("data", null);
            result.put("msg", "异常:" + ex.getMessage());
        }
        return JSON.toJSONString(result);
    }
}
