package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.WxFpxx;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Administrator on 2017/8/17 0017.
 */
public interface WxfpxxJpaDao extends CrudRepository<WxFpxx,String>{

    @Query(nativeQuery = true,value = "select * from wx_fpxx where orderno=?1 and openid=?2")
   WxFpxx findOneByOrderNo(String orderNo,String openId);
}
