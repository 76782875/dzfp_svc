package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.SmJpaDao;
import com.rjxx.taxeasy.dao.SmMapper;
import com.rjxx.taxeasy.domains.Sm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 10 13:25:23 CST 2016
 *
 * @ZhangBing
 */
@Service
public class SmService {

    @Autowired
    private SmJpaDao smJpaDao;

    @Autowired
    private SmMapper smMapper;

    public Sm findOne(int id) {
        return smJpaDao.findOne(id);
    }

    public void save(Sm sm) {
        smJpaDao.save(sm);
    }

    public void save(List<Sm> smList) {
        smJpaDao.save(smList);
    }

    public Sm findOneByParams(Sm sm) {
        return smMapper.findOneByParams(sm);
    }

    public List<Sm> findAllByParams(Sm sm) {
        return smMapper.findAllByParams(sm);
    }

    public List<Sm> findByPage(Pagination pagination) {
        return smMapper.findByPage(pagination);
    }

    public List<Sm> findAll() {
//        Iterable<Sm> smIterable = smJpaDao.findAll();
        List<Sm> smList1 = smJpaDao.findAll();
        List<Sm> smList = new ArrayList<>();
        for (Sm sm : smList1) {
            smList.add(sm);
        }
        return smList;
    }

}

