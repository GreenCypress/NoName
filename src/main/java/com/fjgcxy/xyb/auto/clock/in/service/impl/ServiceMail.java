package com.fjgcxy.xyb.auto.clock.in.service.impl;

import com.fjgcxy.xyb.auto.clock.in.model.dto.DtoMail;
import com.fjgcxy.xyb.auto.clock.in.service.IServiceMail;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


@Service
public class ServiceMail implements IServiceMail {

    private final MailSender mailSender;

    public ServiceMail(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(DtoMail mailDTO) {
        // new 一个简单邮件消息对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 和配置文件中的的username相同，相当于发送方
        message.setFrom("13124089198@163.com");
        // 收件人邮箱
        message.setTo(mailDTO.getMail());
        // 标题
        message.setSubject(mailDTO.getTitle());
        // 正文
        message.setText(mailDTO.getContent());
        // 发送
        mailSender.send(message);
    }
}
