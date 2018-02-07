package com.rjxx.taxeasy.dao.leshui;

import com.rjxx.taxeasy.domains.leshui.Fpcyjl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wangyahui on 2018/1/25 0025.
 */
public interface FpcyjlJpaDao extends JpaRepository<Fpcyjl,Integer> {

    @Query(nativeQuery = true,value = "select * from t_fpcy_jl where fpcyid=?1 and gsdm=?2 order by cyrq desc limit 1")
    Fpcyjl findOneByFpcyIdAndGsdm(Integer fpcyid,String gsdm);


    @Query(nativeQuery = true,value = "select * from t_fpcy_jl where fpcyid=?1  order by cyrq desc ")
    List<Fpcyjl> findOneByFpcyId(Integer fpcyid);

    @Query(nativeQuery = true,value = "select * from t_fpcy_jl where fpcyid=?1  order by cyrq desc limit 1 ")
    Fpcyjl findOneBy1FpcyId(Integer fpcyid);

    @Query(nativeQuery = true,value = "select count(1) from t_fpcy_jl where fpcyid=?1 ")
    Integer findCountByFpcyId(Integer fpcyId);
}
