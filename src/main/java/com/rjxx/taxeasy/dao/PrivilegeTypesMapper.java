package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.PrivilegeTypes;
import com.rjxx.taxeasy.vo.PrivilegeTypesVo;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Dec 01 15:41:04 CST 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface PrivilegeTypesMapper {

    public PrivilegeTypes findOneByParams(Map params);

    public List<PrivilegeTypes> findAllByParams(Map params);
    public List<PrivilegeTypesVo> findBySql(Map params);
    public List<PrivilegeTypes> findByPage(Pagination pagination);

}

