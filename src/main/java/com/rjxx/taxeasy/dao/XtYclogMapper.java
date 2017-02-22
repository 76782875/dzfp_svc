package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.XtYclog;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Feb 15 15:41:09 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface XtYclogMapper {

    public XtYclog findOneByParams(Map params);

    public List<XtYclog> findAllByParams(Map params);

    public List<XtYclog> findByPage(Pagination pagination);

    public void updateById(Map params);
}

