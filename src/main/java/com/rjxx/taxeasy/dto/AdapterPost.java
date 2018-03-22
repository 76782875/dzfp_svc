package com.rjxx.taxeasy.dto;

import lombok.Data;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/3/20
 */
@Data
public class AdapterPost {
    private String appId;
    private String taxNo;
    private String clientNo;
    private String sign;
    private AdapterData data;
}
