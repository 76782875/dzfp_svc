package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Jyspmx;
import com.rjxx.taxeasy.vo.JyspmxDecimal;
import com.rjxx.taxeasy.vo.JyspmxDecimal2;

import java.util.List;
import java.util.Map;

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
    public List<JyspmxDecimal> findBySql(Jyspmx jyspmx);
    public List<JyspmxDecimal> getNeedToKP2(Map params);
    public List<JyspmxDecimal2> getNeedToKP3(Map params);

    public List<Jyspmx> findAll(Map params);

}

