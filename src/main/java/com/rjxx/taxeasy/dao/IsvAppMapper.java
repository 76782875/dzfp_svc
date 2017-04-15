package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.IsvApp;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 13 17:30:56 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface IsvAppMapper {

    public IsvApp findOneByParams(Map params);

    public List<IsvApp> findAllByParams(Map params);

    public List<IsvApp> findByPage(Pagination pagination);

}

