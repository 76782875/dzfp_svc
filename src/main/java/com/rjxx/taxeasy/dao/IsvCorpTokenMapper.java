package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.IsvCorpToken;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 13 17:42:46 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface IsvCorpTokenMapper {

    public IsvCorpToken findOneByParams(Map params);

    public List<IsvCorpToken> findAllByParams(Map params);

    public List<IsvCorpToken> findByPage(Pagination pagination);

	public void deleteCorpToken(Map map);

}

