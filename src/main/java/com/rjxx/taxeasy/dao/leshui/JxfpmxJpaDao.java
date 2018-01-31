package com.rjxx.taxeasy.dao.leshui;

import com.rjxx.taxeasy.domains.leshui.Jxfpmx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wangyahui on 2018/1/29 0029.
 */
public interface JxfpmxJpaDao extends JpaRepository<Jxfpmx,Integer> {

    @Query(nativeQuery = true,value = "select * from t_jxfpmx where fplsh=?1 order by id desc ")
    List<Jxfpmx> findOneByFplsh(Integer fplsh);
}
