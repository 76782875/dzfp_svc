package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Qcc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Administrator on 2017-11-29.
 */
public interface QccJpaDao extends CrudRepository<Qcc,Integer> {
    @Query(nativeQuery = true,value = "select * from t_qcc where id=?1")
    Qcc findOneById(Integer id);
}
