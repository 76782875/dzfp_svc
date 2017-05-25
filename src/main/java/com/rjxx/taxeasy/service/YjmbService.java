package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.YjmbJpaDao;
import com.rjxx.taxeasy.dao.YjmbMapper;
import com.rjxx.taxeasy.domains.Yjmb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu May 25 15:30:57 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class YjmbService {

    @Autowired
    private YjmbJpaDao yjmbJpaDao;

    @Autowired
    private YjmbMapper yjmbMapper;

    public Yjmb findOne(int id) {
        return yjmbJpaDao.findOne(id);
    }

    public void save(Yjmb yjmb) {
        yjmbJpaDao.save(yjmb);
    }

    public void save(List<Yjmb> yjmbList) {
        yjmbJpaDao.save(yjmbList);
    }

    public Yjmb findOneByParams(Map params) {
        return yjmbMapper.findOneByParams(params);
    }

    public List<Yjmb> findAllByParams(Map params) {
        return yjmbMapper.findAllByParams(params);
    }

    public List<Yjmb> findByPage(Pagination pagination) {
        return yjmbMapper.findByPage(pagination);
    }

}

