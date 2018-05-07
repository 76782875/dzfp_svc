package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.AreasJpaDao;
import com.rjxx.taxeasy.dao.AreasMapper;
import com.rjxx.taxeasy.domains.Areas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri May 04 17:37:54 CST 2018
 *
 * @ZhangBing
 */ 
@Service
public class AreasService {

    @Autowired
    private AreasJpaDao areasJpaDao;

    @Autowired
    private AreasMapper areasMapper;

    public Areas findOne(int id) {
        return areasJpaDao.findOne(id);
    }

    public void save(Areas areas) {
        areasJpaDao.save(areas);
    }

    public void save(List<Areas> areasList) {
        areasJpaDao.save(areasList);
    }

    public Areas findOneByParams(Map params) {
        return areasMapper.findOneByParams(params);
    }

    public List<Areas> findAllByParams(Map params) {
        return areasMapper.findAllByParams(params);
    }

    public List<Areas> findByPage(Pagination pagination) {
        return areasMapper.findByPage(pagination);
    }

}

