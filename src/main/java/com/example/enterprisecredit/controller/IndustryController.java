package com.example.enterprisecredit.controller;


import com.alibaba.fastjson.JSON;
import com.example.enterprisecredit.entity.Industry;
import com.example.enterprisecredit.service.impl.IndustryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhufu
 * @since 2023-08-30
 */
@RestController
@RequestMapping("/industry")
public class IndustryController {
    @Autowired
    IndustryServiceImpl industryService;
    @RequestMapping(value="/query")
    public String queryAll(int type){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            List<Industry> resultList  = industryService.queryAll(type);
            List<String> industryNames = new ArrayList<>();
            for (Industry industry : resultList) {
                industryNames.add(industry.getIndustry());
            }
            result.put("status",200);

            result.put("data",industryNames);

        }catch (Exception ex){
            result.put("status",500);
            result.put("errorMsg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }
}
