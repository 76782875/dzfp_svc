package com.rjxx.taxeasy.dto;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/3/26
 */
public class AdapterGet {
    private String on;//订单号
    private String ot;//订单时间
    private String pr;//开票金额
    private String sn;//门店号
    private String sp;//商品代码
    private String tq;//抽取数据提取码
    private String type;//数据处理类型标识

    public String getOn() {
        return on;
    }

    public void setOn(String on) {
        this.on = on;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public String getTq() {
        return tq;
    }

    public void setTq(String tq) {
        this.tq = tq;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
