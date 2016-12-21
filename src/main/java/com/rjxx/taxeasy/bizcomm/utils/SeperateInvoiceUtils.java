package com.rjxx.taxeasy.bizcomm.utils;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.rjxx.taxeasy.vo.JyspmxDecimal;

/**
 * Created by Administrator on 2016/8/11.
 */
public class SeperateInvoiceUtils {

    // 默认除法运算精度
    private static final Integer DEF_DIV_SCALE = 30;

    public static final int detailsNumber = 100;

    /*
     * 价税分离
     */
    public static List<JyspmxDecimal> separatePrice(List<JyspmxDecimal> jyspmxs) throws Exception {
        List<JyspmxDecimal> sepJyspmxs = new ArrayList<JyspmxDecimal>();// 价税分离后的list
        for (int i = 0; i < jyspmxs.size(); i++) {
            JyspmxDecimal mx = jyspmxs.get(i);
            BigDecimal jshj = mx.getJshj();
//            BigDecimal spje = mx.getSpje();
            BigDecimal spsl = mx.getSpsl();
            BigDecimal spdj = mx.getSpdj();
            BigDecimal jeWithoutTax = div(jshj, spsl.add(new BigDecimal(1))).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal jeTax = sub(jshj, jeWithoutTax);
            // 判断单价是否为空！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！todo
            //Double djWithoutTax = div(spdj, 1 + spsl, 6);
            BigDecimal djWithoutTax;
            if (spdj == null) {
                djWithoutTax = null;// 单价不含税
            } else {
                djWithoutTax = div(spdj, spsl.add(new BigDecimal(1)));
            }
            mx.setSpje(jeWithoutTax);// 商品金额不含税
            mx.setSpse(jeTax);// 税额
//            mx.setJshj(spje);// 价税合计
            mx.setSpdj(djWithoutTax);// 单价不含税
            sepJyspmxs.add(mx);
        }
        return sepJyspmxs;
    }

    /*
     * 拆分发票
     */
    public static List<JyspmxDecimal> splitInvoices(List<JyspmxDecimal> jyspmxs, BigDecimal maxje, int mxsl) throws Exception {
        int mxnum = detailsNumber;
        if (mxsl != 0 && mxsl <= detailsNumber) {
            mxnum = mxsl;
        }
        List<JyspmxDecimal> tempJyspmxs = new ArrayList<JyspmxDecimal>();// 缓存商品明细表
        List<JyspmxDecimal> splitKpspmxs = new ArrayList<JyspmxDecimal>();// 拆分发票后的list
        BigDecimal zje = new BigDecimal(0);// 汇总金额
        int fpnum = 1;
        int djh;
        int spmxxh;
        String fphxz;
        String spdm;
        for (int i = 0; i < jyspmxs.size(); i++) {
            JyspmxDecimal jyspmx = jyspmxs.get(i);
            djh = jyspmx.getDjh();
            fphxz = jyspmx.getFphxz();
            spmxxh = jyspmx.getSpmxxh();
            tempJyspmxs.add(jyspmx);
            zje = zje.add(jyspmx.getSpje());
            spdm = jyspmx.getSpdm();
            if (zje.compareTo(maxje) >= 0 || tempJyspmxs.size() == mxnum) {
                if ((tempJyspmxs.size() == mxnum && zje.compareTo(maxje) < 0) || zje.compareTo(maxje) == 0) {
                    //达到每张发票开具最大条数，并且总金额未超出上限。
                    jyspmx.setFpnum(fpnum);
                    splitKpspmxs.add(jyspmx);
                    tempJyspmxs.clear();
                    fpnum++;
                    zje = BigDecimal.ZERO;
                } else {
                    // Jyspmx ccjyspmx = new Jyspmx();//超出金额对象
                    JyspmxDecimal cfjyspmx = new JyspmxDecimal();// 拆分金额对象
                    // ccjyspmx = jyspmx;//超出金额对象
                    // cfjyspmx = jyspmx;//拆分金额对象
                    // 商品名称
                    String spmc = jyspmx.getSpmc();
                    // 规格型号
                    String spggxh = jyspmx.getSpggxh();
                    // 单位
                    String spdw = jyspmx.getSpdw();
                    // 单价
                    BigDecimal spdj = jyspmx.getSpdj();
                    // 税率
                    BigDecimal spsl = jyspmx.getSpsl();
                    BigDecimal spje = jyspmx.getSpje();// 原商品金额
                    BigDecimal spsm = jyspmx.getSps();// 原商品数量
                    BigDecimal spse = jyspmx.getSpse();// 原商品税额
                    BigDecimal ccje = sub(zje, maxje);// 超出金额
                    BigDecimal cfje = sub(spje, ccje);// 拆分金额
                    BigDecimal cfsm = div(spsm, div(spje, cfje));// 拆分数量
                    BigDecimal cfse = div(spse, div(spje, cfje)).setScale(2, BigDecimal.ROUND_HALF_UP);// 拆分税额
                    BigDecimal jshj = add(cfje, cfse);
                    cfjyspmx.setFphxz(fphxz);
                    cfjyspmx.setDjh(djh);
                    cfjyspmx.setSpmxxh(spmxxh);
                    cfjyspmx.setSpje(cfje);
                    cfjyspmx.setSps(cfsm);
                    cfjyspmx.setSpse(cfse);
                    cfjyspmx.setFpnum(fpnum);
                    cfjyspmx.setSpmc(spmc);
                    cfjyspmx.setSpggxh(spggxh);
                    cfjyspmx.setSpdw(spdw);
                    cfjyspmx.setSpdj(spdj);
                    cfjyspmx.setSpsl(spsl);
                    cfjyspmx.setJshj(jshj);
                    cfjyspmx.setYkphj(new BigDecimal(0));
                    cfjyspmx.setSpdm(spdm);
                    cfjyspmx.setGsdm(jyspmx.getGsdm());
                    splitKpspmxs.add(cfjyspmx);

                    int n = (int) Math.floor(div(ccje, maxje).doubleValue());
                    BigDecimal cfsm1 = new BigDecimal(0.00);
                    BigDecimal cfse1 = new BigDecimal(0.00);
                    if (n > 0) {
                        cfsm1 = div(spsm, div(spje, maxje));// 拆分数量
                        cfse1 = div(spse, div(spje, maxje)).setScale(2, BigDecimal.ROUND_HALF_UP);// 拆分税额

                        for (int j = 0; j < n; j++) {
                            JyspmxDecimal ccjyspmx1 = new JyspmxDecimal();
                            // ccjyspmx1 = ccjyspmx;
                            fpnum++;
                            BigDecimal jshj1 = add(maxje, cfse1);
                            ccjyspmx1.setFphxz(fphxz);
                            ccjyspmx1.setDjh(djh);
                            ccjyspmx1.setSpmxxh(spmxxh);
                            ccjyspmx1.setSpje(maxje);
                            ccjyspmx1.setSps(cfsm1);
                            ccjyspmx1.setSpse(cfse1);
                            ccjyspmx1.setFpnum(fpnum);
                            ccjyspmx1.setSpmc(spmc);
                            ccjyspmx1.setSpggxh(spggxh);
                            ccjyspmx1.setSpdw(spdw);
                            ccjyspmx1.setSpdj(spdj);
                            ccjyspmx1.setSpsl(spsl);
                            ccjyspmx1.setJshj(jshj1);
                            ccjyspmx1.setYkphj(new BigDecimal(0));
                            ccjyspmx1.setSpdm(spdm);
                            ccjyspmx1.setGsdm(jyspmx.getGsdm());
                            splitKpspmxs.add(ccjyspmx1);
                        }
                    }
                    ccje = sub(ccje, mul(new BigDecimal(n), maxje));
                    JyspmxDecimal ccjyspmx2 = new JyspmxDecimal();
                    ccjyspmx2.setFphxz(fphxz);
                    ccjyspmx2.setDjh(djh);
                    ccjyspmx2.setSpmxxh(spmxxh);
                    ccjyspmx2.setSpje(ccje);
                    ccjyspmx2.setSpmc(spmc);
                    ccjyspmx2.setSpggxh(spggxh);
                    ccjyspmx2.setSpdj(spdj);
                    ccjyspmx2.setSpsl(spsl);
                    ccjyspmx2.setSpdw(spdw);
                    ccjyspmx2.setSps(sub(sub(spsm, cfsm), mul(new BigDecimal(n), cfsm1)));
                    ccjyspmx2.setSpse(sub(sub(spse, cfse), mul(new BigDecimal(n), cfse1)));
                    ccjyspmx2.setJshj(add(ccjyspmx2.getSpje(), ccjyspmx2.getSpse()));
                    ccjyspmx2.setYkphj(new BigDecimal(0));
                    ccjyspmx2.setSpdm(spdm);
                    ccjyspmx2.setGsdm(jyspmx.getGsdm());
                    fpnum++;
                    ccjyspmx2.setFpnum(fpnum);
                    tempJyspmxs.clear();
                    if (ccje.doubleValue() != 0) {
                        splitKpspmxs.add(ccjyspmx2);
                        tempJyspmxs.add(ccjyspmx2);
                    }
                    zje = ccje;

                }
            } else {
                jyspmx.setFpnum(fpnum);
                splitKpspmxs.add(jyspmx);
            }
        }
        return splitKpspmxs;
    }

    /**
     * 
     * @param jyspmxs
     * 		交易商品明细
     * @param maxje
     * 		开票最大金额
     * @param cpje
     * 		分票金额
     * @param mxsl
     * 		明细数量
     * @return
     * @throws Exception
     */
    public static List<JyspmxDecimal> splitInvoices(List<JyspmxDecimal> jyspmxs, BigDecimal maxje, BigDecimal fpje, int mxsl) throws Exception {
        int mxnum = detailsNumber;
        if (mxsl != 0 && mxsl <= detailsNumber) {
            mxnum = mxsl;
        }
        List<JyspmxDecimal> tempJyspmxs = new ArrayList<JyspmxDecimal>();// 缓存商品明细表
        List<JyspmxDecimal> splitKpspmxs = new ArrayList<JyspmxDecimal>();// 拆分发票后的list
        BigDecimal zje = new BigDecimal(0);// 汇总金额
        BigDecimal total = new BigDecimal(0);
        for (JyspmxDecimal jyspmx : jyspmxs) {
			total = total.add(jyspmx.getSpje());
		}
        if (total.subtract(maxje).doubleValue() > 0) {
			maxje = fpje;
		}
        int fpnum = 1;
        int djh;
        int spmxxh;
        String fphxz;
        String spdm;
        for (int i = 0; i < jyspmxs.size(); i++) {
            JyspmxDecimal jyspmx = jyspmxs.get(i);
            djh = jyspmx.getDjh();
            fphxz = jyspmx.getFphxz();
            spmxxh = jyspmx.getSpmxxh();
            tempJyspmxs.add(jyspmx);
            zje = zje.add(jyspmx.getSpje());
            spdm = jyspmx.getSpdm();
            if (zje.compareTo(maxje) >= 0 || tempJyspmxs.size() == mxnum) {
                if ((tempJyspmxs.size() == mxnum && zje.compareTo(maxje) < 0) || zje.compareTo(maxje) == 0) {
                    //达到每张发票开具最大条数，并且总金额未超出上限。
                    jyspmx.setFpnum(fpnum);
                    splitKpspmxs.add(jyspmx);
                    tempJyspmxs.clear();
                    fpnum++;
                    zje = BigDecimal.ZERO;
                } else {
                    // Jyspmx ccjyspmx = new Jyspmx();//超出金额对象
                    JyspmxDecimal cfjyspmx = new JyspmxDecimal();// 拆分金额对象
                    // ccjyspmx = jyspmx;//超出金额对象
                    // cfjyspmx = jyspmx;//拆分金额对象
                    // 商品名称
                    String spmc = jyspmx.getSpmc();
                    // 规格型号
                    String spggxh = jyspmx.getSpggxh();
                    // 单位
                    String spdw = jyspmx.getSpdw();
                    // 单价
                    BigDecimal spdj = jyspmx.getSpdj();
                    // 税率
                    BigDecimal spsl = jyspmx.getSpsl();
                    BigDecimal spje = jyspmx.getSpje();// 原商品金额
                    BigDecimal spsm = jyspmx.getSps();// 原商品数量
                    BigDecimal spse = jyspmx.getSpse();// 原商品税额
                    BigDecimal ccje = sub(zje, maxje);// 超出金额
                    BigDecimal cfje = sub(spje, ccje);// 拆分金额
                    BigDecimal cfsm = div(spsm, div(spje, cfje));// 拆分数量
                    BigDecimal cfse = div(spse, div(spje, cfje)).setScale(2, BigDecimal.ROUND_HALF_UP);// 拆分税额
                    BigDecimal jshj = add(cfje, cfse);
                    cfjyspmx.setFphxz(fphxz);
                    cfjyspmx.setDjh(djh);
                    cfjyspmx.setSpmxxh(spmxxh);
                    cfjyspmx.setSpje(cfje);
                    cfjyspmx.setSps(cfsm);
                    cfjyspmx.setSpse(cfse);
                    cfjyspmx.setFpnum(fpnum);
                    cfjyspmx.setSpmc(spmc);
                    cfjyspmx.setSpggxh(spggxh);
                    cfjyspmx.setSpdw(spdw);
                    cfjyspmx.setSpdj(spdj);
                    cfjyspmx.setSpsl(spsl);
                    cfjyspmx.setJshj(jshj);
                    cfjyspmx.setYkphj(new BigDecimal(0));
                    cfjyspmx.setSpdm(spdm);
                    cfjyspmx.setGsdm(jyspmx.getGsdm());
                    splitKpspmxs.add(cfjyspmx);

                    int n = (int) Math.floor(div(ccje, maxje).doubleValue());
                    BigDecimal cfsm1 = new BigDecimal(0.00);
                    BigDecimal cfse1 = new BigDecimal(0.00);
                    if (n > 0) {
                        cfsm1 = div(spsm, div(spje, maxje));// 拆分数量
                        cfse1 = div(spse, div(spje, maxje)).setScale(2, BigDecimal.ROUND_HALF_UP);// 拆分税额

                        for (int j = 0; j < n; j++) {
                            JyspmxDecimal ccjyspmx1 = new JyspmxDecimal();
                            // ccjyspmx1 = ccjyspmx;
                            fpnum++;
                            BigDecimal jshj1 = add(maxje, cfse1);
                            ccjyspmx1.setFphxz(fphxz);
                            ccjyspmx1.setDjh(djh);
                            ccjyspmx1.setSpmxxh(spmxxh);
                            ccjyspmx1.setSpje(maxje);
                            ccjyspmx1.setSps(cfsm1);
                            ccjyspmx1.setSpse(cfse1);
                            ccjyspmx1.setFpnum(fpnum);
                            ccjyspmx1.setSpmc(spmc);
                            ccjyspmx1.setSpggxh(spggxh);
                            ccjyspmx1.setSpdw(spdw);
                            ccjyspmx1.setSpdj(spdj);
                            ccjyspmx1.setSpsl(spsl);
                            ccjyspmx1.setJshj(jshj1);
                            ccjyspmx1.setYkphj(new BigDecimal(0));
                            ccjyspmx1.setSpdm(spdm);
                            ccjyspmx1.setGsdm(jyspmx.getGsdm());
                            splitKpspmxs.add(ccjyspmx1);
                        }
                    }
                    ccje = sub(ccje, mul(new BigDecimal(n), maxje));
                    JyspmxDecimal ccjyspmx2 = new JyspmxDecimal();
                    ccjyspmx2.setFphxz(fphxz);
                    ccjyspmx2.setDjh(djh);
                    ccjyspmx2.setSpmxxh(spmxxh);
                    ccjyspmx2.setSpje(ccje);
                    ccjyspmx2.setSpmc(spmc);
                    ccjyspmx2.setSpggxh(spggxh);
                    ccjyspmx2.setSpdj(spdj);
                    ccjyspmx2.setSpsl(spsl);
                    ccjyspmx2.setSpdw(spdw);
                    ccjyspmx2.setSps(sub(sub(spsm, cfsm), mul(new BigDecimal(n), cfsm1)));
                    ccjyspmx2.setSpse(sub(sub(spse, cfse), mul(new BigDecimal(n), cfse1)));
                    ccjyspmx2.setJshj(add(ccjyspmx2.getSpje(), ccjyspmx2.getSpse()));
                    ccjyspmx2.setYkphj(new BigDecimal(0));
                    ccjyspmx2.setSpdm(spdm);
                    ccjyspmx2.setGsdm(jyspmx.getGsdm());
                    fpnum++;
                    ccjyspmx2.setFpnum(fpnum);
                    tempJyspmxs.clear();
                    if (ccje.doubleValue() != 0) {
                        splitKpspmxs.add(ccjyspmx2);
                        tempJyspmxs.add(ccjyspmx2);
                    }
                    zje = ccje;

                }
            } else {
                jyspmx.setFpnum(fpnum);
                splitKpspmxs.add(jyspmx);
            }
        }
        return splitKpspmxs;
    }

    /**
     * 提供精确的加法运算。
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static BigDecimal add(BigDecimal value1, BigDecimal value2) {
        if (value1 == null) {
            return null;
        }
        if (value2 == null) {
            return null;
        }
        return value1.add(value2);
    }

    /**
     * 提供精确的减法运算。
     *
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static BigDecimal sub(BigDecimal value1, BigDecimal value2) {
        if (value1 == null) {
            return null;
        }
        if (value2 == null) {
            return null;
        }
        return value1.subtract(value2);
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static BigDecimal mul(BigDecimal value1, BigDecimal value2) {
        if (value1 == null) {
            return null;
        }
        if (value2 == null) {
            return null;
        }
        return value1.multiply(value2);
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时， 精确到小数点以后指定位，以后的数字四舍五入。
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return 两个参数的商
     */
    public static BigDecimal div(BigDecimal dividend, BigDecimal divisor) {
        if (dividend == null) {
            return null;
        }
        if (divisor == null || divisor.doubleValue() == 0) {
            return null;
        }
        return div(dividend, divisor, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @param scale    表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static BigDecimal div(BigDecimal dividend, BigDecimal divisor, Integer scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        if (dividend == null) {
            return null;
        }
        if (divisor == null || divisor.doubleValue() == 0) {
            return null;
        }
        return dividend.divide(divisor, scale, BigDecimal.ROUND_HALF_UP);
    }
    
    public static void main(String[] args) throws Exception {
		List<JyspmxDecimal> list = new ArrayList<>();
		JyspmxDecimal jymx = new JyspmxDecimal();
		jymx.setJshj(new BigDecimal(106));
		jymx.setSpsl(new BigDecimal(0.06));
		jymx.setSpje(new BigDecimal(100));
		jymx.setSpse(new BigDecimal(6));
		jymx.setDjh(1);
		jymx.setSpmxxh(1);
		list.add(jymx);
		
		List<JyspmxDecimal> res = splitInvoices(list, new BigDecimal(99.99), new BigDecimal(90), 1);
		for (JyspmxDecimal jyspmx : res) {
			System.out.println(jyspmx.getSpje() + "\t" + jyspmx.getSpse() + "\t" + jyspmx.getSpsl() + "\t" + jyspmx.getJshj());
		}
		
	}
}
