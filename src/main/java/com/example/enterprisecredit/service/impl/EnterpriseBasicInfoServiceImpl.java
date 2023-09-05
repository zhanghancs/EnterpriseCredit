package com.example.enterprisecredit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.enterprisecredit.entity.EnterpriseBasicInfo;
import com.example.enterprisecredit.mapper.EnterpriseBasicInfoMapper;
import com.example.enterprisecredit.service.IEnterpriseBasicInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class EnterpriseBasicInfoServiceImpl extends ServiceImpl<EnterpriseBasicInfoMapper, EnterpriseBasicInfo> implements IEnterpriseBasicInfoService {

    @Autowired
    EnterpriseBasicInfoMapper enterpriseBasicInfoMapper;
    public static long total = 0;
    @Override
    public EnterpriseBasicInfo queryEnterpriseBasicInfoByCode(int stockCode) {
        //1)封装查询条件  QueryWrapper
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("stockcode", stockCode);
        //2)执行查询
        EnterpriseBasicInfo EnterpriseBasicInfo = enterpriseBasicInfoMapper.selectOne(wrapper);
        return EnterpriseBasicInfo;
    }

    @Override
    public List<EnterpriseBasicInfo> queryTop5Profits() {
        //1)封装查询条件  QueryWrapper
        QueryWrapper wrapper = new QueryWrapper();

        wrapper.orderByDesc("profit");
        wrapper.last("LIMIT 5");

        //2)执行查询
        List<EnterpriseBasicInfo> resultList = enterpriseBasicInfoMapper.selectList(wrapper);

        //3)提取查询结果
        return resultList;

    }

    @Override
    public List<EnterpriseBasicInfo> getEnterprise(String area) {
        //1)封装查询条件  QueryWrapper
        QueryWrapper wrapper = new QueryWrapper();

        wrapper.eq("area", area);

        //2)执行查询
        List<EnterpriseBasicInfo> resultList = enterpriseBasicInfoMapper.selectList(wrapper);

        //3)提取查询结果
        return resultList;

    }

    @Override
    public List<String> getIndustry() {
        QueryWrapper wrapper = new QueryWrapper();


        //2)执行查询
        List<String> resultList = enterpriseBasicInfoMapper.selectList(wrapper);

        //3)提取查询结果
        return resultList;


    }

    @Override
    public List<EnterpriseBasicInfo> queryByIndex(String area, String transferMode, String industry) {

        QueryWrapper wrapper = new QueryWrapper();
        if (area != null&&area!="All") {
            wrapper.eq("area", area);
        }
        if (transferMode != null&&transferMode!="All") {
            wrapper.eq("transferMode", transferMode);
        }
        if (industry != null&&industry!="All") {
            wrapper.eq("industry", industry);
        }


        //2)执行查询
        List<EnterpriseBasicInfo> EnterpriseBasicInfo = enterpriseBasicInfoMapper.selectList(wrapper);
        return EnterpriseBasicInfo;
    }

    public List<EnterpriseBasicInfo> query2Enterprise(int stockCode1, int stockCode2)
    {
        QueryWrapper<EnterpriseBasicInfo> wrapper = new QueryWrapper<>();
        wrapper.in("stockcode", Arrays.asList(stockCode1, stockCode2));

        // 2) 执行查询
        List<EnterpriseBasicInfo> EnterpriseBasicInfoList = enterpriseBasicInfoMapper.selectList(wrapper);

        return EnterpriseBasicInfoList;

    }
    @Override
    public IPage<EnterpriseBasicInfo> queryEnterpriseByKeyword(String keyword, int p, int size) {
        QueryWrapper<EnterpriseBasicInfo> wrapper = new QueryWrapper<>();
        wrapper.or(
                i -> i.like("stockCode", "%" + keyword + "%")
                        .or()
                        .like("name", "%" + keyword + "%")
                        .or()
                        .like("shortname", "%" + keyword + "%")
                        .or()
                        .like("address", "%" + keyword + "%")
        );
        Page<EnterpriseBasicInfo> page = new Page<EnterpriseBasicInfo>(p, size);
        IPage<EnterpriseBasicInfo> results = enterpriseBasicInfoMapper.selectPage(page, wrapper);
        total = results.getTotal();
        return results;
    }


}
