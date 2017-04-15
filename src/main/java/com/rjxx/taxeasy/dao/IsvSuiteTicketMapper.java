package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.IsvSuiteTicket;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 13 17:44:31 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface IsvSuiteTicketMapper {

    public IsvSuiteTicket findOneByParams(Map params);

    public List<IsvSuiteTicket> findAllByParams(Map params);

    public List<IsvSuiteTicket> findByPage(Pagination pagination);

}

