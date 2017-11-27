package com.rjxx.utils.yjapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rjxx.utils.weixin.HttpClientUtil;
import com.rjxx.utils.weixin.WeixinUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-11-14.
 */
public class QCCUtils {
    private static Logger logger = LoggerFactory.getLogger(WeixinUtils.class);


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
    public static String getQccGsxxNew(String keyWord){
        String resultMap=null;
        try {
            Map map = new HashMap();
            map.put("key",QCCConstants.ApiKey);
            map.put("dtype","json");
            map.put("keyWord",keyWord);
            String response = HttpClientUtil.doGet(QCCConstants.GET_QCC_GSXX_NEW, map);
            logger.debug("返回值------------" + response);
            if(response!=null){
                JSONObject jsonObject = JSON.parseObject(response);
                String status= jsonObject.getString("Status");
                String message = jsonObject.getString("Message");
                if("200".equals(status)){
                    JSONObject result = jsonObject.getJSONObject("Result");
                    if(result!=null){
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
    public static String getQccSearch(String keyWord){
        String resultMap=null;
        List nameList = new ArrayList<>();
        try {
            Map map = new HashMap();
            map.put("key","f2c9da96b3fa45f6bce3637e2a3e6c74");
            map.put("keyWord",keyWord);
            map.put("exactlyMatch","否");
            map.put("pageSize","10");
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
                        for(int i=0;i<resultList.size();i++){
                            JSONObject object= (JSONObject) resultList.get(i);
                            nameList.add(object.getString("Name"));
                        }
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
        String search = getQccGsxxNew("上海容津信息技术有限公司");
        System.out.println(search);
    }
}
