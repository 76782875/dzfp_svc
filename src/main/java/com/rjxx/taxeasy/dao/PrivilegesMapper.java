package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.taxeasy.domains.Privileges;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/9.
 */
@MybatisRepository
public interface PrivilegesMapper {

    public List<Privileges> findByRoleIds(Map params);

}
