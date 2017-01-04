package com.rjxx.taxeasy.domains;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.rjxx.comm.json.JsonDateFormat;
import com.rjxx.comm.json.JsonDatetimeFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * t_tqlj 实体类 由GenEntityMysql类自动生成 Wed Jan 04 10:00:29 CST 2017
 * 
 * @ZhangBing
 */
@Entity
@Table(name = "t_tqlj")
public class Tqlj implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@Column(name = "gsdm")
	protected String gsdm;

	@Column(name = "yxbz")
	protected String yxbz;

	@Column(name = "ppmc")
	protected String ppmc;

	@Column(name = "imgurl")
	protected String imgurl;

	@Column(name = "ppdm")
	protected String ppdm;

	@Column(name = "gsmc")
	protected String gsmc;

	@Column(name = "tqlj")
	protected String tqlj;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGsdm() {
		return gsdm;
	}

	public void setGsdm(String gsdm) {
		this.gsdm = gsdm;
	}

	public String getYxbz() {
		return yxbz;
	}

	public void setYxbz(String yxbz) {
		this.yxbz = yxbz;
	}

	public String getPpmc() {
		return ppmc;
	}

	public void setPpmc(String ppmc) {
		this.ppmc = ppmc;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getPpdm() {
		return ppdm;
	}

	public void setPpdm(String ppdm) {
		this.ppdm = ppdm;
	}

	public String getGsmc() {
		return gsmc;
	}

	public void setGsmc(String gsmc) {
		this.gsmc = gsmc;
	}

	public String getTqlj() {
		return tqlj;
	}

	public void setTqlj(String tqlj) {
		this.tqlj = tqlj;
	}

}
