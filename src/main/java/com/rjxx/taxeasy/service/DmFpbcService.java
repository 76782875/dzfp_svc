package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.DmFpbcJpaDao;
import com.rjxx.taxeasy.dao.DmFpbcMapper;
import com.rjxx.taxeasy.domains.DmFpbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri Feb 17 14:31:27 GMT+08:00 2017
 *
 * @ZhangBing
 */ 
@Service
public class DmFpbcService {

    @Autowired
    private DmFpbcJpaDao dmFpbcJpaDao;

    @Autowired
    private DmFpbcMapper dmFpbcMapper;

    public DmFpbc findOne(int id) {
        return dmFpbcJpaDao.findOne(id);
    }

    public void save(DmFpbc dmFpbc) {
        dmFpbcJpaDao.save(dmFpbc);
    }

    public void save(List<DmFpbc> dmFpbcList) {
        dmFpbcJpaDao.save(dmFpbcList);
    }

    public DmFpbc findOneByParams(Map params) {
        return dmFpbcMapper.findOneByParams(params);
    }

    public List<DmFpbc> findAllByParams(Map params) {
        return dmFpbcMapper.findAllByParams(params);
    }

    public List<DmFpbc> findByPage(Pagination pagination) {
        return dmFpbcMapper.findByPage(pagination);
    }

}

