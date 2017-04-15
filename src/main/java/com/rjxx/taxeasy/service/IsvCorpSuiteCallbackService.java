package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.IsvCorpSuiteCallbackJpaDao;
import com.rjxx.taxeasy.dao.IsvCorpSuiteCallbackMapper;
import com.rjxx.taxeasy.domains.IsvCorpSuiteCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 13 17:40:49 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class IsvCorpSuiteCallbackService {

    @Autowired
    private IsvCorpSuiteCallbackJpaDao isvCorpSuiteCallbackJpaDao;

    @Autowired
    private IsvCorpSuiteCallbackMapper isvCorpSuiteCallbackMapper;

    public IsvCorpSuiteCallback findOne(int id) {
        return isvCorpSuiteCallbackJpaDao.findOne(id);
    }

    public void save(IsvCorpSuiteCallback isvCorpSuiteCallback) {
        isvCorpSuiteCallbackJpaDao.save(isvCorpSuiteCallback);
    }

    public void save(List<IsvCorpSuiteCallback> isvCorpSuiteCallbackList) {
        isvCorpSuiteCallbackJpaDao.save(isvCorpSuiteCallbackList);
    }

    public IsvCorpSuiteCallback findOneByParams(Map params) {
        return isvCorpSuiteCallbackMapper.findOneByParams(params);
    }

    public List<IsvCorpSuiteCallback> findAllByParams(Map params) {
        return isvCorpSuiteCallbackMapper.findAllByParams(params);
    }

    public List<IsvCorpSuiteCallback> findByPage(Pagination pagination) {
        return isvCorpSuiteCallbackMapper.findByPage(pagination);
    }

}

