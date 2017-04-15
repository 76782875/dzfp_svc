package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.IsvSuiteTokenJpaDao;
import com.rjxx.taxeasy.dao.IsvSuiteTokenMapper;
import com.rjxx.taxeasy.domains.IsvSuiteToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 13 17:45:29 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class IsvSuiteTokenService {

    @Autowired
    private IsvSuiteTokenJpaDao isvSuiteTokenJpaDao;

    @Autowired
    private IsvSuiteTokenMapper isvSuiteTokenMapper;

    public IsvSuiteToken findOne(int id) {
        return isvSuiteTokenJpaDao.findOne(id);
    }

    public void save(IsvSuiteToken isvSuiteToken) {
        isvSuiteTokenJpaDao.save(isvSuiteToken);
    }

    public void save(List<IsvSuiteToken> isvSuiteTokenList) {
        isvSuiteTokenJpaDao.save(isvSuiteTokenList);
    }

    public IsvSuiteToken findOneByParams(Map params) {
        return isvSuiteTokenMapper.findOneByParams(params);
    }

    public List<IsvSuiteToken> findAllByParams(Map params) {
        return isvSuiteTokenMapper.findAllByParams(params);
    }

    public List<IsvSuiteToken> findByPage(Pagination pagination) {
        return isvSuiteTokenMapper.findByPage(pagination);
    }

}

