package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Skp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri Oct 14 08:55:04 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
public interface SkpJpaDao extends CrudRepository<Skp, Integer> {
    @Query(nativeQuery = true,value = "select kpddm from t_skp where gsdm=?1")
    String findKpddmByGsdm(String gsdm);

    @Query(nativeQuery = true,value = "select id from t_skp where kpddm=?1")
    Integer findIdByKpddm(String kpddm);

    Skp findOneByKpddmAndGsdm(String kpddm,String gsdm);
}

