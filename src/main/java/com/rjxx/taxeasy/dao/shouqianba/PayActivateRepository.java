package com.rjxx.taxeasy.dao.shouqianba;

import com.rjxx.taxeasy.domains.shouqianba.PayActivate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/6/7
 */
public interface PayActivateRepository extends JpaRepository<PayActivate,Integer> {

    PayActivate findOneBySkpid(Integer skpid);

    PayActivate findOneByTerminalSn(String terminalSn);
}
