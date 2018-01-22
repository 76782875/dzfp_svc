package com.rjxx.utils.leshui;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rjxx.utils.weixin.HttpClientUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyahui on 2018/1/4 0004.
 */
public class LeShuiUtil {
    private final static String LESHUIAPPID="f4404ef078794782b0c55d8ede20de3a";
    private final static String LESHUISECRET="c25fe890-9b39-41ff-bb9c-a6bcef4f6f5c";
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

    public static String invoiceQuery(String uniqueId,String invoiceCode,String invoiceNo,
                                       String taxCode){
        String url = GET_INVOICE_SINGLE;
        Map map = new HashMap();
        map.put("uniqueId", uniqueId);
        map.put("invoiceCode", invoiceCode);
        map.put("invoiceNo", invoiceNo);
        map.put("taxCode", taxCode);
        Map param = new HashMap();
        param.put("head", map);
        String json = JSON.toJSONString(param);
        String result = HttpClientUtil.doPostJson(url, json);
        return result;
    }

    public static String invoiceBatchQuery(String uniqueId,String startTime,String endTime,
                                       String taxCode,String pageNo){
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

    public static String invoiceAuthorize(String batchId,String taxCode,
                                          List body){
        String url = GET_INVOICE_AUTH;
        Map param = new HashMap();
        Map head = new HashMap();
        head.put("batchId", batchId);
        head.put("taxCode", taxCode);
        param.put("head", head);
        param.put("body", body);
        param.put("token", getToken());
        String json = JSON.toJSONString(param);
        String result = HttpClientUtil.doPostJson(url, json);
        return result;
    }
}
