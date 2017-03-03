package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.JyspmxJpaDao;
import com.rjxx.taxeasy.dao.JyspmxMapper;
import com.rjxx.taxeasy.domains.Jyspmx;
import com.rjxx.taxeasy.domains.Kpspmx;
import com.rjxx.taxeasy.vo.JyspmxDecimal;
import com.rjxx.taxeasy.vo.JyspmxDecimal2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 10 13:25:11 CST 2016
 *
 * @ZhangBing
 */
@Service
public class JyspmxService {

    @Autowired
    private JyspmxJpaDao jyspmxJpaDao;

    @Autowired
    private JyspmxMapper jyspmxMapper;

    public Jyspmx findOne(int id) {
        return jyspmxJpaDao.findOne(id);
    }

    public void save(Jyspmx jyspmx) {
        jyspmxJpaDao.save(jyspmx);
    }

    public void save(List<Jyspmx> jyspmxList) {
        jyspmxJpaDao.save(jyspmxList);
    }

    public Jyspmx findOneByParams(Jyspmx jyspmx) {
        return jyspmxMapper.findOneByParams(jyspmx);
    }

    public List<Jyspmx> findAllByParams(Jyspmx jyspmx) {
        return jyspmxMapper.findAllByParams(jyspmx);
    }
    public List<JyspmxDecimal> findBySql(Jyspmx jyspmx) {
        return jyspmxMapper.findBySql(jyspmx);
    }
    public List<Jyspmx> findByPage(Pagination pagination) {
        return jyspmxMapper.findByPage(pagination);
    }
    
    public void deleteAll(List<Jyspmx> list) {
    	 jyspmxJpaDao.deleteAll();
	}
    
    public void delete(Jyspmx jyspmx) {
   	 jyspmxJpaDao.delete(jyspmx.getId());
	}

    /**
     * 根据单据号查找
     *
     * @param djhList
     * @return
     */
    public List<Jyspmx> findByDjhList(List<Integer> djhList) {
        return jyspmxMapper.findByDjhList(djhList);
    }

    public void delete(List<Jyspmx> jyspmxList) {
        jyspmxJpaDao.delete(jyspmxList);
    }
    
    public List<JyspmxDecimal> getNeedToKP2(Map params){
    	return jyspmxMapper.getNeedToKP2(params);
    }
    
    public List<JyspmxDecimal2> getNeedToKP3(Map params){
    	return jyspmxMapper.getNeedToKP3(params);
    }

    public List<Jyspmx> findAll(Map params) {
        return jyspmxMapper.findAll(params);
    }

}

