package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.XmlBean;

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

    public XmlBean findOneByParams(Map params);

    public List<XmlBean> findAllByParams(Map params);

    public List<XmlBean> findByPage(Pagination pagination);

}

