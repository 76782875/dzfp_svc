package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Jkpzdmb;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Mar 20 16:08:27 CST 2018
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface JkpzdmbMapper {

    public Jkpzdmb findOneByParams(Map params);

    public List<Jkpzdmb> findAllByParams(Map params);

    public List<Jkpzdmb> findByPage(Pagination pagination);

}

