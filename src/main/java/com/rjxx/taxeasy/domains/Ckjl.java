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
 * t_ckjl 实体类 调用税控服务器重开记录表 由GenEntityMysql类自动生成 Thu May 04 18:42:05 CST 2017
 * 
 * @ZhangBing
 */
@Entity
@Table(name = "t_ckjl")
public class Ckjl implements Serializable {

	/**
	 * 单据号
	 */

	@Id
	protected Integer djh;

	/**
	 * 重开次数
	 */
	@Column(name = "ckcs")
	protected Integer ckcs;

	/**
	 * 最后一次重开时间
	 */
	@Column(name = "cksj")
	@JsonSerialize(using = JsonDatetimeFormat.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date cksj;

	public Integer getDjh() {
		return djh;
	}

	public void setDjh(Integer djh) {
		this.djh = djh;
	}

	public Integer getCkcs() {
		return ckcs;
	}

	public void setCkcs(Integer ckcs) {
		this.ckcs = ckcs;
	}

	public Date getCksj() {
		return cksj;
	}

	public void setCksj(Date cksj) {
		this.cksj = cksj;
	}

}
