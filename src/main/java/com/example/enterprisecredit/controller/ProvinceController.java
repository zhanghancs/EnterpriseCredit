package com.example.enterprisecredit.controller;

import com.alibaba.fastjson.JSON;
import com.example.enterprisecredit.entity.Dto.MarketDto;
import com.example.enterprisecredit.entity.Province;
import com.example.enterprisecredit.service.impl.ProvinceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 接口2，获取市场信息
  */
@RestController
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    ProvinceServiceImpl provinceService;
    //根据前端发送的请求，从数据库中获取所有的省份信息，同时将数据json格式化，并将其发送到前端
    @GetMapping("/queryAllProvince")
    public String queryAllProvince() {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            List<Province> provinceList = null;
            provinceList = provinceService.queryAllProvince();

            List<Map<String, Object>> formattedProvinceList = new ArrayList<>();
            for (Province province : provinceList) {
                Map<String, Object> formattedProvince = new HashMap<>();
                formattedProvince.put("area", province.getArea());
                formattedProvince.put("number", province.getNumber());
                formattedProvince.put("profit", province.getProfit());
                formattedProvince.put("income", province.getIncome());
                formattedProvince.put("score", province.getScore());
                formattedProvince.put("topTenNum", province.getTopTenNum());
                formattedProvince.put("topTenProfit", province.getTopTenProfitDto());
                formattedProvince.put("topTenIncome", province.getTopTenIncomeDto());
                formattedProvinceList.add(formattedProvince);
            }
            result.put("status", 200);
            result.put("data", formattedProvinceList);
            result.put("msg", "成功查询");
        }
        catch(Exception ex) {
            result.put("status", 500);
            result.put("data", null);
            result.put("msg", "异常:" + ex.getMessage());
        }
        return JSON.toJSONString(result);

    }
    //根据前端发送的请求，从数据库中获取所有的市场信息，并将其发送到前端
    @GetMapping("/queryAllMarket")
    public String queryAllMarket() {
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            List<MarketDto> marketList = provinceService.queryAllMarket();
            if(marketList != null && !marketList.isEmpty()){ //检索到
                result.put("status", 200);
                result.put("data",marketList);
                result.put("msg", "成功查询");
            }else{ //没有检索"到
                result.put("status", 400);
                result.put("data", null);
                result.put("msg","没有检索到信息");
            }
        }catch (Exception ex){ //出现异常
            result.put("status", 500);
            result.put("data", null);
            result.put("msg","出现异常:"+ex.getMessage());
        }
        return JSON.toJSONString(result);
    }
}
