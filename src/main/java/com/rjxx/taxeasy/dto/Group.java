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
@XmlRootElement(name = "group")
public class Group {
    public String fpdm;
    public String qshm;
    public String zzhm;
    public Integer fpfs;
    public Integer syfs;
    public String lgrq;
    public String lgry;

    public String getFpdm() {
        return fpdm;
    }

    public void setFpdm(String fpdm) {
        this.fpdm = fpdm;
    }

    public String getQshm() {
        return qshm;
    }

    public void setQshm(String qshm) {
        this.qshm = qshm;
    }

    public String getZzhm() {
        return zzhm;
    }

    public void setZzhm(String zzhm) {
        this.zzhm = zzhm;
    }

    public Integer getFpfs() {
        return fpfs;
    }

    public void setFpfs(Integer fpfs) {
        this.fpfs = fpfs;
    }

    public Integer getSyfs() {
        return syfs;
    }

    public void setSyfs(Integer syfs) {
        this.syfs = syfs;
    }

    public String getLgrq() {
        return lgrq;
    }

    public void setLgrq(String lgrq) {
        this.lgrq = lgrq;
    }

    public String getLgry() {
        return lgry;
    }

    public void setLgry(String lgry) {
        this.lgry = lgry;
    }
}
