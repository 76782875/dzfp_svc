package com.rjxx.taxeasy.dto;

import java.math.BigDecimal;
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
    private String orderDate;
    private String chargeTaxWay;
    private BigDecimal totalAmount;
    private String taxMark;
    private String remark;
    private String extractedCode;
    private BigDecimal totalDiscount;
    private AdapterDataOrderBuyer buyer;
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

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getChargeTaxWay() {
        return chargeTaxWay;
    }

    public void setChargeTaxWay(String chargeTaxWay) {
        this.chargeTaxWay = chargeTaxWay;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
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

    public AdapterDataOrderBuyer getBuyer() {
        return buyer;
    }

    public void setBuyer(AdapterDataOrderBuyer buyer) {
        this.buyer = buyer;
    }

    public List<AdapterDataOrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<AdapterDataOrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    @Override
    public String toString() {
        return "AdapterDataOrder{" +
                "orderNo='" + orderNo + '\'' +
                ", invoiceList='" + invoiceList + '\'' +
                ", invoiceSplit='" + invoiceSplit + '\'' +
                ", invoiceSfdy='" + invoiceSfdy + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", chargeTaxWay='" + chargeTaxWay + '\'' +
                ", totalAmount=" + totalAmount +
                ", taxMark='" + taxMark + '\'' +
                ", remark='" + remark + '\'' +
                ", extractedCode='" + extractedCode + '\'' +
                ", totalDiscount=" + totalDiscount +
                ", buyer=" + buyer +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
