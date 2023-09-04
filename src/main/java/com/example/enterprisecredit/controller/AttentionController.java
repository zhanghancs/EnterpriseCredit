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
        int res = attentionService.insertAttention(attention);
        result.put("data", res);
        return JSON.toJSONString(result);

    }

    @PostMapping("/delete")
    public String deleteAttention(@RequestBody Attention attention) {
        Map<String, Object> result = new HashMap<String, Object>();
        int res = attentionService.deleteAttention(attention);
        return JSON.toJSONString(result);

    }

    @GetMapping("/queryByUsername")
    public String queryByUsername(@RequestParam String username) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Attention> attentionList = attentionService.queryByUsername(username);
        result.put("data", attentionList);
        return JSON.toJSONString(result);

    }

}
