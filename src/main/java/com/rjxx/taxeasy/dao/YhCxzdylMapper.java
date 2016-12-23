package com.rjxx.taxeasy.dao;

import java.util.List;
import java.util.Map;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.YhCxzdyl;
import com.rjxx.taxeasy.vo.ZdylVo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Dec 21 18:10:14 CST 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface YhCxzdylMapper {

    public YhCxzdyl findOneByParams(Map params);

    public List<ZdylVo> findAllByParams(Map params);

    public List<YhCxzdyl> findByPage(Pagination pagination);

}

