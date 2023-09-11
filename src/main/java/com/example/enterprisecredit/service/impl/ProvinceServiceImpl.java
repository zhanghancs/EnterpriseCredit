package com.example.enterprisecredit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.enterprisecredit.entity.Dto.*;
import com.example.enterprisecredit.entity.Province;
import com.example.enterprisecredit.mapper.ProvinceMapper;
import com.example.enterprisecredit.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province> implements IProvinceService {
    @Autowired
    ProvinceMapper provinceMapper;

    /**
     * 所有省份的信息
     * @return 省份对象数组
     */
    @Override
    public List<Province> queryAllProvince() {
        List<Province> provinceList = null;

        // 从 province 表中读取 province 对象数组
        QueryWrapper<Province> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("number"); // 按照 number 字段的大小从大到小排序
        provinceList = provinceMapper.selectList(wrapper);

        for(Province province : provinceList) {
            // 获取该省份企业数量信息
            List<NumDto> numList = provinceMapper.queryAllNum(province.getArea());
            // 获取该省份企业利润信息
            List<ProfitDto> profitDtoList = provinceMapper.queryAllProfit(province.getArea());
            // 获取该省份企业收入信息
            List<IncomeDto> incomeDtoList =provinceMapper.queryAllIncome(province.getArea());

            int len = numList.size();
            for (int i = len; i < 10; i++) {
                numList.add(new NumDto());
            }
            len = profitDtoList.size();
            for (int i = len; i < 10; i++) {
                profitDtoList.add(new ProfitDto());
            }
            len = incomeDtoList.size();
            for (int i = len; i < 10; i++) {
                incomeDtoList.add(new IncomeDto());
            }
            province.setTopTenNum(numList);
            province.setTopTenProfitDto(profitDtoList);
            province.setTopTenIncomeDto(incomeDtoList);
        }

        return provinceList;
    }


    /**
     * 获取行业信息
     * @return 行业信息对象表
     */
    @Override
    public List<MarketDto> queryAllMarket() {
        return provinceMapper.queryAllMarket();
    }
}

