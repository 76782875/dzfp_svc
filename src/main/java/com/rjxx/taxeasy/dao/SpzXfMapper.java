package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.SpzXf;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Jan 17 15:01:33 GMT+08:00 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface SpzXfMapper {

    public SpzXf findOneByParams(Map params);

    public List<SpzXf> findAllByParams(Map params);

    public List<SpzXf> findByPage(Pagination pagination);

}

