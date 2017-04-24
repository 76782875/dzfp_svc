package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.IsvCorpApp;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 13 17:35:28 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface IsvCorpAppMapper {

    public IsvCorpApp findOneByParams(Map params);

    public List<IsvCorpApp> findAllByParams(Map params);

    public List<IsvCorpApp> findByPage(Pagination pagination);

	public void deleteCorpApp( Map map);

}

