package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.JkmbzbJpaDao;
import com.rjxx.taxeasy.dao.JkmbzbMapper;
import com.rjxx.taxeasy.domains.Jkmbzb;
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
public class JkmbzbService {

    @Autowired
    private JkmbzbJpaDao jkmbzbJpaDao;

    @Autowired
    private JkmbzbMapper jkmbzbMapper;

    public Jkmbzb findOne(int id) {
        return jkmbzbJpaDao.findOne(id);
    }

    public void save(Jkmbzb jkmbzb) {
        jkmbzbJpaDao.save(jkmbzb);
    }

    public void save(List<Jkmbzb> jkmbzbList) {
        jkmbzbJpaDao.save(jkmbzbList);
    }

    public Jkmbzb findOneByParams(Map params) {
        return jkmbzbMapper.findOneByParams(params);
    }

    public List<Jkmbzb> findAllByParams(Map params) {
        return jkmbzbMapper.findAllByParams(params);
    }

    public List<JkpzVo> findByPage(Pagination pagination) {
        return jkmbzbMapper.findByPage(pagination);
    }

    public List<JkpzVo> findByMbId(Map map){
        return jkmbzbMapper.findByMbId(map);
    }
}

