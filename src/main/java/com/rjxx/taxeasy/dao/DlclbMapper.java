package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Dlclb;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 23 18:15:39 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface DlclbMapper {

    public Dlclb findOneByParams(Map params);

    public List<Dlclb> findAllByParams(Map params);

    public List<Dlclb> findByPage(Pagination pagination);

}

