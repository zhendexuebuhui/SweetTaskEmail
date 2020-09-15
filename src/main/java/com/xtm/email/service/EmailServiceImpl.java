package com.xtm.email.service;

import com.xtm.email.entity.MailDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author:藏剑
 * @date:2019/12/3 16:18
 */
@Slf4j
@Service
public class EmailServiceImpl implements EmailService{
    //template模板引擎
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")   // 发件人邮箱
    private String from;

    @Override
    public void sendTemplateMail(MailDO mailDO) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
            messageHelper.setFrom(from);// 发送人的邮箱
            messageHelper.setTo(mailDO.getEmail());//发给谁  对方邮箱
            messageHelper.setSubject(mailDO.getTitle()); //标题
            //使用模板thymeleaf
            //Context是导这个包import org.thymeleaf.context.Context;
            Context context = new Context();
            //定义模板数据
            context.setVariables(mailDO.getAttachment());
            //获取thymeleaf的html模板
            String emailContent = templateEngine.process("email",context); //指定模板路径
            messageHelper.setText(emailContent,true);
            //发送邮件
            javaMailSender.send(mimeMessage);
            log.info("模板邮件发送成功");
        } catch (MessagingException e) {
            log.error("模板邮件发送失败->message:{}",e.getMessage());
            e.printStackTrace();
        }

    }
}
