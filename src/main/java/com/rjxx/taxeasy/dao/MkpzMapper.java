package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Mkpz;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Oct 13 11:13:54 CST 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface MkpzMapper {
	
	public List<Mkpz> findAll();

    public Mkpz findOneByParams(Map params);

    public List<Mkpz> findAllByParams(Map params);

    public List<Mkpz> findByPage(Pagination pagination);

}

