package com.rjxx.taxeasy.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author kzx
 * @company 上海容津信息技术有限公司
 * @date 2018/4/27
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "business")
public class Fplgxx {
    private FplgxxBody body;

    public FplgxxBody getBody() {
        return body;
    }

    public void setBody(FplgxxBody body) {
        this.body = body;
    }
}
