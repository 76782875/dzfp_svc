package com.rjxx.taxeasy.bizcomm.utils;

import com.alibaba.fastjson.JSON;
import org.apache.axiom.om.OMElement;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xlm on 2017/7/3.
 * 获取全家，绿地优鲜接口提供的数据
 */
@Service
public class GetDataService {

    public void getData(String ExtractCode){
            String strMessage = "";
            BufferedReader reader = null;
            StringBuffer buffer = new StringBuffer();
            Map resultMap = null;

            HttpPost httpPost = new HttpPost("");
            CloseableHttpResponse response = null;
            RequestConfig requestConfig = RequestConfig.custom().
                    setSocketTimeout(120*1000).setConnectionRequestTimeout(120*1000).setConnectTimeout(120*1000).build();
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(requestConfig)
                    .build();
            //httpPost.setConfig(requestConfig);
            httpPost.addHeader("Content-Type", "application/json");
            try {
                Map nvps = new HashMap();
                nvps.put("ExtractCode", ExtractCode);
                StringEntity requestEntity = new StringEntity(JSON.toJSONString(nvps), "utf-8");
                httpPost.setEntity(requestEntity);
                response = httpClient.execute(httpPost, new BasicHttpContext());
                if (response.getStatusLine().getStatusCode() != 200) {
                    System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode()
                            + ", url=" + "");
                }
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    reader = new BufferedReader(new InputStreamReader(entity.getContent(), "utf-8"));
                    while ((strMessage = reader.readLine()) != null) {
                        buffer.append(strMessage);
                    }
                }
                System.out.println("接收返回值:" + buffer.toString());
                interpreting(buffer.toString());
            }catch (Exception e){
                System.out.println("request url=" + "" + ", exception, msg=" + e.getMessage());
                e.printStackTrace();
                e.printStackTrace();
            }
    }
    public void interpreting(String data)throws Exception {

        Document xmlDoc = null;
        OMElement root = null;
        try {
            xmlDoc = DocumentHelper.parseText(data);
            root = XmlMapUtils.xml2OMElement(data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Map rootMap = XmlMapUtils.xml2Map(root, "Orders");
    }
}
