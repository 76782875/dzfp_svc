package com.rjxx.taxeasy.dao;

import java.util.List;
import java.util.Map;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Dyfs;
import com.rjxx.taxeasy.vo.Dyvo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Jan 18 09:14:29 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface DyfsMapper {

    public Dyfs findOneByParams(Map params);

    public List<Dyfs> findAllByParams(Map params);

    public List<Dyfs> findByPage(Pagination pagination);
    
    public List<Dyvo> findDyzl(Map params);
    
    public List<Dyvo> findDyfs(Map params);

}

