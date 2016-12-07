package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.TqmtqJpaDao;
import com.rjxx.taxeasy.dao.TqmtqMapper;
import com.rjxx.taxeasy.domains.Tqmtq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Dec 07 13:40:06 CST 2016
 *
 * @ZhangBing
 */ 
@Service
public class TqmtqService {

    @Autowired
    private TqmtqJpaDao tqmtqJpaDao;

    @Autowired
    private TqmtqMapper tqmtqMapper;

    public Tqmtq findOne(int id) {
        return tqmtqJpaDao.findOne(id);
    }

    public void save(Tqmtq tqmtq) {
        tqmtqJpaDao.save(tqmtq);
    }

    public void save(List<Tqmtq> tqmtqList) {
        tqmtqJpaDao.save(tqmtqList);
    }

    public Tqmtq findOneByParams(Map params) {
        return tqmtqMapper.findOneByParams(params);
    }

    public List<Tqmtq> findAllByParams(Map params) {
        return tqmtqMapper.findAllByParams(params);
    }

    public List<Tqmtq> findByPage(Pagination pagination) {
        return tqmtqMapper.findByPage(pagination);
    }

}

