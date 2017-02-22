package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.XtYclogJpaDao;
import com.rjxx.taxeasy.dao.XtYclogMapper;
import com.rjxx.taxeasy.domains.XtYclog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Feb 15 15:41:09 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class XtYclogService {

    @Autowired
    private XtYclogJpaDao xtYclogJpaDao;

    @Autowired
    private XtYclogMapper xtYclogMapper;

    public XtYclog findOne(int id) {
        return xtYclogJpaDao.findOne(id);
    }

    public void save(XtYclog xtYclog) {
        xtYclogJpaDao.save(xtYclog);
    }

    public void save(List<XtYclog> xtYclogList) {
        xtYclogJpaDao.save(xtYclogList);
    }

    public XtYclog findOneByParams(Map params) {
        return xtYclogMapper.findOneByParams(params);
    }

    public List<XtYclog> findAllByParams(Map params) {
        return xtYclogMapper.findAllByParams(params);
    }

    public List<XtYclog> findByPage(Pagination pagination) {
        return xtYclogMapper.findByPage(pagination);
    }
    
    public void updateById(Map params) {
    	xtYclogMapper.updateById(params);
    }

}

