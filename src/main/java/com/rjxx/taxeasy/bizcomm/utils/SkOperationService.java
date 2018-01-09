package com.rjxx.taxeasy.bizcomm.utils;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.client.HessianProxyFactory;
import com.pk.hessian.SKIService;
import com.pk.hessian.bean.FwqxxBean;
import com.pk.hessian.bean.JkglxxBean;
import com.pk.hessian.bean.ResultBean;
import com.rjxx.taxeasy.domains.Cszb;
import com.rjxx.taxeasy.domains.Fwqxx;
import com.rjxx.taxeasy.domains.Skp;
import com.rjxx.taxeasy.service.CszbService;
import com.rjxx.taxeasy.service.SkpService;
import com.sk.common.utils.SResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by xlm on 2018/1/9.
 */
@Service
public class SkOperationService {

    @Autowired
    private SkpService skpService;

    @Autowired
    private CszbService cszbService;


    /**
     * 税控服务器抄报税
     * @param fwqxx
     * @return
     */
    public String Cb(Fwqxx fwqxx){
        String resultxml=null;
        try {
        FwqxxBean fwqxxBean=new FwqxxBean();
        fwqxxBean.setId(fwqxx.getId());
        fwqxxBean.setJqbh(fwqxx.getJqbh());
        fwqxxBean.setIpdz(fwqxx.getIpdz());
        fwqxxBean.setDkh(fwqxx.getDkh());
        fwqxxBean.setFwqlx(fwqxx.getFwqlx());
        fwqxxBean.setFwqxh(fwqxx.getFwqxh());
        fwqxxBean.setFwqrl(fwqxx.getFwqrl());
        fwqxxBean.setFplxdm(fwqxx.getFplxdm());
        fwqxxBean.setQybz(fwqxx.getQybz());
        //fwqxx.setFwqbm(fwqxxMap.get("fwqbm").toString().trim());
        fwqxxBean.setFwqbbh(fwqxx.getFwqbbh());
        fwqxxBean.setKpdwdm(fwqxx.getKpdwdm());
        fwqxxBean.setKpdwmc(fwqxx.getKpdwmc());
        fwqxxBean.setSwjgdm(fwqxx.getSwjgdm());
        fwqxxBean.setSwjgmc(fwqxx.getSwjgmc());
        fwqxxBean.setSldz(fwqxx.getSldz());
        fwqxxBean.setSldkh(fwqxx.getSldkh());
        fwqxxBean.setKpjh(fwqxx.getKpjh());
        Map skpMap=new HashMap<>();
        skpMap.put("skph",fwqxx.getJqbh());
        Skp skp=skpService.findOneByParams(skpMap);
        Cszb cszb=cszbService.getSpbmbbh(skp.getGsdm(),skp.getXfid(),skp.getId(),"skurl");
        String url=cszb.getCsz().substring(0,cszb.getCsz().indexOf("SKServer"))+"SKServer/remoting/SKIService";
        HessianProxyFactory factory = new HessianProxyFactory();
        SKIService  skiService = (SKIService) factory.create(SKIService.class, url);
            JkglxxBean jkglxxBean=new JkglxxBean();
            jkglxxBean.setIpdz(fwqxx.getIpdz());
            jkglxxBean.setDkh(fwqxx.getDkh());
            jkglxxBean.setFplxdm(fwqxx.getFplxdm());
            jkglxxBean.setJqbh(fwqxx.getJqbh());
            SResult sResult=skiService.queryJkxx(jkglxxBean);
            Map<String, Object>  jkxxMap=sResult.getReturndata();
            fwqxxBean.setOne(jkxxMap);
            ResultBean resultBean=skiService.cb(fwqxxBean);
            System.out.println(JSON.toJSONString(resultBean));
            resultxml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                    "<Responese>\n" +
                    "    <ReturnCode>"+resultBean.getId()+"</ReturnCode>\n" +
                    "    <ReturnMessage>" + resultBean.getMsg() + "</ReturnMessage>\n" +
                    "</Responese>\n";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultxml;
    }
    /**
     * 税控服务器反写监控数据
     * @param fwqxx
     * @return
     */
    public String Fx(Fwqxx fwqxx){
        String resultxml=null;
        try {
            FwqxxBean fwqxxBean=new FwqxxBean();
            fwqxxBean.setId(fwqxx.getId());
            fwqxxBean.setJqbh(fwqxx.getJqbh());
            fwqxxBean.setIpdz(fwqxx.getIpdz());
            fwqxxBean.setDkh(fwqxx.getDkh());
            fwqxxBean.setFwqlx(fwqxx.getFwqlx());
            fwqxxBean.setFwqxh(fwqxx.getFwqxh());
            fwqxxBean.setFwqrl(fwqxx.getFwqrl());
            fwqxxBean.setFplxdm(fwqxx.getFplxdm());
            fwqxxBean.setQybz(fwqxx.getQybz());
            //fwqxx.setFwqbm(fwqxxMap.get("fwqbm").toString().trim());
            fwqxxBean.setFwqbbh(fwqxx.getFwqbbh());
            fwqxxBean.setKpdwdm(fwqxx.getKpdwdm());
            fwqxxBean.setKpdwmc(fwqxx.getKpdwmc());
            fwqxxBean.setSwjgdm(fwqxx.getSwjgdm());
            fwqxxBean.setSwjgmc(fwqxx.getSwjgmc());
            fwqxxBean.setSldz(fwqxx.getSldz());
            fwqxxBean.setSldkh(fwqxx.getSldkh());
            fwqxxBean.setKpjh(fwqxx.getKpjh());
            Map skpMap=new HashMap<>();
            skpMap.put("skph",fwqxx.getJqbh());
            Skp skp=skpService.findOneByParams(skpMap);
            Cszb cszb=cszbService.getSpbmbbh(skp.getGsdm(),skp.getXfid(),skp.getId(),"skurl");
            String url=cszb.getCsz().substring(0,cszb.getCsz().indexOf("SKServer"))+"SKServer/remoting/SKIService";
            HessianProxyFactory factory = new HessianProxyFactory();
            SKIService  skiService = (SKIService) factory.create(SKIService.class, url);
            JkglxxBean jkglxxBean=new JkglxxBean();
            jkglxxBean.setIpdz(fwqxx.getIpdz());
            jkglxxBean.setDkh(fwqxx.getDkh());
            jkglxxBean.setFplxdm(fwqxx.getFplxdm());
            jkglxxBean.setJqbh(fwqxx.getJqbh());
            SResult sResult=skiService.queryJkxx(jkglxxBean);
            Map<String, Object>  jkxxMap=sResult.getReturndata();
            fwqxxBean.setOne(jkxxMap);
            ResultBean resultBean=skiService.fx(fwqxxBean);
            System.out.println(JSON.toJSONString(resultBean));
            resultxml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                    "<Responese>\n" +
                    "    <ReturnCode>"+resultBean.getId()+"</ReturnCode>\n" +
                    "    <ReturnMessage>" + resultBean.getMsg() + "</ReturnMessage>\n" +
                    "</Responese>\n";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultxml;
    }
}
