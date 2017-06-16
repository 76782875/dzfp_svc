package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Jyls;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.vo.Fptqvo;
import com.rjxx.taxeasy.vo.FpxxVo;
import com.rjxx.taxeasy.vo.YjfsxxVo;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 10 13:24:59 CST 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface JylsMapper {

    public Jyls findOneByParams(Jyls jyls);

    public List<Jyls> findAllByParams(Jyls jyls);
    public List<Jyls> findBySql(Map map);

    public List<Jyls> findByPage(Pagination pagination);

    /**
     * 根据交易流水号查找
     *
     * @param params
     * @return
     */
    public List<Jyls> findByMapParams(Map params);
    
    public List<Jyls> findAll(Map params);
    
    //根据单据号查找
    public Jyls findJylsByDjh(Map params);
        
    public void updateClzt(Map params);
    
    public List<Fptqvo> fptqcx(Pagination pagination);
    
    public List<YjfsxxVo> findYjfs(Map params);
    
    public List<FpxxVo> findFpxx(Map params);

    public  List<Kpls> findByTqm(Map params);
    
    public Jyls findOne(Map params);
    
    public void updateJshj(Map params);
    
    public List<Jyls> findAllByJylsh(Map params);
    
    public List<Jyls> findJylsFsdx();
    
    public void updateDxbz(Map params);
    
    public List<Jyls> findFsdxSqj();
    
    public List<Jyls> findAllJyls(Map params);

    public List<Kpls> findBykhh(Map params);
}

