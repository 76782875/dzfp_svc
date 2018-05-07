package com.rjxx.taxeasy.domains;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * areas 实体类
 * 行政区域县区信息表
 * 由GenEntityMysql类自动生成
 * Fri May 04 17:37:35 CST 2018
 * @ZhangBing
 */ 
@Entity
@Table(name="areas")
public class Areas  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

@Column(name="areaid")
	protected String areaid;

@Column(name="area")
	protected String area;

@Column(name="cityid")
	protected String cityid;


	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getAreaid(){
		return areaid;
	}

	public void setAreaid(String areaid){
		this.areaid=areaid;
	}

	public String getArea(){
		return area;
	}

	public void setArea(String area){
		this.area=area;
	}

	public String getCityid(){
		return cityid;
	}

	public void setCityid(String cityid){
		this.cityid=cityid;
	}

}

