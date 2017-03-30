package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.XxXtxx;
import com.rjxx.taxeasy.vo.Xtxxvo;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Mar 23 14:23:47 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface XxXtxxMapper {

    public XxXtxx findOneByParams(Map params);

    public List<Xtxxvo> findAllByParams(Map params);

    public List<XxXtxx> findByPage(Pagination pagination);
    
    public void updateYdbz(Map params);
    
    public void delMany(Map params);
    
    public void allRead(Map params);
    
    public void allNotRead(Map params);

}

