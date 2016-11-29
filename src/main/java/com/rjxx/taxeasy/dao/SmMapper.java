package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Sm;

import java.util.List;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 10 13:25:23 CST 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface SmMapper {

    public Sm findOneByParams(Sm sm);

    public List<Sm> findAllByParams(Sm sm);

    public List<Sm> findByPage(Pagination pagination);

}

