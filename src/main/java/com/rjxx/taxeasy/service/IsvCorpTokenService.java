package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.IsvCorpTokenJpaDao;
import com.rjxx.taxeasy.dao.IsvCorpTokenMapper;
import com.rjxx.taxeasy.domains.IsvCorpToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 13 17:42:46 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class IsvCorpTokenService {

    @Autowired
    private IsvCorpTokenJpaDao isvCorpTokenJpaDao;

    @Autowired
    private IsvCorpTokenMapper isvCorpTokenMapper;

    public IsvCorpToken findOne(int id) {
        return isvCorpTokenJpaDao.findOne(id);
    }

    public void save(IsvCorpToken isvCorpToken) {
        isvCorpTokenJpaDao.save(isvCorpToken);
    }

    public void save(List<IsvCorpToken> isvCorpTokenList) {
        isvCorpTokenJpaDao.save(isvCorpTokenList);
    }

    public IsvCorpToken findOneByParams(Map params) {
        return isvCorpTokenMapper.findOneByParams(params);
    }

    public List<IsvCorpToken> findAllByParams(Map params) {
        return isvCorpTokenMapper.findAllByParams(params);
    }

    public List<IsvCorpToken> findByPage(Pagination pagination) {
        return isvCorpTokenMapper.findByPage(pagination);
    }

	public void deleteCorpToken(String suiteKey, String receiveCorpId) {
		// TODO Auto-generated method stub
		Map map=new HashMap();
		map.put("suiteKey", suiteKey);
		map.put("corpId", receiveCorpId);
		isvCorpTokenMapper.deleteCorpToken(map);
	}

}

