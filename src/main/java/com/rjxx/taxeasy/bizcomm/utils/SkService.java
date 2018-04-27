package com.rjxx.taxeasy.bizcomm.utils;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rjxx.taxeasy.business.ims.service.DubboSkpService;
import com.rjxx.taxeasy.business.tcs.service.DubboInvoiceService;
import com.rjxx.taxeasy.domains.Cszb;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.domains.Skp;
import com.rjxx.taxeasy.service.CszbService;
import com.rjxx.taxeasy.service.KplsService;
import com.rjxx.taxeasy.service.KpspmxService;
import com.rjxx.taxeasy.service.SkpService;
import com.rjxx.utils.DesUtils;
import com.rjxx.utils.HttpUtils;
import com.rjxx.utils.StringUtils;
import com.rjxx.utils.XmlJaxbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Value("${skkp_server_url:}")
    private String skkpServerUrl;

    @Reference(version = "1.0.0",group = "tcs",timeout = 12000,retries = '0')
    public DubboInvoiceService dubboInvoiceService;

    @Reference(version = "1.0.0",group = "ims",timeout = 12000,retries = '0')
    public DubboSkpService dubboSkpService;

    @Autowired
    private KplsService kplsService;
    @Autowired
    private CszbService cszbService;
    @Autowired
    private SkpService skpService;
    @Autowired
    private KpspmxService kpspmxService;



    private Logger logger=LoggerFactory.getLogger(this.getClass());



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
        Kpls kpls=kplsService.findOne(kplsh);
        Cszb cszb=cszbService.getSpbmbbh(kpls.getGsdm(),kpls.getXfid(),kpls.getSkpid(),"sfqysknew");
        String result=null;
        if("是".equals(cszb.getCsz())){
             result=dubboInvoiceService.invoice(encryptStr);
        }else{
            String url = skServerUrl + "/invoice/invoice";
            Map<String, String> map = new HashMap<>();
            map.put("p", encryptStr);
             result = HttpUtils.doPost(url, map);
        }
        InvoiceResponse response = XmlJaxbUtils.convertXmlStrToObject(InvoiceResponse.class, result);
        return response;
    }
    /**
     * 重新生成pdf
     *
     * @param kplsh
     * @return
     */
    public InvoiceResponse ReCreatePdf(int kplsh) throws Exception {

        if (StringUtils.isBlank(skkpServerUrl)) {
            return InvoiceResponseUtils.responseError("skkpServerUrl为空");
        }
        String encryptStr = encryptSkServerParameter(kplsh + "");
        Kpls kpls=kplsService.findOne(kplsh);
        Cszb cszb=cszbService.getSpbmbbh(kpls.getGsdm(),kpls.getXfid(),kpls.getSkpid(),"sfqysknew");
        String result=null;
        if("是".equals(cszb.getCsz())){
            result=dubboInvoiceService.ReCreatePdf(encryptStr);
        }else{
            String url = skkpServerUrl + "/invoice/ReCreatePdf";
            Map<String, String> map = new HashMap<>();
            map.put("p", encryptStr);
            result = HttpUtils.doPost(url, map);
        }
        InvoiceResponse response = XmlJaxbUtils.convertXmlStrToObject(InvoiceResponse.class, result);
        return response;
    }
    /**
     * 税控服务器开票
     *
     * @param kplsh
     * @return
     */
    public InvoiceResponse SkServerKP(int kplsh) throws Exception {

        InvoiceResponse response=null;
        String result=null;
        try{
            if (StringUtils.isBlank(skkpServerUrl)) {
                return InvoiceResponseUtils.responseError("skkpServerUrl为空");
            }
            String encryptStr = encryptSkServerParameter(kplsh + "");
            Kpls kpls=kplsService.findOne(kplsh);
            Cszb cszb=cszbService.getSpbmbbh(kpls.getGsdm(),kpls.getXfid(),kpls.getSkpid(),"sfqysknew");
            if("是".equals(cszb.getCsz())){
                result=dubboInvoiceService.skServerKP(encryptStr);
            }else{
                String url = skkpServerUrl + "/invoice/SkServerKP";
                Map<String, String> map = new HashMap<>();
                map.put("p", encryptStr);
                result = HttpUtils.doPost(url, map);
            }
            if(result!=null){
                response= XmlJaxbUtils.convertXmlStrToObject(InvoiceResponse.class, result);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("------返回数据--------"+result);
        }
        return response;
    }
    /**
     * 税控服务器开票
     *
     * @param kplsh
     * @return
     */
    public InvoiceResponse SkBoxKP(int kplsh) throws Exception {

        InvoiceResponse response=null;
        String result=null;
        try{
            if (StringUtils.isBlank(skkpServerUrl)) {
                return InvoiceResponseUtils.responseError("skkpServerUrl为空");
            }
            String encryptStr = encryptSkServerParameter(kplsh + "");
            Kpls kpls=kplsService.findOne(kplsh);
            Cszb cszb=cszbService.getSpbmbbh(kpls.getGsdm(),kpls.getXfid(),kpls.getSkpid(),"sfqysknew");
            if("是".equals(cszb.getCsz())){
                result=dubboInvoiceService.skBoxKP(encryptStr);
            }else{
                String url = skkpServerUrl + "/invoice/SkBoxP";
                Map<String, String> map = new HashMap<>();
                map.put("p", encryptStr);
                result = HttpUtils.doPost(url, map);
            }
            if(result!=null){
                response= XmlJaxbUtils.convertXmlStrToObject(InvoiceResponse.class, result);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("------返回数据--------"+result);
        }
        return response;
    }
    /**
     * 税控服务器开票https
     *
     * @param kplsh
     * @return
     */
    public InvoiceResponse SkServerKPhttps(int kplsh) throws Exception {

        if (StringUtils.isBlank(skkpServerUrl)) {
            return InvoiceResponseUtils.responseError("skkpServerUrl为空");
        }
        String encryptStr = encryptSkServerParameter(kplsh + "");
        String url = skkpServerUrl + "/invoice/SkServerKP";
        Map<String, String> map = new HashMap<>();
        map.put("p", encryptStr);
        String result = com.rjxx.taxeasy.bizcomm.utils.HttpUtils.Https_post(url,map);
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
        Skp skp=skpService.findOne(kpdid);
        Cszb cszb=cszbService.getSpbmbbh(skp.getGsdm(),skp.getXfid(),skp.getId(),"sfqysknew");
        String result=null;
        if("是".equals(cszb.getCsz())){
            result=dubboInvoiceService.getCodeAndNo(encryptStr);
        }else{
            String url = skServerUrl + "/invoice/getCodeAndNo";
            Map<String, String> map = new HashMap<>();
            map.put("p", encryptStr);
            result = HttpUtils.doPost(url, map);
        }
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
        Kpls kpls=kplsService.findOne(kplsh);
        Cszb cszb=cszbService.getSpbmbbh(kpls.getGsdm(),kpls.getXfid(),kpls.getSkpid(),"sfqysknew");
        String result=null;
        if("是".equals(cszb.getCsz())){
            result = dubboInvoiceService.voidInvoice(encryptStr);
        }else{
            String url = skServerUrl + "/invoice/voidInvoice";
            Map<String, String> map = new HashMap<>();
            map.put("p", encryptStr);
            result = HttpUtils.doPost(url, map);
        }
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
        Kpls kpls=kplsService.findOne(kplsh);
        Cszb cszb=cszbService.getSpbmbbh(kpls.getGsdm(),kpls.getXfid(),kpls.getSkpid(),"sfqysknew");
        String result=null;
        if("是".equals(cszb.getCsz())){
            result = dubboInvoiceService.reprintInvoice(encryptStr);
        }else{
            String url = skServerUrl + "/invoice/reprintInvoice";
            Map<String, String> map = new HashMap<>();
            map.put("p", encryptStr);
            result = HttpUtils.doPost(url, map);
        }
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
     *
     * 向税控socket服务发送数据
     * @param
     * @return
     */
    public String sendMessage(String  datajson) throws Exception{
        if (StringUtils.isBlank(skServerUrl)) {
            return "skServerUrl为空";
        }
        String encryptStr = encryptSkServerParameter(datajson + "");
        String url = skServerUrl + "/invoice/sendMessage";
        Map<String, String> map = new HashMap<>();
        map.put("p", encryptStr);
        String result = HttpUtils.doPost(url, map);
        return result;
    }



    public String register(int skpid) throws Exception {
        return dubboSkpService.deviceAuth(skpid);
    }
    public String inputUDiskPassword(int skpid) throws Exception {
        return dubboSkpService.inputUDiskPassword(skpid);
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
