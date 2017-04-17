package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.ClientFile;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Apr 17 09:28:47 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface ClientFileMapper {

    public ClientFile findOneByParams(Map params);

    public List<ClientFile> findAllByParams(Map params);

    public List<ClientFile> findByPage(Pagination pagination);

}

