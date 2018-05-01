package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.ZclogJpaDao;
import com.rjxx.taxeasy.dao.ZclogMapper;
import com.rjxx.taxeasy.domains.Zclog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 26 17:25:58 CST 2018
 *
 * @ZhangBing
 */ 
@Service
public class ZclogService {

    @Autowired
    private ZclogJpaDao zclogJpaDao;

    @Autowired
    private ZclogMapper zclogMapper;

    public Zclog findOne(int id) {
        return zclogJpaDao.findOne(id);
    }

    public void save(Zclog zclog) {
        zclogJpaDao.save(zclog);
    }

    public void save(List<Zclog> zclogList) {
        zclogJpaDao.save(zclogList);
    }

    public Zclog findOneByParams(Map params) {
        return zclogMapper.findOneByParams(params);
    }

    public List<Zclog> findAllByParams(Map params) {
        return zclogMapper.findAllByParams(params);
    }

    public List<Zclog> findByPage(Pagination pagination) {
        return zclogMapper.findByPage(pagination);
    }

	public Zclog findOneBySql(Map<String, Object> params) {
		  return zclogMapper.findOneBySql(params);
	}

	public void updateZcnum(Map<String, Object> map) {
		zclogMapper.updateZcnum(map);
	}

	public void updateTynum(Map<String, Object> map) {
		zclogMapper.updateTynum(map);
	}

}

