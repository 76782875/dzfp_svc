package com.rjxx.utils.jkpz;

import com.rjxx.taxeasy.bizcomm.utils.GetLsvBz;
import com.rjxx.taxeasy.bizcomm.utils.GetXfxx;
import com.rjxx.taxeasy.bizcomm.utils.RemarkProcessingUtil;
import com.rjxx.taxeasy.dao.SkpJpaDao;
import com.rjxx.taxeasy.dao.XfJpaDao;
import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.dto.*;
import com.rjxx.taxeasy.service.CszbService;
import com.rjxx.taxeasy.service.SpvoService;
import com.rjxx.taxeasy.vo.JkpzVo;
import com.rjxx.taxeasy.vo.Spvo;
import com.rjxx.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author: zsq
 * @date: 2018/3/20 17:08
 * @describe: 接口配置类
 */
@Component
public class JkpzUtil {

    @Autowired
    private XfJpaDao xfJpaDao;
    @Autowired
    private SkpJpaDao skpJpaDao;
    @Autowired
    private CszbService cszbService;
    @Autowired
    private SpvoService spvoService;
    @Autowired
    private GetLsvBz getLsvBz;
    @Autowired
    private RemarkProcessingUtil remarkProcessingUtil;

    private static Logger logger = LoggerFactory.getLogger(JkpzUtil.class);

    public char getRandomLetter() {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        return chars.charAt(new Random().nextInt(26));
    }

    public String setDefSeriaNum(Map map) {
        Jyxxsq jyxxsq = (Jyxxsq) map.get("jyxxsq");
        jyxxsq.setJylsh("JY" + System.currentTimeMillis() + getRandomLetter());
        return null;
    }

    public String setDefOrderNo(Map map) {
        Jyxxsq jyxxsq = (Jyxxsq) map.get("jyxxsq");
        jyxxsq.setDdh("DD" + System.currentTimeMillis() + getRandomLetter());
        return null;
    }

    public String setDefOrderDate(Map map) {
        Jyxxsq jyxxsq = (Jyxxsq) map.get("jyxxsq");
        jyxxsq.setDdrq(new Date());
        return null;
    }

    public String getXfBySh(Map map) {
        Xf xf = (Xf) map.get("xf");
        Skp skp = (Skp) map.get("skp");
        Map xfxx = GetXfxx.getXfxx(xf, skp);
        Jyxxsq jyxxsq = (Jyxxsq) map.get("jyxxsq");
        jyxxsq.setXfmc(xf.getXfmc());
        jyxxsq.setXfsh(xf.getXfsh());
        jyxxsq.setXfdz((String) xfxx.get("xfdz"));
        jyxxsq.setXfdh((String) xfxx.get("xfdh"));
        jyxxsq.setXfyh((String) xfxx.get("xfyh"));
        jyxxsq.setXfyhzh((String) xfxx.get("xfyhzh"));
        return null;
    }

    public String defaultKpd(Map map) {
        Skp skp = (Skp) map.get("skp");
        Jyxxsq jyxxsq = (Jyxxsq) map.get("jyxxsq");
        if(StringUtils.isBlank(skp.getKpddm())){
            return "缺少开票点代码";
        }
        jyxxsq.setKpddm(skp.getKpddm());
        return null;
    }


    public String getCszb(Map map) {
        Skp skp = (Skp) map.get("skp");
        Xf xf = (Xf) map.get("xf");
        Gsxx gsxx = (Gsxx) map.get("gsxx");
        JkpzVo jkpzVo= (JkpzVo) map.get("jkpzVo");
        Jyxxsq jyxxsq = (Jyxxsq) map.get("jyxxsq");
        String csm=jkpzVo.getCsm();
        Cszb cszb = cszbService.getSpbmbbh(gsxx.getGsdm(), xf.getId(), skp.getId(), csm);
        String value=cszb.getCsz();
        switch (csm){
            case "mrfpzldm":
                jyxxsq.setFpzldm(value);
                break;
            case "sfdyqd":
                jyxxsq.setSfdyqd(value);
                break;
            case "sfcp":
                jyxxsq.setSfcp(value);
                break;
            case "sfdy":
                jyxxsq.setSfdy(value);
                break;
            case "mrzsfs":
                jyxxsq.setZsfs(value);
                break;
            case "hsbz":
                jyxxsq.setHsbz(value);
                break;
            case "bzmb":
                remarkProcessingUtil.dealRemark(jyxxsq,value);
                break;
            case "spbmbbh":
                break;
            default:
                return "未知的参数";
        }
        return null;
    }

    public String getPerson(Map map) {
        Xf xf = (Xf) map.get("xf");
        Skp skp = (Skp) map.get("skp");
        Map xfxx = GetXfxx.getXfxx(xf, skp);
        Jyxxsq jyxxsq = (Jyxxsq) map.get("jyxxsq");
        jyxxsq.setSkr((String) xfxx.get("skr"));
        jyxxsq.setFhr((String) xfxx.get("fhr"));
        String kpr=(String) xfxx.get("kpr");
        if(StringUtils.isBlank(kpr)){
            return "缺少开票人信息";
        }
        jyxxsq.setKpr(kpr);
        return null;
    }


    public String defaultSp(Map map){
        Gsxx gsxx = (Gsxx)map.get("gsxx");
        Xf xf = (Xf)map.get("xf");
        Skp skp = (Skp)map.get("skp");
        JkpzVo jkpzVo = (JkpzVo)map.get("jkpzVo");
        Spvo spvo = new Spvo();
        AdapterPost adapterPost = (AdapterPost)map.get("adapterPost");
        List<Jymxsq> jymxsqList = (List)map.get("jymxsqList");
        Jyxxsq jyxxsq = (Jyxxsq)map.get("jyxxsq");
        List<AdapterDataOrderDetails> details =null;
        try{
            details = adapterPost.getData().getOrder().getOrderDetails();
        }catch (Exception e){
            return "商品信息缺少金额，税额，价税合计标签";
        }
        try{
            Cszb cszb = cszbService.getSpbmbbh(gsxx.getGsdm(),xf.getId(),skp.getId(),jkpzVo.getCsm());
            if(cszb != null){
                String csz = cszb.getCsz();
                Map param = new HashMap();
                param.put("gsdm",gsxx.getGsdm());
                param.put("spdm",csz);
                spvo = spvoService.findOneSpvo(param);
            }
            int xh = 1;
            for(int i=0;i<details.size();i++){
                Jymxsq jymxsq  = new Jymxsq();
                AdapterDataOrderDetails ads = details.get(i);
                jymxsq.setDdh(jyxxsq.getDdh());
                jymxsq.setSpmxxh(xh);
                xh++;
                jymxsq.setFphxz(ads.getRowType());
                jymxsq.setSpdm(spvo.getSpbm());
                jymxsq.setSpmc(spvo.getSpmc());
                jymxsq.setSpggxh(spvo.getSpggxh());
                jymxsq.setSpzxbm(spvo.getSpdm());
                jymxsq.setSpdw(spvo.getSpdw());
                jymxsq.setSps(ads.getQuantity());
                jymxsq.setSpdj(ads.getUnitPrice());
                jymxsq.setKce(ads.getDeductAmount());
                jymxsq.setSpje(ads.getAmount());
                jymxsq.setSpse(ads.getTaxAmount());
                jymxsq.setJshj(ads.getMxTotalAmount());
                jymxsq.setYkjje(0d);
                jymxsq.setKkjje(ads.getMxTotalAmount());
                jymxsq.setYxbz("1");
                jymxsqList.add(jymxsq);
            }

        }catch (Exception e){
            e.printStackTrace();
            return "获取商品失败信息！";
        }
        return null;
    }


    public String getSpByid(Map map) {
        Spvo spvo = new Spvo();
        Gsxx gsxx = (Gsxx)map.get("gsxx");
        AdapterPost adapterPost = (AdapterPost)map.get("adapterPost");
        List<Jymxsq> jymxsqList = (List)map.get("jymxsqList");
        List<AdapterDataOrderDetails> details =null;
        Jyxxsq jyxxsq = (Jyxxsq)map.get("jyxxsq");
        try{
            details = adapterPost.getData().getOrder().getOrderDetails();
        }catch (Exception e){
            return "商品信息缺少金额，税额，价税合计标签";
        }
        try {
            int xh = 1;
            for(int i=0;i<details.size();i++){
                AdapterDataOrderDetails ads = details.get(i);
                Jymxsq jymxsq  = new Jymxsq();
                Map param = new HashMap();
                param.put("gsdm", gsxx.getGsdm());
                param.put("spdm", ads.getVenderOwnCode());
                spvo = spvoService.findOneSpvo(param);
                jymxsq.setDdh(jyxxsq.getDdh());
                jymxsq.setSpmxxh(xh);
                xh++;
                jymxsq.setFphxz(ads.getRowType());
                jymxsq.setSpdm(spvo.getSpbm());
                jymxsq.setSpmc(spvo.getSpmc());
                jymxsq.setSpggxh(spvo.getSpggxh());
                jymxsq.setSpzxbm(spvo.getSpdm());
                jymxsq.setSpdw(spvo.getSpdw());
                jymxsq.setSps(ads.getQuantity());
                jymxsq.setSpdj(ads.getUnitPrice());
                jymxsq.setKce(ads.getDeductAmount());
                jymxsq.setSpje(ads.getAmount());
                jymxsq.setSpse(ads.getTaxAmount());
                jymxsq.setJshj(ads.getMxTotalAmount());
                jymxsq.setYkjje(0d);
                jymxsq.setKkjje(ads.getMxTotalAmount());
                jymxsq.setYxbz("1");
                jymxsqList.add(jymxsq);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "获取商品信息错误";
        }
        return null;
    }

    public String getSpByMc(Map map) {
        Gsxx gsxx = (Gsxx)map.get("gsxx");
        AdapterPost adapterPost = (AdapterPost)map.get("adapterPost");
        List<Jymxsq> jymxsqList = (List)map.get("jymxsqList");
        List<AdapterDataOrderDetails> details =null;
        Jyxxsq jyxxsq = (Jyxxsq)map.get("jyxxsq");
        Spvo spvo = new Spvo();
        try{
            details = adapterPost.getData().getOrder().getOrderDetails();
        }catch (Exception e){
            return "商品信息缺少金额，税额，价税合计标签";
        }
        try {
            int xh = 1;
            for(int i=0;i<details.size();i++){
                AdapterDataOrderDetails ads = details.get(i);
                Jymxsq jymxsq  = new Jymxsq();
                Map param = new HashMap();
                param.put("gsdm", gsxx.getGsdm());
                param.put("spmc", ads.getProductName());
                spvo = spvoService.findOneSpvo(param);

                jymxsq.setDdh(jyxxsq.getDdh());
                jymxsq.setSpmxxh(xh);
                xh++;
                jymxsq.setFphxz(ads.getRowType());
                jymxsq.setSpdm(spvo.getSpbm());
                jymxsq.setSpmc(spvo.getSpmc());
                jymxsq.setSpggxh(spvo.getSpggxh());
                jymxsq.setSpzxbm(spvo.getSpdm());
                jymxsq.setSpdw(spvo.getSpdw());
                jymxsq.setSps(ads.getQuantity());
                jymxsq.setSpdj(ads.getUnitPrice());
                jymxsq.setKce(ads.getDeductAmount());
                jymxsq.setSpje(ads.getAmount());
                jymxsq.setSpse(ads.getTaxAmount());
                jymxsq.setJshj(ads.getMxTotalAmount());
                jymxsq.setYkjje(0d);
                jymxsq.setKkjje(ads.getMxTotalAmount());
                jymxsq.setYxbz("1");
                jymxsqList.add(jymxsq);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "获取商品信息错误";
        }
        return null;
    }

    public String getYhBySp(Map map) {
        Gsxx gsxx = (Gsxx)map.get("gsxx");
        List<Jymxsq> jymxsqList = (List)map.get("jymxsqList");
        Spvo spvo = new Spvo();

        try {
            for(int i=0;i<jymxsqList.size();i++){
                Jymxsq jymxsq = jymxsqList.get(i);
                Map param = new HashMap();
                param.put("gsdm", gsxx.getGsdm());
                param.put("spbm", jymxsq.getSpdm());
                spvo = spvoService.findOneSpvo(param);
                jymxsq.setLslbz(spvo.getLslbz());
                jymxsq.setYhzcbs(spvo.getYhzcbs());
                jymxsq.setYhzcmc(spvo.getYhzcmc());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "商品优惠信息处理有误！";
        }
        return null;
    }

    public String getYhByBmsl(Map map) {
        List<Jymxsq> jymxsqList = (List)map.get("jymxsqList");
        try {
            for(int i=0;i<jymxsqList.size();i++){
                Jymxsq jymxsq = jymxsqList.get(i);
                Map result = getLsvBz.getLsvBz(jymxsq.getSpsl(), jymxsq.getSpdm());
                jymxsq.setLslbz(String.valueOf(result.get("lslbz")));
                jymxsq.setYhzcbs(String.valueOf(result.get("yhzcbs")));
                jymxsq.setYhzcmc(String.valueOf(result.get("yhzcmc")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "商品优惠信息处理有误！";
        }
        return null;
    }




    public String param(Map map){
        String result ="";
        try {
            JkpzVo jkpzVo = (JkpzVo) map.get("jkpzVo");
            AdapterPost adapterPost = (AdapterPost) map.get("adapterPost");
            Xf xf = (Xf) map.get("xf");
            Gsxx gsxx = (Gsxx) map.get("gsxx");
            Jyxxsq jyxxsq = (Jyxxsq) map.get("jyxxsq");
            List<Jymxsq> jymxsqList = (List<Jymxsq>) map.get("jymxsqList");
            List<Jyzfmx> jyzfmxList = (List<Jyzfmx>) map.get("jyzfmxList");
            String pzcsm = jkpzVo.getPzcsm();
            if(adapterPost.getTaxNo() == null || adapterPost.getClientNo()==null){
                result="获取销方或开票点信息错误";
                return result;
            }
            AdapterData data = adapterPost.getData();
            if(data == null){
                result="获取主信息错误";
                return result;
            }
            //订单
            AdapterDataOrder order = adapterPost.getData().getOrder();
            if(order==null){
                result="获取订单信息错误";
                return result;
            }
            //销方
            AdapterDataSeller seller = adapterPost.getData().getSeller();
            if(seller == null){
                result="获取销方信息错误";
                return result;
            }
            //购方
            AdapterDataOrderBuyer buyer = adapterPost.getData().getOrder().getBuyer();
            if(buyer==null){
                result="获取购方信息错误";
                return result;
            }
            //商品信息list
            List<AdapterDataOrderDetails> orderDetailList = adapterPost.getData().getOrder().getOrderDetails();
            if(orderDetailList.isEmpty()){
                result="获取商品信息错误";
                return result;
            }
            //支付信息list
            List<AdapterDataOrderPayments> paymentsList = adapterPost.getData().getOrder().getPayments();
            Jymxsq jymxsq = new Jymxsq();
            switch (pzcsm){
                case "serialNumber":
                    jyxxsq.setJylsh(data.getSerialNumber());
                    break;
                case "orderNo":
                    jyxxsq.setDdh(order.getOrderNo());
                    break;
                case "orderDate":
                    jyxxsq.setDdrq(order.getOrderDate());
                    break;
                case "clientNo":
                    jyxxsq.setKpddm(adapterPost.getClientNo());
                    break;
                case "invType":
                    jyxxsq.setFpzldm(data.getInvType());
                    break;
                case "invoiceList":
                    jyxxsq.setSfdyqd(order.getInvoiceList());
                    break;
                case "invoiceSplit":
                    jyxxsq.setSfcp(order.getInvoiceSplit());
                    break;
                case "invoicePrint":
                    jyxxsq.setSfdy(order.getInvoiceSfdy());
                    break;
                case "chargeTaxWay":
                    jyxxsq.setZsfs(order.getChargeTaxWay());
                    break;
                case "taxMark":
                    jyxxsq.setHsbz(order.getTaxMark());
                    break;
                case "remark":
                    jyxxsq.setBz(order.getRemark());
                    break;
                case "version":
                    break;
                case "person":
                    jyxxsq.setKpr(data.getDrawer());
                    jyxxsq.setFhr(data.getReviewer());
                    jyxxsq.setSkr(data.getPayee());
                    break;
                case "seller":
                    jyxxsq.setXfid(xf.getId());
                    jyxxsq.setXfsh(seller.getIdentifier());
                    jyxxsq.setXfmc(seller.getName());
                    jyxxsq.setXfyh(seller.getBank());
                    jyxxsq.setXfyhzh(seller.getBankAcc());
                    jyxxsq.setXfdz(seller.getAddress());
                    jyxxsq.setXfdh(seller.getTelephoneNo());
                    break;
                case "items":
                    for (AdapterDataOrderDetails orderDetails : orderDetailList) {
                        int spmxxh = 0;
                        spmxxh++;
                        jymxsq.setDdh(jyxxsq.getDdh());
                        jymxsq.setHsbz(jyxxsq.getHsbz());
                        jymxsq.setSpmxxh(spmxxh);
                        jymxsq.setSpdm(orderDetails.getProductCode());
                        jymxsq.setSpmc(orderDetails.getProductName());
                        jymxsq.setSpggxh(orderDetails.getSpec());
                        jymxsq.setSpzxbm(orderDetails.getVenderOwnCode());
                        jymxsq.setSpdw(orderDetails.getUtil());
                        jymxsq.setSps(orderDetails.getQuantity());
                        jymxsq.setSpdj(orderDetails.getUnitPrice());
                        jymxsq.setSpje(orderDetails.getAmount());
                        jymxsq.setSpsl(orderDetails.getTaxRate());
                        jymxsq.setSpse(orderDetails.getTaxAmount());
                        jymxsq.setJshj(orderDetails.getMxTotalAmount());
                        jymxsq.setGsdm(gsxx.getGsdm());
                        jymxsqList.add(jymxsq);
                    }
                    break;
                case "policyMsg":
                    for (AdapterDataOrderDetails orderDetails : orderDetailList) {
                        jymxsq.setYhzcbs(orderDetails.getPolicyMark());
                        jymxsq.setYhzcmc(orderDetails.getPolicyName());
                        jymxsq.setLslbz(orderDetails.getTaxRateMark());
                        jymxsqList.add(jymxsq);
                    }
                    break;
                case "payments":
                    for (AdapterDataOrderPayments payments : paymentsList) {
                        Jyzfmx jyzfmx = new Jyzfmx();
                        jyzfmx.setGsdm(gsxx.getGsdm());
                        jyzfmx.setDdh(jyxxsq.getDdh());
                        jyzfmx.setZffsDm(payments.getPayCode());
                        jyzfmx.setZfje(payments.getPayPrice());
                        jyzfmxList.add(jyzfmx);
                    }
                    break;
                case "buyer":
                    jyxxsq.setGfsh(buyer.getIdentifier());
                    jyxxsq.setGfmc(buyer.getName());
                    jyxxsq.setGflx(buyer.getCustomerType());
                    jyxxsq.setGfyh(buyer.getBank());
                    jyxxsq.setGfyhzh(buyer.getBankAcc());
                    jyxxsq.setGflxr(buyer.getRecipient());
                    jyxxsq.setGfdz(buyer.getReciAddress());
                    jyxxsq.setGfdh(buyer.getTelephoneNo());
                    jyxxsq.setGfyb(buyer.getZip());
                    jyxxsq.setGfemail(buyer.getEmail());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result="获取数据错误";
            return result;
        }
        return null;
    }
}
