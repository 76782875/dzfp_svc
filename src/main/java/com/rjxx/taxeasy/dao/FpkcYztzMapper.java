package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.FpkcYztz;
import com.rjxx.taxeasy.vo.FpkcTzVo;
import com.rjxx.taxeasy.vo.FpkcYjtzVo;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri Apr 27 13:28:18 CST 2018
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface FpkcYztzMapper {

    public FpkcYztz findOneByParams(Map params);

    public List<FpkcYztz> findAllByParams(Map params);

    public List<FpkcYztz> findByPage(Pagination pagination);

    public void deleteYhtzByYjszid(Map map);

    public List<FpkcYjtzVo> findAllTzList(Map params);

    public List<FpkcTzVo> findAllByPage(Pagination pagination);

}

