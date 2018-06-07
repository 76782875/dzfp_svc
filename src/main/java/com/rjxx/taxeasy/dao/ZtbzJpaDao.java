package com.rjxx.taxeasy.dao;

import com.rjxx.taxeasy.domains.Ztbz;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/6/6
 */
public interface ZtbzJpaDao extends JpaRepository<Ztbz,Long> {

    Ztbz findOneByZtbzdm(String ztbzdm);
}
