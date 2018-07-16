package com.rjxx.taxeasy.bizcomm.utils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 取得邮件内容
 * */
public class GetYjnr {
	
	public String getFpkjYj(Map<String, Object> csmap, String content)throws Exception{
		StringBuffer sb = new StringBuffer();
        Date d = new Date();
        sb.append(1900 + d.getYear()).append("年").append(d.getMonth() + 1).append("月").append(d.getDate()).append("日");
        String dqrq=sb.toString();
        String pdf="";
        for (Map.Entry<String, Object> entry : csmap.entrySet()) {
            String key=entry.getKey();
            if(key.equals("pdfurls")){
                List<String> pdfUrlList=(List<String>)entry.getValue();
                for (String pdfUrl : pdfUrlList) {
                    pdf+= "<a href='" + pdfUrl + "'>" + null2Wz(pdfUrl) + "</a><br/>";
                }
                content=content.replace(key,pdf);
            }else if(key.equals("infoUrl")){
                String infoUrl = entry.getValue().toString();
                String info="";
                if(infoUrl.equals("")){
                    info="";
                }else {
                    info = "电子发票详情页面:<br/><a href='" + infoUrl + "'>" + null2Wz(infoUrl) + "</a><br/>";
                }
                content=content.replace(key,info);
            }else {
                String value=(String)entry.getValue();
                content=content.replace(key,value);
            }
        }
        content=content.replace("dqrq",dqrq);
        return content;
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
    
    /**
     * 爱芙趣发送发票邮件模板
     * @param ewm
     * @param tqlj
     * 
     * */   
    public String getAfEmail(String ewm,String tqlj){
		StringBuilder sb = new StringBuilder();
        sb.append("尊敬的客户您好：<br/>");
        sb.append("<br/>");
        sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;从7月1日起，我们开始推行电子发票。您可以在到货后30天内扫描以下二维码，或者访问"
        		+ "以下电子发票提取连接进行电子发票提取。 请您使用订单号码作为发票提取码，根据网站的指示"
        		+ "提取电子发票。申请公司抬头的发票，请务必输入纳税人识别号。请注意，发票一经开出，恕不"
        		+ "修改或重新开具，请在提交发票提取前核对您的信息。");
        sb.append("<br/>");
        sb.append("<br/>");
        sb.append("<br/>");
        sb.append("电子发票提取连接："+"<a href='"+tqlj+"'>"+tqlj+"</a><br>");
        sb.append("<br/>");
        sb.append("二维码：<br/>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img  width='100' height='100' src='"+ewm+"'/>");
        sb.append("<br/>");
        sb.append("<br/>");
        sb.append("<br/>");
        sb.append("谢谢！<br/>");
        sb.append( "<p align='right'>爱芙趣商贸（上海）有限公司</p>");
        //Date d = new Date();
        //sb.append(1900 + d.getYear()).append("年").append(d.getMonth() + 1).append("月").append(d.getDate()).append("日");
        return sb.toString();	
	}


	//发票库存预警邮件
    public String getFpkcyj(Map<String, Object> csmap, String content)throws Exception{
        try {
            for (Map.Entry<String, Object> entry : csmap.entrySet()) {
                String key=entry.getKey();
                String value=(String)entry.getValue();
                content=content.replace(key,value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}
