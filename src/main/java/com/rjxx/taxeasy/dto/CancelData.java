package com.rjxx.taxeasy.dto;

/**
 * @author: zsq
 * @date: 2018/3/27 17:01
 * @describe: 待退货数据
 */
public class CancelData {

    private String oderNo;
    private double totalAmount;

    public String getOderNo() {
        return oderNo;
    }

    public void setOderNo(String oderNo) {
        this.oderNo = oderNo;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
