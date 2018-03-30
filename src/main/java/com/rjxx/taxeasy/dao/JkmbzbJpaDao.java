package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Jkmbzb;
import com.rjxx.taxeasy.vo.JkpzVo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Mar 20 16:09:03 CST 2018
 *
 * @ZhangBing
 */ 
public interface JkmbzbJpaDao extends CrudRepository<Jkmbzb, Integer> {

    @Query(nativeQuery = true,value = "select * from t_jkmbzb where mbid=?1 ")
    List<Jkmbzb> findListByMbid(Integer mbid);

    @Query(nativeQuery = true,value = "select a.*, b.pzcsm,c.csm,c.cszff,b.pzcsmc from t_jkmbzb a ,t_jkpzb b, t_jkpzdmb c where a.mbid=?1 and a.pzbid = b.id AND  a.cszffid = c.id AND c.pzbid = b.id")
    List<JkpzVo> findListVoByMbid(Integer mbid);


    @Query(nativeQuery = true,value = "select * from t_jkmbzb where mbid=?1 and pzbid=?2 ")
    Jkmbzb findByMbidAndPzbid(Integer mbid,Integer pzbid);
}

