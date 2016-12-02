package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.RoleprivsJpaDao;
import com.rjxx.taxeasy.dao.RoleprivsMapper;
import com.rjxx.taxeasy.domains.Roleprivs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Dec 01 15:36:47 CST 2016
 *
 * @ZhangBing
 */ 
@Service
public class RoleprivsService {

    @Autowired
    private RoleprivsJpaDao roleprivsJpaDao;

    @Autowired
    private RoleprivsMapper roleprivsMapper;

    public Roleprivs findOne(int id) {
        return roleprivsJpaDao.findOne(id);
    }

    public void save(Roleprivs roleprivs) {
        roleprivsJpaDao.save(roleprivs);
    }

    public void save(List<Roleprivs> roleprivsList) {
        roleprivsJpaDao.save(roleprivsList);
    }

    public Roleprivs findOneByParams(Map params) {
        return roleprivsMapper.findOneByParams(params);
    }

    public List<Roleprivs> findAllByParams(Map params) {
        return roleprivsMapper.findAllByParams(params);
    }
    
    public List<Roleprivs> findBySql(Map params) {
        return roleprivsMapper.findBySql(params);
    }
    public List<Roleprivs> findBySql1(Map params) {
        return roleprivsMapper.findBySql1(params);
    }
    public List<Roleprivs> findByPage(Pagination pagination) {
        return roleprivsMapper.findByPage(pagination);
    }

}

