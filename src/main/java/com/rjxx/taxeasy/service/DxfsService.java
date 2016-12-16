package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.DxfsJpaDao;
import com.rjxx.taxeasy.dao.DxfsMapper;
import com.rjxx.taxeasy.domains.Dxfs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Dec 14 15:19:28 CST 2016
 *
 * @ZhangBing
 */ 
@Service
public class DxfsService {

    @Autowired
    private DxfsJpaDao dxfsJpaDao;

    @Autowired
    private DxfsMapper dxfsMapper;

    public Dxfs findOne(int id) {
        return dxfsJpaDao.findOne(id);
    }

    public void save(Dxfs dxfs) {
        dxfsJpaDao.save(dxfs);
    }

    public void save(List<Dxfs> dxfsList) {
        dxfsJpaDao.save(dxfsList);
    }

    public Dxfs findOneByParams(Map params) {
        return dxfsMapper.findOneByParams(params);
    }

    public List<Dxfs> findAllByParams(Map params) {
        return dxfsMapper.findAllByParams(params);
    }

    public List<Dxfs> findByPage(Pagination pagination) {
        return dxfsMapper.findByPage(pagination);
    }

}

