package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.DrmbJpaDao;
import com.rjxx.taxeasy.dao.DrmbMapper;
import com.rjxx.taxeasy.domains.Drmb;
import com.rjxx.taxeasy.vo.DrmbVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Oct 19 09:03:51 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
@Service
public class DrmbService {

    @Autowired
    private DrmbJpaDao drmbJpaDao;

    @Autowired
    private DrmbMapper drmbMapper;

    public Drmb findOne(int id) {
        return drmbJpaDao.findOne(id);
    }

    public void save(Drmb drmb) {
        drmbJpaDao.save(drmb);
    }

    public void save(List<Drmb> drmbList) {
        drmbJpaDao.save(drmbList);
    }

    public Drmb findOneByParams(Drmb drmb) {
        return drmbMapper.findOneByParams(drmb);
    }
    
    public Drmb findMrByParams(Drmb drmb){
    	return drmbMapper.findMrByParams(drmb);
    }

    public List<Drmb> findAllByParams(Map params) {
        return drmbMapper.findAllByParams(params);
    }

    public List<DrmbVo> findByPage(Pagination pagination) {
        return drmbMapper.findByPage(pagination);
    }
    
    public void delete(Drmb drmb){
    	drmbJpaDao.delete(drmb);
    }
    
    public void delete(List<Drmb> drmbs){
    	drmbJpaDao.delete(drmbs);
    }

}

