package com.example.enterprisecredit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.enterprisecredit.entity.Dto.IncomeDto;
import com.example.enterprisecredit.entity.Dto.MarketDto;
import com.example.enterprisecredit.entity.Dto.ProfitDto;
import com.example.enterprisecredit.entity.Dto.NumDto;
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

    public List<Province> queryAllProvince() {
        List<Province> provinceList = null;

        QueryWrapper<Province> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("number"); // 按照 number 字段的大小从大到小排序

        provinceList = provinceMapper.selectList(wrapper);

        for(Province province : provinceList) {
            List<NumDto> numList = provinceMapper.queryAllNum(province.getArea());
            List<ProfitDto> profitDtoList = provinceMapper.queryAllProfit(province.getArea());
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
//    public List<Map<String, Object>> market() {
//        String sql ="select * from market";
//        return provinceMapper.queryUsersByCondition(sql);
//    }

    public List<MarketDto> queryAllMarket() {
        return provinceMapper.queryAllMarket();
    }
}

