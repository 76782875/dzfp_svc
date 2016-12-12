package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Pp;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Dec 12 17:15:27 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface PpMapper {

    public Pp findOneByParams(Map params);

    public List<Pp> findAllByParams(Map params);

    public List<Pp> findByPage(Pagination pagination);

}

