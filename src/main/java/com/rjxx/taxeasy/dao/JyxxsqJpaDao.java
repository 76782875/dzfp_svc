package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Jyxxsq;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Jan 04 13:33:08 CST 2017
 *
 * @ZhangBing
 */
@Repository
public interface JyxxsqJpaDao extends CrudRepository<Jyxxsq, Integer> {
    @Query(nativeQuery = true,value = "select * from t_jyxxsq where gsdm=?1 and ddh=?2 and fpzldm=?3 and yxbz='1' ")
    Jyxxsq findOneByGsdmAndDdhAndFpzldm(String gsdm, String ddh, String fpzldm);

    @Query(nativeQuery = true,value = "select * from t_jyxxsq where khh=?1 and gsdm=?2 and yxbz='1' ")
    List<Jyxxsq> findByKhhAndGsdm(String khh, String gsdm);

    @Query(nativeQuery = true,value = "select * from t_jyxxsq where jylsh=?1 and gsdm=?2 and yxbz='1' ")
    Jyxxsq findOneByJylshAndGsdm(String jylsh, String gsdm);
}

