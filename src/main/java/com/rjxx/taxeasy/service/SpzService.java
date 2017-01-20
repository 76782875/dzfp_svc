package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.SpzJpaDao;
import com.rjxx.taxeasy.dao.SpzMapper;
import com.rjxx.taxeasy.domains.Spz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Jan 17 15:00:54 GMT+08:00 2017
 *
 * @ZhangBing
 */ 
@Service
public class SpzService {

    @Autowired
    private SpzJpaDao spzJpaDao;

    @Autowired
    private SpzMapper spzMapper;

    public Spz findOne(int id) {
        return spzJpaDao.findOne(id);
    }

    public void save(Spz spz) {
        spzJpaDao.save(spz);
    }

    public void save(List<Spz> spzList) {
        spzJpaDao.save(spzList);
    }

    public Spz findOneByParams(Map params) {
        return spzMapper.findOneByParams(params);
    }

    public List<Spz> findAllByParams(Map params) {
        return spzMapper.findAllByParams(params);
    }

    public List<Spz> findByPage(Pagination pagination) {
        return spzMapper.findByPage(pagination);
    }

}

