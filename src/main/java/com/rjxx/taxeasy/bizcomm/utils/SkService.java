package com.rjxx.taxeasy.bizcomm.utils;

import com.rjxx.utils.DesUtils;
import com.rjxx.utils.XmlJaxbUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 税控操作的service
 * Created by Zhangbing on 2017-02-13.
 */
@Service
public class SkService {

    public static final String SK_SERVER_DES_KEY = "R1j2x3x4";

    @Value("${sk_server_url:}")
    private String skServerUrl;

    /**
     * 调用税控服务
     *
     * @param kplsh
     * @return
     */
    public InvoiceResponse callService(int kplsh) throws Exception {
        if (StringUtils.isBlank(skServerUrl)) {
            return InvoiceResponseUtils.responseError("skServerUrl为空");
        }
        String encryptStr = encryptSkServerParameter(kplsh + "");
        HttpPost httpPost = new HttpPost(skServerUrl + "/invoice/invoice");
        NameValuePair pair = new BasicNameValuePair("p", encryptStr);
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(pair);
        HttpEntity httpEntity = new UrlEncodedFormEntity(nameValuePairList);
        httpPost.setEntity(httpEntity);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        InputStream is = httpResponse.getEntity().getContent();
        String result = IOUtils.toString(is, "UTF-8");
        InvoiceResponse response = XmlJaxbUtils.convertXmlStrToObject(InvoiceResponse.class, result);
        return response;
    }

    /**
     * 获取发票代码发票号码
     *
     * @param kpdid
     * @param fplxdm
     * @return
     */
    public InvoiceResponse getCodeAndNo(int kpdid, String fplxdm) throws Exception {
        if (StringUtils.isBlank(skServerUrl)) {
            return InvoiceResponseUtils.responseError("skServerUrl为空");
        }
        String params = "kpdid=" + kpdid + "&fplxdm=" + fplxdm;
        String encryptStr = encryptSkServerParameter(params);
        HttpPost httpPost = new HttpPost(skServerUrl + "/invoice/invoice");
        NameValuePair pair = new BasicNameValuePair("p", encryptStr);
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(pair);
        HttpEntity httpEntity = new UrlEncodedFormEntity(nameValuePairList);
        httpPost.setEntity(httpEntity);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        InputStream is = httpResponse.getEntity().getContent();
        String result = IOUtils.toString(is, "UTF-8");
        InvoiceResponse response = XmlJaxbUtils.convertXmlStrToObject(InvoiceResponse.class, result);
        return response;
    }

    /**
     * 加密税控服务参数
     *
     * @param params
     * @return
     */
    public String encryptSkServerParameter(String params) throws Exception {
        return DesUtils.DESEncrypt(params, SK_SERVER_DES_KEY);
    }

    public String decryptSkServerParameter(String encryptParams) throws Exception {
        return DesUtils.DESDecrypt(encryptParams, SK_SERVER_DES_KEY);
    }


}
