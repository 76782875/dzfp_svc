package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.MkpzJpaDao;
import com.rjxx.taxeasy.dao.MkpzMapper;
import com.rjxx.taxeasy.domains.Mkpz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Oct 13 11:13:54 CST 2016
 *
 * @ZhangBing
 */ 
@Service
public class MkpzService {

    @Autowired
    private MkpzJpaDao mkpzJpaDao;

    @Autowired
    private MkpzMapper mkpzMapper;

    public Mkpz findOne(int id) {
        return mkpzJpaDao.findOne(id);
    }

    public void save(Mkpz mkpz) {
        mkpzJpaDao.save(mkpz);
    }

    public void save(List<Mkpz> mkpzList) {
        mkpzJpaDao.save(mkpzList);
    }

    public Mkpz findOneByParams(Map params) {
        return mkpzMapper.findOneByParams(params);
    }
    
    public List<Mkpz> findAll() {
        return mkpzMapper.findAll();
    }
    
    public List<Mkpz> findAllByParams(Map params) {
        return mkpzMapper.findAllByParams(params);
    }

    public List<Mkpz> findByPage(Pagination pagination) {
        return mkpzMapper.findByPage(pagination);
    }

}

