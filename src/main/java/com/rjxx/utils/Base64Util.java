package com.rjxx.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created by wangyahui on 2018/1/16 0016.
 */
public class Base64Util {


    public static String encode(String input) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(input.getBytes("utf-8"));
    }

    public static String decode(String input) throws UnsupportedEncodingException {
        byte[] bytes = Base64.getDecoder().decode(input);
        return new String(bytes,"utf-8");
    }
}
