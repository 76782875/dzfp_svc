package com.rjxx.taxeasy.dto;

import lombok.Data;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/3/20
 */
@Data
public class AdapterDataOrderBuyer {
    private String customerType;
    private String identifier;
    private String name;
    private String address;
    private String telephoneNo;
    private String bank;
    private String bankAcc;
    private String email;
    private String isSend;
    private String recipient;
    private String reciAddress;
    private String zip;
}
