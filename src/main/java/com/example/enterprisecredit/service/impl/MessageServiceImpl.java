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

    public List<Message> queryMessage() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByDesc("mydate");
        wrapper.last("LIMIT 20");
        List<Message> list =messageMapper.selectList(wrapper);
        return  list;
    }

    public List<MessageDto> queryMessageByName(String name) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", name);
        List<Attention> attentionList = attentionMapper.selectList(wrapper);
        List<MessageDto> messageList = new ArrayList<>();
        for (Attention attention : attentionList) {
            QueryWrapper messageWrapper = new QueryWrapper();
            messageWrapper.eq("stockcode", attention.getStockCode());
            List<Message> messages = messageMapper.selectList(messageWrapper);
            MessageDto messageDto = new MessageDto(attention.getStockCode(), attention.getCompanyName(), messages);
            messageList.add(messageDto);
        }
        return messageList;
    }
}
