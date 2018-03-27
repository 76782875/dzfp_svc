package com.rjxx.taxeasy.dto;

/**
 * @author: zsq
 * @date: 2018/3/27 17:04
 * @describe:
 */
public class AdapterRedInvoiceItem {

    private Double totalAmount;//价税合计

    private String cnnoticeNo;//专用发票红票通知单号

    private String cndnCode;//作废或红冲时对应的原始发票代码

    private String cndnNo;//作废或红冲时对应的原始发票号码

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCnnoticeNo() {
        return cnnoticeNo;
    }

    public void setCnnoticeNo(String cnnoticeNo) {
        this.cnnoticeNo = cnnoticeNo;
    }

    public String getCndnCode() {
        return cndnCode;
    }

    public void setCndnCode(String cndnCode) {
        this.cndnCode = cndnCode;
    }

    public String getCndnNo() {
        return cndnNo;
    }

    public void setCndnNo(String cndnNo) {
        this.cndnNo = cndnNo;
    }
}
