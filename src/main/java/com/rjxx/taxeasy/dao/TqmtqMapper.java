package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Tqmtq;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Dec 07 13:40:06 CST 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface TqmtqMapper {

    public Tqmtq findOneByParams(Map params);

    public List<Tqmtq> findAllByParams(Map params);

    public List<Tqmtq> findByPage(Pagination pagination);

}

