package com.rjxx.taxeasy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.YhcljlJpaDao;
import com.rjxx.taxeasy.dao.YhcljlMapper;
import com.rjxx.taxeasy.domains.Yhcljl;
import com.rjxx.taxeasy.vo.Yhcljlvo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Feb 22 13:59:07 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class YhcljlService {

    @Autowired
    private YhcljlJpaDao yhcljlJpaDao;

    @Autowired
    private YhcljlMapper yhcljlMapper;

    public Yhcljl findOne(int id) {
        return yhcljlJpaDao.findOne(id);
    }

    public void save(Yhcljl yhcljl) {
        yhcljlJpaDao.save(yhcljl);
    }

    public void save(List<Yhcljl> yhcljlList) {
        yhcljlJpaDao.save(yhcljlList);
    }

    public Yhcljl findOneByParams(Map params) {
        return yhcljlMapper.findOneByParams(params);
    }

    public List<Yhcljl> findAllByParams(Map params) {
        return yhcljlMapper.findAllByParams(params);
    }

    public List<Yhcljl> findByPage(Pagination pagination) {
        return yhcljlMapper.findByPage(pagination);
    }
    
    public List<Yhcljlvo> findYhcljl(Map params){
    	return yhcljlMapper.findYhcljl(params);
    }

}

