package com.rjxx.taxeasy.domains.shouqianba;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "pay_record")
public class PayRecord {

  @Id
  @GeneratedValue
  private Integer id;
  private String orderNo;
  private String clientSn;
  private String sn;
  private String tradeNo;
  private String totalAmount;
  private String isSuccess;
  private String qResultCode;
  private String status;
  private String qOrderStatus;
  private String errorCode;
  private String errorMessage;
  private String gsdm;
  private Date lrsj;
  private String reqType;
  private String terminalSn;
  private String terminalKey;
  private String storeNo;

  public String getStoreNo() {
    return storeNo;
  }

  public void setStoreNo(String storeNo) {
    this.storeNo = storeNo;
  }

  public String getTerminalSn() {
    return terminalSn;
  }

  public void setTerminalSn(String terminalSn) {
    this.terminalSn = terminalSn;
  }

  public String getTerminalKey() {
    return terminalKey;
  }

  public void setTerminalKey(String terminalKey) {
    this.terminalKey = terminalKey;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public String getClientSn() {
    return clientSn;
  }

  public void setClientSn(String clientSn) {
    this.clientSn = clientSn;
  }

  public String getSn() {
    return sn;
  }

  public void setSn(String sn) {
    this.sn = sn;
  }

  public String getTradeNo() {
    return tradeNo;
  }

  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
  }

  public String getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(String totalAmount) {
    this.totalAmount = totalAmount;
  }

  public String getIsSuccess() {
    return isSuccess;
  }

  public void setIsSuccess(String isSuccess) {
    this.isSuccess = isSuccess;
  }

  public String getqResultCode() {
    return qResultCode;
  }

  public void setqResultCode(String qResultCode) {
    this.qResultCode = qResultCode;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getqOrderStatus() {
    return qOrderStatus;
  }

  public void setqOrderStatus(String qOrderStatus) {
    this.qOrderStatus = qOrderStatus;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
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

  public String getReqType() {
    return reqType;
  }

  public void setReqType(String reqType) {
    this.reqType = reqType;
  }
}
