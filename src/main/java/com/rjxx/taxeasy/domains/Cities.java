package com.rjxx.taxeasy.domains;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * cities 实体类
 * 行政区域地州市信息表
 * 由GenEntityMysql类自动生成
 * Fri May 04 17:04:51 CST 2018
 * @ZhangBing
 */ 
@Entity
@Table(name="cities")
public class Cities  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

@Column(name="cityid")
	protected String cityid;

@Column(name="city")
	protected String city;

@Column(name="provinceid")
	protected String provinceid;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getCityid(){
		return cityid;
	}

	public void setCityid(String cityid){
		this.cityid=cityid;
	}

	public String getCity(){
		return city;
	}

	public void setCity(String city){
		this.city=city;
	}

	public String getProvinceid(){
		return provinceid;
	}

	public void setProvinceid(String provinceid){
		this.provinceid=provinceid;
	}

}

