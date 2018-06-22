package com.rjxx.taxeasy.domains.shouqianba;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "pay_code")
public class PayCode {

    @Id
    @GeneratedValue
    private Integer id;
    private String code;
    private String msg;
    private String filter;
    private String filterDescript;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
