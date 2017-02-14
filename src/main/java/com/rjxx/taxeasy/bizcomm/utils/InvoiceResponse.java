package com.rjxx.taxeasy.bizcomm.utils;

import java.util.Date;

/**
 * 税控操作返回值
 * Created by zhangbing on 2017-02-13.
 */
public class InvoiceResponse {

    //开票流水号
    private int kplsh;

    //返回值
    private String returnCode;

    //返回信息
    private String returnMessage;

    //发票代码
    private String fpdm;

    //发票号码
    private String fphm;

    //开票日期
    private Date kprq;

    //打印标志，0-未打印，1-打印
    private int printFlag;

    public int getKplsh() {
        return kplsh;
    }

    public void setKplsh(int kplsh) {
        this.kplsh = kplsh;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getFpdm() {
        return fpdm;
    }

    public void setFpdm(String fpdm) {
        this.fpdm = fpdm;
    }

    public String getFphm() {
        return fphm;
    }

    public void setFphm(String fphm) {
        this.fphm = fphm;
    }

    public Date getKprq() {
        return kprq;
    }

    public void setKprq(Date kprq) {
        this.kprq = kprq;
    }

    public int getPrintFlag() {
        return printFlag;
    }

    public void setPrintFlag(int printFlag) {
        this.printFlag = printFlag;
    }
}
