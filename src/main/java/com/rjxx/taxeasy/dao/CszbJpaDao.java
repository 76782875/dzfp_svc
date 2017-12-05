package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Cszb;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Nov 02 13:27:26 CST 2016
 *
 * @ZhangBing
 */ 
public interface CszbJpaDao extends CrudRepository<Cszb, Integer> {


    @Query(nativeQuery = true,value = "select * from t_cszb a,t_csb b where a.csid = b.id and a.yxbz='1' and b.yxbz='1' and a.csid=?1")
    List<Cszb> findByCsid(Integer csid);
}

