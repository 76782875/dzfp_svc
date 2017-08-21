package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.vSpbm;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Aug 21 18:41:15 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface vSpbmMapper {

    public vSpbm findOneByParams(Map params);

    public List<vSpbm> findAllByParams(Map params);

    public List<vSpbm> findByPage(Pagination pagination);

}

