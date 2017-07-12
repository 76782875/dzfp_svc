package com.rjxx.taxeasy.domains;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by xlm on 2017/7/11.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Request")
public class RetrunData2 {

    private  String ExtractCode;

    private  InvoiceItems2  InvoiceItems;

    public String getExtractCode() {
        return ExtractCode;
    }

    public void setExtractCode(String extractCode) {
        ExtractCode = extractCode;
    }

    public InvoiceItems2 getInvoiceItems() {
        return InvoiceItems;
    }

    public void setInvoiceItems(InvoiceItems2 invoiceItems) {
        InvoiceItems = invoiceItems;
    }
}
