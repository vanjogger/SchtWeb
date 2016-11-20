/**
 * @(#) SendEmail.java 2014/06/05 09:39
 * <p/>
 * 版权所有 (c) 北京银软网络技术有限公司
 * 北京市海淀区上地国际创业园西区1号
 * 保留所有权利
 */
package com.scht.util;

import com.scht.common.ConfigHelper;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * 类的功能，目的，描述等写在此处
 *
 * @author pengqc
 * @version 1.0
 */
public class EmailUtil {

    /**
     *
     * @param toEmail
     * @param content
     * @param title
     * @throws EmailException
     */
    public static void send(String toEmail,String content,String title) throws EmailException {
        ConfigHelper config = ConfigHelper.GetInstance();
        HtmlEmail simpleEmail = new HtmlEmail();
        simpleEmail.setCharset("utf-8");
        simpleEmail.setAuthentication(config.GetConfig("SmtpEmail"), config.GetConfig("SmtpPwd"));
        simpleEmail.setHostName(config.GetConfig("SmtpServer"));
        simpleEmail.setSmtpPort(Integer.parseInt(config.GetConfig("SmtpPort")));
        simpleEmail.addTo(toEmail);
        simpleEmail.setFrom(config.GetConfig("SmtpEmail"));
        simpleEmail.setSubject(title);
        simpleEmail.setHtmlMsg(content);
        simpleEmail.send();
    }
}
