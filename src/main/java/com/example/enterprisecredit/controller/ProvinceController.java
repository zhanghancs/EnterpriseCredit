package com.example.enterprisecredit.controller;

import com.alibaba.fastjson.JSON;
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

    @GetMapping("/queryAll")
    public String queryAll() {
        Map<String, Object> result = new HashMap<String, Object>();

        List<Province> provinceList = null;
        provinceList = provinceService.queryAll();

        List<Map<String, Object>> formattedProvinceList = new ArrayList<>();
        for (Province province : provinceList) {
            Map<String, Object> formattedProvince = new HashMap<>();
            formattedProvince.put("area", province.getArea());
            formattedProvince.put("number", province.getNumber());
            formattedProvince.put("profit", province.getProfit());
            formattedProvince.put("income", province.getIncome());
            formattedProvince.put("topTen", province.formatTopTen());
            formattedProvince.put("toptenprofit",province.formatTopTenProfit());
            formattedProvince.put("toptenincome",province.formatTopTenIncome());
            formattedProvinceList.add(formattedProvince);
        }

        result.put("data", formattedProvinceList);
        // result.put("data", 2);
        return JSON.toJSONString(result);

    }
    @GetMapping("/market")
    public String market() {
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            List<Map<String,Object>> orderList = provinceService.market();
            if(orderList!=null && orderList.size()>0){ //检索到
                result.put("status", 200);
                result.put("data",orderList);
            }else{ //没有检索"到
                result.put("status", -1);
                result.put("errorMsg","没有检索到信息");
            }
        }catch (Exception ex){ //出现异常
            result.put("status", 500);
            result.put("errorMsg","出现异常:"+ex.getMessage());
        }
        return JSON.toJSONString(result);
    }
}
