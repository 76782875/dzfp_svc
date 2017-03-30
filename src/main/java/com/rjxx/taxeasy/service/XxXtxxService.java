package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.XxXtxxJpaDao;
import com.rjxx.taxeasy.dao.XxXtxxMapper;
import com.rjxx.taxeasy.domains.XxXtxx;
import com.rjxx.taxeasy.vo.Xtxxvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Mar 23 14:23:47 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class XxXtxxService {

    @Autowired
    private XxXtxxJpaDao xxXtxxJpaDao;

    @Autowired
    private XxXtxxMapper xxXtxxMapper;

    public XxXtxx findOne(int id) {
        return xxXtxxJpaDao.findOne(id);
    }

    public void save(XxXtxx xxXtxx) {
        xxXtxxJpaDao.save(xxXtxx);
    }

    public void save(List<XxXtxx> xxXtxxList) {
        xxXtxxJpaDao.save(xxXtxxList);
    }

    public XxXtxx findOneByParams(Map params) {
        return xxXtxxMapper.findOneByParams(params);
    }

    public List<Xtxxvo> findAllByParams(Map params) {
        return xxXtxxMapper.findAllByParams(params);
    }

    public List<XxXtxx> findByPage(Pagination pagination) {
        return xxXtxxMapper.findByPage(pagination);
    }
    /**
     * 保存未读系统消息
     * @param title:消息标题
     * @param content：消息内容
     * @param xxlx:消息类型，参照t_dy_dyzl
     * @param yhid:消息发送目的账户
     * */
    public void saveXx(String title,String content,String xxlx,Integer yhid){
    	XxXtxx item = new XxXtxx();
    	item.setTitle(title);
    	item.setContent(content);
    	item.setXxlx(xxlx);
    	item.setYdbz("0");
    	item.setYhid(yhid);
    	item.setYxbz("1");
    	item.setLrsj(new Date());
    	save(item);
    }
    
    public void updateYdbz(Map params){
    	xxXtxxMapper.updateYdbz(params);
    }
    
    public void delMany(Map params){
    	xxXtxxMapper.delMany(params);
    }
    
    public void allRead(Map params){
    	xxXtxxMapper.allRead(params);
    }
    
    public void allNotRead(Map params){
    	xxXtxxMapper.allNotRead(params);
    }

}

