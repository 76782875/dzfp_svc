package com.rjxx.taxeasy.domains.leshui;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * Created by wangyahui on 2018/1/25 0025
 */
@Entity(name = "t_fpcy_jl")
public class Fpcyjl {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Integer fpcyid;
  private String cyrq;
  private Integer cycs;
  private String fpzt;
  private String lrry;
  private String bxry;
  private String gsdm;

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
}
