package com.rjxx.taxeasy.domains;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * privileges 实体类
 * 由GenEntityMysql类自动生成
 * Tue Oct 11 14:12:15 CST 2016
 *
 * @ZhangBing
 */
@Entity
@Table(name = "privileges")
public class Privileges implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "privilegetypeid")
    protected Integer privilegetypeid;

    @Column(name = "name")
    protected String name;

    @Column(name = "description")
    protected String description;

    @Column(name = "sort")
    protected Integer sort;

    @Column(name = "urls")
    protected String urls;

    /**
     * 状态标识
     */
    @Column(name = "ztbz")
    protected String ztbz;

    @Column(name = "lrsj")
    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date lrsj;

    @Column(name = "lrry")
    protected String lrry;

    @Column(name = "xgsj")
    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date xgsj;

    @Column(name = "xgry")
    protected String xgry;

    @Column(name = "yxbz")
    protected String yxbz;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrivilegetypeid() {
        return privilegetypeid;
    }

    public void setPrivilegetypeid(Integer privilegetypeid) {
        this.privilegetypeid = privilegetypeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getZtbz() {
        return ztbz;
    }

    public void setZtbz(String ztbz) {
        this.ztbz = ztbz;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public String getLrry() {
        return lrry;
    }

    public void setLrry(String lrry) {
        this.lrry = lrry;
    }

    public Date getXgsj() {
        return xgsj;
    }

    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }

    public String getXgry() {
        return xgry;
    }

    public void setXgry(String xgry) {
        this.xgry = xgry;
    }

    public String getYxbz() {
        return yxbz;
    }

    public void setYxbz(String yxbz) {
        this.yxbz = yxbz;
    }

}

