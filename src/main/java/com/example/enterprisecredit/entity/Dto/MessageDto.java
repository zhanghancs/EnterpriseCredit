package com.example.enterprisecredit.entity.Dto;

import com.example.enterprisecredit.entity.Message;
import lombok.Data;

import java.util.List;

@Data
public class MessageDto {
    public MessageDto(int stockCode, String name, List<Message> messages) {
        this.stockCode = stockCode;
        this.name = name;
        this.messageList = messages;
        this.number = messages.size();
    }
    private int stockCode;
    private String name;
    private int number;
    private List<Message> messageList;
}
