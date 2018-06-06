package com.rjxx.taxeasy.domains;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/6/6
 */

@Entity(name = "t_ztbz")
public class Ztbz {
    @Id
    private Long id;
    private String ztbzdm;
    private String ztbzmc;
    private Date lrsj;
    private Date xgsj;
    private String filter;
    private String filterDescript;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZtbzdm() {
        return ztbzdm;
    }

    public void setZtbzdm(String ztbzdm) {
        this.ztbzdm = ztbzdm;
    }

    public String getZtbzmc() {
        return ztbzmc;
    }

    public void setZtbzmc(String ztbzmc) {
        this.ztbzmc = ztbzmc;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public Date getXgsj() {
        return xgsj;
    }

    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getFilterDescript() {
        return filterDescript;
    }

    public void setFilterDescript(String filterDescript) {
        this.filterDescript = filterDescript;
    }
}
