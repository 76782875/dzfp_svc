package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.KppmxxJpaDao;
import com.rjxx.taxeasy.dao.KppmxxMapper;
import com.rjxx.taxeasy.domains.Kppmxx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Nov 30 10:13:46 CST 2016
 *
 * @ZhangBing
 */ 
@Service
public class KppmxxService {

    @Autowired
    private KppmxxJpaDao kppmxxJpaDao;

    @Autowired
    private KppmxxMapper kppmxxMapper;

    public Kppmxx findOne(int id) {
        return kppmxxJpaDao.findOne(id);
    }

    public void save(Kppmxx kppmxx) {
        kppmxxJpaDao.save(kppmxx);
    }

    public void save(List<Kppmxx> kppmxxList) {
        kppmxxJpaDao.save(kppmxxList);
    }

    public Kppmxx findOneByParams(Map params) {
        return kppmxxMapper.findOneByParams(params);
    }

    public List<Kppmxx> findAllByParams(Map params) {
        return kppmxxMapper.findAllByParams(params);
    }

    public List<Kppmxx> findByPage(Pagination pagination) {
        return kppmxxMapper.findByPage(pagination);
    }

}

