package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Xxts;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Dec 27 09:28:24 CST 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface XxtsMapper {

    public Xxts findOneByParams(Map params);

    public List<Xxts> findAllByParams(Map params);

    public List<Xxts> findByPage(Pagination pagination);

}

