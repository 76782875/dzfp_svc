package com.rjxx.taxeasy.bizcomm.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rjxx.taxeasy.domains.Cszb;
import com.rjxx.taxeasy.dubbo.business.tcs.service.DubboInvoiceService;
import com.rjxx.taxeasy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.taxeasy.domains.Jyls;
import com.rjxx.taxeasy.domains.Kpls;

@Service
public class FpcdService {
	@Autowired
	private KplsService kplsService;
	@Autowired
	private JylsService jylsService;
	@Autowired
	private FpgzService fpgzService;
	@Autowired
	private SkService skService;
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
	private CszbService cszbService;
	@Reference(version = "1.0.0", group = "tcs", timeout = 120000, retries = -2)
	public DubboInvoiceService dubboInvoiceService;

	//重打处理
	public InvoiceResponse cdcl(Integer kplsh, Integer yhid, String gsdm) throws Exception {
		InvoiceResponse response = new InvoiceResponse();
		try {
			Kpls kpls = kplsService.findOne(kplsh);
			Integer djh = kpls.getDjh();
			Map param4 = new HashMap<>();
			param4.put("djh", djh);
			Jyls jyls = jylsService.findJylsByDjh(param4);
			Cszb cszb = cszbService.getSpbmbbh(kpls.getGsdm(), kpls.getXfid(), kpls.getSkpid(), "kpfs");
			if (cszb != null && "01".equals(cszb.getCsz())) {
				skService.reprintInvoice(kplsh);//发票重打接口
				response.setReturnCode("0000");
				response.setReturnMessage("重打请求成功！");
			} else if (cszb != null && "04".equals(cszb.getCsz())) {

				String parms = "skpid=" + kpls.getSkpid() + "&fpzldm=" + kpls.getFpzldm() + "&fpdm=" + kpls.getFpdm() + "&fphm=" + kpls.getFphm();
				String encryptStr = skService.encryptSkServerParameter(parms);
				dubboInvoiceService.PrintInvoice(encryptStr);
//				response = XmlJaxbUtils.convertXmlStrToObject(InvoiceResponse.class, result);
				response.setReturnCode("0000");
				response.setReturnMessage("重打请求成功！");
			} else {
				response.setReturnMessage("不能执行发票重打！请检查开票方式！");

			}
			/*if ("0000".equals(response.getReturnCode())) {
				return response;
			} else {
				dc.saveLog(jyls.getDjh(), "92", "1", "", "调用重打接口失败" + response.getReturnMessage(), 2, jyls.getXfsh(), jyls.getJylsh());
				return response;
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			response.setReturnMessage(e.getMessage());
		}
		return response;
	}
}
