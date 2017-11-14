package com.rjxx.utils.yjapi;

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
                Map maps = jsonparer.readValue(response, Map.class);
                String Status = (String)maps.get("Status");
                String Message = (String) maps.get("Message");
                if("200".equals(Status)){
                    String Result = (String) map.get("Result");
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
                Map maps = jsonparer.readValue(response, Map.class);
                String Status = (String)maps.get("Status");
                String Message = (String) maps.get("Message");
                if("200".equals(Status)){
                    String Result = (String) map.get("Result");
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
        System.out.println(getQccGsxx("上海容津信息技术有限公司"));
    }
}
