package com.rjxx.taxeasy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.YhdykJpaDao;
import com.rjxx.taxeasy.dao.YhdykMapper;
import com.rjxx.taxeasy.domains.Yhdyk;
import com.rjxx.taxeasy.vo.Fpnum;
import com.rjxx.taxeasy.vo.Yhdykvo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Jan 17 16:56:16 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class YhdykService {

    @Autowired
    private YhdykJpaDao yhdykJpaDao;

    @Autowired
    private YhdykMapper yhdykMapper;

    public Yhdyk findOne(int id) {
        return yhdykJpaDao.findOne(id);
    }

    public void save(Yhdyk yhdyk) {
        yhdykJpaDao.save(yhdyk);
    }

    public void save(List<Yhdyk> yhdykList) {
        yhdykJpaDao.save(yhdykList);
    }

    public Yhdyk findOneByParams(Map params) {
        return yhdykMapper.findOneByParams(params);
    }

    public List<Yhdyk> findAllByParams(Map params) {
        return yhdykMapper.findAllByParams(params);
    }

    public List<Yhdykvo> findByPage(Pagination pagination) {
        return yhdykMapper.findByPage(pagination);
    }
    
    public Yhdyk findDyxx(Map params){
    	return yhdykMapper.findDyxx(params);
    }
    
    public void updateYxbz(Map params){
    	yhdykMapper.updateYxbz(params);
    }
    
    public List<Yhdyk> findYhdy(Map params){
    	return yhdykMapper.findYhdy(params);
    }
    
    public Fpnum findTjsj(Map params){
    	return yhdykMapper.findTjsj(params);
    }

}

