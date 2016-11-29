package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.DrPz;

import java.util.List;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 10 13:16:39 CST 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface DrPzMapper {

    public DrPz findOneByParams(DrPz drPz);

    public List<DrPz> findAllByParams(DrPz drPz);

    public List<DrPz> findByPage(Pagination pagination);

}

