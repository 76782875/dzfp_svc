package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Jyls;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 10 13:24:59 CST 2016
 *
 * @ZhangBing
 */ 
public interface JylsJpaDao extends CrudRepository<Jyls, Integer> {

    @Query(nativeQuery = true,value = "select djh from t_jyls where tqm=?1 and gsdm=?2 and yxbz='1'")
    List<Integer> findDjhByTqmAndGsdm(String tqm, String gsdm);

    @Query(nativeQuery = true,value = "select * from t_jyls where gsdm=?1 and ddh=?2 and yxbz='1'")
    List<Jyls> findByGsdmAndDdh(String gsdm, String ddh);
}

