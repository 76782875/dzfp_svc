package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.JkmbbJpaDao;
import com.rjxx.taxeasy.dao.JkmbbMapper;
import com.rjxx.taxeasy.domains.Jkmbb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Mar 27 10:47:00 CST 2018
 *
 * @ZhangBing
 */

@Service
public class JkmbbService {

    @Autowired
    private JkmbbJpaDao jkmbbJpaDao;

    @Autowired
    private JkmbbMapper jkmbbMapper;

    public Jkmbb findOne(int id) {
        return jkmbbJpaDao.findOne(id);
    }

    public void save(Jkmbb jkmbb) {
        jkmbbJpaDao.save(jkmbb);
    }

    public void save(List<Jkmbb> jkmbbList) {
        jkmbbJpaDao.save(jkmbbList);
    }

    public Jkmbb findOneByParams(Map params) {
        return jkmbbMapper.findOneByParams(params);
    }

    public List<Jkmbb> findAllByParams(Map params) {
        return jkmbbMapper.findAllByParams(params);
    }

    public List<Jkmbb> findByPage(Pagination pagination) {
        return jkmbbMapper.findByPage(pagination);
    }

}

