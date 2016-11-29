package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Xf;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/9.
 */
@MybatisRepository
public interface XfMapper {
	
	public Xf findOneByParams(Xf xf);
	
	public List<Xf> findAllByParams(Xf xf);
	
	public List<Xf> findAllByMap(Map<String, Object> map);

    public List<Xf> findByPage(Pagination pagination);

    /**
     * 根据yhid查找该用户下的所有管理的销方
     * @param yhId
     * @return
     */
    public List<Xf> getXfListByYhId(int yhId);

}
