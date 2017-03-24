package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Sp;
import com.rjxx.taxeasy.domains.Spz;
import com.rjxx.taxeasy.vo.Spvo;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Jan 17 15:00:54 GMT+08:00 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface SpzMapper {

    public Spz findOneByParams(Map params);

    public List<Spvo> findAllByParams(Map params);

    public List<Spz> findByPage(Pagination pagination);

}

