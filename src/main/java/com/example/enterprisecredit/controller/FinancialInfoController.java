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

    @GetMapping("/queryByCode")
    public String queryByCode(@RequestParam int stockCode) {
        Map<String,Object> result = new HashMap<String,Object>();
        FinancialInfoDto financialInfoDto = financialInfoService.queryByCode(stockCode);
        result.put("data", financialInfoDto);
        return JSON.toJSONString(result);
    }
}