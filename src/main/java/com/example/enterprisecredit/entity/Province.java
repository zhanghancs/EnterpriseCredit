package com.example.enterprisecredit.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.enterprisecredit.entity.Dto.Income;
import com.example.enterprisecredit.entity.Dto.Industry;
import com.example.enterprisecredit.entity.Dto.Profit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@TableName("province")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Province {

    @TableField("area")
    private String area;

    @TableField("number")
    private int number;

    @TableField("profit")
    private double profit;

    @TableField("income")
    private double income;

    @TableField(exist = false)
    private List<Industry> topTen;

    @TableField(exist = false)
    private List<Profit> topTenProfit;
    @TableField(exist = false)
    private List<Income> topTenIncome;
//    public List<Map<String, Object>> formatTopTen() {
//        List<Map<String, Object>> formattedTopTen = new ArrayList<>();
//        for (Industry industry : topTen) {
//            Map<String, Object> item = new HashMap<>();
//            item.put("industry", industry.getIndustry());
//            item.put("number", industry.getNumber());
//            item.put("profit", industry.getProfit());
//            item.put("income", industry.getIncome());
//            // 添加其他属性
//            formattedTopTen.add(item);
//        }
//        return formattedTopTen;
//    }
public List<Map<String, Object>> formatTopTen() {
    List<Map<String, Object>> formattedTopTen = new ArrayList<>();
    int remainingItems = 10; // 记录剩余需要处理的元素数量

    for (int i = 0; i < 10; i++) {
        Map<String, Object> item = new HashMap<>();
        if (i < topTen.size()) {
            Industry industry = topTen.get(i);
            item.put("industry", industry.getIndustry());
            item.put("number", industry.getNumber());
            item.put("profit", industry.getProfit());
            item.put("income", industry.getIncome());
        } else {
            // 如果topTen中没有足够的元素，将属性填充为0
            item.put("industry", "0");
            item.put("number", 0);
            item.put("profit", 0);
            item.put("income", 0);
        }

        formattedTopTen.add(item);
    }

    return formattedTopTen;
}







//    public List<Map<String, Object>> formatTopTenProfit() {
//        List<Map<String, Object>> formattedTopTen = new ArrayList<>();
//        for (Profit profit : topTenProfit) {
//            Map<String, Object> item = new HashMap<>();
//            item.put("industry",profit.getIndustry());
//
//            item.put("profit", profit.getProfit());
//
//            // 添加其他属性
//            formattedTopTen.add(item);
//        }
//        return formattedTopTen;
//    }
public List<Map<String, Object>> formatTopTenProfit() {
    List<Map<String, Object>> formattedTopTen = new ArrayList<>();
    int remainingItems = 10; // 记录剩余需要处理的元素数量

    for (int i = 0; i < 10; i++) {
        Map<String, Object> item = new HashMap<>();
        if (i < topTenProfit.size()) {
            Profit profit = topTenProfit.get(i);
            item.put("industry", profit.getIndustry());
            item.put("profit", profit.getProfit());
            // 添加其他属性
        } else {
            // 如果topTenProfit中没有足够的元素，将属性填充为0
            item.put("industry", "0");
            item.put("profit", 0);
            // 添加其他属性并设置为0
        }

        formattedTopTen.add(item);
    }

    return formattedTopTen;
}
    public List<Map<String, Object>> formatTopTenIncome() {
        List<Map<String, Object>> formattedTopTen = new ArrayList<>();
        int remainingItems = 10; // 记录剩余需要处理的元素数量

        for (int i = 0; i < 10; i++) {
            Map<String, Object> item = new HashMap<>();
            if (i < topTenIncome.size()) {
                Income income = topTenIncome.get(i);
                item.put("industry", income.getIndustry());
                item.put("income", income.getIncome());
                // 添加其他属性
            } else {
                // 如果topTenIncome中没有足够的元素，将属性填充为0
                item.put("industry", "0");
                item.put("income", 0);
                // 添加其他属性并设置为0
            }

            formattedTopTen.add(item);
        }

        return formattedTopTen;
    }

//    public List<Map<String, Object>> formatTopTenIncome() {
//        List<Map<String, Object>> formattedTopTen = new ArrayList<>();
//        for (Income income : topTenIncome) {
//            Map<String, Object> item = new HashMap<>();
//            item.put("industry",income.getIndustry());
//
//            item.put("income", income.getIncome());
//
//            // 添加其他属性
//            formattedTopTen.add(item);
//        }
//        return formattedTopTen;
//    }
}
