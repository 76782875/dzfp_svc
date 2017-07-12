package com.rjxx.taxeasy.domains;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by xlm on 2017/7/11.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "InvoiceItems")
public class InvoiceItems2 {

    @XmlAttribute
    private int count;

    private List<InvoiceItem2> InvoiceItem;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<InvoiceItem2> getInvoiceItem() {
        return InvoiceItem;
    }

    public void setInvoiceItem(List<InvoiceItem2> invoiceItem) {
        InvoiceItem = invoiceItem;
    }
}
