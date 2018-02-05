package com.rjxx.taxeasy.domains.leshui;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name="t_jxhd_jl")
public class Jxhdjl {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Integer fplsh;
  private Integer dyid;
  private String rtncode;
  private String rtnmsg;
  private String resultcode;
  private String resultmsg;
  private Integer totalsum;
  private Integer pagesize;
  private Integer pageno;
  private Date lrsj;
  private String gsdm;
  private String fpzt;
  private String rzbz;
  private String rzlx;
  private Date rzsj;
  private String uniqueid;
  private String taxcode;
    private String batchid;
  private String rzmsg;

  public String getUniqueid() {
    return uniqueid;
  }

  public void setUniqueid(String uniqueid) {
    this.uniqueid = uniqueid;
  }

  public String getTaxcode() {
    return taxcode;
  }

  public void setTaxcode(String taxcode) {
    this.taxcode = taxcode;
  }

  public String getBatchid() {
    return batchid;
  }

  public void setBatchid(String batchid) {
    this.batchid = batchid;
  }

  public String getRzmsg() {
    return rzmsg;
  }

  public void setRzmsg(String rzmsg) {
    this.rzmsg = rzmsg;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getFplsh() {
    return fplsh;
  }

  public void setFplsh(Integer fplsh) {
    this.fplsh = fplsh;
  }

  public Integer getDyid() {
    return dyid;
  }

  public void setDyid(Integer dyid) {
    this.dyid = dyid;
  }

  public String getRtncode() {
    return rtncode;
  }

  public void setRtncode(String rtncode) {
    this.rtncode = rtncode;
  }

  public String getRtnmsg() {
    return rtnmsg;
  }

  public void setRtnmsg(String rtnmsg) {
    this.rtnmsg = rtnmsg;
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

  public Integer getTotalsum() {
    return totalsum;
  }

  public void setTotalsum(Integer totalsum) {
    this.totalsum = totalsum;
  }

  public Integer getPagesize() {
    return pagesize;
  }

  public void setPagesize(Integer pagesize) {
    this.pagesize = pagesize;
  }

  public Integer getPageno() {
    return pageno;
  }

  public void setPageno(Integer pageno) {
    this.pageno = pageno;
  }

  public Date getLrsj() {
    return lrsj;
  }

  public void setLrsj(Date lrsj) {
    this.lrsj = lrsj;
  }

  public String getGsdm() {
    return gsdm;
  }

  public void setGsdm(String gsdm) {
    this.gsdm = gsdm;
  }

  public String getFpzt() {
    return fpzt;
  }

  public void setFpzt(String fpzt) {
    this.fpzt = fpzt;
  }

  public String getRzbz() {
    return rzbz;
  }

  public void setRzbz(String rzbz) {
    this.rzbz = rzbz;
  }

  public String getRzlx() {
    return rzlx;
  }

  public void setRzlx(String rzlx) {
    this.rzlx = rzlx;
  }

  public Date getRzsj() {
    return rzsj;
  }

  public void setRzsj(Date rzsj) {
    this.rzsj = rzsj;
  }
}
