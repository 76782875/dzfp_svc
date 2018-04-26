package com.rjxx.taxeasy.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/3/20
 */
public class AdapterDataSeller {
    @JSONField(ordinal=1)
    private String identifier;
    @JSONField(ordinal=2)
    private String name;
    @JSONField(ordinal=3)
    private String address;
    @JSONField(ordinal=4)
    private String telephoneNo;
    @JSONField(ordinal=5)
    private String bank;
    @JSONField(ordinal=6)
    private String bankAcc;

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
}
