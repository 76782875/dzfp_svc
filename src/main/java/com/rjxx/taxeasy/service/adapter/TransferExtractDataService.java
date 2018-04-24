package com.rjxx.taxeasy.service.adapter;

import com.alibaba.fastjson.JSON;
import com.rjxx.taxeasy.dao.JyxxsqJpaDao;
import com.rjxx.taxeasy.domains.Jymxsq;
import com.rjxx.taxeasy.domains.Jyxxsq;
import com.rjxx.taxeasy.domains.Jyzfmx;
import com.rjxx.taxeasy.dto.*;
import com.rjxx.taxeasy.service.JymxsqService;
import com.rjxx.taxeasy.service.JyzfmxService;
import com.rjxx.utils.NumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    private static Logger logger = LoggerFactory.getLogger(TransferExtractDataService.class);
    public AdapterPost seaway(String gsdm,String tq) {
        AdapterPost data = new AdapterPost();
        return data;
    }

    public AdapterPost kgc(String gsdm,String tq) {
        AdapterPost data = new AdapterPost();
        return data;
    }

    public AdapterPost fujifilm(String gsdm,String tq) {
        AdapterPost data = new AdapterPost();
        return data;
    }

    public AdapterPost jyxxsq(String gsdm,String tq) {
        logger.info("抽取数据KEY={}",tq);
        Jyxxsq jyxxsq = jyxxsqJpaDao.findOneByGsdmAndDdhAndFpzldm(gsdm, tq, "12");
        if(jyxxsq==null){
            logger.info("TPYE3根据订单号【"+tq+"】未找到数据");
            return null;
        }
        Map jymxsqParam = new HashMap();
        jymxsqParam.put("sqlsh", jyxxsq.getSqlsh());
        List<Jymxsq> jymxsqs = jymxsqService.findAllBySqlsh(jymxsqParam);
        Map jyzfmxparam = new HashMap<>();
        jyzfmxparam.put("gsdm", gsdm);
        jyzfmxparam.put("sqlsh", jyxxsq.getSqlsh());
        jyzfmxparam.put("orderBy", "asc");
        List<Jyzfmx> jyzfmxs = jyzfmxService.findAllByParams(jyzfmxparam);

        AdapterPost post = new AdapterPost();
        AdapterData data = new AdapterData();
        AdapterDataOrder order = new AdapterDataOrder();
        AdapterDataSeller seller = new AdapterDataSeller();
        AdapterDataOrderBuyer buyer = new AdapterDataOrderBuyer();
        List<AdapterDataOrderDetails> details = new ArrayList<>();
        List<AdapterDataOrderPayments> payments = new ArrayList<>();

        //明细
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
            detail.setUtil(jymxsq.getSpdw());
            detail.setRowType(jymxsq.getFphxz());
            detail.setTaxRate(jymxsq.getSpsl());
            detail.setTaxAmount(jymxsq.getSpse());
            detail.setVenderOwnCode(jymxsq.getSpzxbm());
            detail.setTaxRateMark(jymxsq.getLslbz());
            detail.setDeductAmount(jymxsq.getKce());
            details.add(detail);
        }

        //支付
        for (Jyzfmx jyzfmx:jyzfmxs){
            AdapterDataOrderPayments payment = new AdapterDataOrderPayments();
            payment.setPayPrice(jyzfmx.getZfje());
            payment.setPayCode(jyzfmx.getZffsDm());
            payments.add(payment);
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
        return post;
    }

    public static AdapterPost test(String gsdm,String tq) {
        logger.info("抽取数据KEY={}",tq);
        AdapterPost post = new AdapterPost();
        AdapterData data = new AdapterData();
        AdapterDataOrder order = new AdapterDataOrder();
        AdapterDataOrderBuyer buyer = new AdapterDataOrderBuyer();
        AdapterDataSeller seller = new AdapterDataSeller();
        List<AdapterDataOrderDetails> details = new ArrayList<>();
        List<AdapterDataOrderPayments> payments = new ArrayList<>();

        post.setClientNo("wyh_01");
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
            detail.setUtil("次");
            detail.setRowType("0");
            detail.setTaxRate(0.06);
            details.add(detail);
        }

        //支付
        AdapterDataOrderPayments payment = new AdapterDataOrderPayments();
        payment.setPayCode("02");
        payment.setPayPrice(5d);
        payments.add(payment);

        AdapterDataOrderPayments payment2 = new AdapterDataOrderPayments();
        payment2.setPayCode("04");
        payment2.setPayPrice(5d);
        payments.add(payment2);

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
        return post;
    }

    public static void main(String[] args) {
        test("gsdm","q");
    }
}
