# SweetTaskEmail
❤定时每天给女朋友发送邮件(包含实时爬取的墨迹天气及OneDay内容)

## 技术栈

1. SpringBoot

2. thymeleaf

3. javamail

4. jsoup 

## 快速使用方法

### 1. git clone 克隆项目
### 2. IDEA 打开项目
### 3. 修改邮箱地址、授权码、对象所在城市和纪念日开始时间  

```java
// sendEmailTask.java
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
```

```java
// CalDay.java
public class CalDay {
    public static Integer getNumDay(){
        LocalDate today=LocalDate.now();
        LocalDate initDay=LocalDate.of(2019,9,28);  // 纪念日开始时间
        return (int) initDay.until(today, ChronoUnit.DAYS);
    }
}
```

```properties
# application.properties

spring.mail.host=smtp.qq.com
#发送方的邮箱号
spring.mail.username=xxxxxx@qq.com
#对于qq邮箱而言 密码指的就是发送方的授权码
spring.mail.password=xxxxxx
spring.mail.port=465
spring.mail.protocol=smtp
```
### 4. 进行测试

```java
// EmailApplicationTests.java
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
```

### 5. Maven 打包成jar包

<div align=center><img src="https://github.com/zhendexuebuhui/SweetTaskEmail/blob/master/ScreenCapture/package.jpg" /></div>

###　6. 在target文件夹中找到email-2.2.1.RELEASE.jar

```shell
java -jar email-2.2.1.RELEASE.jar // 运行程序
```

浏览器输入 http://localhost:8520/ 出现404代表程序运行成功，404是因为没有写页面

**注: 为了能在后台一直运行程序，建议把jar包上传到服务器上，使用nohup命令后台运行**

## 邮件样例


<div align=center><img src="https://github.com/zhendexuebuhui/SweetTaskEmail/blob/master/ScreenCapture/Examples.jpg" width="450px" /></div>


## 扩展性

　　可以在jsoup文件夹下增加爬取的网页内容，调整email.html的内联样式让html模板邮件更加漂亮
