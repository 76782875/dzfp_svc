package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.vo.Spbm;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Jan 11 16:37:26 GMT+08:00 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface SpbmMapper {

    public Spbm findOneByParams(Map params);

    public List<Spbm> findAllByParams(Map params);

    public List<Spbm> findAllByParam(Map params);

    public List<Spbm> findByPage(Pagination pagination);

    public List<Spbm> findSpbm();
    public List<Spbm> findAllSpbm();
}

