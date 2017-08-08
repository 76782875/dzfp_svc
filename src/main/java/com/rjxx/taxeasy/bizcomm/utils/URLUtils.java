package com.rjxx.taxeasy.bizcomm.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by xlm on 2017/8/8.
 */
@Component
public class URLUtils {

    public static String WS_URL;


    @Value("${ws_url}")
    public void setWsurl(String ws_url) {
        WS_URL = ws_url;
    }
}
