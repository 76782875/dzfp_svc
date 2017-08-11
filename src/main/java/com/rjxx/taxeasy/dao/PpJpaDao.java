package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Pp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Dec 12 17:15:27 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
public interface PpJpaDao extends CrudRepository<Pp, Integer> {
    @Query(nativeQuery = true, value = "select * from t_pp where id=?1 and yxbz='1'")
    Pp findOneById(Integer id);
}

