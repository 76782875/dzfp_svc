package com.rjxx.taxeasy.bizcomm.utils;

import com.rjxx.taxeasy.domains.Skp;
import com.rjxx.taxeasy.domains.Xf;
import com.rjxx.taxeasy.service.SkpService;
import com.rjxx.taxeasy.service.XfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xlm on 2017/12/28.
 */

public class GetXfxx {
    public  static  Map getXfxx(Xf xf,Skp skp){
        Map map=new HashMap();

        if(null !=skp.getKpr() &&!skp.getKpr().equals("")){
            map.put("kpr",skp.getKpr());
        }else{
            map.put("kpr",xf.getKpr());
        }
        if(null !=skp.getFhr() &&!skp.getFhr().equals("")){
            map.put("fhr",skp.getFhr());
        }else{
            map.put("fhr",xf.getFhr());
        }
        if(null !=skp.getSkr() &&!skp.getSkr().equals("")){
            map.put("skr",skp.getSkr());
        }else{
            map.put("skr",xf.getSkr());
        }

        if(null!=skp.getLxdz()&&null!=skp.getLxdh()&&null!=skp.getYhzh()&&null!=skp.getKhyh()){
            map.put("xfdz",skp.getLxdz());
            map.put("xfdh",skp.getLxdh());
            map.put("xfyh",skp.getKhyh());
            map.put("xfyhzh",skp.getYhzh());
        }else {
            map.put("xfdz",xf.getXfdz());
            map.put("xfdh",xf.getXfdh());
            map.put("xfyh",xf.getXfyh());
            map.put("xfyhzh",xf.getXfyhzh());
        }
        return map;
    }
}
