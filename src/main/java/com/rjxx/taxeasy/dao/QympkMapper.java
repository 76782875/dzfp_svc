package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Qympk;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Mar 30 17:24:00 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface QympkMapper {

     Qympk findOneByParams(Map params);

     List<Qympk> findAllByParams(Map params);

     List<Qympk> findAll(Map params);

     List<Qympk> findByPage(Pagination pagination);

     void saveQympk(Qympk qympk);

}

