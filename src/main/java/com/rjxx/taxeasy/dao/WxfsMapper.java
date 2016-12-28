package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Wxfs;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Dec 28 17:48:54 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface WxfsMapper {

    public Wxfs findOneByParams(Map params);

    public List<Wxfs> findAllByParams(Map params);

    public List<Wxfs> findByPage(Pagination pagination);

}

