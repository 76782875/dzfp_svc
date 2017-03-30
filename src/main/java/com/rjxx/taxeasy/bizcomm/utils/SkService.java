package com.rjxx.taxeasy.bizcomm.utils;

import com.rjxx.utils.DesUtils;
import com.rjxx.utils.HttpUtils;
import com.rjxx.utils.XmlJaxbUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 调用税控服务开票
     *
     * @param kplsh
     * @return
     */
    public InvoiceResponse callService(int kplsh) throws Exception {
        if (StringUtils.isBlank(skServerUrl)) {
            return InvoiceResponseUtils.responseError("skServerUrl为空");
        }
        String encryptStr = encryptSkServerParameter(kplsh + "");
        String url = skServerUrl + "/invoice/invoice";
        Map<String, String> map = new HashMap<>();
        map.put("p", encryptStr);
        String result = HttpUtils.doPost(url, map);
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
        String url = skServerUrl + "/invoice/getCodeAndNo";
        Map<String, String> map = new HashMap<>();
        map.put("p", encryptStr);
        String result = HttpUtils.doPost(url, map);
        InvoiceResponse response = XmlJaxbUtils.convertXmlStrToObject(InvoiceResponse.class, result);
        return response;
    }

    /**
     * 作废发票
     *
     * @param kplsh
     * @param
     * @return
     */
    public InvoiceResponse voidInvoice(int kplsh) throws Exception {
        if (StringUtils.isBlank(skServerUrl)) {
            return InvoiceResponseUtils.responseError("skServerUrl为空");
        }
        String encryptStr = encryptSkServerParameter(kplsh + "");
        String url = skServerUrl + "/invoice/voidInovice";
        Map<String, String> map = new HashMap<>();
        map.put("p", encryptStr);
        String result = HttpUtils.doPost(url, map);
        InvoiceResponse response = XmlJaxbUtils.convertXmlStrToObject(InvoiceResponse.class, result);
        return response;
    }

    /**
     * 发票重打
     *
     * @param kplsh
     * @param
     * @return
     */
    public InvoiceResponse reprintInvoice(int kplsh) throws Exception {
        if (StringUtils.isBlank(skServerUrl)) {
            return InvoiceResponseUtils.responseError("skServerUrl为空");
        }
        String encryptStr = encryptSkServerParameter(kplsh + "");
        String url = skServerUrl + "/invoice/reprintInvoice";
        Map<String, String> map = new HashMap<>();
        map.put("p", encryptStr);
        String result = HttpUtils.doPost(url, map);
        InvoiceResponse response = XmlJaxbUtils.convertXmlStrToObject(InvoiceResponse.class, result);
        return response;
    }

    /**
     * 获取库存
     *
     * @param kpdid
     * @return
     */
    public List<Map<String, Object>> getKc(int kpdid) {
        //fpzldm,fpdm,fphms,fphmz,kyl
        return null;
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
