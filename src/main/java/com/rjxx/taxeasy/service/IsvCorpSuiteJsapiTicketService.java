package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.IsvCorpSuiteJsapiTicketJpaDao;
import com.rjxx.taxeasy.dao.IsvCorpSuiteJsapiTicketMapper;
import com.rjxx.taxeasy.domains.IsvCorpSuiteJsapiTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 20 13:06:40 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class IsvCorpSuiteJsapiTicketService {

    @Autowired
    private IsvCorpSuiteJsapiTicketJpaDao isvCorpSuiteJsapiTicketJpaDao;

    @Autowired
    private IsvCorpSuiteJsapiTicketMapper isvCorpSuiteJsapiTicketMapper;

    public IsvCorpSuiteJsapiTicket findOne(int id) {
        return isvCorpSuiteJsapiTicketJpaDao.findOne(id);
    }

    public void save(IsvCorpSuiteJsapiTicket isvCorpSuiteJsapiTicket) {
        isvCorpSuiteJsapiTicketJpaDao.save(isvCorpSuiteJsapiTicket);
    }

    public void save(List<IsvCorpSuiteJsapiTicket> isvCorpSuiteJsapiTicketList) {
        isvCorpSuiteJsapiTicketJpaDao.save(isvCorpSuiteJsapiTicketList);
    }

    public IsvCorpSuiteJsapiTicket findOneByParams(Map params) {
        return isvCorpSuiteJsapiTicketMapper.findOneByParams(params);
    }

    public List<IsvCorpSuiteJsapiTicket> findAllByParams(Map params) {
        return isvCorpSuiteJsapiTicketMapper.findAllByParams(params);
    }

    public List<IsvCorpSuiteJsapiTicket> findByPage(Pagination pagination) {
        return isvCorpSuiteJsapiTicketMapper.findByPage(pagination);
    }

    public void deleteisvCorpSuiteJsapiTicket(IsvCorpSuiteJsapiTicket isvCorpSuiteJsapiTicket) {
        isvCorpSuiteJsapiTicketJpaDao.delete(isvCorpSuiteJsapiTicket);
    }
}

