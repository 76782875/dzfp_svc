package com.rjxx.taxeasy.bizcomm.utils;

import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.service.CszbService;
import com.rjxx.taxeasy.service.ZffsService;
import com.rjxx.taxeasy.vo.JymxsqClVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class Discount2UnitDealUtil {
    
	@Autowired
	private ZffsService zffsService;
	
	@Autowired
	private CszbService cszbService;


	/**
	 * 处理申请明细信息，将其处理到t_jymxsq_cl表中
	 * @param jymxsqList 原单笔交易申请明细
	 * @param zfzkzje 交易支付明细的所有金额（价税合计）
	 *
	 */
	public List<JymxsqCl> dealDiscount(List<Jymxsq> jymxsqList, double qjzk ,double zfzkzje) {
		//处理折扣行最终结果list
		List<JymxsqCl> jymxsqClList = new ArrayList<JymxsqCl>();

		List<JymxsqClVo> jymxsqClVoList = new ArrayList<JymxsqClVo>();
		double dhhj = 0.00;//单行合计(净销售额合计)
		double zshhj = 0.00; //正数行的合计金额(零售价合计)
		for (int i = 0; i < jymxsqList.size(); i++) {
			Jymxsq jymxsq = jymxsqList.get(i);
			dhhj = dhhj + jymxsq.getJshj();
			if (jymxsq.getFphxz().equals("2") || jymxsq.getFphxz().equals("0")) {
				zshhj = zshhj + jymxsq.getJshj();
			}
		}
		List<JymxsqClVo> jymxsqClTmpVoList = mergeDiscountLine(jymxsqList);
		double qjzkje = 0d;//最后一笔的分摊的全局折扣金额倒减出来
		double ftzfje = 0d;//最后一笔的分摊的购物卡支付金额倒减出来
		int flag =0;
		double flag_hj =0d;
		JymxsqClVo jymxsqClVoMax =null;
		for(int t=0;t<jymxsqClTmpVoList.size();t++){
			JymxsqClVo jymxsqClVo = jymxsqClTmpVoList.get(t);
			if(jymxsqClVo.getSpje()>=flag_hj && jymxsqClVo.getSpje() != jymxsqClVo.getSphzkje()){
				flag_hj = jymxsqClVo.getSpje();
				flag = t;
			}
		}
		jymxsqClVoMax = new JymxsqClVo(jymxsqClTmpVoList.get(flag));

		jymxsqClTmpVoList.remove(flag);
		for(int j=0;j<jymxsqClTmpVoList.size();j++){
			/*if(j == jymxsqClTmpVoList.size()-1){
				JymxsqClVo jymxsqClVo = jymxsqClTmpVoList.get(j);
				JymxsqClVo JymxsqClVoTmp = new JymxsqClVo(jymxsqClVo);
				JymxsqClVoTmp.setQjzkje(qjzk-qjzkje);
				JymxsqClVoTmp.setZkzje(JymxsqClVoTmp.getSphzkje()+JymxsqClVoTmp.getQjzkje());
				JymxsqClVoTmp.setSphjjxse(jymxsqClVo.getJshj()-JymxsqClVoTmp.getQjzkje());
				JymxsqClVoTmp.setFtzfje(zfzkzje-ftzfje);
				JymxsqClVoTmp.setFthspdj((JymxsqClVoTmp.getSpdj()==null||JymxsqClVoTmp.getSpdj().equals(""))?null:(JymxsqClVoTmp.getSpdj()-JymxsqClVoTmp.getFtzfje()/JymxsqClVoTmp.getSps()));
				jymxsqClVoList.add(JymxsqClVoTmp);

			}else{*/
				JymxsqClVo jymxsqClVo = jymxsqClTmpVoList.get(j);
				JymxsqClVo JymxsqClVoTmp = new JymxsqClVo(jymxsqClVo);
				double qjzkTmp = qjzk*jymxsqClVo.getJshj()/dhhj;
				JymxsqClVoTmp.setQjzkje(new BigDecimal(qjzkTmp).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				JymxsqClVoTmp.setZkzje(JymxsqClVoTmp.getSphzkje()+JymxsqClVoTmp.getQjzkje());
				JymxsqClVoTmp.setSphjjxse(jymxsqClVo.getJshj()-JymxsqClVoTmp.getQjzkje());
				double ftzfjeTmp = zfzkzje*JymxsqClVoTmp.getSphjjxse()/(dhhj-qjzk);
				JymxsqClVoTmp.setFtzfje(new BigDecimal(ftzfjeTmp).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				JymxsqClVoTmp.setFthspdj((JymxsqClVoTmp.getSpdj()==null||JymxsqClVoTmp.getSpdj().equals(""))?null:(JymxsqClVoTmp.getSpdj()-JymxsqClVoTmp.getFtzfje()/JymxsqClVoTmp.getSps()));
				jymxsqClVoList.add(JymxsqClVoTmp);
				qjzkje = qjzkje+JymxsqClVoTmp.getQjzkje();
				ftzfje = ftzfje+JymxsqClVoTmp.getFtzfje();
			//}


		}

		jymxsqClVoMax.setQjzkje(new BigDecimal(qjzk-qjzkje).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		jymxsqClVoMax.setZkzje(new BigDecimal(jymxsqClVoMax.getSphzkje()+jymxsqClVoMax.getQjzkje()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		jymxsqClVoMax.setSphjjxse(new BigDecimal(jymxsqClVoMax.getJshj()-jymxsqClVoMax.getQjzkje()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		jymxsqClVoMax.setFtzfje(new BigDecimal(zfzkzje-ftzfje).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		jymxsqClVoMax.setFthspdj((jymxsqClVoMax.getSpdj()==null||jymxsqClVoMax.getSpdj().equals(""))?null:new BigDecimal(jymxsqClVoMax.getSpdj()-jymxsqClVoMax.getFtzfje()/jymxsqClVoMax.getSps()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		jymxsqClVoList.add(jymxsqClVoMax);

		for(int t=0;t<jymxsqClVoList.size();t++){
			JymxsqClVo JymxsqClVoTmp2 = jymxsqClVoList.get(t);
			//如果不存在支付折扣和全局折扣，则正常行不进行处理
			if(JymxsqClVoTmp2.getZkzje()<=0d){
				JymxsqCl jymxsqCl = genNewJymxsqCl(JymxsqClVoTmp2);

				jymxsqCl.setSpje((jymxsqCl.getSpdj()==null || jymxsqCl.getSpdj().equals(""))
						?(JymxsqClVoTmp2.getJshj()-JymxsqClVoTmp2.getSphzkje()-JymxsqClVoTmp2.getFtzfje())
						:JymxsqClVoTmp2.getSpje()+JymxsqClVoTmp2.getSphzkje()-JymxsqClVoTmp2.getFtzfje());
				if(jymxsqCl.getSps()!=null){
					jymxsqCl.setSpdj(jymxsqCl.getSpje()/jymxsqCl.getSps());
				}
				jymxsqCl.setJshj(jymxsqCl.getSpje());
				jymxsqCl.setSpse(null);
				jymxsqCl.setFphxz("0");
				jymxsqClList.add(jymxsqCl);
			}else{
				JymxsqCl jymxsqCl = genNewJymxsqCl(JymxsqClVoTmp2);

				jymxsqCl.setSpje((jymxsqCl.getSpdj()==null || jymxsqCl.getSpdj().equals(""))
						?(JymxsqClVoTmp2.getJshj()-JymxsqClVoTmp2.getSphzkje()-JymxsqClVoTmp2.getFtzfje())
						:JymxsqClVoTmp2.getSpje()+JymxsqClVoTmp2.getSphzkje()-JymxsqClVoTmp2.getFtzfje());
				if(jymxsqCl.getSps()!=null){
					jymxsqCl.setSpdj(jymxsqCl.getSpje()/jymxsqCl.getSps());
				}
				jymxsqCl.setJshj(jymxsqCl.getSpje());
				jymxsqCl.setSpse(null);
				jymxsqCl.setFphxz("2");
				jymxsqClList.add(jymxsqCl);
				JymxsqCl jymxsqClTmp = genNewJymxsqCl(JymxsqClVoTmp2);
				jymxsqClTmp.setSpje(-JymxsqClVoTmp2.getZkzje());
				jymxsqClTmp.setSpdj(null);
				jymxsqClTmp.setSps(null);
				jymxsqClTmp.setFphxz("1");
				jymxsqClTmp.setSpse(null);
				jymxsqClTmp.setJshj(-JymxsqClVoTmp2.getZkzje());
				jymxsqClList.add(jymxsqClTmp);
			}

		}
		return jymxsqClList;
	}


	/**
	 * 处理申请明细信息，将折扣行合并到被折扣行。
	 * @param jymxsqList 原单笔交易申请明细
	 *
	 */
	public List<JymxsqClVo> mergeDiscountLine(List<Jymxsq> jymxsqList) {

		List<JymxsqClVo> jymxsqClTmpVoList = new ArrayList<JymxsqClVo>();
		for (int i = 0; i < jymxsqList.size(); i++) {
			Jymxsq jymxsq = jymxsqList.get(i);
			if (jymxsq.getFphxz().equals("0")) {
				JymxsqClVo jymxsqClVo = new JymxsqClVo(jymxsq);
				jymxsqClVo.setSphzkje(0d);
				jymxsqClTmpVoList.add(jymxsqClVo);
			} else {
				if (jymxsq.getFphxz().equals("2")) {
					JymxsqClVo jymxsqClVoTmp = new JymxsqClVo(jymxsq);
					for (int j = 0; j < jymxsqList.size(); j++) {
						Jymxsq jymxsqTmp = jymxsqList.get(j);
						if (jymxsq.getSpmc().equals(jymxsqTmp.getSpmc()) && jymxsq.getSpdm().equals(jymxsqTmp.getSpdm())
								&& jymxsq.getSpggxh().equals(jymxsqTmp.getSpggxh()) && jymxsq.getDdh().equals(jymxsqTmp.getDdh())
								&& jymxsqTmp.getSpmxxh() == jymxsqTmp.getSpmxxh() && jymxsqTmp.getFphxz().equals("1")&& (i+1==j)) {
							jymxsqClVoTmp.setSphzkje(-jymxsqTmp.getJshj());
							jymxsqClVoTmp.setSpje(new BigDecimal(jymxsqClVoTmp.getSpje()+jymxsqTmp.getSpje()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							jymxsqClVoTmp.setSpse(new BigDecimal(jymxsqClVoTmp.getSpse()+jymxsqTmp.getSpse()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							jymxsqClVoTmp.setJshj(new BigDecimal(jymxsqClVoTmp.getJshj()+jymxsqTmp.getJshj()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
							jymxsqClTmpVoList.add(jymxsqClVoTmp);
						}
					}
				}
			}
		}
		return jymxsqClTmpVoList;
	}


	/**
	 * 生成新的JymxsqCl，不会对原对象的值进行改变
	 *
	 * @param
	 * @return 新JymxsqCl
	 */
	private JymxsqCl genNewJymxsqCl(JymxsqClVo jymxsqClVo){
		JymxsqCl jymxsqClR = new JymxsqCl();
		jymxsqClR.setSqlsh(jymxsqClVo.getSqlsh());
		jymxsqClR.setDdh(jymxsqClVo.getDdh());
		jymxsqClR.setKpddm(jymxsqClVo.getKpddm());
		jymxsqClR.setHsbz(jymxsqClVo.getHsbz());
		jymxsqClR.setSpmxxh(jymxsqClVo.getSpmxxh());
		jymxsqClR.setFphxz(jymxsqClVo.getFphxz());
		jymxsqClR.setSpdm(jymxsqClVo.getSpdm());
		jymxsqClR.setSpmc(jymxsqClVo.getSpmc());
		jymxsqClR.setSpggxh(jymxsqClVo.getSpggxh());
		jymxsqClR.setSpzxbm(jymxsqClVo.getSpzxbm());
		jymxsqClR.setYhzcbs(jymxsqClVo.getYhzcbs());
		jymxsqClR.setYhzcmc(jymxsqClVo.getYhzcmc());
		jymxsqClR.setLslbz(jymxsqClVo.getLslbz());
		jymxsqClR.setSpdw(jymxsqClVo.getSpdw());
		jymxsqClR.setSps(jymxsqClVo.getSps());
		jymxsqClR.setSpdj(jymxsqClVo.getSpdj());
		jymxsqClR.setSpje(jymxsqClVo.getSpje());
		jymxsqClR.setSpsl(jymxsqClVo.getSpsl());
		jymxsqClR.setSpse(jymxsqClVo.getSpse());
		jymxsqClR.setKce(jymxsqClVo.getKce());
		jymxsqClR.setJshj(jymxsqClVo.getJshj());
		jymxsqClR.setHzkpxh(jymxsqClVo.getHzkpxh());
		jymxsqClR.setLrsj(jymxsqClVo.getLrsj());
		jymxsqClR.setLrry(jymxsqClVo.getLrry());
		jymxsqClR.setXgsj(jymxsqClVo.getXgsj());
		jymxsqClR.setXgry(jymxsqClVo.getXgry());
		jymxsqClR.setGsdm(jymxsqClVo.getGsdm());
		jymxsqClR.setSkpid(jymxsqClVo.getSkpid());
		jymxsqClR.setXfid(jymxsqClVo.getXfid());
		jymxsqClR.setYxbz(jymxsqClVo.getYxbz());
		jymxsqClR.setSpid(jymxsqClVo.getSpid());
		jymxsqClR.setYkjje(jymxsqClVo.getYkjje());
		jymxsqClR.setKkjje(jymxsqClVo.getKkjje());
		jymxsqClR.setSpbz(jymxsqClVo.getSpbz());
		return jymxsqClR;
	}



	/**
     * 生成新的JymxsqCl，不会对原对象的值进行改变
     *
     * @param
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

        /*double zkzje = 10.02;
        double jshj  = 222.33;
        System.out.println(new BigDecimal((jshj+zkzje)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        DecimalFormat df = new DecimalFormat("0.0");
        double f = 3.181;
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(1, BigDecimal.ROUND_DOWN).doubleValue();
        System.out.println(b.setScale(1, BigDecimal.ROUND_DOWN));*/
        //System.out.println(zkzje/jshj);
		List<Jymxsq> jymxsqList = new ArrayList<Jymxsq>();
		Jymxsq jymxsq1 = new Jymxsq();
		jymxsq1.setSpdm("1010115010000000000");
		jymxsq1.setSpmc("打折出清码(YD)");
		jymxsq1.setFphxz("0");
		jymxsq1.setDdh("123");
		jymxsq1.setSpggxh("");
		jymxsq1.setSps(1d);
		jymxsq1.setSpdj(200d);
		jymxsq1.setSpje(200d);
		jymxsq1.setSpsl(0.11d);
		jymxsq1.setSpse(19.82);
		jymxsq1.setJshj(200d);
		jymxsq1.setSpmxxh(1);

		Jymxsq jymxsq2 = new Jymxsq();
		jymxsq2.setSpdm("1010115000000000000");
		jymxsq2.setSpmc("新西兰姬娜果(YD)");
		jymxsq2.setFphxz("2");
		jymxsq2.setSps(1d);
		jymxsq2.setDdh("123");
		jymxsq2.setSpggxh("");
		jymxsq2.setSpdj(790d);
		jymxsq2.setSpje(790d);
		jymxsq2.setSpsl(0.11d);
		jymxsq2.setSpse(78.29);
		jymxsq2.setJshj(790d);
		jymxsq2.setSpmxxh(2);

		Jymxsq jymxsq3 = new Jymxsq();
		jymxsq3.setSpdm("1010115000000000000");
		jymxsq3.setSpmc("新西兰姬娜果(YD)");
		jymxsq3.setFphxz("1");
		jymxsq3.setDdh("123");
		jymxsq3.setSpggxh("");
		jymxsq3.setSpsl(0.11d);
		jymxsq3.setSpse(-19.82);
		jymxsq3.setSpje(-200d);
		jymxsq3.setJshj(-200d);
		jymxsq3.setSpmxxh(2);

		Jymxsq jymxsq4 = new Jymxsq();
		jymxsq4.setSpdm("1010115000000000000");
		jymxsq4.setSpmc("打折出清码(GG)");
		jymxsq4.setFphxz("0");
		jymxsq4.setSps(2d);
		jymxsq4.setDdh("123");
		jymxsq4.setSpggxh("");
		jymxsq4.setSpdj(400d);
		jymxsq4.setSpsl(0.11d);
		jymxsq4.setSpje(800d);
		jymxsq4.setSpse(79.28);
		jymxsq4.setJshj(800d);
		jymxsq4.setSpmxxh(3);

		Jymxsq jymxsq5 = new Jymxsq();
		jymxsq5.setSpdm("1010115000000000000");
		jymxsq5.setSpmc("出清码(GG)");
		jymxsq5.setFphxz("2");
		jymxsq5.setSps(1d);
		jymxsq5.setDdh("123");
		jymxsq5.setSpggxh("");
		jymxsq5.setSpdj(100d);
		jymxsq5.setSpsl(0.11d);
		jymxsq5.setSpse(9.91);
		jymxsq5.setSpje(100d);
		jymxsq5.setJshj(100d);
		jymxsq5.setSpmxxh(4);

		Jymxsq jymxsq6 = new Jymxsq();
		jymxsq6.setSpdm("1010115000000000000");
		jymxsq6.setSpmc("出清码(GG)");
		jymxsq6.setFphxz("1");
		jymxsq6.setSpsl(0.11d);
		jymxsq6.setSpse(-9.91);
		jymxsq6.setDdh("123");
		jymxsq6.setSpggxh("");
		jymxsq6.setJshj(-100d);
		jymxsq6.setSpje(-100d);
		jymxsq6.setSpmxxh(4);
		jymxsqList.add(jymxsq1);
		jymxsqList.add(jymxsq2);
		jymxsqList.add(jymxsq3);
		jymxsqList.add(jymxsq4);
		jymxsqList.add(jymxsq5);
		jymxsqList.add(jymxsq6);
		Discount2UnitDealUtil t = new Discount2UnitDealUtil();
		List<JymxsqCl> list = t.dealDiscount(jymxsqList,0d,500d);
		double hj=0d;
		for(int i=0;i<list.size();i++){
			JymxsqCl tt = list.get(i);
			System.out.println(tt.getSps()+","+tt.getSpdj()+","+tt.getSpmxxh()+","+tt.getSpmc()+","+tt.getFphxz()+","+tt.getSpje()+","+tt.getSpse()+","+tt.getJshj());
		hj = hj+t.div(tt.getJshj(),1d,2);
		}
		System.out.println(hj);
    }
}
