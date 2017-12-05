package com.rjxx.taxeasy.invoice;

import com.rjxx.utils.XmlJaxbUtils;

/**
 * Created by wangyahui on 2017/12/5 0005
 */
public class ResponeseUtils {

    public static String error(String message) {
        DefaultResult defaultResult = new DefaultResult();
        defaultResult.setReturnCode("9999");
        defaultResult.setReturnMessage(message);
        return XmlJaxbUtils.toXml(defaultResult);
    }

    public static String success(String message) {
        DefaultResult defaultResult = new DefaultResult();
        defaultResult.setReturnCode("0000");
        defaultResult.setReturnMessage("成功");
        return XmlJaxbUtils.toXml(defaultResult);
    }
}
