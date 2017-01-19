package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Dyzl;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Jan 17 15:11:17 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface DyzlMapper {

    public Dyzl findOneByParams(Map params);

    public List<Dyzl> findAllByParams(Map params);

    public List<Dyzl> findByPage(Pagination pagination);

}

