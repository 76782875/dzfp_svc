package com.rjxx.taxeasy.bizcomm.utils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 税控操作返回值
 * Created by zhangbing on 2017-02-13.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Response")
public class InvoiceResponse {

	
	//开票点代码
    @XmlElement(name = "ClientNo")
    private String kpddm;
    
    //交易流水号
    @XmlElement(name = "SwiftNumber")
    private String jylsh;
    
    //开票流水号（单据号）
    @XmlElement(name = "SerialNumber")
    private String lsh;

    //返回值
    @XmlElement(name = "ReturnCode")
    private String returnCode;

    //返回信息
    @XmlElement(name = "ReturnMessage")
    private String returnMessage;

    //发票代码
    @XmlElement(name = "InvoiceCode")
    private String fpdm;

    //发票号码
    @XmlElement(name = "InvoiceNo")
    private String fphm;

    //开票日期
    @XmlElement(name = "InvoiceDate")
    private String kprq;

    //打印标志，0-未打印，1-打印
    @XmlElement(name = "PrintFlag")
    private int printFlag;

    @XmlElement(name = "InvoiceType")
    private String fplxdm;

    @XmlElement(name = "CancelDate")
    private String CancelDate;

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
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

    public String getKprq() {
        return kprq;
    }

    public void setKprq(String kprq) {
        this.kprq = kprq;
    }

    public int getPrintFlag() {
        return printFlag;
    }

    public void setPrintFlag(int printFlag) {
        this.printFlag = printFlag;
    }

    public String getFplxdm() {
        return fplxdm;
    }

    public void setFplxdm(String fplxdm) {
        this.fplxdm = fplxdm;
    }

	public String getKpddm() {
		return kpddm;
	}

	public void setKpddm(String kpddm) {
		this.kpddm = kpddm;
	}

	public String getJylsh() {
		return jylsh;
	}

	public void setJylsh(String jylsh) {
		this.jylsh = jylsh;
	}

    public String getCancelDate() {
        return CancelDate;
    }

    public void setCancelDate(String cancelDate) {
        CancelDate = cancelDate;
    }
}
