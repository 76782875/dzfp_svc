package com.rjxx.taxeasy.bizcomm.utils.pdf;

import java.util.Map;

/**
 * Created by Him on 2015-09-22.
 */
public interface DocumentVo {
    /**
     * 获取主键,用于记录日志
     * @Title: findPrimaryKey
     * @Description: 获取主键,用于记录日志
     * @return
     * @author
     */
    public String findPrimaryKey();
    /**
     * 获取数据map
     * @Title: fillDataMap
     * @Description:
     * @return
     * @author
     */
    public Map<String, Object> fillDataMap();
}
