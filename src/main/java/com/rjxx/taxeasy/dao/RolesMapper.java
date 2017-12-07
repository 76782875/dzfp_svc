package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Roles;
import com.rjxx.taxeasy.vo.RolesVo;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Dec 01 11:15:59 CST 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface RolesMapper {

    public Roles findOneByParams(Map params);

    public List<Roles> findAllByParams(Map params);

    public List<RolesVo> findByPage(Pagination pagination);
    public List<Roles> findBySql(Map params);

    public Roles findDefaultOneByParams(Map params);

}

