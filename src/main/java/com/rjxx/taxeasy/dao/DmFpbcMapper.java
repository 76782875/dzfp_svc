package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.DmFpbc;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri Feb 17 14:31:27 GMT+08:00 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface DmFpbcMapper {

    public DmFpbc findOneByParams(Map params);

    public List<DmFpbc> findAllByParams(Map params);

    public List<DmFpbc> findByPage(Pagination pagination);

}

