package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.ZffsJpaDao;
import com.rjxx.taxeasy.dao.ZffsMapper;
import com.rjxx.taxeasy.domains.Zffs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Jun 01 13:46:11 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class ZffsService {

    @Autowired
    private ZffsJpaDao zffsJpaDao;

    @Autowired
    private ZffsMapper zffsMapper;

    public Zffs findOne(int id) {
        return zffsJpaDao.findOne(id);
    }

    public void save(Zffs zffs) {
        zffsJpaDao.save(zffs);
    }

    public void save(List<Zffs> zffsList) {
        zffsJpaDao.save(zffsList);
    }

    public Zffs findOneByParams(Map params) {
        return zffsMapper.findOneByParams(params);
    }

    public List<Zffs> findAllByParams(Map params) {
        return zffsMapper.findAllByParams(params);
    }

    public List<Zffs> findByPage(Pagination pagination) {
        return zffsMapper.findByPage(pagination);
    }

}

