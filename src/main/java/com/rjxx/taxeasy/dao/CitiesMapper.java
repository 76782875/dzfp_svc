package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Cities;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri May 04 17:05:04 CST 2018
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface CitiesMapper {

    public Cities findOneByParams(Map params);

    public List<Cities> findAllByParams(Map params);

    public List<Cities> findByPage(Pagination pagination);

}

