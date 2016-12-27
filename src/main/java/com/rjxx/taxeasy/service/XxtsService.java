package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.XxtsJpaDao;
import com.rjxx.taxeasy.dao.XxtsMapper;
import com.rjxx.taxeasy.domains.Xxts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Dec 27 09:28:24 CST 2016
 *
 * @ZhangBing
 */ 
@Service
public class XxtsService {

    @Autowired
    private XxtsJpaDao xxtsJpaDao;

    @Autowired
    private XxtsMapper xxtsMapper;

    public Xxts findOne(int id) {
        return xxtsJpaDao.findOne(id);
    }

    public void save(Xxts xxts) {
        xxtsJpaDao.save(xxts);
    }

    public void save(List<Xxts> xxtsList) {
        xxtsJpaDao.save(xxtsList);
    }

    public Xxts findOneByParams(Map params) {
        return xxtsMapper.findOneByParams(params);
    }

    public List<Xxts> findAllByParams(Map params) {
        return xxtsMapper.findAllByParams(params);
    }

    public List<Xxts> findByPage(Pagination pagination) {
        return xxtsMapper.findByPage(pagination);
    }

}

