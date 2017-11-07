package com.rjxx.taxeasy.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class XfKzVo {

	private String sfDm;
	private String sfMc;
	private Integer xfid;
	private String svrIp;
	private String svrPort;

	public String getSfDm() {
		return sfDm;
	}

	public void setSfDm(String sfDm) {
		this.sfDm = sfDm;
	}

	public Integer getXfid() {
		return xfid;
	}

	public void setXfid(Integer xfid) {
		this.xfid = xfid;
	}

	public String getSvrIp() {
		return svrIp;
	}

	public void setSvrIp(String svrIp) {
		this.svrIp = svrIp;
	}

	public String getSvrPort() {
		return svrPort;
	}

	public void setSvrPort(String svrPort) {
		this.svrPort = svrPort;
	}

	public String getSfMc() {

		return sfMc;
	}

	public void setSfMc(String sfMc) {
		this.sfMc = sfMc;
	}
}
