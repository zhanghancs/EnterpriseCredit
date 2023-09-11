package com.example.enterprisecredit.controller;


import com.alibaba.fastjson.JSON;
import com.example.enterprisecredit.entity.Dto.FinancialInfoDto;
import com.example.enterprisecredit.service.impl.FinancialInfoServiceImpl;
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
@RequestMapping("/financialInfo")
public class FinancialInfoController {
    @Autowired
    FinancialInfoServiceImpl financialInfoService;
    //接收前端发送的Get请求和股票编码，根据股票编码从数据库中筛选企业财务数据，然后返回数据
    @GetMapping("/queryByCode")
    public String queryByCode(@RequestParam int stockCode) {
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            FinancialInfoDto financialInfoDto = financialInfoService.queryByCode(stockCode);
            result.put("status", 200);
            result.put("data", financialInfoDto);
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