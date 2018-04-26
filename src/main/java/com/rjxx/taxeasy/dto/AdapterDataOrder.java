package com.rjxx.taxeasy.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/3/20
 */
public class AdapterDataOrder {
    @JSONField(ordinal=1)
    private String orderNo;
    @JSONField(ordinal=2)
    private Date orderDate;
    @JSONField(ordinal=3)
    private Double totalAmount;
    @JSONField(ordinal=4)
    private String remark;
    @JSONField(ordinal=5)
    private AdapterDataOrderBuyer buyer;
    @JSONField(ordinal=6)
    private List<AdapterDataOrderDetails> orderDetails;
    @JSONField(ordinal=7)
    private List<AdapterDataOrderPayments> payments;
    @JSONField(ordinal=8)
    private String invoiceList;
    @JSONField(ordinal=9)
    private String invoiceSplit;
    @JSONField(ordinal=10)
    private String invoiceSfdy;
    @JSONField(ordinal=11)
    private String chargeTaxWay;
    @JSONField(ordinal=12)
    private String taxMark;
    @JSONField(ordinal=13)
    private String extractedCode;
    @JSONField(ordinal=14)
    private Double totalDiscount;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(String invoiceList) {
        this.invoiceList = invoiceList;
    }

    public String getInvoiceSplit() {
        return invoiceSplit;
    }

    public void setInvoiceSplit(String invoiceSplit) {
        this.invoiceSplit = invoiceSplit;
    }

    public String getInvoiceSfdy() {
        return invoiceSfdy;
    }

    public void setInvoiceSfdy(String invoiceSfdy) {
        this.invoiceSfdy = invoiceSfdy;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getChargeTaxWay() {
        return chargeTaxWay;
    }

    public void setChargeTaxWay(String chargeTaxWay) {
        this.chargeTaxWay = chargeTaxWay;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTaxMark() {
        return taxMark;
    }

    public void setTaxMark(String taxMark) {
        this.taxMark = taxMark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExtractedCode() {
        return extractedCode;
    }

    public void setExtractedCode(String extractedCode) {
        this.extractedCode = extractedCode;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public AdapterDataOrderBuyer getBuyer() {
        return buyer;
    }

    public void setBuyer(AdapterDataOrderBuyer buyer) {
        this.buyer = buyer;
    }

    public List<AdapterDataOrderPayments> getPayments() {
        return payments;
    }

    public void setPayments(List<AdapterDataOrderPayments> payments) {
        this.payments = payments;
    }

    public List<AdapterDataOrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<AdapterDataOrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
