package com.rjxx.taxeasy.bizcomm.utils;

import java.util.Date;
import com.rjxx.comm.utils.ApplicationContextUtils;
import com.rjxx.taxeasy.service.GetPropertService;
import com.rjxx.utils.MailUtil;
public class SendEmailNew {
	private GetPropertService getPro = ApplicationContextUtils.getBean(GetPropertService.class);
    
	/**
     * 异常信息邮件主体内容
     *
     * @param 
     * @return
     * @throws Exception
     */
    private static String getYCMailContent(String ycxx, String ycff) throws Exception {
        StringBuffer sb = new StringBuffer();
        //sb.append(null2Wz(iurb.get("BUYER_NAME")));
        sb.append("系统发生异常：<br/>");
        sb.append("<br/>");
        sb.append("异常信息： ");
        sb.append(ycxx).append("<br/>异常发生所在类名、方法及发生异常所在行如下：");
        sb.append(ycff);
        sb.append("<br/>请及时关注。");
        sb.append("<br/>");
        sb.append("<br/>");
        sb.append("<br/><br/>");
//        Map<String, Object> params = new HashMap<>();
//        params.put("gsdm", gsdm);
//        Gsxx gsxx = gsxxService.findOneByParams(params);
        sb.append("<br/>");
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
     * 异常信息发送邮件
     *
     * @param ycxx 异常信息
     * @param ycff 异常方法
     * @param email 邮件地址
     * @param yjzt 邮件主体内容
     * @throws Exception
     */
    public void sendExceptionMail(String ycxx,String ycff,String email,String yjzt) throws Exception {
        MailUtil sendmail = new MailUtil();
        sendmail.setHost(getPro.getEmailHost());
        sendmail.setUserName(getPro.getEmailUserName());
        sendmail.setPassWord(getPro.getEmailPwd());
        sendmail.setTo(email);

        sendmail.setFrom(getPro.getEmailForm());
        sendmail.setSubject(getPro.getEmailTitle());
        sendmail.setContent(yjzt);

        sendmail.sendMail();

        Thread.sleep(5000);
    }
    
    
    public void sendExceptionMail(String ycxx,String ycff,String email) throws Exception {
    	sendExceptionMail(ycxx,ycff,email,this.getYCMailContent(ycxx, ycff));
    } 
}
