package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Drmb;
import com.rjxx.taxeasy.vo.DrmbVo;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Oct 19 09:03:51 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface DrmbMapper {

    public Drmb findOneByParams(Drmb drmb);

    public Drmb findMrByParams(Drmb drmb);

    public List<Drmb> findAllByParams(Map params);

    public List<DrmbVo> findByPage(Pagination pagination);

}

