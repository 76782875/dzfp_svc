package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.JyxxsqHb;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Apr 10 17:02:19 CST 2018
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface JyxxsqHbMapper {

    public JyxxsqHb findOneByParams(Map params);

    public List<JyxxsqHb> findAllByParams(Map params);

    public List<JyxxsqHb> findByPage(Pagination pagination);

}

