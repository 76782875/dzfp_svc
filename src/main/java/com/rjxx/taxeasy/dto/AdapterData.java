package com.rjxx.taxeasy.dto;

import lombok.Data;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/3/20
 */
@Data
public class AdapterData {
    private String serialNumber;
    private String invType;
    private String version;
    private String drawer;
    private String payee;
    private String reviewer;
    private String datasource;
    private String openid;
    private AdapterDataSeller seller;
    private AdapterDataOrder order;
}
