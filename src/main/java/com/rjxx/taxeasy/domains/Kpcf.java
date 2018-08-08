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
 * t_kpcf 实体类
 * 异常开票重发统计表
 * 由GenEntityMysql类自动生成
 * Tue Aug 07 19:10:56 CST 2018
 * @ZhangBing
 */ 
@Entity
@Table(name="t_kpcf")
public class Kpcf  implements Serializable {

/**
 * 开票流水号-主键
 */
@Id
@Column(name="kplsh")
	protected Integer kplsh;

/**
 * 次数
 */ 
@Column(name="kpcfcs")
	protected Integer kpcfcs;

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


	public Integer getKplsh(){
		return kplsh;
	}

	public void setKplsh(Integer kplsh){
		this.kplsh=kplsh;
	}

	public Integer getKpcfcs(){
		return kpcfcs;
	}

	public void setKpcfcs(Integer kpcfcs){
		this.kpcfcs=kpcfcs;
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

}

