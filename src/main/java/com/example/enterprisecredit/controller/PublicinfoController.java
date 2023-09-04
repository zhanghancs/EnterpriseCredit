package com.example.enterprisecredit.controller;


import com.alibaba.fastjson.JSON;
import com.example.enterprisecredit.entity.Dto.Publicinfohelper;
import com.example.enterprisecredit.entity.Enterprisebasicinfo;
import com.example.enterprisecredit.entity.Publicinfo;
import com.example.enterprisecredit.service.impl.PublicinfoServiceImpl;
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
 * @since 2023-08-31
 */
@RestController
@RequestMapping("/publicinfo")
public class PublicinfoController {
     @Autowired
    PublicinfoServiceImpl publicinfoService;
    @RequestMapping(value="/get")
    public String queryUserById(String creditCode){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            Publicinfo publicinfo = publicinfoService.getById(creditCode);
            Publicinfohelper publicinfohelper = new Publicinfohelper();
            List<String> res =publicinfohelper.get(publicinfo);
            result.put("status",200);
            result.put("data",res);
        }catch (Exception ex){
            result.put("status",500);
            result.put("msg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }
}
