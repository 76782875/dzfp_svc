package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Jyzfmx;
import com.rjxx.taxeasy.vo.Jyzfmxvo;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed May 31 15:26:25 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface JyzfmxMapper {

    public Jyzfmx findOneByParams(Map params);

    public List<Jyzfmx> findAllByParams(Map params);

    public List<Jyzfmx> findByPage(Pagination pagination);

    List<Jyzfmxvo> findAllByParamsVo(Map parms);
}

