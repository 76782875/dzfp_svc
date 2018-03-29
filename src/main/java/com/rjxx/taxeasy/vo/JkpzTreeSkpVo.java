package com.rjxx.taxeasy.vo;

/**
 * @author: zsq
 * @date: 2018/3/28 19:36
 * @describe:
 */
public class JkpzTreeSkpVo {
    private String title;
    private String type;
    private String selectedStatus;
    private  JkpzTreeAttr attr;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSelectedStatus() {
        return selectedStatus;
    }

    public void setSelectedStatus(String selectedStatus) {
        this.selectedStatus = selectedStatus;
    }

    public JkpzTreeAttr getAttr() {
        return attr;
    }

    public void setAttr(JkpzTreeAttr attr) {
        this.attr = attr;
    }
}
