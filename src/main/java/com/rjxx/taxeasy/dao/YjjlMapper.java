package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Yjjl;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Dec 20 12:04:34 CST 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface YjjlMapper {

    public Yjjl findOneByParams(Map params);

    public List<Yjjl> findAllByParams(Map params);

    public List<Yjjl> findByPage(Pagination pagination);

}

