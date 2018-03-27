package com.rjxx.taxeasy.dto;

import java.util.List;

/**
 * @author: zsq
 * @date: 2018/3/27 17:01
 * @describe: 待红冲数据
 */
public class AdapterRedData {

    private String serialNumber; //交易流水号

    private String invType;//发票种类

    private List<AdapterRedInvoiceItem> invoiceItem;//发票明细

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getInvType() {
        return invType;
    }

    public void setInvType(String invType) {
        this.invType = invType;
    }

    public List<AdapterRedInvoiceItem> getInvoiceItem() {
        return invoiceItem;
    }

    public void setInvoiceItem(List<AdapterRedInvoiceItem> invoiceItem) {
        this.invoiceItem = invoiceItem;
    }
}
