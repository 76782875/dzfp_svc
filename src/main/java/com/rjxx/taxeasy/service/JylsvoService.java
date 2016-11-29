package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.vo.Jylsvo;
import com.rjxx.taxeasy.dao.JylsvoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 17 13:29:48 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
@Service
public class JylsvoService {

    @Autowired
    private JylsvoMapper jylsvoMapper;


    public Jylsvo findOneByParams(Jylsvo Jylsvo) {
        return jylsvoMapper.findOneByParams(Jylsvo);
    }

    public List<Jylsvo> findAllByParams(Jylsvo params) {
        return jylsvoMapper.findAllByParams(params);
    }

    public List<Jylsvo> findByPage(Pagination pagination) {
        return jylsvoMapper.findByPage(pagination);
    }
    
    //查询重开申请的发票
    public List<Jylsvo> findChsqByPage(Pagination pagination) {
        return jylsvoMapper.findChsqByPage(pagination);
    }
    
  //查询换开申请的发票
    public List<Jylsvo> findHhsqByPage(Pagination pagination) {
        return jylsvoMapper.findHhsqByPage(pagination);
    }

}

