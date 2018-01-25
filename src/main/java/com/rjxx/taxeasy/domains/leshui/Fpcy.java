package com.rjxx.taxeasy.domains.leshui;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
/**
 * Created by wangyahui on 2018/1/25 0025
 */
@Entity(name = "t_fpcy")
public class Fpcy {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String fpdm;
  private String fphm;
  private String kprq;
  private String jym;
  private String cyrq;
  private Integer cycs;
  private String resultcode;
  private String returncode;
  private String fpzldm;
  private String xfsh;
  private String xfmc;
  private String xfdzdh;
  private String xfyhyhzh;
  private String gsdm;
  private Integer gfid;
  private String gfsh;
  private String gfmc;
  private String gfdzdh;
  private String gfyhyhzh;
  private BigDecimal jshj;
  private BigDecimal hjje;
  private BigDecimal hjse;
  private String jqm;
  private String bz;
  private String qdbz;
  private String fpzt;
  private String sjly;
  private String lrsj;
  private Integer lrry;
  private String xgsj;
  private Integer xgry;
  private String yxbz;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFpdm() {
    return fpdm;
  }

  public void setFpdm(String fpdm) {
    this.fpdm = fpdm;
  }

  public String getFphm() {
    return fphm;
  }

  public void setFphm(String fphm) {
    this.fphm = fphm;
  }

  public String getKprq() {
    return kprq;
  }

  public void setKprq(String kprq) {
    this.kprq = kprq;
  }

  public String getJym() {
    return jym;
  }

  public void setJym(String jym) {
    this.jym = jym;
  }

  public String getCyrq() {
    return cyrq;
  }

  public void setCyrq(String cyrq) {
    this.cyrq = cyrq;
  }

  public Integer getCycs() {
    return cycs;
  }

  public void setCycs(Integer cycs) {
    this.cycs = cycs;
  }

  public String getResultcode() {
    return resultcode;
  }

  public void setResultcode(String resultcode) {
    this.resultcode = resultcode;
  }

  public String getReturncode() {
    return returncode;
  }

  public void setReturncode(String returncode) {
    this.returncode = returncode;
  }

  public String getFpzldm() {
    return fpzldm;
  }

  public void setFpzldm(String fpzldm) {
    this.fpzldm = fpzldm;
  }

  public String getXfsh() {
    return xfsh;
  }

  public void setXfsh(String xfsh) {
    this.xfsh = xfsh;
  }

  public String getXfmc() {
    return xfmc;
  }

  public void setXfmc(String xfmc) {
    this.xfmc = xfmc;
  }

  public String getXfdzdh() {
    return xfdzdh;
  }

  public void setXfdzdh(String xfdzdh) {
    this.xfdzdh = xfdzdh;
  }

  public String getXfyhyhzh() {
    return xfyhyhzh;
  }

  public void setXfyhyhzh(String xfyhyhzh) {
    this.xfyhyhzh = xfyhyhzh;
  }

  public String getGsdm() {
    return gsdm;
  }

  public void setGsdm(String gsdm) {
    this.gsdm = gsdm;
  }

  public Integer getGfid() {
    return gfid;
  }

  public void setGfid(Integer gfid) {
    this.gfid = gfid;
  }

  public String getGfsh() {
    return gfsh;
  }

  public void setGfsh(String gfsh) {
    this.gfsh = gfsh;
  }

  public String getGfmc() {
    return gfmc;
  }

  public void setGfmc(String gfmc) {
    this.gfmc = gfmc;
  }

  public String getGfdzdh() {
    return gfdzdh;
  }

  public void setGfdzdh(String gfdzdh) {
    this.gfdzdh = gfdzdh;
  }

  public String getGfyhyhzh() {
    return gfyhyhzh;
  }

  public void setGfyhyhzh(String gfyhyhzh) {
    this.gfyhyhzh = gfyhyhzh;
  }

  public BigDecimal getJshj() {
    return jshj;
  }

  public void setJshj(BigDecimal jshj) {
    this.jshj = jshj;
  }

  public BigDecimal getHjje() {
    return hjje;
  }

  public void setHjje(BigDecimal hjje) {
    this.hjje = hjje;
  }

  public BigDecimal getHjse() {
    return hjse;
  }

  public void setHjse(BigDecimal hjse) {
    this.hjse = hjse;
  }

  public String getJqm() {
    return jqm;
  }

  public void setJqm(String jqm) {
    this.jqm = jqm;
  }

  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public String getQdbz() {
    return qdbz;
  }

  public void setQdbz(String qdbz) {
    this.qdbz = qdbz;
  }

  public String getFpzt() {
    return fpzt;
  }

  public void setFpzt(String fpzt) {
    this.fpzt = fpzt;
  }

  public String getSjly() {
    return sjly;
  }

  public void setSjly(String sjly) {
    this.sjly = sjly;
  }

  public String getLrsj() {
    return lrsj;
  }

  public void setLrsj(String lrsj) {
    this.lrsj = lrsj;
  }

  public Integer getLrry() {
    return lrry;
  }

  public void setLrry(Integer lrry) {
    this.lrry = lrry;
  }

  public String getXgsj() {
    return xgsj;
  }

  public void setXgsj(String xgsj) {
    this.xgsj = xgsj;
  }

  public Integer getXgry() {
    return xgry;
  }

  public void setXgry(Integer xgry) {
    this.xgry = xgry;
  }

  public String getYxbz() {
    return yxbz;
  }

  public void setYxbz(String yxbz) {
    this.yxbz = yxbz;
  }
}
