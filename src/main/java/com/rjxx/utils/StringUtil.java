package com.rjxx.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/3/26
 */
public class StringUtil {
    //全部不能为空
    public static boolean isNotBlankList(String... args){
        for (int i=0;i<args.length;i++){
            boolean notBlank = StringUtils.isNotBlank(args[i]);
            if(!notBlank){
                return false;
            }
        }
        return true;
    }

    //全部为空
    public static boolean isBlankList(String... args){
        for (int i=0;i<args.length;i++){
            boolean notBlank = StringUtils.isBlank(args[i]);
            if(!notBlank){
                return false;
            }
        }
        return true;
    }

    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是GB2312
                String s = encode;
                return s;      //是的话，返回“GB2312“，以下代码同理
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是ISO-8859-1
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {   //判断是不是UTF-8
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是GBK
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";        //如果都不是，说明输入的内容不属于常见的编码格式。
    }
}
