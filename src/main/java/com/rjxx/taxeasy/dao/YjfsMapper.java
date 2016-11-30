package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Yjfs;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Nov 30 10:38:10 CST 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface YjfsMapper {

    public Yjfs findOneByParams(Map params);

    public List<Yjfs> findAllByParams(Map params);

    public List<Yjfs> findByPage(Pagination pagination);

}

