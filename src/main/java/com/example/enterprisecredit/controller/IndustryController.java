package com.example.enterprisecredit.controller;


import com.alibaba.fastjson.JSON;
import com.example.enterprisecredit.entity.EnterpriseBasicInfo;
import com.example.enterprisecredit.entity.Industry;
import com.example.enterprisecredit.service.impl.IndustryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
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
    //根据前端发送的Get请求，有字符表前序字母，字母表后续字母，从数据库中查找所有首字母在这之间的行业，然返回这些行业
    @GetMapping(value="/queryByAlphabet")
    public String queryByAlphabet(@RequestParam String first ,@RequestParam String last){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            List<Industry> resultList  = industryService.queryByAlphabet(first,last);
            List<String> industryNames = new ArrayList<>();
            for (Industry industry : resultList) {
                industryNames.add(industry.getIndustry());
            }
            result.put("status",200);
            result.put("data",industryNames);

        }catch (Exception ex){
            result.put("status",500);
            result.put("msg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

}
