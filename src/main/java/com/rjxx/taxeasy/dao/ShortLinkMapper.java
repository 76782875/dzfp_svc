package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.ShortLink;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Jun 27 09:39:37 CST 2018
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface ShortLinkMapper {

    public ShortLink findOneByParams(Map params);

    public List<ShortLink> findAllByParams(Map params);

    public List<ShortLink> findByPage(Pagination pagination);

}

