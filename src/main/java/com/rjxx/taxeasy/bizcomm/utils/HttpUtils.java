package com.rjxx.taxeasy.bizcomm.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rjxx.utils.SignUtils;
import com.rjxx.utils.XmlJaxbUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xlm on 2017/7/21.
 */
public class HttpUtils {


    private  static Logger logger = LoggerFactory.getLogger(HttpUtils.class);


    //public static String WS_URL = "http://open.datarj.com/webService/services/invoiceService?wsdl";
    public static String WS_URL = "http://test.datarj.com/webService/services/invoiceService?wsdl";
    //public static String WS_URL = "http://localhost:8080/services/invoiceService?wsdl";
    /**
     * 调用2.0接口开票
     * @param QueryData
     * @param AppId
     * @param key
     * @return
     */
    public static String HttpUrlPost(String QueryData,String AppId,String key){
        String result="";
        try {
            logger.info("----------发送的报文------"+QueryData);
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            Client client = dcf.createClient(WS_URL);
            String methodName = "UploadOrderData";
            String sign= SignUtils.getSign(QueryData,key);
            Object[] objects = client.invoke(methodName, AppId, sign, "01", QueryData);
            //输出调用结果
            result = objects[0].toString();
            logger.info("----------接收返回值------"+result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
