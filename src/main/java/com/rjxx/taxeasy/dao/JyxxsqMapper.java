package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Jyxxsq;
import com.rjxx.taxeasy.domains.Skp;
import com.rjxx.taxeasy.domains.Xf;
import com.rjxx.taxeasy.vo.JyxxsqVO;
import com.rjxx.taxeasy.vo.OrderCancelVo;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Jan 04 13:33:08 CST 2017
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface JyxxsqMapper {

    public Jyxxsq findOneByParams(Map params);

    public Jyxxsq findOneByTqmAndJshj(Map params);

    public List<Jyxxsq> findAllByTqms(Map params);

    public List<Jyxxsq> findAllByJylshs(Map params);
    
    public List<Jyxxsq> findAllByDdhs(Map params);
    
    public List<JyxxsqVO> findByPage(Pagination pagination);
    
    public List<Jyxxsq> findByPage1(Pagination pagination);

    public List<JyxxsqVO> findByPage2(Map params);

    public Integer findtotal(Map params);

    public Xf findXfExistByKpd(Map params);

    public void saveJyxxsq(Jyxxsq jyxxsq);
    
    public void addJyxxsqBatch(List<Jyxxsq> Jyxxsqs);
    
    public List<Jyxxsq> findByMapParams(Map params);

    public Jyxxsq findSjlyAndOpenidByMap(Map params);
    
    public List<JyxxsqVO> findYscByPage(Pagination pagination);

	public List<JyxxsqVO> findBykplscxPage( Map params);

    public Integer findBykplscxtotal(Map params);

    public List<JyxxsqVO> findBySqlsh(Map params);

    Xf findXfExistByXfsh(Map params);

    Skp findskpExistByXfid(Map tt);

    Jyxxsq findOneByJylsh(Map jyxxsqMap);

    public void updateGfxx(Map params);

    public Integer findJshjSum(Map params);

    public List<JyxxsqVO> findAllBykplscx( Map params);

    public List<OrderCancelVo> findAllCancelVoByDdh(Map params);

    public void updateJyxxsqTqm(Map params);
}

