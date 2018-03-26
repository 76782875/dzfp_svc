package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.JkpzbJpaDao;
import com.rjxx.taxeasy.dao.JkpzbMapper;
import com.rjxx.taxeasy.domains.Jkpzb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Mar 20 16:06:49 CST 2018
 *
 * @ZhangBing
 */ 
@Service
public class JkpzbService {

    @Autowired
    private JkpzbJpaDao jkpzbJpaDao;

    @Autowired
    private JkpzbMapper jkpzbMapper;

    public Jkpzb findOne(int id) {
        return jkpzbJpaDao.findOne(id);
    }

    public void save(Jkpzb jkpzb) {
        jkpzbJpaDao.save(jkpzb);
    }

    public void save(List<Jkpzb> jkpzbList) {
        jkpzbJpaDao.save(jkpzbList);
    }

    public Jkpzb findOneByParams(Map params) {
        return jkpzbMapper.findOneByParams(params);
    }

    public List<Jkpzb> findAllByParams(Map params) {
        return jkpzbMapper.findAllByParams(params);
    }

    public List<Jkpzb> findByPage(Pagination pagination) {
        return jkpzbMapper.findByPage(pagination);
    }

}

