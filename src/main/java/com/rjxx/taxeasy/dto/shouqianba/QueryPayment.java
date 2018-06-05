package com.rjxx.taxeasy.dto.shouqianba;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/6/4
 */
public class QueryPayment {
    private String type;
    private String amount_total;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount_total() {
        return amount_total;
    }

    public void setAmount_total(String amount_total) {
        this.amount_total = amount_total;
    }
}
