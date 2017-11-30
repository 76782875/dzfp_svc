package com.rjxx.taxeasy.bizcomm.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rjxx.taxeasy.vo.Kpspmxvo;
import com.rjxx.time.TimeUtil;

@Service
public class FphcService {
	 @Autowired private KplsService kplsService;
	 @Autowired private JylsService jylsService;
	 @Autowired private FpgzService fpgzService;
	 @Autowired private SkService skService;
	 @Autowired private JyspmxService jymxService;
	 @Autowired private GsxxService gsxxService;
	 @Autowired private KpspmxService kpspmxService;
	 @Autowired private DataOperte dc;
	 @Autowired private XfService xfService;
	 @Autowired private CszbService cszbService;
	 @Autowired private JyxxsqService jyxxsqService;
	 @Autowired private JymxsqService jymxsqService;



	//红冲处理
		public InvoiceResponse hccl(Integer kplsh,Integer yhid, String gsdm,String hcjeStr,String xhStr,String hztzdh,String jylsh) throws Exception {

			InvoiceResponse response=new InvoiceResponse();
			DecimalFormat df = new DecimalFormat("#.00");
			DecimalFormat df6 = new DecimalFormat("#.000000");
			try {
				Map map = new HashMap<>();
				map.put("kplsh", kplsh);
				Map paramsTmp = new HashMap();
				paramsTmp.put("gsdm", gsdm);
				Gsxx gsxx = gsxxService.findOneByParams(paramsTmp);
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
				if(jyls.getGsdm().equals("fwk")){
					if(null==jylsh||"".equals(jylsh)){
						jylsh = "JY" + new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
						jyls1.setJylsh(jylsh);
					}else{
						jyls1.setJylsh(jylsh);
					}
				}else{
					jyls1.setJylsh("JY" + new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date()));
				}
				Map resultMap=Savejyxxsq(kplsh,"JY" + new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date()),jyls);
				Jyxxsq jyxxsq=(Jyxxsq) resultMap.get("jyxxsq");
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
				jyls1.setHztzdh(hztzdh);
				jyls1.setSqlsh(jyxxsq.getSqlsh());
				jylsService.save(jyls1);
				//保存开票流水
				Kpls kpls2 = new Kpls();
				kpls2.setDjh(jyls1.getDjh());
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
				kpls2.setHzyfpdm(jyls1.getYfpdm());
				kpls2.setHzyfphm(jyls1.getYfphm());
				kpls2.setJshj(jyls1.getJshj());
				kpls2.setGsdm(jyls1.getGsdm());
				kpls2.setYxbz("1");
				kpls2.setLrsj(jyls1.getLrsj());
				kpls2.setXgsj(jyls1.getXgsj());
				kpls2.setSkpid(jyls1.getSkpid());
				kpls2.setLrry(yhid);
				kpls2.setSerialorder(jyls1.getJylsh()+jyls1.getDdh()+jyls1.getGsdm());
				kpls2.setXgry(yhid);
				kpls2.setKpddm(kpls.getKpddm());
				kpls2.setHztzdh(hztzdh);
				kplsService.save(kpls2);
				djh = jyls1.getDjh();
				double hjje = 0;
				double hjse = 0;
				double jshj = 0;
				List<Kpspmx> kpspmxList2=new ArrayList<>();
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
							jymx.setFphxz(mxItem.getFphxz());
							jymx.setYhzcbs(mxItem.getYhzcbs());
							jymx.setLslbz(mxItem.getLslbz());
							jymx.setYhzcmc(mxItem.getYhzcmc());
							jymx.setXfid(kpls.getXfid());
							jymx.setSkpid(kpls.getSkpid());
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
							kpspmx.setGsdm(jymx.getGsdm());
							kpspmx.setYhzcbs(jymx.getYhzcbs());
							kpspmx.setLslbz(jymx.getLslbz());
							kpspmx.setYhzcmc(jymx.getYhzcmc());
							kpspmx.setKpddm(kpls.getKpddm());
							//kpspmxService.save(kpspmx);
							kpspmxList2.add(kpspmx);
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
				kpspmxList2= DiscountDealUtil.discountMergeLinesKpspmx(kpspmxList2);
				kpspmxService.save(kpspmxList2);
				kpls2.setHjje(hjje);
				kpls2.setHjse(hjse);
				kpls2.setJshj(jshj);
				kpls2.setFpztdm("14"); //正在开具
				kplsService.save(kpls2);
				//区分开票方式
				Cszb cszb = cszbService.getSpbmbbh(kpls2.getGsdm(), kpls2.getXfid(), kpls2.getSkpid(), "kpfs");
				if(cszb != null && cszb.getCsz().equals("01")){
					skService.callService(kpls2.getKplsh());
				}
				if(cszb != null && cszb.getCsz().equals("03")){
					if(!kpls2.getFpzldm().equals("12")){
						skService.callService(kpls2.getKplsh());
					}else{
						skService.SkServerKP(kpls2.getKplsh());
					}
				}
				response.setReturnCode("0000");
				response.setReturnMessage("红冲请求已接受！");
				return response;
			}catch (Exception e){
				e.printStackTrace();
				response.setReturnCode("9999");
				response.setReturnMessage("红冲请求失败！");
				return response;
			}
		}
	private Map Savejyxxsq(Integer kplsh,String tqm,Jyls jyls) throws Exception {
		Map result = new HashMap();
		Kpls kpls = kplsService.findOne(kplsh);
		Jyxxsq jyxxsq = new Jyxxsq();
		String jylsh = "JY" + new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
		jyxxsq.setKpddm(kpls.getKpddm());
		jyxxsq.setJylsh(tqm);
		jyxxsq.setSjly("0");
		jyxxsq.setDdh(tqm);
		jyxxsq.setGflxr(kpls.getGflxr());
		jyxxsq.setGfyb(kpls.getGfyb());
		jyxxsq.setBz(kpls.getBz());
		jyxxsq.setClztdm("00");
		jyxxsq.setHsbz(jyls.getHsbz());
		jyxxsq.setDdrq(new Date());
		jyxxsq.setFpczlxdm("12");
		jyxxsq.setFpzldm(kpls.getFpzldm());
		jyxxsq.setFhr(kpls.getFhr());
		jyxxsq.setGfdh(kpls.getGfdh());
		jyxxsq.setGfdz(kpls.getGfdz());
		jyxxsq.setGfemail(kpls.getGfemail());
		jyxxsq.setGfid(kpls.getGfid());
		jyxxsq.setGfmc(kpls.getGfmc());
		jyxxsq.setGfsh(kpls.getGfsh());
		jyxxsq.setGsdm(kpls.getGsdm());
		jyxxsq.setGfyh(kpls.getGfyh());
		jyxxsq.setGfyhzh(kpls.getGfyhzh());
		jyxxsq.setXfdh(kpls.getXfdh());
		jyxxsq.setXfdz(kpls.getXfdz());
		jyxxsq.setXfid(kpls.getXfid());
		jyxxsq.setXflxr(kpls.getXflxr());
		jyxxsq.setXgsj(new Date());
		jyxxsq.setXfmc(kpls.getXfmc());
		jyxxsq.setXfsh(kpls.getXfsh());
		jyxxsq.setXfyb(kpls.getXfyb());
		jyxxsq.setXfyh(kpls.getXfyh());
		jyxxsq.setXfyhzh(kpls.getXfyhzh());
		jyxxsq.setYxbz("1");
		jyxxsq.setYkpjshj(0d);
		jyxxsq.setLrsj(new Date());
		jyxxsq.setJshj(-kpls.getJshj());
		jyxxsq.setHztzdh(kpls.getHztzdh());
		jyxxsq.setKpddm(kpls.getKpddm());
		jyxxsq.setZtbz("3");
		jyxxsq.setXgsj(kpls.getXgsj());
		jyxxsq.setSkr(kpls.getSkr());
		jyxxsq.setSkpid(kpls.getSkpid());
		jyxxsq.setKpr(kpls.getKpr());
		jyxxsq.setYfpdm(kpls.getFpdm());
		jyxxsq.setYfphm(kpls.getFphm());
		jyxxsq.setLrry(kpls.getLrry());
		jyxxsq.setSfdyqd(kpls.getSfdyqd());
		jyxxsq.setHsbz("1");
		jyxxsq.setTqm(tqm);
		jyxxsqService.save(jyxxsq);
		result.put("jyxxsq", jyxxsq);
		Map parms = new HashMap();
		parms.put("kplsh", kpls.getKplsh());
		List<Kpspmx> kpspmxList = kpspmxService.findMxList(parms);
		List<Jymxsq> jymxsqList = new ArrayList<>();
		for (int i = 0; i < kpspmxList.size(); i++) {
			Kpspmx kpspmx = kpspmxList.get(i);
			Jymxsq jymxsq = new Jymxsq();
			jymxsq.setDdh(jyls.getDdh());
			jymxsq.setLrry(kpspmx.getLrry());
			jymxsq.setFphxz(kpspmx.getFphxz());
			jymxsq.setGsdm(kpspmx.getGsdm());
			jymxsq.setJshj(-(kpspmx.getSpje() + kpspmx.getSpse()));
			jymxsq.setKkjje(-(kpspmx.getSpje() + kpspmx.getSpse()));
			jymxsq.setKce(kpspmx.getKce());
			jymxsq.setKpddm(kpspmx.getKpddm());
			jymxsq.setLslbz(kpspmx.getLslbz());
			try {
				jymxsq.setSps(-kpspmx.getSps());
			}catch (Exception e){
				jymxsq.setSps(null);
			}
			try {
				jymxsq.setSpdj(kpspmx.getSpdj());
			}catch (Exception e){
				jymxsq.setSpdj(null);
			}
			jymxsq.setSpdm(kpspmx.getSpdm());
			jymxsq.setSpse(-kpspmx.getSpse());
			jymxsq.setSpdw(kpspmx.getSpdw());
			jymxsq.setSpje(-kpspmx.getSpje());
			jymxsq.setSpsl(kpspmx.getSpsl());
			jymxsq.setSpggxh(kpspmx.getSpggxh());
			jymxsq.setSpmc(kpspmx.getSpmc());
			jymxsq.setSpmxxh(kpspmx.getSpmxxh());
			jymxsq.setYxbz("1");
			jymxsq.setHsbz(jyls.getHsbz());
			jymxsq.setYhzcbs(kpspmx.getYhzcbs());
			jymxsq.setYhzcmc(kpspmx.getYhzcmc());
			jymxsq.setSqlsh(jyxxsq.getSqlsh());
			jymxsq.setXfid(kpls.getXfid());
			jymxsq.setSkpid(kpls.getSkpid());
			jymxsq.setKpddm(kpls.getKpddm());
			jymxsq.setJshj(-(kpspmx.getSpje() + kpspmx.getSpse()));
			jymxsq.setXgsj(new Date());
			jymxsq.setLrsj(new Date());
			jymxsqList.add(jymxsq);
		}
		jymxsqService.save(jymxsqList);
		result.put("jymxsqList",jymxsqList);
		return result;
	}
}
