package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Xf;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Administrator on 2016/10/9.
 */
public interface XfJpaDao extends CrudRepository<Xf,Integer> {
    Xf findOneByGsdm(String gsdm);
}
