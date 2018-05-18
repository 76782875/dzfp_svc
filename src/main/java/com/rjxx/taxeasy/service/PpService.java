package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.PpJpaDao;
import com.rjxx.taxeasy.dao.PpMapper;
import com.rjxx.taxeasy.domains.Pp;
import com.rjxx.taxeasy.domains.Skp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Dec 12 17:15:27 GMT+08:00 2016
 *
 * @ZhangBing
 */
@Service
public class PpService {

    @Autowired
    private PpJpaDao ppJpaDao;

    @Autowired
    private PpMapper ppMapper;

    @Autowired
    private SkpService skpService;

    public Pp findOne(int id) {
        return ppJpaDao.findOne(id);
    }

    public void save(Pp pp) {
        ppJpaDao.save(pp);
    }

    public void save(List<Pp> ppList) {
        ppJpaDao.save(ppList);
    }

    public Pp findOneByParams(Map params) {
        return ppMapper.findOneByParams(params);
    }

    public List<Pp> findAllByParams(Map params) {
        return ppMapper.findAllByParams(params);
    }

    public List<Pp> findByPage(Pagination pagination) {
        return ppMapper.findByPage(pagination);
    }

    public List<Pp> findTqListByPage(Pagination pagination) {
        return ppMapper.findTqListByPage(pagination);
    }


    /**
     * @param skpid
     * @param gsdm
     * @return
     */
    public Pp findOnePpByGsdmSkpid(Integer skpid, String gsdm) {
        Skp skp = skpService.findOne(skpid);
        if (skp == null) {
            return null;
        }
        Integer ppid = skp.getPid();
        if (ppid != null && ppid != 0) {
            return findOne(ppid);
        }
        Map params = new HashMap();
        params.put("gsdm", gsdm);
        return findOneByParams(params);
    }

}

