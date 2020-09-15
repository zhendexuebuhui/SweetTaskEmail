package com.xtm.email.entity;

/**
 * @author:藏剑
 * @date:2019/12/3 16:17
 */

import java.util.Map;

/**
 * 邮件接收参数
 */

public class MailDO {

    //标题
    private String title;
    //内容
    private String content;
    //接收人邮件地址
    private String email;
    //附加，value 文件的绝对地址/动态模板数据
    private Map<String, Object> attachment;

    public MailDO(String title, String content, String email) {
        this.title = title;
        this.content = content;
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }
}