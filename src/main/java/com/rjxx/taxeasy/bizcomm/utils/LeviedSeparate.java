package com.rjxx.taxeasy.bizcomm.utils;



import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.rjxx.taxeasy.domains.Jyspmx;


/**
 * 价税分离 输出数据为两位小数
 *
 * @author k
 */
public class LeviedSeparate {

    /**
     * 获得价税合计 total 通过amount(含税金额)
     *
     * @throws ActiveRecordException
     */
    public BigDecimal getTotal(int djh, List<Jyspmx> jyspmxs)
            {//jyspmxs不空
        double total = 0;
        BigDecimal total1 = new BigDecimal(Double.toString(total));

        for (int i = 0; i < jyspmxs.size(); i++) {
            Jyspmx jyspmx = jyspmxs.get(i);
            BigDecimal amount = new BigDecimal(Double.toString(jyspmx.getSpje()));
            total1 = amount.add(total1);
        }
        //以保留两位小数
        return com.rjxx.utils.LeviedSeparate.getBigDecimal(total1.toString());
    }

    /**
     * 获得合计金额 不含税totalAmount
     */
    public BigDecimal getTotalAmount(int djh, List<Jyspmx> jyspmxs)
           {
        double TotalAmount = 0;
        BigDecimal TotalAmount1 = new BigDecimal(Double.toString(TotalAmount));
        for (int i = 0; i < jyspmxs.size(); i++) {
            Jyspmx jyspmx = jyspmxs.get(i);
            com.rjxx.utils.LeviedSeparate leviedSeparate = new com.rjxx.utils.LeviedSeparate();
            BigDecimal amount1 = leviedSeparate.getAmount(jyspmx.getSpje(), jyspmx.getSpsl());
            TotalAmount1 = amount1.add(TotalAmount1);
        }

        return com.rjxx.utils.LeviedSeparate.getBigDecimal(TotalAmount1.toString());
    }


    public static SeparateBean separatePrice(List<Jyspmx> jyspmxs, String hsbz) throws Exception {
        List<Jyspmx> sepJyspmxs = new ArrayList<Jyspmx>();//价税分离后的list

        SeparateBean separateBean = new SeparateBean();
        Double total = 0.00;
        Double totalAmount = 0.00;
        Double totalTaxAmount = 0.00;
        if ("1".equals(hsbz)) {
            for (int i = 0; i < jyspmxs.size(); i++) {
                Jyspmx mx = jyspmxs.get(i);
                double spje = mx.getSpje();
                double spsl = mx.getSpsl();
                double spdj = mx.getSpdj();

                double jeWithoutTax = com.rjxx.utils.LeviedSeparate.div(spje, 1 + spsl, 2);
                double jeTax = com.rjxx.utils.LeviedSeparate.sub(spje, jeWithoutTax);
                //判断单价是否为空！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！todo
                double djWithoutTax = com.rjxx.utils.LeviedSeparate.div(spdj, 1 + spsl, 6);
                mx.setSpje(jeWithoutTax);//商品金额不含税
                mx.setSpse(jeTax);//税额
                mx.setJshj(spje);//价税合计
                mx.setSpdj(djWithoutTax);//单价不含税
                sepJyspmxs.add(mx);
                ///YEYONG

                totalAmount = com.rjxx.utils.LeviedSeparate.add(totalAmount, jeWithoutTax);
                totalTaxAmount = com.rjxx.utils.LeviedSeparate.add(totalTaxAmount, jeTax);

            }
            separateBean.setT_jyspmxList(sepJyspmxs);
        } else if ("0".equals(hsbz)) {
            for (int i = 0; i < jyspmxs.size(); i++) {
                Jyspmx mx = jyspmxs.get(i);
                double spje = mx.getSpje();
                double spse = mx.getSpse();
                totalAmount = com.rjxx.utils.LeviedSeparate.add(totalAmount, spje);
                totalTaxAmount = com.rjxx.utils.LeviedSeparate.add(totalTaxAmount, spse);

            }

            separateBean.setT_jyspmxList(jyspmxs);
        }
        total = com.rjxx.utils.LeviedSeparate.add(totalAmount, totalTaxAmount);
        separateBean.setTotal(total);
        separateBean.setTotalAmount(totalAmount);
        separateBean.setTotalTaxAmount(totalTaxAmount);
        return separateBean;
    }
}
