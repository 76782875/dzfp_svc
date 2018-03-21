package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Xf;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9.
 */
public interface XfJpaDao extends CrudRepository<Xf,Integer> {

    @Query(nativeQuery = true, value = "select * from t_xf where id=?1 and yxbz='1'")
    Xf findOneById(Integer id);

    @Query(nativeQuery = true, value = "select * from t_xf where xfsh=?1 and yxbz='1'")
    List<Xf> findOneByXfsh(String xfsh);

    @Query(nativeQuery = true, value = "select * from t_xf where gsdm=?1 and yxbz='1'")
    List<Xf> findAllByGsdm(String gsdm);

    @Query(nativeQuery = true, value = "select * from t_xf where gsdm=?1 and yxbz='1'")
    Xf findOneByGsdm(String gsdm);

    @Query(nativeQuery = true, value = "select * from t_xf where xfsh=?1 and gsdm=?2 and yxbz='1'")
    Xf findOneByXfsh1(String xfsh,String gsdm);

}
