package com.rjxx.taxeasy.dao;

import java.util.List;
import java.util.Map;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.taxeasy.domains.Rabbitmq;
import com.rjxx.taxeasy.vo.ShMqInfo;

/**
 * Created by Administrator on 2016/10/9.
 */
@MybatisRepository
public interface RabbitmqMapper {

    public Rabbitmq findByXfid(int xfid);
    
    public List<ShMqInfo> findAll(Map params);
    public List<ShMqInfo> findByParams(Map params);

}
