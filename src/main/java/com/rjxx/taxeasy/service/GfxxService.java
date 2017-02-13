package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.GfxxJpaDao;
import com.rjxx.taxeasy.dao.GfxxMapper;
import com.rjxx.taxeasy.domains.Gfxx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Feb 09 14:46:55 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class GfxxService {

    @Autowired
    private GfxxJpaDao gfxxJpaDao;

    @Autowired
    private GfxxMapper gfxxMapper;

    public Gfxx findOne(int id) {
        return gfxxJpaDao.findOne(id);
    }

    public void save(Gfxx gfxx) {
        gfxxJpaDao.save(gfxx);
    }

    public void save(List<Gfxx> gfxxList) {
        gfxxJpaDao.save(gfxxList);
    }

    public Gfxx findOneByParams(Map params) {
        return gfxxMapper.findOneByParams(params);
    }

    public List<Gfxx> findAllByParams(Map params) {
        return gfxxMapper.findAllByParams(params);
    }

    public List<Gfxx> findByPage(Pagination pagination) {
        return gfxxMapper.findByPage(pagination);
    }

}

