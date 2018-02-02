package com.rjxx.taxeasy.domains.leshui;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name="t_jxyw_jl")
public class Jxywjl {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Integer ywlx;
  private Date kssj;
  private Date jssj;
  private String gsdm;
  private Integer gfid;
  private String zt;
  private Date lrsj;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getYwlx() {
    return ywlx;
  }

  public void setYwlx(Integer ywlx) {
    this.ywlx = ywlx;
  }

  public Date getKssj() {
    return kssj;
  }

  public void setKssj(Date kssj) {
    this.kssj = kssj;
  }

  public Date getJssj() {
    return jssj;
  }

  public void setJssj(Date jssj) {
    this.jssj = jssj;
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

  public String getZt() {
    return zt;
  }

  public void setZt(String zt) {
    this.zt = zt;
  }

  public Date getLrsj() {
    return lrsj;
  }

  public void setLrsj(Date lrsj) {
    this.lrsj = lrsj;
  }
}
