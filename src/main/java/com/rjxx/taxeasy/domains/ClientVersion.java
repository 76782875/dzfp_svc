package com.rjxx.taxeasy.domains;

import javax.persistence.*;
import java.io.Serializable;

/**
 * t_client_version 实体类
 * 由GenEntityMysql类自动生成
 * Thu Mar 09 13:54:54 CST 2017
 *
 * @ZhangBing
 */
@Entity
@Table(name = "t_client_version")
public class ClientVersion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    /**
     * 版本号
     */
    @Column(name = "version")
    protected String version;

    /**
     * 下载地址
     */
    @Column(name = "download_url")
    protected String downloadUrl;

    /**
     * 版本顺序，主要用于获取最新版本，越新的版本越大
     */
    @Column(name = "version_order")
    protected Integer versionOrder;

    @Column(name = "yxbz")
    protected String yxbz;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Integer getVersionOrder() {
        return versionOrder;
    }

    public void setVersionOrder(Integer versionOrder) {
        this.versionOrder = versionOrder;
    }

    public String getYxbz() {
        return yxbz;
    }

    public void setYxbz(String yxbz) {
        this.yxbz = yxbz;
    }

}

