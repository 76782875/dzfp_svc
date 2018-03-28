package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Jkmbb;
import com.rjxx.taxeasy.vo.JkmbbVo;

import java.util.List;
import java.util.Map;

/*
*
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Mar 27 10:47:00 CST 2018
 *
 * @ZhangBing
*/

@MybatisRepository
public interface JkmbbMapper {

    public Jkmbb findOneByParams(Map params);

    public List<Jkmbb> findAllByParams(Map params);

    public List<JkmbbVo> findByPage(Pagination pagination);

}

