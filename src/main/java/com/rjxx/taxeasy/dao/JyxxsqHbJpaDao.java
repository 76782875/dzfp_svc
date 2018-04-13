package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.JyxxsqHb;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Apr 10 17:02:19 CST 2018
 *
 * @ZhangBing
 */ 
public interface JyxxsqHbJpaDao extends CrudRepository<JyxxsqHb, Integer> {

    @Query(nativeQuery = true,value = "select * from t_jyxxsq_hb  where yxbz='1' and  newsqlsh=?1 ")
    List<JyxxsqHb> findByNewsqlsh(Integer newsqlsh);
}

