package com.rjxx.taxeasy.domains;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * t_dr_pz 实体类
 * 导入配置
 * 由GenEntityMysql类自动生成
 * Mon Oct 10 13:14:55 CST 2016
 *
 * @ZhangBing
 */
@Entity
@Table(name = "t_dr_pz")
public class DrPz implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    /**
     * 用户id
     */
    @Column(name = "yhid")
    protected Integer yhid;

    /**
     * 字段名
     */
    @Column(name = "zdm")
    protected String zdm;

    /**
     * 配置类型，auto-默认设置，config-对应导入文件
     */
    @Column(name = "pzlx")
    protected String pzlx;

    /**
     * 配置的值
     */
    @Column(name = "pzz")
    protected String pzz;

    @Column(name = "lrsj")
    @JsonSerialize(using = JsonDatetimeFormat.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date lrsj;

    /**
     * 配置的值
     */
    @Column(name = "mbid")
    protected Integer mbid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYhid() {
        return yhid;
    }

    public void setYhid(Integer yhid) {
        this.yhid = yhid;
    }

    public String getZdm() {
        return zdm;
    }

    public void setZdm(String zdm) {
        this.zdm = zdm;
    }

    public String getPzlx() {
        return pzlx;
    }

    public void setPzlx(String pzlx) {
        this.pzlx = pzlx;
    }

    public String getPzz() {
        return pzz;
    }

    public void setPzz(String pzz) {
        this.pzz = pzz;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

	public Integer getMbid() {
		return mbid;
	}

	public void setMbid(Integer mbid) {
		this.mbid = mbid;
	}

}

