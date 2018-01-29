package com.rjxx.taxeasy.dao.leshui;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.leshui.Fpcy;

import java.util.List;

/**
 * Created by Administrator on 2018-01-26.
 */
@MybatisRepository
public interface FpcyMapper {
    public List<Fpcy> findByPage(Pagination pagination);
}
