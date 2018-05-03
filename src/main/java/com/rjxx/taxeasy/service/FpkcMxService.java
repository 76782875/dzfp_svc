package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.FpkcMxJpaDao;
import com.rjxx.taxeasy.dao.FpkcMxMapper;
import com.rjxx.taxeasy.domains.FpkcMx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed May 02 13:38:11 CST 2018
 *
 * @ZhangBing
 */ 
@Service
public class FpkcMxService {

    @Autowired
    private FpkcMxJpaDao fpkcMxJpaDao;

    @Autowired
    private FpkcMxMapper fpkcMxMapper;

    public FpkcMx findOne(int id) {
        return fpkcMxJpaDao.findOne(id);
    }

    public void save(FpkcMx fpkcMx) {
        fpkcMxJpaDao.save(fpkcMx);
    }

    public void save(List<FpkcMx> fpkcMxList) {
        fpkcMxJpaDao.save(fpkcMxList);
    }

    public FpkcMx findOneByParams(Map params) {
        return fpkcMxMapper.findOneByParams(params);
    }

    public List<FpkcMx> findAllByParams(Map params) {
        return fpkcMxMapper.findAllByParams(params);
    }

    public List<FpkcMx> findByPage(Pagination pagination) {
        return fpkcMxMapper.findByPage(pagination);
    }

    public void deleteMxByKcid(Map params){
        fpkcMxMapper.deleteMxByKcid(params);
    }

}

