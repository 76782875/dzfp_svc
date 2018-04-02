package com.rjxx.taxeasy.vo;




import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;

/**
 * Created by lenovo on 2015/12/14.
 */

public class  JyspmxDecimal2  {
    
    protected Integer sqlsh;
    
    protected Integer spmxxh;
    
    protected String fphxz;
    
    protected String spdm;
    
    protected String spmc;
    
    protected String spggxh;
    
    protected String spdw;
    
    protected BigDecimal sps;
    
    protected BigDecimal spdj;
    
    protected BigDecimal spje;
    
    protected BigDecimal spsl;
    
    protected BigDecimal spse;
    
    protected BigDecimal jshj;
    
    protected BigDecimal ykphj;
    
    protected BigDecimal kkjje;
    
    protected BigDecimal ykjje;
    
    protected Integer hzkpxh;
    
    protected Timestamp lrsj;
    
    protected Integer lrry;
    
    protected Timestamp xgsj;
    
    protected Integer xgry;

    
    protected String gsdm;

    protected int fpnum;
    
    
	public JyspmxDecimal2(JyspmxDecimal2 jyspmx) {
		//super();
		this.sqlsh = jyspmx.getsqlsh();
		this.spmxxh = jyspmx.getSpmxxh();
		this.fphxz = jyspmx.getFphxz();
		this.spdm = jyspmx.getSpdm();
		this.spmc = jyspmx.getSpmc();
		this.spggxh = jyspmx.getSpggxh();
		this.spdw = jyspmx.getSpdw();
		this.sps = jyspmx.getSps();
		this.spdj = jyspmx.getSpdj();
		this.spje = jyspmx.getSpje();
		this.spsl = jyspmx.getSpsl();
		this.spse = jyspmx.getSpse();
		this.jshj = jyspmx.getJshj();
		this.ykphj = jyspmx.getYkphj();
		this.kkjje = jyspmx.getKkjje();
		this.ykjje = jyspmx.getYkjje();
		this.hzkpxh = jyspmx.getHzkpxh();
		this.lrsj = jyspmx.getLrsj();
		this.lrry = jyspmx.getLrry();
		this.xgsj = jyspmx.getXgsj();
		this.xgry = jyspmx.getXgry();
		this.gsdm = jyspmx.getGsdm();
		this.fpnum = jyspmx.getFpnum();
		this.kce = jyspmx.getKce();
		this.yhzcbs = jyspmx.getYhzcbs();
		this.yhzcmc = jyspmx.getYhzcmc();
		this.lslbz = jyspmx.getLslbz();
	}

	/**
     * 扣除额
     */
    @Column(name = "kce")
    protected BigDecimal kce;

    /**
     * 优惠政策标识
     */
    @Column(name = "yhzcbs")
    protected String yhzcbs;

    /**
     * 优惠政策名称
     */
    @Column(name = "yhzcmc")
    protected String yhzcmc;

    /**
     * 零税率标志
     */
    @Column(name = "lslbz")
    protected String lslbz;

    public BigDecimal getKce() {
		return kce;
	}

	public void setKce(BigDecimal kce) {
		this.kce = kce;
	}

	public String getYhzcbs() {
		return yhzcbs;
	}

	public void setYhzcbs(String yhzcbs) {
		this.yhzcbs = yhzcbs;
	}

	public String getYhzcmc() {
		return yhzcmc;
	}

	public void setYhzcmc(String yhzcmc) {
		this.yhzcmc = yhzcmc;
	}

	public String getLslbz() {
		return lslbz;
	}

	public void setLslbz(String lslbz) {
		this.lslbz = lslbz;
	}

	public JyspmxDecimal2() {
    }

    //单据号
    public int getsqlsh() {
        return sqlsh;
    }

    public void setSqlsh(Integer value) {
        this.sqlsh = value;
    }

    //商品明细序号
    public int getSpmxxh() {
        return spmxxh;
    }

    public void setSpmxxh(Integer value) {
        this.spmxxh = value;
    }

    //0、正常行；1、折扣行；2、被折扣行
    public String getFphxz() {
        return fphxz;
    }

    public void setFphxz(String value) {
        this.fphxz = value;
    }

    //商品代码
    public String getSpdm() {
        return spdm;
    }

    public void setSpdm(String value) {
        this.spdm = value;
    }

    //商品名称
    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String value) {
        this.spmc = value;
    }

    //商品规格型号
    public String getSpggxh() {
        return spggxh;
    }

    public void setSpggxh(String value) {
        this.spggxh = value;
    }

    //商品单位
    public String getSpdw() {
        return spdw;
    }

    public void setSpdw(String value) {
        this.spdw = value;
    }

    public BigDecimal getSps() {
        return sps;
    }

    public void setSps(BigDecimal sps) {
        this.sps = sps;
    }

    public BigDecimal getSpdj() {
        return spdj;
    }

    public void setSpdj(BigDecimal spdj) {
        this.spdj = spdj;
    }

    public BigDecimal getSpje() {
        return spje;
    }

    public void setSpje(BigDecimal spje) {
        this.spje = spje;
    }

    public BigDecimal getSpsl() {
        return spsl;
    }

    public void setSpsl(BigDecimal spsl) {
        this.spsl = spsl;
    }

    public BigDecimal getSpse() {
        return spse;
    }

    public void setSpse(BigDecimal spse) {
        this.spse = spse;
    }

    public BigDecimal getJshj() {
        return jshj;
    }

    public void setJshj(BigDecimal jshj) {
        this.jshj = jshj;
    }

    public BigDecimal getYkphj() {
        return ykphj;
    }

    public void setYkphj(BigDecimal ykphj) {
        this.ykphj = ykphj;
    }

    //汇总开票序号
    public Integer getHzkpxh() {
        return hzkpxh;
    }

    public void setHzkpxh(Integer value) {
        this.hzkpxh = value;
    }

    //录入时间
    public Timestamp getLrsj() {
        return lrsj;
    }

    public void setLrsj(Timestamp value) {
        this.lrsj = value;
    }

    //录入人员
    public Integer getLrry() {
        return lrry;
    }

    public void setLrry(Integer value) {
        this.lrry = value;
    }

    //修改时间
    public Timestamp getXgsj() {
        return xgsj;
    }

    public void setXgsj(Timestamp value) {
        this.xgsj = value;
    }

    //修改人员
    public Integer getXgry() {
        return xgry;
    }

    public void setXgry(Integer value) {
        this.xgry = value;
    }

    public String getGsdm() {
        return gsdm;
    }

    public void setGsdm(String gsdm) {
        this.gsdm = gsdm;
    }

    public int getFpnum() {
        return fpnum;
    }

    public void setFpnum(int fpnum) {
        this.fpnum = fpnum;
    }

	public BigDecimal getKkjje() {
		return kkjje;
	}

	public void setKkjje(BigDecimal kkjje) {
		this.kkjje = kkjje;
	}

	public BigDecimal getYkjje() {
		return ykjje;
	}

	public void setYkjje(BigDecimal ykjje) {
		this.ykjje = ykjje;
	}


}