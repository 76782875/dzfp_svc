package com.rjxx.taxeasy.bizcomm.utils;

import java.util.Date;
import java.util.List;

/**
 * 取得邮件内容
 * */
public class GetYjnr {
	
	public String getFpkjYj(String ddh, List<String> pdfUrlList, String gsmc)throws Exception{
		StringBuffer sb = new StringBuffer();
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
        sb.append(gsmc);
        sb.append("<br/>");
        sb.append("<br/>");
        Date d = new Date();
        sb.append(1900 + d.getYear()).append("年").append(d.getMonth() + 1).append("月").append(d.getDate()).append("日");
        return sb.toString();
	}
	
	public String getFpyjEmail(String yhmc,String xfmc,String kpdmc,String fpzlmc,Integer kyl,Integer limit){
		StringBuffer sb = new StringBuffer();
        sb.append(yhmc);
        sb.append(" 先生/小姐您好：<br/>");
        sb.append("<br/>");
        sb.append("您订阅的销方名称为："+xfmc+"，");
        sb.append("<br/>");
        sb.append("开票点名称为："+kpdmc+"，");
        sb.append("<br/>");
        sb.append("发票种类为："+fpzlmc+"的剩余库存为"+kyl+"张，已不足您设置的阈值"+limit+"张，请及时购买发票！");
        sb.append("<br/>");
        sb.append("<br/>");
        sb.append("<br/>");
        Date d = new Date();
        sb.append(1900 + d.getYear()).append("年").append(d.getMonth() + 1).append("月").append(d.getDate()).append("日");
        return sb.toString();	
	}
	
	public String getYhdyEmail(String gsmc,String xfmc,String kpdmc,String yhmc,Integer fpsl,Double hjje,Double hjse){
		StringBuffer sb = new StringBuffer();
        sb.append(yhmc);
        sb.append(" 先生/小姐您好：<br/>");
        sb.append("<br/>");
        sb.append("您订阅"+gsmc+"，");
        if(xfmc !=null && !"".equals(xfmc)){
        	sb.append("销方名称为"+xfmc+",");
        }
        if(kpdmc !=null && !"".equals(kpdmc)){
        	sb.append("开票点名称为"+kpdmc+",");
        }
        sb.append("昨天开了"+fpsl+"张发票,").append("所开票的合计金额为"+hjje+"，合计税额为"+hjse+",(单位：元)<br>");
        sb.append("<br/>");
        Date d = new Date();
        sb.append(1900 + d.getYear()).append("年").append(d.getMonth() + 1).append("月").append(d.getDate()).append("日");
        return sb.toString();
	}
	
	//判空
    private static Object null2Wz(Object s) {
        return s == null || "".equals(s) ? "未知" : s;
    }

}
