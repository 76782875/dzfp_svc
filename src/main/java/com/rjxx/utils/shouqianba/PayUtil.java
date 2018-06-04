package com.rjxx.utils.shouqianba;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rjxx.utils.alipay.AlipaySignUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/6/4
 */
public class PayUtil {

    private static Logger logger = LoggerFactory.getLogger(PayUtil.class);

    //测试信息
    private static String app_id = "2018060400000745";
    private static String vendor_sn = "91800407";
    private static String vendor_key = "8e8d34115a0162d42068d9d685f68258";
    private static String code = "37819981";

    private static String api_domain="https://api.shouqianba.com";
    private static String WAP_API_PRO_URL = "https://m.wosai.cn/qr/gateway";



    /**
     * 计算字符串的MD5值
     *
     * @param signStr:签名字符串
     * @return
     */
    public static String getSign(String signStr) {
        try {
            String md5 = DigestUtils.md5Hex(signStr.getBytes("UTF-8"));
            return md5;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public String getClient_Sn(int codeLenth) {
//        while (true) {
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < codeLenth; i++) {
//                if (i == 0)
//                    sb.append(new Random().nextInt(9) + 1); // first field will not start with 0.
//                else
//                    sb.append(new Random().nextInt(10));
//            }
//            return sb.toString();
//        }
//    }

    /**
     * 终端激活
     *
     * @param code:激活码
     * @param vendor_sn:服务商序列号
     * @param vendor_key:服务商密钥
     * @return {terminal_sn:"$终端号",terminal_key:"$终端密钥"}
     */
    public static JSONObject activate(String vendor_sn, String vendor_key, String appId,String code,String deviceId,String storeNo) {
        String url = api_domain + "/terminal/activate";
        JSONObject params = new JSONObject();
        try {
            params.put("app_id", appId);                                          //APPID
            params.put("code", code);                                          //激活码
//            params.put("type", "2");                                           //设备类型可以不提供。默认为"2"
//            params.put("os_info", "Mac OS");                              //当前系统信息
            params.put("device_id", deviceId);   //设备唯一身份ID
//            params.put("sdk_version", "Java SDK v1.0");     //SDK版本
            params.put("client_sn", storeNo);//第三方终端号

            String sign = getSign(params.toString() + vendor_key);
            String result = HttpUtil.httpPost(url, params.toString(), sign, vendor_sn);
            JSONObject retObj = JSON.parseObject(result);
            String resCode = retObj.get("result_code").toString();
            if (!resCode.equals("200"))
                return null;
            String responseStr = retObj.get("biz_response").toString();
            JSONObject terminal = JSON.parseObject(responseStr);
            if (terminal.get("terminal_sn") == null || terminal.get("terminal_key") == null)
                return null;
            return terminal;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 终端签到
     *
     * @param terminal_sn:终端号
     * @param terminal_key:终端密钥
     * @return {terminal_sn:"$终端号",terminal_key:"$终端密钥"}
     */
    public JSONObject checkin(String terminal_sn, String terminal_key) {
        String url = api_domain + "/terminal/checkin";
        JSONObject params = new JSONObject();
        try {
            params.put("terminal_sn", terminal_sn);                            //终端号
            params.put("device_id", "50a87771-ca8a-4952-a493-9504c39ab495");   //设备唯一身份ID
            params.put("os_info", "Mac OS");                              //当前系统信息
            params.put("sdk_version", "Java SDK v1.0");                      //SDK版本
            String sign = getSign(params.toString() + terminal_key);
            String result = HttpUtil.httpPost(url, params.toString(), sign, terminal_sn);
            JSONObject retObj = JSON.parseObject(result);
            String resCode = retObj.get("result_code").toString();
            if (!resCode.equals("200"))
                return null;
            String responseStr = retObj.get("biz_response").toString();
            JSONObject terminal = JSON.parseObject(responseStr);
            if (terminal.get("terminal_sn") == null || terminal.get("terminal_key") == null)
                return null;
            return terminal;
        } catch (Exception e) {
            return null;
        }
    }

//    /**
//     * 付款
//     *
//     * @param terminal_sn:终端号
//     * @param terminal_key:终端密钥
//     * @return
//     */
//    public String pay(String terminal_sn, String terminal_key) {
//        String url = api_domain + "/upay/v2/pay";
//        JSONObject params = new JSONObject();
//        try {
//            params.put("terminal_sn", terminal_sn);           //终端号
//            params.put("client_sn", getClient_Sn(16));  //商户系统订单号,必须在商户系统内唯一；且长度不超过64字节
//            params.put("total_amount", "1000");               //交易总金额,以分为单位
//            params.put("payway", "1");                         //支付方式,1:支付宝 3:微信 4:百付宝 5:京东钱包
//            params.put("dynamic_id", "130818341921441147");     //条码内容
//            params.put("subject", "Pizza");                     //交易简介
//            params.put("operator", "kay");                     //门店操作员
//
//            String sign = getSign(params.toString() + terminal_key);
//            String result = HttpUtil.httpPost(url, params.toString(), sign, terminal_sn);
//            return result;
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    /**
//     * 退款
//     *
//     * @param terminal_sn:终端号
//     * @param terminal_key:终端密钥
//     * @return
//     */
//    public String refund(String terminal_sn, String terminal_key) {
//        String url = api_domain + "/upay/v2/refund";
//        JSONObject params = new JSONObject();
//        try {
//            params.put("terminal_sn", terminal_sn);            //收钱吧终端ID
//            params.put("sn", "7892259488292938");              //收钱吧系统内部唯一订单号
//            params.put("client_sn", "18348290098298292838");   //商户系统订单号,必须在商户系统内唯一；且长度不超过64字节
//            params.put("refund_amount", "1000");               //退款金额
//            params.put("refund_request_no", "23030349");          //商户退款所需序列号,表明是第几次退款
//            params.put("operator", "kay");                      //门店操作员
//
//            String sign = getSign(params.toString() + terminal_key);
//            String result = HttpUtil.httpPost(url, params.toString(), sign, terminal_sn);
//
//            return result;
//        } catch (Exception e) {
//            return null;
//        }
//    }

    /**
     * 查询
     *
     * @param terminal_sn:终端号
     * @param terminal_key:终端密钥
     * @return
     */
    public static String query(String terminal_sn, String terminal_key,String client_sn) {
        String url = api_domain + "/upay/v2/query";
        JSONObject params = new JSONObject();
        try {
            params.put("terminal_sn", terminal_sn);           //终端号
//            params.put("sn", sn);             //收钱吧系统内部唯一订单号
            params.put("client_sn", client_sn);  //商户系统订单号,必须在商户系统内唯一；且长度不超过64字节

            String sign = getSign(params.toString() + terminal_key);
            String result = HttpUtil.httpPost(url, params.toString(), sign, terminal_sn);

            return result;
        } catch (Exception e) {
            return null;
        }
    }

//    /**
//     * 自动撤单
//     *
//     * @param terminal_sn:终端号
//     * @param terminal_key:终端密钥
//     * @return
//     */
//    public String cancel(String terminal_sn, String terminal_key) {
//        String url = api_domain + "/upay/v2/cancel";
//        JSONObject params = new JSONObject();
//        try {
//            params.put("terminal_sn", terminal_sn);           //终端号
//            params.put("sn", "7892259488292938");             //收钱吧系统内部唯一订单号
//            params.put("client_sn", "18348290098298292838");  //商户系统订单号,必须在商户系统内唯一；且长度不超过64字节
//
//            String sign = getSign(params.toString() + terminal_key);
//            String result = HttpUtil.httpPost(url, params.toString(), sign, terminal_sn);
//
//            return result;
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    /**
//     * 手动撤单
//     *
//     * @param terminal_sn:终端号
//     * @param terminal_key:终端密钥
//     * @return
//     */
//    public String revoke(String terminal_sn, String terminal_key) {
//        String url = api_domain + "/upay/v2/revoke";
//        JSONObject params = new JSONObject();
//        try {
//            params.put("terminal_sn", terminal_sn);           //终端号
//            params.put("sn", "7892259488292938");             //收钱吧系统内部唯一订单号
//            params.put("client_sn", "18348290098298292838");  //商户系统订单号,必须在商户系统内唯一；且长度不超过64字节
//
//            String sign = getSign(params.toString() + terminal_key);
//            String result = HttpUtil.httpPost(url, params.toString(), sign, terminal_sn);
//
//            return result;
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    /**
//     * 预下单
//     *
//     * @param terminal_sn:终端号
//     * @param terminal_key:终端密钥
//     * @return
//     */
//    public String precreate(String terminal_sn, String terminal_key) {
//        String url = api_domain + "/upay/v2/precreate";
//        JSONObject params = new JSONObject();
//        try {
//            params.put("terminal_sn", terminal_sn);           //收钱吧终端ID
//            params.put("client_sn", getClient_Sn(16));  //商户系统订单号,必须在商户系统内唯一；且长度不超过32字节
//            params.put("total_amount", "1000");               //交易总金额
//            params.put("payway", "1");                         //支付方式
//            params.put("dynamic_id", "130818341921441147");     //条码内容
//            params.put("subject", "Pizza");                     //交易简介
//            params.put("operator", "kay");                     //门店操作员
//            params.put("sub_payway", "3");                     //二级支付方式
//
//            String sign = getSign(params.toString() + terminal_key);
//            String result = HttpUtil.httpPost(url, params.toString(), sign, terminal_sn);
//
//            return result;
//        } catch (Exception e) {
//            return null;
//        }
//    }

    /**
     * 支付（WAP_API_PRO）
     * @param terminal_sn   收钱吧终端ID
     * @param client_sn     订单号
     * @param total_amount  金额
     * @param subject       主题
     * @param oprator       操作员
     * @param return_url
     */
    public static  void payIn(String terminal_sn, String terminal_key,String client_sn, String total_amount,
                              String subject,String oprator,String return_url){
        Map<String, String> param = new HashMap<>();
        param.put("terminal_sn", terminal_sn);
        param.put("client_sn", client_sn);
        param.put("total_amount", total_amount);
        param.put("subject", subject);
        param.put("operator", oprator);
        param.put("return_url", return_url);
        String signatureContent = AlipaySignUtil.getSignatureContent(param);//排序
        logger.info("signatureContent={}",signatureContent);
        String sign = DigestUtils.md5Hex(signatureContent + "&key=" + terminal_key).toUpperCase();
        logger.info("sign={}",sign);
        String paramString=signatureContent + "&sign=" + sign;
        String redirectUrl = WAP_API_PRO_URL +"?"+ paramString;
        System.out.println(redirectUrl);
    }



    public static void main(String[] args) {
//        JSONObject activate = activate(vendor_sn, vendor_key, app_id, code, "test", null);
//        System.out.println(activate.toJSONString());

        String query = query("100007450004004732", "4e0473e9dabb20bceae6876685b716ff",
                "orderNo2");
        System.out.println(query);

//        payIn("100007450004004732","4e0473e9dabb20bceae6876685b716ff","orderNo2",
//                "1","测试","wyh","http://www.baidu.com");
    }
}
