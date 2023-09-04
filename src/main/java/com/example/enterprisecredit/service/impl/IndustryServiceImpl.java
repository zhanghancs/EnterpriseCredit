package com.example.enterprisecredit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.enterprisecredit.entity.Industry;
import com.example.enterprisecredit.mapper.IndustryMapper;
import com.example.enterprisecredit.service.IIndustryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class IndustryServiceImpl extends ServiceImpl<IndustryMapper, Industry> implements IIndustryService {
    @Autowired
    IndustryMapper industryMapper;
    public List<Industry> queryAll(int type) {
        QueryWrapper wrapper = new QueryWrapper();
        List<Industry> industryList =industryMapper.selectList(wrapper);
        return  industryList;
    }
}
