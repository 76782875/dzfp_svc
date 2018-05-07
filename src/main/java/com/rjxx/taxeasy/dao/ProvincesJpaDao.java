package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Provinces;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri May 04 16:35:21 CST 2018
 *
 * @ZhangBing
 */ 
public interface ProvincesJpaDao extends CrudRepository<Provinces, Integer> {
    @Query(nativeQuery = true, value = "select * from provinces ")
    List<Provinces> findAll();
}

