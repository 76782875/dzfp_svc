package com.rjxx.taxeasy.bizcomm.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dingtalk.open.client.ServiceFactory;
import com.dingtalk.open.client.api.model.corp.JsapiTicket;
import com.dingtalk.open.client.api.model.isv.SuiteToken;
import com.dingtalk.open.client.api.service.corp.JsapiService;
import com.dingtalk.open.client.api.service.isv.IsvService;
import com.rjxx.taxeasy.dingding.Helper.AuthHelper;

import com.rjxx.taxeasy.domains.IsvCorpSuiteJsapiTicket;
import com.rjxx.taxeasy.domains.IsvCorpToken;
import com.rjxx.taxeasy.domains.IsvSuite;
import com.rjxx.taxeasy.domains.IsvSuiteTicket;
import com.rjxx.taxeasy.domains.IsvSuiteToken;
import com.rjxx.taxeasy.service.IsvCorpSuiteJsapiTicketService;
import com.rjxx.taxeasy.service.IsvCorpTokenService;
import com.rjxx.taxeasy.service.IsvSuiteService;
import com.rjxx.taxeasy.service.IsvSuiteTicketService;
import com.rjxx.taxeasy.service.IsvSuiteTokenService;
/**
 * 定时任务更新token
 * @author xlm
 *
 */
@Service
public class SuiteManageService {
    private static Logger logger = LoggerFactory.getLogger(SuiteManageService.class);

	@Autowired
    private IsvSuiteService  isvsuiteservice;
	
	@Autowired
    private IsvSuiteTicketService  isvsuiteticketservice;
	
	
	@Autowired
    private IsvSuiteTokenService isvsuitetokenservice;
	@Autowired
	private IsvCorpSuiteJsapiTicketService isvcorpsuitejsapiticketservice;
	@Autowired
	private IsvCorpTokenService isvcorptokenservice;

	
	public boolean saveOrUpdateSuiteToken(String suiteKey) {
		// TODO Auto-generated method stub
        try {
        	Map params=new HashMap();
    		params.put("suiteKey", suiteKey);
    		IsvSuite suiteBO = isvsuiteservice.getIsvSuite(params);
    		IsvSuiteToken  IsvSuiteToken=  isvsuitetokenservice.findOneByParams(params);
            IsvSuiteTicket suiteTicketDO = isvsuiteticketservice.findOneByParams(params);
            //调用钉钉接口
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
    		IsvService isvService = serviceFactory.getOpenService(IsvService.class);
			SuiteToken suiteToken = isvService.getSuiteToken(suiteBO.getSuiteKey(), suiteBO.getSuiteSecret(), suiteTicketDO.getTicket());
			IsvSuiteToken suiteTokenDO=new IsvSuiteToken();
			    suiteTokenDO.setId(IsvSuiteToken.getId());
	            suiteTokenDO.setGmtCreate(new Date());
	            suiteTokenDO.setGmtModified(new Date());
	            Calendar calendar = Calendar.getInstance();
	            calendar.setTime(new Date());
	            calendar.add(Calendar.SECOND,(int)suiteToken.getExpires_in());
	            suiteTokenDO.setExpiredTime(calendar.getTime());
	            suiteTokenDO.setSuiteKey(suiteKey);
	            suiteTokenDO.setSuiteToken(suiteToken.getSuite_access_token());
	            isvsuitetokenservice.save(suiteTokenDO);
	    		return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    		return false;
		}
	}

	public boolean getCorpJSAPITicket(String suiteKey, String corpId, String permanentCode) {
		// TODO Auto-generated method stub
		try{
			logger.info("jsapiticket生成任务service日志,suiteKey:{},corpId:{}"+suiteKey,corpId);
			Map params=new HashMap<>();
				params.put("suiteKey", suiteKey);
				params.put("corpId", corpId);
		        Calendar calendar = Calendar.getInstance();
		        calendar.setTime(new Date());
		        calendar.add(Calendar.MINUTE, 10);//为了防止误差,提前10分钟更新jsticket

		    		IsvSuite suiteBO = isvsuiteservice.getIsvSuite(params);//获取套件
		    		IsvSuiteToken  IsvSuiteToken=  isvsuitetokenservice.findOneByParams(params);//获取套件token
		        	String corptoken=AuthHelper.getCorpAccessToken(corpId,IsvSuiteToken.getSuiteToken(),permanentCode);
		        	
		        	System.out.println(corptoken);
		        	
		            ServiceFactory serviceFactory = ServiceFactory.getInstance();

		        	JsapiService jsapiService = serviceFactory.getOpenService(JsapiService.class);

					JsapiTicket JsapiTicket = jsapiService.getJsapiTicket(corptoken, "jsapi");
					String jsTicket = JsapiTicket.getTicket();
			        IsvCorpSuiteJsapiTicket corpJSTicketDO = isvcorpsuitejsapiticketservice.findOneByParams(params);
					Calendar ca=Calendar.getInstance();
					ca.setTime(new Date());
					ca.add(Calendar.HOUR_OF_DAY, 2);
                    if(corpJSTicketDO==null){
						 corpJSTicketDO=new IsvCorpSuiteJsapiTicket();
						 System.out.println("jsapidomains========="+JSON.toJSON(corpJSTicketDO).toString());
					}
					corpJSTicketDO.setCorpId(corpId);
					corpJSTicketDO.setCorpaccesstoken(corptoken);
					corpJSTicketDO.setCorpJsapiTicket(jsTicket);
					corpJSTicketDO.setExpiredTime(ca.getTime());
					corpJSTicketDO.setSuiteKey(suiteKey);
					corpJSTicketDO.setGmtCreate(new Date());
					corpJSTicketDO.setGmtModified(new Date());
		        	IsvCorpToken  isvcorptoken=isvcorptokenservice.findOneByParams(params);
		        	isvcorptoken.setCorpToken(corptoken);
		        	isvcorptoken.setExpiredTime(ca.getTime());
		        	isvcorptokenservice.save(isvcorptoken);
		            isvcorpsuitejsapiticketservice.save(corpJSTicketDO);
				return true;

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
