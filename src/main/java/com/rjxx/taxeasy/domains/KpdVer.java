package com.rjxx.taxeasy.domains;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * t_kpd_ver 实体类
 * 由GenEntityMysql类自动生成
 * Thu Mar 09 10:45:26 CST 2017
 *
 * @ZhangBing
 */
@Entity
@Table(name = "t_kpd_ver")
public class KpdVer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    /**
     * 开票点id
     */
    @Column(name = "kpdid")
    protected Integer kpdid;

    /**
     * 当前版本
     */
    @Column(name = "current_ver")
    protected String currentVer;

    @Column(name = "target_ver")
    protected String targetVer;

    /**
     * MAC地址
     */
    @Column(name = "mac_addr")
    protected String macAddr;

    /**
     * 强制更新，0-不强制，1-强制
     */
    @Column(name = "force_update")
    protected Integer forceUpdate;

    @Column(name = "yxbz")
    protected String yxbz;

    @Column(name = "lrsj")
    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date lrsj;

    @Column(name = "xgsj")
    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date xgsj;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKpdid() {
        return kpdid;
    }

    public void setKpdid(Integer kpdid) {
        this.kpdid = kpdid;
    }

    public String getCurrentVer() {
        return currentVer;
    }

    public void setCurrentVer(String currentVer) {
        this.currentVer = currentVer;
    }

    public String getTargetVer() {
        return targetVer;
    }

    public void setTargetVer(String targetVer) {
        this.targetVer = targetVer;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public Integer getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(Integer forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getYxbz() {
        return yxbz;
    }

    public void setYxbz(String yxbz) {
        this.yxbz = yxbz;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public Date getXgsj() {
        return xgsj;
    }

    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }

}

