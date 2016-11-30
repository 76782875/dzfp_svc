package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Kppmxx;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Nov 30 10:13:46 CST 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface KppmxxMapper {

    public Kppmxx findOneByParams(Map params);

    public List<Kppmxx> findAllByParams(Map params);

    public List<Kppmxx> findByPage(Pagination pagination);

}

