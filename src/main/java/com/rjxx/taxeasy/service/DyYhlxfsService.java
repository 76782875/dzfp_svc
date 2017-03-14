package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.DyYhlxfsJpaDao;
import com.rjxx.taxeasy.dao.DyYhlxfsMapper;
import com.rjxx.taxeasy.domains.DyYhlxfs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Mar 09 14:09:59 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class DyYhlxfsService {

    @Autowired
    private DyYhlxfsJpaDao dyYhlxfsJpaDao;

    @Autowired
    private DyYhlxfsMapper dyYhlxfsMapper;

    public DyYhlxfs findOne(int id) {
        return dyYhlxfsJpaDao.findOne(id);
    }

    public void save(DyYhlxfs dyYhlxfs) {
        dyYhlxfsJpaDao.save(dyYhlxfs);
    }

    public void save(List<DyYhlxfs> dyYhlxfsList) {
        dyYhlxfsJpaDao.save(dyYhlxfsList);
    }

    public DyYhlxfs findOneByParams(Map params) {
        return dyYhlxfsMapper.findOneByParams(params);
    }

    public List<DyYhlxfs> findAllByParams(Map params) {
        return dyYhlxfsMapper.findAllByParams(params);
    }

    public List<DyYhlxfs> findByPage(Pagination pagination) {
        return dyYhlxfsMapper.findByPage(pagination);
    }
    
    public void updateYxbz(Map params){
    	dyYhlxfsMapper.updateYxbz(params);
    }

}

