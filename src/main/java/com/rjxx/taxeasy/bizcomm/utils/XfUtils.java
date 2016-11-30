package com.rjxx.taxeasy.bizcomm.utils;

import com.rjxx.taxeasy.domains.Xf;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9.
 */
public class XfUtils {

    /**
     * 是否包含该税号,存在返回改Xf对象
     *
     * @param xfList
     * @param sh
     * @return
     */
    public static Xf containsSh(List<Xf> xfList, String sh) {
        for (Xf xf : xfList) {
            if (sh.equals(xf.getXfsh())) {
                return xf;
            }
        }
        return null;
    }

}
