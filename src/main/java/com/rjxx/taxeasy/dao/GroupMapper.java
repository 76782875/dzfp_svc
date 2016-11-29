package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Group;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Oct 13 14:35:28 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface GroupMapper {

    public Group findOneByParams(Group group);

    public List<Group> findAllByParams(Group group);

    public List<Group> findByPage(Pagination pagination);

}

