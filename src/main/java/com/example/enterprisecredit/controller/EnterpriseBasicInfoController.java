package com.example.enterprisecredit.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.enterprisecredit.entity.Dto.FinancialInfoDto;
import com.example.enterprisecredit.entity.Dto.Income;
import com.example.enterprisecredit.entity.Dto.Predict;
import com.example.enterprisecredit.entity.Dto.Profit;
import com.example.enterprisecredit.entity.EnterpriseBasicInfo;
import com.example.enterprisecredit.service.impl.AttentionServiceImpl;
import com.example.enterprisecredit.service.impl.EnterpriseBasicInfoServiceImpl;
import com.example.enterprisecredit.service.impl.FinancialInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhufu
 * @since 2023-08-30
 */
@RestController
@RequestMapping("/enterpriseBasicInfo")
public class EnterpriseBasicInfoController {
    @Autowired
    EnterpriseBasicInfoServiceImpl EnterpriseBasicInfoService;
    @Autowired
    AttentionServiceImpl attentionService;
    @Autowired
    FinancialInfoServiceImpl financialInfoService;
    @RequestMapping(value="/queryEnterpriseBasicInfoByCode")
    public String queryEnterpriseBasicInfoByCode(int stockCode){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            EnterpriseBasicInfo EnterpriseBasicInfo = EnterpriseBasicInfoService.queryEnterpriseBasicInfoByCode(stockCode);
            result.put("status",200);
            result.put("data",EnterpriseBasicInfo);
        }catch (Exception ex){
            result.put("status",500);
            result.put("msg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

    //http://localhost:8080/EnterpriseBasicInfo/getEnterpriseBasicInfoprofitMax
    @RequestMapping(value="/queryTop5Profits")
    public String queryTop5Profits(){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            List<EnterpriseBasicInfo> resultList  = EnterpriseBasicInfoService.queryTop5Profits();
            result.put("status",200);

            result.put("data",resultList);

        }catch (Exception ex){
            result.put("status",500);
            result.put("msg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

    @RequestMapping(value="/queryByIndex")
    public String queryByIndex(String area, String transferMode,String industry){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            List <EnterpriseBasicInfo> EnterpriseBasicInfo = EnterpriseBasicInfoService.queryByIndex(area, transferMode, industry);
            result.put("status",200);
            result.put("data",EnterpriseBasicInfo);
        }catch (Exception ex){
            result.put("status",500);
            result.put("msg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }
    @RequestMapping(value="/queryByCondition")
    public String queryByCondition(String area,String transferMode,String industry){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            List <EnterpriseBasicInfo> EnterpriseBasicInfo = EnterpriseBasicInfoService.queryByIndex(area, transferMode, industry);
            for(int i = 0; i < EnterpriseBasicInfo.size(); i++) {
                for (int j = i + 1; j < EnterpriseBasicInfo.size(); j++) {
                    double num1 = Double.MIN_VALUE;
                    double num2 = Double.MIN_VALUE;
                    num1 =EnterpriseBasicInfo.get(i).getIncome();
                    num2 =EnterpriseBasicInfo.get(j).getIncome();
                    if (num1 < num2) {
                        Collections.swap(EnterpriseBasicInfo, i, j);
                    }
                }
            }
            List <Income> EnterpriseBasicInfo1 = new ArrayList<>();
            int limit = Math.min(5, EnterpriseBasicInfo.size()); // 避免越界
            for (int i = 0; i < limit; i++) {
                Income  income =new Income();
                income.income=EnterpriseBasicInfo.get(i).getIncome();
                income.companyName=EnterpriseBasicInfo.get(i).getName();
                income.stockCode=EnterpriseBasicInfo.get(i).getStockCode();
                EnterpriseBasicInfo1.add(income);
            }

            result.put("status",200);
            result.put("data1", EnterpriseBasicInfo1);
            for(int i = 0; i < EnterpriseBasicInfo.size(); i++) {
                for (int j = i + 1; j < EnterpriseBasicInfo.size(); j++) {
                    double num1 = Double.MIN_VALUE;
                    double num2 = Double.MIN_VALUE;
                    num1 =EnterpriseBasicInfo.get(i).getProfit();
                    num2 =EnterpriseBasicInfo.get(j).getProfit();
                    if (num1 < num2) {
                        Collections.swap(EnterpriseBasicInfo, i, j);
                    }
                }
            }
            List <Profit> EnterpriseBasicInfo2 = new ArrayList<>();
            int limit1 = Math.min(5, EnterpriseBasicInfo.size()); // 避免越界
            for (int i = 0; i < limit; i++) {
                Profit profit =new Profit();
                profit.profit=EnterpriseBasicInfo.get(i).getProfit();
                profit.companyName=EnterpriseBasicInfo.get(i).getName();
                profit.stockCode=EnterpriseBasicInfo.get(i).getStockCode();
                EnterpriseBasicInfo2.add(profit);
            }

            result.put("data2", EnterpriseBasicInfo2);
            List<FinancialInfoDto> financialInfoDtoList =new ArrayList<>();

            for(int i=0;i<EnterpriseBasicInfo.size();i++)
            {

                if(financialInfoService.queryByCode(Integer.parseInt(EnterpriseBasicInfo.get(i).getStockCode()))!=null)
                {
                    financialInfoDtoList.add(financialInfoService.queryByCode(Integer.parseInt(EnterpriseBasicInfo.get(i).getStockCode()))) ;
                }

            }

            List<Predict> predictList = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                Double income = 0.0;
                Double profit = 0.0;
                int date = 2020;

                for (int j = 0; j < financialInfoDtoList.size(); j++) {
                    List<Double> totalRevenue = financialInfoDtoList.get(j).getTotalRevenue();
                    List<Double> profitList = financialInfoDtoList.get(j).getProfit();

                    // 检查列表长度是否足够
                    if (totalRevenue.size() > i && profitList.size() > i) {
                        income += totalRevenue.get(i);
                        profit += profitList.get(i);
                    } else {
                        // 处理列表长度不足的情况，可以添加默认值或者其他处理逻辑
                    }
                }

                date += i;
                Predict predict = new Predict();
                predict.aveIncome = income / financialInfoDtoList.size();
                predict.aveProfit = profit / financialInfoDtoList.size();
                predict.Date = String.valueOf(date);
                predictList.add(predict);
            }

            result.put("data3", predictList);

        }catch (Exception ex){
            result.put("status",500);
            result.put("msg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }
    @RequestMapping(value="/query2Enterprise")
    public String query2Enterprise(int stockCode1 ,int  stockCode2){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            List <EnterpriseBasicInfo> EnterpriseBasicInfo = EnterpriseBasicInfoService.query2Enterprise(stockCode1,stockCode2);
            result.put("status",200);
            result.put("data",EnterpriseBasicInfo);
        }catch (Exception ex){
            result.put("status",500);
            result.put("msg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }
    @RequestMapping(value="/queryEnterpriseByKeyword")
    public String queryEnterpriseByKeyword(String keyword ,int page ,int pageSize){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            IPage<EnterpriseBasicInfo> resultList  = EnterpriseBasicInfoService.queryEnterpriseByKeyword(keyword,page,pageSize);
            result.put("status",200);
            result.put("total", EnterpriseBasicInfoService.total);
            result.put("data",resultList);

        }catch (Exception ex){
            result.put("status",500);
            result.put("errorMsg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }
    @RequestMapping(value="/queryEnterprisePlus")
    public String queryEnterprisePlus(String username, int stockCode){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            EnterpriseBasicInfo EnterpriseBasicInfo = EnterpriseBasicInfoService.queryEnterpriseBasicInfoByCode(stockCode);
            result.put("status",200);
            result.put("data",EnterpriseBasicInfo);
            int flag = attentionService.status(username ,stockCode);
            if(flag ==1)
            {
                result.put("flag",0);
            }
            else {
                result.put("flag",1);
            }
        }catch (Exception ex){
            result.put("status",500);
            result.put("msg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }
}

