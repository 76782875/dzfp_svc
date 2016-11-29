package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Jyspmx;

import java.util.List;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 10 13:25:11 CST 2016
 *
 * @ZhangBing
 */
@MybatisRepository
public interface JyspmxMapper {

    public Jyspmx findOneByParams(Jyspmx jyspmx);

    public List<Jyspmx> findAllByParams(Jyspmx jyspmx);

    public List<Jyspmx> findByPage(Pagination pagination);

    /**
     * 根据单据号查找
     *
     * @param djhList
     * @return
     */
    public List<Jyspmx> findByDjhList(List<Integer> djhList);

}

