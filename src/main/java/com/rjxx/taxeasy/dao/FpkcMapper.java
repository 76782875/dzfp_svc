package com.rjxx.taxeasy.dao;

import java.util.List;
import java.util.Map;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Fpkc;
import com.rjxx.taxeasy.vo.Fpkcvo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri Oct 14 14:05:54 CST 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface FpkcMapper {
    
	public void update(Map params);
	
	public void destory(Map params);
	
	public List<Fpkcvo> findKpd(Map params);
	
	public Fpkcvo findKyl(Map params);
	
    public Fpkc findOneByParams(Map params);
    
    public List<Fpkc> findFphmd(Map params);
    
    public List<Fpkc> findFphmdxg(Map params);
    
    public List<Fpkcvo> findAllByParams(Map params);

    public List<Fpkcvo> findByPage(Pagination pagination);
    
    //定时任务查询库存发票量
    public List<Fpkcvo> findKcfpl();
    
    //定时任务更新库存量
    public void updateFpkcl(Map params);
    
    //更具当前用户的订阅信息查询库余量
    public Fpkcvo findZyKyl(Map params);
    
    //发票库存监控查询
    public List<Fpkcvo> findKcjkByPage(Pagination pagination);

}

