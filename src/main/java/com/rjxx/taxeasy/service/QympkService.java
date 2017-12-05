package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.QympkMapper;
import com.rjxx.taxeasy.domains.Qympk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Mar 30 17:24:00 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class QympkService {

   /* @Autowired
    private QympkJpaDao qympkJpaDao;*/

    @Autowired
    private QympkMapper qympkMapper;

    /*public Qympk findOne(int id) {
        return qympkJpaDao.findOne(id);
    }

    public void save(Qympk qympk) {
        qympkJpaDao.save(qympk);
    }

    public void save(List<Qympk> qympkList) {
        qympkJpaDao.save(qympkList);
    }*/

    public Qympk findOneByParams(Map params) {
        return qympkMapper.findOneByParams(params);
    }

    public List<Qympk> findAllByParams(Map params) {
        return qympkMapper.findAllByParams(params);
    }

    public List<Qympk> findAll(Map params) {
        return qympkMapper.findAll(params);
    }

    public List<Qympk> findByPage(Pagination pagination) {
        return qympkMapper.findByPage(pagination);
    }

    public void saveQympk(Qympk qympk){
        qympkMapper.saveQympk(qympk);
    }
}

