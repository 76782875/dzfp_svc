package com.rjxx.taxeasy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.KplsvoMapper;
import com.rjxx.taxeasy.vo.KplsVO;
import com.rjxx.taxeasy.vo.Slcxvo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Dec 01 11:29:47 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
@Service
public class KplsvoService {


    @Autowired
    private KplsvoMapper kplsvoMapper;

    public KplsVO findOneByParams(Map params) {
        return kplsvoMapper.findOneByParams(params);
    }

    public List<KplsVO> findAllByParams(Map params) {
        return kplsvoMapper.findAllByParams(params);
    }

    public List<KplsVO> findByPage(Pagination pagination) {
        return kplsvoMapper.findByPage(pagination);
    }
    
    public List<Slcxvo> Sltjcx(Map params){
    	return kplsvoMapper.Sltjcx(params);
    }

}

