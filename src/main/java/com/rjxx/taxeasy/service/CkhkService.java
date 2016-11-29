package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.CkhkJpaDao;
import com.rjxx.taxeasy.dao.CkhkMapper;
import com.rjxx.taxeasy.domains.Ckhk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri Nov 04 16:45:28 CST 2016
 *
 * @WangYong
 */ 
@Service
public class CkhkService {

    @Autowired
    private CkhkJpaDao ckhkJpaDao;

    @Autowired
    private CkhkMapper ckhkMapper;

    public Ckhk findOne(int id) {
        return ckhkJpaDao.findOne(id);
    }

    public void save(Ckhk ckhk) {
        ckhkJpaDao.save(ckhk);
    }

    public void save(List<Ckhk> ckhkList) {
        ckhkJpaDao.save(ckhkList);
    }

    public Ckhk findOneByParams(Map params) {
        return ckhkMapper.findOneByParams(params);
    }

    public List<Ckhk> findAllByParams(Map params) {
        return ckhkMapper.findAllByParams(params);
    }

    public List<Ckhk> findByPage(Pagination pagination) {
        return ckhkMapper.findByPage(pagination);
    }
    
    public void updateZtbz(Map params){
    	ckhkMapper.updateZtbz(params);
    }

}

