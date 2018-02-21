package com.rjxx.taxeasy.domains.leshui;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by wangyahui on 2018/1/25 0025
 */
@Entity(name = "t_fpcy_jl")
public class Fpcyjl {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Integer fpcyid;
  private Date cyrq;
  private Integer cycs;
  private String fpzt;
  private String lrry;
  private String bxry;
  private String gsdm;
  private String resultcode;
  private String resultmsg;
  private String returncode;
  private String falsecode;
  private String invoicename;
  private Date lrsj;
  private String invoicecode;
  private String invoicenumber;
  private Date billtime;
  private String checkcode;
    private String invoiceamount;
  private String sjly;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getFpcyid() {
    return fpcyid;
  }

  public void setFpcyid(Integer fpcyid) {
    this.fpcyid = fpcyid;
  }

  public Date getCyrq() {
    return cyrq;
  }

  public void setCyrq(Date cyrq) {
    this.cyrq = cyrq;
  }

  public Integer getCycs() {
    return cycs;
  }

  public void setCycs(Integer cycs) {
    this.cycs = cycs;
  }

  public String getFpzt() {
    return fpzt;
  }

  public void setFpzt(String fpzt) {
    this.fpzt = fpzt;
  }

  public String getLrry() {
    return lrry;
  }

  public void setLrry(String lrry) {
    this.lrry = lrry;
  }

  public String getBxry() {
    return bxry;
  }

  public void setBxry(String bxry) {
    this.bxry = bxry;
  }

  public String getGsdm() {
    return gsdm;
  }

  public void setGsdm(String gsdm) {
    this.gsdm = gsdm;
  }

  public String getResultcode() {
    return resultcode;
  }

  public void setResultcode(String resultcode) {
    this.resultcode = resultcode;
  }

  public String getResultmsg() {
    return resultmsg;
  }

  public void setResultmsg(String resultmsg) {
    this.resultmsg = resultmsg;
  }

  public String getReturncode() {
    return returncode;
  }

  public void setReturncode(String returncode) {
    this.returncode = returncode;
  }

  public String getFalsecode() {
    return falsecode;
  }

  public void setFalsecode(String falsecode) {
    this.falsecode = falsecode;
  }

  public String getInvoicename() {
    return invoicename;
  }

  public void setInvoicename(String invoicename) {
    this.invoicename = invoicename;
  }

  public Date getLrsj() {
    return lrsj;
  }

  public void setLrsj(Date lrsj) {
    this.lrsj = lrsj;
  }

  public String getInvoicecode() {
    return invoicecode;
  }

  public void setInvoicecode(String invoicecode) {
    this.invoicecode = invoicecode;
  }

  public String getInvoicenumber() {
    return invoicenumber;
  }

  public void setInvoicenumber(String invoicenumber) {
    this.invoicenumber = invoicenumber;
  }

  public Date getBilltime() {
    return billtime;
  }

  public void setBilltime(Date billtime) {
    this.billtime = billtime;
  }

  public String getCheckcode() {
    return checkcode;
  }

  public void setCheckcode(String checkcode) {
    this.checkcode = checkcode;
  }

  public String getInvoiceamount() {
    return invoiceamount;
  }

  public void setInvoiceamount(String invoiceamount) {
    this.invoiceamount = invoiceamount;
  }

  public String getSjly() {
    return sjly;
  }

  public void setSjly(String sjly) {
    this.sjly = sjly;
  }
}
