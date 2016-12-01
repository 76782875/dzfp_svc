package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.vo.DzfpLogVO;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Dec 01 13:10:34 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface DzfplogvoMapper {

    public DzfpLogVO findOneByParams(Map params);

    public List<DzfpLogVO> findAllByParams(Map params);

    public List<DzfpLogVO> findByPage(Pagination pagination);

}

