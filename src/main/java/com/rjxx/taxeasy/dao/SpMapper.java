package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Sp;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 10 21:51:53 CST 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface SpMapper {

    public Sp findOneByParams(Sp sp);

    public List<Sp> findAllByParams(Sp sp);

    public List<Sp> findByPage(Pagination pagination);

}

