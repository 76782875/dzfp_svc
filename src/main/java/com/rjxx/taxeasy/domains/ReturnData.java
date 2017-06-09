package com.rjxx.taxeasy.domains;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by xlm on 2017/6/9.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Request")
public class ReturnData {

    private  OperationItem OperationItem;

    private  InvoiceItems  InvoiceItems;

    public com.rjxx.taxeasy.domains.OperationItem getOperationItem() {
        return OperationItem;
    }

    public void setOperationItem(com.rjxx.taxeasy.domains.OperationItem operationItem) {
        OperationItem = operationItem;
    }

    public com.rjxx.taxeasy.domains.InvoiceItems getInvoiceItems() {
        return InvoiceItems;
    }

    public void setInvoiceItems(com.rjxx.taxeasy.domains.InvoiceItems invoiceItems) {
        InvoiceItems = invoiceItems;
    }
}
