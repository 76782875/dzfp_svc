package com.rjxx.taxeasy.bizcomm.utils;

import com.rjxx.taxeasy.domains.Yjjl;
import com.rjxx.taxeasy.service.YjjlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
@org.springframework.stereotype.Service
public class SampleMail {
    private static final String ALIDM_SMTP_HOST = "smtpdm.aliyun.com";
    private static final String ALIDM_SMTP_PORT = "25";//或"80"
    @Autowired
    private YjjlService yjjlService;

    public boolean  sendmail1(String djh, String gsdm, String[] sjryx, String type, String ref_Id, String yjnr,
                                String yjbt) {
        // 配置发送邮件的环境属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", ALIDM_SMTP_HOST);
        props.put("mail.smtp.port", ALIDM_SMTP_PORT);
        // 如果使用ssl，则去掉使用25端口的配置，进行如下配置,
        // props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // props.put("mail.smtp.socketFactory.port", "465");
        // props.put("mail.smtp.port", "465");
        // 发件人的账号
        props.put("mail.user", "service@einvoice.datarj.com");
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", "rjxxSMTP1234");
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        try {
            // 设置发件人
            InternetAddress from =  new InternetAddress("service@einvoice.datarj.com");
            message.setFrom(from);
            Address[] a = new Address[1];
            a[0] = new InternetAddress("service@einvoice.datarj.com");
            message.setReplyTo(a);
            // 设置收件人
            InternetAddress[] sendTo = new InternetAddress[sjryx.length];
            for (int i = 0; i < sjryx.length; i++) {
                System.out.println("发送到:" + sjryx[i]);
                sendTo[i] = new InternetAddress(sjryx[i]);
            }
            message.setRecipients(javax.mail.internet.MimeMessage.RecipientType.TO,
                    sendTo);
            // 设置邮件标题
            message.setSubject(yjbt);
            // 设置邮件的内容体
            message.setContent(yjnr, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
            Yjjl yjjl = new Yjjl();
            yjjl.setSjryx(sjryx.toString());
            yjjl.setGsdm(gsdm);
            yjjl.setType(type);
            yjjl.setLrsj(new Date());
            yjjl.setRefId(djh);
            yjjl.setReturnid(message.getContentID());
            //yjjl.setYjnr(yjnr);
            yjjl.setYjbt(yjbt);
            yjjlService.save(yjjl);
        }
        catch (MessagingException e) {
            String err = e.getMessage();
            e.printStackTrace();
            // 在这里处理message内容， 格式是固定的
            System.out.println(err);
        }
        return true;
    }

    public static void main(String[] args) {
        String title = "邮件主题";// 所发送邮件的标题
        String sendTo[] = { "zhangsongqiang@datarj.com", "913015635@qq.com" };// 发送到那里
        // 邮件的文本内容，可以包含html标记则显示为html页面
        String content = "mail test!!!!!!<br><a href=#>aaa</a>";
        String gfEmail ="zhangsongqiang@datarj.com,913015635@qq.com";
        try {
            String[] strings = gfEmail.split(",|，");
            sendmail2(null, "zsq",strings , "发票开具成功发送邮件", null, content, title);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static  void   sendmail2(String djh, String gsdm, String[] sjryx, String type, String ref_Id, String yjnr,
                              String yjbt) {
        // 配置发送邮件的环境属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", ALIDM_SMTP_HOST);
        props.put("mail.smtp.port", ALIDM_SMTP_PORT);
        // 如果使用ssl，则去掉使用25端口的配置，进行如下配置,
        // props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // props.put("mail.smtp.socketFactory.port", "465");
        // props.put("mail.smtp.port", "465");
        // 发件人的账号
        props.put("mail.user", "service@einvoice.datarj.com");
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", "rjxxSMTP1234");
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        try {
            // 设置发件人
            InternetAddress from =  new InternetAddress("service@einvoice.datarj.com");
            message.setFrom(from);
            Address[] a = new Address[1];
            a[0] = new InternetAddress("service@einvoice.datarj.com");
            message.setReplyTo(a);
            // 设置收件人
            InternetAddress[] sendTo = new InternetAddress[sjryx.length];
            for (int i = 0; i < sjryx.length; i++) {
                System.out.println("发送到:" + sjryx[i]);
                sendTo[i] = new InternetAddress(sjryx[i]);
            }
            message.setRecipients(javax.mail.internet.MimeMessage.RecipientType.TO,
                    sendTo);
            // 设置邮件标题
            message.setSubject(yjbt);
            // 设置邮件的内容体
            message.setContent(yjnr, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
        }
        catch (MessagingException e) {
            String err = e.getMessage();
            e.printStackTrace();
            // 在这里处理message内容， 格式是固定的
            System.out.println(err);
        }
        return ;
    }
}