package com.rjxx.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/3/26
 */
public class StringUtil {
    public static boolean isNotBlankList(String... args){
        for (int i=0;i<args.length;i++){
            boolean notBlank = StringUtils.isNotBlank(args[i]);
            if(!notBlank){
                return false;
            }
        }
        return true;
    }
}
