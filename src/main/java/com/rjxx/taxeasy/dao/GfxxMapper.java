package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Gfxx;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Feb 09 14:46:55 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface GfxxMapper {

    public Gfxx findOneByParams(Map params);

    public List<Gfxx> findAllByParams(Map params);

    public List<Gfxx> findByPage(Pagination pagination);
    
    public void deleteById(Map params);

}

