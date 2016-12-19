package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.XmlJpaDao;
import com.rjxx.taxeasy.dao.XmlMapper;
import com.rjxx.taxeasy.domains.Xml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Dec 19 17:55:30 CST 2016
 *
 * @ZhangBing
 */ 
@Service
public class XmlService {

    @Autowired
    private XmlJpaDao xmlJpaDao;

    @Autowired
    private XmlMapper xmlMapper;

    public Xml findOne(int id) {
        return xmlJpaDao.findOne(id);
    }

    public void save(Xml xml) {
        xmlJpaDao.save(xml);
    }

    public void save(List<Xml> xmlList) {
        xmlJpaDao.save(xmlList);
    }

    public Xml findOneByParams(Map params) {
        return xmlMapper.findOneByParams(params);
    }

    public List<Xml> findAllByParams(Map params) {
        return xmlMapper.findAllByParams(params);
    }

    public List<Xml> findByPage(Pagination pagination) {
        return xmlMapper.findByPage(pagination);
    }

}

