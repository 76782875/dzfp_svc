package com.rjxx.taxeasy.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author: zsq
 * @date: 2018/6/22 16:56
 * @describe:
 */
public class GoodsDetail {
    @JSONField(ordinal=1)
    private String spdm;//商品代码
    @JSONField(ordinal=2)
    private String spmc;//商品名称
    @JSONField(ordinal=3)
    private String spsl;//商品税率
    @JSONField(ordinal=4)
    private String spggxh;//商品规格型号
    @JSONField(ordinal=5)
    private String spdw;//商品单位
    @JSONField(ordinal=6)
    private String spdj;//商品单价
    @JSONField(ordinal=7)
    private String spbm;//商品和服务税收分类编码
    @JSONField(ordinal=8)
    public String getSpdm() {
        return spdm;
    }

    public void setSpdm(String spdm) {
        this.spdm = spdm;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public String getSpsl() {
        return spsl;
    }

    public void setSpsl(String spsl) {
        this.spsl = spsl;
    }

    public String getSpggxh() {
        return spggxh;
    }

    public void setSpggxh(String spggxh) {
        this.spggxh = spggxh;
    }

    public String getSpdw() {
        return spdw;
    }

    public void setSpdw(String spdw) {
        this.spdw = spdw;
    }

    public String getSpdj() {
        return spdj;
    }

    public void setSpdj(String spdj) {
        this.spdj = spdj;
    }

    public String getSpbm() {
        return spbm;
    }

    public void setSpbm(String spbm) {
        this.spbm = spbm;
    }
}
