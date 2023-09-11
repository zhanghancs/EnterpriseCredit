package com.example.enterprisecredit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.enterprisecredit.entity.Dto.FinancialInfoDto;
import com.example.enterprisecredit.entity.FinancialInfo;
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

    /**
     * 返回某个公司的财务信息
     * @param stockCode 公司股票代码
     * @return 公司财务信息对象
     */
    @Override
    public FinancialInfoDto queryByCode(int stockCode) {

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("stockCode",stockCode);
        FinancialInfo financialInfo = financialInfoMapper.selectOne(wrapper);
//        FinancialInfo financialInfo = financialInfoMapper.selectById(stockCode);
        FinancialInfoDto financialInfoDto = new FinancialInfoDto();
        if (financialInfo!=null) {
            financialInfoDto.setTotalRevenue(spiltList(financialInfo.getTotalRevenue()));
            financialInfoDto.setTotalCost(spiltList(financialInfo.getTotalCost()));
            financialInfoDto.setCost(spiltList(financialInfo.getCost()));
            financialInfoDto.setExpense(spiltList(financialInfo.getExpense()));
            financialInfoDto.setProfit(spiltList(financialInfo.getProfit()));
        }


        return financialInfoDto;

    }

    /**
     * 辅助函数，将字符串分割成数组
     * @param old 数据库中存储的原字符串
     * @return 分割完成后的数组
     */
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