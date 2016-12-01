package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.DzfplogvoMapper;
import com.rjxx.taxeasy.vo.DzfpLogVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Dec 01 13:10:34 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
@Service
public class DzfplogvoService {

    @Autowired
    private DzfplogvoMapper dzfplogvoMapper;


    public DzfpLogVO findOneByParams(Map params) {
        return dzfplogvoMapper.findOneByParams(params);
    }

    public List<DzfpLogVO> findAllByParams(Map params) {
        return dzfplogvoMapper.findAllByParams(params);
    }

    public List<DzfpLogVO> findByPage(Pagination pagination) {
        return dzfplogvoMapper.findByPage(pagination);
    }

}

