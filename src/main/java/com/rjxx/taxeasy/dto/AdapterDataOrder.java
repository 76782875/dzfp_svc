package com.rjxx.taxeasy.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/3/20
 */
@Data
public class AdapterDataOrder {
    private String orderNo;
    private String invoiceList;
    private String invoiceSplit;
    private String invoiceSfdy;
    private Date orderDate;
    private String chargeTaxWay;
    private Double totalAmount;
    private String taxMark;
    private String remark;
    private String extractedCode;
    private Double totalDiscount;
    private AdapterDataOrderBuyer buyer;
    private List<AdapterDataOrderPayments> payments;
    private List<AdapterDataOrderDetails> orderDetails;
}
