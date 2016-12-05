package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Gdjl;
import com.rjxx.taxeasy.vo.Gdjlvo;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Nov 30 14:07:07 CST 2016
 *
 * @WangYong
 */ 
@MybatisRepository
public interface GdjlMapper {

    public Gdjl findOneByParams(Map params);

    public List<Gdjl> findAllByParams(Map params);

    public List<Gdjlvo> findByPage(Pagination pagination);
    
    public void updategdzt(Map params);
}

