package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.FphxwsjlSb;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Jul 24 13:06:47 CST 2018
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface FphxwsjlSbMapper {

    public FphxwsjlSb findOneByParams(Map params);

    public List<FphxwsjlSb> findAllByParams(Map params);

    public List<FphxwsjlSb> findByPage(Pagination pagination);

}

