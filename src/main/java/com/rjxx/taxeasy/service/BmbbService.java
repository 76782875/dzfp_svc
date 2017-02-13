package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.BmbbJpaDao;
import com.rjxx.taxeasy.dao.BmbbMapper;
import com.rjxx.taxeasy.domains.Bmbb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Jan 16 14:03:49 GMT+08:00 2017
 *
 * @ZhangBing
 */ 
@Service
public class BmbbService {

    @Autowired
    private BmbbJpaDao bmbbJpaDao;

    @Autowired
    private BmbbMapper bmbbMapper;

    public Bmbb findOne(int id) {
        return bmbbJpaDao.findOne(id);
    }

    public void save(Bmbb bmbb) {
        bmbbJpaDao.save(bmbb);
    }

    public void save(List<Bmbb> bmbbList) {
        bmbbJpaDao.save(bmbbList);
    }

    public Bmbb findOneByParams(Map params) {
        return bmbbMapper.findOneByParams(params);
    }

    public List<Bmbb> findAllByParams(Map params) {
        return bmbbMapper.findAllByParams(params);
    }

    public List<Bmbb> findByPage(Pagination pagination) {
        return bmbbMapper.findByPage(pagination);
    }

}

