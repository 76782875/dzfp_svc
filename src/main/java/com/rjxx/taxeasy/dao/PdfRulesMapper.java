package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.PdfRules;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Jan 22 11:31:46 CST 2018
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface PdfRulesMapper {

    public PdfRules findOneByParams(Map params);

    public List<PdfRules> findAllByParams(Map params);

    public List<PdfRules> findByPage(Pagination pagination);

}

