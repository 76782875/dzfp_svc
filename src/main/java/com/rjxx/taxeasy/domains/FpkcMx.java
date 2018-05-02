package com.rjxx.taxeasy.domains;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * t_fpkc_mx 实体类
 * 发票库存明细表
 * 由GenEntityMysql类自动生成
 * Sat Apr 28 13:44:00 CST 2018
 * @ZhangBing
 */ 
@Entity
@Table(name="t_fpkc_mx")
public class FpkcMx  implements Serializable {

/**
 * 主键id
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * t_fpkc主键id
 */ 
@Column(name="kcid")
	protected Integer kcid;

/**
 * 发票代码
 */ 
@Column(name="fpdm")
	protected String fpdm;

/**
 * 发票起始号码
 */ 
@Column(name="qshm")
	protected String qshm;

/**
 * 终止号码
 */ 
@Column(name="zzhm")
	protected String zzhm;

/**
 * 发票份数
 */ 
@Column(name="fpfs")
	protected Integer fpfs;

/**
 * 剩余份数
 */ 
@Column(name="syfs")
	protected Integer syfs;

/**
 * 领购日期
 */ 
@Column(name="lgrq")
	protected String lgrq;

/**
 * 有效标志，默认1有效
 */ 
@Column(name="yxbz")
	protected String yxbz;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Integer getKcid(){
		return kcid;
	}

	public void setKcid(Integer kcid){
		this.kcid=kcid;
	}

	public String getFpdm(){
		return fpdm;
	}

	public void setFpdm(String fpdm){
		this.fpdm=fpdm;
	}

	public String getQshm(){
		return qshm;
	}

	public void setQshm(String qshm){
		this.qshm=qshm;
	}

	public String getZzhm(){
		return zzhm;
	}

	public void setZzhm(String zzhm){
		this.zzhm=zzhm;
	}

	public Integer getFpfs(){
		return fpfs;
	}

	public void setFpfs(Integer fpfs){
		this.fpfs=fpfs;
	}

	public Integer getSyfs(){
		return syfs;
	}

	public void setSyfs(Integer syfs){
		this.syfs=syfs;
	}

	public String getLgrq(){
		return lgrq;
	}

	public void setLgrq(String lgrq){
		this.lgrq=lgrq;
	}

	public String getYxbz(){
		return yxbz;
	}

	public void setYxbz(String yxbz){
		this.yxbz=yxbz;
	}

}

