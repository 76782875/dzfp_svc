package com.rjxx.taxeasy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.FpzlJpaDao;
import com.rjxx.taxeasy.dao.FpzlMapper;
import com.rjxx.taxeasy.domains.Fpzl;
import com.rjxx.taxeasy.domains.Jyxxsq;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.domains.Kpspmx;
import com.rjxx.taxeasy.vo.Fpnum;
import com.rjxx.taxeasy.vo.Slcxvo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Jan 17 11:22:01 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class FpzlService {

    @Autowired
    private FpzlJpaDao fpzlJpaDao;

    @Autowired
    private FpzlMapper fpzlMapper;

    public Fpzl findOne(int id) {
        return fpzlJpaDao.findOne(id);
    }

    public void save(Fpzl fpzl) {
        fpzlJpaDao.save(fpzl);
    }

    public void save(List<Fpzl> fpzlList) {
        fpzlJpaDao.save(fpzlList);
    }

    public Fpzl findOneByParams(Map params) {
        return fpzlMapper.findOneByParams(params);
    }

    public List<Fpzl> findAllByParams(Map params) {
        return fpzlMapper.findAllByParams(params);
    }

    public List<Fpzl> findByPage(Pagination pagination) {
        return fpzlMapper.findByPage(pagination);
    }
    
    public List<Fpnum> findGfpsl(Map params){
    	return fpzlMapper.findGfpsl(params);
    }
    
    public List<Kpspmx> findSpsl(Map params){
    	return fpzlMapper.findSpsl(params);
    }
    
    public List<Slcxvo> findSpje(Map params){
    	return fpzlMapper.findSpje(params);
    }
    
    public List<Jyxxsq> findDbsx(Map params){
    	return fpzlMapper.findDbsx(params);
    }
    
    public Kpls findDkpsj(Map params){
    	return fpzlMapper.findDkpsj(params);
    }

}

