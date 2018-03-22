package com.rjxx.taxeasy.dto;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/3/22
 */
public class AdapterDataOrderPayments {

    private String payCode;
    private String payPrice;

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(String payPrice) {
        this.payPrice = payPrice;
    }

    @Override
    public String toString() {
        return "AdapterDataOrderPayments{" +
                "payCode='" + payCode + '\'' +
                ", payPrice='" + payPrice + '\'' +
                '}';
    }
}
