package com.rjxx.taxeasy.bizcomm.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.service.KplsService;

@Service
public class FpzfService {
	
	 @Autowired private KplsService kplsService;

	 
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
