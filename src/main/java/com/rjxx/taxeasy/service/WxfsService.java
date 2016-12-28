package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.WxfsJpaDao;
import com.rjxx.taxeasy.dao.WxfsMapper;
import com.rjxx.taxeasy.domains.Wxfs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Dec 28 17:48:54 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
@Service
public class WxfsService {

    @Autowired
    private WxfsJpaDao wxfsJpaDao;

    @Autowired
    private WxfsMapper wxfsMapper;

    public Wxfs findOne(int id) {
        return wxfsJpaDao.findOne(id);
    }

    public void save(Wxfs wxfs) {
        wxfsJpaDao.save(wxfs);
    }

    public void save(List<Wxfs> wxfsList) {
        wxfsJpaDao.save(wxfsList);
    }

    public Wxfs findOneByParams(Map params) {
        return wxfsMapper.findOneByParams(params);
    }

    public List<Wxfs> findAllByParams(Map params) {
        return wxfsMapper.findAllByParams(params);
    }

    public List<Wxfs> findByPage(Pagination pagination) {
        return wxfsMapper.findByPage(pagination);
    }

}

