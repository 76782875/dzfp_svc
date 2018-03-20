package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Jkpzzb;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Mar 20 16:09:03 CST 2018
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface JkpzzbMapper {

    public Jkpzzb findOneByParams(Map params);

    public List<Jkpzzb> findAllByParams(Map params);

    public List<Jkpzzb> findByPage(Pagination pagination);

}

