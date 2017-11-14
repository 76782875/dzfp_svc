package com.rjxx.utils.yjapi;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017-11-14.
 */
@Component
public class QCCConstants {

    //企查查 ApiKey
    public static String ApiKey = "f2c9da96b3fa45f6bce3637e2a3e6c74";
    //企查查 获取纳税人识别号
    public static final String GET_QCC_GSXX = "http://i.yjapi.com/ECICreditCode/GetCreditCode";
    //企查查 获取纳税人识别号(包含账户信息)
    public static final String GET_QCC_GSXX_NEW = "http://i.yjapi.com/ECICreditCode/GetCreditCodeNew";
}
