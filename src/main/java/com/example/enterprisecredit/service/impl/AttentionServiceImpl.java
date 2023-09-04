package com.example.enterprisecredit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.enterprisecredit.entity.Attention;
import com.example.enterprisecredit.mapper.AttentionMapper;
import com.example.enterprisecredit.service.IAttentionService;
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
 * @since 2023-08-28
 */
@Service
public class AttentionServiceImpl extends ServiceImpl<AttentionMapper, Attention> implements IAttentionService {

    @Autowired
    AttentionMapper attentionMapper;
    public int insertAttention(Attention attention) {
        return attentionMapper.insert(attention);
    }

    public int deleteAttention(Attention attention) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username",attention.getUserName());
        wrapper.eq("stockcode",attention.getStockCode());
        return attentionMapper.delete(wrapper);
    }

    public List<Attention> queryAllAttention(String username) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        return attentionMapper.selectList(wrapper);
    }
}