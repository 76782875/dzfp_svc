package com.rjxx.taxeasy.dto.alipay;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/5/15
 */
public class AlipayReceiveApplyDto {
    private String applyId;//支付宝开票申请id，该id为支付宝内的开票申请唯一标识，与订单号绑定
    private String userId;//支付宝用户id
    private String invoiceAmount;//开票金额
    private String orderNo;//订单号
    private String mShortName;//商户的品牌名称简称,与发票回传接口的商户名称保持一致
    private String subShortName;//支付宝为商户分配的商户门店简称，与发票回传接口的商户名称保持一致
    private String payerName;//抬头名称
    private String payerRegisterNo;//纳税人识别号
    private String payerAddress;//地址电话
    private String payerTelphone;//地址电话
    private String payerBankName;//银行、银行账号
    private String payerBankAccount;//银行、银行账号
    private String sign;//签名

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getmShortName() {
        return mShortName;
    }

    public void setmShortName(String mShortName) {
        this.mShortName = mShortName;
    }

    public String getSubShortName() {
        return subShortName;
    }

    public void setSubShortName(String subShortName) {
        this.subShortName = subShortName;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerRegisterNo() {
        return payerRegisterNo;
    }

    public void setPayerRegisterNo(String payerRegisterNo) {
        this.payerRegisterNo = payerRegisterNo;
    }

    public String getPayerAddress() {
        return payerAddress;
    }

    public void setPayerAddress(String payerAddress) {
        this.payerAddress = payerAddress;
    }

    public String getPayerTelphone() {
        return payerTelphone;
    }

    public void setPayerTelphone(String payerTelphone) {
        this.payerTelphone = payerTelphone;
    }

    public String getPayerBankName() {
        return payerBankName;
    }

    public void setPayerBankName(String payerBankName) {
        this.payerBankName = payerBankName;
    }

    public String getPayerBankAccount() {
        return payerBankAccount;
    }

    public void setPayerBankAccount(String payerBankAccount) {
        this.payerBankAccount = payerBankAccount;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
