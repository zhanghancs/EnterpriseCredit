package com.example.enterprisecredit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.enterprisecredit.entity.Attention;
import com.example.enterprisecredit.entity.Dto.MessageDto;
import com.example.enterprisecredit.entity.Message;
import com.example.enterprisecredit.mapper.AttentionMapper;
import com.example.enterprisecredit.mapper.MessageMapper;
import com.example.enterprisecredit.service.IMessageService;
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
 * @since 2023-09-01
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {
    @Autowired
    MessageMapper messageMapper;

    @Autowired
    AttentionMapper attentionMapper;

    /**
     * 最近的二十条公司变动信息
     * @return 信息数组
     */
    @Override
    public List<Message> queryMessage() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByDesc("mydate");
        wrapper.last("LIMIT 20");
        List<Message> list =messageMapper.selectList(wrapper);
        return  list;
    }

    /**
     * 查询用户关注的企业变动信息
     * @param name 用户名
     * @return 信息数组
     */
    @Override
    public List<MessageDto> queryMessageByName(String name) {
        // 查询用户关注的企业
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", name);
        wrapper.orderByAsc("stockcode");
        List<Attention> attentionList = attentionMapper.selectList(wrapper);
        // 查询企业的变动信息
        List<MessageDto> messageList = new ArrayList<>();
        for (Attention attention : attentionList) {
            QueryWrapper messageWrapper = new QueryWrapper();
            messageWrapper.eq("stockcode", attention.getStockCode());
            List<Message> messages = messageMapper.selectList(messageWrapper);
            messageWrapper.orderByDesc("mydate");
            MessageDto messageDto = new MessageDto(attention.getStockCode(), attention.getCompanyName(), messages);
            messageList.add(messageDto);
        }
        return messageList;
    }
}
