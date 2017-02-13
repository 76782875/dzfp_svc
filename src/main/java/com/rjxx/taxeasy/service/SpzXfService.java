package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.SpzXfJpaDao;
import com.rjxx.taxeasy.dao.SpzXfMapper;
import com.rjxx.taxeasy.domains.SpzXf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Jan 17 15:01:33 GMT+08:00 2017
 *
 * @ZhangBing
 */ 
@Service
public class SpzXfService {

    @Autowired
    private SpzXfJpaDao spzXfJpaDao;

    @Autowired
    private SpzXfMapper spzXfMapper;

    public SpzXf findOne(int id) {
        return spzXfJpaDao.findOne(id);
    }

    public void save(SpzXf spzXf) {
        spzXfJpaDao.save(spzXf);
    }

    public void save(List<SpzXf> spzXfList) {
        spzXfJpaDao.save(spzXfList);
    }

    public SpzXf findOneByParams(Map params) {
        return spzXfMapper.findOneByParams(params);
    }

    public List<SpzXf> findAllByParams(Map params) {
        return spzXfMapper.findAllByParams(params);
    }

    public List<SpzXf> findByPage(Pagination pagination) {
        return spzXfMapper.findByPage(pagination);
    }

}

