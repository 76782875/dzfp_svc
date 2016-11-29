package com.rjxx.taxeasy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.MkglJpaDao;
import com.rjxx.taxeasy.dao.MkglMapper;
import com.rjxx.taxeasy.domains.Mkgl;
import com.rjxx.taxeasy.vo.Mkvo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Oct 13 10:51:59 CST 2016
 *
 * @ZhangBing
 */ 
@Service
public class MkglService {

    @Autowired
    private MkglJpaDao mkglJpaDao;

    @Autowired
    private MkglMapper mkglMapper;

    public Mkgl findOne(int id) {
        return mkglJpaDao.findOne(id);
    }

    public void save(Mkgl mkgl) {
        mkglJpaDao.save(mkgl);
    }

    public void save(List<Mkgl> mkglList) {
        mkglJpaDao.save(mkglList);
    }
    
    public void update(Map params){
    	mkglMapper.update(params);
    }
    
    public void destory(Map params){
    	mkglMapper.destory(params);;
    }
    
    public void updateSort(Map params){
    	mkglMapper.updateSort(params);
    }
    
    public Mkgl findOneByParams(Map params) {
        return mkglMapper.findOneByParams(params);
    }

    public List<Mkvo> findAllConnect(Map params){
    	return mkglMapper.findAllConnect(params);
    }
    
    public List<Mkgl> findAllByParams(Map params) {
        return mkglMapper.findAllByParams(params);
    }

    public List<Mkgl> findByPage(Pagination pagination) {
        return mkglMapper.findByPage(pagination);
    }
    
  //新增时查询区块内容是否存在
    public Mkgl findQkExist(Map params){
    	return mkglMapper.findQkExist(params);
    }
  //修改时查询区块内容是否存在
    public Mkgl findQkExistUpdate(Map params){
    	return mkglMapper.findQkExistUpdate(params);
    }

}

