package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.PrivilegeTypesJpaDao;
import com.rjxx.taxeasy.dao.PrivilegeTypesMapper;
import com.rjxx.taxeasy.domains.PrivilegeTypes;
import com.rjxx.taxeasy.vo.PrivilegeTypesVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Dec 01 15:41:04 CST 2016
 *
 * @ZhangBing
 */ 
@Service
public class PrivilegeTypesService {

    @Autowired
    private PrivilegeTypesJpaDao privilegeTypesJpaDao;

    @Autowired
    private PrivilegeTypesMapper privilegeTypesMapper;

    public PrivilegeTypes findOne(int id) {
        return privilegeTypesJpaDao.findOne(id);
    }

    public void save(PrivilegeTypes privilegeTypes) {
        privilegeTypesJpaDao.save(privilegeTypes);
    }

    public void save(List<PrivilegeTypes> privilegeTypesList) {
        privilegeTypesJpaDao.save(privilegeTypesList);
    }

    public PrivilegeTypes findOneByParams(Map params) {
        return privilegeTypesMapper.findOneByParams(params);
    }

    public List<PrivilegeTypes> findAllByParams(Map params) {
        return privilegeTypesMapper.findAllByParams(params);
    }

    public List<PrivilegeTypes> findByPage(Pagination pagination) {
        return privilegeTypesMapper.findByPage(pagination);
    }
    public List<PrivilegeTypesVo> findBySql(Map params) {
        return privilegeTypesMapper.findBySql(params);
    }
}

