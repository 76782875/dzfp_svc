package com.rjxx.taxeasy.dto;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/3/20
 */
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

    public String getVenderOwnCode() {
        return venderOwnCode;
    }

    public void setVenderOwnCode(String venderOwnCode) {
        this.venderOwnCode = venderOwnCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRowType() {
        return rowType;
    }

    public void setRowType(String rowType) {
        this.rowType = rowType;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getUtil() {
        return util;
    }

    public void setUtil(String util) {
        this.util = util;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getDeductAmount() {
        return deductAmount;
    }

    public void setDeductAmount(Double deductAmount) {
        this.deductAmount = deductAmount;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getMxTotalAmount() {
        return mxTotalAmount;
    }

    public void setMxTotalAmount(Double mxTotalAmount) {
        this.mxTotalAmount = mxTotalAmount;
    }

    public String getPolicyMark() {
        return policyMark;
    }

    public void setPolicyMark(String policyMark) {
        this.policyMark = policyMark;
    }

    public String getTaxRateMark() {
        return taxRateMark;
    }

    public void setTaxRateMark(String taxRateMark) {
        this.taxRateMark = taxRateMark;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }
}
