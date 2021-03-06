package com.rjxx.taxeasy.bizcomm.utils;

import java.util.Date;
import java.util.List;
import com.rjxx.comm.utils.ApplicationContextUtils;
import com.rjxx.taxeasy.service.GetPropertService;
import com.rjxx.utils.ReadProperties;
public class SendEmail {
	private GetPropertService getPro = ApplicationContextUtils.getBean(GetPropertService.class);
    /*public static void main(String[] args) throws Exception {
        t_jyls jyls = t_jyls.find(t_jyls.class, 151);
        sendMail("123456789", "1051752048@qq.com", new ArrayList<String>() {{
            add("111");
        }}, "af");
    }*/

    /**
     * A发送邮件的内容
     *
     * @param ddh 订单号
     * @return
     * @throws Exception
     */
    private static String getAFMailContent(String ddh, List<String> pdfUrlList, String gsmc) throws Exception {
        StringBuffer sb = new StringBuffer();
        //sb.append(null2Wz(iurb.get("BUYER_NAME")));
        sb.append(" 先生/小姐您好：<br/>");
        sb.append("<br/>");
        sb.append("您的订单号码： ");
        sb.append(ddh).append("的电子发票已开具成功，电子发票下载地址：<br>");
        for (String pdfUrl : pdfUrlList) {
            sb.append("<a href='" + pdfUrl + "'>" + null2Wz(pdfUrl) + "</a><br>");
        }
        sb.append("请及时下载您的发票。");
        sb.append("<br/>");
        sb.append("<br/>");
        sb.append("备注：苹果浏览器无法显示发票章，只能下载PDF才能显示。");
        sb.append("<br/><br/>");
//        Map<String, Object> params = new HashMap<>();
//        params.put("gsdm", gsdm);
//        Gsxx gsxx = gsxxService.findOneByParams(params);
        sb.append(gsmc);
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
     * 发送邮件
     *
     * @param ddh
     * @param email
     * @param pdfUrlList
     * @param gsdm
     * @throws Exception
     */
    public void sendMail(String ddh, String email, List<String> pdfUrlList, String gsmc) throws Exception {
        MailUtil sendmail = new MailUtil();
        sendmail.setHost(getPro.getEmailHost());
        sendmail.setUserName(getPro.getEmailUserName());
        sendmail.setPassWord(getPro.getEmailPwd());
        sendmail.setTo(email);

        sendmail.setFrom(getPro.getEmailForm());
        sendmail.setSubject(getPro.getEmailTitle());
        sendmail.setContent(getAFMailContent(ddh, pdfUrlList, gsmc));
        //TODO 这里需要根据邮件摸板内容进行调整。

//            XXX 先生/小姐您好：
        //
//            订单号码： XXXXXXXX, 您的发票信息如下：
        //
        //
//            发票将邮寄至地址（即订单收货地址）： XXXXXXX  收件人（即定单收货人）： xxxxxx
//            上述资料是您在个人基本资料中所登陆的地址，并已输入发票系统，为避免退货情况产生，
//            请您再次确认住址是否正确，若有需要修改邮寄资料请联络客服中心进行修改。
        //
//            在此提醒若您是在收到此邮件后才修改个人基本资料，则新登陆的邮寄资料将会在下次发票开立时生效
        //
        //
        //
//            爱芙趣商贸（上海）有限公司
//            20xx年x月x日

        sendmail.sendMail();

        Thread.sleep(5000);
    }
}
