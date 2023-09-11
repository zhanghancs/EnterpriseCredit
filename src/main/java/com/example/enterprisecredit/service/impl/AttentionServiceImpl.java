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

    /**
     * 添加一个关注记录
     * @param attention 一个完整的关注对象
     * @return 返回一个 int 值， 1 表示成功关注
     */
    @Override
    public int insertAttention(Attention attention) {
//        if (queryAttention(attention) != null) {
//            return -1;
//        }
        return attentionMapper.insert(attention);
    }

    /**
     * 删除一个关注记录
     * @param attention 一个关注对象，对象中的公司名可以为 null
     * @return 返回一个 int 值， 1 表示成功取消关注
     */
    @Override
    public int deleteAttention(Attention attention) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username",attention.getUserName());
        wrapper.eq("stockcode",attention.getStockCode());
        return attentionMapper.delete(wrapper);
    }

    /**
     * 查询某用户的所有关注记录
     * @param username 用户名
     * @return 所有关注记录
     */
    @Override
    public List<Attention> queryByUsername(String username) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        return attentionMapper.selectList(wrapper);
    }

    public int status(String name ,int stockcode) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", name);
        List<Attention> attentionList = attentionMapper.selectList(wrapper);

        for (Attention attention : attentionList) {
            if (attention.getStockCode() == stockcode) {
                return 0;
            }

        }
        return 1;
    }
}
