package com.rjxx.taxeasy.domains.leshui;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
@Entity(name = "t_jxfpmx")
public class Jxfpmx {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Integer fplsh;
  private Integer spmxxh;
  private String spmc;
  private String spdw;
  private String spggxh;
  private BigDecimal sps;
  private BigDecimal spdj;
  private BigDecimal spje;
  private BigDecimal spsl;
  private BigDecimal spse;
  private String hh;

  public String getHh() {
    return hh;
  }

  public void setHh(String hh) {
    this.hh = hh;
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

  public Integer getSpmxxh() {
    return spmxxh;
  }

  public void setSpmxxh(Integer spmxxh) {
    this.spmxxh = spmxxh;
  }

  public String getSpmc() {
    return spmc;
  }

  public void setSpmc(String spmc) {
    this.spmc = spmc;
  }

  public String getSpdw() {
    return spdw;
  }

  public void setSpdw(String spdw) {
    this.spdw = spdw;
  }

  public String getSpggxh() {
    return spggxh;
  }

  public void setSpggxh(String spggxh) {
    this.spggxh = spggxh;
  }

  public BigDecimal getSps() {
    return sps;
  }

  public void setSps(BigDecimal sps) {
    this.sps = sps;
  }

  public BigDecimal getSpdj() {
    return spdj;
  }

  public void setSpdj(BigDecimal spdj) {
    this.spdj = spdj;
  }

  public BigDecimal getSpje() {
    return spje;
  }

  public void setSpje(BigDecimal spje) {
    this.spje = spje;
  }

  public BigDecimal getSpsl() {
    return spsl;
  }

  public void setSpsl(BigDecimal spsl) {
    this.spsl = spsl;
  }

  public BigDecimal getSpse() {
    return spse;
  }

  public void setSpse(BigDecimal spse) {
    this.spse = spse;
  }
}
