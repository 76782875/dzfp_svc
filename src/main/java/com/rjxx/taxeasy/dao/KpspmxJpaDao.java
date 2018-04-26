package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Kpspmx;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri Oct 21 09:52:47 CST 2016
 *
 * @ZhangBing
 */ 
public interface KpspmxJpaDao extends CrudRepository<Kpspmx, Integer> {


    @Query(nativeQuery = true, value = "select * from t_kpspmx where kplsh=?1")
    List<Kpspmx> findByKplsh(Integer kplsh);
}

