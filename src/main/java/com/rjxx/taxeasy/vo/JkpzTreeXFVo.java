package com.rjxx.taxeasy.vo;

import java.util.Map;

/**
 * @author: zsq
 * @date: 2018/3/28 19:36
 * @describe:
 */
public class JkpzTreeXFVo {
    private String id;
    private String text;
    private String parent;
    private Map state;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Map getState() {
        return state;
    }

    public void setState(Map state) {
        this.state = state;
    }
}
