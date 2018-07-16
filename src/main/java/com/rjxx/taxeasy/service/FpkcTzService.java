package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.FpkcTzJpaDao;
import com.rjxx.taxeasy.dao.FpkcTzMapper;
import com.rjxx.taxeasy.domains.FpkcTz;
import com.rjxx.taxeasy.vo.FpkcTzVo;
import com.rjxx.taxeasy.vo.FpkcYzszVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Jul 09 13:37:30 CST 2018
 *
 * @ZhangBing
 */ 
@Service
public class FpkcTzService {

    @Autowired
    private FpkcTzJpaDao fpkcTzJpaDao;

    @Autowired
    private FpkcTzMapper fpkcTzMapper;

    public FpkcTz findOne(int id) {
        return fpkcTzJpaDao.findOne(id);
    }

    public void save(FpkcTz fpkcTz) {
        fpkcTzJpaDao.save(fpkcTz);
    }

    public void save(List<FpkcTz> fpkcTzList) {
        fpkcTzJpaDao.save(fpkcTzList);
    }

    public FpkcTz findOneByParams(Map params) {
        return fpkcTzMapper.findOneByParams(params);
    }

    public List<FpkcTz> findAllByParams(Map params) {
        return fpkcTzMapper.findAllByParams(params);
    }

    public List<FpkcYzszVo> findByPage(Pagination pagination) {
        return fpkcTzMapper.findByPage(pagination);
    }
    public List<FpkcTzVo> findAllByPage(Pagination pagination) {
        return fpkcTzMapper.findAllByPage(pagination);
    }
    public List<FpkcTzVo> findByParams(Map params) {
        return fpkcTzMapper.findByParams(params);
    }
}

