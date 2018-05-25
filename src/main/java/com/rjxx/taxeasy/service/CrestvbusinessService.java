package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.CrestvbusinessJpaDao;
import com.rjxx.taxeasy.dao.CrestvbusinessMapper;
import com.rjxx.taxeasy.domains.Crestvbusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri May 25 16:26:12 CST 2018
 *
 * @ZhangBing
 */ 
@Service
public class CrestvbusinessService {

    @Autowired
    private CrestvbusinessJpaDao crestvbusinessJpaDao;

    @Autowired
    private CrestvbusinessMapper crestvbusinessMapper;

    public Crestvbusiness findOne(int id) {
        return crestvbusinessJpaDao.findOne(id);
    }

    public void save(Crestvbusiness crestvbusiness) {
        crestvbusinessJpaDao.save(crestvbusiness);
    }

    public void save(List<Crestvbusiness> crestvbusinessList) {
        crestvbusinessJpaDao.save(crestvbusinessList);
    }

    public Crestvbusiness findOneByParams(Map params) {
        return crestvbusinessMapper.findOneByParams(params);
    }

    public List<Crestvbusiness> findAllByParams(Map params) {
        return crestvbusinessMapper.findAllByParams(params);
    }

    public List<Crestvbusiness> findByPage(Pagination pagination) {
        return crestvbusinessMapper.findByPage(pagination);
    }

}

