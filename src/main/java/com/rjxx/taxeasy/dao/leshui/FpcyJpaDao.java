package com.rjxx.taxeasy.dao.leshui;

import com.rjxx.taxeasy.domains.leshui.Fpcy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by wangyahui on 2018/1/25 0025.
 */
public interface FpcyJpaDao extends JpaRepository<Fpcy,Integer> {
    @Query(nativeQuery = true,value = "select * from t_fpcy where fpdm=?1 and fphm=?2 and yxbz ='1' ")
    Fpcy findOneByFpdmAndFphm(String fpdm, String fphm);
}
