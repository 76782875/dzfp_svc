package com.rjxx.taxeasy.bizcomm.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rjxx.comm.utils.ApplicationContextUtils;
import com.rjxx.taxeasy.config.RabbitmqUtils;
import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.service.*;
import com.rjxx.taxeasy.vo.JyspmxDecimal2;
import com.rjxx.taxeasy.vo.KplsVO4;
import com.rjxx.taxeasy.vo.KplsVO5;
import com.rjxx.taxeasy.vo.Kpspmxvo;
import com.rjxx.time.TimeUtil;
import com.rjxx.utils.StringUtils;
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
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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
public class FpclService {
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
    private GeneratePdfService generatePdfService;
    @Autowired
    private CszbService cszbService;

    @Autowired
    private RabbitmqUtils rabbitmqSend;

    @Autowired
    private InvoiceSplitParamsUtil invoiceSplitParamsUtil;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean kpcl1(Integer djh, String dybz,boolean first) throws Exception {
        Jyls jyls1 = jylsService.findOne(djh);
        Jyspmx jyspmx = new Jyspmx();
        jyspmx.setDjh(djh);
        List<Jyspmx> list = jymxService.findAllByParams(jyspmx);
        //保存开票流水
        Kpls kpls = saveKp(jyls1, list, dybz);
        /*jyls1.setClztdm("40");
        jylsService.save(jyls1);*/
        Cszb cszb = cszbService.getSpbmbbh(kpls.getGsdm(), kpls.getXfid(), kpls.getSkpid(), "kpfs");
        if(cszb != null && cszb.getCsz().equals("01")){
            if(first){
                skService.callService(kpls.getKplsh());
            }else{
                kpls.setFpztdm("04");
                kplsService.save(kpls);
            }
        }
        if(cszb != null && cszb.getCsz().equals("03")){
            if(!kpls.getFpzldm().equals("12")){
                skService.callService(kpls.getKplsh());
            }else{
                //skService.SkServerKP(kpls.getKplsh());
                if(kpls.getGsdm().equals("afb")){
                    skService.SkServerKPhttps(kpls.getKplsh());
                }else{
                    skService.SkServerKP(kpls.getKplsh());
                }
            }
        }
        return true;
    }

    //全部开票
    public InvoiceResponse kpcl(Integer djh, String dybz) throws Exception {
        Jyls jyls1 = jylsService.findOne(djh);
        Jyspmx jyspmx = new Jyspmx();
        jyspmx.setDjh(djh);
        List<Jyspmx> list = jymxService.findAllByParams(jyspmx);

        //保存开票流水
        Kpls kpls = saveKp(jyls1, list, dybz);

        jyls1.setClztdm("02");
        jylsService.save(jyls1);

        InvoiceResponse response = skService.callService(kpls.getKplsh());
        if ("0000".equals(response.getReturnCode())) {
        } else {
            Map<String, Object> params = new HashMap<>();
            params.put("kplsh", kpls.getKplsh());
            List<Kpspmx> list2 = kpspmxService.findMxNewList(params);
            kpspmxService.deleteAll(list2);
            kplsService.delete(kpls);
            jyls1.setClztdm("00");
            jylsService.save(jyls1);
            dc.saveLog(djh, "92", "1", "", "调用开票接口失败" + response.getReturnMessage(), 2, jyls1.getXfsh(), jyls1.getJylsh());
            return response;
        }
        response.setReturnCode("0000");
        return response;
    }

    //红冲处理
    public InvoiceResponse hccl(Integer kplsh, Integer yhid, String gsdm, String hcjeStr, String xhStr) throws Exception {
        //	Kpls kpls = kplsService.findOne(kplsh);
        DecimalFormat df = new DecimalFormat("#.00");
        DecimalFormat df6 = new DecimalFormat("#.000000");
        Map map = new HashMap<>();
        map.put("kplsh", kplsh);
    /*	Fpcxvo cxvo = kplsService.selectMonth(map);
        //日期校验
		if (cxvo != null) {
			if (cxvo.getXcyf() != null && cxvo.getXcyf() > 6) {
				result.put("success", false);
				result.put("msg", "超过开票日期6个月，不能红冲！");
				return false;
			}
		}*/
        //kzx 20161212 如果走税控服务器红冲则设置clztdm为03
        Map paramsTmp = new HashMap();
        paramsTmp.put("gsdm", gsdm);
        Gsxx gsxx = gsxxService.findOneByParams(paramsTmp);
        String jylsh = "";
        Integer djh = 0;
        double hjhcje = 0;
        String[] hcje = hcjeStr.substring(0, hcjeStr.length() - 1).split(",");
        for (int j = 0; j < hcje.length; j++) {
            hjhcje += Double.valueOf(hcje[j]);
        }
        String hcjshj = df.format(hjhcje); // 本次红冲金额的价税合计
        String[] xh = xhStr.substring(0, xhStr.length() - 1).split(",");
        // 保存交易流水表
        Map param3 = new HashMap<>();
        param3.put("kplsh", kplsh);
        List<Kpls> kplsList = kplsService.printSingle(param3);
        Kpls kpls = kplsList.get(0);
        djh = kpls.getDjh();
        Map param4 = new HashMap<>();
        param4.put("djh", djh);
        Jyls jyls = jylsService.findJylsByDjh(param4);
        String ddh = jyls.getDdh(); // 查询原交易流水得ddh
        Map jylsParam = new HashMap<>();

        //保存交易流水
        Jyls jyls1 = new Jyls();
        jyls1.setDdh(ddh);
        jylsh = "JY" + new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
        jyls1.setJylsh(jylsh);
        jyls1.setJylssj(TimeUtil.getNowDate());
        jyls1.setFpzldm(kpls.getFpzldm());
        jyls1.setFpczlxdm("12");
        jyls1.setClztdm("02");
        jyls1.setXfid(kpls.getXfid());
        jyls1.setXfsh(kpls.getXfsh());
        jyls1.setXfmc(kpls.getXfmc());
        jyls1.setXfyh(kpls.getXfyh());
        jyls1.setXfyhzh(kpls.getXfyhzh());
        jyls1.setXflxr(kpls.getXflxr());
        jyls1.setXfdh(kpls.getXfdh());
        jyls1.setXfdz(kpls.getXfdz());
        jyls1.setGfid(kpls.getGfid());
        jyls1.setGfsh(kpls.getGfsh());
        jyls1.setGfmc(kpls.getGfmc());
        jyls1.setGfyh(kpls.getGfyh());
        jyls1.setGfyhzh(kpls.getGfyhzh());
        jyls1.setGflxr(kpls.getGflxr());
        jyls1.setGfdh(kpls.getGfdh());
        jyls1.setGfdz(kpls.getGfdz());
        jyls1.setGfyb(kpls.getGfyb());
        jyls1.setGfemail(kpls.getGfemail());
        jyls1.setClztdm("01");
        jyls1.setBz(kpls.getBz());
        jyls1.setSkr(kpls.getSkr());
        jyls1.setKpr(kpls.getKpr());
        jyls1.setFhr(kpls.getFhr());
        jyls1.setSsyf(kpls.getSsyf());
        jyls1.setYfpdm(kpls.getFpdm());
        jyls1.setYfphm(kpls.getFphm());
        jyls1.setHsbz("0");
        jyls1.setJshj(-Double.valueOf(hcjshj));
        jyls1.setYkpjshj(0d);
        jyls1.setYxbz("1");
        jyls1.setGsdm(kpls.getGsdm());
        jyls1.setLrry(yhid);
        jyls1.setLrsj(TimeUtil.getNowDate());
        jyls1.setXgry(yhid);
        jyls1.setXgsj(TimeUtil.getNowDate());
        jyls1.setSkpid(kpls.getSkpid());
        jylsService.save(jyls1);
        //保存开票流水
        Kpls kpls2 = new Kpls();
        kpls2.setDjh(djh);
        kpls2.setJylsh(jylsh);
        kpls2.setJylssj(jyls1.getJylssj());
        kpls2.setFpzldm(jyls1.getFpzldm());
        kpls2.setFpczlxdm(jyls1.getFpczlxdm());
        kpls2.setXfid(jyls1.getXfid());
        kpls2.setXfsh(jyls1.getXfsh());
        kpls2.setXfmc(jyls1.getXfmc());
        kpls2.setXfyh(jyls1.getXfyh());
        kpls2.setXfyhzh(jyls1.getXfyhzh());
        kpls2.setXflxr(jyls1.getXflxr());
        kpls2.setXfdh(jyls1.getXfdh());
        kpls2.setXfdz(jyls1.getXfdz());
        kpls2.setGfid(jyls1.getGfid());
        kpls2.setGfsh(jyls1.getGfsh());
        kpls2.setGfmc(jyls1.getGfmc());
        kpls2.setGfyh(jyls1.getGfyh());
        kpls2.setGfyhzh(jyls1.getGfyhzh());
        kpls2.setGflxr(jyls1.getGflxr());
        kpls2.setGfdh(jyls1.getGfdh());
        kpls2.setGfdz(jyls1.getGfdz());
        kpls2.setGfyb(jyls1.getGfyb());
        kpls2.setGfemail(jyls1.getGfemail());
        kpls2.setBz(jyls1.getBz());
        kpls2.setSkr(jyls1.getSkr());
        kpls2.setKpr(jyls1.getKpr());
        kpls2.setFhr(jyls1.getFhr());
        kpls2.setHztzdh(jyls1.getHztzdh());
        kpls2.setHkFpdm(jyls1.getYfpdm());
        kpls2.setHkFphm(jyls1.getYfphm());
        kpls2.setJshj(jyls1.getJshj());
/*			kpls2.setHjse(hjse);
            kpls2.setHjje(hjje);*/
        kpls2.setGsdm(jyls1.getGsdm());
        kpls2.setYxbz("1");
        kpls2.setLrsj(jyls1.getLrsj());
        kpls2.setXgsj(jyls1.getXgsj());
        kpls2.setSkpid(jyls1.getSkpid());
        kpls2.setLrry(yhid);
        kpls2.setXgry(yhid);
        kpls2.setFpztdm("00");
        kplsService.save(kpls2);
        djh = jyls1.getDjh();
        double hjje = 0;
        double hjse = 0;
        double jshj = 0;
        for (int i = 0; i < xh.length; i++) {
            Map params = new HashMap<>();
            params.put("kplsh", kplsh);
            params.put("xh", xh[i]);
            Kpspmxvo mxItem = kpspmxService.findMxByParams(params);
            if (mxItem.getKhcje() != null) {
                String khcje = df.format(mxItem.getKhcje() - Double.valueOf(hcje[i]));
                Map param1 = new HashMap<>();
                param1.put("khcje", khcje);
                String yhcje = df.format(mxItem.getYhcje() + Double.valueOf(hcje[i]));
                String sps = null;
                if (mxItem.getSps() != null) {
                    sps = df6.format(Double.valueOf(hcje[i]) / mxItem.getJshj() * mxItem.getSps()); // 没红冲部分的商品数量
                }
                param1.put("sps", sps);
                Double spje = Double.valueOf(hcje[i]) / mxItem.getJshj() * mxItem.getSpje();
                param1.put("spje", df6.format(spje));
                Double spse = Double.valueOf(hcje[i]) - spje;
                param1.put("spse", df6.format(spse));
                param1.put("yhcje", yhcje);
                param1.put("kplsh", kplsh);
                param1.put("xh", xh[i]);
                kpspmxService.update(param1);
                // 交易保存明细
                if (Double.valueOf(hcje[i]) != 0) {
                    Jyspmx jymx = new Jyspmx();
                    jymx.setDjh(djh);
                    jymx.setSpmxxh(Integer.parseInt(mxItem.getSpmxxh()));
                    jymx.setSpdm(mxItem.getSpdm());
                    jymx.setSpmc(mxItem.getSpmc());
                    jymx.setSpggxh(mxItem.getSpggxh());
                    jymx.setSpdw(mxItem.getSpdw());
                    if (sps != null) {
                        jymx.setSps(-Double.valueOf(sps));
                    } else {
                        jymx.setSps(null);
                    }
                    jymx.setSpdj(mxItem.getSpdj() == null ? null : mxItem.getSpdj());
                    jymx.setSpje(-Double.valueOf(df6.format(spje)));
                    jymx.setSpsl(mxItem.getSpsl());
                    jymx.setSpse(-Double.valueOf(df.format(spse)));
                    jymx.setJshj(-Double.valueOf(hcje[i]));
                    jymx.setYkphj(Double.valueOf(khcje));
                    jymx.setGsdm(gsdm);
                    jymx.setLrsj(TimeUtil.getNowDate());
                    jymx.setLrry(yhid);
                    jymx.setXgsj(TimeUtil.getNowDate());
                    jymx.setXgry(yhid);
                    jymx.setFphxz("0");
                    hjje += jymx.getSpje();
                    hjse += jymx.getSpse();
                    jshj += jymx.getJshj();
                    jymxService.save(jymx);
                    Kpspmx kpspmx = new Kpspmx();
                    kpspmx.setKplsh(kpls2.getKplsh());
                    kpspmx.setDjh(jymx.getDjh());
                    kpspmx.setSpmxxh(jymx.getSpmxxh());
                    kpspmx.setFphxz(jymx.getFphxz());
                    kpspmx.setSpdm(jymx.getSpdm());
                    kpspmx.setSpmc(jymx.getSpmc());
                    kpspmx.setSpggxh(jymx.getSpggxh());
                    kpspmx.setSpdw(jymx.getSpdw());
                    if (jymx.getSpdj() != null) {
                        kpspmx.setSpdj(jymx.getSpdj().doubleValue());
                    }
                    kpspmx.setSpdw(jymx.getSpdw());
                    if (jymx.getSps() != null) {
                        kpspmx.setSps(jymx.getSps().doubleValue());
                    }
                    kpspmx.setSpje(jymx.getSpje().doubleValue());
                    kpspmx.setSpsl(jymx.getSpsl().doubleValue());
                    kpspmx.setSpse(jymx.getSpse().doubleValue());
                    kpspmx.setLrsj(jymx.getLrsj());
                    kpspmx.setLrry(jymx.getLrry());
                    kpspmx.setXgsj(jymx.getXgsj());
                    kpspmx.setXgry(jymx.getXgry());
                    kpspmx.setKhcje(jymx.getJshj().doubleValue());
                    kpspmx.setYhcje(0d);
                    kpspmxService.save(kpspmx);
                }
            } else {
                String khcje = df.format(mxItem.getJshj() - Double.valueOf(hcje[i]));
                Map param1 = new HashMap<>();
                param1.put("khcje", khcje);
                String yhcje = df.format(Double.valueOf(hcje[i]));
                String sps = null;
                if (mxItem.getSps() != null) {
                    sps = df6.format(Double.valueOf(khcje) / mxItem.getJshj() * mxItem.getSps()); // 没红冲部分的商品数量
                }
                param1.put("sps", sps);
                Double spje = Double.valueOf(khcje) / mxItem.getJshj() * mxItem.getSpje();
                param1.put("spje", df6.format(spje));
                Double spse = Double.valueOf(khcje) - spje;
                param1.put("spse", df6.format(spse));
                param1.put("yhcje", yhcje);
                param1.put("kplsh", kplsh);
                param1.put("xh", xh[i]);
                kpspmxService.update(param1);
                // 交易保存明细
                if (Double.valueOf(hcje[i]) != 0) {
                    Jyspmx jymx = new Jyspmx();
                    jymx.setDjh(djh);
                    jymx.setSpmxxh(Integer.parseInt(mxItem.getSpmxxh()));
                    jymx.setSpdm(mxItem.getSpdm());
                    jymx.setSpmc(mxItem.getSpmc());
                    jymx.setSpggxh(mxItem.getSpggxh());
                    jymx.setSpdw(mxItem.getSpdw());
                    if (sps != null) {
                        jymx.setSps(-Double.valueOf(df6.format(mxItem.getSps() - Double.valueOf(sps))));
                    } else {
                        jymx.setSps(null);
                    }
                    jymx.setSpdj(mxItem.getSpdj() == null ? null : mxItem.getSpdj());
                    jymx.setSpje(-Double.valueOf(df.format(mxItem.getSpje() - spje)));
                    jymx.setSpsl(mxItem.getSpsl());
                    jymx.setSpse(-Double.valueOf(df.format(mxItem.getSpse() - spse)));
                    jymx.setJshj(-Double.valueOf(hcje[i]));
                    jymx.setYkphj(Double.valueOf(khcje));
                    jymx.setGsdm(gsdm);
                    jymx.setLrsj(TimeUtil.getNowDate());
                    jymx.setLrry(yhid);
                    jymx.setXgsj(TimeUtil.getNowDate());
                    jymx.setXgry(yhid);
                    jymx.setFphxz("0");
                    jymxService.save(jymx);
                }
            }

        }
        kpls2.setHjje(hjje);
        kpls2.setHjse(hjse);
        kpls2.setJshj(jshj);
        kplsService.save(kpls2);

        Map param2 = new HashMap<>();
        param2.put("kplsh", kplsh);
        // 部分红冲后修改kpls表的三个金额
            /*
             * Kpls ls = kplsService.findHjje(param2);
			 * param2.put("hjje",ls.getHjje()); param2.put("hjse",ls.getHjse());
			 * param2.put("jshj",ls.getJshj()); kplsService.updateHjje(param2);
			 */
        // 全部红冲后修改
        Kpspmxvo mxvo = kpspmxService.findKhcje(param2);
        if (mxvo.getKhcje() == 0) {
            param2.put("fpztdm", "02");
            kplsService.updateFpczlx(param2);
        } else {
            param2.put("fpztdm", "01");
            kplsService.updateFpczlx(param2);
        }
        InvoiceResponse response = skService.callService(kplsh);
        if ("0000".equals(response.getReturnCode())) {
            return response;
        } else {
            jyls1.setClztdm("02");
            dc.saveLog(djh, "92", "1", "", "调用红冲接口失败" + response.getReturnMessage(), 2, jyls.getXfsh(), jyls.getJylsh());
            return response;
        }
    }

    //作废处理
    public InvoiceResponse zfcl(Integer kplsh, Integer yhid, String gsdm) throws Exception {
        Kpls kpls = kplsService.findOne(kplsh);
        Kpls kpls2 = new Kpls();
        kpls2.setHkFphm(kpls.getFphm());
        kpls2.setHkFpdm(kpls.getFpdm());
        kpls2.setLrsj(new Date());
        kpls2.setJylsh(kpls.getJylsh());
        kpls2.setXgsj(new Date());
        kpls2.setXfsh(kpls.getXfsh());
        kpls2.setXfmc(kpls.getXfmc());
        kpls2.setGfmc(kpls.getGfmc());
        kpls2.setJshj(kpls.getJshj());
        kpls2.setHjje(kpls.getHjje());
        kpls2.setHjse(kpls.getHjse());
        kpls2.setLrry(yhid);
        kpls2.setGsdm(gsdm);
        kpls2.setXgry(yhid);
        kpls2.setZfry(yhid);
        kpls2.setFpczlxdm("14");

        Jyls jyls = new Jyls();
        jyls.setYfphm(kpls.getFphm());
        jyls.setYfpdm(kpls.getFpdm());
        jyls.setLrsj(new Date());
        jyls.setClztdm("02");
        jyls.setXgsj(new Date());
        jyls.setXfsh(kpls.getXfsh());
        jyls.setXfmc(kpls.getXfmc());
        jyls.setJylsh(kpls.getJylsh());
        jyls.setYkpjshj(0d);
        jyls.setGsdm(gsdm);
        jyls.setJshj(kpls.getJshj());
        jyls.setHsbz("0");
        jyls.setLrry(yhid);
        jyls.setXgry(yhid);
        /*jyls.setZfry(yhid);*/
        jyls.setFpczlxdm("14");
        kplsService.save(kpls2);
        jylsService.save(jyls);
        kpls.setFpztdm("08");
        kplsService.save(kpls);
        InvoiceResponse response = skService.callService(kplsh);
        if ("0000".equals(response.getReturnCode())) {
            return response;
        } else {
            dc.saveLog(jyls.getDjh(), "92", "1", "", "调用作废接口失败" + response.getReturnMessage(), 2, jyls.getXfsh(), jyls.getJylsh());
            return response;
        }
    }

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
        kpls.setZsfs(jyls.getZsfs());
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
        kpls.setSerialorder(jyls.getJylsh() + jyls.getDdh()+jyls.getGsdm());
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

    @Transactional
    public Kpls saveKp(Jyls jyls1, List<Jyspmx> list, String dybz) throws Exception {
        Kpls kpls = saveKpls(jyls1, list, dybz, "01");
        saveKpspmx(kpls, list);
        return kpls;
    }

    public String Skzjkp(List jyxxsqList, String kpfs) {
        List fpclList = new ArrayList();
        Map resultMap = null;
        try {
            fpclList = (List) this.zjkp(jyxxsqList, kpfs);//组件
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        List resultList = new ArrayList();
        if (null != fpclList) {
            KplsVO5 zjKplsvo5 = new KplsVO5();
            for (int i = 0; i < fpclList.size(); i++) {
                double hjje = 0.00;
                double hjse = 0.00;
                List<Kpspmxvo> tmpList = new ArrayList<Kpspmxvo>();
                zjKplsvo5 = (KplsVO5) fpclList.get(i);
                //获取对应开票商品明细信息
                Map params = new HashMap();
                params.put("kplsh", zjKplsvo5.getKplsh());
                tmpList = kpspmxService.findSkMxList(params);
                Kpspmxvo kpspmxvo = new Kpspmxvo();
                for (int j = 0; j < tmpList.size(); j++) {
                    kpspmxvo = tmpList.get(j);
                    kpspmxvo.setHsspje(kpspmxvo.getSpje() + kpspmxvo.getSpse());
                    kpspmxvo.setHsspdj((kpspmxvo.getSpje() + kpspmxvo.getSpse()) / kpspmxvo.getSps());
                    hjje = hjje + kpspmxvo.getSpje();
                    hjse = hjse + kpspmxvo.getSpse();
                }
                String path = this.getClass().getClassLoader().getResource("SKFpkjModel.xml")
                        .getPath();
                try {
                    Map params2 = new HashMap();
                    String fpzldm = zjKplsvo5.getFpzldm();
                    if (fpzldm.equals("01")) {
                        zjKplsvo5.setFpzldm("004");
                    } else if (fpzldm.equals("02")) {
                        zjKplsvo5.setFpzldm("007");
                    } else if (fpzldm.equals("12")) {
                        zjKplsvo5.setFpzldm("026");
                    } else if (fpzldm.equals("03")) {
                        zjKplsvo5.setFpzldm("025");
                    }
                    params2.put("kplsvo5", zjKplsvo5);
                    params2.put("tmpList", tmpList);
                    params2.put("count", tmpList.size());
                    params2.put("hjje", hjje);
                    params2.put("hjse", hjse);
                    //params2.put("jyxxsq", jyxxsq);
                    path = URLDecoder.decode(path, "UTF-8");
                    File templateFile = new File(path);
                    String result2 = TemplateUtils.generateContent(templateFile, params2, "gbk");
                    System.out.println(result2);
                    logger.debug("封装传开票通的报文" + result2);
                    String url = "http://116.228.37.198:10002/SKServer/SKDo";
                    resultMap = httpPost(result2, url, zjKplsvo5.getDjh() + "$" + zjKplsvo5.getKplsh(), zjKplsvo5.getXfsh(),
                            zjKplsvo5.getJylsh());
                    if (resultMap.get("returncode").equals("0")) {
                        String fpdm = resultMap.get("fpdm").toString();
                        String fphm = resultMap.get("fphm").toString();
                        String kprq = resultMap.get("kprq").toString();
                        String skm = resultMap.get("skm").toString();
                        String jym = resultMap.get("jym").toString();
                        String ewm = resultMap.get("ewm").toString();
                    }
                    logger.debug("封装传开票通的返回报文" + JSONObject.toJSONString(resultMap));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return JSONObject.toJSONString(resultMap).toString();
    }

    /**
     * 调用电子发票税控服务器https
     *
     * @param sendMes
     * @param url
     * @param key
     * @param xfsh
     * @param jylsh
     * @return
     * @throws Exception
     */
    public Map DzfphttpsPost(String sendMes, String url, String key, String xfsh, String jylsh,int j) throws Exception {
        j--;
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = new SSLClient();
        RequestConfig requestConfig = RequestConfig.custom().
                setSocketTimeout(120 * 1000).setConnectionRequestTimeout(120 * 1000).setConnectTimeout(120 * 1000).build();
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "text/xml");
        String strMessage = "";
        BufferedReader reader = null;
        String kplsh=null;
        kplsh=key;
        StringBuffer buffer = new StringBuffer();
        Map resultMap = null;
        try {
            StringEntity requestEntity = new StringEntity(sendMes, "GBK");
            httpPost.setEntity(requestEntity);
            response = httpClient.execute(httpPost, new BasicHttpContext());
            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode()
                        + ", url=" + url);
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                reader = new BufferedReader(new InputStreamReader(entity.getContent(), "gbk"));
                while ((strMessage = reader.readLine()) != null) {
                    buffer.append(strMessage);
                }
            }
            resultMap = DzfphanderReturnMes(buffer.toString(), key);
            if (null != resultMap && !resultMap.isEmpty()) {
                int pos = key.indexOf("$");
                if (pos != -1) {
                    key = key.substring(0, pos);
                }
                if (resultMap.get("RETURNCODE").equals("0000")) {
                    dataOperate.saveLog(Integer.valueOf(key), "91", "1", "Send:send",
                            "(服务端)发送服务器成功" + resultMap.get("RETURNMSG").toString(), 2, xfsh, jylsh);
                } else {
                    dataOperate.saveLog(Integer.valueOf(key), "92", "1", "Send:send",
                            "(服务端)发送服务器失败" + resultMap.get("RETURNMSG").toString(), 2, xfsh, jylsh);
                }
            }
            int str = kplsh.indexOf("$");
            if (str != -1) {
                kplsh = kplsh.substring(str+1);
                System.out.println("传入开票流水号:" + kplsh);
            }
            Kpls kpls=kplsService.findOne(Integer.parseInt(kplsh));
            if (!resultMap.get("RETURNCODE").equals("0000")) {
                if (kpls.getGsdm().equals("wills")) {
                    if (j == 1) {
                        logger.info("-------递归次数--------" + j);
                        this.DzfphttpsPost(sendMes, url, key, xfsh, jylsh, j);
                    }
                }
            }
        } catch (IOException e) {
            int pos = key.indexOf("$");
            if (pos != -1) {
                key = key.substring(pos + 1);
                System.out.println("传入开票流水号:" + key);
            }
            System.out.println("request url=" + url + ", exception, msg=" + e.getMessage());
            e.printStackTrace();
            if(e.getMessage().startsWith("Read")){
                rabbitmqSend.sendMsg("ErrorException_Sk", "12", key + "");
            }
        } finally {
            if (response != null) try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultMap;
    }
    /**
     * 调用电子发票税控服务器
     *
     * @param sendMes
     * @param url
     * @param key
     * @param xfsh
     * @param jylsh
     * @return
     * @throws Exception
     */
    public Map DzfphttpPost(String sendMes, String url, String key, String xfsh, String jylsh,int j) throws Exception {
        j--;
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().
                setSocketTimeout(120 * 1000).setConnectionRequestTimeout(120 * 1000).setConnectTimeout(120 * 1000).build();
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "text/xml");
        String strMessage = "";
        BufferedReader reader = null;
        String kplsh=null;
        kplsh=key;
        StringBuffer buffer = new StringBuffer();
        Map resultMap = null;
        try {
            StringEntity requestEntity = new StringEntity(sendMes, "GBK");
            httpPost.setEntity(requestEntity);
            response = httpClient.execute(httpPost, new BasicHttpContext());
            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode()
                        + ", url=" + url);
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                reader = new BufferedReader(new InputStreamReader(entity.getContent(), "gbk"));
                while ((strMessage = reader.readLine()) != null) {
                    buffer.append(strMessage);
                }
            }
            resultMap = DzfphanderReturnMes(buffer.toString(), key);
            if (null != resultMap && !resultMap.isEmpty()) {
                int pos = key.indexOf("$");
                if (pos != -1) {
                    key = key.substring(0, pos);
                }
                if (resultMap.get("RETURNCODE").equals("0000")) {
                    dataOperate.saveLog(Integer.valueOf(key), "91", "1", "Send:send",
                            "(服务端)发送服务器成功" + resultMap.get("RETURNMSG").toString(), 2, xfsh, jylsh);
                } else {
                    dataOperate.saveLog(Integer.valueOf(key), "92", "1", "Send:send",
                            "(服务端)发送服务器失败" + resultMap.get("RETURNMSG").toString(), 2, xfsh, jylsh);
                }
            }
        } catch (Exception e) {
            int pos = key.indexOf("$");
            if (pos != -1) {
                key = key.substring(pos + 1);
                System.out.println("传入开票流水号:" + key);
            }
            System.out.println("request url=" + url + ", exception, msg=" + e.getMessage());
            Kpls kpls=kplsService.findOne(Integer.parseInt(key));
            kpls.setFpztdm("04");
            kpls.setErrorReason(e.getMessage());
            kplsService.save(kpls);
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                    httpClient.close();
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }

        return resultMap;
    }

    public Map httpPost(String sendMes, String url, String key, String xfsh, String jylsh) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().
                setSocketTimeout(2000).setConnectTimeout(2000).build();
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "text/xml");
        String strMessage = "";
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();
        Map resultMap = null;
        try {
            StringEntity requestEntity = new StringEntity(sendMes, "GBK");
            httpPost.setEntity(requestEntity);
            response = httpClient.execute(httpPost, new BasicHttpContext());
            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode()
                        + ", url=" + url);
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                reader = new BufferedReader(new InputStreamReader(entity.getContent(), "gbk"));
                while ((strMessage = reader.readLine()) != null) {
                    buffer.append(strMessage);
                }
            }
            System.out.println("接收返回值:" + buffer.toString());
            resultMap = handerReturnMes(buffer.toString(), key);
            if (null != resultMap && !resultMap.isEmpty()) {
                int pos = key.indexOf("$");
                if (pos != -1) {
                    key = key.substring(0, pos);
                }
                if (resultMap.get("returncode").equals("0")) {
                    dataOperate.saveLog(Integer.valueOf(key), "91", "1", "Send:send",
                            "(服务端)发送服务器成功" + resultMap.get("returnmsg").toString(), 2, xfsh, jylsh);
                } else {
                    dataOperate.saveLog(Integer.valueOf(key), "92", "1", "Send:send",
                            "(服务端)发送服务器失败" + resultMap.get("returnmsg").toString(), 2, xfsh, jylsh);
                }
            }
        } catch (IOException e) {
            System.out.println("request url=" + url + ", exception, msg=" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultMap;
    }

    /**
     * 接收返回报文并做后续处理(电票)
     *
     * @param returnMes
     * @throws Exception
     */
    public Map DzfphanderReturnMes(String returnMes, String key) throws Exception {

        Document document = DocumentHelper.parseText(returnMes);
        Element root = document.getRootElement();
        List<Element> childElements = root.elements();
        Map resultMap = new HashMap();
        for (Element child : childElements) {
            int pos = key.indexOf("$");
            if (pos != -1) {
                key = key.substring(pos + 1);
                System.out.println("传入开票流水号:" + key);
            }
            if (child.elementText("RETURNCODE").equals("0000")) {
                resultMap.put("FP_DM", child.elementText("FP_DM"));// 返回结果，发票代码
                resultMap.put("FP_HM", child.elementText("FP_HM"));// 发票号码
                resultMap.put("FP_MW", child.element("FP_MW").getText());// 发票密文
                resultMap.put("JYM", child.element("JYM").getText());// 校验码
                if(null!=child.element("EWM")){
                    resultMap.put("EWM", child.element("EWM").getText());// 二维码
                }
                resultMap.put("JQBH", child.element("JQBH").getText());// 机器编号
                resultMap.put("KPRQ", child.element("KPRQ").getText());
                resultMap.put("RETURNCODE", child.elementText("RETURNCODE"));
                resultMap.put("RETURNMSG", child.elementText("RETURNMSG"));
                resultMap.put("KPLSH", key);
            } else {
                resultMap.put("RETURNCODE", child.elementText("RETURNCODE"));
                resultMap.put("RETURNMSG", child.elementText("RETURNMSG"));
                resultMap.put("KPLSH", key);
            }
        }
        return resultMap;
    }

    /**
     * 接收返回报文并做后续处理(卷票)
     *
     * @param returnMes
     * @throws Exception
     */
    public Map handerReturnMes(String returnMes, String key) throws Exception {

        Document document = DocumentHelper.parseText(returnMes);
        Element root = document.getRootElement();
        List<Element> childElements = root.elements();
        Map resultMap = new HashMap();
        for (Element child : childElements) {
            int pos = key.indexOf("$");
            if (pos != -1) {
                key = key.substring(pos + 1);
                System.out.println("传入开票流水号:" + key);
            }
            if (child.elementText("returncode").equals("0")) {
                resultMap.put("returncode", child.elementText("returncode"));
                resultMap.put("returnmsg", child.elementText("returnmsg"));
                resultMap.put("kplsh", key);
                Element contactList = child.element("returndata");
                List<Element> nodes = contactList.elements();
                for (Element e : nodes) {
                    resultMap.put(e.getName(), e.getText());// 返回结果
                }
            } else if (child.elementText("returncode").equals("99")) {
                resultMap.put("returncode", child.elementText("returncode"));
                resultMap.put("returnmsg", child.elementText("returnmsg"));
                resultMap.put("kplsh", key);
            }
        }
        return resultMap;
    }

    /**
     * 直接开票
     */
    public List<Object> zjkp(List<Jyxxsq> list, String kpfs) throws Exception {
        List<Object> result = new ArrayList<>();
        boolean sfqzfp = false;
        for (int j=0;j<list.size();j++) {
            Jyxxsq jyxxsq=list.get(j);
            // 转换明细
            Map<String, Object> params1 = new HashMap<>();
            params1.put("sqlsh", jyxxsq.getSqlsh());
            List<JyspmxDecimal2> jyspmxs = jymxService.getNeedToKP4(params1);
            // 价税分离
            if ("1".equals(jyxxsq.getHsbz())) {
                jyspmxs = SeperateInvoiceUtils.separatePrice2(jyspmxs);
            }
            Map params = invoiceSplitParamsUtil.getInvoiceSplitParams(jyxxsq);
            String hsbz = String.valueOf(params.get("hsbz"));//确定是否含税分票，目前只支持不含税。
            double zdje = Double.valueOf(params.get("zdje").toString());//开票限额
            double fpje = Double.valueOf(params.get("fpje").toString());//分票金额
            int fphs1 = Integer.valueOf(params.get("fphs1").toString());//纸票分票行数
            int fphs2 = Integer.valueOf(params.get("fphs2").toString());//电子票分票行数
            int fphs3 = Integer.valueOf(params.get("fphs3").toString());//卷票票分票行数
            sfqzfp = Boolean.valueOf(params.get("sfqzfp").toString());//是否强制分票
            boolean spzsfp = Boolean.valueOf(params.get("spzsfp").toString());//是否整数分票
            List<JyspmxDecimal2> splitKpspmxs = new ArrayList<JyspmxDecimal2>();
            Map mapResult = new HashMap();
            mapResult = InvoiceSplitUtils.dealDiscountLine(jyspmxs);
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
                    InvoiceSplitUtils.splitInvoices((List) mapResult.get("jymxsqs"), (Map) mapResult.get("zkAndbzk"), new BigDecimal(Double.valueOf(zdje)),jyxxsq.getZsfs(), new BigDecimal(fpje), fphs2, sfqzfp, spzsfp, 0, splitKpspmxs);
                } else if (jyxxsq.getFpzldm().equals("03")) {//卷票
                    InvoiceSplitUtils.splitInvoices((List) mapResult.get("jymxsqs"), (Map) mapResult.get("zkAndbzk"), new BigDecimal(Double.valueOf(zdje)), jyxxsq.getZsfs(),new BigDecimal(fpje), fphs3, sfqzfp, spzsfp, 0, splitKpspmxs);
                } else {
                    InvoiceSplitUtils.splitInvoices((List) mapResult.get("jymxsqs"), (Map) mapResult.get("zkAndbzk"), new BigDecimal(Double.valueOf(zdje)),jyxxsq.getZsfs(), new BigDecimal(fpje), fphs1, sfqzfp, spzsfp, 0, splitKpspmxs);
                }
            }
            //如果分票失败，继续往下执行。
            if (null == splitKpspmxs || splitKpspmxs.isEmpty()) {
                continue;
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
            for (Map.Entry<Integer, List<JyspmxDecimal2>> entry : fpMap.entrySet()) {
                int fpNum = entry.getKey();
                List<JyspmxDecimal2> fpJyspmxList = entry.getValue();
                Jyls jyls = saveJyls(jyxxsq, fpJyspmxList);
                jyxxsq.setZtbz("3");
                jyxxsq.setXgsj(new Date());
                jyxxsqService.save(jyxxsq);
                List<Jyspmx> list2 = saveKpspmx(jyls, fpJyspmxList);
                if (kpfs.equals("01")) {
                    //保存开票流水
                    Kpls kpls = saveKpls(jyls, list2, jyxxsq.getSfdy(), kpfs);
                    kpls.setKpddm(jyxxsq.getKpddm());
                    kplsService.save(kpls);
                    saveKpspmx(kpls, list2);
                    if(j==0){
                        try{
                            skService.callService(kpls.getKplsh());
                        }catch(Exception e){
                            e.printStackTrace();
                            kpls.setFpztdm("04");
                            kplsService.save(kpls);
                        }
                    }else{
                        kpls.setFpztdm("04");
                        kplsService.save(kpls);
                    }
                    result.add(kpls.getSerialorder());
                } else if (kpfs.equals("02")) {//组件
                    //保存开票流水
                    Kpls kpls = saveKpls(jyls, list2, jyxxsq.getSfdy(), kpfs);
                    kpls.setKpddm(jyxxsq.getKpddm());
                    kplsService.save(kpls);
                    saveKpspmx(kpls, list2);
                    KplsVO4 kplsVO4 = new KplsVO4(kpls, jyxxsq);
                    result.add(kplsVO4);
                } else if (kpfs.equals("03")) {//税控服务器
                    //保存开票流水
                    Kpls kpls = saveKpls(jyls, list2, jyxxsq.getSfdy(), kpfs);
                    kpls.setKpddm(jyxxsq.getKpddm());
                    kplsService.save(kpls);
                    saveKpspmx(kpls, list2);
                    KplsVO5 kplsVO5 = new KplsVO5(kpls, jyxxsq);
                    result.add(kplsVO5);
                    //skService.SkServerKP(kpls.getKplsh());//税控开票
                    if(kpls.getGsdm().equals("afb")){
                        skService.SkServerKPhttps(kpls.getKplsh());
                    }else{
                        try {
                            skService.SkServerKP(kpls.getKplsh());
                        }catch (Exception e){
                             e.printStackTrace();
                            kpls.setFpztdm("04");
                            kpls.setErrorReason(e.getMessage());
                            kplsService.save(kpls);
                        }
                    }
                }else if (kpfs.equals("04")||kpfs.equals("05")) {
                    //保存开票流水 //凯盈开票  盟度开票
                    Kpls kpls = saveKpls(jyls, list2, jyxxsq.getSfdy(), kpfs);
                    kpls.setKpddm(jyxxsq.getKpddm());
                    kplsService.save(kpls);
                    saveKpspmx(kpls, list2);
                    KplsVO5 kplsVO5 = new KplsVO5(kpls, jyxxsq);
                    result.add(kplsVO5);
                        try {
                            if(kpfs.equals("04")){
                                skService.SkBoxKP(kpls.getKplsh());
                            }else if(kpfs.equals("05")){
                                skService.skEkyunKP(kpls.getKplsh());
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            kpls.setFpztdm("04");
                            kpls.setErrorReason(e.getMessage());
                            kplsService.save(kpls);
                        }
                }
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
        jyls1.setZsfs(jyxxsq.getZsfs());
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
        jyls1.setKhh(jyxxsq.getKhh());
        jyls1.setGfsjh(jyxxsq.getGfsjh());
        jyls1.setGfsjrdz(jyxxsq.getGfsjrdz());
        jyls1.setSfdyqd(jyxxsq.getSfdyqd());
        jylsService.save(jyls1);
        return jyls1;
    }

    public List<Jyspmx> saveKpspmx(Jyls jyls, List<JyspmxDecimal2> fpJyspmxList) throws Exception {
        int djh = jyls.getDjh();
        List<Jyspmx> list = new ArrayList<>();
        for (JyspmxDecimal2 mxItem : fpJyspmxList) {
            Jyspmx jymx = new Jyspmx();
            jymx.setDjh(djh);
            jymx.setSpmxxh(mxItem.getSpmxxh());
            jymx.setSpdm(mxItem.getSpdm());
            jymx.setSpmc(mxItem.getSpmc());
            jymx.setSpggxh(mxItem.getSpggxh());
            jymx.setSpdw(mxItem.getSpdw());
            jymx.setSps(mxItem.getSps() == null ? null : mxItem.getSps().doubleValue());
            jymx.setSpdj(mxItem.getSpdj() == null ? null : mxItem.getSpdj().doubleValue());
            jymx.setSpje(mxItem.getSpje() == null ? null : mxItem.getSpje().doubleValue());
            jymx.setSpsl(mxItem.getSpsl().doubleValue());
            jymx.setSpse(mxItem.getSpse() == null ? null : mxItem.getSpse().doubleValue());
            jymx.setJshj(mxItem.getJshj() == null ? null : mxItem.getJshj().doubleValue());
            jymx.setYkphj(0d);
            jymx.setGsdm(jyls.getGsdm());
            jymx.setLrsj(TimeUtil.getNowDate());
            jymx.setLrry(jyls.getLrry());
            jymx.setXgsj(TimeUtil.getNowDate());
            jymx.setXgry(jyls.getXgry());
            jymx.setFphxz(mxItem.getFphxz());
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
        }
        return list;
    }
    /**
     * 线程池执行任务
     */
    private static ThreadPoolTaskExecutor taskExecutor = null;
    /**
     * 多线程执行生成pdf
     */
    class PdfTask implements Runnable {

        private int kplsh;
        @Override
        public void run() {
            //synchronized (this){
            logger.info("------多线程执行生成pdf----------");
            generatePdfService.generatePdf(kplsh);
            //}
        }
        public void setKplsh(int kplsh) {
            this.kplsh = kplsh;
        }
    }
    public String updateKpls(Map resultMap) {
        String kplsh = resultMap.get("KPLSH").toString();
        Kpls kpls = kplsService.findOne(Integer.valueOf(kplsh));
        try {
            String returncode = resultMap.get("RETURNCODE").toString();
            String returnmsg = resultMap.get("RETURNMSG").toString();
            if (returncode.equals("0000")) {
                String fpdm = resultMap.get("FP_DM").toString();
                String fphm = resultMap.get("FP_HM").toString();
                String kprq = resultMap.get("KPRQ").toString();
                String mwq = resultMap.get("FP_MW").toString();
                String jym = resultMap.get("JYM").toString();
                String ewm=null;
                if(null!=resultMap.get("EWM")){
                    ewm = resultMap.get("EWM").toString();
                }
                String jqbh = resultMap.get("JQBH").toString();
                resultMap.remove("EWM");
                logger.info("-------税控服务器返回报文----------"+JSON.toJSONString(resultMap));
                kpls.setFpdm(fpdm);
                kpls.setFphm(fphm);
                kpls.setFpztdm("00");
                kpls.setErrorReason(null);
                kpls.setKprq(TimeUtil.getSysDateInDate(kprq, null));
                kpls.setXgsj(new Date());
                kpls.setXgry(1);
                kpls.setMwq(mwq);
                kpls.setFpEwm(ewm);
                kpls.setJym(jym);
                kpls.setSksbm(jqbh);
                if (("").equals(kpls.getBz()) || null == kpls.getBz()) {
                    kpls.setBz("机器编号：" + jqbh);
                }
                if (StringUtils.isNotBlank(returnmsg)) {
                    kpls.setErrorReason(returnmsg);
                } else {
                    kpls.setErrorReason(null);
                }
                kplsService.save(kpls);
                Jyls jyls = jylsService.findOne(kpls.getDjh());
                jyls.setClztdm("91");
                jylsService.save(jyls);
                String czlxdm = kpls.getFpczlxdm();
                if ("12".equals(czlxdm) || "13".equals(czlxdm)) {
                    if (kpls.getHzyfphm() != null && kpls.getHzyfpdm() != null) {
                        kpls.setJylsh("");
                        Kpls parms=new Kpls();
                        parms.setFpdm(kpls.getHzyfpdm());
                        parms.setFphm(kpls.getHzyfphm());
                        Kpls ykpls = kplsService.findByfphm(parms);
                        Map param2 = new HashMap<>();
                        param2.put("kplsh", ykpls.getKplsh());
                        // 全部红冲后修改
                        //Kpspmxvo mxvo = kpspmxService.findKhcje(param2);
                        //if (mxvo.getKhcje() == 0) {
                            param2.put("fpztdm", "02");
                            kplsService.updateFpczlx(param2);
                        /*} else {
                            param2.put("fpztdm", "01");
                            kplsService.updateFpczlx(param2);
                        }*/
                    }
                }
                kpls.setJylsh(jyls.getJylsh());
                kplsService.save(kpls);
                if(null==kpls.getPdfurl()){
                    //此处生成PDF
                    PdfTask pdfTask=new PdfTask();
                    pdfTask.setKplsh(kpls.getKplsh());
                    if (taskExecutor == null) {
                        taskExecutor = ApplicationContextUtils.getBean(ThreadPoolTaskExecutor.class);
                    }
                    taskExecutor.execute(pdfTask);
                }
            } else {
                kpls.setFpztdm("05");
                if(returnmsg.equals("00F103:Socket连接有误")){
                    kpls.setFpztdm("04");
                }
                kpls.setErrorReason(returnmsg);
                kpls.setXgsj(new Date());
                kpls.setXgry(1);
                kplsService.save(kpls);
                Jyls jyls = jylsService.findOne(kpls.getDjh());
                jyls.setClztdm("92");
                jylsService.save(jyls);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kpls.getSerialorder();
    }

    public String skServerKP(int kplsh) {
        try {
            Kpls kpls = kplsService.findOne(kplsh);
            Jyls jyls = jylsService.findOne(kpls.getDjh());
            Jyxxsq jyxxsq = jyxxsqService.findOne(jyls.getSqlsh());
            KplsVO5 kplsVO5 = new KplsVO5(kpls, jyxxsq);
            Map resultMap = null;
            String resultxml = "";
            List resultList = new ArrayList();
            double hjje = 0.00;
            double hjse = 0.00;
            double kce = 0.00;
            //获取对应开票商品明细信息
            Map params = new HashMap();
            params.put("kplsh", kplsVO5.getKplsh());
            List<Kpspmxvo> tmpList = kpspmxService.findSkMxList(params);
            Kpspmxvo kpspmxvo = new Kpspmxvo();
            List<Kpspmxvo> kpspmxvoListNew=new ArrayList<>();
            for (int j = 0; j < tmpList.size(); j++) {
                kpspmxvo = tmpList.get(j);
                hjje = hjje + kpspmxvo.getSpje();
                hjse = hjse + kpspmxvo.getSpse();
                kce = kce +(kpspmxvo.getKce()==null?0d:kpspmxvo.getKce());
                if (kpspmxvo.getSpdj() != null) {
                    BigDecimal b = new BigDecimal(kpspmxvo.getSpdj());
                    double f1 = b.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
                    kpspmxvo.setSpdj(f1);
                }
                if(kpspmxvo.getFphxz().equals("1")){
                    kpspmxvo.setSpdw(null);
                    kpspmxvo.setSpggxh(null);
                }
                kpspmxvoListNew.add(kpspmxvo);
            }

            Map params2 = new HashMap();
            String fpzldm = kplsVO5.getFpzldm();
            if (fpzldm.equals("01")) {
                kplsVO5.setFpzldm("004");
            } else if (fpzldm.equals("02")) {
                kplsVO5.setFpzldm("007");
            } else if (fpzldm.equals("12")) {
                kplsVO5.setFpzldm("026");
            } else if (fpzldm.equals("03")) {
                kplsVO5.setFpzldm("025");
            }
            String fpczlxdm = kplsVO5.getFpczlxdm();
            String kplx = null;
            if (fpczlxdm.equals("11")) {
                kplx = "0";
            } else if (fpczlxdm.equals("12")) {
                kplx = "1";
            }
            params2.put("kplx", kplx);
            Cszb cszb = cszbService.getSpbmbbh(kplsVO5.getGsdm(), kplsVO5.getXfid(), kplsVO5.getSkpid(), "spbmbbh");
            String spbmbbh = cszb.getCsz();
            params2.put("spbmbbh", spbmbbh);
            params2.put("zsfs",kpls.getZsfs()==null?"0":kpls.getZsfs());
            params2.put("kpls", kplsVO5);
            params2.put("kpspmxList", kpspmxvoListNew);
            params2.put("mxCount", kpspmxvoListNew.size());
            params2.put("hjje", hjje);
            params2.put("hjse", hjse);
            params2.put("kce", fpczlxdm.equals("12")?null:kce);

            /**
             * 模板名称，电子票税控服务器报文
             */
            String templateName = "skdzfp-xml.ftl";
            String result2 = TemplateUtils.generateContent(templateName, params2);
            logger.debug("封装传开票通的报文" + result2);

            Cszb cszb2 = cszbService.getSpbmbbh(kpls.getGsdm(), kpls.getXfid(), kpls.getSkpid(), "skurl");
            String url = cszb2.getCsz();
            int j=2;
            if(kpls.getGsdm().equals("afb")) {
                resultMap = DzfphttpsPost(result2, url, kplsVO5.getDjh() + "$" + kplsVO5.getKplsh(), kplsVO5.getXfsh(),
                        kplsVO5.getJylsh(), j);
            }else{
                resultMap = DzfphttpPost(result2, url, kplsVO5.getDjh() + "$" + kplsVO5.getKplsh(), kplsVO5.getXfsh(),
                        kplsVO5.getJylsh(), j);
            }
            String serialorder = this.updateKpls(resultMap);
            resultxml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                    "<Responese>\n" +
                    "    <ReturnCode>0000</ReturnCode>\n" +
                    "    <ReturnMessage>" + serialorder + "</ReturnMessage>\n" +
                    "</Responese>\n";
            logger.debug("封装传开票通的返回报文" + JSONObject.toJSONString(resultMap));
            return "1";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0";
        }
    }
}
