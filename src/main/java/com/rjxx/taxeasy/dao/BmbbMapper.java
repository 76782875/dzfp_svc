package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Bmbb;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Jan 16 14:03:49 GMT+08:00 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface BmbbMapper {

    public Bmbb findOneByParams(Map params);

    public List<Bmbb> findAllByParams(Map params);

    public List<Bmbb> findByPage(Pagination pagination);

}

