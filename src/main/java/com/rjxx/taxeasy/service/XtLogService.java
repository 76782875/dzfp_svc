package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.XtLogJpaDao;
import com.rjxx.taxeasy.dao.XtLogMapper;
import com.rjxx.taxeasy.domains.XtLog;
import com.rjxx.taxeasy.vo.XtLogVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Jan 16 09:14:56 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class XtLogService {

    @Autowired
    private XtLogJpaDao xtLogJpaDao;

    @Autowired
    private XtLogMapper xtLogMapper;

    public XtLog findOne(int id) {
        return xtLogJpaDao.findOne(id);
    }

    public void save(XtLog xtLog) {
        xtLogJpaDao.save(xtLog);
    }

    public void save(List<XtLog> xtLogList) {
        xtLogJpaDao.save(xtLogList);
    }

    public XtLog findOneByParams(Map params) {
        return xtLogMapper.findOneByParams(params);
    }

    public List<XtLog> findAllByParams(Map params) {
        return xtLogMapper.findAllByParams(params);
    }

    public List<XtLogVo> findByPage(Pagination pagination) {
        return xtLogMapper.findByPage(pagination);
    }

}

