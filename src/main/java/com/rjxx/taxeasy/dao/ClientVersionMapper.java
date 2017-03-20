package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.ClientVersion;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Mar 20 09:20:17 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface ClientVersionMapper {

    public ClientVersion findOneByParams(Map params);

    public List<ClientVersion> findAllByParams(Map params);

    public List<ClientVersion> findByPage(Pagination pagination);

}

