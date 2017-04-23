package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.IsvCorpJpaDao;
import com.rjxx.taxeasy.dao.IsvCorpMapper;
import com.rjxx.taxeasy.domains.IsvCorp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 13 17:34:00 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class IsvCorpService {

    @Autowired
    private IsvCorpJpaDao isvCorpJpaDao;

    @Autowired
    private IsvCorpMapper isvCorpMapper;

    public IsvCorp findOne(int id) {
        return isvCorpJpaDao.findOne(id);
    }

    public void save(IsvCorp isvCorp) {
        isvCorpJpaDao.save(isvCorp);
    }

    public void save(List<IsvCorp> isvCorpList) {
        isvCorpJpaDao.save(isvCorpList);
    }

    public IsvCorp findOneByParams(Map params) {
        return isvCorpMapper.findOneByParams(params);
    }

    public List<IsvCorp> findAllByParams(Map params) {
        return isvCorpMapper.findAllByParams(params);
    }

    public List<IsvCorp> findByPage(Pagination pagination) {
        return isvCorpMapper.findByPage(pagination);
    }

	public void deleteCorp(String receiveCorpId) {
		// TODO Auto-generated method stub
		isvCorpMapper.deleteCorp(receiveCorpId);
	}

}

