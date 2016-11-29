package com.rjxx.taxeasy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.FpyjdyJpaDao;
import com.rjxx.taxeasy.dao.FpyjdyMapper;
import com.rjxx.taxeasy.domains.Fpyjdy;
import com.rjxx.taxeasy.vo.Fpyjdyvo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 24 19:06:11 CST 2016
 *
 * @ZhangBing
 */ 
@Service
public class FpyjdyService {

    @Autowired
    private FpyjdyJpaDao fpyjdyJpaDao;

    @Autowired
    private FpyjdyMapper fpyjdyMapper;

    public Fpyjdy findOne(int id) {
        return fpyjdyJpaDao.findOne(id);
    }

    public void save(Fpyjdy fpyjdy) {
        fpyjdyJpaDao.save(fpyjdy);
    }

    public void save(List<Fpyjdy> fpyjdyList) {
        fpyjdyJpaDao.save(fpyjdyList);
    }

    public Fpyjdy findOneByParams(Map params) {
        return fpyjdyMapper.findOneByParams(params);
    }

    public List<Fpyjdy> findAllByParams(Map params) {
        return fpyjdyMapper.findAllByParams(params);
    }

    public List<Fpyjdy> findByPage(Pagination pagination) {
        return fpyjdyMapper.findByPage(pagination);
    }
    //查询发票订阅情况
    public List<Fpyjdyvo> findFpyjdyByPage(Pagination pagination){
    	return fpyjdyMapper.findFpyjdyByPage(pagination);
    }
    //查询订阅种类
    public Fpyjdyvo findDyxx(Map params){
    	return fpyjdyMapper.findDyxx(params);
    }
    //更新订阅信息
    public void updateDyxx(Map params){
    	fpyjdyMapper.updateDyxx(params);
    }
    
    //根据当前用户查询订阅信息
    public List<Fpyjdyvo> findYhZyDy(Pagination pagination){
    	return fpyjdyMapper.findYhZyDy(pagination);
    }
    //查询email订阅的信息
    public List<Fpyjdyvo> findEmailDy(){
    	return fpyjdyMapper.findEmailDy();
    }
}

