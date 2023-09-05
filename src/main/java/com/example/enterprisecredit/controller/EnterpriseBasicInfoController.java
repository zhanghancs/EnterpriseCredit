package com.example.enterprisecredit.controller;


import com.alibaba.fastjson.JSON;
import com.example.enterprisecredit.entity.EnterpriseBasicInfo;
import com.example.enterprisecredit.service.impl.EnterpriseBasicInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/enterpriseBasicInfo")
public class EnterpriseBasicInfoController {
    @Autowired
    EnterpriseBasicInfoServiceImpl enterpriseBasicInfoService;
    @RequestMapping(value="/queryEnterpriseBasicInfoByCode")
    public String queryEnterpriseBasicInfoByCode(int stockCode){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            EnterpriseBasicInfo enterprisebasicinfo = enterpriseBasicInfoService.queryEnterpriseBasicInfoByCode(stockCode);
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
            List<EnterpriseBasicInfo> resultList  = enterpriseBasicInfoService.getEnterpriseMax();
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
            List <EnterpriseBasicInfo> enterpriseBasicInfo = enterpriseBasicInfoService.getby(area, transferMode, industry);
            result.put("status",200);
            result.put("data",enterpriseBasicInfo);
        }catch (Exception ex){
            result.put("status",500);
            result.put("msg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }
    @RequestMapping(value="/gettwo")
    public String gettwo(String creditCode1 ,String creditCode2){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            List <EnterpriseBasicInfo> enterpriseBasicInfo = enterpriseBasicInfoService.gettwo(creditCode1,creditCode2);
            result.put("status",200);
            result.put("data",enterpriseBasicInfo);
        }catch (Exception ex){
            result.put("status",500);
            result.put("msg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }
    @RequestMapping(value="/searchEnterprisesByKeyword")
    public String searchEnterprisesByKeyword(String Keyword ,int Page ,int PageSize){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            //1)调用userService的 查询单个对象的方法
            List<EnterpriseBasicInfo> resultList  = enterpriseBasicInfoService.searchEnterprisesByKeyword(Keyword,Page,PageSize);
            result.put("status",200);

            result.put("data",resultList);
            result.put("msg","成功查询");
        }catch (Exception ex){
            result.put("status",500);
            result.put("data",null);
            result.put("msg","出现异常:"+ex.getMessage());
            ex.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

}
