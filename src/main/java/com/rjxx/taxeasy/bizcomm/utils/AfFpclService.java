package com.rjxx.taxeasy.bizcomm.utils;

import com.alibaba.fastjson.JSONObject;
import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.service.*;
import com.rjxx.taxeasy.vo.JyspmxDecimal2;
import com.rjxx.taxeasy.vo.KplsVO4;
import com.rjxx.taxeasy.vo.KplsVO5;
import com.rjxx.taxeasy.vo.Kpspmxvo;
import com.rjxx.time.TimeUtil;
import com.rjxx.utils.TemplateUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AfFpclService {
    @Autowired
    private KplsService kplsService;
    @Autowired
    private JylsService jylsService;
    @Autowired
    private SkService skService;
    @Autowired
    private SkpService skpService;
    @Autowired
    private JyspmxService jymxService;
    @Autowired
    private GsxxService gsxxService;
    @Autowired
    private KpspmxService kpspmxService;
    @Autowired
    private DataOperte dc;
    @Autowired
    private XfService xfService;
    @Autowired
    private JyxxsqService jyxxsqService;
    @Autowired
    private ServerSend serversendservice;
    @Autowired
    private DataOperate dataOperate;
    @Autowired
    private InvoiceSplitParamsUtil invoiceSplitParamsUtil;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 保存开票流水
     *
     * @param jyls
     * @return
     */
    public Kpls saveKpls(Jyls jyls, List<Jyspmx> jyspmx1, String dybz, String kpfs) throws Exception {
        Kpls kpls = new Kpls();
        kpls.setDjh(jyls.getDjh());
        kpls.setJylsh(jyls.getJylsh());
        kpls.setJylssj(jyls.getJylssj());
        kpls.setGsdm(jyls.getGsdm());
        kpls.setLrry(jyls.getLrry());
        kpls.setLrsj(TimeUtil.getNowDate());
        kpls.setXgry(jyls.getXgry());
        kpls.setXgsj(TimeUtil.getNowDate());
        kpls.setBz(jyls.getBz());
        kpls.setFpczlxdm(jyls.getFpczlxdm());
        kpls.setFpzldm(jyls.getFpzldm());
        kpls.setGfdh(jyls.getGfdh());
        kpls.setGfdz(jyls.getGfdz());
        if (dybz != null && dybz.equals("1")) {
            kpls.setPrintflag("2");
        } else {
            kpls.setPrintflag("0");
        }
        kpls.setGfmc(jyls.getGfmc());
        kpls.setGfsh(jyls.getGfsh());
        kpls.setGfyh(jyls.getGfyh());
        kpls.setGfyhzh(jyls.getGfyhzh());
        kpls.setGfemail(jyls.getGfemail());
        kpls.setGflxr(jyls.getGflxr());
        kpls.setFhr(jyls.getFhr());
        kpls.setKpr(jyls.getKpr());
        kpls.setSkr(jyls.getSkr());
        kpls.setXfid(jyls.getXfid());
        kpls.setXfsh(jyls.getXfsh());
        kpls.setXfmc(jyls.getXfmc());
        kpls.setXfdz(jyls.getXfdz());
        kpls.setXfdh(jyls.getXfdh());
        kpls.setXfyh(jyls.getXfyh());
        kpls.setXfyhzh(jyls.getXfyhzh());
        kpls.setSerialorder(jyls.getJylsh() + jyls.getDdh());
        String fpczlxdm = jyls.getFpczlxdm();
        if ("12".equals(fpczlxdm) || "13".equals(fpczlxdm) || "23".equals(fpczlxdm)) {
            //红冲或换开操作
            kpls.setHzyfpdm(jyls.getYfpdm());
            kpls.setHzyfphm(jyls.getYfphm());
            kpls.setHcrq(jyls.getLrsj());
            kpls.setHcry(jyls.getLrry());
            if ("12".equals(fpczlxdm)) {
                kpls.setHkbz("0");
            } else if ("13".equals(fpczlxdm)) {
                kpls.setHkbz("1");
            }
        }
        double hjje = 0;
        double hjse = 0;
        for (Jyspmx jyspmx : jyspmx1) {
            hjje += jyspmx.getSpje().doubleValue();
            hjse += jyspmx.getSpse().doubleValue();
        }
        double jshj = hjje + hjse;
        kpls.setHjje(hjje);
        kpls.setHjse(hjse);
        kpls.setJshj(jshj);
        kpls.setSfdyqd(jyls.getSfdyqd());
        kpls.setYxbz("1");
        kpls.setFpztdm("14");
        kpls.setSkpid(jyls.getSkpid());
        kplsService.save(kpls);
        return kpls;
    }

    /**
     * 保存开票商品明细
     *
     * @param kpls
     * @param
     * @return
     */
    public void saveKpspmx(Kpls kpls, List<Jyspmx> jyspmx1) throws Exception {
        int kplsh = kpls.getKplsh();
        for (Jyspmx jyspmx : jyspmx1) {
            Kpspmx kpspmx = new Kpspmx();
            kpspmx.setKplsh(kplsh);
            kpspmx.setDjh(jyspmx.getDjh());
            kpspmx.setSpmxxh(jyspmx.getSpmxxh());
            kpspmx.setSpdm(jyspmx.getSpdm());
            kpspmx.setSpmc(jyspmx.getSpmc());
            kpspmx.setFphxz(jyspmx.getFphxz());
            kpspmx.setSpggxh(jyspmx.getSpggxh());
            if (jyspmx.getSpdj() != null) {
                kpspmx.setSpdj(jyspmx.getSpdj().doubleValue());
            }
            kpspmx.setSpdw(jyspmx.getSpdw());
            if (jyspmx.getSps() != null) {
                kpspmx.setSps(jyspmx.getSps().doubleValue());
            }
            kpspmx.setSpse(jyspmx.getSpse().doubleValue());
            kpspmx.setSpje(jyspmx.getSpje().doubleValue());
            kpspmx.setSpsl(jyspmx.getSpsl().doubleValue());
            kpspmx.setGsdm(kpls.getGsdm());
            kpspmx.setLrry(kpls.getLrry());
            kpspmx.setXgry(kpls.getXgry());
            kpspmx.setLrsj(TimeUtil.getNowDate());
            kpspmx.setXgsj(TimeUtil.getNowDate());
            kpspmx.setKhcje(jyspmx.getJshj().doubleValue());
            if (null == jyspmx.getKce()) {
                kpspmx.setKce(0d);
            } else {
                kpspmx.setKce(jyspmx.getKce().doubleValue());
            }
            kpspmx.setYhzcbs(jyspmx.getYhzcbs());
            kpspmx.setYhzcmc(jyspmx.getYhzcmc());
            kpspmx.setLslbz(jyspmx.getLslbz());
            kpspmx.setYhcje(0d);
            kpspmxService.save(kpspmx);
        }
    }


    public Kpls saveKp(Jyls jyls1, List<Jyspmx> list, String dybz) throws Exception {
        Kpls kpls = saveKpls(jyls1, list, dybz, "01");
        saveKpspmx(kpls, list);
        return kpls;
    }

    /**
     * 直接开票
     */
    public List<Object> afzjkp(List<Jyxxsq> list, String kpfs) throws Exception {
        List<Object> result = new ArrayList<>();
        boolean sfqzfp = true;
        for (Jyxxsq jyxxsq : list) {
            // 转换明细
            Map<String, Object> params1 = new HashMap<>();
            params1.put("sqlsh", jyxxsq.getSqlsh());
            List<JyspmxDecimal2> jyspmxs = jymxService.getNeedToKP4(params1);
            double spje = 0d;
            double spse = 0d;
            double jshj = 0d;
            for (JyspmxDecimal2 mxItem : jyspmxs) {
                spje = spje + (mxItem.getSpje() == null ? null : mxItem.getSpje().doubleValue());
                spse = spse + (mxItem.getSpse() == null ? null : mxItem.getSpse().doubleValue());
                jshj = jshj + (mxItem.getJshj() == null ? null : mxItem.getJshj().doubleValue());
            }
            jyspmxs.get(0).setSpje(new BigDecimal(spje));
            jyspmxs.get(0).setSpse(new BigDecimal(spse));
            jyspmxs.get(0).setJshj(new BigDecimal(jshj));
            List<JyspmxDecimal2> jyspmxOne = new ArrayList<JyspmxDecimal2>();
            jyspmxOne.add(jyspmxs.get(0));
            // 价税分离
            if ("1".equals(jyxxsq.getHsbz())) {
                jyspmxs = SeperateInvoiceUtils.separatePrice2(jyspmxOne);
            }
            Map params = invoiceSplitParamsUtil.getInvoiceSplitParams(jyxxsq);
            String hsbz = String.valueOf(params.get("hsbz"));//确定是否含税分票，目前只支持不含税。
            double zdje = Double.valueOf(params.get("zdje").toString());//开票限额
            double fpje = Double.valueOf(params.get("fpje").toString());//分票金额
            int fphs1 = Integer.valueOf(params.get("fphs1").toString());//纸票分票行数
            int fphs2 = Integer.valueOf(params.get("fphs2").toString());//电子票分票行数
            int fphs3 = Integer.valueOf(params.get("fphs3").toString());//卷票分票行数
            sfqzfp = Boolean.valueOf(params.get("sfqzfp").toString());//是否强制分票
            boolean spzsfp = Boolean.valueOf(params.get("spzsfp").toString());//是否整数分票

            List<JyspmxDecimal2> splitKpspmxs = new ArrayList<JyspmxDecimal2>();
            Map mapResult = new HashMap();
            mapResult = InvoiceSplitUtils.dealDiscountLine(jyspmxOne);
            if (hsbz.equals("1")) {
                // 分票
                if (jyxxsq.getFpzldm().equals("12")) {
                    InvoiceSplitUtils.splitInvoiceshs((List) mapResult.get("jymxsqs"), (Map) mapResult.get("zkAndbzk"), new BigDecimal(Double.valueOf(zdje)), new BigDecimal(fpje), fphs2, sfqzfp, spzsfp, 0, splitKpspmxs);
                } else if (jyxxsq.getFpzldm().equals("03")) {//卷票
                    InvoiceSplitUtils.splitInvoiceshs((List) mapResult.get("jymxsqs"), (Map) mapResult.get("zkAndbzk"), new BigDecimal(Double.valueOf(zdje)), new BigDecimal(fpje), fphs3, sfqzfp, spzsfp, 0, splitKpspmxs);
                } else {
                    InvoiceSplitUtils.splitInvoiceshs((List) mapResult.get("jymxsqs"), (Map) mapResult.get("zkAndbzk"), new BigDecimal(Double.valueOf(zdje)), new BigDecimal(fpje), fphs1, sfqzfp, spzsfp, 0, splitKpspmxs);
                }
            } else {
                if (jyxxsq.getFpzldm().equals("12")) {
                    InvoiceSplitUtils.splitInvoices((List) mapResult.get("jymxsqs"), (Map) mapResult.get("zkAndbzk"), new BigDecimal(Double.valueOf(zdje)), new BigDecimal(fpje), fphs2, sfqzfp, spzsfp, 0, splitKpspmxs);
                } else if (jyxxsq.getFpzldm().equals("03")) {//卷票
                    InvoiceSplitUtils.splitInvoices((List) mapResult.get("jymxsqs"), (Map) mapResult.get("zkAndbzk"), new BigDecimal(Double.valueOf(zdje)), new BigDecimal(fpje), fphs3, sfqzfp, spzsfp, 0, splitKpspmxs);
                } else {
                    InvoiceSplitUtils.splitInvoices((List) mapResult.get("jymxsqs"), (Map) mapResult.get("zkAndbzk"), new BigDecimal(Double.valueOf(zdje)), new BigDecimal(fpje), fphs1, sfqzfp, spzsfp, 0, splitKpspmxs);
                }
            }

            // 保存进交易流水
            Map<Integer, List<JyspmxDecimal2>> fpMap = new HashMap<>();
            for (JyspmxDecimal2 jyspmx : splitKpspmxs) {
                int fpnum = jyspmx.getFpnum();
                List<JyspmxDecimal2> list2 = fpMap.get(fpnum);
                if (list2 == null) {
                    list2 = new ArrayList<>();
                    fpMap.put(fpnum, list2);
                }
                list2.add(jyspmx);
            }
            //fpnum和kplsh对应关系
            Map<Integer, Integer> fpNumKplshMap = new HashMap<>();
            //保存开票信息
            int i = 1;
            String serialorder=null;
            for (Map.Entry<Integer, List<JyspmxDecimal2>> entry : fpMap.entrySet()) {
                int fpNum = entry.getKey();
                List<JyspmxDecimal2> fpJyspmxList = entry.getValue();
                Jyls jyls = saveJyls(jyxxsq, fpJyspmxList);
                jyxxsq.setZtbz("3");
                jyxxsq.setXgsj(new Date());
                jyxxsqService.save(jyxxsq);
                List<Jyspmx> list2 = saveJyspmx(jyls, fpJyspmxList);
                    //保存开票流水
                    Kpls kpls = saveKpls(jyls, list2, jyxxsq.getSfdy(), kpfs);
                    serialorder=kpls.getSerialorder();
                    result.add(serialorder);
                    saveKpspmx(kpls, list2);
                    skService.callService(kpls.getKplsh());
                i++;
            }
        }
        return result;
    }

    /**
     * 保存交易流水`
     *
     * @param
     * @return
     */
    public Jyls saveJyls(Jyxxsq jyxxsq, List<JyspmxDecimal2> jyspmxList) throws Exception {
        Jyls jyls1 = new Jyls();
        jyls1.setDdh(jyxxsq.getDdh());
        jyls1.setJylsh(jyxxsq.getJylsh());
        jyls1.setJylssj(TimeUtil.getNowDate());
        jyls1.setFpzldm(jyxxsq.getFpzldm());
        jyls1.setFpczlxdm("11");
        jyls1.setXfid(jyxxsq.getXfid());
        jyls1.setXfsh(jyxxsq.getXfsh());
        jyls1.setXfmc(jyxxsq.getXfmc());
        jyls1.setXfyh(jyxxsq.getXfyh());
        jyls1.setTqm(jyxxsq.getTqm());
        jyls1.setXfyhzh(jyxxsq.getXfyhzh());
        jyls1.setXflxr(jyxxsq.getXflxr());
        jyls1.setXfdh(jyxxsq.getXfdh());
        jyls1.setXfdz(jyxxsq.getXfdz());
        jyls1.setGfid(jyxxsq.getGfid());
        jyls1.setGfsh(jyxxsq.getGfsh());
        jyls1.setGfmc(jyxxsq.getGfmc());
        jyls1.setGfyh(jyxxsq.getGfyh());
        jyls1.setGfyhzh(jyxxsq.getGfyhzh());
        jyls1.setGflxr(jyxxsq.getGflxr());
        jyls1.setGfdh(jyxxsq.getGfdh());
        jyls1.setGfdz(jyxxsq.getGfdz());
        jyls1.setGfyb(jyxxsq.getGfyb());
        jyls1.setGfemail(jyxxsq.getGfemail());
        jyls1.setClztdm("00");
        jyls1.setBz(jyxxsq.getBz());
        jyls1.setSkr(jyxxsq.getSkr());
        jyls1.setKpr(jyxxsq.getKpr());
        jyls1.setFhr(jyxxsq.getFhr());
        jyls1.setSsyf(jyxxsq.getSsyf());
        jyls1.setSffsyj(jyxxsq.getSffsyj());
        jyls1.setYfpdm(null);
        jyls1.setYfphm(null);
        jyls1.setHsbz(jyxxsq.getHsbz());
        double hjje = 0;
        double hjse = 0;
        for (JyspmxDecimal2 jyspmx : jyspmxList) {
            hjje += jyspmx.getSpje().doubleValue();
            hjse += jyspmx.getSpse().doubleValue();
        }
        jyls1.setJshj(hjje + hjse);
        jyls1.setYkpjshj(0d);
        jyls1.setYxbz("1");
        jyls1.setGsdm(jyxxsq.getGsdm());
        jyls1.setLrry(jyxxsq.getLrry());
        jyls1.setLrsj(TimeUtil.getNowDate());
        jyls1.setXgry(jyxxsq.getXgry());
        jyls1.setXgsj(TimeUtil.getNowDate());
        jyls1.setSkpid(jyxxsq.getSkpid());
        jyls1.setSqlsh(jyxxsq.getSqlsh());
        jylsService.save(jyls1);
        return jyls1;
    }

    public List<Jyspmx> saveJyspmx(Jyls jyls, List<JyspmxDecimal2> fpJyspmxList) throws Exception {
        int djh = jyls.getDjh();
        List<Jyspmx> list = new ArrayList<>();
       /* double spje = 0d;
        double spse = 0d;
        double jshj = 0d;*/
        double spje = Double.valueOf(String.valueOf(fpJyspmxList.get(0).getSpje()));
        double spse = Double.valueOf(String.valueOf(fpJyspmxList.get(0).getSpse()));
        double jshj = Double.valueOf(String.valueOf(fpJyspmxList.get(0).getJshj()));
       /* for (JyspmxDecimal2 mxItem : fpJyspmxList) {
            spje = spje + (mxItem.getSpje() == null ? null : mxItem.getSpje().doubleValue());
            spse = spse + (mxItem.getSpse() == null ? null : mxItem.getSpse().doubleValue());
            jshj = jshj + (mxItem.getJshj() == null ? null : mxItem.getJshj().doubleValue());
        }*/
        JyspmxDecimal2 mxItem = fpJyspmxList.get(0);
            Jyspmx jymx = new Jyspmx();
            jymx.setDjh(djh);
            jymx.setSpmxxh(mxItem.getSpmxxh());
            jymx.setSpdm(mxItem.getSpdm());
            jymx.setSpmc(mxItem.getSpmc());
            jymx.setSpggxh(mxItem.getSpggxh());
            jymx.setSpdw(mxItem.getSpdw());
            jymx.setSps(null);
            jymx.setSpdj(null);
            jymx.setSpje(spje);
            jymx.setSpsl(mxItem.getSpsl().doubleValue());
            jymx.setSpse(spse == 0d ? null : spse);
            jymx.setJshj(jshj);
            jymx.setYkphj(0d);
            jymx.setGsdm(jyls.getGsdm());
            jymx.setLrsj(TimeUtil.getNowDate());
            jymx.setLrry(jyls.getLrry());
            jymx.setXgsj(TimeUtil.getNowDate());
            jymx.setXgry(jyls.getXgry());
            jymx.setFphxz("0");
            if (null == mxItem.getKce()) {
                jymx.setKce(0d);
            } else {
                jymx.setKce(mxItem.getKce().doubleValue());
            }
            jymx.setYhzcbs(mxItem.getYhzcbs());
            jymx.setYhzcmc(mxItem.getYhzcmc());
            jymx.setLslbz(mxItem.getLslbz());
            jymxService.save(jymx);
            list.add(jymx);
        return list;
    }
}
