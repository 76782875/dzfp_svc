package com.rjxx.taxeasy.invoice;

import com.rjxx.taxeasy.domains.Gsxx;
import com.rjxx.taxeasy.service.GsxxService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
     *
     * @param AppId
     * @param Sign
     * @param Operation
     * @param OrderData
     * @return
     */
    public String uploadOrderData(final String AppId, final String Sign, final String Operation,
                                  final String OrderData) {
        try {
            String result = dealOrder(AppId, Sign, Operation, OrderData);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponeseUtils.error("上传交易信息发生异常");
        }
    }

    /**
     * 处理上传的交易信息
     *
     * @param AppId
     * @param Sign
     * @param Operation
     * @param OrderData
     * @return
     */
    public String dealOrder(String AppId, String Sign, String Operation, String OrderData) {
        String result = "";
        Map tempMap = new HashMap();
        tempMap.put("appkey", AppId);
        Gsxx gsxxBean = gsxxservice.findOneByParams(tempMap);
        if (gsxxBean == null) {
            return ResponeseUtils.error("公司信息未找到:" + AppId + "," + Sign);
        }
        // 校验数据是否被篡改过
        String key = gsxxBean.getSecretKey();
        String signSourceData = "data=" + OrderData + "&key=" + key;
        String newSign = DigestUtils.md5Hex(signSourceData);
        if (!Sign.equals(newSign)) {
            return ResponeseUtils.error("签名不通过");
        }
        String gsdm = gsxxBean.getGsdm();
         if (Operation.equals("01")) {
            return dealOrder01.execute(gsdm, OrderData, Operation);
        } else if (Operation.equals("02")) {
            return dealOrder02.execute(gsdm, OrderData, Operation);
        } else if (Operation.equals("04")) {
            return dealOrder04.execute(gsdm, OrderData, Operation);
        }
        return result;
    }
}
