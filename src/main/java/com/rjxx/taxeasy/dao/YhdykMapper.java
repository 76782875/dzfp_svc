package com.rjxx.taxeasy.dao;

import java.util.List;
import java.util.Map;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Yhdyk;
import com.rjxx.taxeasy.vo.Fpnum;
import com.rjxx.taxeasy.vo.Yhdykvo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Jan 17 16:56:16 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface YhdykMapper {

    public Yhdyk findOneByParams(Map params);

    public List<Yhdyk> findAllByParams(Map params);

    public List<Yhdykvo> findByPage(Pagination pagination);
    
    public Yhdyk findDyxx(Map params);
    
    public void updateYxbz(Map params);
    
    public List<Yhdykvo> findYhdy(Map params);
    
    public Fpnum findTjsj(Map params);

}

