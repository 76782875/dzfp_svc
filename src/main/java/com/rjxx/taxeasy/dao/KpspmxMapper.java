package com.rjxx.taxeasy.dao;

import java.util.List;
import java.util.Map;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Kpspmx;
import com.rjxx.taxeasy.vo.Kpspmxvo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri Oct 21 09:52:47 CST 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface KpspmxMapper {

    public Kpspmx findOneByParams(Map params);

    public List<Kpspmxvo> findAllByParams(Map params);

    public List<Kpspmx> findByPage(Pagination pagination);
    //红冲查询单个明细
    public Kpspmxvo findMxByParams(Map params);
    
    //部分红冲更新
    public void update(Map params);
    
    //查询整张发票的可红冲金额
    public Kpspmxvo findKhcje(Map params);
    
    public List<Kpspmx> findMxList(Map params);
    
    public List<Kpspmx> findMxNewList(Map params);
    
    public List<Kpspmx> findMxNewByParams(Map params);

    List<Kpspmxvo> findSkMxList(Map params);

    public List<Kpspmx> findMx2List(Map params);

}

