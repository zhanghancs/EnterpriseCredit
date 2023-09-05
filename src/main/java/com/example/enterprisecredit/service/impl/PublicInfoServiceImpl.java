package com.example.enterprisecredit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.enterprisecredit.entity.Dto.PublicInfoDto;
import com.example.enterprisecredit.entity.PublicInfo;
import com.example.enterprisecredit.mapper.PublicInfoMapper;
import com.example.enterprisecredit.service.IPublicInfoService;
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
public class PublicInfoServiceImpl extends ServiceImpl<PublicInfoMapper, PublicInfo> implements IPublicInfoService {
    @Autowired
    PublicInfoMapper publicinfoMapper;
    public PublicInfoDto queryByCode(int stockCode) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("stockCode",stockCode);
        //2)执行查询
        PublicInfo publicinfo = publicinfoMapper.selectOne(wrapper);
        return new PublicInfoDto(publicinfo);
    }
}
