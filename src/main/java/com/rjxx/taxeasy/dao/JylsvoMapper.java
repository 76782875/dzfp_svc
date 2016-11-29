package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.vo.Jylsvo;

import java.util.List;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 17 13:29:48 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface JylsvoMapper {

    public Jylsvo findOneByParams(Jylsvo jylsvo);

    public List<Jylsvo> findAllByParams(Jylsvo jylsvo);

    public List<Jylsvo> findByPage(Pagination pagination);
    //查询重开申请的发票
    public List<Jylsvo> findChsqByPage(Pagination pagination);
    
    //查询换开申请发票
    public List<Jylsvo> findHhsqByPage(Pagination pagination);
}

