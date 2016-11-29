package com.rjxx.taxeasy.service;

import com.rjxx.taxeasy.dao.RabbitmqMapper;
import com.rjxx.taxeasy.domains.Rabbitmq;
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

}
