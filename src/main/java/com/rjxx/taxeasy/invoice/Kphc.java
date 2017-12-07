package com.rjxx.taxeasy.invoice;

public class Kphc {
    /**
     * 原发票代码
     */
    private String CNDNCode;
    /**
     * 原发票号码
     */
    private String CNDNNo;
    /**
     * 发票种类
     */
    private String InvType;
    /**
     * 提取码
     */
    private String ExtractCode;
    /**
     * 开票点代码
     */
    private String ClientNO;
    /**
     * 发票业务类型
     */
    private String ServiceType;
    /**
     * 序列号
     */
    private String SerialNumber;
    /**
     * 订单号
     */
    private String OrderNumber;
    /**
     * 专票红字通知单号
     */
    private String CNNoticeNo;
    /**
     * 价税合计
     */
    private Double TotalAmount;

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

    public String getInvType() {
        return InvType;
    }

    public void setInvType(String invType) {
        InvType = invType;
    }

    public String getExtractCode() {
        return ExtractCode;
    }

    public void setExtractCode(String extractCode) {
        ExtractCode = extractCode;
    }

    public String getClientNO() {
        return ClientNO;
    }

    public void setClientNO(String clientNO) {
        ClientNO = clientNO;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public String getCNNoticeNo() {
        return CNNoticeNo;
    }

    public void setCNNoticeNo(String CNNoticeNo) {
        this.CNNoticeNo = CNNoticeNo;
    }

    public Double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        TotalAmount = totalAmount;
    }
}
