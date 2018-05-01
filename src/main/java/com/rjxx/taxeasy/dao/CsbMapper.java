package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Csb;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Nov 02 11:58:41 CST 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface CsbMapper {

    public Csb findOneByParams(Map params);

    public List<Csb> findAllByParams(Map params);

    public List<Csb> findByPage(Pagination pagination);

	public List<Csb> findBySql(Map params);

}

