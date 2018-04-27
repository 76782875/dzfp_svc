package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.YanzhengmaJpaDao;
import com.rjxx.taxeasy.dao.YanzhengmaMapper;
import com.rjxx.taxeasy.domains.Yanzhengma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 26 14:23:05 CST 2018
 *
 * @ZhangBing
 */ 
@Service
public class YanzhengmaService {

    @Autowired
    private YanzhengmaJpaDao yanzhengmaJpaDao;

    @Autowired
    private YanzhengmaMapper yanzhengmaMapper;

    public Yanzhengma findOne(int id) {
        return yanzhengmaJpaDao.findOne(id);
    }

    public void save(Yanzhengma yanzhengma) {
        yanzhengmaJpaDao.save(yanzhengma);
    }

    public void save(List<Yanzhengma> yanzhengmaList) {
        yanzhengmaJpaDao.save(yanzhengmaList);
    }

    public Yanzhengma findOneByParams(Map params) {
        return yanzhengmaMapper.findOneByParams(params);
    }

    public List<Yanzhengma> findAllByParams(Map params) {
        return yanzhengmaMapper.findAllByParams(params);
    }

    public List<Yanzhengma> findByPage(Pagination pagination) {
        return yanzhengmaMapper.findByPage(pagination);
    }
    
    public Yanzhengma findBySql(Map params) {
        return yanzhengmaMapper.findBySql(params);
    }
}

