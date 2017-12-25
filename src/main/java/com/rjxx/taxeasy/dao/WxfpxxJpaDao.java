package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.WxFpxx;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Administrator on 2017/8/17 0017.
 */
public interface WxfpxxJpaDao extends CrudRepository<WxFpxx,String>{

    @Query(nativeQuery = true,value = "select * from wx_fpxx where orderno=?1 and gsdm=?2")
    WxFpxx selsetByOrderNo(String orderNo,String gsdm);

    @Query(nativeQuery = true,value = "select * from wx_fpxx where code=?1")
    WxFpxx selectByCode(String code);

    @Query(nativeQuery = true,value = "select * from wx_fpxx where weixinorderNo=?1")
    WxFpxx selectByWeiXinOrderNo(String weixinorderNo);

    @Query(nativeQuery = true,value = "select * from wx_fpxx where authId=?1")
    WxFpxx selectByAuthId(String authId);
}
