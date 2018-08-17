package com.rjxx.taxeasy.bizcomm.utils;

import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.service.*;
import com.rjxx.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FpzfService {
	
	@Autowired
	private KplsService kplsService;

	@Autowired
	private KpspmxService kpspmxService;

	@Autowired
	private JylsService jylsService;

	@Autowired
	private JyxxsqService jyxxsqService;

	@Autowired
	private JymxsqService jymxsqService;

	@Autowired
	private SkService skService;

	@Autowired
	private CszbService cszbService;
	@Autowired
	private SkpService skpService;
	 
	//作废处理
		public InvoiceResponse zfcl(Integer kplsh,Integer yhid,String gsdm) throws Exception {
			InvoiceResponse response=new InvoiceResponse();
			try {

				Kpls kpls = kplsService.findOne(kplsh);
				savejyxxsq(kpls.getKplsh());
				Cszb cszb = cszbService.getSpbmbbh(kpls.getGsdm(), kpls.getXfid(), kpls.getSkpid(), "kpfs");
				//作废处理
				kpls.setFpczlxdm("14");
				//作废走开票申请
				kpls.setFpztdm("14");
				kpls.setZfr(kpls.getKpr());
				kplsService.save(kpls);
				if("01".equals(cszb.getCsz())){
					skService.voidInvoice(kpls.getKplsh());
				}else{
					skService.InvalidateInvoice(kpls.getKplsh());
				}
				response.setReturnCode("0000");
				response.setReturnMessage("待作废提交成功！");
			}catch (Exception e){
				e.printStackTrace();
			}
			return response;
	}

	//服务器开具纸票 -作废
	public String zfcl1(Integer kplsh) throws Exception {
		try {

			Kpls kpls = kplsService.findOne(kplsh);
			Skp skp = skpService.findOne(kpls.getSkpid());
			if(skp==null || StringUtils.isBlank(skp.getKpdip())){
				return  null;
			}
			savejyxxsq(kpls.getKplsh());
			//作废处理
			kpls.setFpczlxdm("14");
			//作废走开票申请
			kpls.setFpztdm("10");
			kpls.setZfr(kpls.getKpr());
			kplsService.save(kpls);
			String xml= GetXmlUtil.getFpzfXml(skp.getKpdip(),kpls.getFpzldm(), "1",kpls);
			return xml;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	private void savejyxxsq(Integer kplsh)throws Exception {
		Kpls kpls=kplsService.findOne(kplsh);
		Integer djh = kpls.getDjh();
		Map param4 = new HashMap<>();
		param4.put("djh", djh);
		Jyls jyls = jylsService.findJylsByDjh(param4);
		String ddh = jyls.getDdh(); // 查询原交易流水得ddh
		Jyxxsq jyxxsq=new Jyxxsq();
		jyxxsq.setJylsh(jyls.getJylsh());
		jyxxsq.setDdh(ddh);
		jyxxsq.setGflxr(kpls.getGflxr());
		jyxxsq.setGfyb(kpls.getGfyb());
		jyxxsq.setBz(kpls.getBz());
		jyxxsq.setClztdm("00");
		jyxxsq.setDdrq(new Date());
		jyxxsq.setFpczlxdm("14");
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
		jyxxsq.setJshj(kpls.getJshj());
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
		jyxxsq.setHsbz(jyls.getHsbz());
		jyxxsqService.save(jyxxsq);
		Map parms=new HashMap();
		parms.put("kplsh",kpls.getKplsh());
		List<Kpspmx> kpspmxList=kpspmxService.findMxList(parms);
		List<Jymxsq> jymxsqList=new ArrayList<>();
		for(int i=0;i<kpspmxList.size();i++){
			Kpspmx kpspmx=kpspmxList.get(i);
			Jymxsq jymxsq=new Jymxsq();
			jymxsq.setLrry(kpspmx.getLrry());
			jymxsq.setFphxz(kpspmx.getFphxz());
			jymxsq.setGsdm(kpspmx.getGsdm());
			jymxsq.setJshj(kpspmx.getSpje()+kpspmx.getSpse());
			jymxsq.setKkjje(kpspmx.getSpje()+kpspmx.getSpse());
			jymxsq.setKce(kpspmx.getKce());
			jymxsq.setKpddm(kpspmx.getKpddm());
			jymxsq.setLslbz(kpspmx.getLslbz());
			jymxsq.setSps(kpspmx.getSps());
			jymxsq.setSpdj(kpspmx.getSpdj());
			jymxsq.setSpdm(kpspmx.getSpdm());
			jymxsq.setSpse(kpspmx.getSpse());
			jymxsq.setSpdw(kpspmx.getSpdw());
			jymxsq.setSpje(kpspmx.getSpje());
			jymxsq.setSpsl(kpspmx.getSpsl());
			jymxsq.setSpggxh(kpspmx.getSpggxh());
			jymxsq.setSpmc(kpspmx.getSpmc());
			jymxsq.setSpmxxh(kpspmx.getSpmxxh());
			jymxsq.setYxbz("1");
			jymxsq.setYhzcbs(kpspmx.getYhzcbs());
			jymxsq.setYhzcmc(kpspmx.getYhzcmc());
			jymxsq.setSqlsh(jyxxsq.getSqlsh());
			jymxsq.setJshj(kpspmx.getSpje()+kpspmx.getSpse());
			jymxsq.setXgsj(new Date());
			jymxsq.setLrsj(new Date());
			jymxsqList.add(jymxsq);
		}
		jymxsqService.save(jymxsqList);
	}
}
