package com.rjxx.taxeasy.bizcomm.utils;

import java.util.Date;
import com.rjxx.utils.ReadProperties;
public class FpyjSendEmail {
	
	/**
     * A发送邮件的内容
     *
     * @param 
     * @return
     * @throws Exception
     */
    private static String getAFMailContent(String yhmc,String kpdmc,String skph,String limit) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append(yhmc);
        sb.append(" 先生/小姐您好：<br/>");
        sb.append("<br/>");
        sb.append("您订阅的开票点： ");
        sb.append(kpdmc+"(税控盘号："+skph+")").append("的剩余发票数量已不足您设置的"+limit+"张，请及时更新！<br>");
        sb.append("<br/>");
        Date d = new Date();
        sb.append(1900 + d.getYear()).append("年").append(d.getMonth() + 1).append("月").append(d.getDate()).append("日");
        return sb.toString();
    }

    //判空
    private static Object null2Wz(Object s) {
        return s == null || "".equals(s) ? "未知" : s;
    }

    /**
     * 发送邮件
     *
     * @param ddh
     * @param email
     * @param pdfUrlList
     * @param gsdm
     * @throws Exception
     */
    public static void sendMail(String email,String yhmc,String kpdmc,String skph,String limit) throws Exception {
        MailUtil sendmail = new MailUtil();
        sendmail.setHost(ReadProperties.read("emailHost"));
        sendmail.setUserName(ReadProperties.read("emailUserName"));
        sendmail.setPassWord(ReadProperties.read("emailPwd"));
        sendmail.setTo(email);
        sendmail.setFrom(ReadProperties.read("emailForm"));
        sendmail.setSubject(ReadProperties.read("emailTitle"));
        sendmail.setContent(getAFMailContent(yhmc,kpdmc,skph,limit));
        sendmail.sendMail();
        Thread.sleep(5000);
    }

}
