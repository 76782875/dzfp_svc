package com.rjxx.taxeasy.domains;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * t_jkpzdmb 实体类
 * 由GenEntityMysql类自动生成
 * Tue Mar 20 16:07:35 CST 2018
 * @ZhangBing
 */ 
@Entity
@Table(name="t_jkpzdmb")
public class Jkpzdmb  implements Serializable {

/**
 * 主键id
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 接口配置参数名（t_jkpzb.pzcsm）
 */ 
@Column(name="jkpzcsm")
	protected String jkpzcsm;

/**
 * 获取参数值方法
 */ 
@Column(name="cszff")
	protected String cszff;

/**
 * 参数名（t_csb.csm）
 */ 
@Column(name="csm")
	protected String csm;

/**
 * cszff字段的中文解释
 */ 
@Column(name="conf_desc")
	protected String confDesc;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getJkpzcsm(){
		return jkpzcsm;
	}

	public void setJkpzcsm(String jkpzcsm){
		this.jkpzcsm=jkpzcsm;
	}

	public String getCszff(){
		return cszff;
	}

	public void setCszff(String cszff){
		this.cszff=cszff;
	}

	public String getCsm(){
		return csm;
	}

	public void setCsm(String csm){
		this.csm=csm;
	}

	public String getConfDesc(){
		return confDesc;
	}

	public void setConfDesc(String confDesc){
		this.confDesc=confDesc;
	}

}

