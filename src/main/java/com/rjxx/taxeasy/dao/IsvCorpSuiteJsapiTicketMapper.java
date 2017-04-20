package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.IsvCorpSuiteJsapiTicket;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 20 13:06:40 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface IsvCorpSuiteJsapiTicketMapper {

    public IsvCorpSuiteJsapiTicket findOneByParams(Map params);

    public List<IsvCorpSuiteJsapiTicket> findAllByParams(Map params);

    public List<IsvCorpSuiteJsapiTicket> findByPage(Pagination pagination);

}

