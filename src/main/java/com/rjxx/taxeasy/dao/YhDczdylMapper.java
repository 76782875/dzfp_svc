package com.rjxx.taxeasy.dao;

import java.util.List;
import java.util.Map;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.YhDczdyl;
import com.rjxx.taxeasy.vo.DczydlVo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Dec 22 16:30:46 CST 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface YhDczdylMapper {

    public YhDczdyl findOneByParams(Map params);

    public List<DczydlVo> findAllByParams(Map params);

    public List<YhDczdyl> findByPage(Pagination pagination);

}

