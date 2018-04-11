package com.rjxx.taxeasy.vo;

public class Cxtjvo {
	
	private Integer fpsl;
	private String kpny;
	private Integer tqsl;
	private String tqny;
	private double jshj;
	private String fpzldm;
	private String fpzlmc;
	private String skpid;
	private String kpdmc;
	public Integer getFpsl() {
		return fpsl;
	}
	public void setFpsl(Integer fpsl) {
		this.fpsl = fpsl;
	}
	public String getKpny() {
		return kpny;
	}
	public void setKpny(String kpny) {
		this.kpny = kpny;
	}
	public Integer getTqsl() {
		return tqsl;
	}
	public void setTqsl(Integer tqsl) {
		this.tqsl = tqsl;
	}
	public String getTqny() {
		return tqny;
	}
	public void setTqny(String tqny) {
		this.tqny = tqny;
	}

	public double getJshj() {
		return jshj;
	}

	public void setJshj(double jshj) {
		this.jshj = jshj;
	}

	public String getFpzldm() {
		return fpzldm;
	}

	public void setFpzldm(String fpzldm) {
		this.fpzldm = fpzldm;
	}

	public String getFpzlmc() {
		return fpzlmc;
	}

	public void setFpzlmc(String fpzlmc) {
		this.fpzlmc = fpzlmc;
	}

	public String getSkpid() {
		return skpid;
	}

	public void setSkpid(String skpid) {
		this.skpid = skpid;
	}

	public String getKpdmc() {
		return kpdmc;
	}

	public void setKpdmc(String kpdmc) {
		this.kpdmc = kpdmc;
	}

	public Cxtjvo() {

	}

	public Cxtjvo(Cxtjvo cxtjvo) {
		this.fpsl = cxtjvo.getFpsl();
		this.kpny = cxtjvo.getKpny();
		this.tqsl = cxtjvo.getTqsl();
		this.tqny = cxtjvo.getTqny();
		this.jshj = cxtjvo.getJshj();
		this.fpzldm = cxtjvo.getFpzldm();
		this.fpzlmc = cxtjvo.getFpzlmc();
		this.skpid = cxtjvo.getSkpid();
		this.kpdmc = cxtjvo.getKpdmc();
	}
}
