package com.rjxx.taxeasy.bizcomm.utils;

import com.rjxx.taxeasy.domains.Jyxxsq;
import com.rjxx.taxeasy.vo.JyspmxDecimal2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rjxx.taxeasy.vo.QzMxTmpVo;
import org.apache.ibatis.mapping.ResultMapping;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/06/04.
 */
@Service
public class InvoiceSplitUtils {

	// 默认除法运算精度
	private static final Integer DEF_DIV_SCALE = 30;

	public static final int detailsNumber = 9999999;

	/**
	 * 处理折扣行，将有折扣行的数据合并成一行，另外一个
	 * 
	 * @param jymxsqs
	 *            交易明细申请数据
	 * @return
	 * @throws Exception
	 */
	public static Map dealDiscountLine(List<JyspmxDecimal2> jymxsqs) {
		Map zkAndbzk = new HashMap();
		List<JyspmxDecimal2> resultList = new ArrayList<JyspmxDecimal2>();
		for (int i = 0; i < jymxsqs.size(); i++) {
			JyspmxDecimal2 jymxsq = new JyspmxDecimal2(jymxsqs.get(i));
			JyspmxDecimal2 jymxsq2 = new JyspmxDecimal2(jymxsqs.get(i));
			if (jymxsq.getFphxz().equals("2")) {
				List list = new ArrayList();
				list.add(jymxsq2);
				// zkAndbzk.put(jymxsq.getSpmxxh(), jymxsq);
				JyspmxDecimal2 jymxsqzk = jymxsqs.get(i + 1);// 有点问题，如果list顺序不对就有问题
				BigDecimal spje = (BigDecimal) ((null == jymxsq.getSpje() || jymxsq.getSpje().equals("")
						|| jymxsq.getSpje().equals("null")) ? 0.00 : jymxsq.getSpje());
				BigDecimal spje2 = (BigDecimal) ((null == jymxsqzk.getSpje() || jymxsqzk.getSpje().equals("")
						|| jymxsqzk.getSpje().equals("null")) ? 0.00 : jymxsqzk.getSpje());

				BigDecimal spse = (BigDecimal) ((null == jymxsq.getSpse() || jymxsq.getSpse().equals("")
						|| jymxsq.getSpse().equals("null")) ? 0.00 : jymxsq.getSpse());
				BigDecimal spse2 = (BigDecimal) ((null == jymxsqzk.getSpse() || jymxsqzk.getSpse().equals("")
						|| jymxsqzk.getSpse().equals("null")) ? 0.00 : jymxsqzk.getSpse());

				BigDecimal jshj = (BigDecimal) ((null == jymxsq.getJshj() || jymxsq.getJshj().equals("")
						|| jymxsq.getJshj().equals("null")) ? 0.00 : jymxsq.getJshj());
				BigDecimal jshj2 = (BigDecimal) ((null == jymxsqzk.getJshj() || jymxsqzk.getJshj().equals("")
						|| jymxsqzk.getJshj().equals("null")) ? 0.00 : jymxsqzk.getJshj());
				jymxsq.setSpje(spje.add(spje2));
				jymxsq.setSpse(spse.add(spse2));
				jymxsq.setJshj(jshj.add(jshj2));
				resultList.add(jymxsq);
				list.add(jymxsqzk);
				zkAndbzk.put(jymxsq.getSpmxxh(), list);
			}else if(jymxsq.getFphxz().equals("0")){
				resultList.add(jymxsq);
			}
		}
		/*for (int j = 0; j < resultList.size(); j++) {
			JyspmxDecimal2 jymxsq2 = resultList.get(j);
			if (jymxsq2.getFphxz().equals("1")) {
				resultList.remove(jymxsq2);
			}
		}*/
		Map result = new HashMap();
		result.put("jymxsqs", resultList);
		result.put("zkAndbzk", zkAndbzk);
		return result;
	}

	/**
	 * 按商品整数来分票(不含税)
	 */
	private static Map<String, BigDecimal> cfsl_byInt(BigDecimal spdj,BigDecimal spje,BigDecimal spsl, BigDecimal spse, BigDecimal jshj,BigDecimal ccje,BigDecimal kce) {

		Map<String, BigDecimal> resultMap = new HashMap<String, BigDecimal>();
		BigDecimal cfsm = div(spje, spdj);
		if(div(spje, spdj).compareTo(BigDecimal.ONE)<0){
			cfsm =BigDecimal.valueOf(1);
			spdj = div(spje, cfsm);
		}
		BigDecimal cfsm1 = cfsm;// 未取整的数量
		BigDecimal cfje_int;// 取整后的金额
		//BigDecimal ccje;// 取整后多出的金额 cfje = cfje_int,ccje
		BigDecimal cfse_int;
		BigDecimal cfjshj_int;
		BigDecimal cfkce_int = BigDecimal.ZERO;
		cfsm = new BigDecimal(Math.floor(cfsm.doubleValue()));// 整数数量，向下取整

		cfje_int = mul(spdj, cfsm).setScale(2, BigDecimal.ROUND_HALF_UP);// 整数数量对应的金额
		if(kce.compareTo(BigDecimal.ZERO)>0){
			BigDecimal cfbl_int = div(cfje_int,spje);
			cfkce_int = mul(kce,cfbl_int).setScale(2, BigDecimal.ROUND_HALF_UP);
			cfse_int = mul(sub(cfje_int,cfkce_int),spsl).setScale(2, BigDecimal.ROUND_HALF_UP);
		}else {
			cfse_int =mul(cfje_int,spsl).setScale(2, BigDecimal.ROUND_HALF_UP); //整数数量对应拆分税额
			ccje = add(sub(spje, cfje_int),ccje.setScale(2, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP);// 多出数量的金额(不含税)
		}
		cfjshj_int = add(cfje_int,cfse_int).setScale(2, BigDecimal.ROUND_HALF_UP);
		if (cfsm.equals(BigDecimal.ZERO)) {
			resultMap.put("cfsm", cfsm1);
			resultMap.put("cfje", spje);
			resultMap.put("cfse", spse);
			resultMap.put("cfjshj", jshj);
			resultMap.put("ccje", BigDecimal.ZERO);
			resultMap.put("cfkce",kce);
		} else {
			resultMap.put("cfsm", cfsm);
			resultMap.put("cfje", cfje_int);
			resultMap.put("cfse",cfse_int);
			resultMap.put("cfjshj", cfjshj_int);
			resultMap.put("ccje", ccje);
			resultMap.put("cfkce", cfkce_int);
		}

		return resultMap;
	}
	
	/**
	 * 按商品整数来分票(含税)
	 */
	private static Map<String, BigDecimal> cfsl_hsbyInt(BigDecimal spdj,BigDecimal spje, BigDecimal spse, BigDecimal jshj,BigDecimal ccje) {

		Map<String, BigDecimal> resultMap = new HashMap<String, BigDecimal>();
		BigDecimal cfsm = div(spje, spdj);
		BigDecimal cfsm1 = cfsm;// 未取整的数量
		BigDecimal cfje_int;// 取整后的金额
		//BigDecimal ccje;// 取整后多出的金额 cfje = cfje_int,ccje
		BigDecimal cfse_int;
		BigDecimal cfjshj_int;
		cfsm = new BigDecimal(Math.floor(cfsm.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue()));// 整数数量，向下取整

		cfje_int = mul(spdj, cfsm);// 整数数量对应的金额
       
		
		cfse_int =mul(spse,div(cfje_int,spje)); //整数数量对应拆分税额
		BigDecimal ccjetmp = sub(spje, cfje_int).compareTo(BigDecimal.ZERO)<0?BigDecimal.ZERO:sub(spje, cfje_int);

		ccje = add(ccjetmp,ccje);// 多出数量的金额(含税)
		cfjshj_int = add(cfje_int,cfse_int);
		if (cfsm.equals(BigDecimal.ZERO)) {
			resultMap.put("cfsm", cfsm1);
			resultMap.put("cfje", spje);
			resultMap.put("cfse", spse);
			resultMap.put("cfjshj", jshj);
			resultMap.put("ccje", BigDecimal.ZERO);
		} else {
			resultMap.put("cfsm", cfsm);
			resultMap.put("cfje", cfje_int);
			resultMap.put("cfse",cfse_int);
			resultMap.put("cfjshj", cfjshj_int);
			resultMap.put("ccje", ccje);
		}

		return resultMap;
	}

	/**
	 * 不包含全折明细的不含税分票方法
	 *
	 * @param jyspmxs
	 *            交易明细申请数据
	 * @param maxje
	 *            开票最大金额
	 * @param fpje
	 *            分票金额
	 * @param mxsl
	 *            明细数量
	 * @param qzfp
	 *            是否强制分票
	 * @param spzsfp
	 *            是否按着商品整数分票
	 * @return
	 * @throws Exception
	 */
	public static List<JyspmxDecimal2> splitInvoicesWithOutAllDiscount(List<JyspmxDecimal2> jyspmxs, Map zkAndbkzMap, BigDecimal maxje,String zsfs,
													 BigDecimal fpje, int mxsl, boolean qzfp, boolean spzsfp,int fpnum, List<JyspmxDecimal2> splitKpspmxs)
	{

		maxje = maxje.setScale(2, BigDecimal.ROUND_HALF_UP);
		fpje = fpje.setScale(2, BigDecimal.ROUND_HALF_UP);
		List<JyspmxDecimal2> jyspmxsResult = new  ArrayList<JyspmxDecimal2>(jyspmxs);//下次需要处理的明细数据
		Map mapResult = new HashMap(zkAndbkzMap);//下次需要处理的折扣数据
		while (null == jyspmxs || jyspmxs.isEmpty()) {
			//解决分票后，价税合计、金额、税额存在尾差不相符的问题
			//splitKpspmxs = reSeparatePrice(splitKpspmxs);
			return splitKpspmxs;
		}

		fpnum ++;
		int mxnum = detailsNumber;
		if (mxsl != 0 && mxsl <= detailsNumber) {
			mxnum = mxsl;
		}
		List<JyspmxDecimal2> tempJyspmxs = new ArrayList<JyspmxDecimal2>();// 缓存商品明细表
		BigDecimal zje = new BigDecimal(0);// 汇总金额
		BigDecimal total = new BigDecimal(0);
		for (JyspmxDecimal2 jyspmx : jyspmxs) {
			total = total.add(jyspmx.getSpje());
		}
		if (qzfp) {
			if (maxje.compareTo(fpje) > 0) {
				maxje = fpje;
			}
		} else {
			if (total.compareTo(maxje) > 0) {
				maxje = fpje;
			}
		}
		int sqlsh;
		int spmxxh;
		String fphxz;
		String spdm;
		int count = 0;//当前处理的明细行数量
		int count_next  = 0; //下一条处理到达的明细行
		for (int i = 0; i < jyspmxs.size(); i++) {
			JyspmxDecimal2 jyspmx = new JyspmxDecimal2(jyspmxs.get(i));
			sqlsh = jyspmx.getsqlsh();
			fphxz = jyspmx.getFphxz();
			spmxxh = jyspmx.getSpmxxh();
			//tempJyspmxs.add(jyspmx);


			zje = zje.add(jyspmx.getSpje().setScale(2, BigDecimal.ROUND_HALF_UP));
			spdm = jyspmx.getSpdm();
			//int count = tempJyspmxs.size();// 记录当前tempJyspmxs行数
			if (jyspmx.getFphxz().equals("0")) {
				count++;
			} else {
				count = count + 2;
			}
			if(i!=jyspmxs.size()-1){
				if(jyspmxs.get(i+1).getFphxz().equals("2")){
					count_next = count + 2;
				}else{
					count_next = count + 1;
				}
			}
			if (zje.compareTo(maxje.setScale(2, BigDecimal.ROUND_HALF_UP)) >= 0 || count_next > mxnum) {// 1.当前明细需要分票，是该发票最后一条明细。
				if ((count_next > mxnum && zje.compareTo(maxje.setScale(2, BigDecimal.ROUND_HALF_UP)) < 0) || zje.compareTo(maxje.setScale(2, BigDecimal.ROUND_HALF_UP)) == 0) {
					// 1.1需要分票，当前明细不需要拆分。
					// 达到每张发票开具最大条数，并且总金额未超出上限。

					if (jyspmx.getFphxz().equals("2")) {
						List<JyspmxDecimal2> zkAndbzkList = (List) zkAndbkzMap.get(jyspmx.getSpmxxh());
						for (int j = 0; j < zkAndbzkList.size(); j++) {
							zkAndbzkList.get(j).setFpnum(fpnum);
							splitKpspmxs.add(zkAndbzkList.get(j));
						}
						mapResult.remove(jyspmx.getSpmxxh());
					} else {
						jyspmx.setFpnum(fpnum);
						splitKpspmxs.add(jyspmx);
					}
					jyspmxsResult.remove(0);
				} else {
					// 1.2需要分票，当前明细需要拆分。
					// 1.2.1当这条明细为被折扣行时。
					if (jyspmx.getFphxz().equals("2")) {

						// 获取折扣行和被折扣行数据
						List<JyspmxDecimal2> zkAndbzkList = (List) zkAndbkzMap.get(jyspmx.getSpmxxh());
						// JyspmxDecimal2 cfjyspmxbzk = new JyspmxDecimal2();
						JyspmxDecimal2 cfjyspmxzk = new JyspmxDecimal2();
						BigDecimal spje = jyspmx.getSpje().setScale(2, BigDecimal.ROUND_HALF_UP);// 原商品金额
						BigDecimal spjshj= jyspmx.getJshj().setScale(2, BigDecimal.ROUND_HALF_UP);// 原加税合计

						BigDecimal ccje = sub(zje, maxje);// 超出金额
						// 当前行商品拆分出留在下张发票金额

						BigDecimal cfje = sub(spje, ccje);// 拆分金额
						// 当前行商品拆分出留在当前发票金额
						BigDecimal cfbl = div(spje, cfje);// 拆分比例
						BigDecimal cfsm = BigDecimal.ZERO;

						BigDecimal cfzje = cfje ;// 分配后剩下的金额（明细）

						BigDecimal cfzse = mul(cfzje,jyspmx.getSpsl()).setScale(2, BigDecimal.ROUND_HALF_UP);

						BigDecimal syspje =BigDecimal.ZERO;
						BigDecimal syjshj =BigDecimal.ZERO;

						for (int j = 0; j < zkAndbzkList.size(); j++) {

							JyspmxDecimal2 cfjyspmxtmp = zkAndbzkList.get(j);
							JyspmxDecimal2 cfjyspmx = new JyspmxDecimal2();

							String s_fphxz = cfjyspmxtmp.getFphxz();

							// 商品名称
							String spmc = cfjyspmxtmp.getSpmc();
							// 规格型号
							String spggxh = cfjyspmxtmp.getSpggxh();
							// 单位
							String spdw = cfjyspmxtmp.getSpdw();
							// 单价
							BigDecimal spdj = cfjyspmxtmp.getSpdj();
							// 税率
							BigDecimal spsl = cfjyspmxtmp.getSpsl().setScale(2, BigDecimal.ROUND_HALF_UP);

							BigDecimal spsm = BigDecimal.ZERO;
							BigDecimal spse;


							spse = cfjyspmxtmp.getSpse().setScale(2, BigDecimal.ROUND_HALF_UP);// 原商品税额
							// BigDecimal ccje_bzk;//被折扣行超出金额
							spmxxh = cfjyspmxtmp.getSpmxxh();
							BigDecimal cfse = BigDecimal.ZERO;
							BigDecimal cfjshj = BigDecimal.ZERO;
							spsm = cfjyspmxtmp.getSps();// 原商品数量

							cfsm = div(spsm, cfbl);// 拆分数量,保留6位解决误差

							cfje = div(cfjyspmxtmp.getSpje(), cfbl,2);// 拆分金额
							cfse = mul(cfje,spsl).setScale(2, BigDecimal.ROUND_HALF_UP);

							cfjshj = add(cfje, cfse).setScale(2, BigDecimal.ROUND_HALF_UP);
							//cfjshj = div(cfjyspmxtmp.getJshj(),cfbl,2);
							//cfse = sub(cfjshj,cfje).setScale(2, BigDecimal.ROUND_HALF_UP);
							if (s_fphxz.equals("2")) {
								// 被折扣行处理


								// 被折扣行的商品金额按比例计算cfje =
								// div(cfjyspmxtmp.getSpje(),cfbl),得出保留在当前发票的被折扣行金额
								// ccje_bzk = sub(cfjyspmxtmp.getSpje(),
								// cfje);// 超出金额

								/**
								 * 按商品整数来分票
								 */
								if (spzsfp && null != spdj && !"".equals(spdj) && null != spsm && !"".equals(spsm)) {
									// 拆分整数数量处理
									Map cfslMap = cfsl_byInt(spdj,cfje,spsl,cfse,cfjshj,ccje,BigDecimal.ZERO);
									cfsm = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfsm"))));// 拆分整数数量
									if((null ==splitKpspmxs || splitKpspmxs.isEmpty()) && Math.floor(cfsm.doubleValue()) == 0){
										splitKpspmxs = splitInvoicesWithOutAllDiscount(new ArrayList<JyspmxDecimal2>(), mapResult, maxje,zsfs, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
										splitKpspmxs.clear();
										jyspmxsResult.clear();
										return splitKpspmxs;
									}else{
										if(Math.floor(cfsm.doubleValue()) == 0){
											for (int s = 0; s < splitKpspmxs.size(); s++) {
												JyspmxDecimal2 temp = splitKpspmxs.get(s);
												if (temp.getFpnum() == fpnum) {
													splitKpspmxs = splitInvoicesWithOutAllDiscount(jyspmxsResult, mapResult, maxje, zsfs,fpje, mxsl,
															qzfp, spzsfp, fpnum, splitKpspmxs);
													return splitKpspmxs;
												} else {
													splitKpspmxs = splitInvoicesWithOutAllDiscount(new ArrayList<JyspmxDecimal2>(), mapResult, maxje,zsfs, fpje, mxsl,
															qzfp, spzsfp, fpnum, splitKpspmxs);
													splitKpspmxs.clear();
													jyspmxsResult.clear();
													return splitKpspmxs;
												}
											}
										}
									}
									cfje = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfje")))).setScale(2, BigDecimal.ROUND_HALF_UP);// 对应整数数量金额
									cfse =  BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfse")))).setScale(2, BigDecimal.ROUND_HALF_UP);// 对应整数数量税额
									cfjshj =  BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfjshj")))).setScale(2, BigDecimal.ROUND_HALF_UP);// 对应整数数量价税合计
									ccje =  BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("ccje")))).setScale(2, BigDecimal.ROUND_HALF_UP);// 对应整数数量金额

									//BigDecimal wcbl = div(div(cfjyspmxtmp.getSpje(), cfbl), cfje);//原拆分金额=div(cfjyspmxtmp.getSpje(), cfbl)
									//wcbl表示原cfje和整数拆分后cfje的比例
									//cfbl = mul(wcbl,cfbl);// 原金额除以整数数量对应金额重新计算比例用于折扣行比例计算
								}
							} else {
								// 折扣行处理
								cfje = div(cfjyspmxtmp.getSpje(), cfbl,2);
								//ccje = add(ccje,sub(cfjyspmxtmp.getSpje(),cfje));
							}
							//20180522kzx注释
							/*if(j==zkAndbzkList.size()-1){
								cfje = cfzje;
								cfse = mul(cfje,spsl).setScale(2, BigDecimal.ROUND_HALF_UP);
							}else{*/
							cfzje = sub(cfzje,cfje).setScale(2, BigDecimal.ROUND_HALF_UP);
							//cfse = div(spse, cfbl,2);// 拆分税额
							//存在误差，改用je*sl
							//cfzse = mul(cfzje,spsl).setScale(2, BigDecimal.ROUND_HALF_UP);
							//}

							// ccjyspmx = jyspmx;//超出金额对象
							cfjyspmx.setFphxz(s_fphxz);
							cfjyspmx.setSqlsh(sqlsh);
							cfjyspmx.setSpmxxh(spmxxh);
							cfjyspmx.setSpje(cfje.setScale(2, BigDecimal.ROUND_HALF_UP));
							if(null != cfsm && !cfsm.equals("") && s_fphxz.equals("2")){
								cfjyspmx.setSps(cfsm.setScale(6, BigDecimal.ROUND_HALF_UP));
							}else{
								if(!s_fphxz.equals("1"))
									cfjyspmx.setSps(cfsm);
							}

							cfjyspmx.setSpse(cfse.setScale(2, BigDecimal.ROUND_HALF_UP));
							cfjyspmx.setFpnum(fpnum);
							cfjyspmx.setSpmc(spmc);
							cfjyspmx.setSpggxh(spggxh);
							cfjyspmx.setSpdw(spdw);
							if(null!=cfsm && !cfsm.equals("") && (!cfsm.equals("")&&null!=cfsm&&cfsm.doubleValue()!=0) && s_fphxz.equals("2")){
								cfjyspmx.setSpdj(div(cfje, cfsm.setScale(6, BigDecimal.ROUND_HALF_UP),15));
							}
							cfjyspmx.setSpsl(spsl);
							cfjyspmx.setJshj(cfjshj);
							cfjyspmx.setYkphj(new BigDecimal(0));
							cfjyspmx.setSpdm(spdm);
							cfjyspmx.setGsdm(jyspmx.getGsdm());
							cfjyspmx.setYhzcbs(jyspmx.getYhzcbs());
							cfjyspmx.setYhzcmc(jyspmx.getYhzcmc());
							cfjyspmx.setKce(jyspmx.getKce());
							cfjyspmx.setLslbz(jyspmx.getLslbz());
							splitKpspmxs.add(cfjyspmx);
							//spje = sub(spje,cfje);//拆分出来的商品金额
							//spjshj = sub(spjshj,cfjshj);//拆分出来的价税合计
							cfjyspmxtmp.setSpje(sub(cfjyspmxtmp.getSpje(),cfje).setScale(2, BigDecimal.ROUND_HALF_UP));
							if (null != spdj && !"".equals(spdj) && null != spsm && !"".equals(spsm) && s_fphxz.equals("2")) {
								cfsm = (null==cfsm?BigDecimal.ZERO:cfsm);
								//cfjyspmxtmp.setSps(sub(cfjyspmxtmp.getSps(),cfsm).setScale(6, BigDecimal.ROUND_HALF_UP));
								//cfjyspmxtmp.setSpdj(div(cfjyspmxtmp.getSpje(),cfjyspmxtmp.getSps(),15));
								BigDecimal sysl = sub(jyspmx.getSps(),cfsm).setScale(6, BigDecimal.ROUND_HALF_UP);//用于处理整数拆分
								cfjyspmxtmp.setSps(sysl.doubleValue()<=0?BigDecimal.ONE.setScale(6, BigDecimal.ROUND_HALF_UP):sysl);
								cfjyspmxtmp.setSpdj(div(cfjyspmxtmp.getSpje(),cfjyspmxtmp.getSps()).setScale(15, BigDecimal.ROUND_HALF_UP));
							}
							cfjyspmxtmp.setSpse(sub(cfjyspmxtmp.getSpse(),cfse).setScale(2, BigDecimal.ROUND_HALF_UP));
							cfjyspmxtmp.setJshj(sub(cfjyspmxtmp.getJshj(),cfjshj).setScale(2, BigDecimal.ROUND_HALF_UP));
							cfjyspmxtmp = reSeparatePrice(cfjyspmxtmp);//剩余下一条再做价税分离（20171120折扣行重新价税分离造成金额变化）

							syspje = add(syspje,cfjyspmxtmp.getSpje()).setScale(2, BigDecimal.ROUND_HALF_UP);
							syjshj= add(syjshj,cfjyspmxtmp.getJshj()).setScale(2, BigDecimal.ROUND_HALF_UP);

						}

						//jyspmxsResult.remove(jyspmx);
						for(int j=0;j<jyspmxsResult.size();j++){
							JyspmxDecimal2 cfjyspmxtmp = jyspmxsResult.get(j);
							if(cfjyspmxtmp.getSpmxxh()==jyspmx.getSpmxxh() && cfjyspmxtmp.getsqlsh()==jyspmx.getsqlsh()){
								cfjyspmxtmp.setSpje(syspje.setScale(2, BigDecimal.ROUND_HALF_UP));
								cfjyspmxtmp.setJshj(syjshj.setScale(2, BigDecimal.ROUND_HALF_UP));
								cfjyspmxtmp.setSpse(sub(syjshj,syspje).setScale(2, BigDecimal.ROUND_HALF_UP));
								if(null == jyspmx.getSps() || jyspmx.getSps().equals("")||(null != jyspmx.getSps() && !jyspmx.getSps().equals("")&&jyspmx.getSps().doubleValue() ==0) ||jyspmx.getFphxz().equals("1")){

								}else{
									cfsm = (null==cfsm?BigDecimal.ZERO:cfsm);
									BigDecimal sysl = sub(jyspmx.getSps(),cfsm).setScale(6, BigDecimal.ROUND_HALF_UP);//用于处理整数拆分
									cfjyspmxtmp.setSps(sysl.doubleValue()<=0?BigDecimal.ONE.setScale(6, BigDecimal.ROUND_HALF_UP):sysl);
									cfjyspmxtmp.setSpdj(div(cfjyspmxtmp.getSpje(),cfjyspmxtmp.getSps()).setScale(15, BigDecimal.ROUND_HALF_UP));
									/*cfjyspmxtmp.setSps(sub(jyspmx.getSps(),cfsm).setScale(6, BigDecimal.ROUND_HALF_UP));
									cfjyspmxtmp.setSpdj(div(cfjyspmxtmp.getSpje(),cfjyspmxtmp.getSps(),15));*/
								}
							}
						}

					} else {
						// 1.2.2 该行不是折扣行，按着原来处理方法。
						// Jyspmx ccjyspmx = new Jyspmx();//超出金额对象
						JyspmxDecimal2 cfjyspmx = new JyspmxDecimal2();// 拆分金额对象
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

						BigDecimal spjshj = jyspmx.getJshj().setScale(2, BigDecimal.ROUND_HALF_UP);
						// 税率
						BigDecimal spsl = jyspmx.getSpsl();
						BigDecimal spje = jyspmx.getSpje().setScale(2, BigDecimal.ROUND_HALF_UP);// 原商品金额
						BigDecimal spsm = jyspmx.getSps();// 原商品数量
						BigDecimal spse = jyspmx.getSpse()==null?BigDecimal.ZERO:jyspmx.getSpse().setScale(2, BigDecimal.ROUND_HALF_UP);// 原商品税额

						BigDecimal kce = null == jyspmx.getKce()?BigDecimal.ZERO:jyspmx.getKce().setScale(2, BigDecimal.ROUND_HALF_UP);

						BigDecimal ccje = sub(zje, maxje).setScale(2, BigDecimal.ROUND_HALF_UP);// 超出金额

						BigDecimal cfje = sub(spje, ccje).setScale(2, BigDecimal.ROUND_HALF_UP);// 拆分金额

						BigDecimal cfbl = div(spje, cfje);// 拆分比例
						/**
						 * 按商品整数来分票
						 */
						BigDecimal cfsm;
						BigDecimal cfse;
						BigDecimal cfjshj;
						BigDecimal cfkce = kce;
						cfsm = div(spsm, cfbl);// 拆分数量
						//cfje = div(spje, cfbl);// 拆分金额
						if(zsfs.equals("2")){
							cfkce = div(kce,cfbl).setScale(2,BigDecimal.ROUND_HALF_UP);//拆分扣除额
							cfse = mul(sub(cfje,cfkce),spsl).setScale(2, BigDecimal.ROUND_HALF_UP).compareTo(BigDecimal.ZERO)<0?BigDecimal.ZERO:mul(sub(cfje,cfkce),spsl).setScale(2, BigDecimal.ROUND_HALF_UP);
						}else{
							cfse = mul(cfje,spsl).setScale(2, BigDecimal.ROUND_HALF_UP);
						}


						cfjshj = add(cfje,cfse).setScale(2, BigDecimal.ROUND_HALF_UP);
						//cfse = div(spse, cfbl).setScale(2, BigDecimal.ROUND_HALF_UP);// 拆分税额
						//cfjshj = add(cfje, cfse).setScale(2, BigDecimal.ROUND_HALF_UP);
						if (spzsfp && null != spdj && !"".equals(spdj) && null != spsm && !"".equals(spsm)) {

							// 拆分整数数量处理
							Map cfslMap = cfsl_byInt(spdj,cfje,spsl,cfse,cfjshj,ccje,cfkce);
							cfsm = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfsm"))));// 拆分整数数量
							if((null ==splitKpspmxs || splitKpspmxs.isEmpty()) && Math.floor(cfsm.doubleValue()) == 0){
								splitKpspmxs = splitInvoicesWithOutAllDiscount(new ArrayList<JyspmxDecimal2>(), mapResult, maxje,zsfs, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
								splitKpspmxs.clear();
								jyspmxsResult.clear();
								return splitKpspmxs;
							}else{
								if(Math.floor(cfsm.doubleValue()) == 0){
									for (int s = 0; s < splitKpspmxs.size(); s++) {
										JyspmxDecimal2 temp = splitKpspmxs.get(s);
										if (temp.getFpnum() == fpnum) {
											splitKpspmxs = splitInvoicesWithOutAllDiscount(jyspmxsResult, mapResult, maxje,zsfs, fpje, mxsl,
													qzfp, spzsfp, fpnum, splitKpspmxs);
											return splitKpspmxs;
										} else {
											splitKpspmxs = splitInvoicesWithOutAllDiscount(new ArrayList<JyspmxDecimal2>(), mapResult, maxje,zsfs, fpje, mxsl,
													qzfp, spzsfp, fpnum, splitKpspmxs);
											splitKpspmxs.clear();
											jyspmxsResult.clear();
											return splitKpspmxs;

										}
									}
								}
							}
							cfje = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfje")))).setScale(2, BigDecimal.ROUND_HALF_UP);// 对应整数数量金额
							cfse = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfse")))).setScale(2, BigDecimal.ROUND_HALF_UP);// 对应整数数量税额
							cfjshj = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfjshj")))).setScale(2, BigDecimal.ROUND_HALF_UP);// 对应整数数量价税合计
							ccje = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("ccje")))).setScale(2, BigDecimal.ROUND_HALF_UP);// 对应整数数量后的超出金额
							cfkce = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfkce")))).setScale(2, BigDecimal.ROUND_HALF_UP);// 对应整数数量扣除额
							/*ccje = add(ccje,
							 BigDecimal.valueOf((long)
							 cfslMap.get("ccje")));*/
							BigDecimal wcbl = div(div(spje, cfbl), cfje);//原拆分金额=div(cfjyspmxtmp.getSpje(), cfbl)
							//wcbl表示原cfje和整数拆分后cfje的比例
							cfbl = mul(wcbl,cfbl);// 原金额除以整数数量对应金额重新计算比例用于折扣行比例计算
						}




						cfjyspmx.setFphxz(fphxz);
						cfjyspmx.setSqlsh(sqlsh);
						cfjyspmx.setSpmxxh(spmxxh);
						cfjyspmx.setSpje(cfje);
						if(null !=cfsm && !cfsm.equals("")){
							cfjyspmx.setSps(cfsm.setScale(6, BigDecimal.ROUND_HALF_UP));
						}else{
							cfjyspmx.setSps(cfsm);
						}
						cfjyspmx.setSpse(cfse);
						cfjyspmx.setFpnum(fpnum);
						cfjyspmx.setSpmc(spmc);
						cfjyspmx.setSpggxh(spggxh);
						cfjyspmx.setSpdw(spdw);
						if(null!= cfsm &&!cfsm.equals("") && (null !=cfsm &&!cfsm.equals("")&&cfsm.doubleValue()!=0)){
							cfjyspmx.setSpdj(div(cfje, cfsm.setScale(6, BigDecimal.ROUND_HALF_UP)).setScale(15, BigDecimal.ROUND_HALF_UP));
						}
						cfjyspmx.setSpsl(spsl);
						cfjyspmx.setJshj(cfjshj);
						cfjyspmx.setYkphj(new BigDecimal(0));
						cfjyspmx.setSpdm(spdm);
						cfjyspmx.setGsdm(jyspmx.getGsdm());
						cfjyspmx.setYhzcbs(jyspmx.getYhzcbs());
						cfjyspmx.setYhzcmc(jyspmx.getYhzcmc());
						cfjyspmx.setKce(cfkce);
						cfjyspmx.setLslbz(jyspmx.getLslbz());
						splitKpspmxs.add(cfjyspmx);
						for(int j=0;j<jyspmxsResult.size();j++){
							JyspmxDecimal2 cfjyspmxtmp = jyspmxsResult.get(j);
							if(cfjyspmxtmp.getSpmxxh()==jyspmx.getSpmxxh() && cfjyspmxtmp.getsqlsh()==jyspmx.getsqlsh()){
								cfjyspmxtmp.setSpje(ccje.setScale(2, BigDecimal.ROUND_HALF_UP));
								cfjyspmxtmp.setJshj(sub(spjshj,cfjshj).setScale(2, BigDecimal.ROUND_HALF_UP));
								cfjyspmxtmp.setSpse(sub(sub(spjshj,cfjshj),ccje).setScale(2, BigDecimal.ROUND_HALF_UP));
								cfjyspmxtmp.setKce(sub(kce,cfkce).setScale(2,BigDecimal.ROUND_HALF_UP));
								if(null == jyspmx.getSps() || jyspmx.getSps().equals("")||(!jyspmx.getSps().equals("")&&jyspmx.getSps().doubleValue() ==0)){

								}else{
									if(cfjyspmxtmp.getSpje().doubleValue()>0d){
										cfsm = (null==cfsm?BigDecimal.ZERO:cfsm);
										BigDecimal sysl = sub(jyspmx.getSps(),cfsm).setScale(6, BigDecimal.ROUND_HALF_UP);//用于处理整数拆分
										cfjyspmxtmp.setSps(sysl.doubleValue()<=0?BigDecimal.ONE.setScale(6, BigDecimal.ROUND_HALF_UP):sysl);
										cfjyspmxtmp.setSpdj(div(cfjyspmxtmp.getSpje(),cfjyspmxtmp.getSps()).setScale(15, BigDecimal.ROUND_HALF_UP));
									}
								}

							}
							//如果分票刚刚好分完最后一条数据，则剩余list清除
							if(cfjyspmxtmp.getSpje().doubleValue()<=0d){
								jyspmxsResult.remove(j);
								continue;
							}else{
								if(!zsfs.equals("2"))
									cfjyspmxtmp = reSeparatePrice(cfjyspmxtmp);//剩余下一条再做价税分离
							}

						}
					}
				}
				break;
			} else {// 2.当前明细不需要分票，不是该发票最后一条明细
				if (jyspmx.getFphxz().equals("2")) {
					List<JyspmxDecimal2> zkAndbzkList = (List) zkAndbkzMap.get(jyspmx.getSpmxxh());
					for (int j = 0; j < zkAndbzkList.size(); j++) {
						zkAndbzkList.get(j).setFpnum(fpnum);
						splitKpspmxs.add(zkAndbzkList.get(j));
					}
					mapResult.remove(jyspmx.getSpmxxh());
				} else {
					jyspmx.setFpnum(fpnum);
					splitKpspmxs.add(jyspmx);
				}
				jyspmxsResult.remove(0);
			}
			//


		}
		splitKpspmxs = splitInvoicesWithOutAllDiscount(jyspmxsResult, mapResult, maxje,zsfs, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
		return splitKpspmxs;
	}

    /**
     *
     * 含有全部折扣明细时，分票后的结果数据
     * @param splitKpspmxs
     * @param qzmxMap
     * @param mxsl
     * @return
     */
	public static List<JyspmxDecimal2> splitInvoicesWithAllDiscount(List<JyspmxDecimal2> splitKpspmxs,Map qzmxMap,List<Integer> qzmxList,int mxsl,boolean zsfp ){
	    if(!qzmxMap.isEmpty()){
			Map<Integer, List<JyspmxDecimal2>> map = new HashMap<>();
			for (JyspmxDecimal2 jyspmxDecimal2 : splitKpspmxs) {
				if (map.containsKey(jyspmxDecimal2.getFpnum())) {
					List<JyspmxDecimal2> rList = map.get(jyspmxDecimal2.getFpnum());
					rList.add(jyspmxDecimal2);
				} else {
					List<JyspmxDecimal2> rList = new ArrayList<>();
					rList.add(jyspmxDecimal2);
					map.put(jyspmxDecimal2.getFpnum(), rList);
				}
			}
			//全折list不为空时，将明细往每张发票里面存放。
			QzMxTmpVo qzMxTmpVo = new QzMxTmpVo();
			qzMxTmpVo.setSpje(BigDecimal.ZERO);
			int maxFpnum = map.size();
			if(!qzmxList.isEmpty()){
				for (Map.Entry<Integer, List<JyspmxDecimal2>> entry : map.entrySet()) {
					List<JyspmxDecimal2> fpJyspmxList = entry.getValue();
					for(int i=0;i<fpJyspmxList.size();i++){
						JyspmxDecimal2 jyspmxDecimal2 = fpJyspmxList.get(i);
						if(qzMxTmpVo.getSpje().compareTo(jyspmxDecimal2.getSpje())<0){
							qzMxTmpVo.setFpnum(jyspmxDecimal2.getFpnum());
							qzMxTmpVo.setSpje(jyspmxDecimal2.getSpje());
							qzMxTmpVo.setFphxz(jyspmxDecimal2.getFphxz());
							qzMxTmpVo.setJshj(jyspmxDecimal2.getJshj());
							//qzMxTmpVo.setMxcount(fpJyspmxList.size());
						}
					}
					int oneFpmxsl = mxsl;//每张发票允许放的最大明细数量
					if(fpJyspmxList.size()+1<oneFpmxsl && !qzmxList.isEmpty()){
						int addCount = (int)Math.floor(Double.valueOf((oneFpmxsl-fpJyspmxList.size())/2));//允许添加的明细行数/2
						oneFpmxsl = oneFpmxsl -addCount*2;//当前张发票还能存放的明细数
						List<JyspmxDecimal2> qzList = (List<JyspmxDecimal2>) qzmxMap.get(qzmxList.get(0));
						for (JyspmxDecimal2 t : qzList){
							t.setFpnum(Integer.valueOf(String.valueOf(fpJyspmxList.get(fpJyspmxList.size()-1).getFpnum())));
							t.setSpmxxh(fpJyspmxList.get(fpJyspmxList.size()-1).getSpmxxh()+1);
							splitKpspmxs.add(t);
						}
						//删除已经存放的全折明细数据。
						qzmxMap.remove(qzmxList.get(0));
						qzmxList.remove(0);
						//当全折明细为空时，跳出循环。
						if(qzmxList.isEmpty())
							return splitKpspmxs;
					}
				}
				//处理所有发票都添加全折明细，还有未处理的全折明细数据
				//定义还需要的发票张数。
				int fpzs =  (int)Math.ceil(qzmxList.size()/(mxsl-2));
				List<JyspmxDecimal2> otherList = new ArrayList<>();//抽出的list;
				for (int m=0;m<splitKpspmxs.size();m++){
					JyspmxDecimal2 tmp = splitKpspmxs.get(m);
					if(qzMxTmpVo.getFpnum() == tmp.getFpnum() && qzMxTmpVo.getSpje()==tmp.getSpje()
							&& qzMxTmpVo.getFphxz().equals(tmp.getFphxz()) && qzMxTmpVo.getJshj()==tmp.getJshj()){
						BigDecimal spje = div(tmp.getSpje(), BigDecimal.valueOf(1+fpzs)).setScale(2, BigDecimal.ROUND_HALF_UP);
						BigDecimal jshj = div(tmp.getJshj(), BigDecimal.valueOf(1+fpzs)).setScale(2, BigDecimal.ROUND_HALF_UP);
						BigDecimal spse = sub(jshj,spje);
						if(tmp.getFphxz().equals("2")){
							tmp.setSpje(spje);
							tmp.setSpse(spse);
							tmp.setJshj(jshj);
							if(tmp.getSps()!=null && tmp.getSps().compareTo(BigDecimal.ZERO)!=0){
								tmp.setSpdj(div(spje,tmp.getSps(),6));
							}
							JyspmxDecimal2 bzkTmp = splitKpspmxs.get(m+1);
							bzkTmp.setSpje(div(bzkTmp.getSpje(), BigDecimal.valueOf(1+fpzs)).setScale(2, BigDecimal.ROUND_HALF_UP));
							bzkTmp.setJshj(div(bzkTmp.getJshj(), BigDecimal.valueOf(1+fpzs)).setScale(2, BigDecimal.ROUND_HALF_UP));
							bzkTmp.setSps(sub(bzkTmp.getJshj(),bzkTmp.getSpje()));
							qzMxTmpVo.setJshj(sub(qzMxTmpVo.getJshj(),jshj));
							qzMxTmpVo.setSpje(sub(qzMxTmpVo.getSpje(),spje));
							JyspmxDecimal2 zkjyspmx = new JyspmxDecimal2(tmp);
							JyspmxDecimal2 bzkjyspmx = new JyspmxDecimal2(bzkTmp);
							otherList.add(zkjyspmx);
							otherList.add(bzkjyspmx);
						}else{
							tmp.setSpje(spje);
							tmp.setSpse(spse);
							tmp.setJshj(jshj);
							if(tmp.getSps()!=null && tmp.getSps().compareTo(BigDecimal.ZERO)!=0){
								tmp.setSpdj(div(spje,tmp.getSps(),6));
							}
							JyspmxDecimal2 zkjyspmx = new JyspmxDecimal2(tmp);
							otherList.add(zkjyspmx);
						}
                        break;
					}
				}
				for (int t=0;t<fpzs;t++){
					int spmxxh = 1;
					for (JyspmxDecimal2 o :otherList){
						o.setFpnum(maxFpnum+(t+1));
						o.setSpmxxh(spmxxh++);
						splitKpspmxs.add(o);
					}
					for(int m=0;m<qzmxList.size();m++){
						List<JyspmxDecimal2> symxList = (List)qzmxMap.get(qzmxList.get(m));
						JyspmxDecimal2 syJyspmx = symxList.get(0);
						syJyspmx.setFpnum(maxFpnum+(t+1));
						syJyspmx.setSpmxxh(spmxxh++);
						JyspmxDecimal2 syJyspmx2 = symxList.get(1);
						syJyspmx2.setFpnum(maxFpnum+(t+1));
						syJyspmx2.setSpmxxh(spmxxh++);
						splitKpspmxs.add(syJyspmx);
						splitKpspmxs.add(syJyspmx2);
						if(m>=mxsl-2) {
							break;
						}
					}
				}
			}
        }
		return splitKpspmxs;
	}

	/**
	 * 不含税分票
	 * 
	 * @param jyspmxs
	 *            交易明细申请数据
	 * @param maxje
	 *            开票最大金额
	 * @param fpje
	 *            分票金额
	 * @param mxsl
	 *            明细数量
	 * @param qzfp
	 *            是否强制分票
	 * @param spzsfp
	 *            是否按着商品整数分票
	 * @return
	 * @throws Exception
	 */
	public static List<JyspmxDecimal2> splitInvoices(List<JyspmxDecimal2> jyspmxs, Map zkAndbkzMap, BigDecimal maxje, String zsfs,
													 BigDecimal fpje, int mxsl, boolean qzfp, boolean spzsfp, int fpnum, List<JyspmxDecimal2> splitKpspmxs) {
		Map qzmxMap = new HashMap();//全折的明细信息Map
		List<Integer> qzmxList = new ArrayList<>(); //全折的明细信息List
		for(int i=0;i<jyspmxs.size();i++){
			JyspmxDecimal2 jyspmx = jyspmxs.get(i);
			if(jyspmx.getJshj().compareTo(BigDecimal.ZERO) ==0){
				qzmxList.add(jyspmx.getSpmxxh());
				qzmxMap.put(jyspmx.getSpmxxh(),zkAndbkzMap.get(jyspmx.getSpmxxh()));
				zkAndbkzMap.remove(jyspmx.getSpmxxh());
				jyspmxs.remove(i);
			}
		}
		splitInvoicesWithOutAllDiscount(jyspmxs, zkAndbkzMap, maxje, zsfs, fpje, mxsl, qzfp, spzsfp, fpnum, splitKpspmxs);
		return splitInvoicesWithAllDiscount(splitKpspmxs,qzmxMap,qzmxList,mxsl,spzsfp);
	}
	
	
	
	
	/**
	 * 含税分票
	 * 
	 * @param jyspmxs
	 *            交易明细申请数据
	 * @param maxje
	 *            开票最大金额
	 * @param fpje
	 *            分票金额
	 * @param mxsl
	 *            明细数量
	 * @param qzfp
	 *            是否强制分票
	 * @param spzsfp
	 *            是否按着商品整数分票
	 * @return
	 * @throws Exception
	 */
	public static List<JyspmxDecimal2> splitInvoiceshs(List<JyspmxDecimal2> jyspmxs,Map zkAndbkzMap, BigDecimal maxje,
			BigDecimal fpje, int mxsl, boolean qzfp, boolean spzsfp,int fpnum, List<JyspmxDecimal2> splitKpspmxs) {
	List<JyspmxDecimal2> jyspmxsResult = new  ArrayList<JyspmxDecimal2>(jyspmxs);//下次需要处理的明细数据
	Map mapResult = new HashMap(zkAndbkzMap);//下次需要处理的折扣数据
	while (null == jyspmxs || jyspmxs.isEmpty()) {
		return splitKpspmxs;
	}
	fpnum++;
	int mxnum = detailsNumber;
	if (mxsl != 0 && mxsl <= detailsNumber) {
		mxnum = mxsl;
	}
	boolean fp = false;
	//List<JyspmxDecimal2> tempJyspmxs = new ArrayList<JyspmxDecimal2>();// 缓存商品明细表
	//List<JyspmxDecimal2> splitKpspmxs = new ArrayList<JyspmxDecimal2>();// 拆分发票后的list
	
	BigDecimal zje = new BigDecimal(0);// 汇总金额不含税
	BigDecimal zje1 = new BigDecimal(0);// 汇总金额含税
	BigDecimal total = new BigDecimal(0);// 商品明细金额总和（不含税）
	for (JyspmxDecimal2 jyspmx : jyspmxs) {
		total = total.add(jyspmx.getSpje());
	}
	if (!qzfp) { // 如果不是强制分票
		if (maxje.compareTo(total) > 0) { // 开票限额（不含税）是否大于商品明细总和（不含税）
			fp = true;
		}
	}
	//int fpnum = 1;
	int sqlsh;
	int spmxxh;
	String fphxz;
	String spdm;
	int count = 0;//当前处理的明细行数量
	int count_next = 0; //下一条处理到达的明细行
	for(int i = 0; i < jyspmxs.size(); i++){

		JyspmxDecimal2 jyspmx = jyspmxs.get(i);
		sqlsh = jyspmx.getsqlsh();
		fphxz = jyspmx.getFphxz();
		spmxxh = jyspmx.getSpmxxh();
		//tempJyspmxs.add(jyspmx);
		
		zje = zje.add(jyspmx.getSpje());// 不含税商品总金额
		zje1 = zje1.add(jyspmx.getJshj());// 含税商品总金额
		spdm = jyspmx.getSpdm();
		if (jyspmx.getFphxz().equals("0")) {
			count++;
		} else {
			count = count + 2;
		}
		if(i!=jyspmxs.size()-1){
			if(jyspmxs.get(i+1).getFphxz().equals("2")){
				count_next = count + 2;
			}else{
				count_next = count + 1;
			}
		}
		if (zje.compareTo(maxje.setScale(2, BigDecimal.ROUND_HALF_UP)) >= 0 || count_next > mxnum || (zje1.compareTo(fpje.setScale(2, BigDecimal.ROUND_HALF_UP)) >= 0 && qzfp)) {
			if (count_next > mxnum && zje.compareTo(maxje.setScale(2, BigDecimal.ROUND_HALF_UP)) <= 0 && zje1.compareTo(fpje.setScale(2, BigDecimal.ROUND_HALF_UP)) <= 0) {
				// 1.1需要分票，当前明细不需要拆分。
				// 达到每张发票开具最大条数，并且总金额未超出上限。
				if (jyspmx.getFphxz().equals("2")) {
					List<JyspmxDecimal2> zkAndbzkList = (List) zkAndbkzMap.get(jyspmx.getSpmxxh());
					for (int j = 0; j < zkAndbzkList.size(); j++) {
						zkAndbzkList.get(j).setFpnum(fpnum);
						splitKpspmxs.add(zkAndbzkList.get(j));
					}
					mapResult.remove(jyspmx.getSpmxxh());
				} else {
					jyspmx.setFpnum(fpnum);
					splitKpspmxs.add(jyspmx);
				}
				jyspmxsResult.remove(0);

			} else if (fpje.divide(new BigDecimal(1).add(jyspmx.getSpsl()), 30, BigDecimal.ROUND_HALF_UP)
					.compareTo(maxje) > 0) {
				/**
				 * 当分票金额(不含税)大于开票限额时，按照开票限额来分票
				 */
				if(jyspmx.getFphxz().equals("2")){
					// 获取折扣行和被折扣行数据
					List<JyspmxDecimal2> zkAndbzkList = (List) zkAndbkzMap.get(jyspmx.getSpmxxh());
					// JyspmxDecimal2 cfjyspmxbzk = new JyspmxDecimal2();
					JyspmxDecimal2 cfjyspmxzk = new JyspmxDecimal2();
					BigDecimal spje = jyspmx.getSpje();// 原商品金额
					BigDecimal spjshj= jyspmx.getJshj();// 原加税合计

					BigDecimal ccje = sub(zje, maxje);// 超出金额
														// 当前行商品拆分出留在下张发票金额

					BigDecimal cfje = sub(spje, ccje);// 拆分金额
														// 当前行商品拆分出留在当前发票金额
					BigDecimal cfbl = div(spje, cfje);// 拆分比例
					BigDecimal cfsm = BigDecimal.ZERO;
					
					for (int j = 0; j < zkAndbzkList.size(); j++) {

						JyspmxDecimal2 cfjyspmxtmp = zkAndbzkList.get(j);
						JyspmxDecimal2 cfjyspmx = new JyspmxDecimal2();

						String s_fphxz = cfjyspmxtmp.getFphxz();

						// 商品名称
						String spmc = cfjyspmxtmp.getSpmc();
						// 规格型号
						String spggxh = cfjyspmxtmp.getSpggxh();
						// 单位
						String spdw = cfjyspmxtmp.getSpdw();
						// 单价
						BigDecimal spdj = cfjyspmxtmp.getSpdj();
						// 税率
						BigDecimal spsl = cfjyspmxtmp.getSpsl();

						BigDecimal spsm = BigDecimal.ZERO;
						BigDecimal spse;
						

						spse = cfjyspmxtmp.getSpse();// 原商品税额
						// BigDecimal ccje_bzk;//被折扣行超出金额
						spmxxh = cfjyspmxtmp.getSpmxxh();
						BigDecimal cfse = BigDecimal.ZERO;
						BigDecimal cfjshj = BigDecimal.ZERO;
						BigDecimal ccjshj = BigDecimal.ZERO;
						if (s_fphxz.equals("2")) {
							// 被折扣行处理
							spsm = cfjyspmxtmp.getSps();// 原商品数量

							cfsm = div(spsm, cfbl);// 拆分数量
							cfje = div(cfjyspmxtmp.getSpje(), cfbl);// 拆分金额
							cfse = div(spse, cfbl).setScale(2, BigDecimal.ROUND_HALF_UP);// 拆分税额
							cfjshj = add(cfje, cfse);
							// 被折扣行的商品金额按比例计算cfje =
							// div(cfjyspmxtmp.getSpje(),cfbl),得出保留在当前发票的被折扣行金额
							// ccje_bzk = sub(cfjyspmxtmp.getSpje(),
							// cfje);// 超出金额

							/**
							 * 按商品整数来分票
							 */
							if (spzsfp && null != spdj && !"".equals(spdj) && null != spsm && !"".equals(spsm)) {
								
								// 拆分整数数量处理
								Map cfslMap = cfsl_byInt(spdj,cfje,spsl,cfse,cfjshj,ccje,BigDecimal.ZERO);
								cfsm = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfsm"))));// 拆分整数数量
								
								if((null ==splitKpspmxs || splitKpspmxs.isEmpty()) && Math.floor(cfsm.doubleValue()) == 0){
									splitKpspmxs = splitInvoiceshs(new ArrayList<JyspmxDecimal2>(), mapResult, maxje, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
									splitKpspmxs.clear();
									jyspmxsResult.clear();
									return splitKpspmxs;
								}else{
									if(Math.floor(cfsm.doubleValue()) == 0){
										for (int s = 0; s < splitKpspmxs.size(); s++) {
											JyspmxDecimal2 temp = splitKpspmxs.get(s);
											if (temp.getFpnum() == fpnum) {
												splitKpspmxs = splitInvoiceshs(jyspmxsResult, mapResult, maxje, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
												return splitKpspmxs;
											} else {
												splitKpspmxs = splitInvoiceshs(new ArrayList<JyspmxDecimal2>(), mapResult, maxje, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
												splitKpspmxs.clear();
												jyspmxsResult.clear();
												return splitKpspmxs;
											}
										}
									}
								}
								
								cfje = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfje"))));// 对应整数数量金额
								cfse = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfse"))));// 对应整数数量税额
								cfjshj = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfjshj"))));// 对应整数数量价税合计
								ccje = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("ccje"))));// 对应整数数量价税合计
								BigDecimal wcbl = div(div(cfjyspmxtmp.getSpje(), cfbl), cfje);//原拆分金额=div(cfjyspmxtmp.getSpje(), cfbl)
								 //wcbl表示原cfje和整数拆分后cfje的比例
								cfbl = mul(wcbl,cfbl);// 原金额除以整数数量对应金额重新计算比例用于折扣行比例计算
							}
						} else {
							// 折扣行处理
							cfje = div(cfjyspmxtmp.getSpje(), cfbl);
							//ccje = add(ccje,sub(cfjyspmxtmp.getSpje(),cfje));
						}

						
						
						ccjshj = sub(jyspmx.getJshj(), cfjshj);// 超出价税合计
						// ccjyspmx = jyspmx;//超出金额对象
						cfjyspmx.setFphxz(s_fphxz);
						cfjyspmx.setSqlsh(sqlsh);
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
						cfjyspmx.setJshj(cfjshj);
						cfjyspmx.setYkphj(new BigDecimal(0));
						cfjyspmx.setSpdm(spdm);
						cfjyspmx.setGsdm(jyspmx.getGsdm());
						cfjyspmx.setYhzcbs(jyspmx.getYhzcbs());
						cfjyspmx.setYhzcmc(jyspmx.getYhzcmc());
						cfjyspmx.setKce(jyspmx.getKce());
						cfjyspmx.setLslbz(jyspmx.getLslbz());
						splitKpspmxs.add(cfjyspmx);
						spje = sub(spje,cfje);//拆分出来的商品金额
						spjshj = sub(spjshj,cfjshj);//拆分出来的价税合计
						cfjyspmxtmp.setSpje(sub(cfjyspmxtmp.getSpje(),cfje));
						if (null != spdj && !"".equals(spdj) && null != spsm && !"".equals(spsm)) {
						   cfjyspmxtmp.setSps(sub(cfjyspmxtmp.getSps(),cfsm));
						}
						cfjyspmxtmp.setSpse(sub(cfjyspmxtmp.getSpse(),cfse));
						cfjyspmxtmp.setJshj(sub(cfjyspmxtmp.getJshj(),cfjshj));
					}
					//jyspmxsResult.remove(jyspmx);
					for(int j=0;j<jyspmxsResult.size();j++){
						JyspmxDecimal2 cfjyspmxtmp = jyspmxsResult.get(j);
						if(cfjyspmxtmp.getSpmxxh()==jyspmx.getSpmxxh() && cfjyspmxtmp.getsqlsh()==jyspmx.getsqlsh()){
							cfjyspmxtmp.setSpje(spje.setScale(2, BigDecimal.ROUND_HALF_UP));
							cfjyspmxtmp.setJshj(spjshj.setScale(2, BigDecimal.ROUND_HALF_UP));
							cfjyspmxtmp.setSpse(sub(spjshj,spje).setScale(2, BigDecimal.ROUND_HALF_UP));					
							cfjyspmxtmp.setSps(sub(jyspmx.getSps(),cfsm));
						}
					}
					
				
				}else{

					// 1.2.2 该行不是折扣行，按着原来处理方法。
					// Jyspmx ccjyspmx = new Jyspmx();//超出金额对象
					JyspmxDecimal2 cfjyspmx = new JyspmxDecimal2();// 拆分金额对象
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
					
					BigDecimal spjshj = jyspmx.getJshj();
					// 税率
					BigDecimal spsl = jyspmx.getSpsl();
					BigDecimal spje = jyspmx.getSpje();// 原商品金额
					BigDecimal spsm = jyspmx.getSps();// 原商品数量
					BigDecimal spse = jyspmx.getSpse();// 原商品税额

					BigDecimal ccje = sub(zje, maxje);// 超出金额

					BigDecimal cfje = sub(spje, ccje);// 拆分金额
					
					BigDecimal cfbl = div(spje, cfje);// 拆分比例
					/**
					 * 按商品整数来分票
					 */
					BigDecimal cfsm;
					BigDecimal cfse;
					BigDecimal cfjshj;
					
					cfsm = div(spsm, cfbl);// 拆分数量
					cfje = div(spje, cfbl);// 拆分金额
					cfse = div(spse, cfbl).setScale(2, BigDecimal.ROUND_HALF_UP);// 拆分税额
				    cfjshj = add(cfje, cfse);
					if (spzsfp && null != spdj && !"".equals(spdj) && null != spsm && !"".equals(spsm)) {
						// 拆分整数数量处理
						Map cfslMap = cfsl_byInt(spdj,cfje,spsl,cfse,cfjshj,ccje,BigDecimal.ZERO);
						cfsm = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfsm"))));// 拆分整数数量
						
						if((null ==splitKpspmxs || splitKpspmxs.isEmpty()) && Math.floor(cfsm.doubleValue()) == 0){
							splitKpspmxs = splitInvoiceshs(new ArrayList<JyspmxDecimal2>(), mapResult, maxje, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
							splitKpspmxs.clear();
							return splitKpspmxs;
						}else{
							if(Math.floor(cfsm.doubleValue()) == 0){
								for (int s = 0; s < splitKpspmxs.size(); s++) {
									JyspmxDecimal2 temp = splitKpspmxs.get(s);
									if (temp.getFpnum() == fpnum) {
										splitKpspmxs = splitInvoiceshs(jyspmxsResult, mapResult, maxje, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
										splitKpspmxs.clear();
										jyspmxsResult.clear();
										return splitKpspmxs;
									} else {
										splitKpspmxs = splitInvoiceshs(new ArrayList<JyspmxDecimal2>(), mapResult, maxje, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
										splitKpspmxs.clear();
										jyspmxsResult.clear();
										return splitKpspmxs;
									}
								}
							}
						}
						
						cfje = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfje"))));// 对应整数数量金额
						cfse = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfse"))));// 对应整数数量税额
						cfjshj = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfjshj"))));// 对应整数数量价税合计
						ccje = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("ccje"))));// 对应整数数量价税合计
						/*ccje = add(ccje,
						 BigDecimal.valueOf((long)
						 cfslMap.get("ccje")));*/
						 BigDecimal wcbl = div(div(spje, cfbl), cfje);//原拆分金额=div(cfjyspmxtmp.getSpje(), cfbl)
						 //wcbl表示原cfje和整数拆分后cfje的比例
						cfbl = mul(wcbl,cfbl);// 原金额除以整数数量对应金额重新计算比例用于折扣行比例计算
					}

				
				     

					cfjyspmx.setFphxz(fphxz);
					cfjyspmx.setSqlsh(sqlsh);
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
					cfjyspmx.setJshj(cfjshj);
					cfjyspmx.setYkphj(new BigDecimal(0));
					cfjyspmx.setSpdm(spdm);
					cfjyspmx.setGsdm(jyspmx.getGsdm());
					cfjyspmx.setYhzcbs(jyspmx.getYhzcbs());
					cfjyspmx.setYhzcmc(jyspmx.getYhzcmc());
					cfjyspmx.setKce(jyspmx.getKce());
					cfjyspmx.setLslbz(jyspmx.getLslbz());
					splitKpspmxs.add(cfjyspmx);
					for(int j=0;j<jyspmxsResult.size();j++){
						JyspmxDecimal2 cfjyspmxtmp = jyspmxsResult.get(j);
						if(cfjyspmxtmp.getSpmxxh()==jyspmx.getSpmxxh() && cfjyspmxtmp.getsqlsh()==jyspmx.getsqlsh()){
							cfjyspmxtmp.setSpje(ccje.setScale(2, BigDecimal.ROUND_HALF_UP));
							cfjyspmxtmp.setJshj(sub(spjshj,cfjshj).setScale(2, BigDecimal.ROUND_HALF_UP));
							cfjyspmxtmp.setSpse(sub(sub(spjshj,cfjshj),ccje).setScale(2, BigDecimal.ROUND_HALF_UP));					
							cfjyspmxtmp.setSps(sub(jyspmx.getSps(),cfsm));
						}
					}
				
				}
				
			} else {
				/**
				 * 分票金额不大于开票限额
				 */
				if (fp) {
					/**
					 * 开票限额大于商品明细金额（不含税）总和时，按照开票限额分票
					 */

					if(jyspmx.getFphxz().equals("2")){
						// 获取折扣行和被折扣行数据
						List<JyspmxDecimal2> zkAndbzkList = (List) zkAndbkzMap.get(jyspmx.getSpmxxh());
						// JyspmxDecimal2 cfjyspmxbzk = new JyspmxDecimal2();
						JyspmxDecimal2 cfjyspmxzk = new JyspmxDecimal2();
						BigDecimal spje = jyspmx.getSpje();// 原商品金额
						BigDecimal spjshj= jyspmx.getJshj();// 原加税合计

						BigDecimal ccje = sub(zje, maxje);// 超出金额
															// 当前行商品拆分出留在下张发票金额

						BigDecimal cfje = sub(spje, ccje);// 拆分金额
															// 当前行商品拆分出留在当前发票金额
						BigDecimal cfbl = div(spje, cfje);// 拆分比例
						BigDecimal cfsm = BigDecimal.ZERO;
						
						for (int j = 0; j < zkAndbzkList.size(); j++) {

							JyspmxDecimal2 cfjyspmxtmp = zkAndbzkList.get(j);
							JyspmxDecimal2 cfjyspmx = new JyspmxDecimal2();

							String s_fphxz = cfjyspmxtmp.getFphxz();

							// 商品名称
							String spmc = cfjyspmxtmp.getSpmc();
							// 规格型号
							String spggxh = cfjyspmxtmp.getSpggxh();
							// 单位
							String spdw = cfjyspmxtmp.getSpdw();
							// 单价
							BigDecimal spdj = cfjyspmxtmp.getSpdj();
							// 税率
							BigDecimal spsl = cfjyspmxtmp.getSpsl();

							BigDecimal spsm = BigDecimal.ZERO;
							BigDecimal spse;
							

							spse = cfjyspmxtmp.getSpse();// 原商品税额
							// BigDecimal ccje_bzk;//被折扣行超出金额
							spmxxh = cfjyspmxtmp.getSpmxxh();
							BigDecimal cfse = BigDecimal.ZERO;
							BigDecimal cfjshj = BigDecimal.ZERO;
							BigDecimal ccjshj = BigDecimal.ZERO;
							if (s_fphxz.equals("2")) {
								// 被折扣行处理
								spsm = cfjyspmxtmp.getSps();// 原商品数量

								cfsm = div(spsm, cfbl);// 拆分数量
								cfje = div(cfjyspmxtmp.getSpje(), cfbl);// 拆分金额
								cfse = div(spse, cfbl).setScale(2, BigDecimal.ROUND_HALF_UP);// 拆分税额
								cfjshj = add(cfje, cfse);
								// 被折扣行的商品金额按比例计算cfje =
								// div(cfjyspmxtmp.getSpje(),cfbl),得出保留在当前发票的被折扣行金额
								// ccje_bzk = sub(cfjyspmxtmp.getSpje(),
								// cfje);// 超出金额

								/**
								 * 按商品整数来分票
								 */
								if (spzsfp && null != spdj && !"".equals(spdj) && null != spsm && !"".equals(spsm)) {
									// 拆分整数数量处理
									Map cfslMap = cfsl_byInt(spdj,cfje,spsl,cfse,cfjshj,ccje,BigDecimal.ZERO);
									cfsm = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfsm"))));// 拆分整数数量
									
									if((null ==splitKpspmxs || splitKpspmxs.isEmpty()) && Math.floor(cfsm.doubleValue()) == 0){
										splitKpspmxs = splitInvoiceshs(new ArrayList<JyspmxDecimal2>(), mapResult, maxje, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
										splitKpspmxs.clear();
										jyspmxsResult.clear();
										return splitKpspmxs;
									}else{
										if(Math.floor(cfsm.doubleValue()) == 0){
											for (int s = 0; s < splitKpspmxs.size(); s++) {
												JyspmxDecimal2 temp = splitKpspmxs.get(s);
												if (temp.getFpnum() == fpnum) {
													splitKpspmxs = splitInvoiceshs(jyspmxsResult, mapResult, maxje, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
													return splitKpspmxs;
												} else {
													splitKpspmxs = splitInvoiceshs(new ArrayList<JyspmxDecimal2>(), mapResult, maxje, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
													splitKpspmxs.clear();
													jyspmxsResult.clear();
													return splitKpspmxs;
												}
											}
										}
									}
									
									cfje = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfje"))));// 对应整数数量金额
									cfse = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfse"))));// 对应整数数量税额
									cfjshj = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfjshj"))));// 对应整数数量价税合计
									ccje = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("ccje"))));// 对应整数数量价税合计
									 BigDecimal wcbl = div(div(cfjyspmxtmp.getSpje(), cfbl), cfje);//原拆分金额=div(cfjyspmxtmp.getSpje(), cfbl)
									 //wcbl表示原cfje和整数拆分后cfje的比例
									cfbl = mul(wcbl,cfbl);// 原金额除以整数数量对应金额重新计算比例用于折扣行比例计算
								}
							} else {
								// 折扣行处理
								cfje = div(cfjyspmxtmp.getSpje(), cfbl);
								//ccje = add(ccje,sub(cfjyspmxtmp.getSpje(),cfje));
							}

							
							
							ccjshj = sub(jyspmx.getJshj(), cfjshj);// 超出价税合计
							// ccjyspmx = jyspmx;//超出金额对象
							cfjyspmx.setFphxz(s_fphxz);
							cfjyspmx.setSqlsh(sqlsh);
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
							cfjyspmx.setJshj(cfjshj);
							cfjyspmx.setYkphj(new BigDecimal(0));
							cfjyspmx.setSpdm(spdm);
							cfjyspmx.setGsdm(jyspmx.getGsdm());
							cfjyspmx.setYhzcbs(jyspmx.getYhzcbs());
							cfjyspmx.setYhzcmc(jyspmx.getYhzcmc());
							cfjyspmx.setKce(jyspmx.getKce());
							cfjyspmx.setLslbz(jyspmx.getLslbz());
							splitKpspmxs.add(cfjyspmx);
							spje = sub(spje,cfje);//拆分出来的商品金额
							spjshj = sub(spjshj,cfjshj);//拆分出来的价税合计
							cfjyspmxtmp.setSpje(sub(cfjyspmxtmp.getSpje(),cfje));
							if (null != spdj && !"".equals(spdj) && null != spsm && !"".equals(spsm)) {
							   cfjyspmxtmp.setSps(sub(cfjyspmxtmp.getSps(),cfsm));
							}
							cfjyspmxtmp.setSpse(sub(cfjyspmxtmp.getSpse(),cfse));
							cfjyspmxtmp.setJshj(sub(cfjyspmxtmp.getJshj(),cfjshj));
						}
						//jyspmxsResult.remove(jyspmx);
						for(int j=0;j<jyspmxsResult.size();j++){
							JyspmxDecimal2 cfjyspmxtmp = jyspmxsResult.get(j);
							if(cfjyspmxtmp.getSpmxxh()==jyspmx.getSpmxxh() && cfjyspmxtmp.getsqlsh()==jyspmx.getsqlsh()){
								cfjyspmxtmp.setSpje(spje.setScale(2, BigDecimal.ROUND_HALF_UP));
								cfjyspmxtmp.setJshj(spjshj.setScale(2, BigDecimal.ROUND_HALF_UP));
								cfjyspmxtmp.setSpse(sub(spjshj,spje).setScale(2, BigDecimal.ROUND_HALF_UP));					
								cfjyspmxtmp.setSps(sub(jyspmx.getSps(),cfsm));
							}
						}
						
					
					}else{

						// 1.2.2 该行不是折扣行，按着原来处理方法。
						// Jyspmx ccjyspmx = new Jyspmx();//超出金额对象
						JyspmxDecimal2 cfjyspmx = new JyspmxDecimal2();// 拆分金额对象
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
						
						BigDecimal spjshj = jyspmx.getJshj();
						// 税率
						BigDecimal spsl = jyspmx.getSpsl();
						BigDecimal spje = jyspmx.getSpje();// 原商品金额
						BigDecimal spsm = jyspmx.getSps();// 原商品数量
						BigDecimal spse = jyspmx.getSpse();// 原商品税额

						BigDecimal ccje = sub(zje, maxje);// 超出金额

						BigDecimal cfje = sub(spje, ccje);// 拆分金额
						
						BigDecimal cfbl = div(spje, cfje);// 拆分比例
						/**
						 * 按商品整数来分票
						 */
						BigDecimal cfsm;
						BigDecimal cfse;
						BigDecimal cfjshj;
						
						cfsm = div(spsm, cfbl);// 拆分数量
						cfje = div(spje, cfbl);// 拆分金额
						cfse = div(spse, cfbl).setScale(2, BigDecimal.ROUND_HALF_UP);// 拆分税额
					    cfjshj = add(cfje, cfse);
						if (spzsfp && null != spdj && !"".equals(spdj) && null != spsm && !"".equals(spsm)) {
							// 拆分整数数量处理
							Map cfslMap = cfsl_byInt(spdj,cfje,spsl,cfse,cfjshj,ccje,BigDecimal.ZERO);
							cfsm = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfsm"))));// 拆分整数数量
							
							if((null ==splitKpspmxs || splitKpspmxs.isEmpty()) && Math.floor(cfsm.doubleValue()) == 0){
								splitKpspmxs = splitInvoiceshs(new ArrayList<JyspmxDecimal2>(), mapResult, maxje, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
								splitKpspmxs.clear();
								jyspmxsResult.clear();
								return splitKpspmxs;
							}else{
								if(Math.floor(cfsm.doubleValue()) == 0){
									for (int s = 0; s < splitKpspmxs.size(); s++) {
										JyspmxDecimal2 temp = splitKpspmxs.get(s);
										if (temp.getFpnum() == fpnum) {
											splitKpspmxs = splitInvoiceshs(jyspmxsResult, mapResult, maxje, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
											return splitKpspmxs;
										} else {
											splitKpspmxs = splitInvoiceshs(new ArrayList<JyspmxDecimal2>(), mapResult, maxje, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
											splitKpspmxs.clear();
											jyspmxsResult.clear();
											return splitKpspmxs;
										}
									}
								}
							}
							
							cfje = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfje"))));// 对应整数数量金额
							cfse = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfse"))));// 对应整数数量金额
							cfjshj= BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfjshj"))));// 对应整数数量金额
							ccje= BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("ccje"))));// 对应整数数量金额
							/*ccje = add(ccje,
							 BigDecimal.valueOf((long)
							 cfslMap.get("ccje")));*/
							 BigDecimal wcbl = div(div(spje, cfbl), cfje);//原拆分金额=div(cfjyspmxtmp.getSpje(), cfbl)
							 //wcbl表示原cfje和整数拆分后cfje的比例
							cfbl = mul(wcbl,cfbl);// 原金额除以整数数量对应金额重新计算比例用于折扣行比例计算
						}

					
					    

						cfjyspmx.setFphxz(fphxz);
						cfjyspmx.setSqlsh(sqlsh);
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
						cfjyspmx.setJshj(cfjshj);
						cfjyspmx.setYkphj(new BigDecimal(0));
						cfjyspmx.setSpdm(spdm);
						cfjyspmx.setGsdm(jyspmx.getGsdm());
						cfjyspmx.setYhzcbs(jyspmx.getYhzcbs());
						cfjyspmx.setYhzcmc(jyspmx.getYhzcmc());
						cfjyspmx.setKce(jyspmx.getKce());
						cfjyspmx.setLslbz(jyspmx.getLslbz());
						splitKpspmxs.add(cfjyspmx);
						for(int j=0;j<jyspmxsResult.size();j++){
							JyspmxDecimal2 cfjyspmxtmp = jyspmxsResult.get(j);
							if(cfjyspmxtmp.getSpmxxh()==jyspmx.getSpmxxh() && cfjyspmxtmp.getsqlsh()==jyspmx.getsqlsh()){
								cfjyspmxtmp.setSpje(ccje.setScale(2, BigDecimal.ROUND_HALF_UP));
								cfjyspmxtmp.setJshj(sub(spjshj,cfjshj).setScale(2, BigDecimal.ROUND_HALF_UP));
								cfjyspmxtmp.setSpse(sub(sub(spjshj,cfjshj),ccje).setScale(2, BigDecimal.ROUND_HALF_UP));					
								cfjyspmxtmp.setSps(sub(jyspmx.getSps(),cfsm));
							}
						}
					
					}
					
				
				} else {
					/**
					 * 按照页面上填写的分票金额来分票
					 */
					if(jyspmx.getFphxz().equals("2")){
						// 获取折扣行和被折扣行数据
						List<JyspmxDecimal2> zkAndbzkList = (List) zkAndbkzMap.get(jyspmx.getSpmxxh());
						JyspmxDecimal2 cfjyspmxzk = new JyspmxDecimal2();
						BigDecimal spje = jyspmx.getSpje();// 原商品金额
						BigDecimal spjshj= jyspmx.getJshj();// 原加税合计

						BigDecimal cfsm = BigDecimal.ZERO;
						
						BigDecimal ccjshj = sub(zje1, fpje);// 超出价税合计 当前行商品拆分出留在下张发票价税合计
						
						BigDecimal cfjshj = sub(spjshj, ccjshj);// 拆分价税合计 当前行商品拆分出留在当前发票价税合计
						
						BigDecimal cfbl = div(spjshj, cfjshj);// 拆分比例
						
						for (int j = 0; j < zkAndbzkList.size(); j++) {

							JyspmxDecimal2 cfjyspmxtmp = zkAndbzkList.get(j);
							JyspmxDecimal2 cfjyspmx = new JyspmxDecimal2();

							String s_fphxz = cfjyspmxtmp.getFphxz();

							// 商品名称
							String spmc = cfjyspmxtmp.getSpmc();
							// 规格型号
							String spggxh = cfjyspmxtmp.getSpggxh();
							// 单位
							String spdw = cfjyspmxtmp.getSpdw();
							// 单价
							BigDecimal spdj = cfjyspmxtmp.getSpdj();
							// 税率
							BigDecimal spsl = cfjyspmxtmp.getSpsl();

							BigDecimal spsm = BigDecimal.ZERO;
							BigDecimal spse;
							

							spse = cfjyspmxtmp.getSpse();// 原商品税额
							// BigDecimal ccje_bzk;//被折扣行超出金额
							spmxxh = cfjyspmxtmp.getSpmxxh();
							BigDecimal cfse  = BigDecimal.ZERO;;
							BigDecimal cfspje = BigDecimal.ZERO;
							if (s_fphxz.equals("2")) {
								// 被折扣行处理
								spsm = cfjyspmxtmp.getSps();// 原商品数量

								cfsm = div(spsm, cfbl);// 拆分数量
								//cfje = div(cfjyspmxtmp.getSpje(), cfbl);// 拆分金额
								cfjshj = div(cfjyspmxtmp.getJshj(), cfbl);
								
								cfse = div(spse, cfbl).setScale(10, BigDecimal.ROUND_HALF_UP);// 拆分税额
								cfspje = sub(cfjshj, cfse);
								// 被折扣行的商品金额按比例计算cfje =
								// div(cfjyspmxtmp.getSpje(),cfbl),得出保留在当前发票的被折扣行金额
								// ccje_bzk = sub(cfjyspmxtmp.getSpje(),
								// cfje);// 超出金额

								/**
								 * 按商品整数来分票
								 */
								if (spzsfp && null != spdj && !"".equals(spdj) && null != spsm && !"".equals(spsm)) {
									// 拆分整数数量处理
									Map cfslMap = cfsl_hsbyInt(spdj,cfspje,cfse,cfjshj,ccjshj);
									cfsm = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfsm"))));// 拆分整数数量
									if((null ==splitKpspmxs || splitKpspmxs.isEmpty()) && Math.floor(cfsm.doubleValue()) == 0){
										splitKpspmxs = splitInvoiceshs(new ArrayList<JyspmxDecimal2>(), mapResult, maxje, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
										splitKpspmxs.clear();
										jyspmxsResult.clear();
										return splitKpspmxs;
									}else{
										if(Math.floor(cfsm.doubleValue()) == 0){
											for (int s = 0; s < splitKpspmxs.size(); s++) {
												JyspmxDecimal2 temp = splitKpspmxs.get(s);
												if (temp.getFpnum() == fpnum) {
													splitKpspmxs = splitInvoiceshs(jyspmxsResult, mapResult, maxje, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
													return splitKpspmxs;
												} else {
													splitKpspmxs = splitInvoiceshs(new ArrayList<JyspmxDecimal2>(), mapResult, maxje, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
													splitKpspmxs.clear();
													jyspmxsResult.clear();
													return splitKpspmxs;
												}
											}
										}
									}
									cfjshj = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfjshj"))));// 对应整数数量金额
									cfspje = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfje"))));// 对应整数数量金额
									cfse = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfse"))));// 对应整数数量金额
									ccjshj = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("ccje"))));// 对应整数数量金额
									 BigDecimal wcbl = div(div(cfjyspmxtmp.getJshj(), cfbl), cfjshj);//原拆分金额=div(cfjyspmxtmp.getSpje(), cfbl)
									 //wcbl表示原cfje和整数拆分后cfje的比例
									cfbl = mul(wcbl,cfbl);// 原金额除以整数数量对应金额重新计算比例用于折扣行比例计算
								}
							} else {
								// 折扣行处理
								cfjshj = div(cfjyspmxtmp.getJshj(), cfbl);
								//ccje = add(ccje,sub(cfjyspmxtmp.getSpje(),cfje));
							}

							
							
							//ccjshj = sub(jyspmx.getJshj(), jshj);// 超出价税合计
							//ccje = sub(jyspmx.getSpje(), bcspje);
							// ccjyspmx = jyspmx;//超出金额对象
							cfjyspmx.setFphxz(s_fphxz);
							cfjyspmx.setSqlsh(sqlsh);
							cfjyspmx.setSpmxxh(spmxxh);
							cfjyspmx.setSpje(cfspje);
							cfjyspmx.setSps(cfsm);
							cfjyspmx.setSpse(cfse);
							cfjyspmx.setFpnum(fpnum);
							cfjyspmx.setSpmc(spmc);
							cfjyspmx.setSpggxh(spggxh);
							cfjyspmx.setSpdw(spdw);
							
							cfjyspmx.setSpsl(spsl);
							cfjyspmx.setJshj(cfjshj);
							cfjyspmx.setSpdj(div(cfspje,cfsm));
							cfjyspmx.setYkphj(new BigDecimal(0));
							cfjyspmx.setSpdm(spdm);
							cfjyspmx.setGsdm(jyspmx.getGsdm());
							cfjyspmx.setYhzcbs(jyspmx.getYhzcbs());
							cfjyspmx.setYhzcmc(jyspmx.getYhzcmc());
							cfjyspmx.setKce(jyspmx.getKce());
							cfjyspmx.setLslbz(jyspmx.getLslbz());
							splitKpspmxs.add(cfjyspmx);
							spje = sub(spje,cfspje);//拆分出来的商品金额
							spjshj = sub(spjshj,cfjshj);//拆分出来的价税合计
							cfjyspmxtmp.setSpje(sub(cfjyspmxtmp.getSpje(),cfspje));
							if (null != spdj && !"".equals(spdj) && null != spsm && !"".equals(spsm)) {
							   cfjyspmxtmp.setSps(div(cfjyspmxtmp.getSpje(),cfjyspmxtmp.getSpdj()));
							}
							cfjyspmxtmp.setSpse(sub(cfjyspmxtmp.getSpse(),cfse));
							cfjyspmxtmp.setJshj(sub(cfjyspmxtmp.getJshj(),cfjshj));
						}
						//jyspmxsResult.remove(jyspmx);
						for(int j=0;j<jyspmxsResult.size();j++){
							JyspmxDecimal2 cfjyspmxtmp = jyspmxsResult.get(j);
							if(cfjyspmxtmp.getSpmxxh()==jyspmx.getSpmxxh() && cfjyspmxtmp.getsqlsh()==jyspmx.getsqlsh()){
								cfjyspmxtmp.setSpje(spje.setScale(2, BigDecimal.ROUND_HALF_UP));
								cfjyspmxtmp.setJshj(spjshj.setScale(2, BigDecimal.ROUND_HALF_UP));
								cfjyspmxtmp.setSpse(sub(spjshj,spje).setScale(2, BigDecimal.ROUND_HALF_UP));					
								cfjyspmxtmp.setSps(sub(jyspmx.getSps(),cfsm));
							}
						}
						
					
					}else{

						// 1.2.2 该行不是折扣行，按着原来处理方法。
						// Jyspmx ccjyspmx = new Jyspmx();//超出金额对象
						JyspmxDecimal2 cfjyspmx = new JyspmxDecimal2();// 拆分金额对象
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
						
						BigDecimal spjshj = jyspmx.getJshj();
						// 税率
						BigDecimal spsl = jyspmx.getSpsl();
						BigDecimal spje = jyspmx.getSpje();// 原商品金额
						BigDecimal spsm = jyspmx.getSps();// 原商品数量
						BigDecimal spse = jyspmx.getSpse();// 原商品税额

						//BigDecimal ccje = sub(zje, maxje);// 超出金额
						BigDecimal ccjshj = sub(zje1, fpje);// 超出价税合计
						
						//BigDecimal cfje = sub(spje, ccje);// 拆分金额
						BigDecimal cfjshj = sub(spjshj, ccjshj);// 拆分金额
						
						BigDecimal cfbl = div(spjshj, cfjshj);// 拆分比例
						/**
						 * 按商品整数来分票
						 */
						BigDecimal cfsm;
						BigDecimal cfse;
						BigDecimal cfspje;
						
						cfsm = div(spsm, cfbl);// 拆分数量
						cfjshj = div(spjshj, cfbl);// 拆分金额
					     cfse = div(spse, cfbl).setScale(10, BigDecimal.ROUND_HALF_UP);// 拆分税额
					     cfspje = sub(cfjshj, cfse);

						if (spzsfp && null != spdj && !"".equals(spdj) && null != spsm && !"".equals(spsm)) {
							// 拆分整数数量处理
							Map cfslMap = cfsl_hsbyInt(spdj,cfspje,cfse,cfjshj,ccjshj);
							cfsm = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfsm"))));// 拆分整数数量
							
							if((null ==splitKpspmxs || splitKpspmxs.isEmpty()) && Math.floor(cfsm.doubleValue()) == 0){
								splitKpspmxs = splitInvoiceshs(new ArrayList<JyspmxDecimal2>(), mapResult, maxje, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
								splitKpspmxs.clear();
								jyspmxsResult.clear();
								return splitKpspmxs;
							}else{
								if(Math.floor(cfsm.doubleValue()) == 0){
										for (int s = 0; s < splitKpspmxs.size(); s++) {
											JyspmxDecimal2 temp = splitKpspmxs.get(s);
											if (temp.getFpnum() == fpnum) {
												splitKpspmxs = splitInvoiceshs(jyspmxsResult, mapResult, maxje, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
												return splitKpspmxs;
											} else {
												splitKpspmxs = splitInvoiceshs(new ArrayList<JyspmxDecimal2>(), mapResult, maxje, fpje, mxsl, qzfp, spzsfp, fpnum,splitKpspmxs);
												splitKpspmxs.clear();
												jyspmxsResult.clear();
												return splitKpspmxs;
											}
										}
									}
							}
							cfjshj = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfjshj"))));// 对应整数数量金额
							cfspje = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfje"))));// 对应整数数量金额
							cfse = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("cfse"))));// 对应整数数量金额
							ccjshj = BigDecimal.valueOf(Double.valueOf(String.valueOf(cfslMap.get("ccje"))));// 对应整数数量金额
							/*ccje = add(ccje,
							 BigDecimal.valueOf((long)
							 cfslMap.get("ccje")));*/
							 BigDecimal wcbl = div(div(spjshj, cfbl), cfjshj);//原拆分金额=div(cfjyspmxtmp.getSpje(), cfbl)
							 //wcbl表示原cfje和整数拆分后cfje的比例
							cfbl = mul(wcbl,cfbl);// 原金额除以整数数量对应金额重新计算比例用于折扣行比例计算
						}

					
					
						cfjyspmx.setFphxz(fphxz);
						cfjyspmx.setSqlsh(sqlsh);
						cfjyspmx.setSpmxxh(spmxxh);
						cfjyspmx.setSpje(cfspje);
						cfjyspmx.setSps(cfsm);
						cfjyspmx.setSpse(cfse);
						cfjyspmx.setFpnum(fpnum);
						cfjyspmx.setSpmc(spmc);
						cfjyspmx.setSpggxh(spggxh);
						cfjyspmx.setSpdw(spdw);
						
						cfjyspmx.setSpsl(spsl);
						cfjyspmx.setJshj(cfjshj);
						cfjyspmx.setSpdj(div(cfspje, cfsm));
						cfjyspmx.setYkphj(new BigDecimal(0));
						cfjyspmx.setSpdm(spdm);
						cfjyspmx.setGsdm(jyspmx.getGsdm());
						cfjyspmx.setYhzcbs(jyspmx.getYhzcbs());
						cfjyspmx.setYhzcmc(jyspmx.getYhzcmc());
						cfjyspmx.setKce(jyspmx.getKce());
						cfjyspmx.setLslbz(jyspmx.getLslbz());
						splitKpspmxs.add(cfjyspmx);
						for(int j=0;j<jyspmxsResult.size();j++){
							JyspmxDecimal2 cfjyspmxtmp = jyspmxsResult.get(j);
							if(cfjyspmxtmp.getSpmxxh()==jyspmx.getSpmxxh() && cfjyspmxtmp.getsqlsh()==jyspmx.getsqlsh()){
								cfjyspmxtmp.setSpje(sub(spje,cfspje).setScale(2, BigDecimal.ROUND_HALF_UP));
								cfjyspmxtmp.setJshj(ccjshj.setScale(2, BigDecimal.ROUND_HALF_UP));
								cfjyspmxtmp.setSpse(sub(ccjshj,sub(spje,cfspje)).setScale(2, BigDecimal.ROUND_HALF_UP));					
								cfjyspmxtmp.setSps(div(cfjyspmxtmp.getSpje(),cfjyspmxtmp.getSpdj()));
							}
						}
					
					}
					
				
				}
			}
			break;
		} else {
			// 2.当前明细不需要分票，不是该发票最后一条明细
			if (jyspmx.getFphxz().equals("2")) {
				List<JyspmxDecimal2> zkAndbzkList = (List) zkAndbkzMap.get(jyspmx.getSpmxxh());
				for (int j = 0; j < zkAndbzkList.size(); j++) {
					zkAndbzkList.get(j).setFpnum(fpnum);
					splitKpspmxs.add(zkAndbzkList.get(j));
				}
				mapResult.remove(jyspmx.getSpmxxh());
			} else {
				jyspmx.setFpnum(fpnum);
				splitKpspmxs.add(jyspmx);
			}
			jyspmxsResult.remove(0);
		
		}
	
	}
	splitKpspmxs = splitInvoiceshs(jyspmxsResult,mapResult, maxje, fpje,mxsl,qzfp,spzsfp,fpnum,splitKpspmxs) ;
    return splitKpspmxs;
}

	/**
	 * 再次根据行上的价税合计处理金额和税额。
	 *
	 * @param jyspmx
	 *  
	 * @return jyspmxs
	 */
    public static JyspmxDecimal2 reSeparatePrice(JyspmxDecimal2 jyspmx) {

            BigDecimal jshj = jyspmx.getJshj().setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal spsl = jyspmx.getSpsl().setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal spdj = jyspmx.getSpdj();
            BigDecimal sps = jyspmx.getSps();
            BigDecimal jeWithoutTax = div(jshj, spsl.add(new BigDecimal(1)),2);
            BigDecimal jeTax = sub(jshj, jeWithoutTax).setScale(2, BigDecimal.ROUND_HALF_UP);
            // 判断单价是否为空
            //Double djWithoutTax = div(spdj, 1 + spsl, 6);
            BigDecimal djWithoutTax;
            if (null == spdj || null == sps || sps == BigDecimal.ZERO || spdj == BigDecimal.ZERO) {
                djWithoutTax = null;// 单价不含税
            } else {
                djWithoutTax = div(jeWithoutTax,sps,15);
            }
            jyspmx.setSpje(jeWithoutTax);// 商品金额不含税
            jyspmx.setSpse(jeTax);// 税额
//            mx.setJshj(spje);// 价税合计
            jyspmx.setSpdj(djWithoutTax);// 单价不含税
            //sepJyspmxs.add(mx);
        return jyspmx;
    }


	/**
	 * 提供精确的加法运算。
	 *
	 * @param value1
	 *            被加数
	 * @param value2
	 *            加数
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
	 * @param value1
	 *            被减数
	 * @param value2
	 *            减数
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
	 * @param value1
	 *            被乘数
	 * @param value2
	 *            乘数
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
	 * @param dividend
	 *            被除数
	 * @param divisor
	 *            除数
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
	 * @param dividend
	 *            被除数
	 * @param divisor
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
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
		List<JyspmxDecimal2> list = new ArrayList<>();
		JyspmxDecimal2 jymx = new JyspmxDecimal2();
		jymx.setJshj(new BigDecimal(23456));
		jymx.setSpsl(new BigDecimal(0.03));
		jymx.setSpje(new BigDecimal(22772.82));
		jymx.setSpse(new BigDecimal(683.18));
		jymx.setSqlsh(1);
		jymx.setSpmxxh(1);
		for (int i = 0; i < 10; i++) {
			list.add(jymx);
			jymx.setSpmxxh(i + 2);
		}
		BigDecimal maxje =new BigDecimal(9999.99333333333).setScale(2,  BigDecimal.ROUND_HALF_UP);
		BigDecimal ccje = div(new BigDecimal(10000.22).setScale(2, BigDecimal.ROUND_HALF_UP), maxje.setScale(2, BigDecimal.ROUND_HALF_UP));// 超出金额
		System.out.println(maxje);
		/*List<JyspmxDecimal2> res = splitInvoicesbhs(list, new BigDecimal(9999.99), new BigDecimal(10000), 8, false,
				false);
		for (JyspmxDecimal2 jyspmx : res) {
			System.out.println(jyspmx.getJshj() + "\t" + jyspmx.getFpnum() + "\t" + jyspmx.getSpje() + "\t"
					+ jyspmx.getSpse() + "\t" + jyspmx.getSpsl() + "\t");
		}
*/
	}
}
