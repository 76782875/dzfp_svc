package com.rjxx.taxeasy.domains;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * t_dyzl 实体类
 * 由GenEntityMysql类自动生成
 * Tue Jan 17 15:10:28 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="t_dyzl")
public class Dyzl  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 订阅标题
 */ 
@Column(name="dybt")
	protected String dybt;

@Column(name="bz")
	protected String bz;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getDybt(){
		return dybt;
	}

	public void setDybt(String dybt){
		this.dybt=dybt;
	}

	public String getBz(){
		return bz;
	}

	public void setBz(String bz){
		this.bz=bz;
	}

}

