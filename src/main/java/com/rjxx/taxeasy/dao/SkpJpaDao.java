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
    @Query(nativeQuery = true,value = "select kpddm from t_skp where gsdm=?1 and yxbz='1' ")
    String findKpddmByGsdm(String gsdm);

    @Query(nativeQuery = true,value = "select * from t_skp where gsdm=?2 and kpddm=?1 and yxbz='1' ")
    Skp findOneByKpddmAndGsdm(String kpddm,String gsdm);

    @Query(nativeQuery = true,value = "select * from t_skp where id=?1 and yxbz='1' ")
    Skp findOneById(Integer id);

    @Query(nativeQuery = true,value = "select * from t_skp where gsdm=?1 and xfid=?2 and yxbz='1' ")
    Skp findOneByGsdmAndXfsh(String gsdm,Integer xfid);
}

