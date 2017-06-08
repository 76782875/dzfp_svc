package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.JyzfmxJpaDao;
import com.rjxx.taxeasy.dao.JyzfmxMapper;
import com.rjxx.taxeasy.domains.Jyzfmx;
import com.rjxx.taxeasy.vo.Jyzfmxvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed May 31 15:26:25 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class JyzfmxService {

    @Autowired
    private JyzfmxJpaDao jyzfmxJpaDao;

    @Autowired
    private JyzfmxMapper jyzfmxMapper;

    public Jyzfmx findOne(int id) {
        return jyzfmxJpaDao.findOne(id);
    }

    public void save(Jyzfmx jyzfmx) {
        jyzfmxJpaDao.save(jyzfmx);
    }

    public void save(List<Jyzfmx> jyzfmxList) {
        jyzfmxJpaDao.save(jyzfmxList);
    }

    public Jyzfmx findOneByParams(Map params) {
        return jyzfmxMapper.findOneByParams(params);
    }

    public List<Jyzfmx> findAllByParams(Map params) {
        return jyzfmxMapper.findAllByParams(params);
    }

    public List<Jyzfmx> findByPage(Pagination pagination) {
        return jyzfmxMapper.findByPage(pagination);
    }

    public List<Jyzfmxvo> findAllByParamsVo(Map parms) {
        return jyzfmxMapper.findAllByParamsVo(parms);
    }
}

