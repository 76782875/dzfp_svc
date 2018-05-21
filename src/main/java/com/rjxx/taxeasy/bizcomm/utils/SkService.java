package com.rjxx.taxeasy.bizcomm.utils;

import com.alibaba.dubbo.config.annotation.Reference;

import com.rjxx.taxeasy.domains.Cszb;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.domains.Skp;
import com.rjxx.taxeasy.dubbo.business.ims.service.DubboSkpService;
import com.rjxx.taxeasy.dubbo.business.tcs.service.DubboInvoiceService;
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

    @Reference(version = "1.0.0",group = "tcs",timeout = 120000,retries = -2)
    public DubboInvoiceService dubboInvoiceService;

    @Reference(version = "1.0.0",group = "ims",timeout = 120000,retries = -2)
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
        }
        return response;
    }
    /**
     * 凯盈盒子开票
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
     * 凯盈盒子查询开票信息
     *
     * @param kplsh
     * @return
     */
    public String skInvoiceQuery(int kplsh) throws Exception {

        InvoiceResponse response=null;
        String result=null;
        try{
            if (StringUtils.isBlank(skkpServerUrl)) {
                return "skkpServerUrl为空";
            }
            String encryptStr = encryptSkServerParameter(kplsh + "");
            Kpls kpls=kplsService.findOne(kplsh);
            Cszb cszb=cszbService.getSpbmbbh(kpls.getGsdm(),kpls.getXfid(),kpls.getSkpid(),"sfqysknew");
            if("是".equals(cszb.getCsz())){
                result=dubboInvoiceService.skInvoiceQuery(encryptStr);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("------返回数据--------"+result);
        }
        return result;
    }
    /**
     * 凯盈盒子作废发票
     *
     * @param kplsh
     * @return
     */
    public String InvalidateInvoice(int kplsh) throws Exception {

        InvoiceResponse response=null;
        String result=null;
        try{
            if (StringUtils.isBlank(skkpServerUrl)) {
                return "skkpServerUrl为空";
            }
            String encryptStr = encryptSkServerParameter(kplsh + "");
            Kpls kpls=kplsService.findOne(kplsh);
            Cszb cszb=cszbService.getSpbmbbh(kpls.getGsdm(),kpls.getXfid(),kpls.getSkpid(),"sfqysknew");
            if("是".equals(cszb.getCsz())){
                result=dubboInvoiceService.InvalidateInvoice(encryptStr);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("------返回数据--------"+result);
        }
        return result;
    }

    /**
     * 凯盈获取当前发票段信息
     * @param kplsh
     * @return
     */
    public String GetCurrentInvoiceInfo(int kplsh) throws Exception {

        InvoiceResponse response=null;
        String result=null;
        try{
            if (StringUtils.isBlank(skkpServerUrl)) {
                return "skkpServerUrl为空";
            }
            String encryptStr = encryptSkServerParameter(kplsh + "");
            Kpls kpls=kplsService.findOne(kplsh);
            Cszb cszb=cszbService.getSpbmbbh(kpls.getGsdm(),kpls.getXfid(),kpls.getSkpid(),"sfqysknew");
            if("是".equals(cszb.getCsz())){
                result=dubboInvoiceService.GetCurrentInvoiceInfo(encryptStr);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("------返回数据--------"+result);
        }
        return result;
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
     * 盟度开票
     * @param kplsh
     * @param
     * @return
     */
    public void skEkyunKP(int kplsh) throws Exception {
        String encryptStr = encryptSkServerParameter(kplsh + "");
        Kpls kpls=kplsService.findOne(kplsh);
        Cszb cszb=cszbService.getSpbmbbh(kpls.getGsdm(),kpls.getXfid(),kpls.getSkpid(),"sfqysknew");
        String result=null;
        if("是".equals(cszb.getCsz())){
            dubboInvoiceService.skEkyunKP(encryptStr);
        }
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


    /**
     * 凯盈终端盒子注册
     * @param skpid
     * @return
     * @throws Exception
     */
    public String register(int skpid) throws Exception {
        return dubboSkpService.deviceAuth(skpid);
    }

    /**
     * 凯盈终端盒子初始化税控盘密码，证书
     * @param skpid
     * @return
     * @throws Exception
     */
    public String inputUDiskPassword(int skpid) throws Exception {
        return dubboSkpService.inputUDiskPassword(skpid);
    }

    /**
     * 获取凯盈盒子在线状态
     * @param skpid
     * @return
     * @throws Exception
     */
    public String deviceState(int skpid) throws Exception {
        return dubboSkpService.deviceState(skpid);
    }

    /**
     * 凯盈查询发票上传状态
     * @param skpid
     * @return
     * @throws Exception
     */
    public String GetUploadStates(int skpid) throws Exception {
        return dubboSkpService.GetUploadStates(skpid);
    }

    /**
     * 凯盈立即上传发票
     * @param skpid
     * @return
     * @throws Exception
     */
    public String TriggerUpload(int skpid) throws Exception {
        return dubboSkpService.TriggerUpload(skpid);
    }

    /**
     * 凯盈抄报状态查询
     * @param skpid
     * @return
     * @throws Exception
     */
    public String GetDeclareTaxStates(int skpid) throws Exception {
        return dubboSkpService.GetDeclareTaxStates(skpid);
    }

    /**
     * 立即抄报
     * @param skpid
     * @return
     * @throws Exception
     */
    public String TriggerDeclareTax(int skpid) throws Exception {
        return dubboSkpService.TriggerDeclareTax(skpid);
    }

    /**
     * 获取税控装置信息
     * @param skpid
     * @return
     * @throws Exception
     */
    public String UDiskInfo(int skpid) throws Exception {
        return dubboSkpService.UDiskInfo(skpid);
    }

    /**
     * 获取当前税控装置内发票的监控管理信息
     * @param skpid
     * @return
     * @throws Exception
     */
    public String InvoiceControlInfo(int skpid) throws Exception {
        return dubboSkpService.InvoiceControlInfo(skpid);
    }

    /**
     * 获取指定税控装置（当前已载入的）内指定票种的全部发票段信息
     * @param skpid
     * @return
     * @throws Exception
     */
    public String GetAllInvoiceSections(int skpid) throws Exception {
        return dubboSkpService.GetAllInvoiceSections(skpid);
    }

    /**
     * 在本地已载入税控盘/金税盘与对应的报税盘之间分发、回收发票，
     * 或从局端下载、退回发票到本地 盘，或从本地报税盘分发至远程税控盘
     * @param skpMap
     * @return
     * @throws Exception
     */
    public String InvoiceDistribute(Map skpMap) throws Exception {
        return dubboSkpService.InvoiceDistribute(skpMap);
    }

    /**
     * 。本指令可用于清空绑定列表，使终端可以绑定新的税控装置或纳税人。
     * @param skpid
     * @return
     * @throws Exception
     */
    public String UDiskBinding(int skpid) throws Exception {
        return dubboSkpService.UDiskBinding(skpid);
    }

    /**
     * 切换至终端连接的另一个税控装置（不包括报税盘）并重新初始化
     * @param skpid
     * @return
     * @throws Exception
     */
    public String SwitchUDisk(int skpid) throws Exception {
        return dubboSkpService.SwitchUDisk(skpid);
    }

    /**
     * 获取设备信息。
     * @param skpid
     * @return
     * @throws Exception
     */
    public String DeviceInfo(int skpid) throws Exception {
        return dubboSkpService.DeviceInfo(skpid);
    }

    /**
     * 将终端恢复出厂设置。
     * @param skpid
     * @return
     * @throws Exception
     */
    public String FactoryReset(int skpid) throws Exception {
        return dubboSkpService.FactoryReset(skpid);
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
