package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Yjmb;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu May 25 15:30:57 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface YjmbMapper {

    public Yjmb findOneByParams(Map params);

    public List<Yjmb> findAllByParams(Map params);

    public List<Yjmb> findByPage(Pagination pagination);

}

