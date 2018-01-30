package com.rjxx.taxeasy.dao.leshui;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.leshui.Jxfpxx;

import java.util.List;

/**
 * Created by Administrator on 2018-01-26.
 */
@MybatisRepository
public interface JxfpxxMapper {
    public List<Jxfpxx> findByPage(Pagination pagination);


}
