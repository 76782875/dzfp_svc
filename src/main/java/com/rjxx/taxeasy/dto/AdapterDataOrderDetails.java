package com.rjxx.taxeasy.dto;

import java.math.BigDecimal;

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
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal amount;
    private BigDecimal deductAmount;
    private Double taxRate;
    private BigDecimal taxAmount;
    private BigDecimal mxTotalAmount;
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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDeductAmount() {
        return deductAmount;
    }

    public void setDeductAmount(BigDecimal deductAmount) {
        this.deductAmount = deductAmount;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getMxTotalAmount() {
        return mxTotalAmount;
    }

    public void setMxTotalAmount(BigDecimal mxTotalAmount) {
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

    @Override
    public String toString() {
        return "AdapterDataOrderDetails{" +
                "venderOwnCode='" + venderOwnCode + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", rowType='" + rowType + '\'' +
                ", spec='" + spec + '\'' +
                ", util='" + util + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", amount=" + amount +
                ", deductAmount=" + deductAmount +
                ", taxRate=" + taxRate +
                ", taxAmount=" + taxAmount +
                ", mxTotalAmount=" + mxTotalAmount +
                ", policyMark='" + policyMark + '\'' +
                ", taxRateMark='" + taxRateMark + '\'' +
                ", policyName='" + policyName + '\'' +
                '}';
    }
}
