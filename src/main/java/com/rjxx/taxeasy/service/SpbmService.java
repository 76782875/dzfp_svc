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
 * Wed Jan 11 16:37:26 GMT+08:00 2017
 *
 * @ZhangBing
 */ 
@Service
public class SpbmService {


    @Autowired
    private SpbmMapper spbmMapper;

    public Spbm findOneByParams(Map params) {
        return spbmMapper.findOneByParams(params);
    }

    public List<Spbm> findAllByParams(Map params) {
        return spbmMapper.findAllByParams(params);
    }

    public List<Spbm> findAllByParam(Map params) {
        return spbmMapper.findAllByParam(params);
    }

    public List<Spbm> findByPage(Pagination pagination) {
        return spbmMapper.findByPage(pagination);
    }

}

