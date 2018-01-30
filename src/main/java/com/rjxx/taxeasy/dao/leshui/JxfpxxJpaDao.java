package com.rjxx.taxeasy.dao.leshui;

import com.rjxx.taxeasy.domains.leshui.Jxfpxx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wangyahui on 2018/1/29 0029.
 */
public interface JxfpxxJpaDao extends JpaRepository<Jxfpxx,Integer>{
    @Query(nativeQuery = true,value = "select * from t_jxfpxx where fpdm=?1 and fphm=?2 and yxbz='1' ")
    Jxfpxx findByFpdmAndFphm(String fpdm, String fphm);
}
