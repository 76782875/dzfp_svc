package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Jkmbzb;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Mar 20 16:09:03 CST 2018
 *
 * @ZhangBing
 */ 
public interface JkmbzbJpaDao extends CrudRepository<Jkmbzb, Integer> {

    @Query(nativeQuery = true,value = "select * from t_jkmbzb where mbid=?1 ")
    List<Jkmbzb> findListByMbid(Integer mbid);
}

