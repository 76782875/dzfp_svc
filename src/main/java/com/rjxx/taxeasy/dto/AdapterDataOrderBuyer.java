package com.rjxx.taxeasy.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/3/20
 */
public class AdapterDataOrderBuyer {
    @JSONField(ordinal=1)
    private String name;
    @JSONField(ordinal=2)
    private String identifier;
    @JSONField(ordinal=3)
    private String address;
    @JSONField(ordinal=4)
    private String telephoneNo;
    @JSONField(ordinal=5)
    private String bank;
    @JSONField(ordinal=6)
    private String bankAcc;
    @JSONField(ordinal=7)
    private String email;
    @JSONField(ordinal=8)
    private String customerType;
    @JSONField(ordinal=9)
    private String isSend;
    @JSONField(ordinal=10)
    private String recipient;
    @JSONField(ordinal=11)
    private String reciAddress;
    @JSONField(ordinal=12)
    private String zip;
    @JSONField(ordinal=13)
    private String memberId;//客户号

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankAcc() {
        return bankAcc;
    }

    public void setBankAcc(String bankAcc) {
        this.bankAcc = bankAcc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getReciAddress() {
        return reciAddress;
    }

    public void setReciAddress(String reciAddress) {
        this.reciAddress = reciAddress;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
