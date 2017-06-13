package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Fphxwsjl;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Jun 13 13:21:38 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface FphxwsjlMapper {

    public Fphxwsjl findOneByParams(Map params);

    public List<Fphxwsjl> findAllByParams(Map params);

    public List<Fphxwsjl> findByPage(Pagination pagination);

}

