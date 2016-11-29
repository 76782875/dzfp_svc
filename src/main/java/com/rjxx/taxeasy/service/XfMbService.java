package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.XfMbJpaDao;
import com.rjxx.taxeasy.dao.XfMbMapper;
import com.rjxx.taxeasy.domains.XfMb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Tue Nov 01 17:15:08 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
@Service
public class XfMbService {

    @Autowired
    private XfMbJpaDao xfMbJpaDao;

    @Autowired
    private XfMbMapper xfMbMapper;

    public XfMb findOne(int id) {
        return xfMbJpaDao.findOne(id);
    }

    public void save(XfMb xfMb) {
        xfMbJpaDao.save(xfMb);
        Map<String, Object> params = new HashMap<>();
        params.put("id", xfMb.getId());
        params.put("xfsh", xfMb.getXfsh());
        List<XfMb> list = xfMbMapper.findAllByParams(params);
        if (!list.isEmpty()) {
			for (XfMb xfMb2 : list) {
				xfMb2.setYxbz("0");
				xfMb2.setXgry(xfMb.getLrry());
				xfMb2.setXgsj(new Date());
				xfMbJpaDao.save(xfMb2);
			}
		}
    }

    public void save(List<XfMb> xfMbList) {
        xfMbJpaDao.save(xfMbList);
    }

    public XfMb findOneByParams(Map params) {
        return xfMbMapper.findOneByParams(params);
    }

    public List<XfMb> findAllByParams(Map params) {
        return xfMbMapper.findAllByParams(params);
    }

    public List<XfMb> findByPage(Pagination pagination) {
        return xfMbMapper.findByPage(pagination);
    }

}

