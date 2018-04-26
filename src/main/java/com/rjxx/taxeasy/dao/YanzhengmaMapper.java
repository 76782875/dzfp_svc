package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Yanzhengma;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 26 14:23:05 CST 2018
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface YanzhengmaMapper {

    public Yanzhengma findOneByParams(Map params);

    public List<Yanzhengma> findAllByParams(Map params);

    public List<Yanzhengma> findByPage(Pagination pagination);

}

