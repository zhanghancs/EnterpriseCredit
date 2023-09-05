package com.example.enterprisecredit.controller;


import com.alibaba.druid.sql.parser.Keywords;
import com.alibaba.fastjson.JSON;
import com.example.enterprisecredit.entity.Dto.FinancialInfoDto;
import com.example.enterprisecredit.entity.Dto.Income;
import com.example.enterprisecredit.entity.Dto.Predict;
import com.example.enterprisecredit.entity.Dto.Profit;
import com.example.enterprisecredit.entity.Enterprisebasicinfo;
import com.example.enterprisecredit.service.impl.AttentionServiceImpl;
import com.example.enterprisecredit.service.impl.EnterprisebasicinfoServiceImpl;
import com.example.enterprisecredit.service.impl.FinancialInfoServiceImpl;
import io.swagger.models.auth.In;
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
@RequestMapping("/enterprisebasicinfo")
public class EnterprisebasicinfoController {
    @Autowired
    EnterprisebasicinfoServiceImpl enterprisebasicinfoService;
    @Autowired
    AttentionServiceImpl attentionService;
    @Autowired
    FinancialInfoServiceImpl financialInfoService;
    @RequestMapping(value="/getEnterprisebasicinfoById")
    public String queryUserById(String creditCode){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            Enterprisebasicinfo enterprisebasicinfo = enterprisebasicinfoService.getEnterpriseById(creditCode);
            result.put("status",200);
            result.put("data",enterprisebasicinfo);
        }catch (Exception ex){
            result.put("status",500);
            result.put("msg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

    //http://localhost:8080/enterprisebasicinfo/getEnterprisebasicinfoprofitMax
    @RequestMapping(value="/getEnterprisebasicinfoprofitMax")
    public String getEnterpriseMax(){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            List<Enterprisebasicinfo> resultList  = enterprisebasicinfoService.getEnterpriseMax();
            result.put("status",200);

            result.put("data",resultList);

        }catch (Exception ex){
            result.put("status",500);
            result.put("errorMsg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

    @RequestMapping(value="/getby")
    public String getby(String area,String transferMode,String industry){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            List <Enterprisebasicinfo> enterprisebasicinfo = enterprisebasicinfoService.getby(area, transferMode, industry);
            result.put("status",200);
            result.put("data",enterprisebasicinfo);
        }catch (Exception ex){
            result.put("status",500);
            result.put("msg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }
    @RequestMapping(value="/getbyplus")
    public String getbyplus(String area,String transferMode,String industry){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            List <Enterprisebasicinfo> enterprisebasicinfo = enterprisebasicinfoService.getby(area, transferMode, industry);
            for(int i = 0; i < enterprisebasicinfo.size(); i++) {
                for (int j = i + 1; j < enterprisebasicinfo.size(); j++) {
                    double num1 = Integer.MIN_VALUE;
                    double num2 = Integer.MIN_VALUE;
                    num1 =enterprisebasicinfo.get(i).getIncome();
                    num2 =enterprisebasicinfo.get(j).getIncome();
                    if (num1 < num2) {
                        Collections.swap(enterprisebasicinfo, i, j);
                    }
                }
            }
            List <Income> enterprisebasicinfo1 = new ArrayList<>();
            int limit = Math.min(5, enterprisebasicinfo.size()); // 避免越界
            for (int i = 0; i < limit; i++) {
            Income  income =new Income();
            income.income=enterprisebasicinfo.get(i).getIncome();
            income.companyName=enterprisebasicinfo.get(i).getName();
            income.stockCode=enterprisebasicinfo.get(i).getStockCode();
            enterprisebasicinfo1.add(income);
            }

            result.put("status",200);
            result.put("data1", enterprisebasicinfo1);
            for(int i = 0; i < enterprisebasicinfo.size(); i++) {
                for (int j = i + 1; j < enterprisebasicinfo.size(); j++) {
                    double num1 = Integer.MIN_VALUE;
                    double num2 = Integer.MIN_VALUE;
                   num1 =enterprisebasicinfo.get(i).getProfit();
                   num2 =enterprisebasicinfo.get(j).getProfit();
                    if (num1 < num2) {
                        Collections.swap(enterprisebasicinfo, i, j);
                    }
                }
            }
            List <Profit> enterprisebasicinfo2 = new ArrayList<>();
            int limit1 = Math.min(5, enterprisebasicinfo.size()); // 避免越界
            for (int i = 0; i < limit; i++) {
                Profit profit =new Profit();
                profit.profit=enterprisebasicinfo.get(i).getProfit();
                profit.companyName=enterprisebasicinfo.get(i).getName();
                profit.stockCode=enterprisebasicinfo.get(i).getStockCode();
                enterprisebasicinfo2.add(profit);
            }

            result.put("data2", enterprisebasicinfo2);
            List<FinancialInfoDto> financialInfoDtoList =new ArrayList<>();
            for(int i=0;i<enterprisebasicinfo.size();i++)
            {
                financialInfoDtoList.add(financialInfoService.queryByCode(Integer.parseInt(enterprisebasicinfo.get(i).getStockCode()))) ;
            }
            List<Predict> predictList = new ArrayList<>();
            for(int i =0;i<5;i++)
            {
                Double income =0.0;
                Double profit =0.0;
                int date =2020;
                for(int j =0;j<financialInfoDtoList.size();j++)
                {
                    income+=financialInfoDtoList.get(j).getTotalRevenue().get(i);
                    profit+=financialInfoDtoList.get(j).getProfit().get(i);

                }
                date+=i;
                Predict predict =new Predict();
                predict.aveIncome=income/financialInfoDtoList.size();
                predict.aveProfit=profit/financialInfoDtoList.size();
                predict.Date=String.valueOf(date);
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
    @RequestMapping(value="/gettwo")
    public String gettwo(String creditCode1 ,String  creditCode2){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            List <Enterprisebasicinfo> enterprisebasicinfo = enterprisebasicinfoService.gettwo(creditCode1,creditCode2);
            result.put("status",200);
            result.put("data",enterprisebasicinfo);
        }catch (Exception ex){
            result.put("status",500);
            result.put("msg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }
    @RequestMapping(value="/searchEnterprisesByKeyword")
    public String searchEnterprisesByKeyword( String Keyword ,int Page ,int PageSize){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            List<Enterprisebasicinfo> resultList  = enterprisebasicinfoService.searchEnterprisesByKeyword(Keyword,Page,PageSize);
            result.put("status",200);
            result.put("total",enterprisebasicinfoService.total);
            result.put("data",resultList);

        }catch (Exception ex){
            result.put("status",500);
            result.put("errorMsg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }
    @RequestMapping(value="/searchEnterprisePlus")
    public String searchEnterprisePlus( String creditCode ,String name ,int stockcode){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            Enterprisebasicinfo enterprisebasicinfo = enterprisebasicinfoService.getEnterpriseById(creditCode);

            result.put("status",200);
            result.put("data",enterprisebasicinfo);
            int flag = attentionService.status(name ,stockcode);
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
