package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.KpdVer;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Mar 09 10:46:09 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface KpdVerMapper {

    public KpdVer findOneByParams(Map params);

    public List<KpdVer> findAllByParams(Map params);

    public List<KpdVer> findByPage(Pagination pagination);

    public KpdVer findOneByKpdid(int kpdid);
}

