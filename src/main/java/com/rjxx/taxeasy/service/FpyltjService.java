package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.FpyltjJpaDao;
import com.rjxx.taxeasy.dao.FpyltjMapper;
import com.rjxx.taxeasy.domains.Fpyltj;
import com.rjxx.taxeasy.vo.Cxtjvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu May 03 16:46:51 CST 2018
 *
 * @ZhangBing
 */ 
@Service
public class FpyltjService {

    @Autowired
    private FpyltjJpaDao fpyltjJpaDao;

    @Autowired
    private FpyltjMapper fpyltjMapper;

    public Fpyltj findOne(int id) {
        return fpyltjJpaDao.findOne(id);
    }

    public void save(Fpyltj fpyltj) {
        fpyltjJpaDao.save(fpyltj);
    }

    public void save(List<Fpyltj> fpyltjList) {
        fpyltjJpaDao.save(fpyltjList);
    }

    public Fpyltj findOneByParams(Map params) {
        return fpyltjMapper.findOneByParams(params);
    }

    public List<Fpyltj> findAllByParams(Map params) {
        return fpyltjMapper.findAllByParams(params);
    }

    public List<Fpyltj> findByPage(Pagination pagination) {
        return fpyltjMapper.findByPage(pagination);
    }

    public List<Cxtjvo> findYplByParams(Map params){return fpyltjMapper.findYplByParams(params);}
}

