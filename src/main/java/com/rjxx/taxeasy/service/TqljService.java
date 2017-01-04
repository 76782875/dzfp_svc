package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.TqljJpaDao;
import com.rjxx.taxeasy.dao.TqljMapper;
import com.rjxx.taxeasy.domains.Tqlj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Jan 04 10:01:04 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class TqljService {

    @Autowired
    private TqljJpaDao tqljJpaDao;

    @Autowired
    private TqljMapper tqljMapper;

    public Tqlj findOne(int id) {
        return tqljJpaDao.findOne(id);
    }

    public void save(Tqlj tqlj) {
        tqljJpaDao.save(tqlj);
    }

    public void save(List<Tqlj> tqljList) {
        tqljJpaDao.save(tqljList);
    }

    public Tqlj findOneByParams(Map params) {
        return tqljMapper.findOneByParams(params);
    }

    public List<Tqlj> findAllByParams(Map params) {
        return tqljMapper.findAllByParams(params);
    }

    public List<Tqlj> findByPage(Pagination pagination) {
        return tqljMapper.findByPage(pagination);
    }

}

