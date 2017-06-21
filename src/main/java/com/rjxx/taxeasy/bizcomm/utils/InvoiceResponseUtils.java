package com.rjxx.taxeasy.bizcomm.utils;

/**
 * Created by Administrator on 2017-02-16.
 */
public class InvoiceResponseUtils {

    public static InvoiceResponse responseError(String errorMessage){
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        invoiceResponse.setReturnCode("9999");
        invoiceResponse.setReturnMessage(errorMessage);
        return invoiceResponse;
    }

    public static InvoiceResponse responseSuccess(String message){
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        invoiceResponse.setReturnCode("0000");
        invoiceResponse.setReturnMessage(message);
        return invoiceResponse;
    }

    public static InvoiceResponse responseSuccess(){
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        invoiceResponse.setReturnCode("9999");
        invoiceResponse.setReturnMessage("成功");
        return invoiceResponse;
    }
}
