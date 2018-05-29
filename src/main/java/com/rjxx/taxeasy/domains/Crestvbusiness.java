package com.rjxx.taxeasy.domains;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
/**
 * t_crestvbusiness 实体类
 * 由GenEntityMysql类自动生成
 * Fri May 25 16:25:25 CST 2018
 * @ZhangBing
 */ 
@Entity
@Table(name="t_crestvbusiness")
public class Crestvbusiness  implements Serializable {

/**
 * 主键
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 平台开票流水号
 */ 
@Column(name="kplsh")
	protected String kplsh;

/**
 * 录入时间
 */ 
@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

/**
 * 修改时间
 */ 
@Column(name="xgsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

	/**
	 * 是否发送mq标志0否1是
	 */
	@Column(name="mqbz")
	protected String mqbz;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getKplsh(){
		return kplsh;
	}

	public void setKplsh(String kplsh){
		this.kplsh=kplsh;
	}

	public Date getLrsj(){
		return lrsj;
	}

	public void setLrsj(Date lrsj){
		this.lrsj=lrsj;
	}

	public Date getXgsj(){
		return xgsj;
	}

	public void setXgsj(Date xgsj){
		this.xgsj=xgsj;
	}

	public String getMqbz() {
		return mqbz;
	}

	public void setMqbz(String mqbz) {
		this.mqbz = mqbz;
	}
}

