package com.rjxx.utils.weixin;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rjxx.taxeasy.dao.WxfpxxJpaDao;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.domains.Kpspmx;
import com.rjxx.taxeasy.domains.WxFpxx;
import com.rjxx.taxeasy.service.KpspmxService;
import com.rjxx.utils.StringUtils;
import com.rjxx.utils.TimeUtil;
import com.rjxx.utils.WeixinUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017-12-21.
 */
@Service
public class WechatBatchCard {
    private static Logger logger = LoggerFactory.getLogger(WechatBatchCard.class);

    @Autowired
    private WxfpxxJpaDao wxfpxxJpaDao;
    @Autowired
    private KpspmxService kpspmxService;

    /**
     * 一次授权批量插卡--获取授权页链接
     * @param gsdm
     * @param orderid
     * @param timestamp
     * @param menDianId
     * @param type
     * @param access_token
     * @param ticket
     * @param spappid
     * @return
     */
    public Map getWXURL(String gsdm, List orderid, String timestamp, String menDianId, String type, String access_token, String ticket, String spappid){
        Map resultMap = new HashMap();
        if (null == orderid  || timestamp == null || spappid == null || access_token == null || ticket == null) {
            return null;
        }
        logger.info("拉取授权订单编号" + orderid  + "时间" + timestamp + "门店号" + menDianId+"--------------"+gsdm);
        Date dateTime = null;
        if (null != timestamp && !timestamp.equals("")) {
            String[] s = timestamp.split("-");
            if (s.length > 1) {
                dateTime = TimeUtil.getSysDateInDate(timestamp, "yyyy-MM-dd HH:mm:ss");
            } else {
                dateTime = TimeUtil.getSysDateInDate(timestamp, "yyyyMMddHHmmss");
            }
        }
        String source = "web";
        String redirect_url ="";
        if(gsdm.equals("dicos")){
            redirect_url = WeiXinConstants.DICOS_REDIRECT_URL;
        }else if(gsdm.equals("gvc")){
            redirect_url = WeiXinConstants.GVC_REDIRECT_URL;
        }else if(gsdm.equals("chamate")&&menDianId.equals("chamate_test")){
            redirect_url=WeiXinConstants.YCYZ_REDIRECT_URL;
        }else {
            redirect_url = WeiXinConstants.SUCCESS_REDIRECT_URL;
        }
        Map nvps = new HashMap();
        nvps.put("s_pappid", spappid);
        nvps.put("orders", orderid);
        nvps.put("timestamp", dateTime.getTime() / 1000);
        nvps.put("source", source);
        if(type.equals("1")){
            nvps.put("redirect_url", redirect_url);//正式跳转url
        }
        nvps.put("type", type);

        String sj = JSON.toJSONString(nvps);
        String urls = WeiXinConstants.HQ_SQ_URL+access_token;
        logger.info("传给微信的数据---"+sj);
        String jsonStr3 = WeixinUtil.httpRequest(urls, "POST", sj);
        if (null != jsonStr3) {
            ObjectMapper jsonparer = new ObjectMapper();// 初始化解析json格式的对象
            try {
                Map map = jsonparer.readValue(jsonStr3, Map.class);
                logger.info("微信返回数据"+JSON.toJSONString(map));
                int errcode = (int) map.get("errcode");
                if (errcode == 0) {
                    String auth_url = (String) map.get("auth_url");
                    logger.info("跳转url" + auth_url);
                    String authid= (String) map.get("auth_id");
//                    WxFpxx wxFpxx = new WxFpxx();
//                    wxFpxx.setAuthid(authid);
//                    wxfpxxJpaDao.save(wxFpxx);
                    return map;
                } else {
                    logger.info("获取微信授权链接失败!");
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return resultMap;
    }

    /**
     * 一次授权批量插卡--查询授权信息
     * @param auth_id
     * @param access_token
     * @param s_pappid
     * @return
     */
    public Map batchZDCXstatus(String auth_id, String access_token,String s_pappid) {
        Map resultMap = new HashMap();
        String URL = WeiXinConstants.BATCH_SQ_STATUS+access_token;
        if(null == auth_id || s_pappid == null){
            resultMap.put("msg","72038");
            return resultMap;
        }
        Map nvps = new HashMap();
        nvps.put("s_pappid", s_pappid);
        nvps.put("auth_id", auth_id);
        String sj = JSON.toJSONString(nvps);
        String jsonStr3 = WeixinUtil.httpRequest(URL, "POST", sj);
        if (null != jsonStr3) {
            ObjectMapper jsonparer = new ObjectMapper();// 初始化解析json格式的对象
            try {
                Map map = jsonparer.readValue(jsonStr3, Map.class);
                Integer errcode = (Integer) map.get("errcode");
                if (null != errcode && errcode == 0) {
                    logger.info("主动查询授权微信返回数据" + JSON.toJSONString(map));
                    int auth_time = (int) map.get("auth_time");
                    Map user_auth_info = (Map) map.get("user_auth_info");
                    Date date = new Date(auth_time);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String xdsj = sdf.format(date);//下单时间
                    String invoice_status = (String) map.get("invoice_status");
                    if(!invoice_status.equals("auth success")){
                        logger.info("查询授权状态，用户不是已授权");
                        return null;
                    }
                    //个人抬头
                    if (null != user_auth_info.get("user_field")&& !user_auth_info.get("user_field").equals("")) {
                        Map user_field = (Map) user_auth_info.get("user_field");
                        String title = (String) user_field.get("title");
                        String phone = (String) user_field.get("phone");
                        String email = (String) user_field.get("email");
                        List custom_field = (List) user_field.get("custom_field");//备注
                        String key = "";
                        String value = "";
                        if (custom_field.size() > 0) {
                            for (int i = 0; i < custom_field.size(); i++) {
                                Map map1 = (Map) custom_field.get(i);
                                key = (String) map1.get("key");
                                value = (String) map1.get("value");
                            }
                        }
                        resultMap.put("xdsj", xdsj);//下单时间
                        resultMap.put("title", title);//发票抬头名称
                        resultMap.put("phone", phone);//电话
                        resultMap.put("email", email);//邮箱
                        resultMap.put("key", key);
                        resultMap.put("value", value);
                        logger.info("封装之后的数据" + JSON.toJSONString(resultMap));
                        resultMap.put("msg","0");
                        return resultMap;
                    }
                    //单位抬头
                    if (null != user_auth_info.get("biz_field")&&!user_auth_info.get("biz_field").equals("")) {
                        Map biz_field = (Map) user_auth_info.get("biz_field");
                        String title = (String) biz_field.get("title");
                        String tax_no = (String) biz_field.get("tax_no");
                        String addr = (String) biz_field.get("addr");
                        String phone = (String) biz_field.get("phone");
                        String bank_type = (String) biz_field.get("bank_type");
                        String bank_no = (String) biz_field.get("bank_no");
                        List custom_field = (List) biz_field.get("custom_field");
                        String key = "";
                        String value = "";
                        if (custom_field.size() > 0) {
                            for (int i = 0; i < custom_field.size(); i++) {
                                Map map1 = (Map) custom_field.get(i);
                                key = (String) map1.get("key");
                                value = (String) map1.get("value");

                            }
                        }
                        resultMap.put("title", title);//抬头
                        resultMap.put("tax_no", tax_no);//税号
                        resultMap.put("addr", addr);//地址
                        resultMap.put("phone", phone);//电话
                        resultMap.put("bank_type", bank_type);//开户类型
                        resultMap.put("bank_no", bank_no);//银行账号
                        resultMap.put("key", key);//邮箱
                        resultMap.put("value", value);//
                        if (null != key && key.equals("邮箱")) {
                            resultMap.put("email", value);
                        }
                        logger.info("封装之后的数据" + JSON.toJSONString(resultMap));
                        resultMap.put("msg","0");
                        return resultMap;
                    }
                } else {
                    logger.info( auth_id + "授权状态"+errcode+",错误代码" + errcode);
                   resultMap.put("msg","72038");
                   return resultMap;
                }

            } catch (Exception e) {
                e.printStackTrace();
                resultMap.put("msg","72038");
                return resultMap;
            }
        }
        resultMap.put("msg","0");
        return resultMap;
    }
    /**
     * 拒绝开票
     * @param auth_id
     * @param reason
     * @param access_token
     * @return
     */
    public boolean batchRefuseKp(String auth_id, String reason, String access_token,String s_pappid) {
        String refuseURL = WeiXinConstants.BATCH_REFUSE_KP + access_token;
        Map mapInfo = new HashMap();
        mapInfo.put("s_pappid", s_pappid);
        mapInfo.put("order_id", auth_id);
        mapInfo.put("reason", reason);
        String info = JSON.toJSONString(mapInfo);
        String jsonStr3 = WeixinUtil.httpRequest(refuseURL, "POST", info);
        logger.info("返回信息" + jsonStr3.toString());
        if (null != jsonStr3) {
            ObjectMapper jsonparer = new ObjectMapper();// 初始化解析json格式的对象
            try {
                Map map = jsonparer.readValue(jsonStr3, Map.class);
                int errcode = (int) map.get("errcode");
                String errmsg = (String) map.get("errmsg");
                if (errcode == 0) {
                    WxFpxx wxFpxx = wxfpxxJpaDao.selectByAuthId(auth_id);
                    int coun = wxFpxx.getCount()+ 1;
                    wxFpxx.setCount(coun);
                    wxfpxxJpaDao.save(wxFpxx);
                    logger.info("拒绝开票成功,更新计数+1-----"+coun);
                    return true;
                } else if(errcode == 72035) {
                    WxFpxx wxFpxx = wxfpxxJpaDao.selectByAuthId(auth_id);
                    int coun = wxFpxx.getCount()+ 1;
                    wxFpxx.setCount(coun);
                    wxfpxxJpaDao.save(wxFpxx);
                    logger.info("发票已被拒绝,更新计数+1------"+coun);
                    return  true;
                }else {
                    logger.info("拒绝开票失败---" + errmsg);
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * 一次授权批量插卡---将发票插入用户卡包
     * @param auth_id
     * @param card_id
     * @param pdf_file_url
     * @param kplsList
     * @param access_token
     * @return
     */
    public boolean batchDZFPInCard(String auth_id, String card_id,
                                   String pdf_file_url, List<Kpls> kplsList,
                                   String access_token) {
        String appid = WeiXinConstants.APP_ID;
        WeixinUtils weixinUtils = new WeixinUtils();
        Map sj = new HashMap();
        List batch_card_exts = new ArrayList();// 发票列表具体内容
        sj.put("auth_id", auth_id);   //授权id
        sj.put("card_id", card_id);     //发票模板id
        sj.put("appid", appid);         //容津APPid
        sj.put("batch_card_exts", batch_card_exts); // 发票列表具体内容

        for (Kpls kpls : kplsList) {
            Map card_ext = new HashMap();//发票具体内容
            Map map = new HashMap();
            map.put("card_ext", card_ext);//发票具体内容
            map.put("order_id", kpls.getDjh());//订单号 list
            batch_card_exts.add(map);
            WeiXinInfo weiXinInfo = new WeiXinInfo();
            List<Map> info = new ArrayList<>();
            Map invoice_user_data = new HashMap();
            card_ext.put("nonce_str", System.currentTimeMillis() + "");
            Map user_card = new HashMap();//用户信息结构体
            card_ext.put("user_card", user_card);
            user_card.put("invoice_user_data", invoice_user_data);//用户信息结构体
            weiXinInfo.setTitle(kpls.getGfmc());//发票抬头    必填

            BigDecimal bigjshj = new BigDecimal(kpls.getJshj().toString());//价税合计
            BigDecimal bigzh = bigjshj.multiply(new BigDecimal(100));
            //Double doujshj = new Double(bigzh.toString());
            //weiXinInfo.setFee(doujshj);//卡包开票金额,价税合计  必填

            weiXinInfo.setBilling_time(String.valueOf(kpls.getKprq().getTime() / 1000));//开票时间  必填
            weiXinInfo.setBilling_no(kpls.getFpdm());//发票代码      必填
            weiXinInfo.setBilling_code(kpls.getFphm());//发票号码    必填

            BigDecimal bighjje = new BigDecimal(kpls.getHjje().toString());//不含税金额
            BigDecimal bigzzhjje = bighjje.multiply(new BigDecimal(100));
            //Double douhjje = new Double(bigzzhjje.toString());
            //weiXinInfo.setFee_without_tax(douhjje);//不含税金额  必填

            BigDecimal bighjse = new BigDecimal(kpls.getHjse().toString());//税额
            BigDecimal bigzzhjse = bighjse.multiply(new BigDecimal(100));
            //Double douhjse = new Double(bigzzhjse.toString());
            //weiXinInfo.setTax(douhjse);//税额        必填

            weiXinInfo.setCheck_code(kpls.getJym());//校验码    必填
            Map params2 = new HashMap();
            params2.put("kplsh", kpls.getKplsh());
            List<Kpspmx> kpspmxList = kpspmxService.findMxNewList(params2);
            if (kpspmxList.size() > 0) {
                for (Kpspmx kpspmx : kpspmxList) {
                    Map ma = new HashMap();
                    ma.put("name", kpspmx.getSpmc());//商品名称 必填
                    ma.put("num", kpspmx.getSps());//商品数量    必填
                    ma.put("unit", kpspmx.getSpdj());//商品单位  必填
                    ma.put("price", kpspmx.getSpdw());//商品单价 必填
                    info.add(ma);
                }
            }
            //上传PDF生成的一个发票s_media_id   关联发票PDF和发票卡券  必填
            String pdfUrl = kpls.getPdfurl();
            String s_media_id_pdf = weixinUtils.creatPDF(pdfUrl, pdf_file_url, access_token);
            if (null != s_media_id_pdf && StringUtils.isNotBlank(s_media_id_pdf)) {
                weiXinInfo.setS_pdf_media_id(s_media_id_pdf);
            }
            invoice_user_data.put("fee", bigzh.intValue());
            invoice_user_data.put("title", weiXinInfo.getTitle());
            invoice_user_data.put("billing_time", weiXinInfo.getBilling_time());
            invoice_user_data.put("billing_no", weiXinInfo.getBilling_no());
            invoice_user_data.put("billing_code", weiXinInfo.getBilling_code());
            invoice_user_data.put("info", info);
            invoice_user_data.put("fee_without_tax", bigzzhjje.intValue());
            invoice_user_data.put("tax", bigzzhjse.intValue());
            invoice_user_data.put("s_pdf_media_id", weiXinInfo.getS_pdf_media_id());
            invoice_user_data.put("s_trip_pdf_media_id", weiXinInfo.getS_trip_pdf_media_id());
            invoice_user_data.put("check_code", weiXinInfo.getCheck_code());
            invoice_user_data.put("buyer_number", weiXinInfo.getBuyer_number());
            invoice_user_data.put("buyer_address_and_phone", weiXinInfo.getBuyer_address_and_phone());
            invoice_user_data.put("buyer_bank_account", weiXinInfo.getBuyer_bank_account());
            invoice_user_data.put("seller_number", weiXinInfo.getSeller_number());
            invoice_user_data.put("seller_address_and_phone", weiXinInfo.getSeller_address_and_phone());
            invoice_user_data.put("seller_bank_account", weiXinInfo.getSeller_bank_account());
            invoice_user_data.put("remarks", weiXinInfo.getRemarks());
            invoice_user_data.put("cashier", weiXinInfo.getCashier());
            invoice_user_data.put("maker", weiXinInfo.getMaker());
        }
        logger.info("封装数据为"+JSON.toJSONString(sj));
        String URL = WeiXinConstants.BATCH_DZFP_IN_CARD + access_token;
        String jsonStr = WeixinUtil.httpRequest(URL, "POST", JSON.toJSONString(sj));
        if (null != jsonStr) {
            ObjectMapper jsonparer = new ObjectMapper();// 初始化解析json格式的对象
            try {
                Map map = jsonparer.readValue(jsonStr, Map.class);
                logger.info("插卡返回数据"+JSON.toJSONString(map));
                int errcode = (int) map.get("errcode");
                String errmsg = (String) map.get("errmsg");
                if (errcode == 0) {
                    //String openid = (String) map.get("openid");
                    //String code = (String) map.get("code");
                    //WxFpxx wxFpxx = wxfpxxJpaDao.selectByAuthId(auth_id);
                    //wxFpxx.setCode(code);
                   // wxfpxxJpaDao.save(wxFpxx);
                    logger.info("发票插入卡包成功-------------------------");
                    return true;
                } else {
                    logger.info("返回的错误信息为" + errmsg);
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return  false;
            }
        }
        return true;
    }

    /**
     * 一次授权批量插卡--插卡结果查询
     * @param auth_id
     * @param access_token
     * @return
     */
    public Map inCardResult(String auth_id,String access_token){
        String appId = WeiXinConstants.APP_ID;
        String url =WeiXinConstants.BATCH_IN_CARD_RESULT+access_token;
        Map sj = new HashMap();
        sj.put("auth_id",auth_id);
        sj.put("auth_id",appId);
        try {
            String jsonStr3 = WeixinUtil.httpRequest(url, "POST", JSON.toJSONString(sj));
            logger.info("返回数据-"+jsonStr3);
            if(jsonStr3!=null){
                ObjectMapper jsonparer = new ObjectMapper();// 初始化解析json格式的对象
                Map map = jsonparer.readValue(jsonStr3, Map.class);
                int errcode = (int) map.get("errcode");
                if(errcode==0){
                    List order_data = (List) map.get("order_data");
                    if(order_data.size()>0){
                        for (int i=0;i<order_data.size();i++){
                            Map map1 = (Map) order_data.get(i);
                            String order_id = (String) map1.get("order_id");
                            String code = (String) map1.get("code");
                            String card_id = (String) map1.get("card_id");
                        }
                    }
                    return map;
                }else {
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static void main(String[] args) {

    }

    /**
     * 解析微信推送消息xml
     * @param request
     * @return
     * @throws Exception
     */
    public Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        String requestXml = document.asXML();
        String subXml = requestXml.split(">")[0] + ">";
        requestXml = requestXml.substring(subXml.length());
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的全部子节点
        List<Element> elementList = root.elements();
        // 遍历全部子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        map.put("requestXml", requestXml);
        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;

    }
}
