package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.RolesJpaDao;
import com.rjxx.taxeasy.dao.RolesMapper;
import com.rjxx.taxeasy.domains.Roles;
import com.rjxx.taxeasy.vo.RolesVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Dec 01 11:15:59 CST 2016
 *
 * @ZhangBing
 */ 
@Service
public class RolesService {

    @Autowired
    private RolesJpaDao rolesJpaDao;

    @Autowired
    private RolesMapper rolesMapper;

    public Roles findOne(int id) {
        return rolesJpaDao.findOne(id);
    }

    public void save(Roles roles) {
        rolesJpaDao.save(roles);
    }

    public void save(List<Roles> rolesList) {
        rolesJpaDao.save(rolesList);
    }

    public Roles findOneByParams(Map params) {
        return rolesMapper.findOneByParams(params);
    }

    public List<Roles> findAllByParams(Map params) {
        return rolesMapper.findAllByParams(params);
    }

    public List<RolesVo> findByPage(Pagination pagination) {
        return rolesMapper.findByPage(pagination);
    }
    public List<Roles> findBySql(Map params) {
        return rolesMapper.findBySql(params);
    }

    public Roles findDefaultOneByParams(Map params) {
        return rolesMapper.findDefaultOneByParams(params);
    }

}

