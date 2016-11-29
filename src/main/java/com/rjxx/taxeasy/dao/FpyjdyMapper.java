package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Fpyjdy;
import com.rjxx.taxeasy.vo.Fpyjdyvo;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 24 19:06:11 CST 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface FpyjdyMapper {

    public Fpyjdy findOneByParams(Map params);

    public List<Fpyjdy> findAllByParams(Map params);

    public List<Fpyjdy> findByPage(Pagination pagination);
    //查询发票订阅情况
    public List<Fpyjdyvo> findFpyjdyByPage(Pagination pagination);
  //查询订阅种类
    public Fpyjdyvo findDyxx(Map params);
    
    //更新订阅信息
    public void updateDyxx(Map params);
    
    //查询当前用户主页订阅信息
    public List<Fpyjdyvo> findYhZyDy(Pagination pagination);
    
    //查询email订阅的的信息
    public List<Fpyjdyvo> findEmailDy();
}

