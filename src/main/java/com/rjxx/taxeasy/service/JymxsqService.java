package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.JymxsqJpaDao;
import com.rjxx.taxeasy.dao.JymxsqMapper;
import com.rjxx.taxeasy.domains.Jymxsq;
import com.rjxx.taxeasy.domains.Jyspmx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Jan 04 13:34:29 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class JymxsqService {

    @Autowired
    private JymxsqJpaDao jymxsqJpaDao;

    @Autowired
    private JymxsqMapper jymxsqMapper;

    public Jymxsq findOne(int id) {
        return jymxsqJpaDao.findOne(id);
    }

    public void save(Jymxsq jymxsq) {
        jymxsqJpaDao.save(jymxsq);
    }

    public void save(List<Jymxsq> jymxsqList) {
        jymxsqJpaDao.save(jymxsqList);
    }

    public Jymxsq findOneByParams(Map params) {
        return jymxsqMapper.findOneByParams(params);
    }

    public List<Jymxsq> findAllByParams(Map params) {
        return jymxsqMapper.findAllByParams(params);
    }
    
    public List<Jymxsq> findAllByDdhs(Map params) {
        return jymxsqMapper.findAllByDdhs(params);
    }

    public List<Jymxsq> findByPage(Pagination pagination) {
        return jymxsqMapper.findByPage(pagination);
    }
    
    /**
     * 根据单据号查找
     *
     * @param djhList
     * @return
     */
    public List<Jymxsq> findBySqlshList(List<Integer> sqlshList) {
        return jymxsqMapper.findBySqlshList(sqlshList);
    }

    

    public void delete(List<Jymxsq> jymxsqList) {
    	jymxsqJpaDao.delete(jymxsqList);
    }

    public void addJymxsqBatch(List<Jymxsq> jymxsqs){
    	jymxsqMapper.addJymxsqBatch(jymxsqs);
    }
	
}

