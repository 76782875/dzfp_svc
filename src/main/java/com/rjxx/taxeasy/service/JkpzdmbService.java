package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.JkpzdmbJpaDao;
import com.rjxx.taxeasy.dao.JkpzdmbMapper;
import com.rjxx.taxeasy.domains.Jkpzdmb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Mar 20 16:08:27 CST 2018
 *
 * @ZhangBing
 */ 
@Service
public class JkpzdmbService {

    @Autowired
    private JkpzdmbJpaDao jkpzdmbJpaDao;

    @Autowired
    private JkpzdmbMapper jkpzdmbMapper;

    public Jkpzdmb findOne(int id) {
        return jkpzdmbJpaDao.findOne(id);
    }

    public void save(Jkpzdmb jkpzdmb) {
        jkpzdmbJpaDao.save(jkpzdmb);
    }

    public void save(List<Jkpzdmb> jkpzdmbList) {
        jkpzdmbJpaDao.save(jkpzdmbList);
    }

    public Jkpzdmb findOneByParams(Map params) {
        return jkpzdmbMapper.findOneByParams(params);
    }

    public List<Jkpzdmb> findAllByParams(Map params) {
        return jkpzdmbMapper.findAllByParams(params);
    }

    public List<Jkpzdmb> findByPage(Pagination pagination) {
        return jkpzdmbMapper.findByPage(pagination);
    }

}

