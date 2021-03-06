package com.rjxx.taxeasy.dao;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.vo.Spvo;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9.
 */
@MybatisRepository
public interface SpvoMapper {

    public List<Spvo> findAllByParams(Spvo params);

    public List<Spvo> findAllOnPage(Pagination pagination);

}
