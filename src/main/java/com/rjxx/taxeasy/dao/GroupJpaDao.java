package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Oct 13 14:35:28 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
public interface GroupJpaDao extends CrudRepository<Group, Integer> {
    @Query(nativeQuery = true, value = "select * from t_group where yxbz='1' and  yhid=?1 and skpid is not null and xfid is not null ")
    Group findOneByYhid(Integer yhid);
}

