package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.FpkcMx;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed May 02 13:38:11 CST 2018
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface FpkcMxMapper {

    public FpkcMx findOneByParams(Map params);

    public List<FpkcMx> findAllByParams(Map params);

    public List<FpkcMx> findByPage(Pagination pagination);

    public void deleteMxByKcid(Map params);

}

