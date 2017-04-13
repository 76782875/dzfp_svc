package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Hbgz;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 13 09:19:03 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface HbgzMapper {

    public Hbgz findOneByParams(Map params);

    public List<Hbgz> findAllByParams(Map params);

    public List<Hbgz> findByPage(Pagination pagination);

}

