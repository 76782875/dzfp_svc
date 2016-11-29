package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.YhJpaDao;
import com.rjxx.taxeasy.dao.YhMapper;
import com.rjxx.taxeasy.domains.Yh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9.
 */
@Service
public class YhService {

    @Autowired
    private YhJpaDao yhJpaDao;

    @Autowired
    private YhMapper yhMapper;

    public Yh findOne(int id) {
        return yhJpaDao.findOne(id);
    }

    public void save(Yh yh) {
        yhJpaDao.save(yh);
    }

    public void save(List<Yh> yhList) {
        yhJpaDao.save(yhList);
    }

    public Yh findOneByParams(Yh yh) {
        return yhMapper.findOneByParams(yh);
    }

    public List<Yh> findAllByParams(Yh yh) {
        return yhMapper.findAllByParams(yh);
    }

    public List<Yh> findByPage(Pagination pagination) {
        return yhMapper.findByPage(pagination);
    }

    /**
     * 根据登录账号查找用户
     *
     * @param dlyhid
     * @return
     */
    public Yh findByDlyhid(String dlyhid) {
        Yh params = new Yh();
        params.setDlyhid(dlyhid);
        return findOneByParams(params);
    }

}
