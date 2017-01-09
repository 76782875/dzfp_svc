package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.JyxxsqJpaDao;
import com.rjxx.taxeasy.dao.JyxxsqMapper;
import com.rjxx.taxeasy.domains.Jyxxsq;
import com.rjxx.taxeasy.domains.Xf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Jan 04 13:33:08 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class JyxxsqService {

    @Autowired
    private JyxxsqJpaDao jyxxsqJpaDao;

    @Autowired
    private JyxxsqMapper jyxxsqMapper;

    public Jyxxsq findOne(int id) {
        return jyxxsqJpaDao.findOne(id);
    }

    public void save(Jyxxsq jyxxsq) {
        jyxxsqJpaDao.save(jyxxsq);
    }

    public void save(List<Jyxxsq> jyxxsqList) {
        jyxxsqJpaDao.save(jyxxsqList);
    }

    public Jyxxsq findOneByParams(Map params) {
        return jyxxsqMapper.findOneByParams(params);
    }

    public List<Jyxxsq> findAllByTqms(Map params) {
        return jyxxsqMapper.findAllByTqms(params);
    }
    
    public List<Jyxxsq> findAllByJylshs(Map params) {
        return jyxxsqMapper.findAllByJylshs(params);
    }

    public List<Jyxxsq> findAllByDdhs(Map params) {
        return jyxxsqMapper.findAllByDdhs(params);
    } 
    
    public List<Jyxxsq> findByPage(Pagination pagination) {
        return jyxxsqMapper.findByPage(pagination);
    }
    
    public Xf findXfExistByKpd(Map params) {
        return jyxxsqMapper.findXfExistByKpd(params);
    }


}

