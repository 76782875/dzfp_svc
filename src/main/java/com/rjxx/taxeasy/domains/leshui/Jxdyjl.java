package com.rjxx.taxeasy.domains.leshui;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "t_jxdy_jl")
public class Jxdyjl {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Integer ywid;
  private Integer dyxh;
  private String zt;
  private String gsdm;
  private String uniqueid;
  private String invoicecode;
  private String invoiceno;
  private String taxcode;
  private Date starttime;
  private Date endtime;
  private Integer pageno;
  private String batchid;
  private Integer mxbz;
  private Date lrsj;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getYwid() {
    return ywid;
  }

  public void setYwid(Integer ywid) {
    this.ywid = ywid;
  }

  public Integer getDyxh() {
    return dyxh;
  }

  public void setDyxh(Integer dyxh) {
    this.dyxh = dyxh;
  }

  public String getZt() {
    return zt;
  }

  public void setZt(String zt) {
    this.zt = zt;
  }

  public String getGsdm() {
    return gsdm;
  }

  public void setGsdm(String gsdm) {
    this.gsdm = gsdm;
  }

  public String getUniqueid() {
    return uniqueid;
  }

  public void setUniqueid(String uniqueid) {
    this.uniqueid = uniqueid;
  }

  public String getInvoicecode() {
    return invoicecode;
  }

  public void setInvoicecode(String invoicecode) {
    this.invoicecode = invoicecode;
  }

  public String getInvoiceno() {
    return invoiceno;
  }

  public void setInvoiceno(String invoiceno) {
    this.invoiceno = invoiceno;
  }

  public String getTaxcode() {
    return taxcode;
  }

  public void setTaxcode(String taxcode) {
    this.taxcode = taxcode;
  }

  public Date getStarttime() {
    return starttime;
  }

  public void setStarttime(Date starttime) {
    this.starttime = starttime;
  }

  public Date getEndtime() {
    return endtime;
  }

  public void setEndtime(Date endtime) {
    this.endtime = endtime;
  }

  public Integer getPageno() {
    return pageno;
  }

  public void setPageno(Integer pageno) {
    this.pageno = pageno;
  }

  public String getBatchid() {
    return batchid;
  }

  public void setBatchid(String batchid) {
    this.batchid = batchid;
  }

  public Integer getMxbz() {
    return mxbz;
  }

  public void setMxbz(Integer mxbz) {
    this.mxbz = mxbz;
  }

  public Date getLrsj() {
    return lrsj;
  }

  public void setLrsj(Date lrsj) {
    this.lrsj = lrsj;
  }
}
