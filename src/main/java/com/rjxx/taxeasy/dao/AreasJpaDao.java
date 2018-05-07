package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Areas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri May 04 17:37:54 CST 2018
 *
 * @ZhangBing
 */ 
public interface AreasJpaDao extends CrudRepository<Areas, Integer> {
    @Query(nativeQuery = true, value = "select * from areas where cityid =?1 ")
    List<Areas> findListByProvinceid(String cityid);
}

