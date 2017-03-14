package com.rjxx.taxeasy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.DyfsJpaDao;
import com.rjxx.taxeasy.dao.DyfsMapper;
import com.rjxx.taxeasy.domains.Dyfs;
import com.rjxx.taxeasy.vo.Dyvo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Jan 18 09:14:29 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class DyfsService {

    @Autowired
    private DyfsJpaDao dyfsJpaDao;

    @Autowired
    private DyfsMapper dyfsMapper;

    public Dyfs findOne(int id) {
        return dyfsJpaDao.findOne(id);
    }

    public void save(Dyfs dyfs) {
        dyfsJpaDao.save(dyfs);
    }

    public void save(List<Dyfs> dyfsList) {
        dyfsJpaDao.save(dyfsList);
    }

    public Dyfs findOneByParams(Map params) {
        return dyfsMapper.findOneByParams(params);
    }

    public List<Dyfs> findAllByParams(Map params) {
        return dyfsMapper.findAllByParams(params);
    }

    public List<Dyfs> findByPage(Pagination pagination) {
        return dyfsMapper.findByPage(pagination);
    }
    
    public List<Dyvo> findDyzl(Map params){
    	return dyfsMapper.findDyzl(params);
    }
    
    public List<Dyvo> findDyfs(Map params){
    	return dyfsMapper.findDyfs(params);
    }

}

