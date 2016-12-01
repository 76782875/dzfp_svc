package com.rjxx.taxeasy.dao;

import java.util.List;
import java.util.Map;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.vo.Fpcxvo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 17 14:37:57 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface KplsMapper {

    public Kpls findOneByParams(Map params);
    //查询发票是否存在
    public List<Kpls> findFpExist(Map params);
    
    public List<Fpcxvo> findAllByParams(Map params);

    public List<Fpcxvo> findByPage(Pagination pagination);
    
    public void update(Map params);
    
    //更新换开标志
    public void updateHkbz(Map params);
    
    public List<Kpls> printSingle(Map params);
    
    public List<Fpcxvo> printmany(Map params);
    
    //查询红冲数据
    public List<Fpcxvo> findKhcfpByPage(Pagination pagination);
    //红虫后更新发票状态
    public void updateFpczlx(Map params);
    
    public List<Kpls> findAllByKpls(Kpls kpls);
    
    //查询明细合计金额
    public Kpls findHjje(Map params);
    
    //更新主表合计金额
    public void updateHjje(Map params);
    
    //红冲查询相差月份
    public Fpcxvo selectMonth(Map params);
    
    //查寻发票换开数据
    public List<Fpcxvo> findFphkByPage(Pagination pagination);
    
    //发票重开数据查询
    public List<Fpcxvo> findFpcksqKpls(Map params);
    
    /*发票换开申请数据查询*/
    public List<Fpcxvo> findHkfpsqByPage(Pagination pagination);
    
    public List<Kpls> findKplsByDjh(Map params);
    
    public List<Kpls> findKplsByPms(Map params);
}

