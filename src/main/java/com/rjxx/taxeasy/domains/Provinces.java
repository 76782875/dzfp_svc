package com.rjxx.taxeasy.domains;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * provinces 实体类
 * 省份信息表
 * 由GenEntityMysql类自动生成
 * Fri May 04 16:34:57 CST 2018
 * @ZhangBing
 */ 
@Entity
@Table(name="provinces")
public class Provinces  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

@Column(name="provinceid")
	protected String provinceid;

@Column(name="province")
	protected String province;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getProvinceid(){
		return provinceid;
	}

	public void setProvinceid(String provinceid){
		this.provinceid=provinceid;
	}

	public String getProvince(){
		return province;
	}

	public void setProvince(String province){
		this.province=province;
	}

}

