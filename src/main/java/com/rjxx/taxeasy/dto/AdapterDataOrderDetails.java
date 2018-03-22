package com.rjxx.taxeasy.dto;

import lombok.Data;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/3/20
 */
@Data
public class AdapterDataOrderDetails{
    private String venderOwnCode;
    private String productCode;
    private String productName;
    private String rowType;
    private String spec;
    private String util;
    private Double quantity;
    private Double unitPrice;
    private Double amount;
    private Double deductAmount;
    private Double taxRate;
    private Double taxAmount;
    private Double mxTotalAmount;
    private String policyMark;
    private String taxRateMark;
    private String policyName;
}
