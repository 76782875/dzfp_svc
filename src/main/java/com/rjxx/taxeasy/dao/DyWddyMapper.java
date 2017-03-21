package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.DyWddy;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Mar 09 14:07:44 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface DyWddyMapper {

    public DyWddy findOneByParams(Map params);

    public List<DyWddy> findAllByParams(Map params);

    public List<DyWddy> findByPage(Pagination pagination);
    
    public void updateYxbz(Map params);
    
    public List<DyWddy> findDyzlYh(Map params);

}

