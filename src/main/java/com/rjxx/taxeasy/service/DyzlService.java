package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.DyzlJpaDao;
import com.rjxx.taxeasy.dao.DyzlMapper;
import com.rjxx.taxeasy.domains.Dyzl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Jan 17 15:11:17 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class DyzlService {

    @Autowired
    private DyzlJpaDao dyzlJpaDao;

    @Autowired
    private DyzlMapper dyzlMapper;

    public Dyzl findOne(int id) {
        return dyzlJpaDao.findOne(id);
    }

    public void save(Dyzl dyzl) {
        dyzlJpaDao.save(dyzl);
    }

    public void save(List<Dyzl> dyzlList) {
        dyzlJpaDao.save(dyzlList);
    }

    public Dyzl findOneByParams(Map params) {
        return dyzlMapper.findOneByParams(params);
    }

    public List<Dyzl> findAllByParams(Map params) {
        return dyzlMapper.findAllByParams(params);
    }

    public List<Dyzl> findByPage(Pagination pagination) {
        return dyzlMapper.findByPage(pagination);
    }

}

