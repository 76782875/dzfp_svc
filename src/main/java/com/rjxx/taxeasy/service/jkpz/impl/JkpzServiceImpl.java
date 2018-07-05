package com.rjxx.taxeasy.service.jkpz.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rjxx.taxeasy.bizcomm.utils.GetXmlUtil;
import com.rjxx.taxeasy.bizcomm.utils.HttpUtils;
import com.rjxx.taxeasy.dao.JkmbzbJpaDao;
import com.rjxx.taxeasy.dao.SkpJpaDao;
import com.rjxx.taxeasy.dao.XfJpaDao;
import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.dto.*;
import com.rjxx.taxeasy.invoice.DealOrder04;
import com.rjxx.taxeasy.invoice.DefaultResult;
import com.rjxx.taxeasy.invoice.KpService;
import com.rjxx.taxeasy.invoice.Kphc;
import com.rjxx.taxeasy.service.*;
import com.rjxx.taxeasy.service.jkpz.JkpzService;
import com.rjxx.taxeasy.vo.JkpzVo;
import com.rjxx.taxeasy.vo.OrderCancelVo;
import com.rjxx.utils.CheckOrderUtil;
import com.rjxx.utils.StringUtils;
import com.rjxx.utils.XmlJaxbUtils;
import com.rjxx.utils.jkpz.JkpzUtil;
import com.rjxx.utils.yjapi.Result;
import com.rjxx.utils.yjapi.ResultUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: zsq
 * @date: 2018/3/20 18:10
 * @describe:接口配置实现
 */
@Service
public class JkpzServiceImpl implements JkpzService {

    @Autowired
    private JkmbzbService jkmbzbService;

    @Autowired
    private CszbService cszbService;

    @Autowired
    private SkpJpaDao skpJpaDao;

    @Autowired
    private XfJpaDao xfJpaDao;

    @Autowired
    private JkpzUtil jkpzUtil;

    @Autowired
    private GsxxService gsxxService;

    @Autowired
    private CheckOrderUtil checkOrderUtil;

    @Autowired
    private KpService kpService;

    @Autowired
    private JyxxsqService jyxxsqService;

    @Autowired
    private KplsService kplsService;

    private static Logger logger = LoggerFactory.getLogger(JkpzServiceImpl.class);

    /**
     * 接口配置业务处理:发票开具、上传、红冲
     * @param data
     * @return
     */
    public Result jkpzInvoice(String data){
        try {
            String result="";
            JSONObject jsonObject = JSONObject.parseObject(data);
            String reqType = jsonObject.getString("reqType");
            if(StringUtils.isBlank(reqType)){
                logger.info("error={}","参数传入类型为空");
                return ResultUtil.error("参数传入类型为空");
            }
            //发票开具、上传
            if(reqType.equals("01")||reqType.equals("02")){

                ObjectMapper mapper = new ObjectMapper();
//                AdapterPost adapterPost=mapper.readValue(data, AdapterPost.class);
                AdapterPost adapterPost=JSON.parseObject(data,AdapterPost.class);
                if(adapterPost==null){
                    logger.info("error={}","参数错误");
                    return ResultUtil.error("参数错误");
                }
                Jyxxsq jyxxsq = new Jyxxsq();
                List<Jymxsq> jymxsqList = new ArrayList();
                List<Jyzfmx> jyzfmxList = new ArrayList();
                Map map = new HashMap();
                map.put("appkey",adapterPost.getAppId());
                Gsxx gsxx = gsxxService.findOneByParams(map);
                String gsdm = gsxx.getGsdm();
                //处理销方
                Xf xf=null;
                String xfsh = adapterPost.getTaxNo();
                try {
                    if(StringUtils.isNotBlank(xfsh)){
                        xf = xfJpaDao.findOneByXfshAndGsdm(xfsh,gsdm);
                        if(xf==null){
                            logger.info("error={}","根据销方税号，获取销方有误");
                            return ResultUtil.error("根据销方税号，获取销方有误");
                        }
                    }else{
                        xf=xfJpaDao.findOneByGsdm(gsdm);
                        if(xf==null){
                            logger.info("error={}","获取销方有误");
                            return ResultUtil.error("获取销方有误");
                        }
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    logger.info("error={}","获取销方信息有误");
                    return ResultUtil.error("获取销方信息有误");
                }
                //处理开票点
                String kpddm = adapterPost.getClientNo();
                Skp skp;
                try {
                    if(StringUtils.isNotBlank(kpddm)){
                        skp = skpJpaDao.findOneByKpddmAndGsdm(kpddm, gsdm);
                        if(skp==null){
                            logger.info("error={}","根据开票点代码，获取开票点有误");
                            return ResultUtil.error("根据开票点代码，获取开票点有误");
                        }
                    }else{
                        skp = skpJpaDao.findOneByGsdmAndXfsh(gsdm, xf.getId());
                        if(skp==null){
                            logger.info("error={}","获取开票点有误");
                            return ResultUtil.error("获取开票点有误");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.info("error={}","获取开票点信息有误");
                    return ResultUtil.error("获取开票点信息有误");
                }
                Cszb cszb = cszbService.getSpbmbbh(gsdm, xf.getId(), skp.getId(), "jkpzmbid");
                if(cszb==null || cszb.getCsz()==null || "".equals(cszb.getCsz())){
                    logger.info("error={}","模板未配置");
                    return ResultUtil.error("模板未配置");
                }
                Map map1 = new HashMap();
                map1.put("mbid",cszb.getCsz());
                logger.info(JSON.toJSONString(map1));
                //获取数据模板
                List<JkpzVo> jkmbzbList = jkmbzbService.findByMbId(map1);
                if(jkmbzbList.isEmpty()){
                    logger.info("error={}","模板设置有误");
                    return ResultUtil.error("模板设置有误");
                }
                if(adapterPost.getData().getOrder()!=null){
                    //加税合计
                    if(adapterPost.getData().getOrder().getTotalAmount()==null){
                        return ResultUtil.error("商品主信息金额有误");
                    }else {
                        jyxxsq.setJshj(adapterPost.getData().getOrder().getTotalAmount());
                    }
                    //全局折扣
                    if(adapterPost.getData().getOrder().getTotalDiscount()!=null){
                        jyxxsq.setQjzk(adapterPost.getData().getOrder().getTotalDiscount());
                    }else {
                        jyxxsq.setQjzk(0d);
                    }
                    if("fujifilm".equals(gsdm)){
                        //提取码
                        if(StringUtils.isNotBlank(adapterPost.getData().getOrder().getExtractedCode())){
                            jyxxsq.setTqm(adapterPost.getData().getOrder().getExtractedCode());
                        }else {
                            //没有值订单号
                            jyxxsq.setTqm(adapterPost.getData().getOrder().getOrderNo());
                        }
                    }else{
                        //提取码
                        if(StringUtils.isNotBlank(adapterPost.getData().getOrder().getExtractedCode())){
                            jyxxsq.setTqm(adapterPost.getData().getOrder().getExtractedCode());
                        }
                    }
                    //数据来源
                    if(adapterPost.getData().getDatasource()!=null){
                        jyxxsq.setSjly(adapterPost.getData().getDatasource());
                    }else {
                        jyxxsq.setSjly("1");
                    }
                    //openid
                    if(adapterPost.getData().getOpenid()!=null && adapterPost.getData().getOpenid()!=""){
                        jyxxsq.setOpenid(adapterPost.getData().getOpenid());
                    }
                    jyxxsq.setYkpjshj(0d);
                    jyxxsq.setGsdm(gsdm);
                    jyxxsq.setFpczlxdm("11");
                    jyxxsq.setClztdm("00");
                }
                //反射 封装数据
                for (JkpzVo jkpzVo : jkmbzbList) {
                    Map paraMap = new HashMap();
                    paraMap.put("gsxx", gsxx);
                    paraMap.put("xf", xf);
                    paraMap.put("skp", skp);
                    paraMap.put("jyxxsq", jyxxsq);
                    paraMap.put("jymxsqList", jymxsqList);
                    paraMap.put("jyzfmxList", jyzfmxList);
                    paraMap.put("adapterPost", adapterPost);
                    paraMap.put("jkpzVo",jkpzVo);
                    String execute = execute(jkpzVo.getCszff(), paraMap);
                    if(StringUtils.isNotBlank(execute)){
                        result +=execute;
                    }
                }
                if(StringUtils.isNotBlank(result)){
                    logger.info("error={}",result);
                    return ResultUtil.error(result);
                }
                //校验数据
                List<Jyxxsq> jyxxsqList = new ArrayList<>();
                Date date = new Date();
                jyxxsq.setLrsj(date);
                jyxxsq.setXgsj(date);
                jyxxsq.setLrry(1);
                jyxxsq.setXgry(1);
                jyxxsq.setYxbz("1");
                jyxxsqList.add(jyxxsq);
                if(jyzfmxList!=null&&!jyzfmxList.isEmpty()){
                    for (Jyzfmx jyzfmx : jyzfmxList) {
                        jyzfmx.setLrsj(date);
                        jyzfmx.setXgsj(date);
                        jyzfmx.setLrry(1);
                        jyzfmx.setXgry(1);
                    }
                }
                if(jymxsqList!=null &&!jymxsqList.isEmpty()){
                    for (Jymxsq jymxsq : jymxsqList) {
                        jymxsq.setLrry(1);
                        jymxsq.setXgry(1);
                        jymxsq.setLrsj(date);
                        jymxsq.setXgsj(date);
                        jymxsq.setYxbz("1");
                    }
                }
                String msg ="";
                if(adapterPost.getReqType().equals("02")){
                    msg = checkOrderUtil.checkOrders(jyxxsqList,jymxsqList,jyzfmxList,gsdm,"02");
                }
                if(adapterPost.getReqType().equals("01")){
                    msg = checkOrderUtil.checkAll(jyxxsqList,jymxsqList,jyzfmxList,gsdm,"01");
                }
                if(StringUtils.isNotBlank(msg)){
                    logger.info("msg={}",msg);
                    return ResultUtil.error(msg);
                }

                Map kpMap = new HashMap();
                kpMap.put("jyxxsqList",jyxxsqList);
                kpMap.put("jymxsqList",jymxsqList);
                kpMap.put("jyzfmxList",jyzfmxList);
                //01 开票，02 上传数据 不开票
                String kpresult = kpService.uploadOrderData(gsdm, kpMap,reqType);
                DefaultResult defaultResult = XmlJaxbUtils.convertXmlStrToObject(DefaultResult.class, kpresult);
                if(defaultResult.getReturnCode().equals("0000")){
                    logger.info("success={}",defaultResult.getReturnMessage());
                    return ResultUtil.success(defaultResult.getReturnMessage());
                }else {
                    logger.info("error={}",defaultResult.getReturnMessage());
                    return ResultUtil.error(defaultResult.getReturnMessage());
                }
            }
            //红冲
            if(reqType.equals("04")){
                String adapterRedData = jsonObject.getString("data");
                ObjectMapper mapper = new ObjectMapper();
                AdapterRedData adapterRedData1 = mapper.readValue(adapterRedData, AdapterRedData.class);
                String appId = jsonObject.getString("appId");
                Map map = new HashMap();
                map.put("appkey",appId);
                Gsxx gsxx = gsxxService.findOneByParams(map);
                String gsdm = gsxx.getGsdm();
                //发票明细
                List<AdapterRedInvoiceItem> list = adapterRedData1.getInvoiceItem();
                String invType = adapterRedData1.getInvType();
                String serialNumber = adapterRedData1.getSerialNumber();
//                String hcResult = "";
                for (AdapterRedInvoiceItem item : list) {
                    Kphc kphc= new Kphc();
                    kphc.setSerialNumber(serialNumber);//序列号
                    kphc.setTotalAmount(item.getTotalAmount());//加税合计
                    kphc.setCNDNCode(item.getCndnCode());//原发票代码
                    kphc.setCNDNNo(item.getCndnNo());//原发票号码
                    kphc.setInvType(invType);//发票种类
                    kphc.setCNNoticeNo(item.getCnnoticeNo());//专票红字通知单号
                    kphc.setServiceType("1");
                    if(invType.equals("01")){
                        if(StringUtils.isBlank(item.getCnnoticeNo())){
                            result ="发票种类为专票，红字通知单号不能为空！";
                            break;
                        }
                    }
                    //红冲
                    Map HcMap = new HashMap();
                    HcMap.put("Kphc",kphc);
                    String hcResult = kpService.uploadOrderData(gsdm, HcMap, reqType);
                    DefaultResult defaultResult = XmlJaxbUtils.convertXmlStrToObject(DefaultResult.class, hcResult);
                    if(!defaultResult.getReturnCode().equals("0000")){
                       result += defaultResult.getReturnMessage();
                    }
                }
                if(StringUtils.isNotBlank(result)){
                    logger.info("error={}",result);
                    return ResultUtil.error(result);
                }
                return ResultUtil.success(result);
            }else if(reqType.equals("03")){
                //订单退货处理
                String appId = jsonObject.getString("appId");
                Map map = new HashMap();
                map.put("appkey",appId);
                Gsxx gsxx = gsxxService.findOneByParams(map);
                String gsdm = gsxx.getGsdm();
                String adapterCancel = jsonObject.getString("data");
                ObjectMapper mapper = new ObjectMapper();
                CancelData cancelData = mapper.readValue(adapterCancel, CancelData.class);
                Map resultMap = dealCancel(cancelData,gsdm);
                String code = String.valueOf(resultMap.get("code"));
                // code为0表示无法进行退货；1表示可以进行退货，置退货状态；2表示可以进行订单红冲。
                if (code.equals("1")) {
                    List<Integer> sqlshList = new ArrayList<>();
                    sqlshList.add(Integer.valueOf(resultMap.get("sqlsh").toString()));
                    jyxxsqService.updateJyxxsqZtzt(sqlshList,"8");
                    return ResultUtil.success("退货成功！");
                }else if(code.equals("2")){
                    List<OrderCancelVo> orderCancelVoList = (List)resultMap.get("orderCancelVoList");
                    for (OrderCancelVo item : orderCancelVoList) {
                        Kphc kphc= new Kphc();
                        kphc.setSerialNumber("JY" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));//序列号
                        kphc.setTotalAmount(-item.getKpje());//加税合计
                        kphc.setCNDNCode(item.getFpdm());//原发票代码
                        kphc.setCNDNNo(item.getFphm());//原发票号码
                        kphc.setInvType("12");//发票种类
                        //kphc.setCNNoticeNo(item.getCnnoticeNo());//专票红字通知单号
                        kphc.setServiceType("1");
                        //红冲
                        Map HcMap = new HashMap();
                        HcMap.put("Kphc",kphc);
                        String hcResult = kpService.uploadOrderData(gsdm, HcMap, "04");
                        DefaultResult defaultResult = XmlJaxbUtils.convertXmlStrToObject(DefaultResult.class, hcResult);
                        if(!defaultResult.getReturnCode().equals("0000")){
                            result += defaultResult.getReturnMessage();
                        }else{
                            List<Integer> sqlshList = new ArrayList<>();
                            sqlshList.add(Integer.valueOf(resultMap.get("sqlsh").toString()));
                            jyxxsqService.updateJyxxsqZtzt(sqlshList,"9");
                            return ResultUtil.success("退货成功！");
                        }
                    }
                    if(!result.equals("")){
                        logger.info("error={}",result);
                        return ResultUtil.error(result);
                    }else{
                        logger.info("success={}",result);
                        return ResultUtil.success(result);
                    }
                }else{
                    logger.info("error={}",result);
                    result = String.valueOf(resultMap.get("resMsg"));
                    return ResultUtil.error(result);
                }
            }else{
                result ="不支持的请求类型";
                return ResultUtil.error(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("系统错误");
        }
//        return ResultUtil.success();
    }

    /**
     *  反射接口配置util
     *  返回成功null 失败string
     */
    public String execute(String methodName, Map map){
        String result ="";
        try {
            Method target = jkpzUtil.getClass().getDeclaredMethod(methodName,Map.class);
            result = (String) target.invoke(jkpzUtil,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     *  处理退货方法
     * @param cancelData
     * @return
     */
    private Map dealCancel(CancelData cancelData,String gsdm){
            // code为0表示无法进行退货；1表示可以进行退货，置退货状态；2表示可以进行订单红冲。
            String resMsg ="";
            Map resultMap = new HashMap();
            Map param = new HashMap();
            param.put("gsdm",gsdm);
            param.put("ddh",cancelData.getOderNo());
            List<OrderCancelVo> orderCancelVoList = jyxxsqService.findAllCancelVoByDdh(param);
            orderCancelVoList.remove(null);
            if(orderCancelVoList.isEmpty() ||  orderCancelVoList.size()>1){
                resMsg += "未查询到订单或者存在多笔订单无法进行退货操作！";
            }else{
                OrderCancelVo orderCancelVo = orderCancelVoList.get(0);
                if(orderCancelVo.getDdje()!=cancelData.getTotalAmount()){
                    resMsg += "退货订单金额和系统订单金额不一致！";
                }else{
                    if(orderCancelVo.getMinZtbz().equals(orderCancelVo.getMaxZtbz()) && orderCancelVo.getMinZtbz().equals("6")){
                        resultMap.put("code","1");
                        resultMap.put("resMsg","");
                        resultMap.put("sqlsh",orderCancelVo.getSqlsh());
                        return resultMap;
                    }else if(!orderCancelVo.getMinZtbz().equals("6")){
                        Map param2 = new HashMap();
                        param2.put("sqlsh",orderCancelVo.getSqlsh());
                        param2.put("gsdm",gsdm);
                        List<OrderCancelVo> orderCancelVoList1 = kplsService.findAllFpBySqlsh(param2);
                        if(!orderCancelVoList1.isEmpty()){
                            double sumJe = 0d;
                            for (int i=0;i<orderCancelVoList1.size();i++){
                                OrderCancelVo orderCancelVo1 = orderCancelVoList1.get(i);
                                sumJe = sumJe+orderCancelVo1.getKpje();
                            }
                            if(orderCancelVo.getDdje()!= sumJe){
                                resMsg += "该订单所生成发票未全部开具，无法进行退货红冲操作！";
                            }else{
                                resultMap.put("code","2");
                                resultMap.put("resMsg","");
                                resultMap.put("sqlsh",orderCancelVo.getSqlsh());
                                resultMap.put("orderCancelVoList",orderCancelVoList1);
                                return resultMap;
                            }
                        }
                    }else{
                        resMsg += "订单存在问题，无法进行退货！";
                    }
                }
            }
        resultMap.put("code","0");
        resultMap.put("resMsg",resMsg);
        return resultMap;
    }
}
