package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.SpzSp;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Jan 17 15:01:20 GMT+08:00 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface SpzSpMapper {

    public SpzSp findOneByParams(Map params);

    public List<SpzSp> findAllByParams(Map params);

    public List<SpzSp> findByPage(Pagination pagination);

}

