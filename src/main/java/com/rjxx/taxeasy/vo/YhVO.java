package com.rjxx.taxeasy.vo;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import com.rjxx.taxeasy.domains.Yh;

import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * t_yh 实体类
 * 用户表
 * 由GenEntityMysql类自动生成
 * Thu Dec 01 12:47:26 CST 2016
 * @ZhangBing
 */ 
@Entity
@Table(name="t_yh")
public class YhVO  implements Serializable {

/**
 * 用户ID
 */ 
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

/**
 * 登录用户名 登录用户名唯一
 */ 
@Column(name="dlyhid")
	protected String dlyhid;

/**
 * 用户名称
 */ 
@Column(name="yhmc")
	protected String yhmc;

/**
 * 用户口令
 */ 
@Column(name="yhmm")
	protected String yhmm;

/**
 * 用户机构
 */ 
@Column(name="yhjg")
	protected Integer yhjg;

/**
 * 公司代码
 */ 
@Column(name="gsdm")
	protected String gsdm;

/**
 * 性别 1、男；0、女
 */ 
@Column(name="xb")
	protected String xb;

@Column(name="lrsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

/**
 * 录入人员
 */ 
@Column(name="lrry")
	protected Integer lrry;

@Column(name="xgsj")
@JsonSerialize(using = JsonDatetimeFormat.class)
@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

/**
 * 修改人员
 */ 
@Column(name="xgry")
	protected Integer xgry;

/**
 * 授权key
 */ 
@Column(name="appkey")
	protected String appkey;

/**
 * 授权秘钥
 */ 
@Column(name="secret")
	protected String secret;

/**
 * 角色id
 */ 
@Column(name="roleids")
	protected String roleids;

/**
 * 状态标识
 */ 
@Column(name="ztbz")
	protected String ztbz;

/**
 * 是否为超级管理员
 */ 
@Column(name="sup")
	protected String sup;

/**
 * 手机号码
 */ 
@Column(name="sjhm")
	protected String sjhm;

/**
 * 邮箱
 */ 
@Column(name="yx")
	protected String yx;

/**
 * 有效标志
 */ 
@Column(name="yxbz")
	protected String yxbz;

@Column(name="admin")
	protected String admin;

	protected String lrrymc;

	protected String jsmc;
	
	protected String zhlxmc;
	
	protected String yhcount;

	protected String flag;
	
	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getDlyhid(){
		return dlyhid;
	}

	public void setDlyhid(String dlyhid){
		this.dlyhid=dlyhid;
	}

	public String getYhmc(){
		return yhmc;
	}

	public void setYhmc(String yhmc){
		this.yhmc=yhmc;
	}

	public String getYhmm(){
		return yhmm;
	}

	public void setYhmm(String yhmm){
		this.yhmm=yhmm;
	}

	public Integer getYhjg(){
		return yhjg;
	}

	public void setYhjg(Integer yhjg){
		this.yhjg=yhjg;
	}

	public String getGsdm(){
		return gsdm;
	}

	public void setGsdm(String gsdm){
		this.gsdm=gsdm;
	}

	public String getXb(){
		return xb;
	}

	public void setXb(String xb){
		this.xb=xb;
	}

	public Date getLrsj(){
		return lrsj;
	}

	public void setLrsj(Date lrsj){
		this.lrsj=lrsj;
	}

	public Integer getLrry(){
		return lrry;
	}

	public void setLrry(Integer lrry){
		this.lrry=lrry;
	}

	public Date getXgsj(){
		return xgsj;
	}

	public void setXgsj(Date xgsj){
		this.xgsj=xgsj;
	}

	public Integer getXgry(){
		return xgry;
	}

	public void setXgry(Integer xgry){
		this.xgry=xgry;
	}

	public String getAppkey(){
		return appkey;
	}

	public void setAppkey(String appkey){
		this.appkey=appkey;
	}

	public String getSecret(){
		return secret;
	}

	public void setSecret(String secret){
		this.secret=secret;
	}

	public String getRoleids(){
		return roleids;
	}

	public void setRoleids(String roleids){
		this.roleids=roleids;
	}

	public String getZtbz(){
		return ztbz;
	}

	public void setZtbz(String ztbz){
		this.ztbz=ztbz;
	}

	public String getSup(){
		return sup;
	}

	public void setSup(String sup){
		this.sup=sup;
	}

	public String getSjhm(){
		return sjhm;
	}

	public void setSjhm(String sjhm){
		this.sjhm=sjhm;
	}

	public String getYx(){
		return yx;
	}

	public void setYx(String yx){
		this.yx=yx;
	}

	public String getYxbz(){
		return yxbz;
	}

	public void setYxbz(String yxbz){
		this.yxbz=yxbz;
	}

	public String getAdmin(){
		return admin;
	}

	public void setAdmin(String admin){
		this.admin=admin;
	}

	public String getLrrymc() {
		return lrrymc;
	}

	public void setLrrymc(String lrrymc) {
		this.lrrymc = lrrymc;
	}

	public String getJsmc() {
		return jsmc;
	}

	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}
    
	public String getZhlxmc() {
		return zhlxmc;
	}

	public void setZhlxmc(String zhlxmc) {
		this.zhlxmc = zhlxmc;
	}
    
	public String getYhcount() {
		return yhcount;
	}

	public void setYhcount(String yhcount) {
		this.yhcount = yhcount;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public YhVO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public YhVO(Integer id, String dlyhid, String yhmc, String yhmm, Integer yhjg, String gsdm, String xb, Date lrsj,
			Integer lrry, Date xgsj, Integer xgry, String appkey, String secret, String roleids, String ztbz,
			String sup, String sjhm, String yx, String yxbz, String admin, String lrrymc, String jsmc, String zhlxmc,String yhcount) {
		super();
		this.id = id;
		this.dlyhid = dlyhid;
		this.yhmc = yhmc;
		this.yhmm = yhmm;
		this.yhjg = yhjg;
		this.gsdm = gsdm;
		this.xb = xb;
		this.lrsj = lrsj;
		this.lrry = lrry;
		this.xgsj = xgsj;
		this.xgry = xgry;
		this.appkey = appkey;
		this.secret = secret;
		this.roleids = roleids;
		this.ztbz = ztbz;
		this.sup = sup;
		this.sjhm = sjhm;
		this.yx = yx;
		this.yxbz = yxbz;
		this.admin = admin;
		this.lrrymc = lrrymc;
		this.jsmc = jsmc;
		this.zhlxmc = zhlxmc;
		this.yhcount=yhcount;
		this.flag = "";
	}

	public YhVO(Yh yh) {
		super();
		this.id = yh.getId();
		this.dlyhid = yh.getDlyhid();
		this.yhmc = yh.getYhmc();
		this.yhmm = yh.getYhmm();
		this.yhjg = yh.getYhjg();
		this.gsdm = yh.getGsdm();
		this.xb = yh.getXb();
		this.lrsj = yh.getLrsj();
		this.lrry = yh.getLrry();
		this.xgsj = yh.getXgsj();
		this.xgry = yh.getXgry();
		this.appkey = yh.getAppkey();
		this.secret = yh.getSecret();
		this.roleids = yh.getRoleids();
		this.ztbz = yh.getZtbz();
		this.sup = yh.getSup();
		this.sjhm = yh.getSjhm();
		this.yx = yh.getYx();
		this.yxbz = yh.getYxbz();
		this.admin = yh.getAdmin();
		this.flag = "";
	}
	
}

