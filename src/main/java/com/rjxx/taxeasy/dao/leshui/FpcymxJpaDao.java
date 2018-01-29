package com.rjxx.taxeasy.dao.leshui;

import com.rjxx.taxeasy.domains.leshui.Fpcymx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wangyahui on 2018/1/25 0025.
 */
public interface FpcymxJpaDao extends JpaRepository<Fpcymx,Integer> {
    @Query(nativeQuery = true,value = "select * from t_fpcy_mx where fpcyid=?1 order by id desc ")
    List<Fpcymx> findOneByFpcyId(Integer fpcyid);
}
