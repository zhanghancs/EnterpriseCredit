package com.example.enterprisecredit.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Random;

@Slf4j
@Api(tags = "发送邮件")
@RequiredArgsConstructor
@RestController
public class EmailController {

    private final JavaMailSender javaMailSender;

    //发送邮件的邮箱账号
    @Value("${spring.mail.username}")
    private String account;

    //接收前端发送的String类型的邮件地址，然后生成一个验证码，给目标邮箱发送邮件
    @ApiOperation("发送简单邮件")
    @PostMapping("sendSimpleMail")
    public String sendSimpleMail(
            @ApiParam("收件地址") @RequestParam String address)
    {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(account);
        smm.setTo(address);
        smm.setSubject("验证码");
        Random r = new Random();
        String code = Integer.toString(r.nextInt(10000));
        smm.setText(code);
        javaMailSender.send(smm);
        return "发送成功";
    }
    //接收前端发送的String类型的邮件地址、String类型的标题、String类型的正文，和MultipartFile的附件，给目标邮箱发送带附件的邮件
    @ApiOperation("发送带附件的邮件")
    @PostMapping("sendAttachmentMail")
    public String sendAttachmentMail(
            @ApiParam("收件地址") @RequestParam String address,
            @ApiParam("标题") @RequestParam String subject,
            @ApiParam("正文") @RequestParam String body,
            @ApiParam("附件") @RequestPart MultipartFile attach) throws MessagingException, IOException {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setFrom(account);
        mimeMessageHelper.setTo(new String[]{address});
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body);
        //文件路径
        byte[] bytes = attach.getBytes();
        String name = attach.getName();
        mimeMessageHelper.addAttachment(name, new ByteArrayResource(bytes));
        log.info("fileName:{}", name);
        javaMailSender.send(mimeMailMessage);
        return "发送成功";
    }
}
