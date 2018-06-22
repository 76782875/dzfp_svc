package com.rjxx.taxeasy.domains.shouqianba;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "pay_payments")
public class PayPayments {

  @Id
  @GeneratedValue
  private Integer id;
  private Integer outId;
  private String type;
  private String amountTotal;
  private String gsdm;
  private Date lrsj;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getOutId() {
    return outId;
  }

  public void setOutId(Integer outId) {
    this.outId = outId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getAmountTotal() {
    return amountTotal;
  }

  public void setAmountTotal(String amountTotal) {
    this.amountTotal = amountTotal;
  }

  public String getGsdm() {
    return gsdm;
  }

  public void setGsdm(String gsdm) {
    this.gsdm = gsdm;
  }

  public Date getLrsj() {
    return lrsj;
  }

  public void setLrsj(Date lrsj) {
    this.lrsj = lrsj;
  }
}
