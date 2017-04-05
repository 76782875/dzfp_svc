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

    public Skp findOneByParams(Map skp);

    public List<Skp> findAllByParams(Skp skp);
    public List<Skp> findBySql(Skp skp);
    public List<SkpVo> findByPage(Pagination pagination);
    	
	public List<Skp> getSkpListByYhId(Map params);

    public List<Skp> getKpd(Map params);
    public List<Skp> findCsz(Skp skp);
    
    public SkpVo findXfSkp(Map params);

	public SkpVo findXfSkpNum(Map map);
}

