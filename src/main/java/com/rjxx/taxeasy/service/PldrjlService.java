package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.PldrjlJpaDao;
import com.rjxx.taxeasy.dao.PldrjlMapper;
import com.rjxx.taxeasy.domains.Drmb;
import com.rjxx.taxeasy.domains.Jyxxsq;
import com.rjxx.taxeasy.domains.Pldrjl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Aug 03 10:28:01 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class PldrjlService {

    @Autowired
    private PldrjlJpaDao pldrjlJpaDao;

    @Autowired
    private PldrjlMapper pldrjlMapper;

    public Pldrjl findOne(int id) {
        return pldrjlJpaDao.findOne(id);
    }

    public void save(Pldrjl pldrjl) {
        pldrjlJpaDao.save(pldrjl);
    }

    public void save(List<Pldrjl> pldrjlList) {
        pldrjlJpaDao.save(pldrjlList);
    }

    public Pldrjl findOneByParams(Map params) {
        return pldrjlMapper.findOneByParams(params);
    }

    public List<Pldrjl> findAllByParams(Map params) {
        return pldrjlMapper.findAllByParams(params);
    }

    public List<Pldrjl> findByPage(Pagination pagination) {
        return pldrjlMapper.findByPage(pagination);
    }

    public List<Jyxxsq> findAllJyxxsqByParams(Map params){
    	 return pldrjlMapper.findAllJyxxsqByParams(params);
    }
    
    public void delete(Pldrjl pldrjl){
    	pldrjlJpaDao.delete(pldrjl);
    }
}

