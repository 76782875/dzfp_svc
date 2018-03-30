package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Jkmbb;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Mar 27 10:47:00 CST 2018
 *
 * @ZhangBing
 */

public interface JkmbbJpaDao extends CrudRepository<Jkmbb, Integer> {

    @Query(nativeQuery = true,value = "select * from t_jkmbb where id=?1 and yxbz='1'")
    Jkmbb findByid(Integer id);
}

