package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.IsvCorpSuiteAuthFaileJpaDao;
import com.rjxx.taxeasy.dao.IsvCorpSuiteAuthFaileMapper;
import com.rjxx.taxeasy.domains.IsvCorpSuiteAuthFaile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 13 17:37:43 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class IsvCorpSuiteAuthFaileService {

    @Autowired
    private IsvCorpSuiteAuthFaileJpaDao isvCorpSuiteAuthFaileJpaDao;

    @Autowired
    private IsvCorpSuiteAuthFaileMapper isvCorpSuiteAuthFaileMapper;

    public IsvCorpSuiteAuthFaile findOne(int id) {
        return isvCorpSuiteAuthFaileJpaDao.findOne(id);
    }

    public void save(IsvCorpSuiteAuthFaile isvCorpSuiteAuthFaile) {
        isvCorpSuiteAuthFaileJpaDao.save(isvCorpSuiteAuthFaile);
    }

    public void save(List<IsvCorpSuiteAuthFaile> isvCorpSuiteAuthFaileList) {
        isvCorpSuiteAuthFaileJpaDao.save(isvCorpSuiteAuthFaileList);
    }

    public IsvCorpSuiteAuthFaile findOneByParams(Map params) {
        return isvCorpSuiteAuthFaileMapper.findOneByParams(params);
    }

    public List<IsvCorpSuiteAuthFaile> findAllByParams(Map params) {
        return isvCorpSuiteAuthFaileMapper.findAllByParams(params);
    }

    public List<IsvCorpSuiteAuthFaile> findByPage(Pagination pagination) {
        return isvCorpSuiteAuthFaileMapper.findByPage(pagination);
    }

}

