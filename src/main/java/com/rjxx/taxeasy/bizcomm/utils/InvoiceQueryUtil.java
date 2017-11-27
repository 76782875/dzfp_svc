package com.rjxx.taxeasy.bizcomm.utils;

import com.jcraft.jsch.HASH;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.service.KplsService;
import com.rjxx.taxeasy.vo.Fpcxvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceQueryUtil {

    @Autowired
    private KplsService kplsService;

    /**
    *  @param gsdm,khh
     * @return list
     * 通过客户号，公司代码获取发票信息list
    */
    public List<Fpcxvo> getInvoiceListByKhh(String gsdm,String khh){
        List<Fpcxvo> list = new ArrayList<Fpcxvo>();
        Map params = new HashMap<String,Object>();
        params.put("gsdm",gsdm);
        params.put("khh",khh);
        params.put("fpzt","00");
        params.put("fpczlx","11");
        list = kplsService.findAllByParams2(params);
        return list;
    }

    /**
     *  @param gsdm,ddh
     * @return list
     * 通过订单号，公司代码获取发票信息list
     */
    public List<Fpcxvo> getInvoiceListByDdh(String gsdm,String ddh){
        List<Fpcxvo> list = new ArrayList<Fpcxvo>();
        Map params = new HashMap<String,Object>();
        params.put("gsdm",gsdm);
        params.put("ddh",ddh);
        params.put("fpzt","00");
        params.put("fpczlx","11");
        list = kplsService.findAllByParams2(params);
        return list;
    }
}
