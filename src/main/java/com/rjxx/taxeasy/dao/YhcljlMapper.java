package com.rjxx.taxeasy.dao;

import java.util.List;
import java.util.Map;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Yhcljl;
import com.rjxx.taxeasy.vo.Yhcljlvo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Feb 22 13:59:07 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface YhcljlMapper {

    public Yhcljl findOneByParams(Map params);

    public List<Yhcljl> findAllByParams(Map params);

    public List<Yhcljl> findByPage(Pagination pagination);
    
    public List<Yhcljlvo> findYhcljl(Map params);

}

