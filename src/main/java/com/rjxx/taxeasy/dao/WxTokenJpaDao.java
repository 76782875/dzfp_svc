package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.WxToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017-09-18.
 */
@Repository
public interface WxTokenJpaDao extends CrudRepository<WxToken,Integer> {

    @Query(nativeQuery = true,value = "select * from wx_token where flag=?1")
    WxToken findByFlag(String flag);
}
