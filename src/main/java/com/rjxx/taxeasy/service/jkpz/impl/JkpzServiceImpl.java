package com.rjxx.taxeasy.service.jkpz.impl;

import com.alibaba.fastjson.JSON;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.rjxx.taxeasy.dao.SkpJpaDao;
import com.rjxx.taxeasy.dao.XfJpaDao;
import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.dto.AdapterPost;
import com.rjxx.taxeasy.service.*;
import com.rjxx.taxeasy.service.jkpz.JkpzService;
import com.rjxx.taxeasy.vo.JkpzVo;
import com.rjxx.utils.CheckOrderUtil;
import com.rjxx.utils.StringUtils;
import com.rjxx.utils.jkpz.JkpzUtil;
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

    private Logger logger = LoggerFactory.getLogger(JkpzServiceImpl.class);

    /**
     * 接口配置业务处理，封装数据
     * @param adapterPost
     * @return
     */
    public Map jkpzInvoice(AdapterPost adapterPost){

        Map resultMap=new HashMap();
        if(adapterPost==null){
            resultMap.put("ReturnCode","9999");
            resultMap.put("ReturnMessage","参数错误");
            return resultMap;
        }
        try {
            Jyxxsq jyxxsq = new Jyxxsq();
            List<Jymxsq> jymxsqList = new ArrayList();
            List<Jyzfmx> jyzfmxList = new ArrayList<Jyzfmx>();
            Map map = new HashMap();
            map.put("appkey",adapterPost.getAppId());
            Gsxx gsxx = gsxxService.findOneByParams(map);
            String gsdm = gsxx.getGsdm();
            Xf  xf  = jkpzUtil.getXfBySh(gsdm, adapterPost.getTaxNo());
            Skp skp = jkpzUtil.defaultKpd(adapterPost.getClientNo(), gsdm, adapterPost.getTaxNo());
            Cszb cszb = cszbService.getSpbmbbh(gsdm, xf.getId(), skp.getId(), "jkpzmbid");
            if(cszb==null){
                resultMap.put("ReturnCode","9999");
                resultMap.put("ReturnMessage","模板未配置");
                return resultMap;
            }
            logger.info("取到的模板--"+cszb.getCsz());
            List<JkpzVo> jkpzzbList = jkpzzbService.findByMbId(Integer.getInteger(cszb.getCsz()));
            if(jkpzzbList.isEmpty()){
                resultMap.put("ReturnCode","9999");
                resultMap.put("ReturnMessage","模板设置有误！");
                return resultMap;
            }
            String result ="";
            for (JkpzVo jkpzVo : jkpzzbList) {
                Map paraMap = new HashMap();
                paraMap.put("gsxx", gsxx);
                paraMap.put("xf", xf);
                paraMap.put("skp", skp);
                paraMap.put("jyxxsq", jyxxsq);
                paraMap.put("jymxsqList", jymxsqList);
                paraMap.put("jyzfmxList", jyzfmxList);
                paraMap.put("adapterPost", adapterPost);
                result += execute(jkpzVo.getCszff(), paraMap);
            }
            if(StringUtils.isNotBlank(result)){
                resultMap.put("ReturnCode","9999");
                resultMap.put("ReturnMessage",result);
                return resultMap;
            }
            List<Jyxxsq> jyxxsqList = new ArrayList<>();
            jyxxsqList.add(jyxxsq);
            String msg = checkOrderUtil.checkOrders(jyxxsqList,jymxsqList,jyzfmxList,gsdm,"");
            if(StringUtils.isNotBlank(msg)){
                resultMap.put("ReturnCode","9999");
                resultMap.put("ReturnMessage",msg);
                return resultMap;
            }
           /*     String jylsh ="";
                if(!jkpzVo.getPzcsm().isEmpty()&&jkpzVo.getPzcsm().equals("serialNumber")){
//                    logger.info("处理交易流水号");
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("param")){
//                        logger.info("通过接口获取用户传入参数值");
                        jylsh=adapterPost.getData().getSerialNumber();
                    }
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("setDefSerialNum")){
//                        logger.info("系统默认生成”JY”+System.currentTimeMillis()");
                        jylsh = jkpzUtil.setDefSeriaNum();
                    }
                }
                String ddh="";
                if(!jkpzVo.getPzcsm().isEmpty()&&jkpzVo.getPzcsm().equals("orderNo")){
//                    logger.info("处理订单号（必须）");
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("param")){
//                        logger.info("通过接口获取用户传入参数值");
                        ddh = adapterPost.getData().getOrder().getOrderNo();
                    }
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("setDefOrderNo")){
//                        logger.info("系统默认生成”DD” +System.currentTimeMillis()");
                        ddh = jkpzUtil.setDefOrderNo();
                    }
                }
                Date ddrq=null;
                if(!jkpzVo.getPzcsm().isEmpty()&&jkpzVo.getPzcsm().equals("orderDate")){
//                    logger.info("订单日期");
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("param")){
//                        logger.info("通过接口获取用户传入参数值");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                        ddrq = dateFormat.parse(adapterPost.getData().getOrder().getOrderDate());
                    }
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("setDefOrderDate")){
//                        logger.info("系统默认时间（new data）");
                        ddrq = jkpzUtil.setDefOrderDate();
                    }
                }
                String kpddm="";
                if(!jkpzVo.getPzcsm().isEmpty()&&jkpzVo.getPzcsm().equals("clientNo")){
//                    logger.info("开票点代码（必须）");
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("param")){
//                        logger.info("通过接口获取用户传入参数值");
                    }
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("defaultKpd")){
//                        logger.info("根据公司代码或销方获取开票点代码");
                    }
                }
                String fpzldm="";
                if(!jkpzVo.getPzcsm().isEmpty()&&jkpzVo.getPzcsm().equals("invType")){
                    logger.info("发票种类（01专票，02普票，12电子票）（必须）");
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("param")){
                        logger.info("通过接口获取用户传入参数值");
                    }
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("getCszb")){
//                        logger.info("单一票种通过参数设置，取用配置种类");
                        fpzldm = jkpzUtil.getCszb(gsdm,xf.getId(),skp.getId(),"mrfpzl");
                    }
                }
                String sfdyqd="";
                if(!jkpzVo.getPzcsm().isEmpty()&&jkpzVo.getPzcsm().equals("invoiceList")){
    //                logger.info("是否打印清单（纸票）（必须）");
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("param")){
                        logger.info("通过接口获取用户传入参数值");
                    }
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("getCszb")){
//                        logger.info("通过参数表中对应的参数（sfdyqd）获取数据");
                        sfdyqd = jkpzUtil.getCszb(gsdm,xf.getId(),skp.getId(),"sfdyqd");
                    }
                }
                String sfcp="";
                if(!jkpzVo.getPzcsm().isEmpty()&&jkpzVo.getPzcsm().equals("invoiceSplit")){
    //                logger.info("是否自动拆分（必须）");
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("param")){
                        logger.info("通过接口获取用户传入参数值");
                    }
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("getCszb")){
//                        logger.info("通过参数表中对应的参数获取数据");
                        sfcp = jkpzUtil.getCszb(gsdm,xf.getId(),skp.getId(),"sfzdcf");
                    }
                }
                String sfdy ="";
                if(!jkpzVo.getPzcsm().isEmpty()&&jkpzVo.getPzcsm().equals("invoicePrint")){
    //                logger.info("是否立即打印（纸票）（必须）");
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("param")){
                        logger.info("通过接口获取用户传入参数值");
                    }
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("getCszb")){
//                        logger.info("通过参数表中对应的参数获取数据");
                        sfdy = jkpzUtil.getCszb(gsdm,xf.getId(),skp.getId(),"sfljdy");
                    }
                }
                String zsfs="";
                if(!jkpzVo.getPzcsm().isEmpty()&&jkpzVo.getPzcsm().equals("chargeTaxWay")){
    //                logger.info("征税方式（0-普通征税，1-减按征税，2-差额征税）（必须）");
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("param")){
                        logger.info("通过接口获取用户传入参数值");
                    }
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("getCszb")){
//                        logger.info("通过参数表中对应的参数获取数据");
                        zsfs = jkpzUtil.getCszb(gsdm,xf.getId(),skp.getId(),"mrzsfs");
                    }
                }
                String hsbz="";
                if(!jkpzVo.getPzcsm().isEmpty()&&jkpzVo.getPzcsm().equals("taxMark")){
    //                logger.info("含税标志（1、含税；0、不含税）（必须）");
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("param")){
                        logger.info("通过接口获取用户传入参数值");
                    }
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("getCszb")){
//                        logger.info("通过参数表中对应的参数获取数据");
                        hsbz = jkpzUtil.getCszb(gsdm,xf.getId(),skp.getId(),"hsbz");
                    }
                }
                String bz="";
                if(!jkpzVo.getPzcsm().isEmpty()&&jkpzVo.getPzcsm().equals("remark")){
    //                logger.info("备注（A,表示原有备注放在处理备注之后，B在之前,例：A订单号：$ddh）");
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("param")){
                        logger.info("通过接口获取用户传入参数值");
                    }
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("getCszb")){
//                        logger.info("通过参数表中对应的参数（bzmb）处理数据");
                        bz = jkpzUtil.getCszb(gsdm, xf.getId(), skp.getId(),"bzmb");
                    }
                }
                String spbmbbh="";
                if(!jkpzVo.getPzcsm().isEmpty()&&jkpzVo.getPzcsm().equals("version")){
    //                logger.info("商品编码版本号（必须）");
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("param")){
                        logger.info("通过接口获取用户传入参数值");
                    }
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("getCszb")){
//                        logger.info("通过参数表中对应的参数（spbmbbh）处理数据");
                        spbmbbh =  jkpzUtil.getCszb(gsdm,xf.getId(),skp.getId(),"spbmbbh");
                    }
                }
                Map personMap = new HashMap();
                String kpr="";
                String fhr="";
                String shr ="";
                if(!jkpzVo.getPzcsm().isEmpty()&&jkpzVo.getPzcsm().equals("person")){
    //                logger.info("落款人（开票人，收款人，复核人）（必须）");
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("param")){
                        logger.info("通过接口获取用户传入参数值（收款人，复核，开票人）");
                    }
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("getPerson")){
//                        logger.info("通过参数表中对应的参数获取数据");
                        personMap = jkpzUtil.getPerson(null, null, null, kpddm, xf.getId(), gsdm);
                        kpr = personMap.get("kpr").toString();
                        fhr = personMap.get("fhr").toString();
                        shr = personMap.get("shr").toString();
                    }
                }
                Xf xf1 = new Xf();
                if(!jkpzVo.getPzcsm().isEmpty()&&jkpzVo.getPzcsm().equals("seller")){
    //                logger.info("销方全信息（必须）");
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("param")){
//                        logger.info("通过用户接口传入值（xfsh，xfmc，xfdz，xfdh，xfyh，xfyhzh）");
//                        xf1.setXfsh();
                    }
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("getXfBySh")){
                        logger.info("xfb方法通过销方税号从平台销方表获取商品全信息");
                    }
                }
                List<Jymxsq> spList=new ArrayList();
                List list = adapterPost.getData().getOrder().getOrderDetails();
                for (int i = 0 ;i<list.size();i++) {
                    //商品
                    if(!jkpzVo.getPzcsm().isEmpty()&&jkpzVo.getPzcsm().equals("items")){
                        //                logger.info("商品全信息（必须）");
                        if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("param")){
                            logger.info("通过用户接口传入值（spdm，spbm，spmc，spggxh，spdw，spdj，sps等信息）");
                        }
                        if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("defaultSp")){
                            logger.info("csb方法通过参数名称从平台参数表和参数子表获取参数值，根据参数值再获取商品全信息");
                        }
                        if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("getSpByid")){
                            logger.info("spb方法通过商品代码从平台商品表获取商品全信息");
                        }
                        if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("getSpByMc")){
                            logger.info("spb方法通过商品名称从平台商品表获取商品全信息");
                        }
                    }
                    //优惠政策
                    Map yhzcMap = new HashMap();
                    if(!jkpzVo.getPzcsm().isEmpty()&&jkpzVo.getPzcsm().equals("policyMsg")){
                        //                logger.info("商品优惠信息（必须）");
                        if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("param")){
//                        logger.info("通过用户接口传入值（lslbz，yhzcbz，yhzcmc）");
                            yhzcMap.put("lslbz",adapterPost.getData().getOrder().getOrderDetails().get(i).getTaxRateMark());
                            yhzcMap.put("yhzcbz",adapterPost.getData().getOrder().getOrderDetails().get(i).getPolicyMark());
                            yhzcMap.put("yhzcmc",adapterPost.getData().getOrder().getOrderDetails().get(i).getPolicyName());
                        }
                        if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("getYhBySp")){
                            logger.info("通过商品编码查询spvo获取");
                        }
                        if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("getYhByBmsl")){
                            logger.info("通过商品编码和税率规则匹配");
                        }
                    }
                        int spmxxh = 0;
                        Jymxsq jymxsq = new Jymxsq();
                        spmxxh++;
                        jymxsq.setDdh(ddh);
                        jymxsq.setKpddm(kpddm);
                        jymxsq.setHsbz(hsbz);
                        jymxsq.setSpmxxh(spmxxh);
                        jymxsq.setFphxz(spList.get(i).getFphxz());
                        jymxsq.setSpdm(spList.get(i).getSpdm());
                        jymxsq.setSpmc(spList.get(i).getSpmc());
                        jymxsq.setSpggxh(spList.get(i).getSpggxh());
                        jymxsq.setSpzxbm(spList.get(i).getSpzxbm());
                        jymxsq.setYhzcbs(yhzcMap.get("yhzcbz").toString());
                        jymxsq.setYhzcmc(yhzcMap.get("yhzcmc").toString());
                        jymxsq.setLslbz(yhzcMap.get("lslbz").toString());
                        jymxsq.setSpdw(spList.get(i).getSpdw());
                        jymxsq.setSps(spList.get(i).getSps());
                        jymxsq.setSpdj(spList.get(i).getSpdj());
                        jymxsq.setSpje(spList.get(i).getSpje());
                        jymxsq.setSpsl(spList.get(i).getSpsl());
                        jymxsq.setSpse(spList.get(i).getSpse());
                        jymxsq.setKce(spList.get(i).getKce());
                        jymxsq.setJshj(spList.get(i).getJshj());
                        jymxsq.setLrsj(new Date());
                        jymxsq.setGsdm(gsdm);
                        jymxsq.setSkpid(skp.getId());
                        jymxsq.setSpbz(null);
                        jymxsqList.add(jymxsq);
                }



                List zfList = new ArrayList();
                if(!jkpzVo.getPzcsm().isEmpty()&&jkpzVo.getPzcsm().equals("payments")){
    //                logger.info("支付信息（必须）");
                    if(!jkpzVo.getCszff().isEmpty()&&jkpzVo.getCszff().equals("param")){
//                        logger.info("通过接口获取用户传入参数值（支付方式代码，支付金额）");

                    }
                }
                jyxxsq.setKpddm(kpddm);
                jyxxsq.setJylsh(jylsh);
                jyxxsq.setDdrq(ddrq);
                jyxxsq.setDdh(ddh);
                jyxxsq.setFpzldm(fpzldm);
                jyxxsq.setFpczlxdm("11");//发票操作类型代码
                jyxxsq.setXfid(xf1.getId());
                jyxxsq.setXfsh(xf1.getXfsh());
                jyxxsq.setXfmc(xf1.getXfmc());
                jyxxsq.setXfyh(xf1.getXfyh());
                jyxxsq.setXfyhzh(xf1.getXfyhzh());
                jyxxsq.setXflxr(xf1.getXflxr());
                jyxxsq.setXfdz(xf1.getXfdz());
                jyxxsq.setXfdh(xf1.getXfdh());
                jyxxsq.setXfyb(xf1.getXfyb());
                jyxxsq.setClztdm("00");
                jyxxsq.setBz(bz);
                jyxxsq.setSkr(shr);
                jyxxsq.setFhr(fhr);
                jyxxsq.setKpr(kpr);
                jyxxsq.setSfcp(sfcp);
                jyxxsq.setSfdyqd(sfdyqd);
                jyxxsq.setZsfs(zsfs);
                jyxxsq.setHsbz(hsbz);
                jyxxsq.setJshj(adapterPost.getData().getOrder().getTotalAmount().doubleValue());
                jyxxsq.setYxbz("1");
                jyxxsq.setLrsj(new Date());
                jyxxsq.setGsdm(gsdm);
                jyxxsq.setTqm(adapterPost.getData().getOrder().getExtractedCode());
                jyxxsq.setSkpid(skp.getId());
                jyxxsq.setSjly(adapterPost.getData().getDatasource());
                jyxxsq.setOpenid(adapterPost.getData().getOpenid());
                jyxxsq.setSfdy(sfdy);
                jyxxsq.setQjzk(adapterPost.getData().getOrder().getTotalDiscount().doubleValue());
                jyxxsq.setGfsh(adapterPost.getData().getOrder().getBuyer().getIdentifier());
                jyxxsq.setGfmc(adapterPost.getData().getOrder().getBuyer().getName());
                jyxxsq.setGflx(adapterPost.getData().getOrder().getBuyer().getCustomerType());
                jyxxsq.setGfyh(adapterPost.getData().getOrder().getBuyer().getBank());
                jyxxsq.setGfyhzh(adapterPost.getData().getOrder().getBuyer().getBankAcc());
                jyxxsq.setGflxr(adapterPost.getData().getOrder().getBuyer().getRecipient());
                jyxxsq.setGfdz(adapterPost.getData().getOrder().getBuyer().getReciAddress());
                jyxxsq.setGfdh(adapterPost.getData().getOrder().getBuyer().getTelephoneNo());
                jyxxsq.setGfyb(adapterPost.getData().getOrder().getBuyer().getZip());
                jyxxsq.setGfemail(adapterPost.getData().getOrder().getBuyer().getEmail());*/


        } catch (Exception e) {
            e.printStackTrace();
           resultMap.put("ReturnCode","9999");
           resultMap.put("ReturnMessage","系统错误");
        }
        return resultMap;
    }

    /**
     *  反射接口配置util
     *  返回
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
