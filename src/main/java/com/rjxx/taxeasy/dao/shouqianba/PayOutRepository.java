package com.rjxx.taxeasy.dao.shouqianba;

import com.rjxx.taxeasy.domains.shouqianba.PayOut;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/6/8
 */
public interface PayOutRepository extends JpaRepository<PayOut,Integer> {
    PayOut findOneByGsdmAndOrderNo(String gsdm,String orderNo);

    PayOut findOneByTradeNo(String tradeNo);
}
