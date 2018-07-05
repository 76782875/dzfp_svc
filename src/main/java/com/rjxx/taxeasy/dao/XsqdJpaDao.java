package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Xsqd;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Jul 03 16:36:36 CST 2018
 *
 * @ZhangBing
 */ 
public interface XsqdJpaDao extends CrudRepository<Xsqd, Integer> {

    @Query(nativeQuery = true, value = "select * from t_xsqd where orderTypeName=?1 and channel=?2 and platformName=?3 and use_mark='1' ")
    Xsqd findByOrderChannelPla(String orderTypeName,String channel ,String platformName);
}

