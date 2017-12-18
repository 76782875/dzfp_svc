package com.rjxx.utils.yjapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rjxx.taxeasy.domains.Qympk;
import com.rjxx.taxeasy.service.QympkService;
import com.rjxx.utils.weixin.HttpClientUtil;
import com.rjxx.utils.weixin.WeixinUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017-11-14.
 */
@Service
public class QCCUtils {
    private static Logger logger = LoggerFactory.getLogger(WeixinUtils.class);


    @Autowired
    private QympkService qympkService;

    /**
     * 企查查--获取纳税人识别号
     * @param keyWord
     * @return
     */
    public static String getQccGsxx(String keyWord){
        String resultMap=null;
        try {
            Map map = new HashMap();
            map.put("key",QCCConstants.ApiKey);
            map.put("dtype","json");
            map.put("keyWord",keyWord);
            String response = HttpClientUtil.doGet(QCCConstants.GET_QCC_GSXX, map);
            logger.debug("返回值------------" + response);
            if(response!=null){
                JSONObject jsonObject = JSON.parseObject(response);
                String status= jsonObject.getString("Status");
                String message = jsonObject.getString("Message");
                if("200".equals(status)){
                    JSONObject result = jsonObject.getJSONObject("Result");
                    if(result!=null){
                        resultMap=result.toJSONString();
                    }
                }else {
                    resultMap=message;
                }
            }
        }catch (Exception e){
            logger.info("msg=" +e);
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 企查查-获取纳税人识别号(包含账户信息)
     * @param keyWord
     * @return
     */
    public  String getQccGsxxNew(String keyWord){
        String resultMap=null;
        try {
            Map map = new HashMap();
            map.put("key",QCCConstants.ApiKey);
            map.put("dtype","json");
            map.put("keyWord",keyWord);
            String response = HttpClientUtil.doGet(QCCConstants.GET_QCC_GSXX_NEW, map);
            if(response!=null){
                JSONObject jsonObject = JSON.parseObject(response);
                String status= jsonObject.getString("Status");
                String message = jsonObject.getString("Message");
                if("200".equals(status)){
                    JSONObject result = jsonObject.getJSONObject("Result");
                    if(result!=null){
                        logger.info("结果"+JSON.toJSONString(result));
                        Qympk qympk = new Qympk();
                        qympk.setDwmc(result.getString("Name"));//公司名称
                        qympk.setNsrsbh(result.getString("CreditCode"));//税号
                        qympk.setQylx(result.getString("EconKind"));//企业类型
                        qympk.setQyzt(result.getString("Status"));//企业状态
                        qympk.setZcdz(result.getString("Address"));//地址
                        qympk.setZcdh(result.getString("Tel"));//电话
                        qympk.setKhyh(result.getString("Bank"));//开户行
                        qympk.setYhzh(result.getString("BankAccount"));//银行账号
                        qympk.setLrsj(new Date());
                        //qccJpaDao.save(qcc);
                        resultMap= result.toJSONString();
                    }
                }else {
                    resultMap=message;
                }
            }
        }catch (Exception e){
            logger.info("msg=" +e);
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 企业关键字模糊查询
     * @param keyWord
     * @return
     */
    public  String getQccSearch(String keyWord){
        String resultMap=null;
        List nameList = new ArrayList<>();
        try {
            Map map = new HashMap();
            map.put("key","f2c9da96b3fa45f6bce3637e2a3e6c74");
            map.put("keyWord",keyWord);
            map.put("exactlyMatch","否");
            map.put("pageSize","20");
            map.put("pageIndex","1");
            map.put("dtype","json");
            String response = HttpClientUtil.doGet(QCCConstants.GET_QCC_SEARCH, map);
            logger.debug("返回值------------" + response);
            if(response!=null){
                JSONObject jsonObject = JSON.parseObject(response);
                String status= jsonObject.getString("Status");
                String message = jsonObject.getString("Message");
                if("200".equals(status)){
                    JSONArray resultList = jsonObject.getJSONArray("Result");
                    if(resultList!=null){
                        List<Qympk> qympkList = new ArrayList<>();
                        for(int i=0;i<resultList.size();i++){
                            JSONObject object= (JSONObject) resultList.get(i);
                            Map insertMap = new HashMap();
                            if(!object.getString("Name").equals("")&&!object.getString("CreditCode").equals("")){
                                insertMap.put("label", object.getString("Name") + "(" + object.getString("CreditCode")+")");
                                insertMap.put("value", object.getString("Name"));
                                nameList.add(insertMap);
                                Qympk qympk = new Qympk();
                                qympk.setDwmc(object.getString("Name"));
                                qympk.setFrmc(object.getString("OperName"));
                                String startDate = object.getString("StartDate");
                                DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                                Date clrq = df.parse(startDate);
                                qympk.setClrq(clrq);
                                qympk.setQyzt(object.getString("Status"));
                                qympk.setQyzch(object.getString("No"));
                                qympk.setNsrsbh(object.getString("CreditCode"));
                                qympk.setLrsj(new Date());
                                qympkList.add(qympk);
                            }

                        }
                        QccTask qccTask = new QccTask();
                        qccTask.setQympkService(qympkService);
                        qccTask.setQympkList(qympkList);
                        Thread thread = new Thread(qccTask);
                        thread.start();
//                        qccTask.run();
                        //qccJpaDao.save(qccs);
                        resultMap = JSON.toJSONString(nameList);
                    }
                }else {
                    resultMap=message;
                }
            }
        }catch (Exception e){
            logger.info("msg=" +e);
            e.printStackTrace();
        }
        return resultMap;
    }

    public static void main(String[] args) {
        QCCUtils qccUtils = new QCCUtils();
        String search = qccUtils.getQccGsxxNew("上海容津信息技术有限公司");
        System.out.println(search);
    }
}
