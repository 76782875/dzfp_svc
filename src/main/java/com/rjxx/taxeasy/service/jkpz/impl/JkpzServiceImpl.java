package com.rjxx.taxeasy.service.jkpz.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.rjxx.taxeasy.bizcomm.utils.GetXmlUtil;
import com.rjxx.taxeasy.bizcomm.utils.HttpUtils;
import com.rjxx.taxeasy.dao.JkmbzbJpaDao;
import com.rjxx.taxeasy.dao.SkpJpaDao;
import com.rjxx.taxeasy.dao.XfJpaDao;
import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.dto.AdapterPost;
import com.rjxx.taxeasy.dto.AdapterPostRedData;
import com.rjxx.taxeasy.dto.AdapterRedData;
import com.rjxx.taxeasy.dto.AdapterRedInvoiceItem;
import com.rjxx.taxeasy.invoice.KpService;
import com.rjxx.taxeasy.invoice.Kphc;
import com.rjxx.taxeasy.service.*;
import com.rjxx.taxeasy.service.jkpz.JkpzService;
import com.rjxx.taxeasy.vo.JkpzVo;
import com.rjxx.utils.CheckOrderUtil;
import com.rjxx.utils.StringUtils;
import com.rjxx.utils.jkpz.JkpzUtil;
import com.rjxx.utils.yjapi.Result;
import com.rjxx.utils.yjapi.ResultUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
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
    private JkmbzbJpaDao jkmbzbJpaDao;

    private Logger logger = LoggerFactory.getLogger(JkpzServiceImpl.class);

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
                return ResultUtil.error("参数传入类型为空");
            }
            //发票开具、上传
            if(reqType.equals("01")||reqType.equals("02")){
                ObjectMapper mapper = new ObjectMapper();
                AdapterPost adapterPost=mapper.readValue(data, AdapterPost.class);
                if(adapterPost==null){
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
                Xf xf;
                String xfsh = adapterPost.getTaxNo();
                try {
                    if(StringUtils.isNotBlank(xfsh)){
                        xf = xfJpaDao.findOneByXfshAndGsdm(xfsh,gsdm);
                    }else{
                        xf=xfJpaDao.findOneByGsdm(gsdm);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return ResultUtil.error("获取销方信息有误");
                }
                //处理开票点
                String kpddm = adapterPost.getClientNo();
                Skp skp;
                try {
                    if(StringUtils.isNotBlank(kpddm)){
                        skp = skpJpaDao.findOneByKpddmAndGsdm(kpddm, gsdm);
                    }else{
                        skp = skpJpaDao.findOneByGsdmAndXfsh(gsdm, xf.getId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResultUtil.error("获取开票点信息有误");
                }
                Cszb cszb = cszbService.getSpbmbbh(gsdm, xf.getId(), skp.getId(), "jkpzmbid");
                if(cszb==null){
                    return ResultUtil.error("模板未配置");
                }
                Map map1 = new HashMap();
                map1.put("mbid",Integer.getInteger(cszb.getCsz()));
                //获取数据模板
                List<JkpzVo> jkmbzbList = jkmbzbService.findByMbId(map1);
                if(jkmbzbList.isEmpty()){
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
                    //提取码
                    if(adapterPost.getData().getOrder().getExtractedCode()!=null){
                        jyxxsq.setTqm(adapterPost.getData().getOrder().getExtractedCode());
                    }
                    //数据来源
                    if(adapterPost.getData().getDatasource()!=null){
                        jyxxsq.setSjly(adapterPost.getData().getDatasource());
                    }else {
                        jyxxsq.setSjly("1");
                    }
                    jyxxsq.setYkpjshj(0d);
                    jyxxsq.setGsdm(gsdm);
                    jyxxsq.setFpczlxdm("11");
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
                    return ResultUtil.error(msg);
                }
                //开票
                Map kpMap = new HashMap();
                kpMap.put("jyxxsqList",jyxxsqList);
                kpMap.put("jymxsqList",jymxsqList);
                kpMap.put("jyzfmxList",jyzfmxList);
                result = kpService.dealOrder(gsdm, kpMap, "01");
                return ResultUtil.success(result);
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
                    String hcResult = kpService.dealOrder(gsdm, HcMap, "04");
                    if(StringUtils.isNotBlank(hcResult)){
                        result += hcResult;
                    }
                }
                return ResultUtil.success(result);
            }else {
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
}
