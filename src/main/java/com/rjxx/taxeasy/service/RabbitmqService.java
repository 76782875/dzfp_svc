package com.rjxx.taxeasy.service;

import com.rjxx.taxeasy.dao.RabbitmqMapper;
import com.rjxx.taxeasy.domains.Rabbitmq;
import com.rjxx.taxeasy.vo.ShMqInfo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/10/9.
 */
@Service
public class RabbitmqService {

    @Autowired
    private RabbitmqMapper rabbitmqMapper;

    public Rabbitmq findByXfid(int xfid) {
        return rabbitmqMapper.findByXfid(xfid);
    }
    
    public List<ShMqInfo> findAll(Map params){
    	return rabbitmqMapper.findAll(params);
    }

    public List<ShMqInfo> findByParams(Map params){
    	return rabbitmqMapper.findByParams(params);
    }
}
