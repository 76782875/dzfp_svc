package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.config.RabbitmqUtils;
import com.rjxx.taxeasy.dao.DlclbJpaDao;
import com.rjxx.taxeasy.dao.DlclbMapper;
import com.rjxx.taxeasy.domains.Dlclb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 23 18:15:39 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class DlclbService {

    @Autowired
    private DlclbJpaDao dlclbJpaDao;

    @Autowired
    private DlclbMapper dlclbMapper;

    @Autowired
    private RabbitmqUtils rabbitmqSend;

    public Dlclb findOne(int id) {
        return dlclbJpaDao.findOne(id);
    }

    public void save(Dlclb dlclb) {
        dlclbJpaDao.save(dlclb);
    }

    public void save(List<Dlclb> dlclbList) {
        dlclbJpaDao.save(dlclbList);
    }

    public Dlclb findOneByParams(Map params) {
        return dlclbMapper.findOneByParams(params);
    }

    public List<Dlclb> findAllByParams(Map params) {
        return dlclbMapper.findAllByParams(params);
    }

    public List<Dlclb> findByPage(Pagination pagination) {
        return dlclbMapper.findByPage(pagination);
    }

    /**
     * 向队列中放入需要处理的数据
     * @param dlmc
     * @param key 待处理表中业务数据id值
     * @param clcs 处理次数，处理次数大于clls不再放入队列
     * //@param ywkey t_dlclb中id值，对应不同的业务操作
     */
    public void sendQueueMessage(String dlmc,String key,int clcs/*,int ywkey*/){
        try {
            rabbitmqSend.sendMsg(dlmc, "", key + "-"+clcs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断对应的数据是否需要放入队列中
     * @param message
     * @param dlmc
     * @result boolean
     *
     */
    public boolean isInputQueue(String dlmc,String message){
        Map params = new HashMap();
        params.put("dlmc",dlmc);
        Dlclb dlclb = dlclbMapper.findOneByParams(params);
        if(null !=dlclb && !dlclb.equals("")){
            String ywkey = dlclb.getYwkey();
            if(null !=ywkey && !ywkey.equals("")){
                String array[] = ywkey.split(",");
                for(int i=0;i<array.length;i++){
                    String tmp = array[i];
                    if(message.equals(tmp)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

