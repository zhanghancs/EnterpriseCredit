package com.example.enterprisecredit.controller;


import com.alibaba.druid.sql.parser.Keywords;
import com.alibaba.fastjson.JSON;
import com.example.enterprisecredit.entity.Enterprisebasicinfo;
import com.example.enterprisecredit.service.impl.AttentionServiceImpl;
import com.example.enterprisecredit.service.impl.EnterprisebasicinfoServiceImpl;
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
@RequestMapping("/enterprisebasicinfo")
public class EnterprisebasicinfoController {
    @Autowired
    EnterprisebasicinfoServiceImpl enterprisebasicinfoService;
    @Autowired
    AttentionServiceImpl attentionService;
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
