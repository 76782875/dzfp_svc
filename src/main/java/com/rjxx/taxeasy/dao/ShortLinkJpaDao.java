package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.ShortLink;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Jun 27 09:39:37 CST 2018
 *
 * @ZhangBing
 */ 
public interface ShortLinkJpaDao extends CrudRepository<ShortLink, Integer> {

    @Query(nativeQuery = true, value = "select * from t_short_link where short_link=?1 and use_mark='1'")
    ShortLink findOneByShortLink(String short_link);

    @Query(nativeQuery = true, value = "select * from t_short_link where normal_link=?1 and use_mark='1' order by  create_date desc limit  1")
    ShortLink findOneByNormalLink(String normal_link);
}

