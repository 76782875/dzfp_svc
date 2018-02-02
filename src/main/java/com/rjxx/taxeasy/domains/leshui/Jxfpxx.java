package com.rjxx.taxeasy.domains.leshui;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "t_jxfpxx")
public class Jxfpxx {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer fplsh;
  private String uniqueid;
  private String batchid;
  private String fpdm;
  private String fphm;
  private Date kprq;
  private String fpzldm;
  private String fpzt;
  private String xfsh;
  private String xfmc;
  private String xfdzdh;
  private String xfyhyhzh;
  private String gfsh;
  private String gfmc;
  private String gfdzdh;
  private String gfyhyhzh;
  private BigDecimal jshj;
  private BigDecimal hjje;
  private BigDecimal hjse;
  private String jqm;
  private String bz;
  private String qdbz;//暂无
  private String gxbz = "0";//勾选
  private String rzbz = "0";
  private String rzlx;
  private Date rzsj;
  private Date lrsj;
  private Date gxsj;
  private Integer lrry;
  private Date xgsj;
  private Integer xgry;
  private String yxbz;
  private String gsdm;
  private String jym;
  private String sfsp;
  private String rzzt;

  public String getRzzt() {
    return rzzt;
  }

  public void setRzzt(String rzzt) {
    this.rzzt = rzzt;
  }

  public String getSfsp() {
    return sfsp;
  }

  public void setSfsp(String sfsp) {
    this.sfsp = sfsp;
  }

  public String getJym() {
    return jym;
  }

  public void setJym(String jym) {
    this.jym = jym;
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

  public Integer getFplsh() {
    return fplsh;
  }

  public void setFplsh(Integer fplsh) {
    this.fplsh = fplsh;
  }

  public String getUniqueid() {
    return uniqueid;
  }

  public void setUniqueid(String uniqueid) {
    this.uniqueid = uniqueid;
  }

  public String getBatchid() {
    return batchid;
  }

  public void setBatchid(String batchid) {
    this.batchid = batchid;
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

  public String getGxbz() {
    return gxbz;
  }

  public void setGxbz(String gxbz) {
    this.gxbz = gxbz;
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


  public Integer getLrry() {
    return lrry;
  }

  public void setLrry(Integer lrry) {
    this.lrry = lrry;
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

  public Date getKprq() {
    return kprq;
  }

  public void setKprq(Date kprq) {
    this.kprq = kprq;
  }

  public Date getRzsj() {
    return rzsj;
  }

  public void setRzsj(Date rzsj) {
    this.rzsj = rzsj;
  }

  public Date getLrsj() {
    return lrsj;
  }

  public void setLrsj(Date lrsj) {
    this.lrsj = lrsj;
  }

  public Date getGxsj() {
    return gxsj;
  }

  public void setGxsj(Date gxsj) {
    this.gxsj = gxsj;
  }

  public Date getXgsj() {
    return xgsj;
  }

  public void setXgsj(Date xgsj) {
    this.xgsj = xgsj;
  }
}
