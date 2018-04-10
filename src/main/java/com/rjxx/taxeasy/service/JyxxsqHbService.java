package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.JyxxsqHbJpaDao;
import com.rjxx.taxeasy.dao.JyxxsqHbMapper;
import com.rjxx.taxeasy.domains.JyxxsqHb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Apr 10 17:02:19 CST 2018
 *
 * @ZhangBing
 */ 
@Service
public class JyxxsqHbService {

    @Autowired
    private JyxxsqHbJpaDao jyxxsqHbJpaDao;

    @Autowired
    private JyxxsqHbMapper jyxxsqHbMapper;

    public JyxxsqHb findOne(int id) {
        return jyxxsqHbJpaDao.findOne(id);
    }

    public void save(JyxxsqHb jyxxsqHb) {
        jyxxsqHbJpaDao.save(jyxxsqHb);
    }

    public void save(List<JyxxsqHb> jyxxsqHbList) {
        jyxxsqHbJpaDao.save(jyxxsqHbList);
    }

    public JyxxsqHb findOneByParams(Map params) {
        return jyxxsqHbMapper.findOneByParams(params);
    }

    public List<JyxxsqHb> findAllByParams(Map params) {
        return jyxxsqHbMapper.findAllByParams(params);
    }

    public List<JyxxsqHb> findByPage(Pagination pagination) {
        return jyxxsqHbMapper.findByPage(pagination);
    }

}

