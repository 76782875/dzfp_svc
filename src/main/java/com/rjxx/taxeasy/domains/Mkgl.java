package com.rjxx.taxeasy.domains;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * t_mkgl 实体类
 * 由GenEntityMysql类自动生成
 * Thu Oct 13 12:45:49 CST 2016
 * @wangyong
 */ 
@Entity
@Table(name="t_mkgl")
public class Mkgl  implements Serializable {

/**
 * 主键
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 用户id
 */ 
@Column(name="yhid")
	protected Integer yhid;

/**
 * 配置id
 */ 
@Column(name="pzid")
	protected Integer pzid;

/**
 * 区块名称
 */ 
@Column(name="mkmc")
	protected String mkmc;

/**
 * 区块比例
 */ 
@Column(name="mkbl")
	protected String mkbl;

/**
 * 排序
 */ 
@Column(name="sort")
	protected Integer sort;

@Column(name="lrry")
	protected Integer lrry;

@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

@Column(name="xgry")
	protected Integer xgry;

@Column(name="xgsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

/**
 * 有效标志
 */ 
@Column(name="yxbz")
	protected String yxbz;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getYhid(){
		return yhid;
	}

	public void setYhid(Integer yhid){
		this.yhid=yhid;
	}

	public Integer getPzid(){
		return pzid;
	}

	public void setPzid(Integer pzid){
		this.pzid=pzid;
	}

	public String getMkmc(){
		return mkmc;
	}

	public void setMkmc(String mkmc){
		this.mkmc=mkmc;
	}

	public String getMkbl(){
		return mkbl;
	}

	public void setMkbl(String mkbl){
		this.mkbl=mkbl;
	}

	public Integer getSort(){
		return sort;
	}

	public void setSort(Integer sort){
		this.sort=sort;
	}

	public Integer getLrry(){
		return lrry;
	}

	public void setLrry(Integer lrry){
		this.lrry=lrry;
	}

	public Date getLrsj(){
		return lrsj;
	}

	public void setLrsj(Date lrsj){
		this.lrsj=lrsj;
	}

	public Integer getXgry(){
		return xgry;
	}

	public void setXgry(Integer xgry){
		this.xgry=xgry;
	}

	public Date getXgsj(){
		return xgsj;
	}

	public void setXgsj(Date xgsj){
		this.xgsj=xgsj;
	}

	public String getYxbz(){
		return yxbz;
	}

	public void setYxbz(String yxbz){
		this.yxbz=yxbz;
	}

}

