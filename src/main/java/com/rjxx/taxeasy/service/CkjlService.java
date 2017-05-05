package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.CkjlJpaDao;
import com.rjxx.taxeasy.dao.CkjlMapper;
import com.rjxx.taxeasy.domains.Ckjl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu May 04 18:42:46 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class CkjlService {

    @Autowired
    private CkjlJpaDao ckjlJpaDao;

    @Autowired
    private CkjlMapper ckjlMapper;

    public Ckjl findOne(int id) {
        return ckjlJpaDao.findOne(id);
    }

    public void save(Ckjl ckjl) {
        ckjlJpaDao.save(ckjl);
    }

    public void save(List<Ckjl> ckjlList) {
        ckjlJpaDao.save(ckjlList);
    }

    public Ckjl findOneByParams(Map params) {
        return ckjlMapper.findOneByParams(params);
    }

    public List<Ckjl> findAllByParams(Map params) {
        return ckjlMapper.findAllByParams(params);
    }

    public List<Ckjl> findByPage(Pagination pagination) {
        return ckjlMapper.findByPage(pagination);
    }

}

