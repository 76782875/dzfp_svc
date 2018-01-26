package com.rjxx.taxeasy.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rjxx.taxeasy.dao.leshui.FpcyJpaDao;
import com.rjxx.taxeasy.dao.leshui.FpcyjlJpaDao;
import com.rjxx.taxeasy.dao.leshui.FpcymxJpaDao;
import com.rjxx.taxeasy.domains.leshui.Fpcy;
import com.rjxx.taxeasy.domains.leshui.Fpcyjl;
import com.rjxx.taxeasy.domains.leshui.Fpcymx;
import com.rjxx.utils.leshui.LeShuiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    private final static String INVOICE_INFO_SUCCESS = "00";
    private final static String INVOICE_QUERY_SUCCESS = "000";

    public String fpcyAndSave(String invoiceCode, String invoiceNumber, String billTime,
                       String checkCode, String invoiceAmount) {
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

        Fpcy OldFpcy = fpcyJpaDao.findOneByFpdmAndFphm(invoiceCode, invoiceNumber);
        //如果库中没有记录
        if (OldFpcy == null) {
            //保存结果
            Fpcy newFpcy = new Fpcy();
            List<Fpcymx> mxs = new ArrayList<>();
            newFpcy.setReturncode(rtnCode_r);
            newFpcy.setResultcode(resultCode_r);
            newFpcy.setResultmsg(resultMsg_r);
            newFpcy.setInvoicename(invoiceName_r);
            newFpcy.setFalsecode(invoicefalseCode_r);
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
                        fpcymx.setSpmxxh(i+1);
                        fpcymx.setQdhbz(isBillLine);
                        mxs.add(fpcymx);
                    }
                }
            }
            //保存主表
            Fpcy save = fpcyJpaDao.save(newFpcy);
            Integer fpcyid = save.getId();
            //保存明细表
            if(mxs!=null && mxs.size()>0){
                for(Fpcymx fpcymx:mxs){
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
            fpcyjlJpaDao.save(fpcyjl);

            //库中如果有记录
        }else{
            OldFpcy.setReturncode(rtnCode_r);
            OldFpcy.setResultcode(resultCode_r);
            OldFpcy.setResultmsg(resultMsg_r);
            OldFpcy.setInvoicename(invoiceName_r);
            OldFpcy.setFalsecode(invoicefalseCode_r);
            if (INVOICE_INFO_SUCCESS.equals(rtnCode_r)) {
                if (invoiceResult_r != null) {
                    String voidMark_r = invoiceResult_r.getString("voidMark");//作废标志，0：正常，1：作废
                    Integer checkNum_r = invoiceResult_r.getInteger("checkNum");//查询次数
                    OldFpcy.setFpzt(voidMark_r);
                    OldFpcy.setCycs(checkNum_r);
                }
            }
            Fpcy save = fpcyJpaDao.save(OldFpcy);
            //保存记录表
            Fpcyjl fpcyjl = new Fpcyjl();
            fpcyjl.setFpcyid(save.getId());
            fpcyjl.setCycs(save.getCycs());
            fpcyjl.setCyrq(save.getCyrq());
            fpcyjl.setFpzt(save.getFpzt());
            fpcyjlJpaDao.save(fpcyjl);
        }
        return result;
    }
}
