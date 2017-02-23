package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.FpgzJpaDao;
import com.rjxx.taxeasy.dao.FpgzMapper;
import com.rjxx.taxeasy.domains.Fpgz;
import com.rjxx.taxeasy.vo.FpgzVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri Feb 17 11:59:38 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class FpgzService {

    @Autowired
    private FpgzJpaDao fpgzJpaDao;

    @Autowired
    private FpgzMapper fpgzMapper;

    public Fpgz findOne(int id) {
        return fpgzJpaDao.findOne(id);
    }

    public void save(Fpgz fpgz) {
        fpgzJpaDao.save(fpgz);
    }

    public void save(List<Fpgz> fpgzList) {
        fpgzJpaDao.save(fpgzList);
    }

    public Fpgz findOneByParams(Map params) {
        return fpgzMapper.findOneByParams(params);
    }

    public List<Fpgz> findAllByParams(Map params) {
        return fpgzMapper.findAllByParams(params);
    }

    public List<FpgzVo> findByPage(Pagination pagination) {
        return fpgzMapper.findByPage(pagination);
    }

}

