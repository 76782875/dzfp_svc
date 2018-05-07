package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Provinces;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri May 04 16:35:21 CST 2018
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface ProvincesMapper {

    public Provinces findOneByParams(Map params);

    public List<Provinces> findAllByParams(Map params);

    public List<Provinces> findByPage(Pagination pagination);

}

