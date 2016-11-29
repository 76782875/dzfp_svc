package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.FpztJpaDao;
import com.rjxx.taxeasy.dao.FpztMapper;
import com.rjxx.taxeasy.domains.Fpzt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 31 17:42:21 CST 2016
 *
 * @ZhangBing
 */ 
@Service
public class FpztService {

    @Autowired
    private FpztJpaDao fpztJpaDao;

    @Autowired
    private FpztMapper fpztMapper;

    public Fpzt findOne(int id) {
        return fpztJpaDao.findOne(id);
    }

    public void save(Fpzt fpzt) {
        fpztJpaDao.save(fpzt);
    }

    public void save(List<Fpzt> fpztList) {
        fpztJpaDao.save(fpztList);
    }

    public Fpzt findOneByParams(Map params) {
        return fpztMapper.findOneByParams(params);
    }

    public List<Fpzt> findAllByParams(Map params) {
        return fpztMapper.findAllByParams(params);
    }

    public List<Fpzt> findByPage(Pagination pagination) {
        return fpztMapper.findByPage(pagination);
    }

}

