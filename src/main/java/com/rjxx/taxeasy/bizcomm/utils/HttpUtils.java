package com.rjxx.taxeasy.bizcomm.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rjxx.taxeasy.domains.Fphxwsjl;
import com.rjxx.taxeasy.domains.Gsxx;
import com.rjxx.taxeasy.service.FphxwsjlService;
import com.rjxx.utils.SignUtils;
import com.rjxx.utils.XmlJaxbUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by xlm on 2017/7/21.
 */
@Service
public class HttpUtils {



    private  static Logger logger = LoggerFactory.getLogger(HttpUtils.class);





    //public static String WS_URL = "http://open2.datarj.com/webService/services/invoiceService?wsdl";
    public static String WS_URL = "http://test.datarj.com/webService/services/invoiceService?wsdl";
    //public static String WS_URL = "http://localhost:8080/services/invoiceService?wsdl";
   // public static String WS_URL = "http://192.1681.133:8080/webService/services/invoiceService?wsdl";
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
            Client client = dcf.createClient(URLUtils.WS_URL);
            String methodName = "UploadOrderData";
            HTTPConduit conduit = (HTTPConduit) client.getConduit();
            HTTPClientPolicy policy = new HTTPClientPolicy();
            policy.setConnectionTimeout(180000); //连接超时时间
            policy.setReceiveTimeout(180000);//请求超时时间.
            conduit.setClient(policy);
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

    public static String HttpUrlWebService(String QueryData,String AppId,String key,String kpff){
        String result="";
        try {
            logger.info("----------发送的报文------"+QueryData);
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            Client client = dcf.createClient(URLUtils.WS_URL);
            String methodName = "UploadOrderData";
            String sign= SignUtils.getSign(QueryData,key);
            Object[] objects = client.invoke(methodName, AppId, sign, kpff, QueryData);
            //输出调用结果
            result = objects[0].toString();
            logger.info("----------接收返回值------"+result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    static int socketTimeout = 180*1000;// 请求超时时间
    static int connectTimeout = 180*1000;// 传输超时时间


    /**
     * 使用SOAP1.1发送消息
     *
     * @param postUrl
     * @param soapXml
     * @param soapAction
     * @return
     */
    public static String doPostSoap1_1(String postUrl, String soapXml,
                                       String soapAction,String username,String password) {
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(postUrl);
        //  设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);
        try {
            httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
            httpPost.setHeader("SOAPAction", soapAction);
            String authString = username + ":" + password;
            byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
            String authStringEnc = new String(authEncBytes);
            httpPost.addHeader("Authorization",  "Basic " + authStringEnc);
            StringEntity data = new StringEntity(soapXml,
                    Charset.forName("UTF-8"));
            httpPost.setEntity(data);
            CloseableHttpResponse response = closeableHttpClient
                    .execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                retStr = EntityUtils.toString(httpEntity, "UTF-8");
                //System.out.println("response:" + retStr);
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            System.out.println("exception in doPostSoap1_1" + e);
        }
        return retStr;
    }


    /**
     * 使用SOAP1.2发送消息
     *
     * @param postUrl
     * @param soapXml
     * @param soapAction
     * @return
     */
    public static String doPostSoap1_2(String postUrl, String soapXml,
                                       String soapAction,String username,String password) {
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(postUrl);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);
        try {
            httpPost.setHeader("Content-Type",
                    "application/soap+xml;charset=UTF-8");
            httpPost.setHeader("SOAPAction", soapAction);
            String authString = username + ":" + password;
            byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
            String authStringEnc = new String(authEncBytes);
            httpPost.addHeader("Authorization",  "Basic " + authStringEnc);
            StringEntity data = new StringEntity(soapXml,
                    Charset.forName("UTF-8"));
            httpPost.setEntity(data);
            CloseableHttpResponse response = closeableHttpClient
                    .execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                retStr = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println("response:" + retStr);
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            System.out.println("exception in doPostSoap1_2" + e);
        }
        return retStr;
    }

    /**
     * .net webService
     * @param url
     * @param methodName
     * @param QueryData
     * @param AppId
     * @param key
     * @return
     */
    public static String netWebService (String url,String methodName,String QueryData,String AppId,String key){
        String result="";
        try {
            logger.info("----------发送的报文------"+QueryData);
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            Client client = dcf.createClient(url);
            String sign= SignUtils.getSign(QueryData,key);
            Object[] objects = client.invoke(methodName, AppId,QueryData,sign);
            //输出调用结果
            result = objects[0].toString();
            logger.info("----------接收返回值------"+result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 调用.net ws设置参数接口
     * @param url
     * @param methodName
     * @param servletip
     * @param servletport
     * @param keypwd
     * @return
     */
    public static String netSetParameter (String url,String methodName,String servletip,String servletport,String keypwd,String t){
        String result="";
        try {
            logger.info("----------发送的报文------"+keypwd);
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            Client client = dcf.createClient(url);
            //String sign= SignUtils.getSign(QueryData,key);
            Object[] objects = client.invoke(methodName, servletip,servletport,keypwd,t);
            //输出调用结果
            result = objects[0].toString();
            logger.info("----------接收返回值------"+result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 调用.net ws获取发票领购信息
     * @param url
     * @param methodName
     * @param nsrsbh
     * @param kpzdbs
     * @param fplxdm
     * @param fpzt
     * @param lgqxx
     * @return
     */
    public static String netFPLGXXCX (String url,String methodName,String nsrsbh,String kpzdbs,String fplxdm,String fpzt,String lgqxx){
        String result="";
        try {
            logger.info("----------发送的报文------"+methodName);
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            Client client = dcf.createClient(url);
            //String sign= SignUtils.getSign(QueryData,key);
            Object[] objects = client.invoke(methodName, nsrsbh,kpzdbs,fplxdm,fpzt,lgqxx);
            //输出调用结果
            result = objects[0].toString();
            logger.info("----------接收返回值------"+result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String HttpPost_Basic(String url,String data){
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        RequestConfig requestConfig = RequestConfig.custom().
                setSocketTimeout(120*1000).setConnectionRequestTimeout(120*1000).setConnectTimeout(120*1000).build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();
        String strMessage = "";
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();
        httpPost.addHeader("Content-Type", "application/json");
        try {
            StringEntity requestEntity = new StringEntity(data, "utf-8");
            httpPost.setEntity(requestEntity);
            response = httpClient.execute(httpPost, new BasicHttpContext());
            if (response.getStatusLine().getStatusCode() != 200) {
                logger.info("request url failed, http code=" + response.getStatusLine().getStatusCode()
                        + ", url=" + url);
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                reader = new BufferedReader(new InputStreamReader(entity.getContent(), "utf-8"));
                while ((strMessage = reader.readLine()) != null) {
                    buffer.append(strMessage);
                }
            }
            logger.info("接收返回值:" + buffer.toString());
        }catch (IOException e){
            logger.info("request url=" + url + ", exception, msg=" + e.getMessage());
            e.printStackTrace();
        }
        return buffer.toString();
    }
    public static String Https_post(String url,Map<String,String> data) throws Exception {

            String result=null;
            CloseableHttpResponse httpResponse=null;
            CloseableHttpClient httpClient=null;
        try{
            List<NameValuePair> nameValuePairList = new ArrayList<>(data.size());
            for (Map.Entry<String, String> entry : data.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
                nameValuePairList.add(pair);
            }
            HttpPost httpPost = new HttpPost(url);
            HttpEntity httpEntity = new UrlEncodedFormEntity(nameValuePairList);
            httpPost.setEntity(httpEntity);
            httpClient = new SSLClient();
            httpResponse = httpClient.execute(httpPost);
            InputStream is = httpResponse.getEntity().getContent();
            result = IOUtils.toString(is, "UTF-8");
        }catch (IOException e){
            logger.info("request url=" + url + ", exception, msg=" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (httpResponse != null) try {
                httpResponse.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    /**
     * https使用SOAP1.2发送消息
     *
     * @param postUrl
     * @param soapXml
     * @param soapAction
     * @return
     */
    public static String HttpsdoPostSoap1_2(String postUrl, String soapXml,
                                       String soapAction,String username,String password) {
        String retStr = "";
        // 创建HttpClientBuilder
        try {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = new SSLClient();
        HttpPost httpPost = new HttpPost(postUrl);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);

            httpPost.setHeader("Content-Type",
                    "application/soap+xml;charset=UTF-8");
            httpPost.setHeader("SOAPAction", soapAction);
            /*String authString = username + ":" + password;
            byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
            String authStringEnc = new String(authEncBytes);
            httpPost.addHeader("Authorization",  "Basic " + authStringEnc);*/
            StringEntity data = new StringEntity(soapXml,
                    Charset.forName("UTF-8"));
            httpPost.setEntity(data);
            CloseableHttpResponse response = closeableHttpClient
                    .execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                retStr = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println("response:" + retStr);
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            System.out.println("exception in doPostSoap1_2" + e);
        }
        return retStr;
    }
    public static void main(String[] args) {
        /*String wsurl="https://192.9.250.216/webService/services/invoiceService?wsdl";
        String xml="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.rj.com\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ser:CallService2>\n" +
                "         <!--Optional:-->\n" +
                "         <invoiceData>测试发士大夫士大夫士大夫</invoiceData>\n" +
                "      </ser:CallService2>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        HttpsdoPostSoap1_2(wsurl,xml,null,null,null);*/
        String wsurl="http://192.168.1.200:8009/Service.asmx?wsdl";
        //netSetParameter(wsurl,"SetParameter","210.14.78.228","7090","00000000",null);

        netFPLGXXCX(wsurl,"FPLGXXCX",null,"qs1njkpd1","026","1","1");
        //System.out.println(t);
    }
}
