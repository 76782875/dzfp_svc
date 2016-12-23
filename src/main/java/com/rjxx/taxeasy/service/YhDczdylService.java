package com.rjxx.taxeasy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.YhDczdylJpaDao;
import com.rjxx.taxeasy.dao.YhDczdylMapper;
import com.rjxx.taxeasy.domains.YhDczdyl;
import com.rjxx.taxeasy.vo.DczydlVo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Dec 22 16:30:46 CST 2016
 *
 * @ZhangBing
 */ 
@Service
public class YhDczdylService {

    @Autowired
    private YhDczdylJpaDao yhDczdylJpaDao;

    @Autowired
    private YhDczdylMapper yhDczdylMapper;

    public YhDczdyl findOne(int id) {
        return yhDczdylJpaDao.findOne(id);
    }

    public void save(YhDczdyl yhDczdyl) {
        yhDczdylJpaDao.save(yhDczdyl);
    }

    public void save(List<YhDczdyl> yhDczdylList) {
        yhDczdylJpaDao.save(yhDczdylList);
    }

    public YhDczdyl findOneByParams(Map params) {
        return yhDczdylMapper.findOneByParams(params);
    }

    public List<DczydlVo> findAllByParams(Map params) {
        return yhDczdylMapper.findAllByParams(params);
    }

    public List<YhDczdyl> findByPage(Pagination pagination) {
        return yhDczdylMapper.findByPage(pagination);
    }

}

