package com.xtm.email.task;

import com.xtm.email.entity.MailDO;
import com.xtm.email.jsoup.MojiJsoup;
import com.xtm.email.jsoup.OneDayJsoup;
import com.xtm.email.service.EmailService;
import com.xtm.email.util.CalDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:藏剑
 * @date:2019/12/3 20:36
 */
@Component
@EnableScheduling
public class sendEmailTask {
    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "00 30 7 * * *") // 定时发送任务 每天7:30发送
    private void sendEmail(){
        MailDO mailDO = new MailDO("给xxx的暖心邮件", "", "xxxxxxxxx@qq.com"); // 发送给对象的邮箱
        Map<String, Object> map = new HashMap<>();
        map.put("numDays", CalDay.getNumDay());
        // 修改url来切换所在城市的天气
        map.putAll(MojiJsoup.getMojiWeather("https://tianqi.moji.com/weather/china/guangxi/lingui-district"));
        map.putAll(OneDayJsoup.getOneDay("http://wufazhuce.com/"));
        mailDO.setAttachment(map);
        emailService.sendTemplateMail(mailDO);
        mailDO.setEmail("xxxxxxxxxx@qq.com"); // 同时发给自己一封查看内容
        emailService.sendTemplateMail(mailDO);
    }

    @Scheduled(cron = "00 00 21 * * *") // 定时检查任务 每天21:00检查程序是否正确
    private void sendInitEmail(){
        MailDO mailDO = new MailDO("检查邮件", "", "xxxxxxxxx@qq.com"); // 发送给自己的邮箱
        Map<String, Object> map = new HashMap<>();
        map.put("numDays", CalDay.getNumDay());
        // 修改url来切换所在城市的天气
        map.putAll(MojiJsoup.getMojiWeather("https://tianqi.moji.com/weather/china/guangxi/lingui-district"));
        map.putAll(OneDayJsoup.getOneDay("http://wufazhuce.com/"));
        mailDO.setAttachment(map);
        emailService.sendTemplateMail(mailDO);
    }

}
