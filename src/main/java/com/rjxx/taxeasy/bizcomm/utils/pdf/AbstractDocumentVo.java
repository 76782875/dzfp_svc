package com.rjxx.taxeasy.bizcomm.utils.pdf;

import com.rjxx.utils.JacksonBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * 模板中需要的数据视图 抽象类
 * Created by Him on 2015-09-22.
 */
public abstract class AbstractDocumentVo implements DocumentVo {
    /**
     * 填充模板中数据,获取模板数据map
     * @return
     */
    @Override
    public Map<String, Object> fillDataMap() {
        Map<String, Object> map = new HashMap<String, Object>();

        DocumentVo vo = this.getDocumentVo();
        map = JacksonBinder.buildNonDefaultBinder().convertValue(vo, HashMap.class);

        return map;
    }
    private DocumentVo getDocumentVo() {
        return this;
    }
}
