package com.rjxx.taxeasy.domains;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * v_spbm 实体类
 * VIEW
 * 由GenEntityMysql类自动生成
 * Mon Aug 21 18:39:46 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="v_spbm")
public class vSpbm  implements Serializable {

@Column(name="spbm")
	protected String spbm;

@Column(name="yhzcbs")
	protected Long yhzcbs;

@Column(name="yhzcmc")
	protected String yhzcmc;

@Column(name="lslbz")
	protected String lslbz;


	public String getSpbm(){
		return spbm;
	}

	public void setSpbm(String spbm){
		this.spbm=spbm;
	}

	public Long getYhzcbs(){
		return yhzcbs;
	}

	public void setYhzcbs(Long yhzcbs){
		this.yhzcbs=yhzcbs;
	}

	public String getYhzcmc(){
		return yhzcmc;
	}

	public void setYhzcmc(String yhzcmc){
		this.yhzcmc=yhzcmc;
	}

	public String getLslbz(){
		return lslbz;
	}

	public void setLslbz(String lslbz){
		this.lslbz=lslbz;
	}

}

