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
}
