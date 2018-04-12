package com.rjxx.taxeasy.bizcomm.utils;

import com.rjxx.taxeasy.service.CszbService;
import com.rjxx.taxeasy.vo.CsbVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zsq
 * @date: 2018/4/11 19:05
 * @describe:根据公司代码，参数代码查看参数设置。
 */
@Service
public class GetCszService {

    @Autowired
    private CszbService cszbService;

    public  List getCsz(String gsdm,String csm){
        List resultList = new ArrayList();
        try {
            Map map = new HashMap();
            map.put("gsdm",gsdm);
            map.put("csm",csm);
            resultList = cszbService.findAllByGsdmAndCsm(map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return resultList;
    }

}
