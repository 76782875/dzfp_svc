package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.IsvSuiteJpaDao;
import com.rjxx.taxeasy.dao.IsvSuiteMapper;
import com.rjxx.taxeasy.domains.IsvSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 13 17:43:39 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class IsvSuiteService {

    @Autowired
    private IsvSuiteJpaDao isvSuiteJpaDao;

    @Autowired
    private IsvSuiteMapper isvSuiteMapper;

    public IsvSuite findOne(int id) {
        return isvSuiteJpaDao.findOne(id);
    }

    public void save(IsvSuite isvSuite) {
        isvSuiteJpaDao.save(isvSuite);
    }

    public void save(List<IsvSuite> isvSuiteList) {
        isvSuiteJpaDao.save(isvSuiteList);
    }

    public IsvSuite findOneByParams(Map params) {
        return isvSuiteMapper.findOneByParams(params);
    }

    public List<IsvSuite> findAllByParams(Map params) {
        return isvSuiteMapper.findAllByParams(params);
    }

    public List<IsvSuite> findByPage(Pagination pagination) {
        return isvSuiteMapper.findByPage(pagination);
    }

    public IsvSuite getIsvSuite(Map map) {
        return isvSuiteMapper.getIsvSuite(map);
    }
}

