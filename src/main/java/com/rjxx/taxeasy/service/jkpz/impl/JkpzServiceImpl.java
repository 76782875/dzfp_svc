package com.rjxx.taxeasy.service.jkpz.impl;

import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.rjxx.taxeasy.dao.SkpJpaDao;
import com.rjxx.taxeasy.dao.XfJpaDao;
import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.dto.AdapterPost;
import com.rjxx.taxeasy.invoice.KpService;
import com.rjxx.taxeasy.service.*;
import com.rjxx.taxeasy.service.jkpz.JkpzService;
import com.rjxx.taxeasy.vo.JkpzVo;
import com.rjxx.utils.CheckOrderUtil;
import com.rjxx.utils.StringUtils;
import com.rjxx.utils.jkpz.JkpzUtil;
import com.rjxx.utils.yjapi.Result;
import com.rjxx.utils.yjapi.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author: zsq
 * @date: 2018/3/20 18:10
 * @describe:接口配置实现
 */
@Service
public class JkpzServiceImpl implements JkpzService {

    @Autowired
    private JkpzzbService jkpzzbService;

    @Autowired
    private CszbService cszbService;

    @Autowired
    private SkpJpaDao skpJpaDao;

    @Autowired
    private XfJpaDao xfJpaDao;

    @Autowired
    private JkpzUtil jkpzUtil;

    @Autowired
    private GsxxService gsxxService;

    @Autowired
    private CheckOrderUtil checkOrderUtil;

    @Autowired
    private KpService kpService;

    private Logger logger = LoggerFactory.getLogger(JkpzServiceImpl.class);

    /**
     * 接口配置业务处理，封装数据
     * @param adapterPost
     * @return
     */
    public Result jkpzInvoice(AdapterPost adapterPost){

        Map resultMap=new HashMap();
        if(adapterPost==null){
            return ResultUtil.error("参数错误");
        }
        try {
            Jyxxsq jyxxsq = new Jyxxsq();
            List<Jymxsq> jymxsqList = new ArrayList();
            List<Jyzfmx> jyzfmxList = new ArrayList();
            Map map = new HashMap();
            map.put("appkey",adapterPost.getAppId());
            Gsxx gsxx = gsxxService.findOneByParams(map);
            String gsdm = gsxx.getGsdm();
            //处理销方
            Xf xf = new Xf();
            String xfsh = adapterPost.getTaxNo();
            try {
                if(StringUtils.isNotBlank(xfsh)){
                    xf = xfJpaDao.findOneByXfshAndGsdm(gsdm,xfsh);
                }else{
                    xf=xfJpaDao.findOneByGsdm(gsdm);
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
            //处理开票点
            String kpddm = adapterPost.getClientNo();
            Skp skp = new Skp();
            try {
                if(StringUtils.isNotBlank(kpddm)){
                    skp = skpJpaDao.findOneByKpddmAndGsdm(kpddm, gsdm);
                }else{
                    skp = skpJpaDao.findOneByGsdmAndXfsh(gsdm, xf.getId());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
           /* Xf  xf  = jkpzUtil.getXfBySh(gsdm, adapterPost.getTaxNo());
            Skp skp = jkpzUtil.defaultKpd(adapterPost.getClientNo(), gsdm, adapterPost.getTaxNo());*/
            Cszb cszb = cszbService.getSpbmbbh(gsdm, xf.getId(), skp.getId(), "jkpzmbid");
            if(cszb==null){
                return ResultUtil.error("模板未配置");
            }
            logger.info("取到的模板--"+cszb.getCsz());
            //获取数据模板
            List<JkpzVo> jkpzzbList = jkpzzbService.findByMbId(Integer.getInteger(cszb.getCsz()));
            if(jkpzzbList.isEmpty()){
                return ResultUtil.error("模板设置有误");
            }
            String result ="";
            //反射 封装数据
            for (JkpzVo jkpzVo : jkpzzbList) {
                Map paraMap = new HashMap();
                paraMap.put("gsxx", gsxx);
                paraMap.put("xf", xf);
                paraMap.put("skp", skp);
                paraMap.put("jyxxsq", jyxxsq);
                paraMap.put("jymxsqList", jymxsqList);
                paraMap.put("jyzfmxList", jyzfmxList);
                paraMap.put("adapterPost", adapterPost);
                paraMap.put("jkpzVo",jkpzVo);
                result += execute(jkpzVo.getCszff(), paraMap);
            }
            if(StringUtils.isNotBlank(result)){
                return ResultUtil.error(result);
            }
            //校验数据
            List<Jyxxsq> jyxxsqList = new ArrayList<>();
            jyxxsq.setLrsj(new Date());
            jyxxsq.setXgsj(new Date());
            jyxxsq.setLrry(1);
            jyxxsq.setXgry(1);
            jyxxsqList.add(jyxxsq);
            for (Jyzfmx jyzfmx : jyzfmxList) {
                jyzfmx.setLrsj(new Date());
                jyzfmx.setXgsj(new Date());
                jyzfmx.setLrry(1);
                jyzfmx.setXgry(1);
                jyzfmxList.add(jyzfmx);
            }
            for (Jymxsq jymxsq : jymxsqList) {
                jymxsq.setLrry(1);
                jymxsq.setXgry(1);
                jymxsq.setLrsj(new Date());
                jymxsq.setXgsj(new Date());
                jymxsqList.add(jymxsq);
            }
            String msg = checkOrderUtil.checkOrders(jyxxsqList,jymxsqList,jyzfmxList,gsdm,"");
            if(StringUtils.isNotBlank(msg)){
                return ResultUtil.error(msg);
            }
            //开票
            Map kpMap = new HashMap();
            kpMap.put("jyxxsqList",jyxxsqList);
            kpMap.put("jymxsqList",jymxsqList);
            kpMap.put("jyzfmxList",jyzfmxList);
            String resu = kpService.dealOrder(gsdm, kpMap, "01");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("ReturnCode","9999");
            resultMap.put("ReturnMessage","系统错误");
        }
        return ResultUtil.success();
    }

    /**
     *  反射接口配置util
     *  返回成功null 失败string
     */
    public String execute(String methodName, Map map){
        String result ="";
        try {
            Method target = jkpzUtil.getClass().getMethod(methodName,Map.class);
            result = (String) target.invoke(jkpzUtil,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
