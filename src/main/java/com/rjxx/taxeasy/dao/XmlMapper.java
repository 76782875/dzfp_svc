package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Xml;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Dec 19 17:55:30 CST 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface XmlMapper {

    public Xml findOneByParams(Map params);

    public List<Xml> findAllByParams(Map params);

    public List<Xml> findByPage(Pagination pagination);

}

