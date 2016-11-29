package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.vo.Spbm;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri Oct 14 14:45:05 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface SpbmMapper {

    public Spbm findOneByParams(Spbm spbm);

    public List<Spbm> findAllByParams(Map params);

    public List<Spbm> findByPage(Pagination pagination);

}

