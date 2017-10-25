package com.rjxx.taxeasy.domains;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by xlm on 2017/10/25.发票红冲Data转换XML
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Request")
public class HcData {

    private  String ClientNO;

    private  String SerialNumber;

    private  String InvType;

    private  String ServiceType;

    private  String ChargeTaxWay;

    private  String TotalAmount;

    private  String CNNoticeNo;

    private  String CNDNCode;

    private  String CNDNNo;

    public String getClientNO() {
        return ClientNO;
    }

    public void setClientNO(String clientNO) {
        ClientNO = clientNO;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getInvType() {
        return InvType;
    }

    public void setInvType(String invType) {
        InvType = invType;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public String getChargeTaxWay() {
        return ChargeTaxWay;
    }

    public void setChargeTaxWay(String chargeTaxWay) {
        ChargeTaxWay = chargeTaxWay;
    }

    public String getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getCNNoticeNo() {
        return CNNoticeNo;
    }

    public void setCNNoticeNo(String CNNoticeNo) {
        this.CNNoticeNo = CNNoticeNo;
    }

    public String getCNDNCode() {
        return CNDNCode;
    }

    public void setCNDNCode(String CNDNCode) {
        this.CNDNCode = CNDNCode;
    }

    public String getCNDNNo() {
        return CNDNNo;
    }

    public void setCNDNNo(String CNDNNo) {
        this.CNDNNo = CNDNNo;
    }
}
