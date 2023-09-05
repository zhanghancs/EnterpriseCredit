package com.example.enterprisecredit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.enterprisecredit.entity.Dto.FinancialInfoDto;
import com.example.enterprisecredit.entity.Enterprisebasicinfo;
import com.example.enterprisecredit.entity.FinancialInfo;
import com.example.enterprisecredit.entity.Publicinfo;
import com.example.enterprisecredit.mapper.FinancialInfoMapper;
import com.example.enterprisecredit.service.IFinancialInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhufu
 * @since 2023-08-31
 */
@Service
public class FinancialInfoServiceImpl extends ServiceImpl<FinancialInfoMapper, FinancialInfo> implements IFinancialInfoService {

    @Autowired
    FinancialInfoMapper financialInfoMapper;

    public FinancialInfoDto queryByCode(int stockCode) {

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("stockCode",stockCode);
        FinancialInfo financialInfo = financialInfoMapper.selectOne(wrapper);
//        FinancialInfo financialInfo = financialInfoMapper.selectById(stockCode);
        FinancialInfoDto financialInfoDto = new FinancialInfoDto();

        financialInfoDto.setTotalRevenue(spiltList(financialInfo.getTotalRevenue()));
        financialInfoDto.setTotalCost(spiltList(financialInfo.getTotalCost()));
        financialInfoDto.setCost(spiltList(financialInfo.getCost()));
        financialInfoDto.setExpense(spiltList(financialInfo.getExpense()));
        financialInfoDto.setProfit(spiltList(financialInfo.getProfit()));

        return financialInfoDto;

    }
    private List<Double> spiltList(String old) {
        List<Double> list = new ArrayList<Double>();
        String[] elements = old.split("\\|");
        int len = elements.length;

        // 创建一个 double 数组来存储转换后的元素
        double[] doubles = new double[len];

        // 遍历字符串数组并将每个元素转换为 double
        for (int i = len - 1; i >= 0; i--) {
            doubles[i] = Double.parseDouble(elements[i]);
            list.add(doubles[i]);
        }
        return list;
    }

}