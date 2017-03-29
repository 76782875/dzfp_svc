package com.rjxx.taxeasy.dao;

import java.util.List;
import java.util.Map;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Cszb;
import com.rjxx.taxeasy.vo.CsbVo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Nov 02 13:27:26 CST 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface CszbMapper {

    public Cszb findOneByParams(Cszb params);

    public List<Cszb> findAllByParams(Map params);

    public List<CsbVo> findByPage(Pagination pagination);

	public Cszb findsfzlkpByParams(Cszb cszb);

}

