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
@XmlRootElement(name = "returndata")
public class Returndata {
    private String  dqfpdm;
    private String  dqfphm;
    private Integer  zsyfs;
    private Lgxx lgxx;

    public String getDqfpdm() {
        return dqfpdm;
    }

    public void setDqfpdm(String dqfpdm) {
        this.dqfpdm = dqfpdm;
    }

    public String getDqfphm() {
        return dqfphm;
    }

    public void setDqfphm(String dqfphm) {
        this.dqfphm = dqfphm;
    }

    public Integer getZsyfs() {
        return zsyfs;
    }

    public void setZsyfs(Integer zsyfs) {
        this.zsyfs = zsyfs;
    }

    public Lgxx getLgxx() {
        return lgxx;
    }

    public void setLgxx(Lgxx lgxx) {
        this.lgxx = lgxx;
    }
}
