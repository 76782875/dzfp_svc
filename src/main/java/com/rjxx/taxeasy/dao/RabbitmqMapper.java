package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.taxeasy.domains.Rabbitmq;

/**
 * Created by Administrator on 2016/10/9.
 */
@MybatisRepository
public interface RabbitmqMapper {

    public Rabbitmq findByXfid(int xfid);

}
