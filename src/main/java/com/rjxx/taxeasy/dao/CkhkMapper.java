package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Ckhk;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri Nov 04 16:45:28 CST 2016
 *
 * @WangYong
 */ 
@MybatisRepository
public interface CkhkMapper {

    public Ckhk findOneByParams(Map params);

    public List<Ckhk> findAllByParams(Map params);

    public List<Ckhk> findByPage(Pagination pagination);
    
    public void updateZtbz(Map params);

}

