package com.rjxx.taxeasy.bizcomm.utils;

import com.rjxx.taxeasy.domains.Jyls;
import com.rjxx.taxeasy.service.JylsService;
import com.rjxx.taxeasy.vo.Fpcxvo;
import com.rjxx.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-12-06.
 */
@Service
public class SendEmailNewService {

    @Autowired
    private InvoiceQueryUtil invoiceQueryUtil;
    @Value("${emailInfoUrl:}")
    private String emailInfoUrl;
    @Value("${fpdz_:}")
    private String fpdz_;
    /**
     * A发送邮件的内容
     *
     * @param ddh 订单号
     * @return
     * @throws Exception
     */
    public  String getNewMailContent(String ddh, List<String> pdfUrlList, String xfmc,String gsdm) throws Exception {
        StringBuffer sb = new StringBuffer();
        Jyls jyls = new Jyls();
        jyls.setDdh(ddh);
        List<Fpcxvo> list = invoiceQueryUtil.getInvoiceListByDdh(gsdm, ddh);
        String q="";
        if(list.get(0).getTqm()!=null && !list.get(0).getTqm().equals("")){
            q=  list.get(0).getTqm();
        }else if(list.get(0).getKhh()!=null && !list.get(0).getKhh().equals("")){
            q= list.get(0).getKhh();
        }
        // sb.append(null2Wz(iurb.get("BUYER_NAME")));
        sb.append(" 先生/小姐您好：<br/>");
        sb.append("<br/>");
        sb.append("您的订单号码： ");
        sb.append(ddh).append("的电子发票已开具成功，<br>");
        sb.append(ddh).append("的发票详情页面：<br>");
        //String url =emailInfoUrl+"g="+gsdm+"&q="+q;
        String url =fpdz_+"?q="+list.get(0).getSerialorder();
        sb.append("<a href='" + url + "'>" + null2Wz(url) + "</a><br>");
        sb.append(ddh).append("电子发票下载地址：<br>");
        for (String pdfUrl : pdfUrlList) {
            sb.append("<a href='" + pdfUrl + "'>" + null2Wz(pdfUrl) + "</a><br>");
        }
        sb.append("请及时下载您的发票。<br><br>");
        /*sb.append("提示:苹果浏览器无法显示发票章,只能下载pdf显示<br>");*/
        sb.append("<br/><br/>");
        sb.append(xfmc);
        sb.append("<br/>");
        sb.append("<br/>");
        Date d = new Date();
        sb.append(1900 + d.getYear()).append("年").append(d.getMonth() + 1).append("月").append(d.getDate()).append("日");
        return sb.toString();
    }

    // 判空
    private static Object null2Wz(Object s) {
        return s == null || "".equals(s) ? "未知" : s;
    }
}
