package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.IsvCorpAppJpaDao;
import com.rjxx.taxeasy.dao.IsvCorpAppMapper;
import com.rjxx.taxeasy.domains.IsvCorpApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 13 17:35:28 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class IsvCorpAppService {

    @Autowired
    private IsvCorpAppJpaDao isvCorpAppJpaDao;

    @Autowired
    private IsvCorpAppMapper isvCorpAppMapper;

    public IsvCorpApp findOne(int id) {
        return isvCorpAppJpaDao.findOne(id);
    }

    public void save(IsvCorpApp isvCorpApp) {
        isvCorpAppJpaDao.save(isvCorpApp);
    }

    public void save(List<IsvCorpApp> isvCorpAppList) {
        isvCorpAppJpaDao.save(isvCorpAppList);
    }

    public IsvCorpApp findOneByParams(Map params) {
        return isvCorpAppMapper.findOneByParams(params);
    }

    public List<IsvCorpApp> findAllByParams(Map params) {
        return isvCorpAppMapper.findAllByParams(params);
    }

    public List<IsvCorpApp> findByPage(Pagination pagination) {
        return isvCorpAppMapper.findByPage(pagination);
    }

	public void deleteCorpApp(String receiveCorpId, Long appId) {
		// TODO Auto-generated method stub
		Map map=new HashMap();
		map.put("appId", appId);
		map.put("corpId", receiveCorpId);
		 isvCorpAppMapper.deleteCorpApp(map);
	}

	public void deleteCorpApp(String receiveCorpId) {
		// TODO Auto-generated method stub
		Map map=new HashMap();
		map.put("corpId", receiveCorpId);
		 isvCorpAppMapper.deleteCorpApp(map);
	}

}

