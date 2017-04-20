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
import com.dingtalk.open.client.api.model.isv.SuiteToken;
import com.dingtalk.open.client.api.service.isv.IsvService;
import com.rjxx.taxeasy.dingding.Helper.AuthHelper;
import com.rjxx.taxeasy.dingding.Helper.ConfOapiRequestHelper;
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
	@Autowired
    private ConfOapiRequestHelper confOapiRequestHelper;
	
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
				Map params=new HashMap<>();
				params.put("suiteKey", suiteKey);
				params.put("corpId", corpId);
		        IsvCorpSuiteJsapiTicket corpJSTicketDO = isvcorpsuitejsapiticketservice.findOneByParams(params);
		        Calendar calendar = Calendar.getInstance();
		        calendar.setTime(new Date());
		        calendar.add(Calendar.MINUTE, 10);//为了防止误差,提前10分钟更新jsticket
		        if (null == corpJSTicketDO || calendar.getTime().compareTo(corpJSTicketDO.getExpiredTime()) != -1) {
		    		IsvSuite suiteBO = isvsuiteservice.getIsvSuite(params);//获取套件
		    		IsvSuiteToken  IsvSuiteToken=  isvsuitetokenservice.findOneByParams(params);//获取套件token
		        	String corptoken=AuthHelper.getCorpAccessToken(corpId,IsvSuiteToken.getSuiteToken(),permanentCode);
		            IsvCorpSuiteJsapiTicket jsAPITicketSr = confOapiRequestHelper.getJSTicket(suiteKey, corpId, corptoken);
		        	logger.info("jsapiticket:{}"+JSON.toJSONString(jsAPITicketSr));
		        	corpJSTicketDO.setCorpId(corpId);
		        	corpJSTicketDO.setCorpaccesstoken(corptoken);
		        	corpJSTicketDO.setCorpJsapiTicket(jsAPITicketSr.getCorpJsapiTicket());
		        	//corpJSTicketDO.setId(jsAPITicketSr.getId());
		        	corpJSTicketDO.setExpiredTime(jsAPITicketSr.getExpiredTime());
		        	corpJSTicketDO.setSuiteKey(suiteKey);
		        	corpJSTicketDO.setGmtCreate(jsAPITicketSr.getGmtCreate());
		        	corpJSTicketDO.setGmtModified(jsAPITicketSr.getGmtModified());
		        }
		        isvcorpsuitejsapiticketservice.save(corpJSTicketDO);
				return true;

		}catch(Exception e){
			return false;
		}
	}
}
