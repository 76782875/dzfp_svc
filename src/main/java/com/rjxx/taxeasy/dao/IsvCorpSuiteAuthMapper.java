package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.IsvCorpSuiteAuth;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 13 17:36:36 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface IsvCorpSuiteAuthMapper {

    public IsvCorpSuiteAuth findOneByParams(Map params);

    public List<IsvCorpSuiteAuth> findAllByParams(Map params);

    public List<IsvCorpSuiteAuth> findByPage(Pagination pagination);

}

