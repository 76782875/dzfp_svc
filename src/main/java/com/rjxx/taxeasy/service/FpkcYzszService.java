package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.FpkcYzszJpaDao;
import com.rjxx.taxeasy.dao.FpkcYzszMapper;
import com.rjxx.taxeasy.domains.FpkcYzsz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Sat Mar 18 17:35:59 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class FpkcYzszService {

    @Autowired
    private FpkcYzszJpaDao fpkcYzszJpaDao;

    @Autowired
    private FpkcYzszMapper fpkcYzszMapper;

    public FpkcYzsz findOne(int id) {
        return fpkcYzszJpaDao.findOne(id);
    }

    public void save(FpkcYzsz fpkcYzsz) {
        fpkcYzszJpaDao.save(fpkcYzsz);
    }

    public void save(List<FpkcYzsz> fpkcYzszList) {
        fpkcYzszJpaDao.save(fpkcYzszList);
    }

    public FpkcYzsz findOneByParams(Map params) {
        return fpkcYzszMapper.findOneByParams(params);
    }

    public List<FpkcYzsz> findAllByParams(Map params) {
        return fpkcYzszMapper.findAllByParams(params);
    }

    public List<FpkcYzsz> findByPage(Pagination pagination) {
        return fpkcYzszMapper.findByPage(pagination);
    }
    
    public List<FpkcYzsz> findYhyzsz(Map params){
    	return fpkcYzszMapper.findYhYzsz(params);
    }

}

