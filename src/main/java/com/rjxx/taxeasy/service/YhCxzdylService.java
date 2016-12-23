package com.rjxx.taxeasy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.YhCxzdylJpaDao;
import com.rjxx.taxeasy.dao.YhCxzdylMapper;
import com.rjxx.taxeasy.domains.YhCxzdyl;
import com.rjxx.taxeasy.vo.ZdylVo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Dec 21 18:10:14 CST 2016
 *
 * @ZhangBing
 */ 
@Service
public class YhCxzdylService {

    @Autowired
    private YhCxzdylJpaDao yhCxzdylJpaDao;

    @Autowired
    private YhCxzdylMapper yhCxzdylMapper;

    public YhCxzdyl findOne(int id) {
        return yhCxzdylJpaDao.findOne(id);
    }

    public void save(YhCxzdyl yhCxzdyl) {
        yhCxzdylJpaDao.save(yhCxzdyl);
    }

    public void save(List<YhCxzdyl> yhCxzdylList) {
        yhCxzdylJpaDao.save(yhCxzdylList);
    }

    public YhCxzdyl findOneByParams(Map params) {
        return yhCxzdylMapper.findOneByParams(params);
    }

    public List<ZdylVo> findAllByParams(Map params) {
        return yhCxzdylMapper.findAllByParams(params);
    }

    public List<YhCxzdyl> findByPage(Pagination pagination) {
        return yhCxzdylMapper.findByPage(pagination);
    }

}

