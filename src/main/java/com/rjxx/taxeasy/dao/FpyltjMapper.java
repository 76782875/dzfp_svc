package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Fpyltj;
import com.rjxx.taxeasy.vo.Cxtjvo;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu May 03 16:46:51 CST 2018
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface FpyltjMapper {

    public Fpyltj findOneByParams(Map params);

    public List<Fpyltj> findAllByParams(Map params);

    public List<Fpyltj> findByPage(Pagination pagination);

    public List<Cxtjvo> findYplByParams(Map params);

}

