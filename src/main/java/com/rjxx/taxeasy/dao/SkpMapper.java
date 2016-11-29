package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Skp;
import com.rjxx.taxeasy.domains.Xf;
import com.rjxx.taxeasy.vo.SkpVo;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri Oct 14 08:55:04 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface SkpMapper {

    public Skp findOneByParams(Skp skp);

    public List<Skp> findAllByParams(Skp skp);

    public List<SkpVo> findByPage(Pagination pagination);
    	
	public List<Skp> getSkpListByYhId(Integer yhId);
}

