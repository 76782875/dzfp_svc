package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.SpbmMapper;
import com.rjxx.taxeasy.vo.Spbm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri Oct 14 14:45:05 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
@Service
public class SpbmService {

    @Autowired
    private SpbmMapper spbmJpaDao;

    @Autowired
    private SpbmMapper spbmMapper;

    public Spbm findOneByParams(Spbm spbm) {
        return spbmMapper.findOneByParams(spbm);
    }

    public List<Spbm> findAllByParams(Map params) {
        return spbmMapper.findAllByParams(params);
    }

    public List<Spbm> findByPage(Pagination pagination) {
        return spbmMapper.findByPage(pagination);
    }

}

