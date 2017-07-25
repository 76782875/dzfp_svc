package com.rjxx.taxeasy.bizcomm.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rjxx.utils.SignUtils;
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

    /**
     * 调用2.0接口开票
     * @param QueryData
     * @param AppId
     * @param key
     * @return
     */
    public static String HttpUrlPost(String QueryData,String AppId,String key){
        String sign= SignUtils.getSign(QueryData,key);
        ObjectMapper mapper=new ObjectMapper();
        Map param = new HashMap();
        param.put("methodName", "UploadOrderData");
        param.put("AppKey",AppId);
        param.put("Secret", sign);
        param.put("Operation","01");
        param.put("InvoiceData",QueryData);
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();
        try {
            String jsonString = mapper.writeValueAsString(param);
            String strMessage = "";
            // 接报文的地址
            String urldev="http://localhost:8080/dzfp-service-new/service";//本地
            String urldev1="http://test.datarj.com/webService/service";//测试库
            String url="http://open2.datarj.com/webService/service";//生产库
            URL uploadServlet = new URL(urldev1);
            HttpURLConnection servletConnection = (HttpURLConnection) uploadServlet.openConnection();
            // 设置连接参数
            servletConnection.setRequestProperty("Content-Type", "plain/text; charset=UTF-8");
            servletConnection.setRequestMethod("POST");
            servletConnection.setDoOutput(true);
            servletConnection.setDoInput(true);
            servletConnection.setAllowUserInteraction(true);
            // 开启流，写入XML数据
            OutputStream output = servletConnection.getOutputStream();
            logger.info("----------发送的报文------"+jsonString.toString());
            output.write(jsonString.toString().getBytes());
            output.flush();
            output.close();
            // 获取返回的数据
            InputStream inputStream = servletConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((strMessage = reader.readLine()) != null) {
                buffer.append(strMessage);
            }
            logger.info("----------接收返回值------"+buffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
