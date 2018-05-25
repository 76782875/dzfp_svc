package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Crestvbusiness;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri May 25 16:26:12 CST 2018
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface CrestvbusinessMapper {

    public Crestvbusiness findOneByParams(Map params);

    public List<Crestvbusiness> findAllByParams(Map params);

    public List<Crestvbusiness> findByPage(Pagination pagination);

}

