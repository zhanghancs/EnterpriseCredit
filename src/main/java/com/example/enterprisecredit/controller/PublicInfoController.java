package com.example.enterprisecredit.controller;


import com.alibaba.fastjson.JSON;
import com.example.enterprisecredit.entity.Dto.PublicInfoDto;
import com.example.enterprisecredit.service.impl.PublicInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhufu
 * @since 2023-08-31
 */
@RestController
@RequestMapping("/publicInfo")
public class PublicInfoController {
     @Autowired
     PublicInfoServiceImpl publicInfoService;
    @GetMapping("/queryByCode")
    public String queryByCode(@RequestParam int stockCode){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            PublicInfoDto publicinfoDto = publicInfoService.queryByCode(stockCode);
            result.put("status", 200);
            result.put("data", publicinfoDto);
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
