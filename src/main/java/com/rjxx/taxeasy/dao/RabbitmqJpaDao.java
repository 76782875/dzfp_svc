package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Rabbitmq;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Administrator on 2016/10/9.
 */
public interface RabbitmqJpaDao extends CrudRepository<Rabbitmq, Integer> {
}
