package com.rjxx.taxeasy.vo;

import java.util.Date;


public class SkpVo {
	/**
	 * 税控盘号
	 */
	protected String skph;

	/**
	 * 税控盘密码
	 */
	protected String skpmm;

	/**
	 * 证书密码
	 */
	protected String zsmm;

	protected String zcm;

	/**
	 * 发票库存预警阈值
	 */
	protected Integer fpyz;
	
	protected String ppdm;
	
	protected String ppmc;

	/**
	 * 备注
	 */
	protected String bz;

	protected Date lrsj;

	/**
	 * 录入人员
	 */
	protected Integer lrry;

	protected Date xgsj;

	/**
	 * 修改人员
	 */
	protected Integer xgry;

	/**
	 * 销方id
	 */
	protected Integer xfid;

	/**
	 * 销方id
	 */
	protected Integer pid;

	/**
	 * 公司代码
	 */
	protected String gsdm;

	/**
	 * 开票点名称
	 */
	protected String kpdmc;

	/**
	 * 有效标志：0，无效；1，有效
	 */
	protected String yxbz;

	/**
	 * 电子发票最大开票限额
	 */
	protected Double dpmax;

	/**
	 * 地址发票开票阈值
	 */
	protected Double fpfz;

	/**
	 * 普票开票最大限额
	 */
	protected Double ppmax;

	/**
	 * 普票开票阈值
	 */
	protected Double ppfz;

	protected Integer id;

	/**
	 * 专票最大开票限额
	 */
	protected Double zpmax;

	/**
	 * 专票阈值
	 */
	protected Double zpfz;

	/**
	 * 开票点ip地址
	 */
	protected String kpdip;
	protected String kpddm;
	protected String xfmc;
	protected String sbcs;

	protected String lxdz;

	protected String lxdh;

	protected String khyh;

	protected String yhzh;

	protected String skr;

	protected String fhr;

	protected String kpr;

	protected String kplx;

	protected String fpzl;

	protected String wrzs;
	
	protected String skpcount;

	protected String provinceid;

	protected String cityid;

	protected String areaid;

	protected String address;

	protected String token;

	protected String kpjh;

	protected String devicesn;

	protected String devicepassword;

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

	public String getXfmc() {
		return xfmc;
	}

	public void setXfmc(String xfmc) {
		this.xfmc = xfmc;
	}

	public String getKpddm() {
		return kpddm;
	}

	public void setKpddm(String kpddm) {
		this.kpddm = kpddm;
	}

	public String getPpdm() {
		return ppdm;
	}

	public void setPpdm(String ppdm) {
		this.ppdm = ppdm;
	}

	public String getPpmc() {
		return ppmc;
	}

	public void setPpmc(String ppmc) {
		this.ppmc = ppmc;
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

	public String getFpzl() {
		String str = "";
		if (kplx != null) {
			for (String s : kplx.split(",")) {
				if (s.equals("01")) {
					str += "专用发票,";
				}else if (s.equals("02")){
					str += "普通发票,";
				}else{
					str += "电子发票,";
				}
			}
			return str.substring(0, str.length()-1);
		}
		return fpzl;
	}

	public void setFpzl(String fpzl) {
		this.fpzl = fpzl;
	}

	public String getSkpcount() {
		return skpcount;
	}

	public void setSkpcount(String skpcount) {
		this.skpcount = skpcount;
	}

	public String getWrzs() {
		return wrzs;
	}

	public void setWrzs(String wrzs) {
		this.wrzs = wrzs;
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
}
