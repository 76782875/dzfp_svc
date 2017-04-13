package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.HbgzJpaDao;
import com.rjxx.taxeasy.dao.HbgzMapper;
import com.rjxx.taxeasy.domains.Hbgz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 13 09:19:03 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class HbgzService {

    @Autowired
    private HbgzJpaDao hbgzJpaDao;

    @Autowired
    private HbgzMapper hbgzMapper;

    public Hbgz findOne(int id) {
        return hbgzJpaDao.findOne(id);
    }

    public void save(Hbgz hbgz) {
        hbgzJpaDao.save(hbgz);
    }

    public void save(List<Hbgz> hbgzList) {
        hbgzJpaDao.save(hbgzList);
    }

    public Hbgz findOneByParams(Map params) {
        return hbgzMapper.findOneByParams(params);
    }

    public List<Hbgz> findAllByParams(Map params) {
        return hbgzMapper.findAllByParams(params);
    }

    public List<Hbgz> findByPage(Pagination pagination) {
        return hbgzMapper.findByPage(pagination);
    }

}

