package com.rjxx.taxeasy.bizcomm.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.taxeasy.domains.Gsxx;
import com.rjxx.taxeasy.domains.Jyls;
import com.rjxx.taxeasy.domains.Jyspmx;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.domains.Kpspmx;
import com.rjxx.taxeasy.service.GsxxService;
import com.rjxx.taxeasy.service.JylsService;
import com.rjxx.taxeasy.service.JyspmxService;
import com.rjxx.taxeasy.service.KplsService;
import com.rjxx.taxeasy.service.KpspmxService;
import com.rjxx.taxeasy.vo.Fpcxvo;
import com.rjxx.taxeasy.vo.Kpspmxvo;
import com.rjxx.time.TimeUtil;

@Service
public class FpclService {
 @Autowired private KplsService kplsService;
 @Autowired private JylsService jylsService;
 @Autowired private SkService skService;
 @Autowired private JyspmxService jymxService;
 @Autowired private GsxxService gsxxService;
 @Autowired private KpspmxService kpspmxService;
 @Autowired private DataOperte dc;
	public Boolean kpcl(Integer djh,Integer yhid) throws Exception {
		//规则处理
		Jyls jyls1 = jylsService.findOne(djh);
	
		Jyspmx jyspmx = new Jyspmx();
		jyspmx.setDjh(djh);
		List<Jyspmx> list = jymxService.findAllByParams(jyspmx);

		Double hjje=0d;
		Double hjse= 0d;
		Double jshj = 0d;
		for (Jyspmx jyspmx2 : list) {
			hjje+=jyspmx2.getSpje();
			hjse+=jyspmx2.getSpse();
		}
		jshj=hjje+hjse;
		Kpls kpls2 = new Kpls();
		kpls2.setDjh(djh);
		kpls2.setJylsh(jyls1.getJylsh());
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
		kpls2.setHjse(hjse);
		kpls2.setHjje(hjje);
		kpls2.setGsdm(jyls1.getGsdm());
		kpls2.setYxbz("1");
		kpls2.setLrsj(jyls1.getLrsj());
		kpls2.setXgsj(jyls1.getXgsj());
		kpls2.setSkpid(jyls1.getSkpid());
		kpls2.setLrry(yhid);
		kpls2.setXgry(yhid);
		kpls2.setFpztdm("00");
		kplsService.save(kpls2);
		for (Jyspmx jyspmx2 : list) {
			Kpspmx kpspmx = new Kpspmx();
			kpspmx.setKplsh(kpls2.getKplsh());
			kpspmx.setDjh(jyspmx2.getDjh());
			kpspmx.setSpmxxh(jyspmx2.getSpmxxh());
			kpspmx.setFphxz(jyspmx2.getFphxz());
			kpspmx.setSpdm(jyspmx2.getSpdm());
			kpspmx.setSpmc(jyspmx2.getSpmc());
			kpspmx.setSpggxh(jyspmx2.getSpggxh());
			kpspmx.setSpdw(jyspmx2.getSpdw());
		    if (jyspmx2.getSpdj() != null) {
                kpspmx.setSpdj(jyspmx2.getSpdj().doubleValue());
            }
            kpspmx.setSpdw(jyspmx2.getSpdw());
            if (jyspmx2.getSps() != null) {
                kpspmx.setSps(jyspmx2.getSps().doubleValue());
            }
			kpspmx.setSpje(jyspmx2.getSpje().doubleValue());
			kpspmx.setSpsl(jyspmx2.getSpsl().doubleValue());
			kpspmx.setSpse(jyspmx2.getSpse().doubleValue());
			kpspmx.setLrsj(new Date());
			kpspmx.setLrry(yhid);
			kpspmx.setXgsj(new Date());
			kpspmx.setXgry(yhid);
			kpspmx.setKhcje(jyspmx2.getJshj().doubleValue());
			kpspmx.setYhcje(0d);
			kpspmxService.save(kpspmx);
		}
		jyls1.setClztdm("02");
		jylsService.save(jyls1);
		InvoiceResponse response = skService.callService(kpls2.getKplsh());
		if ("0000".equals(response.getReturnCode())) {
		
			return true;
		}else{
			dc.saveLog(djh, "92", "1", "", "调用开票接口失败"+response.getReturnMessage(), 2, jyls1.getXfsh(), jyls1.getJylsh());
			return false;
		}
	}
	//红冲处理
	public Boolean hccl(Integer kplsh,Integer yhid, String gsdm,String hcjeStr,String xhStr) throws Exception {
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
				return true;
			}else{
				jyls1.setClztdm("02");
				dc.saveLog(djh, "92", "1", "", "调用红冲接口失败"+response.getReturnMessage(), 2, jyls.getXfsh(), jyls.getJylsh());
				return false;
			}
	}
	//作废处理
	public Boolean zfcl(Integer kplsh,Integer yhid,String gsdm) throws Exception {
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
			return true;
		}else{
			dc.saveLog(jyls.getDjh(), "92", "1", "", "调用作废接口失败"+response.getReturnMessage(), 2, jyls.getXfsh(), jyls.getJylsh());
			return false;
		}
	}

}
