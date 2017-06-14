package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.JymxsqClJpaDao;
import com.rjxx.taxeasy.dao.JymxsqClMapper;
import com.rjxx.taxeasy.domains.JymxsqCl;
import com.rjxx.taxeasy.vo.JyspmxDecimal2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed May 31 17:18:47 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class JymxsqClService {

    @Autowired
    private JymxsqClJpaDao jymxsqClJpaDao;

    @Autowired
    private JymxsqClMapper jymxsqClMapper;

    public JymxsqCl findOne(int id) {
        return jymxsqClJpaDao.findOne(id);
    }

    public void save(JymxsqCl jymxsqCl) {
        jymxsqClJpaDao.save(jymxsqCl);
    }

    public void save(List<JymxsqCl> jymxsqClList) {
        jymxsqClJpaDao.save(jymxsqClList);
    }

    public JymxsqCl findOneByParams(Map params) {
        return jymxsqClMapper.findOneByParams(params);
    }

    public List<JyspmxDecimal2> findAllByParams(Map params) {
        return jymxsqClMapper.findAllByParams(params);
    }

    public List<JymxsqCl> findByPage(Pagination pagination) {
        return jymxsqClMapper.findByPage(pagination);
    }

}

