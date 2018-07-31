package com.rjxx.utils;

import java.util.Random;

/**
 * Created by Administrator on 2017/8/21 0021.
 */
public class NumberUtil {

    public static char getRandomLetter(){
        String chars = "abcdefghijklmnopqrstuvwxyz";
        return chars.charAt(new Random().nextInt(26));
    }
    public static String fplxdm(String fpzldm){
        String fplxdm="";
        //增值税专用发票
        if("01".equals(fpzldm)){
            fplxdm="004";
        }
        //增值税普通发票
        else if("02".equals(fpzldm)){
            fplxdm="007";
        }
        //增值税普通电子发票
        else if("12".equals(fpzldm)){
            fplxdm="026";
        }
        else {
            fplxdm = fpzldm;
        }
        return fplxdm;
    }

    public static void main(String[] args) {
        String s ="预缴金额:2000 补缴金额:0 个人支付:57.86 个人 帐户支付:500 医保预缴支付:0 附加支付:0 现金支付中:分类自负 :0 自负:12.86 当年帐户余额:484 历年帐户余额:100";
        System.out.println(s.length());
    }
}
