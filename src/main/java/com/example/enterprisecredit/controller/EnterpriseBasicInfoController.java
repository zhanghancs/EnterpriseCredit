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

    //接收前端发送的int类型的股票编码，然后根据股票编码返回单个EnterpriseBasicInfo数据，或者未找到数据返回空，然后将数据返回到前端
    @RequestMapping(value="/queryEnterpriseBasicInfoByCode")
    public String queryEnterpriseBasicInfoByCode(int stockCode){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            EnterpriseBasicInfo enterpriseBasicInfo = EnterpriseBasicInfoService.
                    queryEnterpriseBasicInfoByCode(stockCode);
            result.put("status",200);
            result.put("data", enterpriseBasicInfo);
        }catch (Exception ex){
            result.put("status",500);
            result.put("msg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

    //接收前端发送的查找请求，从数据库中查找利润最高的前五企业，然后返回List<EnterpriseBasicInfo>，后端将数据发送到前端
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

    //接收前端的多条件查宅请求，筛选出数据库所有满足条件的数据，返回List<EnterpriseBasicInfo>，然后将List打包封装好发送到前端
    @RequestMapping(value="/queryByIndex")
    public String queryByIndex(String area, String transferMode,String industry){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            List <EnterpriseBasicInfo> enterpriseBasicInfo = EnterpriseBasicInfoService.queryByIndex(area,
                    transferMode, industry);
            result.put("status", 200);
            result.put("data", enterpriseBasicInfo);
        }catch (Exception ex){
            result.put("status",500);
            result.put("msg","出现异常:" + ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }
    /*接收前端的发送地址、交易类型、产业、和速度，根据发送地址、交易类型、产业数据从数据库查找符合条件的数据，分别按照利润最高获获取前五企业，
    然后按照收入最高获取前五企业，然后根据前端发送的速度要求对产业财务进行筛选统计，给出预测数据*/
    @RequestMapping(value="/queryByCondition")
    public String queryByCondition(String area,String transferMode,String industry,int speed){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            List<List<EnterpriseBasicInfo>> Enter =EnterpriseBasicInfoService.queryTop150ByIncomeAndProfit(area, transferMode, industry);
            // 获取前五家收入最高的企业信息并转化为Income对象列表
            List <EnterpriseBasicInfo> EnterpriseBasicInfo = Enter.get(0);
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

            List <Profit> EnterpriseBasicInfo2 = new ArrayList<>();
            // 获取前五家利润最高的企业信息并转化为Profit对象列表
            EnterpriseBasicInfo=Enter.get(1);
            int limit1 = Math.min(5, EnterpriseBasicInfo.size()); // 避免越界
            for (int i = 0; i < limit; i++) {
                Profit profit =new Profit();
                profit.profit=EnterpriseBasicInfo.get(i).getProfit();
                profit.companyName=EnterpriseBasicInfo.get(i).getName();
                profit.stockCode=EnterpriseBasicInfo.get(i).getStockCode();
                EnterpriseBasicInfo2.add(profit);
            }

            result.put("data2", EnterpriseBasicInfo2);
            List<FinancialInfoDto> financialInfoDtoList = new ArrayList<>();
            // 根据速度要求对产业财务数据进行筛选，获取预测数据
            for (int i = 0; i < EnterpriseBasicInfo.size(); i++) {

                if (financialInfoService.queryByCode(Integer.parseInt(EnterpriseBasicInfo.get(i).getStockCode())) != null) {
                    financialInfoDtoList.add(financialInfoService.queryByCode(Integer.parseInt(EnterpriseBasicInfo.get(i).getStockCode())));
                }
                Random r = new Random();
                int num = Integer.max(EnterpriseBasicInfo.size() /speed,1);//调整运算速度
                i += num;
            }

            List<Predict> predictList = new ArrayList<>();
            // 计算并获取预测数据
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
    //根据接收前端的两个股票编码，然后从数据库中查找出对应的两个企业，然后后端返回这两个企业数据
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
    /*接收前端发送的关键词、页数、页尺寸数据，后端根据关键词筛选出所有数据，同时统计出所有统计数据的总数量，然后根据页号
    与页尺码返回这些数据List，和数据总数量*/
    @RequestMapping(value="/queryEnterpriseByKeyword")
    public String queryEnterpriseByKeyword(String keyword ,int page ,int pageSize){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            IPage<EnterpriseBasicInfo> resultList  = EnterpriseBasicInfoService.queryEnterpriseByKeyword(keyword,page,pageSize);
            result.put("status",200);
            result.put("total", resultList.getTotal());
            result.put("data",resultList);

        }catch (Exception ex){
            result.put("status",500);
            result.put("errorMsg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }
    //接收前端发送的用户名和股票编码，从数据库中根据股票编码来筛选企业，发送企业数据
    //同时检索这个企业是否被该用户关注，如果关注返回flag=1，没有关注返回0，
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

