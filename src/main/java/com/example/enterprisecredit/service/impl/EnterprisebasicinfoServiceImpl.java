package com.example.enterprisecredit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.enterprisecredit.entity.Enterprisebasicinfo;
import com.example.enterprisecredit.mapper.EnterprisebasicinfoMapper;
import com.example.enterprisecredit.service.IEnterprisebasicinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhufu
 * @since 2023-08-30
 */
@Service
public class EnterprisebasicinfoServiceImpl extends ServiceImpl<EnterprisebasicinfoMapper, Enterprisebasicinfo> implements IEnterprisebasicinfoService {
    @Autowired
    EnterprisebasicinfoMapper enterprisebasicinfoMapper;

    @Override
    public Enterprisebasicinfo getEnterpriseById(String creditCode) {
        //1)封装查询条件  QueryWrapper
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("creditCode", creditCode);
        //2)执行查询
        Enterprisebasicinfo enterprisebasicinfo = enterprisebasicinfoMapper.selectOne(wrapper);
        return enterprisebasicinfo;
    }

    @Override
    public List<Enterprisebasicinfo> getEnterpriseMax() {
        //1)封装查询条件  QueryWrapper
        QueryWrapper wrapper = new QueryWrapper();

        wrapper.orderByDesc("profit");
        wrapper.last("LIMIT 5");

        //2)执行查询
        List<Enterprisebasicinfo> resultList = enterprisebasicinfoMapper.selectList(wrapper);

        //3)提取查询结果
        return resultList;

    }

    @Override
    public List<Enterprisebasicinfo> getEnterprise(String area) {
        //1)封装查询条件  QueryWrapper
        QueryWrapper wrapper = new QueryWrapper();

        wrapper.eq("area", area);

        //2)执行查询
        List<Enterprisebasicinfo> resultList = enterprisebasicinfoMapper.selectList(wrapper);

        //3)提取查询结果
        return resultList;

    }

    @Override
    public List<String> getIndustry() {
        QueryWrapper wrapper = new QueryWrapper();


        //2)执行查询
        List<String> resultList = enterprisebasicinfoMapper.selectList(wrapper);

        //3)提取查询结果
        return resultList;


    }

    @Override
    public List<Enterprisebasicinfo> getby(String area, String transferMode, String industry) {

        QueryWrapper wrapper = new QueryWrapper();
        if (area != null) {
            wrapper.eq("area", area);
        }
        if (transferMode != null) {
            wrapper.eq("transferMode", transferMode);
        }
        if (industry != null) {
            wrapper.eq("industry", industry);
        }
        wrapper.last("LIMIT 5");

        //2)执行查询
        List<Enterprisebasicinfo> enterprisebasicinfo = enterprisebasicinfoMapper.selectList(wrapper);
        return enterprisebasicinfo;
    }

    public List<Enterprisebasicinfo> gettwo(String creditCode1, String creditCode2)
    {

        QueryWrapper<Enterprisebasicinfo> wrapper = new QueryWrapper<>();
        wrapper.in("creditCode", Arrays.asList(creditCode1, creditCode2));

        // 2) 执行查询
        List<Enterprisebasicinfo> enterprisebasicinfoList = enterprisebasicinfoMapper.selectList(wrapper);

        return enterprisebasicinfoList;

    }

}
