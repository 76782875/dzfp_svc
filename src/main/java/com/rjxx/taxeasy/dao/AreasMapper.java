package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Areas;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri May 04 17:37:54 CST 2018
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface AreasMapper {

    public Areas findOneByParams(Map params);

    public List<Areas> findAllByParams(Map params);

    public List<Areas> findByPage(Pagination pagination);

}

