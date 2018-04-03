package com.rjxx.taxeasy.bizcomm.utils;

import com.rjxx.taxeasy.service.SpbmService;
import com.rjxx.taxeasy.vo.Spbm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xlm on 2018/3/16.
 */
@Service
public class GetLsvBz {

    @Autowired
    private SpbmService spbmService;


    public  Map getLsvBz(Double sl,String spbm){

        Map yhzcMap=new HashMap();
        Map spbmMap=new HashMap();
        spbmMap.put("spbm",spbm);
        Spbm spbmvo=spbmService.findOneByParams(spbmMap);
        if(null==spbmvo.getZzstsgl()||("").equals(spbmvo.getZzstsgl())||sl>0){
            yhzcMap.put("yhzcbs","0");
        }else{
            yhzcMap.put("yhzcbs","1");
        }
        if(sl>0){
            yhzcMap.put("lslbz",null);
            yhzcMap.put("yhzcmc",null);
        }else{
            yhzcMap.put("yhzcmc",spbmvo.getZzstsgl());
        }
        if(spbmvo.getZzstsgl().equals("免税")){
            yhzcMap.put("lslbz","1");
        }
        if(spbmvo.getZzstsgl().equals("不征税")){
            yhzcMap.put("lslbz","2");
        }
        if(sl==0){
            yhzcMap.put("lslbz","3");
        }

        return yhzcMap;
    }
}
