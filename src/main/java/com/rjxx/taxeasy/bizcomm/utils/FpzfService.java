package com.rjxx.taxeasy.bizcomm.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.taxeasy.domains.Cszb;
import com.rjxx.taxeasy.domains.Jyls;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.service.CszbService;
import com.rjxx.taxeasy.service.FpgzService;
import com.rjxx.taxeasy.service.GsxxService;
import com.rjxx.taxeasy.service.JylsService;
import com.rjxx.taxeasy.service.JyspmxService;
import com.rjxx.taxeasy.service.KplsService;
import com.rjxx.taxeasy.service.KpspmxService;
import com.rjxx.taxeasy.service.XfService;

@Service
public class FpzfService {
	
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
	 
	//作废处理
		public InvoiceResponse zfcl(Integer kplsh,Integer yhid,String gsdm) throws Exception {
			
			InvoiceResponse response=new InvoiceResponse();
			
			Kpls kpls = kplsService.findOne(kplsh);
			Integer djh = kpls.getDjh();
			Map param4 = new HashMap<>();
			param4.put("djh", djh);
			Jyls jyls = jylsService.findJylsByDjh(param4);
			
			/*Cszb cszb=new Cszb();
			cszb.setGsdm(gsdm);
			cszb.setXfid(kpls.getXfid());
			cszb.setKpdid(kpls.getSkpid());
			cszb.setCsid(15);
			Cszb cszb2 =(Cszb) cszbService.findsfzlkpByParams(cszb);
			
			if(cszb2.getCsz().equals("否")){*/
				kpls.setFpztdm("10");//待作废
				kplsService.save(kpls);
				response.setReturnCode("0000");
				response.setReturnMessage("待作废提交成功！");
			/*}else if(cszb2.getCsz().equals("是")){
			
			
		    response = skService.voidInvoice(kplsh);//发票作废接口
			if ("0000".equals(response.getReturnCode())) {
				kpls.setFpczlxdm("14");
				kpls.setFpztdm("08");
				kplsService.save(kpls);
			    jyls.setFpczlxdm("14");
				jylsService.save(jyls);

				//return response;
			}else{
				dc.saveLog(jyls.getDjh(), "92", "1", "", "调用作废接口失败"+response.getReturnMessage(), 2, jyls.getXfsh(), jyls.getJylsh());
				//return response;
			}
		}*/
			return response;
	}

}
