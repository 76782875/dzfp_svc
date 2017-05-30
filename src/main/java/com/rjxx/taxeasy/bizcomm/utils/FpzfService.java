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
			    kpls.setFpczlxdm("14");//作废处理
				kpls.setFpztdm("04");//作废走开票申请呢
				kplsService.save(kpls);
				response.setReturnCode("0000");
				response.setReturnMessage("待作废提交成功！");
			return response;
	}

}
