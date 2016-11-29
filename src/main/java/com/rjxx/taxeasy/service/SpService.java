package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.SpJpaDao;
import com.rjxx.taxeasy.dao.SpMapper;
import com.rjxx.taxeasy.domains.Sp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 10 21:51:53 CST 2016
 *
 * @ZhangBing
 */
@Service
public class SpService {

    @Autowired
    private SpJpaDao spJpaDao;

    @Autowired
    private SpMapper spMapper;

    public Sp findOne(int id) {
        return spJpaDao.findOne(id);
    }

    public void save(Sp sp) {
        spJpaDao.save(sp);
    }

    public void save(List<Sp> spList) {
        spJpaDao.save(spList);
    }

    public Sp findOneByParams(Sp sp) {
        return spMapper.findOneByParams(sp);
    }

    public List<Sp> findAllByParams(Sp sp) {
        return spMapper.findAllByParams(sp);
    }

    public List<Sp> findByPage(Pagination pagination) {
        return spMapper.findByPage(pagination);
    }

}

