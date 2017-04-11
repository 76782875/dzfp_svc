package com.rjxx.taxeasy.bizcomm.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.taxeasy.domains.Fpgz;
import com.rjxx.taxeasy.domains.Gsxx;
import com.rjxx.taxeasy.domains.Jyls;
import com.rjxx.taxeasy.domains.Jyspmx;
import com.rjxx.taxeasy.domains.Jyxxsq;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.domains.Kpspmx;
import com.rjxx.taxeasy.domains.Skp;
import com.rjxx.taxeasy.domains.Xf;
import com.rjxx.taxeasy.service.FpgzService;
import com.rjxx.taxeasy.service.GsxxService;
import com.rjxx.taxeasy.service.JylsService;
import com.rjxx.taxeasy.service.JyspmxService;
import com.rjxx.taxeasy.service.KplsService;
import com.rjxx.taxeasy.service.KpspmxService;
import com.rjxx.taxeasy.service.SkpService;
import com.rjxx.taxeasy.service.XfService;
import com.rjxx.taxeasy.vo.Fpcxvo;
import com.rjxx.taxeasy.vo.JyspmxDecimal;
import com.rjxx.taxeasy.vo.JyspmxDecimal2;
import com.rjxx.taxeasy.vo.Kpspmxvo;
import com.rjxx.time.TimeUtil;

@Service
public class FpclService {
 @Autowired private KplsService kplsService;
 @Autowired private JylsService jylsService;
 @Autowired private FpgzService fpgzService;
 @Autowired private SkService skService;
 @Autowired private SkpService skpService;
 @Autowired private JyspmxService jymxService;
 @Autowired private GsxxService gsxxService;
 @Autowired private KpspmxService kpspmxService;
 @Autowired private DataOperte dc;
 @Autowired private XfService xfService;
	public boolean kpcl1(Integer djh,String dybz) throws Exception {
		Jyls jyls1 = jylsService.findOne(djh);
		Jyspmx jyspmx = new Jyspmx();
		jyspmx.setDjh(djh);
		List<Jyspmx> list = jymxService.findAllByParams(jyspmx);
		//保存开票流水
		Kpls kpls = saveKp(jyls1, list, dybz);
  		//jyls1.setClztdm("02");
		jyls1.setClztdm("40");
 		jylsService.save(jyls1);
 		return true;
/*		Jyls jyls1 = jylsService.findOne(djh);
		Jyspmx jyspmx = new Jyspmx();
		jyspmx.setDjh(djh);
		List<Jyspmx> list = jymxService.findAllByParams(jyspmx);
		//保存开票流水
  		//jyls1.setClztdm("02");
 		if (csz==1) {
 			Kpls kpls = saveKp(jyls1, list, dybz,"14");
 			jyls1.setClztdm("40");
 	 		jylsService.save(jyls1);
 			InvoiceResponse response = skService.callService(kpls.getKplsh());
 			return response;
		}else{
			Kpls kpls = saveKp(jyls1, list, dybz,"04");
 			jyls1.setClztdm("40");
 	 		jylsService.save(jyls1);
		}
 		return new InvoiceResponse();*/
/* 		
		if ("0000".equals(response.getReturnCode())) {
		}else{
			kpls.setFpztdm("04");
			Map<String, Object> params = new HashMap<>();
			params.put("kplsh", kpls.getKplsh());
			List<Kpspmx> list2 = kpspmxService.findMxNewList(params);
			kpspmxService.deleteAll(list2);
			kplsService.delete(kpls);
			jyls1.setClztdm("00");
	 		jylsService.save(jyls1);
			dc.saveLog(djh, "92", "1", "", "调用开票接口失败"+response.getReturnMessage(), 2, jyls1.getXfsh(), jyls1.getJylsh());
			return response;
		}
         response.setReturnCode("0000");*/
	}
	
	//全部开票
	public InvoiceResponse kpcl(Integer djh,String dybz) throws Exception {
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
		}else{
			Map<String, Object> params = new HashMap<>();
			params.put("kplsh", kpls.getKplsh());
			List<Kpspmx> list2 = kpspmxService.findMxNewList(params);
			kpspmxService.deleteAll(list2);
			kplsService.delete(kpls);
	  		jyls1.setClztdm("00");
	 		jylsService.save(jyls1);
			dc.saveLog(djh, "92", "1", "", "调用开票接口失败"+response.getReturnMessage(), 2, jyls1.getXfsh(), jyls1.getJylsh());
			return response;
		}
         response.setReturnCode("0000");
		return response;
	}
	
	//红冲处理
	public InvoiceResponse hccl(Integer kplsh,Integer yhid, String gsdm,String hcjeStr,String xhStr) throws Exception {
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
			double hjje=0; 
			double hjse=0;
			double jshj=0;
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
						hjje+=jymx.getSpje();
						hjse+=jymx.getSpse();
						jshj+=jymx.getJshj();
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
			}else{
				jyls1.setClztdm("02");
				dc.saveLog(djh, "92", "1", "", "调用红冲接口失败"+response.getReturnMessage(), 2, jyls.getXfsh(), jyls.getJylsh());
				return response;
			}
	}
	//作废处理
	public InvoiceResponse zfcl(Integer kplsh,Integer yhid,String gsdm) throws Exception {
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
		}else{
			dc.saveLog(jyls.getDjh(), "92", "1", "", "调用作废接口失败"+response.getReturnMessage(), 2, jyls.getXfsh(), jyls.getJylsh());
			return response;
		}
	}
	 /**
     * 保存开票流水
     *
     * @param jyls
     * @return
     */
	public Kpls saveKpls(Jyls jyls, List<Jyspmx> jyspmx1,String dybz) throws Exception {
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
        if (dybz!=null&&dybz.equals("1")) {
			kpls.setPrintflag("2");
		}else{
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
        String fpczlxdm = jyls.getFpczlxdm();
        if ("12".equals(fpczlxdm) || "13".equals(fpczlxdm)||"23".equals(fpczlxdm)) {
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
        kpls.setFpztdm("04");
        kpls.setSkpid(jyls.getSkpid());
        kplsService.save(kpls);
        return kpls;
    }

    /**
     * 保存开票商品明细
     *
     * @param kpls
     * @param fpJyspmxList
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
            kpspmx.setYhcje(0d);
            kpspmxService.save(kpspmx);
        }
    }
    @Transactional
    public Kpls saveKp( Jyls jyls1 ,List<Jyspmx> list ,String dybz) throws Exception{
		Kpls kpls = saveKpls(jyls1, list,dybz);
		saveKpspmx(kpls, list);
		return kpls;
    }
    
    //直接开票
    /*
     * 
     */
    public List<Object> zjkp(List<Jyxxsq> list,String kpfs) throws Exception{
    	List<Object> result = new ArrayList<>();
    	boolean sfqzfp = true;
    	for (Jyxxsq jyxxsq : list) {
    		// 转换明细
    		Map<String, Object> params1 = new HashMap<>();
			params1.put("sqlsh", jyxxsq.getSqlsh());
    		List<JyspmxDecimal2> jyspmxs = jymxService.getNeedToKP3(params1);
    		// 价税分离
			if ("1".equals(jyxxsq.getHsbz())) {
				jyspmxs = SeperateInvoiceUtils.separatePrice2(jyspmxs);
			}
			//取最大限额
			double zdje=0d;
			double fpje=0d;
			int fphs1 = 8;
			int fphs2 = 100;
			String hsbz="0";
			boolean flag = false;
			Skp skp = skpService.findOne(jyxxsq.getSkpid());
			if ("01".equals(jyxxsq.getFpzldm())) {
				zdje=skp.getZpmax();
				fpje = skp.getZpfz();
			}else if ("02".equals(jyxxsq.getFpzldm())) {
				zdje=skp.getPpmax();
				fpje = skp.getPpfz();
			}else if ("12".equals(jyxxsq.getFpzldm())) {
				zdje=skp.getDpmax();
				fpje = skp.getFpfz();
			}
			List<Fpgz> listt = fpgzService.findAllByParams(new HashMap<>());
			Xf x = new Xf();
			x.setGsdm(jyxxsq.getGsdm());
			x.setXfsh(jyxxsq.getXfsh());
			Xf xf = xfService.findOneByParams(x);
			for (Fpgz fpgz : listt) {
				if (fpgz.getXfids().contains(String.valueOf(xf.getId()))) {
					if ("01".equals(jyxxsq.getFpzldm())) {
						fphs1 = fpgz.getZphs();
						fpje= fpgz.getZpxe();
					} else if ("02".equals(jyxxsq.getFpzldm())) {
						fphs1 = fpgz.getPphs();
						fpje= fpgz.getPpxe();
					} else if ("12".equals(jyxxsq.getFpzldm())) {
						fphs2 = fpgz.getDzphs();
						fpje= fpgz.getDzpxe();
					}
					flag = true;
					hsbz=fpgz.getHsbz();
					if (fpgz.getSfqzfp().equals("0")) {
						sfqzfp=false;
					}
				}
			}
			if (!flag) {
				Map<String, Object> paramse = new HashMap<>();
				paramse.put("mrbz", "1");
				paramse.put("gsdm", jyxxsq.getGsdm());
				Fpgz fpgz2 = fpgzService.findOneByParams(paramse);
				if (null != fpgz2) {
					if ("01".equals(jyxxsq.getFpzldm())) {
						fphs1 = fpgz2.getZphs();
						fpje= fpgz2.getZpxe();
					} else if ("02".equals(jyxxsq.getFpzldm())) {
						fphs1 = fpgz2.getPphs();
						fpje= fpgz2.getPpxe();
					} else if ("12".equals(jyxxsq.getFpzldm())) {
						fphs2 = fpgz2.getDzphs();
						fpje= fpgz2.getDzpxe();
					}
					hsbz=fpgz2.getHsbz();
					if (fpgz2.getSfqzfp().equals("0")) {
						sfqzfp=false;
					}
				}
			}
			if (jyxxsq.getSfdyqd()!=null&&jyxxsq.getSfdyqd().equals("1")) {
				fphs1=99999;
				fphs2=99999;
			}
			if (0==fpje) {
				fpje=zdje;
			}
			if(hsbz.equals("1")){
				// 分票
				if (jyxxsq.getFpzldm().equals("12")) {
						jyspmxs=SeperateInvoiceUtils.splitInvoicesbhs(jyspmxs, new BigDecimal(Double.valueOf(zdje)), new BigDecimal(fpje), fphs2,sfqzfp);
				} else {
						jyspmxs=SeperateInvoiceUtils.splitInvoicesbhs(jyspmxs, new BigDecimal(Double.valueOf(zdje)), new BigDecimal(fpje), fphs1,sfqzfp);
				}
			}else{
				if (jyxxsq.getFpzldm().equals("12")) {
					jyspmxs=SeperateInvoiceUtils.splitInvoices2(jyspmxs, new BigDecimal(Double.valueOf(zdje)), new BigDecimal(fpje), fphs2,sfqzfp);
			} else {
					jyspmxs=SeperateInvoiceUtils.splitInvoices2(jyspmxs, new BigDecimal(Double.valueOf(zdje)), new BigDecimal(fpje), fphs1,sfqzfp);
			}	
			}
		
			// 保存进交易流水
		    Map<Integer, List<JyspmxDecimal2>> fpMap = new HashMap<>();
            for (JyspmxDecimal2 jyspmx : jyspmxs) {
                int fpnum = jyspmx.getFpnum();
                List<JyspmxDecimal2> list2 = fpMap.get(fpnum);
                if (list == null) {
                    list = new ArrayList<>();
                    fpMap.put(fpnum, list2);
                }
                list2.add(jyspmx);
            }
            //fpnum和kplsh对应关系
            Map<Integer, Integer> fpNumKplshMap = new HashMap<>();
            //保存开票信息
            int i= 1;
            for (Map.Entry<Integer, List<JyspmxDecimal2>> entry : fpMap.entrySet()) {
                int fpNum = entry.getKey();
                List<JyspmxDecimal2> fpJyspmxList = entry.getValue();
                Jyls jyls = saveJyls(jyxxsq, fpJyspmxList);
                List<Jyspmx> list2 = saveKpspmx(jyls, fpJyspmxList);
                //保存开票流水
                Kpls kpls = saveKpls(jyls, list2, jyxxsq.getSfdy());
                saveKpspmx(kpls, list2);
                if (kpfs.equals("01")) {
                	  InvoiceResponse response =  skService.callService(kpls.getKplsh());
                	  result.add(response);
				}else{
					kpls.setSfdyqd(jyxxsq.getSfdyqd()+"-"+jyxxsq.getSqlsh());
					 result.add(kpls);
				}
               i++;
            }
		}
    	return result;
    }
    /**
	 * 保存交易流水`
	 *
	 * @param jyls
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
			jymx.setFphxz("0");
			jymxService.save(jymx);
			list.add(jymx);
		}
		return list;
	}
}
