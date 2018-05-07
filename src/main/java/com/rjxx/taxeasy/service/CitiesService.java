package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.CitiesJpaDao;
import com.rjxx.taxeasy.dao.CitiesMapper;
import com.rjxx.taxeasy.domains.Cities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri May 04 17:05:04 CST 2018
 *
 * @ZhangBing
 */ 
@Service
public class CitiesService {

    @Autowired
    private CitiesJpaDao citiesJpaDao;

    @Autowired
    private CitiesMapper citiesMapper;

    public Cities findOne(int id) {
        return citiesJpaDao.findOne(id);
    }

    public void save(Cities cities) {
        citiesJpaDao.save(cities);
    }

    public void save(List<Cities> citiesList) {
        citiesJpaDao.save(citiesList);
    }

    public Cities findOneByParams(Map params) {
        return citiesMapper.findOneByParams(params);
    }

    public List<Cities> findAllByParams(Map params) {
        return citiesMapper.findAllByParams(params);
    }

    public List<Cities> findByPage(Pagination pagination) {
        return citiesMapper.findByPage(pagination);
    }

}

