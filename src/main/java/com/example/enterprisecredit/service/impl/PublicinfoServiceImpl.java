package com.example.enterprisecredit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.enterprisecredit.entity.Enterprisebasicinfo;
import com.example.enterprisecredit.entity.Publicinfo;
import com.example.enterprisecredit.mapper.PublicinfoMapper;
import com.example.enterprisecredit.service.IPublicinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhufu
 * @since 2023-08-31
 */
@Service
public class PublicinfoServiceImpl extends ServiceImpl<PublicinfoMapper, Publicinfo> implements IPublicinfoService {
    @Autowired
    PublicinfoMapper publicinfoMapper;
    public Publicinfo getById(String creditCode) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        //2)执行查询
        Publicinfo publicinfo = publicinfoMapper.selectOne(wrapper);
        return publicinfo;
    }
}
