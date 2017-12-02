package com.mark.test.framework.util;

import org.apache.commons.mail.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mark .
 * Data   : 2017/12/2
 * Author : mark
 * Desc   :
 */

public class EmailUtils {

    private static Logger logger = LoggerFactory.getLogger(EmailUtils.class);
    public static Email email;

    private void buildSender() throws EmailException {
        email.setHostName("smtp.163.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("mamingfeng007", "crystal08"));
        email.setSSLOnConnect(true);
        email.setFrom("mamingfeng007@163.com");
        email.setSubject("TestMail");
        email.setMsg("This is a test mail ... :-)");
        email.addTo("mamingfeng007@163.com");
        email.send();
    }

    /**
     * 发送邮件
     */
    private void sendMail() throws EmailException {
        email = new SimpleEmail();
        this.buildSender();
    }

    public static void main(String[] args) throws EmailException {


        for (int i =0;i <100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        new EmailUtils().sendMail();
                    } catch (EmailException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
