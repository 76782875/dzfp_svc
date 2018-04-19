package com.rjxx.taxeasy.invoice;

import com.rjxx.taxeasy.service.GsxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * Created by wangyahui on 2017/12/5 0005
 */
@Service
public class KpService {

    @Autowired
    private GsxxService gsxxservice;

    @Autowired
    @Qualifier("dealOrder01-svc")
    private DealOrder01 dealOrder01;

    @Autowired
    @Qualifier("dealOrder02-svc")
    private DealOrder02 dealOrder02;

    @Autowired
    @Qualifier("dealOrder04-svc")
    private DealOrder04 dealOrder04;

    /**
     * 交易数据上传service
     */
    public String uploadOrderData(String gsdm, Map map, String Operation) {
        try {
            String result = dealOrder(gsdm, map, Operation);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponeseUtils.error("上传交易信息发生异常");
        }
    }

    /**
     * 处理上传的交易信息
     */
    private String dealOrder(String gsdm, Map map, String Operation) {
        if (Operation.equals("01")) {
            return dealOrder01.execute(gsdm, map, Operation);
        } else if (Operation.equals("02")) {
            return dealOrder02.execute(gsdm, map, Operation);
        } else if (Operation.equals("04")) {
            return dealOrder04.execute(gsdm, map,Operation);
        } else {
            return ResponeseUtils.error("未知的操作方式");
        }
    }
}
