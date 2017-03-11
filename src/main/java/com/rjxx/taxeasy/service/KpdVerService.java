package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.KpdVerJpaDao;
import com.rjxx.taxeasy.dao.KpdVerMapper;
import com.rjxx.taxeasy.domains.KpdVer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Mar 09 10:46:09 CST 2017
 *
 * @ZhangBing
 */
@Service
public class KpdVerService {

    @Autowired
    private KpdVerJpaDao kpdVerJpaDao;

    @Autowired
    private KpdVerMapper kpdVerMapper;

    public KpdVer findOne(int id) {
        return kpdVerJpaDao.findOne(id);
    }

    public void save(KpdVer kpdVer) {
        kpdVerJpaDao.save(kpdVer);
    }

    public void save(List<KpdVer> kpdVerList) {
        kpdVerJpaDao.save(kpdVerList);
    }

    public KpdVer findOneByParams(Map params) {
        return kpdVerMapper.findOneByParams(params);
    }

    public List<KpdVer> findAllByParams(Map params) {
        return kpdVerMapper.findAllByParams(params);
    }

    public List<KpdVer> findByPage(Pagination pagination) {
        return kpdVerMapper.findByPage(pagination);
    }

    public KpdVer findOneByKpdid(int kpdid){
        return kpdVerMapper.findOneByKpdid(kpdid);
    }

}

