package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.FpkcYzsz;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Sat Mar 18 17:35:59 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface FpkcYzszMapper {

    public FpkcYzsz findOneByParams(Map params);

    public List<FpkcYzsz> findAllByParams(Map params);

    public List<FpkcYzsz> findByPage(Pagination pagination);

}

