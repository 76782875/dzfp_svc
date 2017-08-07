package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Jymxsq;
import com.rjxx.taxeasy.domains.JymxsqCl;
import com.rjxx.taxeasy.vo.JyspmxDecimal2;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed May 31 17:18:47 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface JymxsqClMapper {

    public JymxsqCl findOneByParams(Map params);

    public List<JyspmxDecimal2> findAllByParams(Map params);

    public List<JymxsqCl> findByPage(Pagination pagination);

    public List<JymxsqCl> findBySqlsh(Map params);

    public List<JymxsqCl> findBySqlshList(List<Integer> sqlshList);

    public void addJymxsqClBatch(List<JymxsqCl> jymxsqCls);
}

