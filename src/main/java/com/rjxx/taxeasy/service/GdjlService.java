package com.rjxx.taxeasy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.GdjlJpaDao;
import com.rjxx.taxeasy.dao.GdjlMapper;
import com.rjxx.taxeasy.domains.Gdjl;
import com.rjxx.taxeasy.vo.Gdjlvo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Nov 30 14:07:07 CST 2016
 *
 * @WangYong
 */ 
@Service
public class GdjlService {

    @Autowired
    private GdjlJpaDao gdjlJpaDao;

    @Autowired
    private GdjlMapper gdjlMapper;

    public Gdjl findOne(int id) {
        return gdjlJpaDao.findOne(id);
    }

    public void save(Gdjl gdjl) {
        gdjlJpaDao.save(gdjl);
    }

    public void save(List<Gdjl> gdjlList) {
        gdjlJpaDao.save(gdjlList);
    }

    public Gdjl findOneByParams(Map params) {
        return gdjlMapper.findOneByParams(params);
    }

    public List<Gdjl> findAllByParams(Map params) {
        return gdjlMapper.findAllByParams(params);
    }

    public List<Gdjlvo> findByPage(Pagination pagination) {
        return gdjlMapper.findByPage(pagination);
    }
    
    public void updategdzt(Map params){
    	gdjlMapper.updategdzt(params);
    }

}

