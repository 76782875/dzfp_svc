package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.FpkcTz;
import com.rjxx.taxeasy.vo.FpkcTzVo;
import com.rjxx.taxeasy.vo.FpkcYzszVo;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Jul 09 13:37:30 CST 2018
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface FpkcTzMapper {

    public FpkcTz findOneByParams(Map params);

    public List<FpkcTz> findAllByParams(Map params);

    public List<FpkcYzszVo> findByPage(Pagination pagination);

    public List<FpkcTzVo> findAllByPage(Pagination pagination);

    public List<FpkcTzVo> findByParams(Map params);

}

