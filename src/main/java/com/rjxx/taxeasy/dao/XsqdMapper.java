package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Xsqd;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Jul 03 16:36:36 CST 2018
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface XsqdMapper {

    public Xsqd findOneByParams(Map params);

    public List<Xsqd> findAllByParams(Map params);

    public List<Xsqd> findByPage(Pagination pagination);

}

