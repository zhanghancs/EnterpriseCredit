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
    @PostMapping("/insert")
    public String insertAttention(@RequestBody Attention attention) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            int res = attentionService.insertAttention(attention);
            if (res != 1) {
                result.put("status", 400);
                result.put("data", res);
                result.put("msg", "该用户已关注");
            } else {
                result.put("status", 200);
                result.put("data", res);
                result.put("msg", "关注成功");
            }
        } catch(Exception ex) {
            result.put("status", 500);
            result.put("data", null);
            result.put("msg", "异常:" + ex.getMessage());
        }
        return JSON.toJSONString(result);

    }

    @PostMapping("/delete")
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
