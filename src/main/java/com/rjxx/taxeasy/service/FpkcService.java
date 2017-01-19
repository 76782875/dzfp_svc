package com.rjxx.taxeasy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.FpkcJpaDao;
import com.rjxx.taxeasy.dao.FpkcMapper;
import com.rjxx.taxeasy.domains.Fpkc;
import com.rjxx.taxeasy.vo.Fpkcvo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri Oct 14 14:05:54 CST 2016
 *
 * @ZhangBing
 */ 
@Service
public class FpkcService {

    @Autowired
    private FpkcJpaDao fpkcJpaDao;

    @Autowired
    private FpkcMapper fpkcMapper;

    public Fpkc findOne(int id) {
        return fpkcJpaDao.findOne(id);
    }

    public void save(Fpkc fpkc) {
        fpkcJpaDao.save(fpkc);
    }

    public void save(List<Fpkc> fpkcList) {
        fpkcJpaDao.save(fpkcList);
    }
    public void update(Map params){
    	fpkcMapper.update(params);
    }
    
    public void destory(Map params){
    	fpkcMapper.destory(params);
    }
    
    public Fpkc findOneByParams(Map params) {
        return fpkcMapper.findOneByParams(params);
    }
    //查询库存发票
    public List<Fpkcvo> findAllByParams(Map params) {
        return fpkcMapper.findAllByParams(params);
    }
    //查询库存中的已开发票量
    public Fpkcvo findKyl(Map params) {
        return fpkcMapper.findKyl(params);
    }
    //查询发票代码段
    public List<Fpkc> findFphmd(Map params) {
        return fpkcMapper.findFphmd(params);
    }
    
  //修改时查询发票代码段
    public List<Fpkc> findFphmdxg(Map params) {
        return fpkcMapper.findFphmdxg(params);
    }

    public List<Fpkcvo> findByPage(Pagination pagination) {
        return fpkcMapper.findByPage(pagination);
    }
    //查询开票点
    public List<Fpkcvo> findKpd(Map params){
    	return fpkcMapper.findKpd(params);
    }
    //定时任务查询库存
    public List<Fpkcvo> findKcfpl(){
    	return fpkcMapper.findKcfpl();
    }
    //定时任务更新发票库存量
    public void updateFpkcl(Map params){
    	fpkcMapper.updateFpkcl(params);
    }
    
  //更具当前用户的订阅信息查询库余量
    public Fpkcvo findZyKyl(Map params){
    	return fpkcMapper.findZyKyl(params);
    }
    
    //查询发票库存监控
    public List<Fpkcvo> findKcjkByPage(Pagination pagination){
    	return fpkcMapper.findKcjkByPage(pagination);
    }

}

