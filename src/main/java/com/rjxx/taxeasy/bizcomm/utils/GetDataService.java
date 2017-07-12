package com.rjxx.taxeasy.bizcomm.utils;

import com.alibaba.fastjson.JSON;
import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.service.*;
import com.rjxx.taxeasy.vo.Spvo;
import org.apache.axiom.om.OMElement;
import org.apache.commons.codec.digest.DigestUtils;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by xlm on 2017/7/3.
 * 获取全家，绿地优鲜接口提供的数据
 */
@Service
public class GetDataService {
    @Autowired
    private YhService yhService;
    @Autowired
    private SkpService skpService;
    @Autowired
    private XfService xfService;
    @Autowired
    private JyxxsqService jyxxsqService;
    @Autowired
    private ZffsService zffsService;
    @Autowired
    private SpvoService spvoService;
    @Autowired
    private GsxxService gsxxService;
    private static String getSign(String QueryData, String key) {
        String signSourceData = "data=" + QueryData + "&key=" + key;
        String newSign = DigestUtils.md5Hex(signSourceData);
        return newSign;
    }
    public Map getData(String ExtractCode,String gsdm){
            Map parmsMap=new HashMap();
            String strMessage = "";
            BufferedReader reader = null;
            StringBuffer buffer = new StringBuffer();
            Map parms=new HashMap();
            parms.put("gsdm",gsdm);
            Gsxx gsxx=gsxxService.findOneByParams(parms);
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
                String ExtractCodeXml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<Request>\n" +
                        "<ExtractCode>"+ExtractCode+"</ExtractCode>\n" +
                        "</Request>\n";
                String Secret=getSign(ExtractCodeXml,gsxx.getSecretKey());
                nvps.put("ExtractCode", ExtractCodeXml);
                nvps.put("Secret", Secret);
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
                String kpddm=ExtractCode.substring(4,10);
                Map params = new HashMap();
                params.put("kpddm", kpddm);
                params.put("gsdm", gsdm);
                Skp skp = skpService.findOneByParams(params);
                Xf xf=xfService.findOne(skp.getXfid());
                parmsMap=interpreting(xf,skp,gsdm,buffer.toString());
                List<Jyxxsq> jyxxsqList = (List) parmsMap.get("jyxxsqList");
                List<Jymxsq> jymxsqList = (List) parmsMap.get("jymxsqList");
                List<Jyzfmx> jyzfmxList = (List) parmsMap.get("jyzfmxList");

                String tmp = this.checkAll(jyxxsqList, jymxsqList, jyzfmxList,gsdm);
                parmsMap.put("tmp",tmp);
            }catch (Exception e){
                System.out.println("request url=" + "" + ", exception, msg=" + e.getMessage());
                e.printStackTrace();
                e.printStackTrace();
            }
            return parmsMap;
    }
    public String checkAll(List<Jyxxsq> jyxxsqList, List<Jymxsq> jymxsqList, List<Jyzfmx> jyzfmxList, String gsdm) {
        String result = "";
        String ddh = "";
        String ddh2 = "";
        List tqmList = new ArrayList();
        List jylshList = new ArrayList();
        Map tqmMap = new HashMap();
        Map jylshMap = new HashMap();
        Jyxxsq jyxxsq = new Jyxxsq();
        Jymxsq jymxsq = new Jymxsq();
        Jyzfmx jyzfmx = new Jyzfmx();

        for (int i = 0; i < jyxxsqList.size(); i++) {
            BigDecimal ajshj;
            BigDecimal jshj = new BigDecimal("0");
            BigDecimal jshj2 = new BigDecimal("0");
            for (int j = 0; j < jymxsqList.size(); j++) {
                jymxsq = (Jymxsq) jymxsqList.get(j);
                ddh = jymxsq.getDdh();
                if (jyxxsqList.get(i).getDdh().equals(jymxsq.getDdh())) {
                    jyxxsq = jyxxsqList.get(i);
                    if (ddh != null && !ddh.equals("")) {
                        if (ddh.length() > 20) {
                            result += "明细数据" + ddh + ":订单号太长;";
                        }
                    } else {
                        result += "明细数据订单号不能为空;";
                    }
                    String ProductCode = (String) jymxsq.getSpdm();
                    if (ProductCode == null) {
                        result += "订单号为" + ddh + "的订单ProductCode为空";
                    } else if (ProductCode.length() != 19) {
                        result += "订单号为" + ddh + "的订单ProductCode不等于19位;";
                    }
                    // 商品名称
                    String ProductName = (String) jymxsq.getSpmc();
                    if (ProductName == null) {
                        result += "订单号为" + ddh + "的订单ProductName为空！";
                    } else if (ProductName.length() > 50) {
                        result += "订单号为" + ddh + "的订单ProductName太长！";
                    }
                    // 发票行性质
                    String RowType = (String) jymxsq.getFphxz();
                    if (RowType == null) {
                        result += "订单号为" + ddh + "的订单RowType为空;";
                    } else if (!("0".equals(RowType) || "1".equals(RowType) || "2".equals(RowType))) {
                        result += "订单号为" + ddh + "的订单RowType只能填写0，1或2;";
                    }


                    // 商品金额
                    String Amount = String.valueOf(jymxsq.getSpje());
                    if (Amount == null) {
                        result += "订单号为" + ddh + "的订单商品Amount为空;";
                    } else if (!Amount.matches("^\\-?[0-9]{0,15}+(.[0-9]{0,2})?$")) {
                        result += "订单号为" + ddh + "的订单Amount格式不正确！";
                    }
                    // 商品税率
                    String TaxRate = String.valueOf(jymxsq.getSpsl());
                    if (TaxRate == null) {
                        result = "订单号为" + ddh + "的订单TaxRate为空;";
                    } else {
                        double taxRate = Double.valueOf(TaxRate);
                        if (!(taxRate == 0 || taxRate == 0.03 || taxRate == 0.04
                                || taxRate == 0.06 || taxRate == 0.11 || taxRate == 0.13
                                || taxRate == 0.17)) {
                            result += "订单号为" + ddh + "的订单TaxRate格式有误;";
                        }
                    }
                    if((jymxsq.getSpdj()==null&&jymxsq.getSps()!=null)||(jymxsq.getSps()==null&&jymxsq.getSpdj()!=null)){
                        result += "订单号为" + ddh + "的订单第" + i+1+ "行商品单价，商品数量必须全部为空或者全部不为空！";
                    }
                    if (jymxsq.getSpdj() != null && jymxsq.getSps() != null && jymxsq.getSpje() != null) {
                        double res = jymxsq.getSpdj() * jymxsq.getSps();
                        BigDecimal big1 = new BigDecimal(res);
                        big1 = big1.setScale(2, BigDecimal.ROUND_HALF_UP);
                        BigDecimal big2 = new BigDecimal(jymxsq.getSpje());
                        big2 = big2.setScale(2, BigDecimal.ROUND_HALF_UP);
                        if (big1.compareTo(big2) != 0) {
                            result += "订单号为" + ddh + "的订单第" + i+1+ "行商品单价，商品数量，商品金额之间的计算校验不通过，请检查！";
                        }
                    }
                    if(jymxsq.getSpdj() != null && jymxsq.getSps() != null && jymxsq.getSpje() != null){
                        double spdj = jymxsq.getSpdj();
                        double sps = jymxsq.getSps();
                        BigDecimal big1 = new BigDecimal(spdj);
                        BigDecimal big2 = new BigDecimal(sps);
                        if((big1.compareTo(new BigDecimal(0))==0)||(big2.compareTo(new BigDecimal(0))==0)){
                            result += "订单号为" + ddh + "的订单第" + i+1+ "行商品单价或商品数量不能为零！";
                        }
                    }
                    // 商品税额
                    String TaxAmount = String.valueOf(jymxsq.getSpse());
                    if (TaxAmount != null && TaxAmount.equals("^\\-?[0-9]{0,15}+(.[0-9]{0,2})?$")) {
                        result += "订单号为" + ddh + "的订单第" + i +1+ "条商品TaxAmount格式不正确！";
                    }

                    // 校验金额误差
                    String TaxMark = jyxxsq.getHsbz();
                    double je = Double.valueOf(Amount);
                    double se = 0;
                    //含税时，忽略税额
                    if (TaxMark.equals("0")) {
                        if (TaxAmount != null && !"".equals(TaxAmount)) {
                            se = Double.valueOf(TaxAmount);
                        }
                    }
                    double sl = Double.valueOf(TaxRate);
                    if (TaxMark.equals("0") && je * sl - se >= 0.0625) {
                        result += "订单号为" + ddh + "的订单(Amount，TaxRate，TaxAmount)之间的校验不通过";
                    }

                    BigDecimal bd = new BigDecimal(je);
                    BigDecimal bd1 = new BigDecimal(se);
                    ajshj = bd.add(bd1);
                    jshj = jshj.add(ajshj);
                    String ChargeTaxWay = jyxxsq.getZsfs();
                    String DeductAmount = String.valueOf(jymxsq.getKce());
                    if (ChargeTaxWay.equals("2") && (null == DeductAmount || DeductAmount.equals(""))) {
                        result += "订单号为" + ddh + "的订单DeductAmount不能为空";
                    }
                }
            }
            // 价税合计
            Double  TotalAmount = jyxxsq.getJshj();
            if (TotalAmount == null) {
                result += ddh + ":价税合计为空;";
            }
            // 订单号
            ddh = jyxxsq.getDdh();
            if (ddh != null && !ddh.equals("")) {
                if (ddh.length() > 100) {
                    result += "交易数据" + ddh + ":订单号太长;";
                }
            } else {
                result += "交易数据订单号不能为空;";
            }
            // 订单时间
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            if (null != jyxxsq.getDdrq() && !jyxxsq.getDdrq().equals("")) {
                String OrderDate = sim.format(jyxxsq.getDdrq());
                Pattern p = Pattern.compile(
                        "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
                if (OrderDate != null && !p.matcher(OrderDate).matches()) {
                    result += ddh + ":订单时间格式不正确;";
                }
            }
            BigDecimal bd2 = new BigDecimal(jyxxsq.getJshj());
            if (bd2.setScale(2, BigDecimal.ROUND_HALF_UP).subtract(jshj.setScale(2, BigDecimal.ROUND_HALF_UP)).doubleValue() != 0.0) {
                result += "订单号为" + ddh + "的订单TotalAmount，Amount，TaxAmount计算校验不通过";
            }
            // 提取码校验
            String tqm = jyxxsq.getTqm();
            if (null !=tqm && !tqm.equals("")) {
                tqmList.add(tqm);
            }
            // 交易流水号校验
            String jylsh = jyxxsq.getJylsh();
            jylshList.add(jylsh);
            // 一次性校验提取码和交易流水号是否重复上传，每笔交易流水号必须唯一，提取码也唯一,订单号也必须唯一
            if (null != tqmList && !tqmList.isEmpty()) {
                tqmMap.put("tqmList", tqmList);
                tqmMap.put("gsdm", gsdm);
                List<Jyxxsq> t1 = jyxxsqService.findAllByTqms(tqmMap);
                if (null != t1 && !t1.isEmpty()) {
                    for (int a = 0; a < t1.size(); a++) {
                        Jyxxsq jy1 = (Jyxxsq) t1.get(a);
                        result += "提取码" + jy1.getTqm() + "已存在;";
                    }
                }
            }
            jylshMap.put("jylshList", jylshList);
            jylshMap.put("gsdm", gsdm);
            List<Jyxxsq> t2 = jyxxsqService.findAllByJylshs(jylshMap);
            if (null != t2 && !t2.isEmpty()) {
                for (int b = 0; b < t2.size(); b++) {
                    Jyxxsq jy2 = (Jyxxsq) t2.get(b);
                    result += "交易流水号" + jy2.getJylsh() + "已存在;";
                }
            }
            if (null != jyzfmxList && !jyzfmxList.isEmpty()) {
                List kpfsList = new ArrayList();
                Map params = new HashMap();
                params.put("gsdm", gsdm);
                params.put("kpfsList", kpfsList);
                List<Zffs> zffsList = zffsService.findAllByParams(params);
                if(null == zffsList ||zffsList.isEmpty()){
                    result += "请去平台支付方式管理维护对应的支付方式;";
                }
                String flag ="0";
                for (int j = 0; j < jyzfmxList.size(); j++) {
                    jyzfmx = (Jyzfmx) jyzfmxList.get(j);
                    if(null != zffsList && !zffsList.isEmpty()){
                        for(int k=0;k<zffsList.size();k++){
                            Zffs  zffs = zffsList.get(k);
                            if(jyzfmx.getZffsDm().equals(zffs.getZffsDm())){
                                flag = "1";
                            }
                        }
                        if(flag.equals("0")){
                            result += "订单号为" + ddh + "的订单,支付方式代码"+jyzfmx.getZffsDm()+"未在平台维护;";
                        }
                    }
                    ddh2 = jyzfmx.getDdh();
                    if (ddh.equals(ddh2)) {
                        BigDecimal zfje = new BigDecimal(jyzfmx.getZfje());
                        jshj2 = jshj2.add(zfje);
                    }
                }
                if (jshj2.compareTo(bd2) !=0) {
                    result += "订单号为" + ddh + "的订单PayPrice合计与TotalAmount不等;";
                }
            }
        }
        return result;
    }
    /**
     * 解析数据
     * @param xf
     * @param skp
     * @param gsdm
     * @param data
     * @return
     * @throws Exception
     */
    public Map interpreting(Xf xf,Skp skp,String gsdm,String data)throws Exception {
        Map params1 = new HashMap();
        params1.put("gsdm", gsdm);
        Yh yh = yhService.findOneByParams(params1);
        int lrry = yh.getId();
        List<Jyxxsq> jyxxsqList = new ArrayList();
        List<Jymxsq> jymxsqList = new ArrayList();
        List<Jyzfmx> jyzfmxList = new ArrayList<Jyzfmx>();
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
        String ReturnCode=rootMap.get("ReturnCode").toString();
        String ReturnMessage=rootMap.get("ReturnMessage").toString();
        Element ReturnData  = (Element) xmlDoc.selectSingleNode("Responese/ReturnData");
        // 提取码
        String ExtractCode = "";
        if (null != ReturnData.selectSingleNode("ExtractCode")
                && !ReturnData.selectSingleNode("ExtractCode").equals("")) {
            ExtractCode = ReturnData.selectSingleNode("ExtractCode").getText();
        }
        // 会员号
        String MemberID = "";
        if (null != ReturnData.selectSingleNode("MemberID")
                && !ReturnData.selectSingleNode("MemberID").equals("")) {
            MemberID = ReturnData.selectSingleNode("MemberID").getText();
        }
        // 发票种类
        String InvType = "";
        if (null != ReturnData.selectSingleNode("InvType")
                && !ReturnData.selectSingleNode("InvType").equals("")) {
            InvType = ReturnData.selectSingleNode("InvType").getText();
        }
        // 商品编码版本号
        String Spbmbbh = "";
        if (null != ReturnData.selectSingleNode("Spbmbbh")
                && !ReturnData.selectSingleNode("Spbmbbh").equals("")) {
            Spbmbbh = ReturnData.selectSingleNode("Spbmbbh").getText();
        }
        // 门店号
        String StoreNo = "";
        if (null != ReturnData.selectSingleNode("StoreNo")
                && !ReturnData.selectSingleNode("StoreNo").equals("")) {
            StoreNo = ReturnData.selectSingleNode("StoreNo").getText();
        }
        Element Seller  = (Element) xmlDoc.selectSingleNode("Responese/ReturnData/Seller");
        // 销方税号
        String Identifier = "";
        if (null != ReturnData.selectSingleNode("Identifier")
                && !ReturnData.selectSingleNode("Identifier").equals("")) {
            Identifier = ReturnData.selectSingleNode("Identifier").getText();
        }
        // 销方名称
        String Name = "";
        if (null != ReturnData.selectSingleNode("Name")
                && !ReturnData.selectSingleNode("Name").equals("")) {
            Name = ReturnData.selectSingleNode("Name").getText();
        }
        // 销方地址
        String Address = "";
        if (null != ReturnData.selectSingleNode("Address")
                && !ReturnData.selectSingleNode("Address").equals("")) {
            Address = ReturnData.selectSingleNode("Address").getText();
        }
        // 销方电话
        String TelephoneNo = "";
        if (null != ReturnData.selectSingleNode("TelephoneNo")
                && !ReturnData.selectSingleNode("TelephoneNo").equals("")) {
            TelephoneNo = ReturnData.selectSingleNode("TelephoneNo").getText();
        }
        // 销方银行
        String Bank = "";
        if (null != ReturnData.selectSingleNode("Bank")
                && !ReturnData.selectSingleNode("Bank").equals("")) {
            Bank = ReturnData.selectSingleNode("Bank").getText();
        }
        // 销方银行账号
        String BankAcc = "";
        if (null != ReturnData.selectSingleNode("BankAcc")
                && !ReturnData.selectSingleNode("BankAcc").equals("")) {
            BankAcc = ReturnData.selectSingleNode("BankAcc").getText();
        }
        List<Element> xnList = xmlDoc.selectNodes("Responese/ReturnData/Orders");
        if (null != xnList && xnList.size() > 0) {
            for (Element xn : xnList) {
                Jyxxsq jyxxsq = new Jyxxsq();
                Element orderMainMap = (Element) xn.selectSingleNode("OrderMain");
                // 订单号
                String orderNo = "";
                if (null != orderMainMap.selectSingleNode("OrderNo")
                        && !orderMainMap.selectSingleNode("OrderNo").equals("")) {
                    orderNo = orderMainMap.selectSingleNode("OrderNo").getText();
                }
                // 订单时间
                String orderDate = "";
                if (null != orderMainMap.selectSingleNode("OrderDate")
                        && !orderMainMap.selectSingleNode("OrderDate").equals("")) {
                    orderDate = orderMainMap.selectSingleNode("OrderDate").getText();
                }
                // 征税方式
                String chargeTaxWay = "";
                if (null != orderMainMap.selectSingleNode("ChargeTaxWay")
                        && !orderMainMap.selectSingleNode("ChargeTaxWay").equals("")) {
                    chargeTaxWay = orderMainMap.selectSingleNode("ChargeTaxWay").getText();
                }
                // 价税合计
                String totalAmount = "";
                if (null != orderMainMap.selectSingleNode("TotalAmount")
                        && !orderMainMap.selectSingleNode("TotalAmount").equals("")) {
                    totalAmount = orderMainMap.selectSingleNode("TotalAmount").getText();
                }
                // 含税标志
                String taxMark = "";
                if (null != orderMainMap.selectSingleNode("TaxMark")
                        && !orderMainMap.selectSingleNode("TaxMark").equals("")) {
                    taxMark = orderMainMap.selectSingleNode("TaxMark").getText();
                }

                // 备注
                String remark = "";
                if (null != orderMainMap.selectSingleNode("Remark")
                        && !orderMainMap.selectSingleNode("Remark").equals("")) {
                    remark = orderMainMap.selectSingleNode("Remark").getText();
                }
                Jyxxsq jxxxsq=new Jyxxsq();
                jxxxsq.setTqm(ExtractCode);
                jxxxsq.setDdh(orderNo);
                SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    jyxxsq.setDdrq(orderDate == null ? new Date() : sim.parse(orderDate));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                jyxxsq.setXfid(skp.getXfid());
                jyxxsq.setJylsh("JY" + new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date()));
                jyxxsq.setSkpid(skp.getId());
                jyxxsq.setKpddm(skp.getKpddm());
                jyxxsq.setJshj(Double.valueOf(totalAmount));
                jyxxsq.setHsbz(taxMark);
                jyxxsq.setBz(remark);
                jyxxsq.setFpzldm(InvType);
                jyxxsq.setZsfs(chargeTaxWay);
                jyxxsq.setKpr(xf.getKpr());
                jyxxsq.setSkr(xf.getSkr());
                jyxxsq.setFhr(xf.getFhr());
                jyxxsq.setXfsh(xf.getXfsh());
                jyxxsq.setXfmc(xf.getXfmc());
                jyxxsq.setXfdz(xf.getXfdz());
                jyxxsq.setXfdh(xf.getXfdh());
                jyxxsq.setXfyh(xf.getXfyh());
                jyxxsq.setXfyhzh(xf.getXfyhzh());
                jyxxsq.setYkpjshj(Double.valueOf("0.00"));
                jyxxsq.setYxbz("1");
                jyxxsq.setLrsj(new Date());
                jyxxsq.setLrry(lrry);
                jyxxsq.setXgry(lrry);
                jyxxsq.setFpczlxdm("11");
                jyxxsq.setXgsj(new Date());
                jyxxsq.setGsdm(gsdm);
                jyxxsq.setSjly("1");
                jyxxsq.setClztdm("00");
                jyxxsqList.add(jyxxsq);
                Element OrderDetails = (Element) xn.selectSingleNode("OrderDetails");
                List<Element> orderDetailsList = (List<Element>) OrderDetails.elements("ProductItem");
                if (null != orderDetailsList && orderDetailsList.size() > 0) {
                    int spmxxh = 0;
                    for (Element orderDetails : orderDetailsList) {
                        Jymxsq jymxsq = new Jymxsq();
                        // Map ProductItem = (Map) orderDetailsList.get(j);
                        spmxxh++;
                        // 商品编码
                        String ProductCode = "";
                        if (null != orderDetails.selectSingleNode("ProductCode")
                                && !orderDetails.selectSingleNode("ProductCode").equals("")) {
                            ProductCode = orderDetails.selectSingleNode("ProductCode").getText();
                        }

                        jymxsq.setSpdm(ProductCode);
                        // 商品名称
                        String ProductName = "";
                        if (null != orderDetails.selectSingleNode("ProductName")
                                && !orderDetails.selectSingleNode("ProductName").equals("")) {
                            ProductName = orderDetails.selectSingleNode("ProductName").getText();
                        }

                        jymxsq.setSpmc(ProductName);
                        jymxsq.setDdh(jyxxsq.getDdh());
                        jymxsq.setHsbz(jyxxsq.getHsbz());
                        // 发票行性质
                        String RowType = "";
                        if (null != orderDetails.selectSingleNode("RowType")
                                && !orderDetails.selectSingleNode("RowType").equals("")) {
                            RowType = orderDetails.selectSingleNode("RowType").getText();
                        }

                        jymxsq.setFphxz(RowType);
                        // 商品规格型号
                        String Spec = "";
                        if (null != orderDetails.selectSingleNode("Spec")
                                && !orderDetails.selectSingleNode("Spec").equals("")) {
                            Spec = orderDetails.selectSingleNode("Spec").getText();
                        }

                        jymxsq.setSpggxh(Spec);
                        // 商品单位
                        String Unit = "";
                        if (null != orderDetails.selectSingleNode("Unit")
                                && !orderDetails.selectSingleNode("Unit").equals("")) {
                            Unit = orderDetails.selectSingleNode("Unit").getText();
                        }

                        jymxsq.setSpdw(Unit);
                        // 商品数量
                        String Quantity = "";
                        if (null != orderDetails.selectSingleNode("Quantity")
                                && !orderDetails.selectSingleNode("Quantity").equals("")) {
                            Quantity = orderDetails.selectSingleNode("Quantity").getText();
                            jymxsq.setSps(Double.valueOf(Quantity));
                        }

                        // 商品单价
                        String UnitPrice = "";
                        if (null != orderDetails.selectSingleNode("UnitPrice")
                                && !orderDetails.selectSingleNode("UnitPrice").equals("")) {
                            UnitPrice = orderDetails.selectSingleNode("UnitPrice").getText();
                            jymxsq.setSpdj(Double.valueOf(UnitPrice));
                        }

                        // 商品金额
                        String Amount = "";
                        if (null != orderDetails.selectSingleNode("Amount")
                                && !orderDetails.selectSingleNode("Amount").equals("")) {
                            Amount = orderDetails.selectSingleNode("Amount").getText();
                            jymxsq.setSpje(Double.valueOf(Amount));
                        }

                        // 扣除金额
                        String DeductAmount = "";
                        if (null != orderDetails.selectSingleNode("DeductAmount")
                                && !orderDetails.selectSingleNode("DeductAmount").equals("")) {
                            DeductAmount = orderDetails.selectSingleNode("DeductAmount").getText();
                            jymxsq.setKce((null == DeductAmount || DeductAmount.equals("")) ? Double.valueOf("0.00")
                                    : Double.valueOf(DeductAmount));
                        }
                        //商品税率
                        String TaxRate = "";
                        if (null != orderDetails.selectSingleNode("TaxRate")
                                && !orderDetails.selectSingleNode("TaxRate").equals("")) {
                            TaxRate = orderDetails.selectSingleNode("TaxRate").getText();
                            jymxsq.setSpsl(Double.valueOf(TaxRate));
                        }
                        //商品税额
                        String TaxAmount = "";
                        if (null != orderDetails.selectSingleNode("TaxAmount")
                                && !orderDetails.selectSingleNode("TaxAmount").equals("")) {
                            TaxAmount = orderDetails.selectSingleNode("TaxAmount").getText();
                            jymxsq.setSpse(Double.valueOf(TaxAmount));
                        }
                        //价税合计
                        String MxTotalAmount = "";
                        if (null != orderDetails.selectSingleNode("MxTotalAmount")
                                && !orderDetails.selectSingleNode("MxTotalAmount").equals("")) {
                            MxTotalAmount = orderDetails.selectSingleNode("MxTotalAmount").getText();
                            jymxsq.setJshj(Double.valueOf(MxTotalAmount));
                        }
                        //商品明细序号
                        jymxsq.setSpmxxh(spmxxh);
                        //可开具金额
                        jymxsq.setKkjje(Double.valueOf(MxTotalAmount));
                        //已开具金额
                        jymxsq.setYkjje(0d);
                        //商品自行编码
                        String VenderOwnCode = "";
                        if (null != orderDetails.selectSingleNode("VenderOwnCode")
                                && !orderDetails.selectSingleNode("VenderOwnCode").equals("")) {
                            VenderOwnCode = orderDetails.selectSingleNode("VenderOwnCode").getText();
                        }
                        jymxsq.setSpzxbm(VenderOwnCode);
                        Map spbmMap=new HashMap();
                        spbmMap.put("spbm",ProductCode);
                        spbmMap.put("gsdm",gsdm);
                        Spvo spvo=spvoService.findOneSpvo(spbmMap);
                        if(spvo!=null){
                            jymxsq.setYhzcbs(spvo.getYhzcbs());
                            jymxsq.setLslbz(spvo.getLslbz());
                            jymxsq.setYhzcmc(spvo.getYhzcmc());
                        }
                        //优惠政策标识
                        String PolicyMark = "";
                        if (null != orderDetails.selectSingleNode("PolicyMark")
                                && !orderDetails.selectSingleNode("PolicyMark").equals("")) {
                            PolicyMark = orderDetails.selectSingleNode("PolicyMark").getText();
                        }
                        //零税率标志
                        String TaxRateMark = "";
                        if (null != orderDetails.selectSingleNode("TaxRateMark")
                                && !orderDetails.selectSingleNode("TaxRateMark").equals("")) {
                            TaxRateMark = orderDetails.selectSingleNode("TaxRateMark").getText();
                        }
                        //优惠政策名称
                        String PolicyName = "";
                        if (null != orderDetails.selectSingleNode("PolicyName")
                                && !orderDetails.selectSingleNode("PolicyName").equals("")) {
                            PolicyName = orderDetails.selectSingleNode("PolicyName").getText();
                        }
                        jymxsq.setGsdm(gsdm);
                        jymxsq.setLrry(lrry);
                        jymxsq.setLrsj(new Date());
                        jymxsq.setXgry(lrry);
                        jymxsq.setXgsj(new Date());
                        jymxsq.setYxbz("1");
                        jymxsqList.add(jymxsq);
                    }
                }
                // 获取参数中对应的支付信息
                Element payments = (Element) xn.selectSingleNode("Payments");
                if (null != payments && !payments.equals("")) {
                    List<Element> paymentItemList = (List<Element>) payments.elements("PaymentItem");
                    if (null != paymentItemList && paymentItemList.size() > 0) {
                        for (Element PaymentItem : paymentItemList) {
                            Jyzfmx jyzfmx = new Jyzfmx();
                            //支付方式代码
                            String zffsDm = "";
                            if (null != PaymentItem.selectSingleNode("PayCode")
                                    && !PaymentItem.selectSingleNode("PayCode").equals("")) {
                                zffsDm = PaymentItem.selectSingleNode("PayCode").getText();
                                jyzfmx.setZffsDm(zffsDm);
                            }
                            //支付总金额
                            String zfje = "";
                            if (null != PaymentItem.selectSingleNode("PayPrice")
                                    && !PaymentItem.selectSingleNode("PayPrice").equals("")) {
                                zfje = PaymentItem.selectSingleNode("PayPrice").getText();
                                jyzfmx.setZfje(Double.valueOf(zfje));
                            }
                            //支付序列号
                            String PayNumber = "";
                            if (null != PaymentItem.selectSingleNode("PayNumber")
                                    && !PaymentItem.selectSingleNode("PayNumber").equals("")) {
                                PayNumber = PaymentItem.selectSingleNode("PayNumber").getText();
                                jyzfmx.setPaynumber(PayNumber);
                            }
                            jyzfmx.setGsdm(gsdm);
                            jyzfmx.setDdh(jyxxsq.getDdh());
                            jyzfmx.setLrry(lrry);
                            jyzfmx.setLrsj(new Date());
                            jyzfmx.setXgry(lrry);
                            jyzfmx.setXgsj(new Date());
                            jyzfmxList.add(jyzfmx);
                        }
                    }
                }
            }
        }
        Map rsMap=new HashMap();
        rsMap.put("jyxxsqList", jyxxsqList);
        rsMap.put("jymxsqList", jymxsqList);
        rsMap.put("jyzfmxList", jyzfmxList);
        return rsMap;
    }
}
