package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Yh;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Dec 01 12:47:48 CST 2016
 *
 * @ZhangBing
 */ 
public interface YhJpaDao extends CrudRepository<Yh, Integer> {
    @Query(nativeQuery = true,value = "select yhmm from t_yh where dlyhid=?1")
    String findYhmmByDlyhid(String dlyhid);

    @Query(nativeQuery = true,value = "select gsdm from t_yh where dlyhid=?1")
    String findGsdmByDlyhid(String dlyhid);
}

