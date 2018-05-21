package com.rjxx.taxeasy.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * t_fpkc_yzsz 实体类
 * 由GenEntityMysql类自动生成
 * Sat Mar 18 17:34:57 CST 2017
 * @ZhangBing
 */ 

public class FpkcYzszVo{

	protected Integer id;
	protected String gsdm;
	protected Integer xfid;
	protected Integer skpid;
	protected Integer yhid;
	protected Integer yjyz;
	protected Integer lrry;
	@JsonSerialize(using = JsonDatetimeFormat.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;
	protected Integer xgry;
	@JsonSerialize(using = JsonDatetimeFormat.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;
	protected String yxbz;
	protected String fpzldm;
	protected String xfmc;
	protected String xfsh;
	protected String skph;
	protected String kpdmc;
	protected String fpzlmc;
	protected String kpfs;
    protected String csz;
	protected String fpkcl;
	@JsonSerialize(using = JsonDatetimeFormat.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date kchqsj;

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

	public String getXfmc() {
		return xfmc;
	}

	public void setXfmc(String xfmc) {
		this.xfmc = xfmc;
	}

	public String getXfsh() {
		return xfsh;
	}

	public void setXfsh(String xfsh) {
		this.xfsh = xfsh;
	}

	public String getSkph() {
		return skph;
	}

	public void setSkph(String skph) {
		this.skph = skph;
	}

	public String getKpdmc() {
		return kpdmc;
	}

	public void setKpdmc(String kpdmc) {
		this.kpdmc = kpdmc;
	}

	public String getFpzlmc() {
		return fpzlmc;
	}

	public void setFpzlmc(String fpzlmc) {
		this.fpzlmc = fpzlmc;
	}

	public String getKpfs() {
		return kpfs;
	}

	public void setKpfs(String kpfs) {
		this.kpfs = kpfs;
	}

    public String getCsz() {
        return csz;
    }

    public void setCsz(String csz) {
        this.csz = csz;
    }

	public String getFpkcl() {
		return fpkcl;
	}

	public void setFpkcl(String fpkcl) {
		this.fpkcl = fpkcl;
	}

	public Date getKchqsj() {
		return kchqsj;
	}

	public void setKchqsj(Date kchqsj) {
		this.kchqsj = kchqsj;
	}
}

