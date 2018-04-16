package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Cszb;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Nov 02 13:27:26 CST 2016
 *
 * @ZhangBing
 */ 
public interface CszbJpaDao extends CrudRepository<Cszb, Integer> {


    @Query(nativeQuery = true,value = "select * from t_cszb a,t_csb b where a.csid = b.id and a.yxbz='1' and b.yxbz='1' and a.csid=?1")
    List<Cszb> findByCsid(Integer csid);

    @Query(nativeQuery = true,value = "select * from t_cszb  where yxbz='1' and id=?1")
    Cszb finfOneById(Integer id);

    @Query(nativeQuery = true,value = "select * from t_cszb  where yxbz='1' and csid=?1 and gsdm=?2 and xfid=?3 and kpdid=?4 ")
    Cszb findOneByCsidAndGsdmAndXfAndSkp(Integer csid,String gsdm,Integer xfid,Integer kpdid);

    @Query(nativeQuery = true,value = "select * from t_cszb  where yxbz='1' and csid=?1 and gsdm=?2 and xfid=?3 and kpdid is null")
    Cszb findOneByCsidAndGsdmAndXf(Integer csid,String gsdm,Integer xfid);


    @Query(nativeQuery = true,value = "select * from t_cszb  where yxbz='1' and  csid=?1 and gsdm=?2 and csz=?3")
    List<Cszb> findByGsdmAndCszAndCsid(Integer csid,String gsdm,String csz);

    @Query(nativeQuery = true,value = "select * from t_cszb  where yxbz='1' and  csid=?1 and csz=?2")
    List<Cszb> findByCsidAndCsz(Integer csid,String csz);


    @Query(nativeQuery = true,value = "select * from t_cszb  where yxbz='1' and csid=?1 and gsdm=?2 and kpdid is null and xfid is null")
    Cszb findOneByCsidAndGsdm(Integer csid,String gsdm);
}

