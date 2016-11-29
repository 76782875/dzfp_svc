package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Yh;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9.
 */
@MybatisRepository
public interface YhMapper {

    public Yh findOneByParams(Yh yh);

    public List<Yh> findAllByParams(Yh yh);

    public List<Yh> findByPage(Pagination pagination);

}
