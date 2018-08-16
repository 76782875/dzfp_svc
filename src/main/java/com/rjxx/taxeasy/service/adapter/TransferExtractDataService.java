package com.rjxx.taxeasy.service.adapter;

import com.alibaba.fastjson.JSON;
import com.rjxx.taxeasy.bizcomm.utils.GetDataService;
import com.rjxx.taxeasy.dao.JyxxsqJpaDao;
import com.rjxx.taxeasy.dao.SkpJpaDao;
import com.rjxx.taxeasy.dao.XfJpaDao;
import com.rjxx.taxeasy.dao.ZtbzJpaDao;
import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.dto.*;
import com.rjxx.taxeasy.service.*;
import com.rjxx.taxeasy.vo.Spvo;
import com.rjxx.utils.NumberUtil;
import com.rjxx.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/3/30
 */

@Component
public class TransferExtractDataService {
    @Autowired
    private JymxsqService jymxsqService;
    @Autowired
    private JyzfmxService jyzfmxService;
    @Autowired
    private JyxxsqJpaDao jyxxsqJpaDao;
    @Autowired
    private GetDataService getDataService;
    @Autowired
    private CszbService cszbService;
    @Autowired
    private ZtbzJpaDao ztbzJpaDao;
    @Autowired
    private SkpService skpService;
    @Autowired
    private SpvoService spvoService;
    @Autowired
    private JyxxService jyxxService;
    @Autowired
    private XfJpaDao xfJpaDao;
    @Autowired
    private SkpJpaDao skpJpaDao;

    private static Logger logger = LoggerFactory.getLogger(TransferExtractDataService.class);
    public Map seaway(String gsdm,String tq) {
        Map resultMap =new HashMap();
        AdapterPost data = new AdapterPost();
        resultMap.put("post",data);
        return resultMap;
    }

    /**
     * 绿地获取数据
     * @param gsdm
     * @param tq
     * @return
     */
    public Map ldyx(String gsdm,String tq) {
        Map resultMap =  new HashMap();
        try {
            logger.info("抽取数据KEY={}",tq);
            Jyxxsq jyxxsq = jyxxsqJpaDao.findOneByGsdmAndTqm(gsdm, tq);
            if(jyxxsq!=null){
                resultMap.put("msg","该订单已开具过");
                return resultMap;
            }
            AdapterPost data = new AdapterPost();
            Map map = getDataService.getldyxFirData(tq,gsdm);
            if(map==null){
                resultMap.put("msg", "系统出现异常，请重试！");
                return resultMap;
            }
            String accessToken = map.get("accessToken").toString();
            if(accessToken==null || "".equals(accessToken)){
                resultMap.put("msg", "未查询到数据，请重试！");
                return resultMap;
            }
            Map resMap = getDataService.getldyxSecData(tq,gsdm,accessToken);
            List<Jyxxsq> jyxxsqList = (List) resMap.get("jyxxsqList");
            List<Jymxsq> jymxsqList = (List) resMap.get("jymxsqList");
            List<Jyzfmx> jyzfmxList = (List) resMap.get("jyzfmxList");
            resultMap.put("jyxxsqList",jyxxsqList);
            resultMap.put("jymxsqList",jymxsqList);
            resultMap.put("jyzfmxList",jyzfmxList);
            resultMap.put("post",data);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("msg","获取数据失败，系统异常");
        }
        return resultMap;
    }

    /**
     * 全家获取数据
     * @param gsdm
     * @param tq
     * @return
     */
    public Map family(String gsdm,String tq) {
        Map resultMap =  new HashMap();
        try {
            logger.info("抽取数据KEY={}",tq);
            Jyxxsq jyxxsq = jyxxsqJpaDao.findOneByGsdmAndTqm(gsdm, tq);
            if(jyxxsq!=null){
                resultMap.put("msg","该订单已开具过");
                return resultMap;
            }
            AdapterPost data = new AdapterPost();
            Map resMap=getDataService.getData(tq,gsdm);
            String error = (String) resMap.get("error");
            String tmp = (String) resMap.get("tmp");
            if(resMap==null){
                resultMap.put("msg","获取数据失败");
                return resultMap;
            }
            if(StringUtils.isNotBlank(error)){
                resultMap.put("msg",error);
                return resultMap;
            }
            if(StringUtils.isNotBlank(tmp)){
                resultMap.put("msg",tmp);
                return resultMap;
            }
            List<Jyxxsq> jyxxsqList = (List) resMap.get("jyxxsqList");
            List<Jymxsq> jymxsqList = (List) resMap.get("jymxsqList");
            List<Jyzfmx> jyzfmxList = (List) resMap.get("jyzfmxList");
            resultMap.put("jyxxsqList",jyxxsqList);
            resultMap.put("jymxsqList",jymxsqList);
            resultMap.put("jyzfmxList",jyzfmxList);
            resultMap.put("post",data);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("msg","获取数据失败，系统异常");
        }
        return resultMap;
    }

    /**
     * 波奇网获取数据
     * @param gsdm
     * @param tq
     * @return
     */
    public Map bqw(String gsdm,String tq) {
        Map resultMap =  new HashMap();
        try {
            logger.info("抽取数据KEY={}",tq);
            Jyxxsq jyxxsq = jyxxsqJpaDao.findOneByGsdmAndTqm(gsdm, tq);
            if(jyxxsq!=null){
                resultMap.put("msg","该订单已开具过");
                return resultMap;
            }
            AdapterPost data = new AdapterPost();
            Cszb csz =  cszbService.getSpbmbbh(gsdm, null,null, "sfhhurl");
            Map resMap=getDataService.getDataForBqw(tq,gsdm,csz.getCsz());
            String error = (String) resMap.get("error");
            String tmp = (String) resMap.get("tmp");
            if(resMap==null){
                resultMap.put("msg","获取数据失败");
                return resultMap;
            }
            if(StringUtils.isNotBlank(error)){
                resultMap.put("msg",error);
                return resultMap;
            }
            if(StringUtils.isNotBlank(tmp)){
                resultMap.put("msg",tmp);
                return resultMap;
            }
            List<Jyxxsq> jyxxsqList = (List) resMap.get("jyxxsqList");
            List<Jymxsq> jymxsqList = (List) resMap.get("jymxsqList");
            List<Jyzfmx> jyzfmxList = (List) resMap.get("jyzfmxList");
            resultMap.put("jyxxsqList",jyxxsqList);
            resultMap.put("jymxsqList",jymxsqList);
            resultMap.put("jyzfmxList",jyzfmxList);
            resultMap.put("post",data);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("msg","获取数据失败，系统异常");
        }
        return resultMap;
    }

    public Map kgc(String gsdm,String tq) {
        Map resultMap =new HashMap();
        AdapterPost data = new AdapterPost();
        resultMap.put("post",data);
        return resultMap;
    }

    public Map fujifilm(String gsdm,String tq) {
        Map resultMap =new HashMap();
        AdapterPost data = new AdapterPost();
        resultMap.put("post",data);
        return resultMap;
    }

    /**
     * sqj数据封装
     * @param gsdm
     * @param tq
     * @return
     */
    public Map sqj(String gsdm,String tq) {
        Map resultMap =  new HashMap();
        if (tq.length() > 12) {
            String storeNo = tq.substring(8, 12);
            Map params = new HashMap();
            params.put("kpddm", storeNo);
            Skp skp = skpService.findOneByParams(params);
            if (skp == null) {
                //提取码不正确
                resultMap.put("msg","提取码有误,请核对！");
                return resultMap;
            }
            Cszb cszb = cszbService.getSpbmbbh("sqj", skp.getXfid(), skp.getId(), "dyspbmb");
            Map mapoo = new HashMap();
            mapoo.put("gsdm", "sqj");
            if (cszb.getCsz() != null) {
                mapoo.put("spdm", cszb.getCsz());
            }
            Spvo spvo = spvoService.findOneSpvo(mapoo);
            if(spvo == null){
                //提取码不正确
                resultMap.put("msg","提取码有误,请核对！");
                return resultMap;
            }
        } else {
            //提取码不正确
            resultMap.put("msg","提取码长度有误！");
            return resultMap;
        }
        logger.info("抽取数据KEY={}",tq);
        Map map = new HashMap<>();
        map.put("tqm", tq);
        map.put("gsdm", "sqj");
        Jyxx jyxx = jyxxService.findOneByParams(map);
        if(jyxx==null){
            logger.info("食其家根据提取码【"+tq+"】未找到数据");
            return null;
        }
        String orderNo = jyxx.getOrderNo();
        String orderTime = jyxx.getOrderTime();
        String price = jyxx.getPrice().toString();
        String storeNo = jyxx.getStoreNo();
        Skp skp = skpJpaDao.findOneByKpddmAndGsdm(storeNo, "sqj");
        Integer xfid = skp.getXfid(); //销方id
        Xf xf = xfJpaDao.findOneById(xfid);
        Integer kpdid = skp.getId();//税控盘id(开票点id)
        Jyxxsq jyxxsq = new Jyxxsq();
        jyxxsq.setJshj(Double.valueOf(price));
        jyxxsq.setDdh(orderNo);
        jyxxsq.setGsdm("sqj");
        jyxxsq.setKpddm(storeNo);
        jyxxsq.setXfmc(xf.getXfmc());
        jyxxsq.setKpr(xf.getKpr());
        jyxxsq.setFhr(xf.getFhr());
        jyxxsq.setSkr(xf.getSkr());
        jyxxsq.setXfid(xfid);
        jyxxsq.setXfsh(xf.getXfsh());
        jyxxsq.setXfyhzh(xf.getXfyhzh());
        jyxxsq.setXfyh(xf.getXfyh());
        jyxxsq.setXfdh(xf.getXfdh());//销方电话
        jyxxsq.setXfdz(xf.getXfdz());//销方地址
        jyxxsq.setXflxr(xf.getXflxr());//销方联系人
        jyxxsq.setXfyb(xf.getXfyb());//销方邮编
        jyxxsq.setJylsh(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + NumberUtil.getRandomLetter());
        jyxxsq.setFpzldm("12");
        jyxxsq.setFpczlxdm("11");
        jyxxsq.setSffsyj("1");
        jyxxsq.setZsfs("0");
        jyxxsq.setHsbz("1");
        jyxxsq.setSjly("6");//其他浏览器
        jyxxsq.setLrsj(new Date());
        jyxxsq.setXgsj(new Date());
        try {
            jyxxsq.setDdrq(new SimpleDateFormat("yyyyMMddHHmmss").parse(orderTime));
        } catch (ParseException e) {
            e.printStackTrace();
            jyxxsq.setDdrq(null);
        }
        jyxxsq.setTqm(orderNo);

        List<Jymxsq> jymxsqList = new ArrayList<>();

        Jymxsq jymxsq = new Jymxsq();
        jymxsq.setJshj(Double.valueOf(price));
        Cszb cszb = cszbService.getSpbmbbh("sqj", xfid, kpdid, "dyspbmb");
        Map mapoo = new HashMap();
        mapoo.put("gsdm", "sqj");
        if (cszb.getCsz() != null) {
            mapoo.put("spdm", cszb.getCsz());
        }
        Spvo spvo = spvoService.findOneSpvo(mapoo);
        jymxsq.setSpdm(spvo.getSpbm());
        jymxsq.setYhzcmc(spvo.getYhzcmc());
        jymxsq.setYhzcbs(spvo.getYhzcbs());
        jymxsq.setLslbz(spvo.getLslbz());
        jymxsq.setFphxz("0");
        jymxsq.setSpmc(spvo.getSpmc());
        jymxsq.setLrsj(new Date());
        jymxsq.setXgsj(new Date());
        jymxsq.setSpsl(spvo.getSl());
        jymxsq.setSpje(jymxsq.getJshj());
        jymxsq.setSpdj(jymxsq.getJshj());
        jymxsq.setSps(1d);
        jymxsqList.add(jymxsq);

        List<Jyzfmx> jyzfmxList = new ArrayList<>();
        AdapterPost post = new AdapterPost();
        AdapterData data = new AdapterData();
        AdapterDataOrder order = new AdapterDataOrder();
        AdapterDataSeller seller = new AdapterDataSeller();
        AdapterDataOrderBuyer buyer = new AdapterDataOrderBuyer();
        List<AdapterDataOrderDetails> details = new ArrayList<>();
        List<AdapterDataOrderPayments> payments = new ArrayList<>();

        //明细
        if(jymxsqList.size()>0){
            for(int i=0;i<jymxsqList.size();i++){
                Jymxsq jymxsq1 = jymxsqList.get(i);
                AdapterDataOrderDetails detail = new AdapterDataOrderDetails();
                detail.setAmount(jymxsq1.getSpje());
                detail.setMxTotalAmount(jymxsq1.getJshj());
                detail.setPolicyMark(jymxsq1.getYhzcbs());
                detail.setPolicyName(jymxsq1.getYhzcmc());
                detail.setProductCode(jymxsq1.getSpdm());
                detail.setProductName(jymxsq1.getSpmc());
                detail.setQuantity(jymxsq1.getSps());
                detail.setUnitPrice(jymxsq1.getSpdj());
                detail.setSpec(jymxsq1.getSpggxh());
                detail.setUnit(jymxsq1.getSpdw());
                detail.setRowType(jymxsq1.getFphxz());
                detail.setTaxRate(jymxsq1.getSpsl());
                detail.setTaxAmount(jymxsq1.getSpse());
                detail.setVenderOwnCode(jymxsq1.getSpzxbm());
                detail.setTaxRateMark(jymxsq1.getLslbz());
                detail.setDeductAmount(jymxsq1.getKce());
                details.add(detail);
            }
        }

        //支付
        if(jyzfmxList.size()>0){
            for (Jyzfmx jyzfmx:jyzfmxList){
                AdapterDataOrderPayments payment = new AdapterDataOrderPayments();
                payment.setPayPrice(jyzfmx.getZfje());
                payment.setPayCode(jyzfmx.getZffsDm());
                payments.add(payment);
            }
        }

        //订单
        order.setBuyer(buyer);
        order.setPayments(payments);
        order.setOrderDetails(details);
        order.setOrderNo(jyxxsq.getDdh());
        order.setOrderDate(jyxxsq.getDdrq());
        order.setTotalAmount(jyxxsq.getJshj());
        order.setRemark(jyxxsq.getBz());
        order.setInvoiceSfdy(jyxxsq.getSfdy());
        order.setTaxMark(jyxxsq.getHsbz());
        order.setInvoiceSplit(jyxxsq.getSfcp());
        order.setInvoiceList(jyxxsq.getSfdyqd());
        order.setChargeTaxWay(jyxxsq.getZsfs());
        order.setTotalDiscount(jyxxsq.getQjzk());
        order.setExtractedCode(jyxxsq.getTqm());

        //销方
        seller.setName(jyxxsq.getXfmc());
        seller.setIdentifier(jyxxsq.getXfsh());
        seller.setTelephoneNo(jyxxsq.getXfdh());
        seller.setAddress(jyxxsq.getXfdz());
        seller.setBank(jyxxsq.getXfyh());
        seller.setBankAcc(jyxxsq.getXfyhzh());

        //数据
        data.setSerialNumber(jyxxsq.getJylsh());
        data.setDrawer(jyxxsq.getKpr());
        data.setPayee(jyxxsq.getSkr());
        data.setReviewer(jyxxsq.getFhr());
        data.setOrder(order);
        data.setSeller(seller);

        post.setClientNo(jyxxsq.getKpddm());
        post.setData(data);
        logger.info("抽取的数据=【"+JSON.toJSONString(post)+"】");
        resultMap.put("post",post);
        resultMap.put("jyxxsq",jyxxsq);
        resultMap.put("jymxsqList",jymxsqList);
        resultMap.put("jyzfmxList",jyzfmxList);
        return resultMap;
    }


    public Map jyxxsq(String gsdm,String tq) {
        Map resultMap =new HashMap();
        logger.info("抽取数据KEY={}",tq);
        Jyxxsq jyxxsq=null;
        try {
//            jyxxsq = jyxxsqJpaDao.findOneByGsdmAndDdh(gsdm, tq);
            jyxxsq = jyxxsqJpaDao.findOneByGsdmAndTqm(gsdm, tq);
            if(jyxxsq==null){
                logger.info("TPYE3根据提取码【"+tq+"】未找到数据");
                return null;
            }
            String b = JyxxsqProcessed(jyxxsq);
            if(b!=null){
                logger.info("该订单"+b);
                resultMap.put("msg","该订单"+b);
                resultMap.put("jyxxsq", jyxxsq);
                return resultMap;
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询多条，获取数据失败！");
            resultMap.put("msg","获取数据失败或已红冲！");
            return resultMap;
        }
        Map jymxsqParam = new HashMap();
        jymxsqParam.put("sqlsh", jyxxsq.getSqlsh());
        List<Jymxsq> jymxsqs = jymxsqService.findAllBySqlsh(jymxsqParam);
        Map jyzfmxparam = new HashMap<>();
        jyzfmxparam.put("sqlsh", jyxxsq.getSqlsh());
        List<Jyzfmx> jyzfmxs = jyzfmxService.findAllByParams(jyzfmxparam);

        AdapterPost post = new AdapterPost();
        AdapterData data = new AdapterData();
        AdapterDataOrder order = new AdapterDataOrder();
        AdapterDataSeller seller = new AdapterDataSeller();
        AdapterDataOrderBuyer buyer = new AdapterDataOrderBuyer();
        List<AdapterDataOrderDetails> details = new ArrayList<>();
        List<AdapterDataOrderPayments> payments = new ArrayList<>();

        //明细
        if(jymxsqs.size()>0){
            for(int i=0;i<jymxsqs.size();i++){
                Jymxsq jymxsq = jymxsqs.get(i);
                AdapterDataOrderDetails detail = new AdapterDataOrderDetails();
                detail.setAmount(jymxsq.getSpje());
                detail.setMxTotalAmount(jymxsq.getJshj());
                detail.setPolicyMark(jymxsq.getYhzcbs());
                detail.setPolicyName(jymxsq.getYhzcmc());
                detail.setProductCode(jymxsq.getSpdm());
                detail.setProductName(jymxsq.getSpmc());
                detail.setQuantity(jymxsq.getSps());
                detail.setUnitPrice(jymxsq.getSpdj());
                detail.setSpec(jymxsq.getSpggxh());
                detail.setUnit(jymxsq.getSpdw());
                detail.setRowType(jymxsq.getFphxz());
                detail.setTaxRate(jymxsq.getSpsl());
                detail.setTaxAmount(jymxsq.getSpse());
                detail.setVenderOwnCode(jymxsq.getSpzxbm());
                detail.setTaxRateMark(jymxsq.getLslbz());
                detail.setDeductAmount(jymxsq.getKce());
                details.add(detail);
            }
        }

        //支付
        if(jyzfmxs.size()>0){
            for (Jyzfmx jyzfmx:jyzfmxs){
                AdapterDataOrderPayments payment = new AdapterDataOrderPayments();
                payment.setPayPrice(jyzfmx.getZfje());
                payment.setPayCode(jyzfmx.getZffsDm());
                payments.add(payment);
            }
        }

        //购方
        buyer.setMemberId(jyxxsq.getKhh());

        //订单
        order.setBuyer(buyer);
        order.setPayments(payments);
        order.setOrderDetails(details);
        order.setOrderNo(jyxxsq.getDdh());
        order.setOrderDate(jyxxsq.getDdrq());
        order.setTotalAmount(jyxxsq.getJshj());
        order.setRemark(jyxxsq.getBz());
        order.setInvoiceSfdy(jyxxsq.getSfdy());
        order.setTaxMark(jyxxsq.getHsbz());
        order.setInvoiceSplit(jyxxsq.getSfcp());
        order.setInvoiceList(jyxxsq.getSfdyqd());
        order.setChargeTaxWay(jyxxsq.getZsfs());
        order.setTotalDiscount(jyxxsq.getQjzk());
        order.setExtractedCode(jyxxsq.getTqm());

        //销方
        seller.setName(jyxxsq.getXfmc());
        seller.setIdentifier(jyxxsq.getXfsh());
        seller.setTelephoneNo(jyxxsq.getXfdh());
        seller.setAddress(jyxxsq.getXfdz());
        seller.setBank(jyxxsq.getXfyh());
        seller.setBankAcc(jyxxsq.getXfyhzh());

        //数据
        data.setSerialNumber(jyxxsq.getJylsh());
        data.setDrawer(jyxxsq.getKpr());
        data.setPayee(jyxxsq.getSkr());
        data.setReviewer(jyxxsq.getFhr());
        data.setOrder(order);
        data.setSeller(seller);

        post.setClientNo(jyxxsq.getKpddm());
        post.setData(data);

        logger.info("抽取的数据=【"+JSON.toJSONString(post)+"】");
        resultMap.put("post",post);
        resultMap.put("jyxxsq",jyxxsq);
        resultMap.put("jymxsqList",jymxsqs);
        resultMap.put("jyzfmxList",jyzfmxs);
        return resultMap;
    }

    public static Map test(String gsdm,String tq) {
        logger.info("抽取数据KEY={}",tq);
        AdapterPost post = new AdapterPost();
        AdapterData data = new AdapterData();
        AdapterDataOrder order = new AdapterDataOrder();
        AdapterDataOrderBuyer buyer = new AdapterDataOrderBuyer();
        AdapterDataSeller seller = new AdapterDataSeller();
        List<AdapterDataOrderDetails> details = new ArrayList<>();
        List<AdapterDataOrderPayments> payments = new ArrayList<>();

        post.setClientNo("seaway_01");
        post.setData(data);

        //明细
        for (int i = 2; i > 0; i--) {
            AdapterDataOrderDetails detail = new AdapterDataOrderDetails();
            detail.setAmount(5d);
            detail.setMxTotalAmount(5d);
            detail.setPolicyMark("0");
            detail.setProductCode("3070401000000000000");
            detail.setProductName("餐饮服务");
            detail.setQuantity(1d);
            detail.setUnitPrice(5d);
            detail.setSpec("规格型号");
            detail.setUnit("次");
            detail.setRowType("0");
            detail.setTaxRate(0.06);
            details.add(detail);
        }

        //购方
        buyer.setMemberId("khh12345678");

        //订单
        order.setBuyer(buyer);
        order.setPayments(payments);
        order.setOrderDetails(details);
        order.setOrderNo("DDH"+System.currentTimeMillis());
        order.setOrderDate(new Date());
        order.setTotalAmount(10d);
        order.setRemark("这是备注");
        order.setInvoiceSfdy("0");
        order.setTaxMark("1");
        order.setInvoiceSplit("1");
        order.setInvoiceList("0");
        order.setChargeTaxWay("0");

        //销方
        seller.setName("上海百旺测试3643");
        seller.setIdentifier("500102010003643");
        seller.setAddress("销方地址");
        seller.setTelephoneNo("110");
        seller.setBank("销方银行");
        seller.setBankAcc("123");

        //数据
        data.setDrawer("开票人");
        data.setPayee("收款人");
        data.setReviewer("复核人");
        data.setOrder(order);
        data.setSeller(seller);
        data.setSerialNumber("JY" + System.currentTimeMillis() + NumberUtil.getRandomLetter());


        logger.info("抽取的数据=【"+JSON.toJSONString(post)+"】");
        Map resultMap =new HashMap();
        resultMap.put("post",post);
        return resultMap;
    }

    public String JyxxsqProcessed(Jyxxsq jyxxsq){
        String ztbz = jyxxsq.getZtbz();
        Ztbz oneByZtbzdm = ztbzJpaDao.findOneByZtbzdm(ztbz);
        String filter = oneByZtbzdm.getFilter();
        String cancellFlag = filter.substring(0, 1);
        if("1".equals(cancellFlag)){
            return oneByZtbzdm.getZtbzmc();
        }else{
            return null;
        }
    }

    public static void main(String[] args) {
        test("gsdm","q");
    }
}
