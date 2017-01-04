package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Tqlj;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Jan 04 10:01:04 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface TqljMapper {

    public Tqlj findOneByParams(Map params);

    public List<Tqlj> findAllByParams(Map params);

    public List<Tqlj> findByPage(Pagination pagination);

}

