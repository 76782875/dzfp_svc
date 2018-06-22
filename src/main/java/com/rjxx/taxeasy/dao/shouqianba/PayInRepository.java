package com.rjxx.taxeasy.dao.shouqianba;

import com.rjxx.taxeasy.domains.shouqianba.PayIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/6/7
 */
public interface PayInRepository extends JpaRepository<PayIn, Integer> {

    PayIn findOneByClientSn(String clientSn);

    List<PayIn> findAllByOrderNo(String orderNo);

    @Query(nativeQuery = true,value = "select * from pay_in where order_no=?1 order by lrsj desc limit 1")
    PayIn findLastOneByOrderNo(String orderNo);
}
