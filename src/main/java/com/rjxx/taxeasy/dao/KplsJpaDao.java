package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Kpls;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 17 14:37:57 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
public interface KplsJpaDao extends CrudRepository<Kpls, Integer> {

    @Query(nativeQuery = true,value = "select * from t_kpls where serialorder=?1 and yxbz='1'")
    Kpls findOneBySerialorder(String serialorder);

    @Query(nativeQuery = true,value = "select * from t_kpls where djh=?1 and yxbz='1'")
    Kpls findOneByDjh(Integer djh);
}

