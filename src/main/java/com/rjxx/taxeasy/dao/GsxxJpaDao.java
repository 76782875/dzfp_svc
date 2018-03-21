package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Gsxx;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Nov 03 17:18:52 CST 2016
 *
 * @ZhangBing
 */ 
public interface GsxxJpaDao extends CrudRepository<Gsxx, Integer> {
    @Query(nativeQuery = true, value = "select * from t_gsxx where gsdm=?1")
    Gsxx findOneByGsdm(String gsdm);

    @Query(nativeQuery = true, value = "select * from t_gsxx where appkey=?1")
    Gsxx findOneByAppid(String appid);
}

