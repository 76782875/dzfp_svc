package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.DyWddyJpaDao;
import com.rjxx.taxeasy.dao.DyWddyMapper;
import com.rjxx.taxeasy.domains.DyWddy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Mar 09 14:07:44 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class DyWddyService {

    @Autowired
    private DyWddyJpaDao dyWddyJpaDao;

    @Autowired
    private DyWddyMapper dyWddyMapper;

    public DyWddy findOne(int id) {
        return dyWddyJpaDao.findOne(id);
    }

    public void save(DyWddy dyWddy) {
        dyWddyJpaDao.save(dyWddy);
    }

    public void save(List<DyWddy> dyWddyList) {
        dyWddyJpaDao.save(dyWddyList);
    }

    public DyWddy findOneByParams(Map params) {
        return dyWddyMapper.findOneByParams(params);
    }

    public List<DyWddy> findAllByParams(Map params) {
        return dyWddyMapper.findAllByParams(params);
    }

    public List<DyWddy> findByPage(Pagination pagination) {
        return dyWddyMapper.findByPage(pagination);
    }
    
    public void updateYxbz(Map params){
    	dyWddyMapper.updateYxbz(params);
    }
    
    public List<DyWddy> findDyzlYh(Map params){
    	return dyWddyMapper.findDyzlYh(params);
    }

}

