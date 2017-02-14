package com.rjxx.taxeasy.bizcomm.utils;

import com.rjxx.taxeasy.domains.Kpls;
import org.springframework.stereotype.Service;

/**
 * 税控操作的service
 * Created by Zhangbing on 2017-02-13.
 */
@Service
public class SkService {


    /**
     * 调用税控服务
     *
     * @param kplsh
     * @return
     */
    public InvoiceResponse callService(int kplsh) throws Exception {
        InvoiceResponse response = new InvoiceResponse();
        response.setReturnCode("0000");
        return response;
    }

}
