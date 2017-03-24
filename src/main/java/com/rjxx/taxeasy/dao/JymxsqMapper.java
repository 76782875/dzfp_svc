package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Jymxsq;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Jan 04 13:34:29 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface JymxsqMapper {

    public Jymxsq findOneByParams(Map params);

    public List<Jymxsq> findAllByParams(Map params);
    
    public List<Jymxsq> findAllByDdhs(Map params);

    public List<Jymxsq> findByPage(Pagination pagination);

    public List<Jymxsq> findBySqlshList(List<Integer> sqlshList);

}

