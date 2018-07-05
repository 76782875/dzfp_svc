package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.XsqdJpaDao;
import com.rjxx.taxeasy.dao.XsqdMapper;
import com.rjxx.taxeasy.domains.Xsqd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Jul 03 16:36:36 CST 2018
 *
 * @ZhangBing
 */ 
@Service
public class XsqdService {

    @Autowired
    private XsqdJpaDao xsqdJpaDao;

    @Autowired
    private XsqdMapper xsqdMapper;

    public Xsqd findOne(int id) {
        return xsqdJpaDao.findOne(id);
    }

    public void save(Xsqd xsqd) {
        xsqdJpaDao.save(xsqd);
    }

    public void save(List<Xsqd> xsqdList) {
        xsqdJpaDao.save(xsqdList);
    }

    public Xsqd findOneByParams(Map params) {
        return xsqdMapper.findOneByParams(params);
    }

    public List<Xsqd> findAllByParams(Map params) {
        return xsqdMapper.findAllByParams(params);
    }

    public List<Xsqd> findByPage(Pagination pagination) {
        return xsqdMapper.findByPage(pagination);
    }

}

