package com.rjxx.taxeasy.bizcomm.utils;

import com.rjxx.taxeasy.domains.Jymxsq;
import com.rjxx.taxeasy.domains.Jyxxsq;
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
    public static String getFpkjXml(Jyxxsq jyxxsq, List<Jymxsq> jymxsqList){

        String templateName = "Fpkj.ftl";
        String jylssj = TimeUtil.formatDate(null == jyxxsq.getDdrq()? new Date():jyxxsq.getDdrq(), "yyyy-MM-dd HH:mm:ss");
        Map params2=new HashMap();
        params2.put("jyxxsq",jyxxsq);
        params2.put("jymxsqList",jymxsqList);
        params2.put("jylssj", jylssj);
        params2.put("count", jymxsqList.size());
        String result=null;
        try {
            result = TemplateUtils.generateContent(templateName, params2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
