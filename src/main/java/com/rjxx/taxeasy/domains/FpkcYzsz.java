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
 * t_fpkc_yzsz 实体类
 * 由GenEntityMysql类自动生成
 * Sat Mar 18 17:34:57 CST 2017
 * @ZhangBing
 */ 
@Entity
@Table(name="t_fpkc_yzsz")
public class FpkcYzsz  implements Serializable {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

@Column(name="gsdm")
	protected String gsdm;

@Column(name="xfid")
	protected Integer xfid;

@Column(name="skpid")
	protected Integer skpid;


@Column(name="skph")
	protected String skph;

@Column(name="yhid")
	protected Integer yhid;

/**
 * 库存预警阈值
 */ 
@Column(name="yjyz")
	protected Integer yjyz;

@Column(name="lrry")
	protected Integer lrry;

@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

@Column(name="xgry")
	protected Integer xgry;

@Column(name="xgsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

@Column(name="yxbz")
	protected String yxbz;

@Column(name="fpzldm")
protected String fpzldm;

@Column(name="kpfs")
protected String kpfs;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getGsdm(){
		return gsdm;
	}

	public void setGsdm(String gsdm){
		this.gsdm=gsdm;
	}

	public Integer getXfid(){
		return xfid;
	}

	public void setXfid(Integer xfid){
		this.xfid=xfid;
	}

	public Integer getSkpid(){
		return skpid;
	}

	public void setSkpid(Integer skpid){
		this.skpid=skpid;
	}

	public Integer getYhid(){
		return yhid;
	}

	public void setYhid(Integer yhid){
		this.yhid=yhid;
	}

	public Integer getYjyz(){
		return yjyz;
	}

	public void setYjyz(Integer yjyz){
		this.yjyz=yjyz;
	}

	public Integer getLrry(){
		return lrry;
	}

	public void setLrry(Integer lrry){
		this.lrry=lrry;
	}

	public Date getLrsj(){
		return lrsj;
	}

	public void setLrsj(Date lrsj){
		this.lrsj=lrsj;
	}

	public Integer getXgry(){
		return xgry;
	}

	public void setXgry(Integer xgry){
		this.xgry=xgry;
	}

	public Date getXgsj(){
		return xgsj;
	}

	public void setXgsj(Date xgsj){
		this.xgsj=xgsj;
	}

	public String getYxbz(){
		return yxbz;
	}

	public void setYxbz(String yxbz){
		this.yxbz=yxbz;
	}

	public String getFpzldm() {
		return fpzldm;
	}

	public void setFpzldm(String fpzldm) {
		this.fpzldm = fpzldm;
	}

	public String getKpfs() {
		return kpfs;
	}

	public void setKpfs(String kpfs) {
		this.kpfs = kpfs;
	}

	public String getSkph() {
		return skph;
	}

	public void setSkph(String skph) {
		this.skph = skph;
	}
}

