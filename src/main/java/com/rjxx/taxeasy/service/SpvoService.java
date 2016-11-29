package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.SpvoMapper;
import com.rjxx.taxeasy.vo.Spvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9.
 */
@Service
public class SpvoService {

    @Autowired
    private SpvoMapper spvoMapper;

    public List<Spvo> findAllByParams(Spvo spvo) {
        return spvoMapper.findAllByParams(spvo);
    }

    public List<Spvo> findAllByGsdm(String gsdm) {
        Spvo spvo = new Spvo();
        spvo.setGsdm(gsdm);
        return findAllByParams(spvo);
    }
    
    public List<Spvo> findAllOnPage(Pagination pagination){
    	return spvoMapper.findAllOnPage(pagination);
    }

}
