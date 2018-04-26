package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Zffs;
import com.rjxx.taxeasy.vo.ZffsVo;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Jun 01 13:46:11 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface ZffsMapper {

    public Zffs findOneByParams(Map params);

    public List<Zffs> findAllByParams(Map params);

    public List<ZffsVo> findAllByMap(Map params);
    
    public List<ZffsVo> findByPage(Pagination pagination);

}

