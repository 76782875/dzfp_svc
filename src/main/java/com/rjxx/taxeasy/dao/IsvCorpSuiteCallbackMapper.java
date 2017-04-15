package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.IsvCorpSuiteCallback;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 13 17:40:49 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface IsvCorpSuiteCallbackMapper {

    public IsvCorpSuiteCallback findOneByParams(Map params);

    public List<IsvCorpSuiteCallback> findAllByParams(Map params);

    public List<IsvCorpSuiteCallback> findByPage(Pagination pagination);

}

