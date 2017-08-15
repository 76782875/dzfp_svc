package com.rjxx.taxeasy.bizcomm.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.taxeasy.domains.Cszb;
import com.rjxx.taxeasy.domains.Jymxsq;
import com.rjxx.taxeasy.domains.JymxsqCl;
import com.rjxx.taxeasy.domains.Jyxxsq;
import com.rjxx.taxeasy.domains.Jyzfmx;
import com.rjxx.taxeasy.domains.Zffs;
import com.rjxx.taxeasy.service.CszbService;
import com.rjxx.taxeasy.service.ZffsService;

@Service
public class DiscountDealUtil {
    
	@Autowired
	private ZffsService zffsService;
	
	@Autowired
	private CszbService cszbService;
	
	/**
	 * 将多笔订单分开，逐条调用处理折扣数据（开票方式为2折扣）。
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
			if (null != jyzfmxList && !jyzfmxList.isEmpty() && null !=zffsList && !zffsList.isEmpty()) {
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
			zkzje = getShePontsPrice(zkzje,jyxxsq);//判断是否需要做舍分处理
			jymxsqClTmpList = dealDiscount(jymxsqTmpList,zkzje,jshj,hsbz);
			//判断是否需要做合并折扣行
			Cszb cszb = cszbService.getSpbmbbh(jyxxsq.getGsdm(), jyxxsq.getXfid(), jyxxsq.getSkpid(), "sfhbzk");
			if(null != cszb && !cszb.equals("")){
				if("是" == cszb.getCsz() || cszb.getCsz().equals("是")){
					List<JymxsqCl> resultList = discountMergeLines(jymxsqClTmpList);
					JymxsqClResultList.addAll(resultList);	
				}else{
					JymxsqClResultList.addAll(jymxsqClTmpList);
				}
				
			}else{
				JymxsqClResultList.addAll(jymxsqClTmpList);
			}
			
		}
		return JymxsqClResultList;
	}
	
	/**
	 * 判断是否需要做舍分处理，将舍分金额预处理到各行明细中
	 * @param zkzje 原折扣总金额
	 * @param zkzje 处理后的折扣总金额
	 */
	private double getShePontsPrice(double zkzje,Jyxxsq jyxxsq){
		double clzkzje = 0.00;
		Cszb cszb = cszbService.getSpbmbbh(jyxxsq.getGsdm(), jyxxsq.getXfid(), jyxxsq.getSkpid(), "sfsfcl");
		if(null != cszb && !cszb.equals("")){
		    if("是" == cszb.getCsz() || cszb.getCsz().equals("是")){
		    	 //BigDecimal b = new BigDecimal(jyxxsq.getJshj());
				String strD = String.valueOf(jyxxsq.getJshj()*10);
				String[] strArr = strD.split("\\.");
				double f1 = Double.parseDouble(strArr[0])/10;
				clzkzje = new BigDecimal((zkzje+jyxxsq.getJshj()-f1)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		    }else{
		    	clzkzje = zkzje;
		    }
		}else{
			clzkzje = zkzje;
		}
		return clzkzje;
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
			double zkl = div(zkzje,jshj, 20);
			//int spmxxh = 1;
			
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
					//jymxsq.setSpmxxh(spmxxh);
					jymxsq.setFphxz("2");// 被折扣行
					JymxsqCl jymxsqClTmp = new JymxsqCl(jymxsq);
					jymxsqClList.add(jymxsqClTmp);
					//spmxxh++;
					JymxsqCl jymxsqCl = new JymxsqCl(jymxsq);
					jymxsqCl.setFphxz("1");// 折扣行
					jymxsqCl.setSpje(-jymxsq.getSpje() * zkl);
					jymxsqCl.setSpse(hsbz.equals("1")?0d:-jymxsq.getSpse() * zkl);
					jymxsqCl.setJshj(new BigDecimal((jymxsqCl.getSpje())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()
							+new BigDecimal((jymxsqCl.getSpse())).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
					jymxsqCl.setSps(null);
					jymxsqCl.setSpdj(null);
					jymxsqCl.setSpmxxh(jymxsq.getSpmxxh());
					jymxsqClList.add(jymxsqCl);
					//spmxxh++;
				} else {
					if (jymxsq.getFphxz().equals("2")) {
						//int mxxh = jymxsq.getSpmxxh();
						//jymxsq.setSpmxxh(spmxxh);
						// jymxsq.setFphxz("2");//被折扣行
						JymxsqCl jymxsqClTmp = new JymxsqCl(jymxsq);
						jymxsqClList.add(jymxsqClTmp);
						//spmxxh++;
						for (int j = 0; j < jymxsqList.size(); j++) {
							Jymxsq jymxsq2 = new Jymxsq(jymxsqList.get(j));
							if (jymxsq2.getSpmc().equals(jymxsq.getSpmc()) && jymxsq2.getSpdm().equals(jymxsq.getSpdm())
									&& jymxsq2.getSpggxh().equals(jymxsq.getSpggxh())&&jymxsq2.getDdh().equals(jymxsq.getDdh())
									&& jymxsq2.getSpmxxh() ==jymxsq.getSpmxxh() && jymxsq2.getFphxz().equals("1")) {
								// jymxsqCl.setFphxz("1");//折扣行
								JymxsqCl jymxsqCl = new JymxsqCl(jymxsq2);
								jymxsqCl.setSpje(-(jymxsq2.getSpje() + jymxsq.getSpje()) * zkl+jymxsq2.getSpje());
								jymxsqCl.setSpse(hsbz.equals("1")?0d:-(jymxsq2.getSpse() + jymxsq.getSpse()) * zkl+jymxsq2.getSpse());
								jymxsqCl.setJshj(new BigDecimal((jymxsqCl.getSpje())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()
										+new BigDecimal((jymxsqCl.getSpse())).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
								jymxsqCl.setSps(null);
								jymxsqCl.setSpdj(null);
								jymxsqCl.setSpmxxh(jymxsq2.getSpmxxh());
								jymxsqClList.add(jymxsqCl);
								//spmxxh++;
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
				//jymxsqMax.setSpmxxh(spmxxh);
				jymxsqMax.setFphxz("2");// 被折扣行
				JymxsqCl jymxsqClTmp = new JymxsqCl(jymxsqMax);
				jymxsqClList.add(jymxsqClTmp);
				//spmxxh++;
				JymxsqCl jymxsqCl = new JymxsqCl(jymxsqMax);
				jymxsqCl.setFphxz("1");// 折扣行
				jymxsqCl.setSpje(hsbz.equals("1")?-(zkzje+zkjehj):-(zkzje+zkjehj)/(1+jymxsqCl.getSpsl()));
				jymxsqCl.setJshj(-(zkzje+zkjehj));
				jymxsqCl.setSpse(hsbz.equals("1")?0:jymxsqCl.getJshj()-jymxsqCl.getSpje());
				
				jymxsqCl.setSps(null);
				jymxsqCl.setSpdj(null);
				//jymxsqCl.setSpmxxh(spmxxh);
				jymxsqClList.add(jymxsqCl);
				//spmxxh++;
			}else{
				for(int n=0;n<jshjMaxList.size();n++){
				  Jymxsq jymxsqMax = new Jymxsq(jshjMaxList.get(n));
					if(jymxsqMax.getFphxz().equals("2")){
						//jymxsqMax.setSpmxxh(spmxxh);
						// jymxsq.setFphxz("2");//被折扣行
						JymxsqCl jymxsqClTmp = new JymxsqCl(jymxsqMax);
						jymxsqClList.add(jymxsqClTmp);
						//spmxxh++;
					}else{
						JymxsqCl jymxsqCl = new JymxsqCl(jymxsqMax);
						jymxsqCl.setFphxz("1");// 折扣行
						double yjshj = jymxsqMax.getJshj();
						jymxsqCl.setSpje(hsbz.equals("1")?-(zkzje+zkjehj-yjshj):-(zkzje+zkjehj-yjshj)/(1+jymxsqCl.getSpsl()));
						jymxsqCl.setJshj(-(zkzje+zkjehj-yjshj));
						jymxsqCl.setSpse(hsbz.equals("1")?0:jymxsqCl.getJshj()-jymxsqCl.getSpje());
						
						jymxsqCl.setSps(null);
						jymxsqCl.setSpdj(null);
						//jymxsqCl.setSpmxxh(spmxxh);
						jymxsqClList.add(jymxsqCl);
						//spmxxh++;
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
							if (jymxsq2.getSpmc().equals(jymxsq.getSpmc()) && jymxsq2.getSpdm().equals(jymxsq.getSpdm())
									&& jymxsq2.getSpggxh().equals(jymxsq.getSpggxh())&& jymxsq2.getDdh().equals(jymxsq.getDdh())
									&& jymxsq2.getSpmxxh() == (mxxh + 1) && jymxsq2.getFphxz().equals("1")) {
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
	 * 将带有折扣行的数据进行合并，并将fphxz改为0。
	 * @param jymxsqList 原单笔交易申请明细
	 * @param zkzje 需要通过折扣处理的总金额（价税合计）
	 * @param jshj 单笔订单总价税合计
	 */
	public List<JymxsqCl> discountMergeLines(List<JymxsqCl> jymxsqClList) {
		List<JymxsqCl> resultList = new ArrayList<JymxsqCl>();//处理返回list
		int spmxxh = 1;//商品明细
		if (null != jymxsqClList && !jymxsqClList.isEmpty()) {
			for (int i = 0; i < jymxsqClList.size(); i++) {
				JymxsqCl jymxsqCl = jymxsqClList.get(i);
				JymxsqCl jymxsqClR = new JymxsqCl();
				//int mxxh = jymxsqCl.getSpmxxh();
				if (jymxsqCl.getFphxz().equals("2") || "2" == jymxsqCl.getFphxz()) {
					for (int j = 0; j < jymxsqClList.size(); j++) {
						JymxsqCl jymxsqCl2 = jymxsqClList.get(j);
						if (jymxsqCl2.getSpmc().equals(jymxsqCl.getSpmc())
								&& jymxsqCl2.getSpdm().equals(jymxsqCl.getSpdm())
								&& jymxsqCl2.getSpggxh().equals(jymxsqCl.getSpggxh())
								&& jymxsqCl2.getSpmxxh() ==jymxsqCl.getSpmxxh() && jymxsqCl2.getFphxz().equals("1")) {
							// jymxsqCl.setFphxz("1");//折扣行
							jymxsqClR = genNewJymxsqCl(jymxsqCl);
							jymxsqClR.setSpje(jymxsqCl.getSpje() + jymxsqCl2.getSpje());
							jymxsqClR.setSpse(jymxsqCl.getSpse() + jymxsqCl2.getSpse());
							jymxsqClR.setJshj(jymxsqCl.getJshj() + jymxsqCl2.getJshj());
							jymxsqClR.setSps(jymxsqCl.getSps());
							if(null != jymxsqClR.getSps() && !jymxsqClR.getSps().equals("")){
								jymxsqClR.setSpdj(jymxsqClR.getSpje()/jymxsqClR.getSps());
							}
							jymxsqClR.setSpmxxh(spmxxh);
							jymxsqClR.setFphxz("0");
							resultList.add(jymxsqClR);
							spmxxh++;
						}
					}
				} else if (jymxsqCl.getFphxz().equals("0")) {
					jymxsqClR = genNewJymxsqCl(jymxsqCl);
					jymxsqClR.setSpmxxh(spmxxh);
					resultList.add(jymxsqClR);
					spmxxh++;
				}
			}
		}
		return resultList;
	}
    
	/**
     * 生成新的JymxsqCl，不会对原对象的值进行改变
     *
     * @param JymxsqCl
     * @return 新JymxsqCl
     */
    private JymxsqCl genNewJymxsqCl(JymxsqCl jymxsqCl){
    	JymxsqCl jymxsqClR = new JymxsqCl();
    	jymxsqClR.setSqlsh(jymxsqCl.getSqlsh());
		jymxsqClR.setDdh(jymxsqCl.getDdh());
		jymxsqClR.setKpddm(jymxsqCl.getKpddm());
		jymxsqClR.setHsbz(jymxsqCl.getHsbz());
		jymxsqClR.setSpmxxh(jymxsqCl.getSpmxxh());
		jymxsqClR.setFphxz(jymxsqCl.getFphxz());
		jymxsqClR.setSpdm(jymxsqCl.getSpdm());
		jymxsqClR.setSpmc(jymxsqCl.getSpmc());
		jymxsqClR.setSpggxh(jymxsqCl.getSpggxh());
		jymxsqClR.setSpzxbm(jymxsqCl.getSpzxbm());
		jymxsqClR.setYhzcbs(jymxsqCl.getYhzcbs());
		jymxsqClR.setYhzcmc(jymxsqCl.getYhzcmc());
		jymxsqClR.setLslbz(jymxsqCl.getLslbz());
		jymxsqClR.setSpdw(jymxsqCl.getSpdw());
		jymxsqClR.setSps(jymxsqCl.getSps());
		jymxsqClR.setSpdj(jymxsqCl.getSpdj());
		jymxsqClR.setSpje(jymxsqCl.getSpje());
		jymxsqClR.setSpsl(jymxsqCl.getSpsl());
		jymxsqClR.setSpse(jymxsqCl.getSpse());
		jymxsqClR.setKce(jymxsqCl.getKce());
		jymxsqClR.setJshj(jymxsqCl.getJshj());
		jymxsqClR.setHzkpxh(jymxsqCl.getHzkpxh());
		jymxsqClR.setLrsj(jymxsqCl.getLrsj());
		jymxsqClR.setLrry(jymxsqCl.getLrry());
		jymxsqClR.setXgsj(jymxsqCl.getXgsj());
		jymxsqClR.setXgry(jymxsqCl.getXgry());
		jymxsqClR.setGsdm(jymxsqCl.getGsdm());
		jymxsqClR.setSkpid(jymxsqCl.getSkpid());
		jymxsqClR.setXfid(jymxsqCl.getXfid());
		jymxsqClR.setYxbz(jymxsqCl.getYxbz());
		jymxsqClR.setSpid(jymxsqCl.getSpid());
		jymxsqClR.setYkjje(jymxsqCl.getYkjje());
		jymxsqClR.setKkjje(jymxsqCl.getKkjje());
		jymxsqClR.setSpbz(jymxsqCl.getSpbz());
    	return jymxsqClR;
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

        double zkzje = 10.02;
        double jshj  = 222.33;
        System.out.println(new BigDecimal((jshj+zkzje)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        DecimalFormat df = new DecimalFormat("0.0");
        double f = 3.181;
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(1, BigDecimal.ROUND_DOWN).doubleValue();
        System.out.println(b.setScale(1, BigDecimal.ROUND_DOWN));

        //System.out.println(zkzje/jshj);
    }
}
