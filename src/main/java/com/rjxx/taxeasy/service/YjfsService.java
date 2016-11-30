package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.YjfsJpaDao;
import com.rjxx.taxeasy.dao.YjfsMapper;
import com.rjxx.taxeasy.domains.Yjfs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Nov 30 10:38:10 CST 2016
 *
 * @ZhangBing
 */ 
@Service
public class YjfsService {

    @Autowired
    private YjfsJpaDao yjfsJpaDao;

    @Autowired
    private YjfsMapper yjfsMapper;

    public Yjfs findOne(int id) {
        return yjfsJpaDao.findOne(id);
    }

    public void save(Yjfs yjfs) {
        yjfsJpaDao.save(yjfs);
    }

    public void save(List<Yjfs> yjfsList) {
        yjfsJpaDao.save(yjfsList);
    }

    public Yjfs findOneByParams(Map params) {
        return yjfsMapper.findOneByParams(params);
    }

    public List<Yjfs> findAllByParams(Map params) {
        return yjfsMapper.findAllByParams(params);
    }

    public List<Yjfs> findByPage(Pagination pagination) {
        return yjfsMapper.findByPage(pagination);
    }

}

