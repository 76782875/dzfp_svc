package com.rjxx.taxeasy.domains;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;
import com.rjxx.taxeasy.vo.SkpVo;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * t_skp 实体类 税控盘信息表 由GenEntityMysql类自动生成 Fri Oct 14 08:53:29 GMT+08:00 2016
 *
 * @ZhangBing
 */
@Entity
@Table(name = "t_skp")
public class Skp implements Serializable {

	/**
	 *
	 */
	//private static final long serialVersionUID = 1L;

	/**
	 * 税控盘号
	 */
	@Column(name = "skph")
	protected String skph;

	/**
	 * 税控盘密码
	 */
	@Column(name = "skpmm")
	protected String skpmm;

	/**
	 * 证书密码
	 */
	@Column(name = "zsmm")
	protected String zsmm;

	@Column(name = "zcm")
	protected String zcm;

	/**
	 * 发票库存预警阈值
	 */
	@Column(name = "fpyz")
	protected Integer fpyz;

	/**
	 * 备注
	 */
	@Column(name = "bz")
	protected String bz;

	@Column(name = "lrsj")
	@JsonSerialize(using = JsonDatetimeFormat.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date lrsj;

	/**
	 * 录入人员
	 */
	@Column(name = "lrry")
	protected Integer lrry;

	@Column(name = "xgsj")
	@JsonSerialize(using = JsonDatetimeFormat.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date xgsj;

	/**
	 * 修改人员
	 */
	@Column(name = "xgry")
	protected Integer xgry;

	/**
	 * 销方id
	 */
	@Column(name = "xfid")
	protected Integer xfid;



	/**
	 * 公司代码
	 */
	@Column(name = "gsdm")
	protected String gsdm;

	/**
	 * 有效标志：0，无效；1，有效
	 */
	@Column(name = "yxbz")
	protected String yxbz;
	/**
	 * 开票点名称
	 */
	@Column(name = "kpdmc")
	protected String kpdmc;
	/**
	 * 电子发票最大开票限额
	 */
	@Column(name = "dpmax")
	protected Double dpmax;

	/**
	 * 地址发票开票阈值
	 */
	@Column(name = "fpfz")
	protected Double fpfz;

	/**
	 * 普票开票最大限额
	 */
	@Column(name = "ppmax")
	protected Double ppmax;

	/**
	 * 普票开票阈值
	 */
	@Column(name = "ppfz")
	protected Double ppfz;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	/**
	 * 专票最大开票限额
	 */
	@Column(name = "zpmax")
	protected Double zpmax;

	/**
	 * 专票阈值
	 */
	@Column(name = "zpfz")
	protected Double zpfz;

	/**
	 * 开票点ip地址
	 */
	@Column(name = "kpdip")
	protected String kpdip;

	/**
	 * 开票点名称
	 */
	@Column(name = "kpddm")
	protected String kpddm;

	/**
	 *品牌id
	 */
	@Column(name = "pid")
	protected Integer pid;

	@Column(name = "sbcs")
	protected String sbcs;

	@Column(name = "lxdz")
	protected String lxdz;

	@Column(name = "lxdh")
	protected String lxdh;

	@Column(name = "khyh")
	protected String khyh;

	@Column(name = "yhzh")
	protected String yhzh;

	@Column(name = "skr")
	protected String skr;

	@Column(name = "fhr")
	protected String fhr;

	@Column(name = "kpr")
	protected String kpr;

	@Column(name = "kplx")
	protected String kplx;

	@Column(name = "jkfs")
	protected String jkfs;

	@Column(name = "wrzs")
	protected String wrzs;

	@Column(name = "kpqssj")
	protected Time kpqssj;

	@Column(name = "kpjssj")
	protected Time kpjssj;

	@Column(name = "provinceid")
	protected String provinceid;

	@Column(name = "cityid")
	protected String cityid;

	@Column(name = "areaid")
	protected String areaid;

	@Column(name = "address")
	protected String address;

	@Column(name = "token")
	protected String token;

	@Column(name = "kpjh")
	protected String kpjh;

	@Column(name = "devicesn")
	protected String devicesn;

	@Column(name = "devicepassword")
	protected String devicepassword;

	@Column(name = "devicekey")
	protected String devicekey;

	public String getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Time getKpqssj() {
		return kpqssj;
	}

	public void setKpqssj(Time kpqssj) {
		this.kpqssj = kpqssj;
	}

	public Time getKpjssj() {
		return kpjssj;
	}

	public void setKpjssj(Time kpjssj) {
		this.kpjssj = kpjssj;
	}

	public String getSkph() {
		return skph;
	}

	public void setSkph(String skph) {
		this.skph = skph;
	}

	public String getSkpmm() {
		return skpmm;
	}

	public void setSkpmm(String skpmm) {
		this.skpmm = skpmm;
	}

	public String getZsmm() {
		return zsmm;
	}

	public void setZsmm(String zsmm) {
		this.zsmm = zsmm;
	}

	public String getZcm() {
		return zcm;
	}

	public void setZcm(String zcm) {
		this.zcm = zcm;
	}

	public Integer getFpyz() {
		return fpyz;
	}

	public void setFpyz(Integer fpyz) {
		this.fpyz = fpyz;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public Date getLrsj() {
		return lrsj;
	}

	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}

	public Integer getLrry() {
		return lrry;
	}

	public void setLrry(Integer lrry) {
		this.lrry = lrry;
	}

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

	public Integer getXgry() {
		return xgry;
	}

	public void setXgry(Integer xgry) {
		this.xgry = xgry;
	}

	public Integer getXfid() {
		return xfid;
	}

	public void setXfid(Integer xfid) {
		this.xfid = xfid;
	}

	public String getGsdm() {
		return gsdm;
	}

	public void setGsdm(String gsdm) {
		this.gsdm = gsdm;
	}

	public String getKpdmc() {
		return kpdmc;
	}

	public void setKpdmc(String kpdmc) {
		this.kpdmc = kpdmc;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}

	public Double getDpmax() {
		return dpmax;
	}

	public void setDpmax(Double dpmax) {
		this.dpmax = dpmax;
	}

	public Double getFpfz() {
		return fpfz;
	}

	public void setFpfz(Double fpfz) {
		this.fpfz = fpfz;
	}

	public Double getPpmax() {
		return ppmax;
	}

	public void setPpmax(Double ppmax) {
		this.ppmax = ppmax;
	}

	public Double getPpfz() {
		return ppfz;
	}

	public void setPpfz(Double ppfz) {
		this.ppfz = ppfz;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getZpmax() {
		return zpmax;
	}

	public void setZpmax(Double zpmax) {
		this.zpmax = zpmax;
	}

	public Double getZpfz() {
		return zpfz;
	}

	public void setZpfz(Double zpfz) {
		this.zpfz = zpfz;
	}

	public String getKpdip() {
		return kpdip;
	}

	public void setKpdip(String kpdip) {
		this.kpdip = kpdip;
	}

	public String getKpddm() {
		return kpddm;
	}

	public void setKpddm(String kpddm) {
		this.kpddm = kpddm;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getSbcs() {
		return sbcs;
	}

	public void setSbcs(String sbcs) {
		this.sbcs = sbcs;
	}

	public String getLxdz() {
		return lxdz;
	}

	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getKhyh() {
		return khyh;
	}

	public void setKhyh(String khyh) {
		this.khyh = khyh;
	}

	public String getYhzh() {
		return yhzh;
	}

	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}

	public String getSkr() {
		return skr;
	}

	public void setSkr(String skr) {
		this.skr = skr;
	}

	public String getFhr() {
		return fhr;
	}

	public void setFhr(String fhr) {
		this.fhr = fhr;
	}

	public String getKpr() {
		return kpr;
	}

	public void setKpr(String kpr) {
		this.kpr = kpr;
	}

	public String getKplx() {
		return kplx;
	}

	public void setKplx(String kplx) {
		this.kplx = kplx;
	}

	public String getJkfs() {
		return jkfs;
	}

	public void setJkfs(String jkfs) {
		this.jkfs = jkfs;
	}

	public String getWrzs() {
		return wrzs;
	}

	public void setWrzs(String wrzs) {
		this.wrzs = wrzs;
	}

	public Skp() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getKpjh() {
		return kpjh;
	}

	public void setKpjh(String kpjh) {
		this.kpjh = kpjh;
	}

	public String getDevicesn() {
		return devicesn;
	}

	public void setDevicesn(String devicesn) {
		this.devicesn = devicesn;
	}

	public String getDevicepassword() {
		return devicepassword;
	}

	public void setDevicepassword(String devicepassword) {
		this.devicepassword = devicepassword;
	}

	public String getDevicekey() {
		return devicekey;
	}

	public void setDevicekey(String devicekey) {
		this.devicekey = devicekey;
	}

	public Skp(SkpVo skpVo) {
		this.kpddm = skpVo.getKpddm();
		this.kpdmc = skpVo.getKpdmc();
		this.skph = skpVo.getSkph();
		this.sbcs = skpVo.getSbcs();
		this.lxdz = skpVo.getLxdz();
		this.skpmm = skpVo.getSkpmm();
		this.zsmm = skpVo.getZsmm();
		this.lxdh = skpVo.getLxdh();
		this.khyh = skpVo.getKhyh();
		this.yhzh = skpVo.getYhzh();
		this.skr = skpVo.getSkr();
		this.fhr = skpVo.getFhr();
		this.kpr = skpVo.getKpr();
		this.dpmax = skpVo.getDpmax();
		this.fpfz = skpVo.getFpfz();
		this.zpmax = skpVo.getZpmax();
		this.zpfz = skpVo.getZpfz();
		this.ppmax = skpVo.getPpmax();
		this.ppfz = skpVo.getPpfz();
		this.lrry = skpVo.getLrry();
		this.lrsj = skpVo.getLrsj();
		this.xgry = skpVo.getXgry();
		this.xgsj = skpVo.getXgsj();
		this.yxbz = skpVo.getYxbz();
		this.pid = skpVo.getPid();
		this.kplx = skpVo.getKplx();
		this.wrzs = skpVo.getWrzs();
		this.gsdm = skpVo.getGsdm();
		this.xfid = skpVo.getXfid();
		this.devicesn=skpVo.getDevicesn();
		this.devicepassword=skpVo.getDevicepassword();
	}
}
