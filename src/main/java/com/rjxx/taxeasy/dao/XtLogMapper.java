package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.XtLog;
import com.rjxx.taxeasy.vo.XtLogVo;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Jan 16 09:14:56 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface XtLogMapper {

    public XtLog findOneByParams(Map params);

    public List<XtLog> findAllByParams(Map params);

    public List<XtLogVo> findByPage(Pagination pagination);

}

