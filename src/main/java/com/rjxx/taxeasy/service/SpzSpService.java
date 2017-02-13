package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.SpzSpJpaDao;
import com.rjxx.taxeasy.dao.SpzSpMapper;
import com.rjxx.taxeasy.domains.SpzSp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Jan 17 15:01:20 GMT+08:00 2017
 *
 * @ZhangBing
 */ 
@Service
public class SpzSpService {

    @Autowired
    private SpzSpJpaDao spzSpJpaDao;

    @Autowired
    private SpzSpMapper spzSpMapper;

    public SpzSp findOne(int id) {
        return spzSpJpaDao.findOne(id);
    }

    public void save(SpzSp spzSp) {
        spzSpJpaDao.save(spzSp);
    }

    public void save(List<SpzSp> spzSpList) {
        spzSpJpaDao.save(spzSpList);
    }

    public SpzSp findOneByParams(Map params) {
        return spzSpMapper.findOneByParams(params);
    }

    public List<SpzSp> findAllByParams(Map params) {
        return spzSpMapper.findAllByParams(params);
    }

    public List<SpzSp> findByPage(Pagination pagination) {
        return spzSpMapper.findByPage(pagination);
    }

}

