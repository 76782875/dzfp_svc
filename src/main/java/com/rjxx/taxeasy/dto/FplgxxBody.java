package com.rjxx.taxeasy.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author kzx
 * @company 上海容津信息技术有限公司
 * @date 2018/4/27
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "body")
public class FplgxxBody {
    private String  returncode;
    private String  returnmsg;
    private Returndata  returndata;

    public String getReturncode() {
        return returncode;
    }

    public void setReturncode(String returncode) {
        this.returncode = returncode;
    }

    public String getReturnmsg() {
        return returnmsg;
    }

    public void setReturnmsg(String returnmsg) {
        this.returnmsg = returnmsg;
    }

    public Returndata getReturndata() {
        return returndata;
    }

    public void setReturndata(Returndata returndata) {
        this.returndata = returndata;
    }
}
