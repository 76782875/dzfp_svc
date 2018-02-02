package com.rjxx.taxeasy.domains.leshui;

import java.util.Date;

public class SvatInvoices{
    private String invoiceCode;
    private String invoiceNo;
    private String type;
    private String status;
    private String amount;
    private String taxAmount;
    private String totalAmount;
    private String salerCompany;
    private String salerCode;
    private String salerAddress;
    private String salerBankAccount;
    private String buyerCompany;
    private String buyerCode;
    private String buyerAddress;
    private String createDate;
    private String verifyCode;
    private String invoicesStatus;
    private String machineCode;
    private String isAuth;
    private Date authTime;
    private String remark;
    private String authType;
    private SvatGoods[] goods;

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSalerCompany() {
        return salerCompany;
    }

    public void setSalerCompany(String salerCompany) {
        this.salerCompany = salerCompany;
    }

    public String getSalerCode() {
        return salerCode;
    }

    public void setSalerCode(String salerCode) {
        this.salerCode = salerCode;
    }

    public String getSalerAddress() {
        return salerAddress;
    }

    public void setSalerAddress(String salerAddress) {
        this.salerAddress = salerAddress;
    }

    public String getSalerBankAccount() {
        return salerBankAccount;
    }

    public void setSalerBankAccount(String salerBankAccount) {
        this.salerBankAccount = salerBankAccount;
    }

    public String getBuyerCompany() {
        return buyerCompany;
    }

    public void setBuyerCompany(String buyerCompany) {
        this.buyerCompany = buyerCompany;
    }

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getInvoicesStatus() {
        return invoicesStatus;
    }

    public void setInvoicesStatus(String invoicesStatus) {
        this.invoicesStatus = invoicesStatus;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(String isAuth) {
        this.isAuth = isAuth;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public SvatGoods[] getGoods() {
        return goods;
    }

    public void setGoods(SvatGoods[] goods) {
        this.goods = goods;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }
}
