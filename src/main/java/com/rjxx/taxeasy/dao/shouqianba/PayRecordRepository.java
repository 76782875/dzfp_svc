package com.rjxx.taxeasy.dao.shouqianba;

import com.rjxx.taxeasy.domains.shouqianba.PayRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/6/13
 */
public interface PayRecordRepository extends JpaRepository<PayRecord,Integer> {
}
