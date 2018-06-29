package com.rjxx.taxeasy.dto;

import java.util.List;

/**
 * @author kzx
 * @company 上海容津信息技术有限公司
 * @date 2018/3/20
 */
public class SellerData {
    private String identifier;
    private String yidentifier;
    private String name;
    private String address;
    private String telephoneNo;
    private String bank;
    private String bankAcc;
    private String ybnsrqssj;
    private String ybnsrlx;
    private String drawer;
    private String payee;
    private String reviewer;
    private String type;
    private String issueType;
    private double eticketLim;
    private double specialticketLim;
    private double ordinaryticketLim;
    private double rollticketLim;
    private List<ClientData> client;

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

    public String getYbnsrqssj() {
        return ybnsrqssj;
    }

    public void setYbnsrqssj(String ybnsrqssj) {
        this.ybnsrqssj = ybnsrqssj;
    }

    public String getYbnsrlx() {
        return ybnsrlx;
    }

    public void setYbnsrlx(String ybnsrlx) {
        this.ybnsrlx = ybnsrlx;
    }

    public String getDrawer() {
        return drawer;
    }

    public void setDrawer(String drawer) {
        this.drawer = drawer;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public double getEticketLim() {
        return eticketLim;
    }

    public void setEticketLim(double eticketLim) {
        this.eticketLim = eticketLim;
    }

    public double getSpecialticketLim() {
        return specialticketLim;
    }

    public void setSpecialticketLim(double specialticketLim) {
        this.specialticketLim = specialticketLim;
    }

    public double getOrdinaryticketLim() {
        return ordinaryticketLim;
    }

    public void setOrdinaryticketLim(double ordinaryticketLim) {
        this.ordinaryticketLim = ordinaryticketLim;
    }

    public List<ClientData> getClient() {
        return client;
    }

    public void setClient(List<ClientData> client) {
        this.client = client;
    }

    public String getYidentifier() {
        return yidentifier;
    }

    public void setYidentifier(String yidentifier) {
        this.yidentifier = yidentifier;
    }

    public double getRollticketLim() {
        return rollticketLim;
    }

    public void setRollticketLim(double rollticketLim) {
        this.rollticketLim = rollticketLim;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
