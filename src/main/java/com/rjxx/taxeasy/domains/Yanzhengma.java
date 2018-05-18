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
 * t_yanzhengma 实体类
 * 由GenEntityMysql类自动生成
 * Thu Apr 26 14:22:51 CST 2018
 * @ZhangBing
 */ 
@Entity
@Table(name="t_yanzhengma")
public class Yanzhengma  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 手机号
 */ 
@Column(name="phone")
	protected String phone;

/**
 * 验证码
 */ 
@Column(name="code")
	protected String code;

/**
 * 录入时间
 */ 
@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;
	/**
	 * 录入时间
	 */
	@Column(name="xgsj")
	@JsonSerialize(using = JsonDatetimeFormat.class)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;
/**
 * 短信请求ID
 */ 
@Column(name="requestid")
	protected String requestid;

	/**
	 * 获取验证码次数
	 */
	@Column(name="hqnum")
	protected Integer hqnum;

	/**
	 * 体验次数
	 */
	@Column(name="tynum")
	protected Integer tynum;
	/**
	 * 有效标识
	 */
	@Column(name="yxbz")
	protected  String yxbz;



	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getPhone(){
		return phone;
	}

	public void setPhone(String phone){
		this.phone=phone;
	}

	public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code=code;
	}

	public Date getLrsj(){
		return lrsj;
	}

	public void setLrsj(Date lrsj){
		this.lrsj=lrsj;
	}

	public String getRequestid(){
		return requestid;
	}

	public void setRequestid(String requestid){
		this.requestid=requestid;
	}

	public Integer getHqnum() {
		return hqnum;
	}

	public void setHqnum(Integer hqnum) {
		this.hqnum = hqnum;
	}

	public Integer getTynum() {
		return tynum;
	}

	public void setTynum(Integer tynum) {
		this.tynum = tynum;
	}

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}
}

