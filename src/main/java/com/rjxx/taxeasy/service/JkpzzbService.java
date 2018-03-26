package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.JkpzzbJpaDao;
import com.rjxx.taxeasy.dao.JkpzzbMapper;
import com.rjxx.taxeasy.domains.Jkpzzb;
import com.rjxx.taxeasy.vo.JkpzVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Mar 20 16:09:03 CST 2018
 *
 * @ZhangBing
 */ 
@Service
public class JkpzzbService {

    @Autowired
    private JkpzzbJpaDao jkpzzbJpaDao;

    @Autowired
    private JkpzzbMapper jkpzzbMapper;

    public Jkpzzb findOne(int id) {
        return jkpzzbJpaDao.findOne(id);
    }

    public void save(Jkpzzb jkpzzb) {
        jkpzzbJpaDao.save(jkpzzb);
    }

    public void save(List<Jkpzzb> jkpzzbList) {
        jkpzzbJpaDao.save(jkpzzbList);
    }

    public Jkpzzb findOneByParams(Map params) {
        return jkpzzbMapper.findOneByParams(params);
    }

    public List<Jkpzzb> findAllByParams(Map params) {
        return jkpzzbMapper.findAllByParams(params);
    }

    public List<Jkpzzb> findByPage(Pagination pagination) {
        return jkpzzbMapper.findByPage(pagination);
    }

    public List<JkpzVo> findByMbId(Integer mbid){
        return jkpzzbMapper.findByMbId(mbid);
    }
}

