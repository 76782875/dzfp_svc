package com.rjxx.taxeasy.dingding.Helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.rjxx.taxeasy.domains.IsvCorpSuiteAuth;
import com.rjxx.taxeasy.domains.IsvCorpSuiteJsapiTicket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 开放平台取conf或者token相关http接口封装
 * Created by xlm on 2017/4/11.
 */

@ConfigurationProperties("oapiDomain")
@Configuration
public class ConfOapiRequestHelper {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    private HttpRequestHelper httpRequestHelper;
    private String oapiDomain;

    public String getOapiDomain() {
        return oapiDomain;
    }

    public void setOapiDomain(String oapiDomain) {
        this.oapiDomain = oapiDomain;
    }

    public HttpRequestHelper getHttpRequestHelper() {
        return httpRequestHelper;
    }

    public void setHttpRequestHelper(HttpRequestHelper httpRequestHelper) {
        this.httpRequestHelper = httpRequestHelper;
    }

    /**
     * 获取企业的jsapi ticket
     * @param suiteKey
     * @param corpId
     * @param accessToken
     * @return
     */
    public IsvCorpSuiteJsapiTicket getJSTicket(String suiteKey, String corpId, String accessToken) {
        try {
            String url = getOapiDomain() + "/get_jsapi_ticket?access_token=" + accessToken;
            String sr = httpRequestHelper.doHttpGet(url);
            JSONObject jsonObject = JSON.parseObject(sr);
            Long errCode = jsonObject.getLong("errcode");
            if (Long.valueOf(0).equals(errCode)) {
                String ticket = jsonObject.getString("ticket");
                Long expires_in = jsonObject.getLong("expires_in");
                IsvCorpSuiteJsapiTicket corpJSTicketVO = new IsvCorpSuiteJsapiTicket();
                corpJSTicketVO.setCorpId(corpId);
                corpJSTicketVO.setSuiteKey(suiteKey);
                corpJSTicketVO.setCorpJsapiTicket(ticket);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.SECOND, expires_in.intValue());
                corpJSTicketVO.setExpiredTime(calendar.getTime());
                return corpJSTicketVO;
            }else{
            	return null;
            }
        } catch (Throwable e) {
           
        	logger.info("accessToken:{}"+accessToken);

            return null;
        }
    }


    /**
     * 获取永久授权码
     * @param suiteKey
     * @param tmpAuthCode
     * @param suiteAccessToken
     * @return
     */
    public IsvCorpSuiteAuth getPermanentCode(String suiteKey,String tmpAuthCode, String suiteAccessToken) {
        try {
            String url = getOapiDomain() + "/service/get_permanent_code?suite_access_token=" + suiteAccessToken;
            Map<String, Object> parmMap = new HashMap<String, Object>();
            parmMap.put("tmp_auth_code", tmpAuthCode);
            String sr = httpRequestHelper.httpPostJson(url, JSON.toJSONString(parmMap));
            JSONObject jsonObject = JSON.parseObject(sr);
            Long errCode = jsonObject.getLong("errcode");
            if (Long.valueOf(0).equals(errCode)) {
                JSONObject corpObject = jsonObject.getJSONObject("auth_corp_info");
                String chPcode = StringUtils.isEmpty(jsonObject.getString("ch_permanent_code"))?"":jsonObject.getString("ch_permanent_code");
                String pCode = StringUtils.isEmpty(jsonObject.getString("permanent_code"))?"":jsonObject.getString("permanent_code");
                String corpId = corpObject.getString("corpid");
                IsvCorpSuiteAuth corpSuiteAuthVO = new IsvCorpSuiteAuth();
                corpSuiteAuthVO.setSuiteKey(suiteKey);
                corpSuiteAuthVO.setChPermanentCode(chPcode);
                corpSuiteAuthVO.setPermanentCode(pCode);
                corpSuiteAuthVO.setCorpId(corpId);
                return corpSuiteAuthVO;
            }else{
            	return null;
            }
        } catch (Throwable e) {
        	logger.info("suiteAccessToken:{}"+suiteAccessToken);
            return null;
        }
    }



   /* *//**
     * 获取服务窗token
     * @param suiteKey
     * @param corpId
     * @param chPermanentCode
     * @param suiteAccessToken
     * @return
     *//*
    public ServiceResult<CorpChannelTokenVO> getCorpChannelToken(String suiteKey, String corpId, String chPermanentCode, String suiteAccessToken) {
        try {
            String url = getOapiDomain() + "/service/get_channel_corp_token?suite_access_token=" + suiteAccessToken;
            Map<String, Object> parmMap = new HashMap<String, Object>();
            parmMap.put("auth_corpid", corpId);
            parmMap.put("ch_permanent_code", chPermanentCode);
            String sr = httpRequestHelper.httpPostJson(url, JSON.toJSONString(parmMap));
            JSONObject jsonObject = JSON.parseObject(sr);
            Long errCode = jsonObject.getLong("errcode");
            if (Long.valueOf(0).equals(errCode)) {
                String chAccessToken = jsonObject.getString("access_token");
                Long expiresIn = jsonObject.getLong("expires_in");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.SECOND, expiresIn.intValue());
                CorpChannelTokenVO corpChannelTokenVO = new CorpChannelTokenVO();
                corpChannelTokenVO.setCorpId(corpId);
                corpChannelTokenVO.setSuiteKey(suiteKey);
                corpChannelTokenVO.setCorpChannelToken(chAccessToken);
                corpChannelTokenVO.setExpiredTime(calendar.getTime());
                return ServiceResult.success(corpChannelTokenVO);
            }
            return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(), ServiceResultCode.SYS_ERROR.getErrCode());
        } catch (Throwable e) {
            bizLogger.error(LogFormatter.getKVLogData(LogFormatter.LogEvent.END,
                    LogFormatter.KeyValue.getNew("accessToken", suiteAccessToken)
            ), e);
            return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(), ServiceResultCode.SYS_ERROR.getErrCode());
        }
    }*/




    /**
     * 获取企业的服务窗jsapi ticket
     * @param suiteKey
     * @param corpId
     * @param accessToken
     * @return
     *//*
    public ServiceResult<CorpChannelJSAPITicketVO> getChannelJsapiTicket(String suiteKey, String corpId, String accessToken) {
        try {
            String url = getOapiDomain() + "/channel/get_channel_jsapi_ticket?access_token=" + accessToken;
            String sr = httpRequestHelper.doHttpGet(url);
            JSONObject jsonObject = JSON.parseObject(sr);
            Long errCode = jsonObject.getLong("errcode");
            if (Long.valueOf(0).equals(errCode)) {
                String ticket = jsonObject.getString("ticket");
                Long expires_in = jsonObject.getLong("expires_in");
                CorpChannelJSAPITicketVO corpJSTicketVO = new CorpChannelJSAPITicketVO();
                corpJSTicketVO.setCorpId(corpId);
                corpJSTicketVO.setSuiteKey(suiteKey);
                corpJSTicketVO.setCorpChannelJSAPITicket(ticket);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.SECOND, expires_in.intValue());
                corpJSTicketVO.setExpiredTime(calendar.getTime());
                return ServiceResult.success(corpJSTicketVO);
            }
            return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(), ServiceResultCode.SYS_ERROR.getErrCode());
        } catch (Throwable e) {
            bizLogger.info(LogFormatter.getKVLogData(LogFormatter.LogEvent.END,
                    LogFormatter.KeyValue.getNew("accessToken", accessToken)
            ), e);
            return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(), ServiceResultCode.SYS_ERROR.getErrCode());
        }
    }*/




    /**
     * 获取企业的授权信息
     * @param suiteKey
     * @param corpId
     * @param suiteAccessToken
     * @return
     */
    public IsvCorpSuiteAuth getAuthInfo(String suiteKey, String corpId, String suiteAccessToken) {
        try {
            String url = getOapiDomain() + "/service/get_auth_info?suite_access_token=" + suiteAccessToken;

            Map<String, Object> parmMap = new HashMap<String, Object>();
            parmMap.put("suite_key", suiteKey);
            parmMap.put("auth_corpid", corpId);
            String sr = httpRequestHelper.httpPostJson(url, JSON.toJSONString(parmMap));

            JSONObject jsonObject = JSON.parseObject(sr);
            Long errCode = jsonObject.getLong("errcode");
            if (Long.valueOf(0).equals(errCode)) {
            	IsvCorpSuiteAuth corpAuthInfoVO = JSON.parseObject(sr,IsvCorpSuiteAuth.class);
               return corpAuthInfoVO;
            }else{
            	return null;
            }
            
        } catch (Throwable e) {
           
            logger.info("suiteAccessToken:{}"+suiteAccessToken);
            return null;
        }
    }
}
