package com.rjxx.taxeasy.domains;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_group")
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "yhid")
    protected Integer yhid;
    
    @Column(name = "xfid")
    protected Integer xfid;
    
    @Column(name = "yxbz")
    protected String yxbz;
    
    @Column(name = "lrry")
    protected Integer lrry;
    
    @Column(name = "xgry")
    protected Integer xgry;
    
    @Column(name = "xgsj")
    protected Date xgsj;
    
    @Column(name = "lrsj")
    protected Date lrsj;
    
    @Column(name = "skpid")
    protected Integer skpid;

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

	public Integer getXfid() {
		return xfid;
	}

	public void setXfid(Integer xfid) {
		this.xfid = xfid;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}

	public Integer getLrry() {
		return lrry;
	}

	public void setLrry(Integer lrry) {
		this.lrry = lrry;
	}

	public Integer getXgry() {
		return xgry;
	}

	public void setXgry(Integer xgry) {
		this.xgry = xgry;
	}

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

	public Date getLrsj() {
		return lrsj;
	}

	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}

	public Integer getSkpid() {
		return skpid;
	}

	public void setSkpid(Integer skpid) {
		this.skpid = skpid;
	}
}
