package com.rjxx.taxeasy.service;

import com.alibaba.fastjson.JSON;
import com.google.common.eventbus.EventBus;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.IsvCorpSuiteAuthJpaDao;
import com.rjxx.taxeasy.dao.IsvCorpSuiteAuthMapper;
import com.rjxx.taxeasy.dingding.Helper.ConfOapiRequestHelper;
import com.rjxx.taxeasy.dingding.Model.event.AuthChangeEvent;
import com.rjxx.taxeasy.domains.IsvApp;
import com.rjxx.taxeasy.domains.IsvCorpSuiteAuth;
import com.rjxx.taxeasy.domains.IsvSuite;
import com.rjxx.taxeasy.domains.IsvSuiteToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 13 17:36:36 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class IsvCorpSuiteAuthService {
    
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IsvCorpSuiteAuthJpaDao isvCorpSuiteAuthJpaDao;
    /*@Autowired
    private EventBus corpAuthSuiteEventBus;*/
    @Autowired
    private ConfOapiRequestHelper confOapiRequestHelper;
    @Autowired
    private IsvCorpSuiteAuthMapper isvCorpSuiteAuthMapper;
    @Autowired
    private IsvSuiteService isvSuiteService;
    @Autowired
    private IsvAppService isvappservice;
    @Autowired
    private IsvCorpAppService IsvCorpAppService;
    @Autowired
    private IsvSuiteTokenService isvsuitetokenservice;
    @Autowired
    private IsvCorpTokenService IsvCorpTokenService;

    public IsvCorpSuiteAuth findOne(int id) {
        return isvCorpSuiteAuthJpaDao.findOne(id);
    }

    public void save(IsvCorpSuiteAuth isvCorpSuiteAuth) {
        isvCorpSuiteAuthJpaDao.save(isvCorpSuiteAuth);
    }

    public void save(List<IsvCorpSuiteAuth> isvCorpSuiteAuthList) {
        isvCorpSuiteAuthJpaDao.save(isvCorpSuiteAuthList);
    }

    public IsvCorpSuiteAuth findOneByParams(Map params) {
        return isvCorpSuiteAuthMapper.findOneByParams(params);
    }

    public List<IsvCorpSuiteAuth> findAllByParams(Map params) {
        return isvCorpSuiteAuthMapper.findAllByParams(params);
    }

    public List<IsvCorpSuiteAuth> findByPage(Pagination pagination) {
        return isvCorpSuiteAuthMapper.findByPage(pagination);
    }
    /**
     * 用临时授权码换取企业授权信息
     *
     * @param suiteKey  套件key
     * @param tmpAuthCode  临时授权码
     * @return
     */
	public boolean saveOrUpdateCorpSuiteAuth(String suiteKey, String tmpAuthCode) {
		// TODO Auto-generated method stub
		
		logger.info("suiteKey:{},tmpAuthCode:{}",suiteKey,tmpAuthCode);
		
		
        try{
        Map map=new HashMap();
        map.put("suiteKey",suiteKey);
        //获取套件access_token
        IsvSuiteToken IsvSuiteToken=isvsuitetokenservice.findOneByParams(map);
        
        String suiteToken = IsvSuiteToken.getSuiteToken();
       
        IsvCorpSuiteAuth isvcorpsuiteauth = confOapiRequestHelper.getPermanentCode(suiteKey,tmpAuthCode,suiteToken);
        IsvCorpSuiteAuth IsvCorpSuiteAuthsave=new IsvCorpSuiteAuth();
        IsvCorpSuiteAuthsave.setChPermanentCode(isvcorpsuiteauth.getChPermanentCode());
        IsvCorpSuiteAuthsave.setCorpId(isvcorpsuiteauth.getCorpId());
        IsvCorpSuiteAuthsave.setId(isvcorpsuiteauth.getId());
        IsvCorpSuiteAuthsave.setPermanentCode(isvcorpsuiteauth.getPermanentCode());
        IsvCorpSuiteAuthsave.setSuiteKey(isvcorpsuiteauth.getSuiteKey());
        IsvCorpSuiteAuthsave.setGmtCreate(isvcorpsuiteauth.getGmtCreate());
        IsvCorpSuiteAuthsave.setGmtModified(new Date());
        this.save(IsvCorpSuiteAuthsave);
        
		    return true;
		}catch(Exception e){
			return false;
		}
	}
	/**
     * 处理权限变更事件
     *
     * @param suiteKey
     * @param corpId
     * @return
     */
	public boolean handleChangeAuth(String suiteKey, String corpId) {
		
		// TODO Auto-generated method stub
		logger.info("suiteKey:{},corpId:{}",suiteKey,corpId);
		String suiteToken = "";
        String permanentCode = "";
        try {
        	
        	 Map map=new HashMap();
             map.put("suiteKey",suiteKey);
             map.put("corpId", corpId);
             //获取套件access_token
             IsvSuiteToken IsvSuiteToken=isvsuitetokenservice.findOneByParams(map);
             IsvCorpSuiteAuth isvcorpsuiteauth=this.findOneByParams(map);
             if(isvcorpsuiteauth==null){
            	 logger.info("授权关系不存在或者已经解除,  suiteKey:{},corpId:{}",suiteKey,corpId);
                 return false;
             }
             suiteToken=IsvSuiteToken.getSuiteToken();
             permanentCode=isvcorpsuiteauth.getPermanentCode();
        }catch(Exception e){
        	return false;
        }
      //异步逻辑
        AuthChangeEvent authChangeEvent = new AuthChangeEvent();
        authChangeEvent.setSuiteKey(suiteKey);
        authChangeEvent.setSuiteToken(suiteToken);
        authChangeEvent.setCorpId(corpId);
        authChangeEvent.setPermanentCode(permanentCode);
        //corpAuthSuiteEventBus.post(authChangeEvent);
		return true;
	}
	 /**
     * 处理解除授权事件
     *
     * @param suiteKey
     * @param corpId
     * @return
     */
	public boolean handleRelieveAuth(String suiteKey, String receiveCorpId) {
		// TODO Auto-generated method stub
		logger.info("suiteKey:{},corpId:{}",suiteKey,receiveCorpId);
		try{
		 //1.删除掉企业对套件的授权信息
		this.deleteCorpSuiteAuth(receiveCorpId, suiteKey);
		  //2.删除掉企业使用的微应用
		Map params=new HashMap<>();
		params.put("suiteKey", suiteKey);
        List<IsvApp> isvapplist = isvappservice.findAllByParams(params);
        for (IsvApp isvapp : isvapplist) {
        	IsvCorpAppService.deleteCorpApp(receiveCorpId, isvapp.getAppId());
        }
      //4.删除企业token.这个必须删除,一旦出现解除授权立即授权的情况,之前的token是不可用的
        IsvCorpTokenService.deleteCorpToken(suiteKey, receiveCorpId);
		    return true;
		}catch(Exception e){
			return false;
		}
	}
    /**
     * //1.删除掉企业对套件的授权信息
     *
     * @param receiveCorpId
     * @param suiteKey
     */
	private void deleteCorpSuiteAuth(String receiveCorpId, String suiteKey) {
		// TODO Auto-generated method stub
		Map map=new HashMap();
        map.put("suiteKey",suiteKey);
        map.put("corpId", receiveCorpId);
        IsvCorpSuiteAuth isvcorpsuiteauth=this.findOneByParams(map);

        //获取套件access_token
		isvCorpSuiteAuthJpaDao.delete(isvcorpsuiteauth);
	}

}

