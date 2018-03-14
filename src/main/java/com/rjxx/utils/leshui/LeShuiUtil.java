package com.rjxx.utils.leshui;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rjxx.taxeasy.domains.leshui.InvoiceAuth;
import com.rjxx.utils.weixin.HttpClientUtil;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by wangyahui on 2018/1/4 0004.
 */
@Component
public class LeShuiUtil {
    private final static String LESHUIAPPID = "f4404ef078794782b0c55d8ede20de3a";
    private final static String LESHUISECRET = "c25fe890-9b39-41ff-bb9c-a6bcef4f6f5c";
    /**
     * 获取token
     */
    private static final String GET_TOKEN = "https://open.leshui365.com/getToken";
    /**
     * 根据发票号码代码查验
     */
    private static final String GET_CHECK_CODE_NUM_URL = "https://open.leshui365.com/api/invoiceInfoForCom";
    /**
     * 根据二维码查验
     */
    private static final String GET_CHECK_QRCODE = "https://open.leshui365.com/api/invoiceInfoByQRCode";
    /**
     * 发信信息单个查询
     */
    private static final String GET_INVOICE_SINGLE = "https://open.leshui365.com/api/invoiceQuery";
    /**
     * 发票信息批量查询
     */
    private static final String GET_INVOICE_LIST = "https://open.leshui365.com/api/invoiceBatchQuery";
    /**
     * 发票认证
     */
    private static final String GET_INVOICE_AUTH = "https://open.leshui365.com/api/invoiceAuthorize";


    public static String getToken() {
        String url = GET_TOKEN;
        Map map = new HashMap<>();
        map.put("appKey", LESHUIAPPID);
        map.put("appSecret", LESHUISECRET);
        String json = HttpClientUtil.doGet(url, map);
        JSONObject jsonObject = JSON.parseObject(json);
        String token = jsonObject.getString("token");
        return token;
    }

    /**
     * i1 发票查验
     *
     * @invoiceCode 发票代码（长度10位或者12位）
     * @invoiceNumber 发票号码（长度8位）
     * @billTime 开票时间（时间格式必须为：2017-05-11，不支持其他格式）
     * @checkCode 校验码（检验码后六位，增值税专用发票，增值税机动车发票可以不传）
     * @invoiceAmount 开具金额、不含税价（增值税普通发票，增值税电子发票可以不传）
     */
    public static String invoiceInfoForCom(String invoiceCode, String invoiceNumber, String billTime,
                                           String checkCode, String invoiceAmount) {
        String url = GET_CHECK_CODE_NUM_URL;
        Map map = new HashMap();
        map.put("invoiceCode", invoiceCode);
        map.put("invoiceNumber", invoiceNumber);
        map.put("billTime", billTime);
        map.put("checkCode", checkCode);
        map.put("invoiceAmount", invoiceAmount);
        map.put("token", getToken());
        String result = HttpClientUtil.doPost(url, map);
        return result;
    }

    /**
     * i2 单张发票查询
     *
     * @uniqueId 唯一编码（20位），客户系统生成，规则："QBI"+"yyyyMMddhhmmss+001"
     * @invoiceCode 发票代码
     * @invoiceNo 发票号码
     * @taxCode 纳税人识别号(一般为购方纳税人识别号，即客户系统公司纳税人识别号)
     */
    public static String invoiceQuery(String uniqueId,String invoiceCode, String invoiceNo,
                                      String taxCode) {
        String url = GET_INVOICE_SINGLE;
        Map map = new HashMap();
        map.put("uniqueId", uniqueId);
        map.put("invoiceCode", invoiceCode);
        map.put("invoiceNo", invoiceNo);
        map.put("taxCode", taxCode);
        Map param = new HashMap();
        param.put("head", map);
        param.put("token", getToken());
        String json = JSON.toJSONString(param);
        String result = HttpClientUtil.doPostJson(url, json);
        return result;
    }

    /**
     * i3 发票信息批量查询
     *
     * @uniqueId 唯一编码（20位），客户系统生成，规则："QBI"+"yyyyMMddhhmmss+001"
     * @startTime 开始时间
     * @endTime 结束时间
     * @taxCode 纳税人识别号(一般为购方纳税人识别号，即客户系统公司纳税人识别号)
     * @pageNo 第几页
     */
    public static String invoiceBatchQuery(String uniqueId, String startTime, String endTime,
                                           String taxCode, Integer pageNo) {
        String url = GET_INVOICE_LIST;
        Map map = new HashMap();
        map.put("uniqueId", uniqueId);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("taxCode", taxCode);
        map.put("pageNo", pageNo);
        Map param = new HashMap();
        param.put("head", map);
        param.put("token", getToken());
        String json = JSON.toJSONString(param);
        String result = HttpClientUtil.doPostJson(url, json);
        return result;
    }

    /**
     * i4 发票认证
     *
     * @batchId 唯一编码（20位），客户系统生成，规则："QBI"+"yyyyMMddhhmmss+001"
     * @taxCode 纳税人识别号(一般为购方纳税人识别号，即客户系统公司纳税人识别号)
     * @period 认证所属期"//格式：201801
     * @body 需要认证的发票信息 invoiceCode&invoiceNo
     */
    public static String invoiceAuthorize(String batchId,String taxCode,String period,
                                          List<InvoiceAuth> body) {
        String url = GET_INVOICE_AUTH;
        Map param = new HashMap();
        Map head = new HashMap();
        head.put("batchId", batchId);
        head.put("taxCode", taxCode);
        head.put("period", period);
        param.put("head", head);
        param.put("body", body);
        param.put("token", getToken());
        String json = JSON.toJSONString(param);
        String result = HttpClientUtil.doPostJson(url, json);
        return result;
    }

    public static char getRandomLetter(){
        String chars = "abcdefghijklmnopqrstuvwxyz";
        return chars.charAt(new Random().nextInt(26));
    }
}
