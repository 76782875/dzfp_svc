package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.IsvAppJpaDao;
import com.rjxx.taxeasy.dao.IsvAppMapper;
import com.rjxx.taxeasy.domains.IsvApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 13 17:30:56 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class IsvAppService {

    @Autowired
    private IsvAppJpaDao isvAppJpaDao;

    @Autowired
    private IsvAppMapper isvAppMapper;

    public IsvApp findOne(int id) {
        return isvAppJpaDao.findOne(id);
    }

    public void save(IsvApp isvApp) {
        isvAppJpaDao.save(isvApp);
    }

    public void save(List<IsvApp> isvAppList) {
        isvAppJpaDao.save(isvAppList);
    }

    public IsvApp findOneByParams(Map params) {
        return isvAppMapper.findOneByParams(params);
    }

    public List<IsvApp> findAllByParams(Map params) {
        return isvAppMapper.findAllByParams(params);
    }

    public List<IsvApp> findByPage(Pagination pagination) {
        return isvAppMapper.findByPage(pagination);
    }

}

