package com.rjxx.taxeasy.dao.leshui;

import com.rjxx.taxeasy.domains.leshui.Jxywjl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wangyahui on 2018/2/1 0001.
 */
public interface JxywjlJpaDao extends JpaRepository<Jxywjl,Integer> {
    @Query(nativeQuery = true,value = "select * from t_jxyw_jl where gfid=?1 and gsdm=?2 and kssj is not null and jssj is not null order by lrsj desc limit 1")
    Jxywjl findOneByGfId(Integer gfid, String gsdm);
}
