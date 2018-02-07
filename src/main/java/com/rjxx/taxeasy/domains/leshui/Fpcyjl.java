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

  public Date getCyrq() {
    return cyrq;
  }

  public void setCyrq(Date cyrq) {
    this.cyrq = cyrq;
  }
}
