package com.xtm.email;

import com.xtm.email.entity.MailDO;
import com.xtm.email.entity.OneData;
import com.xtm.email.jsoup.MojiJsoup;
import com.xtm.email.jsoup.OneDayJsoup;
import com.xtm.email.service.EmailServiceImpl;
import com.xtm.email.util.CalDay;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
class EmailApplicationTests {

    @Autowired
    private EmailServiceImpl emailService;


    @Test
    void contextLoads() throws MessagingException {
        MailDO mailDO = new MailDO("给xxxx的一封暖心邮件", "", "xxxxxx@qq.com"); // 用自己的邮箱测试
        Map<String, Object> map = new HashMap<>();
        map.put("numDays", CalDay.getNumDay());
        // 修改url来切换所在城市的天气
        map.putAll(MojiJsoup.getMojiWeather("https://tianqi.moji.com/weather/china/guangxi/lingui-district"));
        map.putAll(OneDayJsoup.getOneDay("http://wufazhuce.com/"));
        mailDO.setAttachment(map);
        emailService.sendTemplateMail(mailDO);

    }

}
