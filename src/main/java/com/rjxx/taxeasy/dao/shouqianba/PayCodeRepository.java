package com.rjxx.taxeasy.dao.shouqianba;

import com.rjxx.taxeasy.domains.shouqianba.PayCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/6/13
 */
public interface PayCodeRepository extends JpaRepository<PayCode,Integer> {

    PayCode findOneByCode(String code);

    @Query(nativeQuery = true,value = "select code from pay_code where filter like '1%'")
    List<String> findByIsFinal();
}
