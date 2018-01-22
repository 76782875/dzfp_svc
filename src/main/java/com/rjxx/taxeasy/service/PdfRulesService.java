package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.PdfRulesJpaDao;
import com.rjxx.taxeasy.dao.PdfRulesMapper;
import com.rjxx.taxeasy.domains.PdfRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Jan 22 11:31:46 CST 2018
 *
 * @ZhangBing
 */ 
@Service
public class PdfRulesService {

    @Autowired
    private PdfRulesJpaDao pdfRulesJpaDao;

    @Autowired
    private PdfRulesMapper pdfRulesMapper;

    public PdfRules findOne(int id) {
        return pdfRulesJpaDao.findOne(id);
    }

    public void save(PdfRules pdfRules) {
        pdfRulesJpaDao.save(pdfRules);
    }

    public void save(List<PdfRules> pdfRulesList) {
        pdfRulesJpaDao.save(pdfRulesList);
    }

    public PdfRules findOneByParams(Map params) {
        return pdfRulesMapper.findOneByParams(params);
    }

    public List<PdfRules> findAllByParams(Map params) {
        return pdfRulesMapper.findAllByParams(params);
    }

    public List<PdfRules> findByPage(Pagination pagination) {
        return pdfRulesMapper.findByPage(pagination);
    }

}

