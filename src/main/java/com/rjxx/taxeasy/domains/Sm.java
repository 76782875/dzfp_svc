package com.rjxx.taxeasy.domains;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * t_sm 实体类
 * 税目表
 * 由GenEntityMysql类自动生成
 * Mon Oct 10 15:58:17 CST 2016
 *
 * @ZhangBing
 */
@Entity
@Table(name = "t_sm")
public class Sm implements Serializable {

    /**
     * 税目ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    /**
     * 税目代码
     */
    @Column(name = "smdm")
    protected String smdm;

    /**
     * 税目名称
     */
    @Column(name = "smmc")
    protected String smmc;

    /**
     * 税率
     */
    @Column(name = "sl")
    protected Double sl;

    @Column(name = "lrsj")
    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date lrsj;

    /**
     * 录入人员
     */
    @Column(name = "lrry")
    protected Integer lrry;

    @Column(name = "xgsj")
    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date xgsj;

    /**
     * 修改人员
     */
    @Column(name = "xgry")
    protected Integer xgry;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSmdm() {
        return smdm;
    }

    public void setSmdm(String smdm) {
        this.smdm = smdm;
    }

    public String getSmmc() {
        return smmc;
    }

    public void setSmmc(String smmc) {
        this.smmc = smmc;
    }

    public Double getSl() {
        return sl;
    }

    public void setSl(Double sl) {
        this.sl = sl;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public Integer getLrry() {
        return lrry;
    }

    public void setLrry(Integer lrry) {
        this.lrry = lrry;
    }

    public Date getXgsj() {
        return xgsj;
    }

    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }

    public Integer getXgry() {
        return xgry;
    }

    public void setXgry(Integer xgry) {
        this.xgry = xgry;
    }

}

