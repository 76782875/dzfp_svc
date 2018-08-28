package com.rjxx.taxeasy.bizcomm.utils;

import com.rjxx.taxeasy.domains.*;
import com.rjxx.utils.NumberUtil;
import com.rjxx.utils.TemplateUtils;
import com.rjxx.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xlm on 2017/7/21.
 */
public class GetXmlUtil {

    private  static Logger logger = LoggerFactory.getLogger(GetXmlUtil.class);

    /**
     * 获取接口xml数据定义
     * @param jyxxsq
     * @param jymxsqList
     * @param
     * @return
     */
    public static String getFpkjXml(Jyxxsq jyxxsq, List<Jymxsq> jymxsqList,List<Jyzfmx> jyzfmxList){

        String templateName = "Fpkj.ftl";
        String jylssj = TimeUtil.formatDate(null == jyxxsq.getDdrq()? new Date():jyxxsq.getDdrq(), "yyyy-MM-dd HH:mm:ss");
        Map params2=new HashMap();
        params2.put("jyxxsq",jyxxsq);
        params2.put("jymxsqList",jymxsqList);
        params2.put("jylssj", jylssj);
        params2.put("count", jymxsqList.size());
        params2.put("jyzfmxList",jyzfmxList);
        String result=null;
        try {
            result = TemplateUtils.generateContent(templateName, params2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    /**
     * 获取接口xml数据定义
     * @param
     * @return
     */
    public static String getFpkjzpXml(Skp skp,String fplxdm,String kplx,String tspz, List<Jyspmx> jyspmxList,Kpls kpls){

        String templateName = "Fpkjzp.ftl";
        Map params2=new HashMap();
        params2.put("skp",skp);
        params2.put("fplxdm", NumberUtil.fplxdm(fplxdm));
        params2.put("kplx",kplx);
        params2.put("tspz",tspz);
        params2.put("jyspmxList",jyspmxList);
        params2.put("kce",jyspmxList.get(0).getKce());
        params2.put("count", jyspmxList.size());
        params2.put("kpls", kpls);
        String result=null;
        try {
            result = TemplateUtils.generateContent(templateName, params2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //发票打印
    public static String getFpdyXml(String kpdip,String fplxdm,String dylx,Kpls kpls){

        String templateName = "Fpdy.ftl";
        Map params2=new HashMap();
        params2.put("kpdip",kpdip);
        params2.put("fplxdm", NumberUtil.fplxdm(fplxdm));
        params2.put("dylx",dylx);
        params2.put("kpls", kpls);
        String result=null;
        try {
            result = TemplateUtils.generateContent(templateName, params2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    //发票作废-freemarker
    public static String getFpzfXml(String kpdip,String fplxdm,String zflx,Kpls kpls){

        String templateName = "Fpzf.ftl";
        Map params2=new HashMap();
        params2.put("kpdip",kpdip);
        params2.put("fplxdm", NumberUtil.fplxdm(fplxdm));
        params2.put("zflx",zflx);
        params2.put("kpls", kpls);
        params2.put("zfr", kpls.getKpr());
        String result=null;
        try {
            result = TemplateUtils.generateContent(templateName, params2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getFphczpXml(Skp skp,String fplxdm,String kplx,String tspz, List<Kpspmx> kpspmxList,Kpls kpls){

        String templateName = "Fpkjzp.ftl";
        Map params2=new HashMap();
        params2.put("skp",skp);
        params2.put("fplxdm", NumberUtil.fplxdm(fplxdm));
        params2.put("kplx",kplx);
        params2.put("tspz",tspz);
        params2.put("jyspmxList",kpspmxList);
        params2.put("kce",kpspmxList.get(0).getKce());
        params2.put("count", kpspmxList.size());
        params2.put("kpls", kpls);
        String result=null;
        try {
            result = TemplateUtils.generateContent(templateName, params2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
