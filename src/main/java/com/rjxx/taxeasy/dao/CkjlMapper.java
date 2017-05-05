package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Ckjl;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu May 04 18:42:46 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface CkjlMapper {

    public Ckjl findOneByParams(Map params);

    public List<Ckjl> findAllByParams(Map params);

    public List<Ckjl> findByPage(Pagination pagination);

}

