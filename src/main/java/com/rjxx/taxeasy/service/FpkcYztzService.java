package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.FpkcYztzJpaDao;
import com.rjxx.taxeasy.dao.FpkcYztzMapper;
import com.rjxx.taxeasy.domains.FpkcYztz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri Apr 27 13:28:18 CST 2018
 *
 * @ZhangBing
 */ 
@Service
public class FpkcYztzService {

    @Autowired
    private FpkcYztzJpaDao fpkcYztzJpaDao;

    @Autowired
    private FpkcYztzMapper fpkcYztzMapper;

    public FpkcYztz findOne(int id) {
        return fpkcYztzJpaDao.findOne(id);
    }

    public void save(FpkcYztz fpkcYztz) {
        fpkcYztzJpaDao.save(fpkcYztz);
    }

    public void save(List<FpkcYztz> fpkcYztzList) {
        fpkcYztzJpaDao.save(fpkcYztzList);
    }

    public FpkcYztz findOneByParams(Map params) {
        return fpkcYztzMapper.findOneByParams(params);
    }

    public List<FpkcYztz> findAllByParams(Map params) {
        return fpkcYztzMapper.findAllByParams(params);
    }

    public List<FpkcYztz> findByPage(Pagination pagination) {
        return fpkcYztzMapper.findByPage(pagination);
    }

    public void deleteYhtzByYjszid(Map map){
        fpkcYztzMapper.deleteYhtzByYjszid(map);
    }

}

