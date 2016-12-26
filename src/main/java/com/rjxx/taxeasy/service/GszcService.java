package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.GszcJpaDao;
import com.rjxx.taxeasy.dao.GszcMapper;
import com.rjxx.taxeasy.domains.Gszc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Dec 20 15:51:26 CST 2016
 *
 * @ZhangBing
 */ 
@Service
public class GszcService {

    @Autowired
    private GszcJpaDao gszcJpaDao;

    @Autowired
    private GszcMapper gszcMapper;

    public Gszc findOne(int id) {
        return gszcJpaDao.findOne(id);
    }

    public void save(Gszc gszc) {
        gszcJpaDao.save(gszc);
    }

    public void save(List<Gszc> gszcList) {
        gszcJpaDao.save(gszcList);
    }

    public Gszc findOneByParams(Map params) {
        return gszcMapper.findOneByParams(params);
    }

    public List<Gszc> findAllByParams(Map params) {
        return gszcMapper.findAllByParams(params);
    }

    public List<Gszc> findByPage(Pagination pagination) {
        return gszcMapper.findByPage(pagination);
    }

}

