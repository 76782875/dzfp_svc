package com.rjxx.taxeasy.dao;

import java.util.List;
import java.util.Map;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Fpzl;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.domains.Kpspmx;
import com.rjxx.taxeasy.vo.Fpnum;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Jan 17 11:22:01 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface FpzlMapper {

    public Fpzl findOneByParams(Map params);

    public List<Fpzl> findAllByParams(Map params);

    public List<Fpzl> findByPage(Pagination pagination);
    
    public List<Fpnum> findGfpsl(Map params);
    
    public List<Kpspmx> findSpsl(Map params);
    
    public Kpls findSpje(Map params);
    
    public List<Fpnum> findDbsx(Map params);

}

