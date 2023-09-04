package com.example.enterprisecredit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.enterprisecredit.entity.Dto.Income;
import com.example.enterprisecredit.entity.Dto.Industry;
import com.example.enterprisecredit.entity.Dto.Profit;
import com.example.enterprisecredit.entity.Province;
import com.example.enterprisecredit.mapper.ProvinceMapper;
import com.example.enterprisecredit.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province> implements IProvinceService {
    @Autowired
    ProvinceMapper provinceMapper;

    public List<Province> queryAll() {
        List<Province> provinceList = null;

        QueryWrapper<Province> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("number"); // 按照 number 字段的大小从大到小排序

        provinceList = provinceMapper.selectList(wrapper);

        for(Province province : provinceList) {
            List<Industry> industryList = provinceMapper.queryAllIndustry(province.getArea());
            List<Profit> profitList = provinceMapper.queryAllProfit(province.getArea());
            List<Income> incomeList =provinceMapper.queryAllIncome(province.getArea());
            province.setTopTen(industryList);
            province.setTopTenProfit(profitList);
            province.setTopTenIncome(incomeList);
        }


        return provinceList;
    }
    public List<Map<String, Object>> market() {
        String sql ="select * from market";
        return provinceMapper.queryUsersByCondition(sql);
    }
}
