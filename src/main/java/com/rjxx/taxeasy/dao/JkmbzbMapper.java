package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Jkmbzb;
import com.rjxx.taxeasy.vo.JkpzVo;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Mar 20 16:09:03 CST 2018
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface JkmbzbMapper {

    public Jkmbzb findOneByParams(Map params);

    public List<Jkmbzb> findAllByParams(Map params);

    public List<JkpzVo> findByPage(Pagination pagination);

    List<JkpzVo> findByMbId(Map map);

}

