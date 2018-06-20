package com.rjxx.taxeasy.dao.shouqianba;

import com.rjxx.taxeasy.domains.shouqianba.PayPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/6/13
 */
public interface PayPaymentsRepository extends JpaRepository<PayPayments,Integer> {

    @Query(nativeQuery = true,value = "select * from pay_payments where out_id=?1")
    List<PayPayments> findByoutId(Integer outId);
}
