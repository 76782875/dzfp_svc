package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Zclog;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 26 17:25:58 CST 2018
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface ZclogMapper {

    public Zclog findOneByParams(Map params);

    public List<Zclog> findAllByParams(Map params);

    public List<Zclog> findByPage(Pagination pagination);

	public Zclog findOneBySql(Map<String, Object> params);

	public void updateZcnum(Map<String, Object> map);

	public void updateTynum(Map<String, Object> map);

}

