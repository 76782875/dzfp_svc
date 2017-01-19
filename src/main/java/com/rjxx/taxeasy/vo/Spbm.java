package com.rjxx.taxeasy.vo;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * spbm 实体类
 * 由GenEntityMysql类自动生成
 * Fri Oct 14 14:44:22 GMT+08:00 2016
 * @ZhangBing
 */ 
public class Spbm  implements Serializable {

	protected String bbh;

	protected String spbm;

	protected String spmc;

	protected String sm;

	protected String zzssl;

	protected String gjz;

	protected String zzstsgl;

	protected String sjspbm;


	public String getBbh(){
		return bbh;
	}

	public void setBbh(String bbh){
		this.bbh=bbh;
	}

	public String getSpbm(){
		return spbm;
	}

	public void setSpbm(String spbm){
		this.spbm=spbm;
	}

	public String getSpmc(){
		return spmc;
	}

	public void setSpmc(String spmc){
		this.spmc=spmc;
	}

	public String getSm(){
		return sm;
	}

	public void setSm(String sm){
		this.sm=sm;
	}

	public String getZzssl(){
		return zzssl;
	}

	public void setZzssl(String zzssl){
		this.zzssl=zzssl;
	}

	public String getGjz(){
		return gjz;
	}

	public void setGjz(String gjz){
		this.gjz=gjz;
	}

	public String getZzstsgl(){
		return zzstsgl;
	}

	public void setZzstsgl(String zzstsgl){
		this.zzstsgl=zzstsgl;
	}

	public String getSjspbm() {
		return sjspbm;
	}

	public void setSjspbm(String sjspbm) {
		this.sjspbm = sjspbm;
	}

}

