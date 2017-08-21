package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.vSpbmJpaDao;
import com.rjxx.taxeasy.dao.vSpbmMapper;
import com.rjxx.taxeasy.domains.vSpbm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Aug 21 18:41:15 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class vSpbmService {

    @Autowired
    private vSpbmJpaDao vSpbmJpaDao;

    @Autowired
    private vSpbmMapper vSpbmMapper;

    public vSpbm findOne(int id) {
        return vSpbmJpaDao.findOne(id);
    }

    public void save(vSpbm vSpbm) {
        vSpbmJpaDao.save(vSpbm);
    }

    public void save(List<vSpbm> vSpbmList) {
        vSpbmJpaDao.save(vSpbmList);
    }

    public vSpbm findOneByParams(Map params) {
        return vSpbmMapper.findOneByParams(params);
    }

    public List<vSpbm> findAllByParams(Map params) {
        return vSpbmMapper.findAllByParams(params);
    }

    public List<vSpbm> findByPage(Pagination pagination) {
        return vSpbmMapper.findByPage(pagination);
    }

}

