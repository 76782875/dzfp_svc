package com.rjxx.taxeasy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.KpspmxJpaDao;
import com.rjxx.taxeasy.dao.KpspmxMapper;
import com.rjxx.taxeasy.domains.Jyspmx;
import com.rjxx.taxeasy.domains.Kpspmx;
import com.rjxx.taxeasy.vo.Kpspmxvo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri Oct 21 09:52:47 CST 2016
 *
 * @ZhangBing
 */ 
@Service
public class KpspmxService {

    @Autowired
    private KpspmxJpaDao kpspmxJpaDao;

    @Autowired
    private KpspmxMapper kpspmxMapper;

    public Kpspmx findOne(int id) {
        return kpspmxJpaDao.findOne(id);
    }

    public void save(Kpspmx kpspmx) {
        kpspmxJpaDao.save(kpspmx);
    }

    public void save(List<Kpspmx> kpspmxList) {
        kpspmxJpaDao.save(kpspmxList);
    }

    public Kpspmx findOneByParams(Map params) {
        return kpspmxMapper.findOneByParams(params);
    }

    public List<Kpspmxvo> findAllByParams(Map params) {
        return kpspmxMapper.findAllByParams(params);
    }

    public List<Kpspmx> findByPage(Pagination pagination) {
        return kpspmxMapper.findByPage(pagination);
    }
    
    //根据开票流水号跟明细序号查询明细
    public Kpspmxvo findMxByParams(Map params){
    	return kpspmxMapper.findMxByParams(params);
    }
    
    //部分红冲更新
    public void update(Map params){
    	kpspmxMapper.update(params);
    }
    
    //查询这张发票的库存金额
    public Kpspmxvo findKhcje(Map params){
    	return kpspmxMapper.findKhcje(params);
    }
    
    public List<Kpspmx> findMxList(Map params){
    	return kpspmxMapper.findMxList(params);
    }
 
    public List<Kpspmx> findMxNewList(Map params){
    	return kpspmxMapper.findMxNewList(params);
    }
    
    public List<Kpspmx> findMxNewByParams(Map params){
    	return kpspmxMapper.findMxNewByParams(params);
    }
    
    public void deleteAll(List<Kpspmx> list) {
    	kpspmxJpaDao.deleteAll();
	}
   
    public void delete(Kpspmx kpspmx) {
	   kpspmxJpaDao.delete(kpspmx.getId());
	}

    public List<Kpspmxvo> findSkMxList(Map params) {
        return kpspmxMapper.findSkMxList(params);
    }

    public List<Kpspmx> findMx2List(Map params){
        return kpspmxMapper.findMx2List(params);
    }

}

