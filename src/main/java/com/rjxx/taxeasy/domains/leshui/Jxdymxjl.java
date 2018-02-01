package com.rjxx.taxeasy.domains.leshui;

import javax.persistence.Entity;

@Entity(name="t_jxdymx_jl")
public class Jxdymxjl {

  private Integer id;
  private String invoicecode;
  private String invoceno;
  private Integer dyid;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getInvoicecode() {
    return invoicecode;
  }

  public void setInvoicecode(String invoicecode) {
    this.invoicecode = invoicecode;
  }

  public String getInvoceno() {
    return invoceno;
  }

  public void setInvoceno(String invoceno) {
    this.invoceno = invoceno;
  }

  public Integer getDyid() {
    return dyid;
  }

  public void setDyid(Integer dyid) {
    this.dyid = dyid;
  }
}
