package com.rjxx.utils.alipay;

import com.alipay.api.AlipayObject;

import java.util.List;

/**
 * Created by ZhangBing on 2017-06-26.
 */
public class AlipayBizObject extends AlipayObject {

    private String m_short_name;

    private String sub_m_short_name;

    private List<InvoiceInfo> invoice_info;

    private String apply_id;

    public String getM_short_name() {
        return m_short_name;
    }

    public void setM_short_name(String m_short_name) {
        this.m_short_name = m_short_name;
    }

    public String getSub_m_short_name() {
        return sub_m_short_name;
    }

    public void setSub_m_short_name(String sub_m_short_name) {
        this.sub_m_short_name = sub_m_short_name;
    }

    public List<InvoiceInfo> getInvoice_info() {
        return invoice_info;
    }

    public void setInvoice_info(List<InvoiceInfo> invoice_info) {
        this.invoice_info = invoice_info;
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }
}
