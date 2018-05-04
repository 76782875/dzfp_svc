package com.rjxx.taxeasy.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author kzx
 * @company 上海容津信息技术有限公司
 * @date 2018/4/27
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lgxx")
public class Lgxx {
    List<Group> group;
    public List<Group> getGroup() {
        return group;
    }
    public void setGroup(List<Group> group) {
        this.group = group;
    }
}
