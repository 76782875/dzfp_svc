package com.rjxx.taxeasy.dto;

import java.util.Date;
import java.util.List;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/3/20
 */
public class AdapterDataOrder {
    private String orderNo;
    private String invoiceList;
    private String invoiceSplit;
    private String invoiceSfdy;
    private Date orderDate;
    private String chargeTaxWay;
    private Double totalAmount;
    private String taxMark;
    private String remark;
    private String extractedCode;
    private Double totalDiscount;
    private AdapterDataOrderBuyer buyer;
    private List<AdapterDataOrderPayments> payments;
    private List<AdapterDataOrderDetails> orderDetails;

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
