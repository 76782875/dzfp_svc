package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Sm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 10 13:25:23 CST 2016
 *
 * @ZhangBing
 */ 
public interface SmJpaDao extends CrudRepository<Sm, Integer> {

    @Query(nativeQuery = true, value = "select * from t_sm where  yxbz='1'")
    List<Sm> findAll();

}

