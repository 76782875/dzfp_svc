package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Pp;
import com.rjxx.taxeasy.domains.Xf;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Administrator on 2016/10/9.
 */
public interface XfJpaDao extends CrudRepository<Xf,Integer> {

    @Query(nativeQuery = true, value = "select * from t_xf where gsdm=?1 and yxbz='1'")
    Xf findOneByGsdm(String gsdm);

    @Query(nativeQuery = true, value = "select * from t_xf where id=?1 and yxbz='1'")
    Xf findOneById(Integer id);
}
