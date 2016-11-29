package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.XfMb;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Nov 01 17:15:08 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface XfMbMapper {

    public XfMb findOneByParams(Map params);

    public List<XfMb> findAllByParams(Map params);

    public List<XfMb> findByPage(Pagination pagination);

}

