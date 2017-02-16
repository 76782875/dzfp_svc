package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.ClientLogin;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Jan 05 14:36:48 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface ClientLoginMapper {

    public ClientLogin findOneByParams(Map params);

    public List<ClientLogin> findAllByParams(Map params);

    public List<ClientLogin> findByPage(Pagination pagination);

}

