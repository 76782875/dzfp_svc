package com.rjxx.taxeasy.service.leshui;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rjxx.taxeasy.dao.leshui.*;
import com.rjxx.taxeasy.domains.leshui.*;
import com.rjxx.utils.leshui.LeShuiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * Created by wangyahui on 2018/1/26 0026.
 */
@Service
public class LeshuiService {
    @Autowired
    private FpcyJpaDao fpcyJpaDao;
    @Autowired
    private FpcyjlJpaDao fpcyjlJpaDao;
    @Autowired
    private FpcymxJpaDao fpcymxJpaDao;
    @Autowired
    private JxfpxxJpaDao jxfpxxJpaDao;
    @Autowired
    private JxfpmxJpaDao jxfpmxJpaDao;

    private final static String INVOICE_INFO_SUCCESS = "00";
    private final static String INVOICE_QUERY_SUCCESS = "000";

    /**
     * 发票查验
     *
     * @param invoiceCode   发票代码
     * @param invoiceNumber 发票号码
     * @param billTime      开票日期
     * @param checkCode     校验码
     * @param invoiceAmount 不含税金额
     * @param sjly          数据来源
     * @param gsdm          公司代码
     * @return 乐税接口返回数据
     */
    public String fpcyAndSave(String invoiceCode, String invoiceNumber, String billTime,
                              String checkCode, String invoiceAmount, String sjly, String gsdm) {
        //调发票查验接口
        String result = LeShuiUtil.invoiceInfoForCom(invoiceCode, invoiceNumber, billTime, checkCode, invoiceAmount);
        //解析返回值
        JSONObject resultJson = JSON.parseObject(result);
        String rtnCode_r = resultJson.getString("RtnCode");//服务器是否正常标志
        String resultCode_r = resultJson.getString("resultCode");//查询发票状态码
        String resultMsg_r = resultJson.getString("resultMsg");//查验结果
        String invoiceName_r = resultJson.getString("invoiceName");//发票名称
        JSONObject invoiceResult_r = resultJson.getJSONObject(" invoiceResult");//发票主体信息
        String invoicefalseCode_r = resultJson.getString("invoicefalseCode");//错误码;

        Fpcy oldFpcy = fpcyJpaDao.findOneByFpdmAndFphm(invoiceCode, invoiceNumber);
        //如果库中没有记录
        if (oldFpcy == null) {
            //保存结果
            Fpcy newFpcy = new Fpcy();
            List<Fpcymx> mxs = new ArrayList<>();
            newFpcy.setReturncode(rtnCode_r);
            newFpcy.setResultcode(resultCode_r);
            newFpcy.setResultmsg(resultMsg_r);
            newFpcy.setInvoicename(invoiceName_r);
            newFpcy.setFalsecode(invoicefalseCode_r);
            newFpcy.setSjly(sjly);
            newFpcy.setGsdm(gsdm);
            newFpcy.setYxbz("1");
            if (INVOICE_INFO_SUCCESS.equals(rtnCode_r)) {
                newFpcy.setFpdm(invoiceCode);
                newFpcy.setFphm(invoiceNumber);
                newFpcy.setJym(checkCode);
                newFpcy.setKprq(billTime);
                newFpcy.setHjje(new BigDecimal(invoiceAmount));
                if (invoiceResult_r != null) {
                    String invoiceDataCode_r = invoiceResult_r.getString("invoiceDataCode");//发票代码
                    String invoiceNumber_r = invoiceResult_r.getString("invoiceNumber");//发票号码
                    String invoiceTypeName_r = invoiceResult_r.getString("invoiceTypeName");//发票类型名称
                    String invoiceTypeCode_r = invoiceResult_r.getString("invoiceTypeCode");//发票类型  01:增值税专票,02:货物运输业增值税专用发票,04:增值税普通发票,03:机动车销售统一发票,10:电子发票,11:卷式普通发票,20:国税,30:地税
                    String billingTime_r = invoiceResult_r.getString("billingTime");//开票时间
                    String checkDate_r = invoiceResult_r.getString("checkDate");//查询时间
                    String checkCode_r = invoiceResult_r.getString("checkCode");// 校验码
                    String taxDiskCode_r = invoiceResult_r.getString("taxDiskCode");//机器码
                    String purchaserName_r = invoiceResult_r.getString("purchaserName");//购方名称
                    String taxpayerNumber_r = invoiceResult_r.getString("taxpayerNumber");//购方识别号
                    String taxpayerBankAccount_r = invoiceResult_r.getString("taxpayerBankAccount");//购方银行账号
                    String taxpayerAddressOrId_r = invoiceResult_r.getString("taxpayerAddressOrId");//购方地址，电话
                    String salesName_r = invoiceResult_r.getString("salesName");//销方名称
                    String salesTaxpayerNum_r = invoiceResult_r.getString("salesTaxpayerNum");//销方纳税人识别号
                    String salesTaxpayerBankAccount_r = invoiceResult_r.getString("salesTaxpayerBankAccount");//销方银行，账号
                    String salesTaxpayerAddress_r = invoiceResult_r.getString("salesTaxpayerAddress");//销方地址，电话
                    BigDecimal totalTaxSum_r = invoiceResult_r.getBigDecimal("totalTaxSum");//价税合计
                    BigDecimal totalTaxNum_r = invoiceResult_r.getBigDecimal("totalTaxNum");//税额
                    BigDecimal totalAmount_r = invoiceResult_r.getBigDecimal("totalAmount");//不含税价（金额）
                    String invoiceRemarks_r = invoiceResult_r.getString("invoiceRemarks");//备注
                    String isBillMark_r = invoiceResult_r.getString("isBillMark");//是否为清单票，Y：是，N：否
                    String voidMark_r = invoiceResult_r.getString("voidMark");//作废标志，0：正常，1：作废
                    Integer checkNum_r = invoiceResult_r.getInteger("checkNum");//查询次数
                    newFpcy.setCycs(checkNum_r);
                    newFpcy.setFpzt(voidMark_r);
                    newFpcy.setQdbz(isBillMark_r);
                    newFpcy.setBz(invoiceRemarks_r);
                    newFpcy.setHjje(totalAmount_r);
                    newFpcy.setHjse(totalTaxNum_r);
                    newFpcy.setJshj(totalTaxSum_r);
                    newFpcy.setXfdzdh(salesTaxpayerAddress_r);
                    newFpcy.setXfyhyhzh(salesTaxpayerBankAccount_r);
                    newFpcy.setXfsh(salesTaxpayerNum_r);
                    newFpcy.setXfmc(salesName_r);
                    newFpcy.setGfdzdh(taxpayerAddressOrId_r);
                    newFpcy.setGfyhyhzh(taxpayerBankAccount_r);
                    newFpcy.setGfsh(taxpayerNumber_r);
                    newFpcy.setGfmc(purchaserName_r);
                    newFpcy.setJqm(taxDiskCode_r);
                    newFpcy.setCyrq(checkDate_r);
                    newFpcy.setFpdm(invoiceDataCode_r);
                    newFpcy.setFphm(invoiceNumber_r);
                    newFpcy.setJym(checkCode_r);
                    newFpcy.setKprq(billingTime_r);
                    newFpcy.setFpzldm(invoiceTypeCode_r);
                    newFpcy.setFpzlmc(invoiceTypeName_r);
                    //机动车票无此字段
                    JSONArray invoiceDetailData_r = invoiceResult_r.getJSONArray("invoiceDetailData");//发票明细
                    for (int i = 0; i < invoiceDetailData_r.size(); i++) {
                        Fpcymx fpcymx = new Fpcymx();
                        JSONObject o = (JSONObject) invoiceDetailData_r.get(i);
                        String unit = o.getString("unit");//单位
                        String model = o.getString("model");//型号
                        String isBillLine = o.getString("isBillLine");//是否是清单行
                        BigDecimal price = o.getBigDecimal("price");//单价
                        BigDecimal tax = o.getBigDecimal("tax");//税额
                        BigDecimal taxRate = o.getBigDecimal("taxRate");//税率
                        String goodserviceName = o.getString("goodserviceName");//货劳务名称
                        BigDecimal sum = o.getBigDecimal("sum");//金额
                        BigDecimal number = o.getBigDecimal("number");//数量
                        fpcymx.setSpdw(unit);
                        fpcymx.setSpdj(price);
                        fpcymx.setSpggxh(model);
                        fpcymx.setSpmc(goodserviceName);
                        fpcymx.setSps(number);
                        fpcymx.setSpje(sum);
                        fpcymx.setSpsl(taxRate);
                        fpcymx.setSpse(tax);
                        fpcymx.setSpmxxh(i + 1);
                        fpcymx.setQdhbz(isBillLine);
                        mxs.add(fpcymx);
                    }
                }
            } else {
                newFpcy.setFpzt("失败");
            }
            //保存主表
            Fpcy save = fpcyJpaDao.save(newFpcy);
            Integer fpcyid = save.getId();
            //保存明细表
            if (mxs != null && mxs.size() > 0) {
                for (Fpcymx fpcymx : mxs) {
                    fpcymx.setFpcyid(fpcyid);
                }
            }
            fpcymxJpaDao.save(mxs);
            //保存记录表
            Fpcyjl fpcyjl = new Fpcyjl();
            fpcyjl.setFpcyid(fpcyid);
            fpcyjl.setCycs(save.getCycs());
            fpcyjl.setCyrq(save.getCyrq());
            fpcyjl.setFpzt(save.getFpzt());
            fpcyjl.setGsdm(gsdm);
            fpcyjlJpaDao.save(fpcyjl);

            //库中如果有记录
        } else {
            oldFpcy.setReturncode(rtnCode_r);
            oldFpcy.setResultcode(resultCode_r);
            oldFpcy.setResultmsg(resultMsg_r);
            oldFpcy.setInvoicename(invoiceName_r);
            oldFpcy.setFalsecode(invoicefalseCode_r);
            oldFpcy.setSjly(sjly);
            oldFpcy.setYxbz("1");
            if (INVOICE_INFO_SUCCESS.equals(rtnCode_r)) {
                if (invoiceResult_r != null) {
                    String voidMark_r = invoiceResult_r.getString("voidMark");//作废标志，0：正常，1：作废
                    Integer checkNum_r = invoiceResult_r.getInteger("checkNum");//查询次数
                    oldFpcy.setFpzt(voidMark_r);
                    oldFpcy.setCycs(checkNum_r);
                }
            }
            Fpcy save = fpcyJpaDao.save(oldFpcy);
            //保存记录表
            Fpcyjl fpcyjl = new Fpcyjl();
            fpcyjl.setFpcyid(save.getId());
            fpcyjl.setCycs(save.getCycs());
            fpcyjl.setCyrq(save.getCyrq());
            fpcyjl.setFpzt(save.getFpzt());
            fpcyjl.setGsdm(gsdm);
            fpcyjlJpaDao.save(fpcyjl);
        }
        return result;
    }


    /**
     * 单张发票查询
     *
     * @param invoiceCode 发票代码
     * @param invoiceNo   发票号码
     * @param taxCode     税号
     * @param gsdm        公司代码
     * @return 乐税接口返回结果
     */
    public String fpcx(String invoiceCode, String invoiceNo,
                       String taxCode, String gsdm) {
        //调单张发票查询接口
        String uniqueId = "QBI" + new SimpleDateFormat("yyyyMMddhhmmss")
                .format(new Date())
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + LeShuiUtil.getRandomLetter();
        String result = LeShuiUtil.invoiceQuery(uniqueId, invoiceCode, invoiceNo, taxCode);
        JSONObject resultJson = JSON.parseObject(result);
        JSONObject head = resultJson.getJSONObject("head");
        String rtnMsg = head.getString("rtnMsg");
        String rtnCode = head.getString("rtnCode");
        JSONObject body = resultJson.getJSONObject("body");
        //查询库中是否已有
        Jxfpxx oldJxfpxx = jxfpxxJpaDao.findByFpdmAndFphm(invoiceCode, invoiceNo);
        if (oldJxfpxx == null) {
            //如果返回成功
            if (INVOICE_QUERY_SUCCESS.equals(rtnCode)) {
                List<Jxfpmx> jxfpmxList = new ArrayList<>();
                Jxfpxx newJxfpxx = new Jxfpxx();
                newJxfpxx.setGsdm(gsdm);
                newJxfpxx.setYxbz("1");
                newJxfpxx.setUniqueid(uniqueId);
                String invoiceCode_r = body.getString("invoiceCode");
                String invoiceNo_r = body.getString("invoiceNo");
                String type = body.getString("type");
                String status = body.getString("status");
                BigDecimal amount = body.getBigDecimal("amount");
                BigDecimal taxAmount = body.getBigDecimal("taxAmount");
                BigDecimal totalAmount = body.getBigDecimal("totalAmount");
                String salerCompany = body.getString("salerCompany");
                String salerCode = body.getString("salerCode");
                String salerAddress = body.getString("salerAddress");
                String salerBankAccount = body.getString("salerBankAccount");
                String buyerCompany = body.getString("buyerCompany");
                String buyerCode = body.getString("buyerCode");
                String buyerAddress = body.getString("buyerAddress");
                String buyerBankAccount = body.getString("buyerBankAccount");
                String createDate = body.getString("createDate");
                String verifyCode = body.getString("verifyCode");
                String machineCode = body.getString("machineCode");
                String invoicesStatus = body.getString("invoicesStatus");
                String isAuth = body.getString("isAuth");
                String authTime = body.getString("authTime");
                String authType = body.getString("authType");
                String remark = body.getString("remark");
                newJxfpxx.setBz(remark);
                newJxfpxx.setRzlx(authType);
                newJxfpxx.setRzsj(authTime);
                newJxfpxx.setRzbz(isAuth);
                newJxfpxx.setFpzt(invoicesStatus);
                newJxfpxx.setJqm(machineCode);
                newJxfpxx.setJym(verifyCode);
                newJxfpxx.setKprq(createDate);
                newJxfpxx.setGfyhyhzh(buyerBankAccount);
                newJxfpxx.setGfdzdh(buyerAddress);
                newJxfpxx.setGfsh(buyerCode);
                newJxfpxx.setGfmc(buyerCompany);
                newJxfpxx.setXfdzdh(salerBankAccount);
                newJxfpxx.setXfdzdh(salerAddress);
                newJxfpxx.setXfsh(salerCode);
                newJxfpxx.setXfmc(salerCompany);
                newJxfpxx.setJshj(totalAmount);
                newJxfpxx.setHjse(taxAmount);
                newJxfpxx.setHjje(amount);
                newJxfpxx.setFpzldm(type);
                newJxfpxx.setFpdm(invoiceCode_r);
                newJxfpxx.setFphm(invoiceNo_r);
                JSONArray goods = body.getJSONArray("goods");
                for (int j = 0; j < goods.size(); j++) {
                    Jxfpmx jxfpmx = new Jxfpmx();
                    JSONObject good = (JSONObject) goods.get(j);
                    String goodName = good.getString("goodName");
                    String unit = good.getString("unit");
                    BigDecimal rate = good.getBigDecimal("rate");
                    BigDecimal taxAmountLine = good.getBigDecimal("taxAmount");
                    BigDecimal amountLine = good.getBigDecimal("amount");
                    BigDecimal price = good.getBigDecimal("price");
                    String model = good.getString("model");
                    BigDecimal count = good.getBigDecimal("count");
                    String lineNum = good.getString("lineNum");
                    jxfpmx.setSpsl(rate);
                    jxfpmx.setSps(count);
                    jxfpmx.setSpdj(price);
                    jxfpmx.setSpdw(unit);
                    jxfpmx.setSpggxh(model);
                    jxfpmx.setSpje(amountLine);
                    jxfpmx.setSpse(taxAmountLine);
                    jxfpmx.setSpmc(goodName);
                    jxfpmx.setHh(lineNum);
                    jxfpmx.setSpmxxh(j + 1);
                    jxfpmxList.add(jxfpmx);
                }
                Jxfpxx save = jxfpxxJpaDao.save(newJxfpxx);
                Integer fplsh = save.getFplsh();
                //保存明细
                for (Jxfpmx jxfpmx : jxfpmxList) {
                    jxfpmx.setFplsh(fplsh);
                }
                jxfpmxJpaDao.save(jxfpmxList);
            }
            //如果库中存在
        } else {
            if (INVOICE_QUERY_SUCCESS.equals(rtnCode)) {
                oldJxfpxx.setUniqueid(uniqueId);
                oldJxfpxx.setGsdm(gsdm);
                oldJxfpxx.setYxbz("1");
                String invoicesStatus = body.getString("invoicesStatus");
                oldJxfpxx.setFpzt(invoicesStatus);
                String isAuth = body.getString("isAuth");
                oldJxfpxx.setRzbz(isAuth);
                String authType = body.getString("authType");
                oldJxfpxx.setRzlx(authType);
                String authTime = body.getString("authTime");
                oldJxfpxx.setRzsj(authTime);
                jxfpxxJpaDao.save(oldJxfpxx);
            }
        }
        return result;
    }

    /**
     * 发票批量查询
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param taxCode   税号
     * @param pageNo    第几页
     */
    public String fpcxBatch(String startTime, String endTime,
                          String taxCode, String pageNo,String gsdm) {
        String uniqueId = "QBI" + new SimpleDateFormat("yyyyMMddhhmmss")
                .format(new Date())
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + LeShuiUtil.getRandomLetter();
        //调接口
        String result = LeShuiUtil.invoiceBatchQuery(uniqueId, startTime, endTime, taxCode, pageNo);
        JSONObject resultJson = JSON.parseObject(result);
        JSONObject head = resultJson.getJSONObject("head");
        String rtnMsg = head.getString("rtnMsg");
        String rtnCode = head.getString("rtnCode");
        JSONObject body = resultJson.getJSONObject("body");
        String pageNo_r = body.getString("pageNo");
        String pageSize = body.getString("pageSize");
        String totalSum = body.getString("totalSum");
        JSONArray invoices = body.getJSONArray("invoices");
        //成功
        if(INVOICE_QUERY_SUCCESS.equals(rtnCode)){
            //如果发票信息不为空
            if (invoices != null && invoices.size() > 0) {
                for (int i = 0; i < invoices.size(); i++) {
                    JSONObject fpcxBatch = (JSONObject) invoices.get(i);
                    String invoiceCode = fpcxBatch.getString("invoiceCode");
                    String invoiceNo = fpcxBatch.getString("invoiceNo");
                    String type = fpcxBatch.getString("type");
                    String status = fpcxBatch.getString("status");
                    BigDecimal amount = fpcxBatch.getBigDecimal("amount");
                    BigDecimal taxAmount = fpcxBatch.getBigDecimal("taxAmount");
                    BigDecimal totalAmount = fpcxBatch.getBigDecimal("totalAmount");
                    String salerCompany = fpcxBatch.getString("salerCompany");
                    String salerCode = fpcxBatch.getString("salerCode");
                    String salerAddress = fpcxBatch.getString("salerAddress");
                    String salerBankAccount = fpcxBatch.getString("salerBankAccount");
                    String buyerCompany = fpcxBatch.getString("buyerCompany");
                    String buyerCode = fpcxBatch.getString("buyerCode");
                    String buyerAddress = fpcxBatch.getString("buyerAddress");
                    String buyerBankAccount = fpcxBatch.getString("buyerBankAccount");
                    String createDate = fpcxBatch.getString("createDate");
                    String verifyCode = fpcxBatch.getString("verifyCode");
                    String machineCode = fpcxBatch.getString("machineCode");
                    String invoicesStatus = fpcxBatch.getString("invoicesStatus");
                    String isAuth = fpcxBatch.getString("isAuth");
                    String authTime = fpcxBatch.getString("authTime");
                    String authType = fpcxBatch.getString("authType");
                    String remark = fpcxBatch.getString("remark");
                    JSONArray goods = fpcxBatch.getJSONArray("goods");
                    List<Jxfpmx> jxfpmxList = new ArrayList<>();
                    for (int j = 0; j < goods.size(); j++) {
                        Jxfpmx jxfpmx = new Jxfpmx();
                        JSONObject good = (JSONObject) goods.get(j);
                        String goodName = good.getString("goodName");
                        String unit = good.getString("unit");
                        BigDecimal rate = good.getBigDecimal("rate");
                        BigDecimal taxAmountLine = good.getBigDecimal("taxAmount");
                        BigDecimal amountLine = good.getBigDecimal("amount");
                        BigDecimal price = good.getBigDecimal("price");
                        String model = good.getString("model");
                        BigDecimal count = good.getBigDecimal("count");
                        String lineNum = good.getString("lineNum");
                        jxfpmx.setSpsl(rate);
                        jxfpmx.setSps(count);
                        jxfpmx.setSpdj(price);
                        jxfpmx.setSpdw(unit);
                        jxfpmx.setSpggxh(model);
                        jxfpmx.setSpje(amountLine);
                        jxfpmx.setSpse(taxAmountLine);
                        jxfpmx.setSpmc(goodName);
                        jxfpmx.setHh(lineNum);
                        jxfpmx.setSpmxxh(j + 1);
                        jxfpmxList.add(jxfpmx);
                    }
                    //查询表中是否已存在
                    Jxfpxx oldJxfpxx = jxfpxxJpaDao.findByFpdmAndFphm(invoiceCode, invoiceNo);
                    if(oldJxfpxx==null){
                        Jxfpxx newJxfpxx = new Jxfpxx();
                        newJxfpxx.setGsdm(gsdm);
                        newJxfpxx.setYxbz("1");
                        newJxfpxx.setUniqueid(uniqueId);
                        newJxfpxx.setBz(remark);
                        newJxfpxx.setRzlx(authType);
                        newJxfpxx.setRzsj(authTime);
                        newJxfpxx.setRzbz(isAuth);
                        newJxfpxx.setFpzt(invoicesStatus);
                        newJxfpxx.setJqm(machineCode);
                        newJxfpxx.setJym(verifyCode);
                        newJxfpxx.setKprq(createDate);
                        newJxfpxx.setGfyhyhzh(buyerBankAccount);
                        newJxfpxx.setGfdzdh(buyerAddress);
                        newJxfpxx.setGfsh(buyerCode);
                        newJxfpxx.setGfmc(buyerCompany);
                        newJxfpxx.setXfdzdh(salerBankAccount);
                        newJxfpxx.setXfdzdh(salerAddress);
                        newJxfpxx.setXfsh(salerCode);
                        newJxfpxx.setXfmc(salerCompany);
                        newJxfpxx.setJshj(totalAmount);
                        newJxfpxx.setHjse(taxAmount);
                        newJxfpxx.setHjje(amount);
                        newJxfpxx.setFpzldm(type);
                        newJxfpxx.setFpdm(invoiceCode);
                        newJxfpxx.setFphm(invoiceNo);
                        Jxfpxx save = jxfpxxJpaDao.save(newJxfpxx);
                        Integer fplsh = save.getFplsh();
                        //保存明细
                        for (Jxfpmx jxfpmx : jxfpmxList) {
                            jxfpmx.setFplsh(fplsh);
                        }
                        jxfpmxJpaDao.save(jxfpmxList);
                        //存在
                    }else{
                        oldJxfpxx.setUniqueid(uniqueId);
                        oldJxfpxx.setGsdm(gsdm);
                        oldJxfpxx.setYxbz("1");
                        oldJxfpxx.setFpzt(body.getString("invoicesStatus"));
                        oldJxfpxx.setRzbz(body.getString("isAuth"));
                        oldJxfpxx.setRzlx(body.getString("authType"));
                        oldJxfpxx.setRzsj(body.getString("authTime"));
                        jxfpxxJpaDao.save(oldJxfpxx);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 发票认证
     * @param taxCode 税号
     * @param body 发票信息
     * @return 乐税接口返回数据
     */
    public String fprz(String taxCode,
                     List<InvoiceAuth> body){
        String batchId = "QBI" + new SimpleDateFormat("yyyyMMddhhmmss")
                .format(new Date())
                + new Random().nextInt(9)
                + new Random().nextInt(9)
                + LeShuiUtil.getRandomLetter();
        String result = LeShuiUtil.invoiceAuthorize(batchId, taxCode, body);
        JSONObject resultJson = JSON.parseObject(result);
        JSONObject head = resultJson.getJSONObject("head");
        JSONObject body_r = resultJson.getJSONObject("body");
        String rtnMsg = head.getString("rtnMsg");
        String rtnCode = head.getString("rtnCode");
        String resultCode = body_r.getString("resultCode");
        String resultMsg = body_r.getString("resultMsg");
        for(InvoiceAuth auth:body){
            String fpdm = auth.getFpdm();
            String fphm = auth.getFphm();
            Jxfpxx oldJxfpxx = jxfpxxJpaDao.findByFpdmAndFphm(fpdm, fphm);
            if(oldJxfpxx==null){
                Jxfpxx jxfpxx = new Jxfpxx();
                jxfpxx.setFpdm(fpdm);
                jxfpxx.setFphm(fphm);
                jxfpxx.setBatchid(batchId);
                jxfpxx.setRzresultcode(resultCode);
                jxfpxx.setRzresultmsg(resultMsg);
                jxfpxx.setRzreturncode(rtnCode);
                jxfpxx.setRzreturnmsg(rtnMsg);
                jxfpxxJpaDao.save(jxfpxx);
            }else{
                oldJxfpxx.setBatchid(batchId);
                oldJxfpxx.setRzresultcode(resultCode);
                oldJxfpxx.setRzresultmsg(resultMsg);
                oldJxfpxx.setRzreturncode(rtnCode);
                oldJxfpxx.setRzreturnmsg(rtnMsg);
                jxfpxxJpaDao.save(oldJxfpxx);
            }
        }
        return result;
    }
}
