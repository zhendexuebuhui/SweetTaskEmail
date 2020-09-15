package com.xtm.email.service;

import com.xtm.email.entity.MailDO;

/**
 * @author:藏剑
 * @date:2019/12/3 16:18
 */



public interface EmailService {
    void sendTemplateMail(MailDO mailDO);
}