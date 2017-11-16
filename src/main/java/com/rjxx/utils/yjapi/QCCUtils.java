package com.rjxx.utils.yjapi;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rjxx.utils.weixin.HttpClientUtil;
import com.rjxx.utils.weixin.WeixinUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
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
            logger.info("返回值------------" + response);
            if(response!=null){
                ObjectMapper jsonparer = new ObjectMapper();// 初始化解析json格式的对象
                Map resultmap = jsonparer.readValue(response, Map.class);
                String Status = (String)resultmap.get("Status");
                String Message = (String)resultmap.get("Message");
                if("200".equals(Status)){
                    String Result = (String)resultmap.get("Result");
                    if(Result!=null){
                        resultMap=Result;
                    }
                }else {
                    resultMap=Message;
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
            logger.info("返回值------------" + response);
            if(response!=null){
                ObjectMapper jsonparer = new ObjectMapper();// 初始化解析json格式的对象
                Map resultmap = jsonparer.readValue(response, Map.class);
                String Status = (String)resultmap.get("Status");
                String Message = (String) resultmap.get("Message");
                if("200".equals(Status)){
                    String Result = (String) resultmap.get("Result");
                    if(Result!=null){
                        resultMap=Result;
                    }
                }else {
                    resultMap=Message;
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
        try {
            Map map = new HashMap();
            map.put("key","f2c9da96b3fa45f6bce3637e2a3e6c74");
            map.put("keyWord",keyWord);
            map.put("exactlyMatch","否");
            map.put("pageSize","10");
            map.put("pageIndex","1");
            map.put("dtype","json");
            String response = HttpClientUtil.doGet(QCCConstants.GET_QCC_SEARCH, map);
            logger.info("返回值------------" + response);
            if(response!=null){
                ObjectMapper jsonparer = new ObjectMapper();// 初始化解析json格式的对象
                Map resultmap = jsonparer.readValue(response, Map.class);
                String Status = (String)resultmap.get("Status");
                String Message = (String)resultmap.get("Message");
                if("200".equals(Status)){
                    String Result = (String)resultmap.get("Result");
                    if(Result!=null){
                        resultMap=Result;
                    }
                }else {
                    resultMap=Message;
                }
            }
        }catch (Exception e){
            logger.info("msg=" +e);
            e.printStackTrace();
        }
        return resultMap;
    }

    public static void main(String[] args) {
        String search = getQccSearch("容津");
        System.out.println(JSON.toJSONString(search));
    }
}
