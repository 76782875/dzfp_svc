package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Jyxxsq;
import com.rjxx.taxeasy.domains.Xf;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Jan 04 13:33:08 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface JyxxsqMapper {

    public Jyxxsq findOneByParams(Map params);

    public List<Jyxxsq> findAllByTqms(Map params);

    public List<Jyxxsq> findAllByJylshs(Map params);
    
    public List<Jyxxsq> findAllByDdhs(Map params);
    
    public List<Jyxxsq> findByPage(Pagination pagination);
    
    public Xf findXfExistByKpd(Map params);

    public void saveJyxxsq(Jyxxsq jyxxsq);
}

