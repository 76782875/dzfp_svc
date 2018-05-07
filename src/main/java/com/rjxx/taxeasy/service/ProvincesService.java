package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.ProvincesJpaDao;
import com.rjxx.taxeasy.dao.ProvincesMapper;
import com.rjxx.taxeasy.domains.Provinces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri May 04 16:35:21 CST 2018
 *
 * @ZhangBing
 */ 
@Service
public class ProvincesService {

    @Autowired
    private ProvincesJpaDao provincesJpaDao;

    @Autowired
    private ProvincesMapper provincesMapper;

    public Provinces findOne(int id) {
        return provincesJpaDao.findOne(id);
    }

    public void save(Provinces provinces) {
        provincesJpaDao.save(provinces);
    }

    public void save(List<Provinces> provincesList) {
        provincesJpaDao.save(provincesList);
    }

    public Provinces findOneByParams(Map params) {
        return provincesMapper.findOneByParams(params);
    }

    public List<Provinces> findAllByParams(Map params) {
        return provincesMapper.findAllByParams(params);
    }

    public List<Provinces> findByPage(Pagination pagination) {
        return provincesMapper.findByPage(pagination);
    }

}

