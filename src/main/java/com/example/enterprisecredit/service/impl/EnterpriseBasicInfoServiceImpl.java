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


    /**
     * 获取企业基本信息
     * @param stockCode 企业股票编码
     * @return 企业基本信息对象
     */
    @Override
    public EnterpriseBasicInfo queryEnterpriseBasicInfoByCode(int stockCode) {
        //1)封装查询条件  QueryWrapper
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("stockcode", stockCode);
        //2)执行查询
        EnterpriseBasicInfo EnterpriseBasicInfo = enterpriseBasicInfoMapper.selectOne(wrapper);
        return EnterpriseBasicInfo;
    }

    /**
     * 获取利润前 5 企业
     * @return 企业基本信息对象
     */
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

    /**
     *
     * @param area
     * @return
     */
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
    /**
     * 索引查询
     * @param area 地区
     * @param transferMode 交易方式
     * @param industry 行业
     * @return 基本信息对象数组
     */
    @Override
    public List<EnterpriseBasicInfo> queryByIndex(String area, String transferMode, String industry) {

        QueryWrapper wrapper = new QueryWrapper();
        if (area != null && !area.equals("All")) {
            wrapper.eq("area", area);
        }

        if (transferMode != null && !transferMode.equals("All")) {
            wrapper.eq("transfermode", transferMode);
        }

        if (industry != null && !industry.equals("All")) {
            wrapper.eq("industry", industry);
        }
        wrapper.orderByDesc("income");
        wrapper.last("LIMIT 200 ");
        //2)执行查询
        List<EnterpriseBasicInfo> EnterpriseBasicInfo = enterpriseBasicInfoMapper.selectList(wrapper);
        System.out.println(EnterpriseBasicInfo.size());
        return EnterpriseBasicInfo;

    }

    /**
     * 查询利润和收入前 150
     * @param area 地区
     * @param transferMode 交易方式
     * @param industry 行业
     * @return 利润前150list，收入前150list
     */

    public List<List<EnterpriseBasicInfo>> queryTop150ByIncomeAndProfit(String area, String transferMode, String industry) {
        QueryWrapper<EnterpriseBasicInfo> wrapper = new QueryWrapper<>();

        if (area != null && !area.equals("All")) {
            wrapper.eq("area", area);
        }

        if (transferMode != null && !transferMode.equals("All")) {
            wrapper.eq("transferMode", transferMode);
        }

        if (industry != null && !industry.equals("All")) {
            wrapper.eq("industry", industry);
        }

        // 按照income字段从大到小排序，限制最大返回行数为150
        wrapper.orderByDesc("income").last("LIMIT 150");
        List<EnterpriseBasicInfo> top150ByIncome = enterpriseBasicInfoMapper.selectList(wrapper);

        // 清空wrapper，以便重新设置排序条件
        QueryWrapper<EnterpriseBasicInfo> wrapper1 = new QueryWrapper<>();

        if (area != null && !area.equals("All")) {
            wrapper1.eq("area", area);
        }
        if (transferMode != null && !transferMode.equals("All")) {
            wrapper1.eq("transferMode", transferMode);
        }
        if (industry != null && !industry.equals("All")) {
            wrapper1.eq("industry", industry);
        }
        // 按照profit字段从大到小排序，限制最大返回行数为150
        wrapper1.orderByDesc("profit").last("LIMIT 150");
        List<EnterpriseBasicInfo> top150ByProfit = enterpriseBasicInfoMapper.selectList(wrapper1);

        // 创建一个包含两个List的List
        List<List<EnterpriseBasicInfo>> result = new ArrayList<>();
        result.add(top150ByIncome);
        result.add(top150ByProfit);
        List<Integer> temp  = new ArrayList<>();
        return result;
    }

    /**
     * 企业信息对比
     * @param stockCode1 企业 1 股票编码
     * @param stockCode2 企业 2 股票编码
     * @return 企业基本信息对象数组
     */
    public List<EnterpriseBasicInfo> query2Enterprise(int stockCode1, int stockCode2) {
        QueryWrapper<EnterpriseBasicInfo> wrapper = new QueryWrapper<>();
        wrapper.in("stockcode", Arrays.asList(stockCode1, stockCode2));
        // 2) 执行查询
        List<EnterpriseBasicInfo> EnterpriseBasicInfoList = enterpriseBasicInfoMapper.selectList(wrapper);
        return EnterpriseBasicInfoList;
    }

    /**
     * 关键词查询
     * @param keyword 关键词
     * @param p 第几页
     * @param size 页大小
     * @return 对应页的对象数组
     */
    @Override
    public IPage<EnterpriseBasicInfo> queryEnterpriseByKeyword(String keyword, int p, int size) {
        QueryWrapper<EnterpriseBasicInfo> wrapper = new QueryWrapper<>();
        wrapper.or(
                i -> i.like("stockcode", "%" + keyword + "%")
                        .or()
                        .like("name", "%" + keyword + "%")
                        .or()
                        .like("shortname", "%" + keyword + "%")
//                        .or()
//                        .like("address", "%" + keyword + "%")
        );
        wrapper.orderByAsc("stockcode");
        Page<EnterpriseBasicInfo> page = new Page<EnterpriseBasicInfo>(p, size);
        IPage<EnterpriseBasicInfo> results = enterpriseBasicInfoMapper.selectPage(page, wrapper);
//        total = results.getTotal();
        return results;
    }

}

