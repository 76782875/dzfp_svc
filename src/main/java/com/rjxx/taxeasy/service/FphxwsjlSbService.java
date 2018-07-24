package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.FphxwsjlSbJpaDao;
import com.rjxx.taxeasy.dao.FphxwsjlSbMapper;
import com.rjxx.taxeasy.domains.FphxwsjlSb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Jul 24 13:06:47 CST 2018
 *
 * @ZhangBing
 */ 
@Service
public class FphxwsjlSbService {

    @Autowired
    private FphxwsjlSbJpaDao fphxwsjlSbJpaDao;

    @Autowired
    private FphxwsjlSbMapper fphxwsjlSbMapper;

    public FphxwsjlSb findOne(int id) {
        return fphxwsjlSbJpaDao.findOne(id);
    }

    public void save(FphxwsjlSb fphxwsjlSb) {
        fphxwsjlSbJpaDao.save(fphxwsjlSb);
    }

    public void save(List<FphxwsjlSb> fphxwsjlSbList) {
        fphxwsjlSbJpaDao.save(fphxwsjlSbList);
    }

    public FphxwsjlSb findOneByParams(Map params) {
        return fphxwsjlSbMapper.findOneByParams(params);
    }

    public List<FphxwsjlSb> findAllByParams(Map params) {
        return fphxwsjlSbMapper.findAllByParams(params);
    }

    public List<FphxwsjlSb> findByPage(Pagination pagination) {
        return fphxwsjlSbMapper.findByPage(pagination);
    }

}

