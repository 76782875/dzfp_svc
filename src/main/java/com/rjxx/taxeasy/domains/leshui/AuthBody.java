package com.rjxx.taxeasy.domains.leshui;

import java.util.Date;

public class AuthBody{
    private String invoiceCode;
    private String invoiceNo;
    private String status;
    private Date authorizeTime;
    private String message;

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Date getAuthorizeTime() {
        return authorizeTime;
    }

    public void setAuthorizeTime(Date authorizeTime) {
        this.authorizeTime = authorizeTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
