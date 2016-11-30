package com.rjxx.taxeasy.bizcomm.utils;



import java.util.List;

import com.rjxx.taxeasy.domains.Jyspmx;

/**
 * Created by guoyan on 2016/1/10.
 */
public class SeparateBean {
    private List<Jyspmx> t_jyspmxList;
    private Double total;
    private Double totalAmount;
    private Double totalTaxAmount;

    public List<Jyspmx> getT_jyspmxList() {
        return t_jyspmxList;
    }

    public void setT_jyspmxList(List<Jyspmx> t_jyspmxList) {
        this.t_jyspmxList = t_jyspmxList;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(Double totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }
}
