package com.rjxx.taxeasy.bizcomm.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.taxeasy.domains.Jymxsq;
import com.rjxx.taxeasy.domains.JymxsqCl;
import com.rjxx.taxeasy.domains.Jyxxsq;
import com.rjxx.taxeasy.domains.Jyzfmx;
import com.rjxx.taxeasy.domains.Zffs;
import com.rjxx.taxeasy.service.ZffsService;

@Service
public class DiscountDealUtil {
    
	@Autowired
	private ZffsService zffsService;
	
	/**
	 * 将多笔订单分开，逐条调用处理折扣数据。
	 */
	public List<JymxsqCl> dealDiscount(List<Jyxxsq> jyxxsqList,List<Jymxsq> jymxsqList,List<Jyzfmx> jyzfmxList,String gsdm){
		
		List<JymxsqCl> JymxsqClResultList = new ArrayList<JymxsqCl>(); 
		//查看当前
		List kpfsList = new ArrayList();
		kpfsList.add("02");
		Map params = new HashMap();
		params.put("gsdm", gsdm);
		params.put("kpfsList", kpfsList);
		List<Zffs> zffsList = zffsService.findAllByParams(params);
		for(int i=0;i<jyxxsqList.size();i++){
			List<JymxsqCl> jymxsqClTmpList = new ArrayList<JymxsqCl>(); 
			List<Jymxsq> jymxsqTmpList = new ArrayList<Jymxsq>(); 
			double zkzje = 0.00;
			double jshj = 0.00;
			String hsbz = "";//0不含税，1含税
			Jyxxsq jyxxsq = jyxxsqList.get(i);
			for(int j=0;j<jymxsqList.size();j++){
				Jymxsq jymxsq = jymxsqList.get(j);
				if(jyxxsq.getDdh().equals(jymxsq.getDdh())){
					jymxsqTmpList.add(jymxsq);
				}
			}
			if (null != jyzfmxList && !jyzfmxList.isEmpty() && null !=kpfsList && !kpfsList.isEmpty()) {
				for (int k = 0; k < jyzfmxList.size(); k++) {
					Jyzfmx jyzfmx = jyzfmxList.get(k);
					if (jyxxsq.getDdh().equals(jyzfmx.getDdh())) {
						for (int m = 0; m < zffsList.size(); m++) {
							Zffs zffs = zffsList.get(m);
							if (jyzfmx.getZffsDm().equals(zffs.getZffsDm())) {
								zkzje = zkzje + jyzfmx.getZfje();
							}
						}

					}
				}
			} else {
				zkzje = 0.00;
			}
			jshj = jyxxsq.getJshj();
			hsbz = jyxxsq.getHsbz();
			jymxsqClTmpList = dealDiscount(jymxsqTmpList,zkzje,jshj,hsbz);
			JymxsqClResultList.addAll(jymxsqClTmpList);
		}
		return JymxsqClResultList;
	}
	/**
	 * 处理申请明细信息，将其处理到t_jymxsq_cl表中 
	 * @param jymxsqList 原单笔交易申请明细
	 * @param zkzje 需要通过折扣处理的总金额（价税合计）
	 * @param jshj 单笔订单总价税合计
	 * @param hsbz 含税标志0不含税，1含税
	 */
	public List<JymxsqCl> dealDiscount(List<Jymxsq> jymxsqList, double zkzje, double jshj,String hsbz) {
        //处理折扣行最终结果list
		List<JymxsqCl> jymxsqClList = new ArrayList<JymxsqCl>();
		
        //折扣总金额判断，金额大于0说明需要处理折扣问题
		if (zkzje > 0) {
			//计算折扣率，保留十位小数
			double zkl = div(zkzje,jshj, 10);
			int spmxxh = 1;
			
			//存储金额最大的一条或两条申请明细
			List<Jymxsq> jshjMaxList = new ArrayList<Jymxsq>();
		    double maxJshj = 0; //最大明细的加税合计
		    int zdxh = 0; // 最大明细的所在list序号
			for(int t=0;t<jymxsqList.size();t++){
				Jymxsq jymxsq = new Jymxsq(jymxsqList.get(t));
				 //jymxsq = jymxsqList.get(t);
				if(jymxsq.getFphxz().equals("2")){
					if(maxJshj<(jymxsq.getJshj()+jymxsqList.get(t+1).getJshj())){
		            	   maxJshj = jymxsq.getJshj()+jymxsqList.get(t+1).getJshj();
		            	   zdxh = t;
		               }
				}else{
					if(maxJshj<jymxsq.getJshj()){
		            	   maxJshj = jymxsq.getJshj();
		            	   zdxh = t;
		               }
				}
	           
			}
			//将最大价税合计的数据放入新的list，待后续处理，避免尾差
			Jymxsq zdJymxsq = jymxsqList.get(zdxh);
			if(zdJymxsq.getFphxz().equals("2")){
				jshjMaxList.add(zdJymxsq);
				jshjMaxList.add(jymxsqList.get(zdxh+1));
				jymxsqList.removeAll(jshjMaxList);
			}else{
				jshjMaxList.add(zdJymxsq);
				jymxsqList.removeAll(jshjMaxList);
			}
			
			
			//循环交易申请明细list，进行处理操作
			for (int i = 0; i < jymxsqList.size(); i++) {
				Jymxsq jymxsq = new Jymxsq(jymxsqList.get(i));
				//该行为正常行
				if (jymxsq.getFphxz().equals("0")) {
					jymxsq.setSpmxxh(spmxxh);
					jymxsq.setFphxz("2");// 被折扣行
					JymxsqCl jymxsqClTmp = new JymxsqCl(jymxsq);
					jymxsqClList.add(jymxsqClTmp);
					spmxxh++;
					JymxsqCl jymxsqCl = new JymxsqCl(jymxsq);
					jymxsqCl.setFphxz("1");// 折扣行
					jymxsqCl.setSpje(-jymxsq.getSpje() * zkl);
					jymxsqCl.setSpse(-jymxsq.getSpse() * zkl);
					jymxsqCl.setJshj(-(jymxsq.getSpje() * zkl + jymxsq.getSpse() * zkl));
					jymxsqCl.setSps(-jymxsq.getSpje() * zkl / jymxsq.getSpdj());
					jymxsqCl.setSpmxxh(spmxxh);
					jymxsqClList.add(jymxsqCl);
					spmxxh++;
				} else {
					if (jymxsq.getFphxz().equals("2")) {
						int mxxh = jymxsq.getSpmxxh();
						jymxsq.setSpmxxh(spmxxh);
						// jymxsq.setFphxz("2");//被折扣行
						JymxsqCl jymxsqClTmp = new JymxsqCl(jymxsq);
						jymxsqClList.add(jymxsqClTmp);
						spmxxh++;
						for (int j = 0; j < jymxsqList.size(); j++) {
							Jymxsq jymxsq2 = new Jymxsq(jymxsqList.get(j));
							if (jymxsq2.getSpmc().equals(jymxsq.getSpmc()) && jymxsq2.getSpdj().equals(jymxsq.getSpdj())
									&& jymxsq2.getSpggxh().equals(jymxsq.getSpggxh())
									&& jymxsq2.getSpmxxh() == (mxxh + 1)) {
								// jymxsqCl.setFphxz("1");//折扣行
								JymxsqCl jymxsqCl = new JymxsqCl(jymxsq2);
								jymxsqCl.setSpje(-(jymxsq2.getSpje() + jymxsq.getSpje()) * zkl+jymxsq2.getSpje());
								jymxsqCl.setSpse(-(jymxsq2.getSpse() + jymxsq.getSpse()) * zkl+jymxsq2.getSpse());
								jymxsqCl.setJshj(-((jymxsq2.getSpje() + jymxsq.getSpje()) * zkl
										+ (jymxsq2.getSpse() + jymxsq.getSpse()) * zkl)+jymxsq2.getJshj());
								jymxsqCl.setSps((-(jymxsq2.getSpje() + jymxsq.getSpje()) * zkl +jymxsq2.getSpje())/ jymxsq2.getSpdj());
								jymxsqCl.setSpmxxh(spmxxh);
								jymxsqClList.add(jymxsqCl);
								spmxxh++;
							}
						}
					}
				}

			}
			double zkjehj = 0;
			for(int xh=0;xh<jymxsqClList.size();xh++){
				JymxsqCl jymxsqCl22 = jymxsqClList.get(xh);
				if(jymxsqCl22.getFphxz().equals("1")){
					zkjehj += jymxsqCl22.getJshj();
				}
			}
			if(jshjMaxList.size()==1){
				Jymxsq jymxsqMax = new Jymxsq(jshjMaxList.get(0));
				jymxsqMax.setSpmxxh(spmxxh);
				jymxsqMax.setFphxz("2");// 被折扣行
				JymxsqCl jymxsqClTmp = new JymxsqCl(jymxsqMax);
				jymxsqClList.add(jymxsqClTmp);
				spmxxh++;
				JymxsqCl jymxsqCl = new JymxsqCl(jymxsqMax);
				jymxsqCl.setFphxz("1");// 折扣行
				jymxsqCl.setSpje(hsbz.equals("1")?-(zkzje+zkjehj):-(zkzje+zkjehj)/(1+jymxsqCl.getSpsl()));
				jymxsqCl.setJshj(-(zkzje+zkjehj));
				jymxsqCl.setSpse(hsbz.equals("1")?0:jymxsqCl.getJshj()-jymxsqCl.getSpje());
				
				jymxsqCl.setSps(-jymxsqMax.getSpje()/jymxsqMax.getSpdj());
				jymxsqCl.setSpmxxh(spmxxh);
				jymxsqClList.add(jymxsqCl);
				spmxxh++;
			}else{
				for(int n=0;n<jshjMaxList.size();n++){
				  Jymxsq jymxsqMax = new Jymxsq(jshjMaxList.get(n));
					if(jymxsqMax.getFphxz().equals("2")){
						jymxsqMax.setSpmxxh(spmxxh);
						// jymxsq.setFphxz("2");//被折扣行
						JymxsqCl jymxsqClTmp = new JymxsqCl(jymxsqMax);
						jymxsqClList.add(jymxsqClTmp);
						spmxxh++;
					}else{
						JymxsqCl jymxsqCl = new JymxsqCl(jymxsqMax);
						jymxsqCl.setFphxz("1");// 折扣行
						jymxsqCl.setSpje(hsbz.equals("1")?-(zkzje+zkjehj):-(zkzje+zkjehj)/(1+jymxsqCl.getSpsl()));
						jymxsqCl.setJshj(-(zkzje+zkjehj));
						jymxsqCl.setSpse(hsbz.equals("1")?0:jymxsqCl.getJshj()-jymxsqCl.getSpje());
						
						jymxsqCl.setSps(-jymxsqMax.getSpje()/jymxsqMax.getSpdj());
						jymxsqCl.setSpmxxh(spmxxh);
						jymxsqClList.add(jymxsqCl);
						spmxxh++;
					}
				}
			}
			
			
		} else {
			for (int i = 0; i < jymxsqList.size(); i++) {
				JymxsqCl jymxsqCl = new JymxsqCl(jymxsqList.get(i));
				jymxsqClList.add(jymxsqCl);
			}
		}
		return jymxsqClList;
	}
    
	
	/**
	 * 处理申请明细信息，将其处理到t_jymxsq_cl表中，每条折扣信息全部为乘折扣率算出。
	 * @param jymxsqList 原单笔交易申请明细
	 * @param zkzje 需要通过折扣处理的总金额（价税合计）
	 * @param jshj 单笔订单总价税合计
	 */
	public List<JymxsqCl> dealDiscount(List<Jymxsq> jymxsqList, double zkzje, double jshj) {
        //处理折扣行最终结果list
		List<JymxsqCl> jymxsqClList = new ArrayList<JymxsqCl>();
        //折扣总金额判断，金额大于0说明需要处理折扣问题
		if (zkzje > 0) {
			//计算折扣率，保留十位小数
			double zkl = div(zkzje, jshj, 10);
			int spmxxh = 1;

			//循环交易申请明细list，进行处理操作
			for (int i = 0; i < jymxsqList.size(); i++) {
				Jymxsq jymxsq = jymxsqList.get(i);
				//该行为正常行
				if (jymxsq.getFphxz().equals("0")) {
					jymxsq.setSpmxxh(spmxxh);
					jymxsq.setFphxz("2");// 被折扣行
					JymxsqCl jymxsqClTmp = new JymxsqCl(jymxsq);
					jymxsqClList.add(jymxsqClTmp);
					spmxxh++;
					JymxsqCl jymxsqCl = new JymxsqCl(jymxsq);
					jymxsqCl.setFphxz("1");// 折扣行
					jymxsqCl.setSpje(-div(jymxsq.getSpje() * zkl,jymxsq.getSpje() * zkl,2));
					jymxsqCl.setSpse(-div(jymxsq.getSpse() * zkl,jymxsq.getSpse() * zkl,2));
					jymxsqCl.setJshj(-div((jymxsq.getSpje() * zkl + jymxsq.getSpse() * zkl),(jymxsq.getSpje() * zkl + jymxsq.getSpse() * zkl),2));
					jymxsqCl.setSps(-jymxsq.getSpje() * zkl / jymxsq.getSpdj());
					jymxsqCl.setSpmxxh(spmxxh);
					jymxsqClList.add(jymxsqCl);
					spmxxh++;
				} else {
					if (jymxsq.getFphxz().equals("2")) {
						int mxxh = jymxsq.getSpmxxh();
						jymxsq.setSpmxxh(spmxxh);
						// jymxsq.setFphxz("2");//被折扣行
						JymxsqCl jymxsqClTmp = new JymxsqCl(jymxsq);
						jymxsqClList.add(jymxsqClTmp);
						spmxxh++;
						for (int j = 0; j < jymxsqList.size(); j++) {
							Jymxsq jymxsq2 = jymxsqList.get(j);
							if (jymxsq2.getSpmc().equals(jymxsq.getSpmc()) && jymxsq2.getSpdj().equals(jymxsq.getSpdj())
									&& jymxsq2.getSpggxh().equals(jymxsq.getSpggxh())
									&& jymxsq2.getSpmxxh() == (mxxh + 1)) {
								// jymxsqCl.setFphxz("1");//折扣行
								JymxsqCl jymxsqCl = new JymxsqCl(jymxsq2);
								jymxsqCl.setSpje(-(jymxsq2.getSpje() + jymxsq.getSpje()) * zkl+jymxsq2.getSpje());
								jymxsqCl.setSpse(-(jymxsq2.getSpse() + jymxsq.getSpse()) * zkl+jymxsq2.getSpse());
								jymxsqCl.setJshj(-((jymxsq2.getSpje() + jymxsq.getSpje()) * zkl
										+ (jymxsq2.getSpse() + jymxsq.getSpse()) * zkl)+jymxsq2.getJshj());
								jymxsqCl.setSps((-(jymxsq2.getSpje() + jymxsq.getSpje()) * zkl +jymxsq2.getSpje())/ jymxsq2.getSpdj());
								jymxsqCl.setSpmxxh(spmxxh);
								jymxsqClList.add(jymxsqCl);
								spmxxh++;
							}
						}
					}
				}

			}
			
		} else {
			for (int i = 0; i < jymxsqList.size(); i++) {
				JymxsqCl jymxsqCl = new JymxsqCl(jymxsqList.get(i));
				jymxsqClList.add(jymxsqCl);
			}
		}
		return jymxsqClList;
	}
    
    
    /**
     * 提供（相对）精确的除法运算。 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @param scale    表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public  Double div(Double dividend, Double divisor, Integer scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        if (dividend == null) {
            return null;
        }
        if (divisor == null || divisor == 0) {
            return null;
        }
        BigDecimal b1 = new BigDecimal(Double.toString(dividend));
        BigDecimal b2 = new BigDecimal(Double.toString(divisor));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    public static void main(String[] args) {

        double zkzje = 10.3333;
        double jshj  = 222.332;
        //System.out.println(div(zkzje,jshj,5));
        //System.out.println(zkzje/jshj);
    }
}
