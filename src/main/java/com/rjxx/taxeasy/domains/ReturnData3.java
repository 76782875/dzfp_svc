package com.rjxx.taxeasy.domains;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by xlm on 2017/8/29.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Request")
public class ReturnData3 {
    private  OperationItem3 OperationItem;

    private  InvoiceItems3  InvoiceItems;

    public OperationItem3 getOperationItem() {
        return OperationItem;
    }

    public void setOperationItem(OperationItem3 operationItem) {
        OperationItem = operationItem;
    }

    public InvoiceItems3 getInvoiceItems() {
        return InvoiceItems;
    }

    public void setInvoiceItems(InvoiceItems3 invoiceItems) {
        InvoiceItems = invoiceItems;
    }
}
