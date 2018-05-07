package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Cities;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri May 04 17:05:04 CST 2018
 *
 * @ZhangBing
 */ 
public interface CitiesJpaDao extends CrudRepository<Cities, Integer> {
    @Query(nativeQuery = true, value = "select * from cities where provinceid =?1 ")
    List<Cities> findListByProvinceid(String provinceid);
}

