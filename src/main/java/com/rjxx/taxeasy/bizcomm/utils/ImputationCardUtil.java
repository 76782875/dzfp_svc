package com.rjxx.taxeasy.bizcomm.utils;

/**
 * Created by admin 2017-08-16
 * 微信，支付宝归集卡包公共类
 */

import com.rjxx.taxeasy.domains.Jyxxsq;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.domains.Kpspmx;
import com.rjxx.taxeasy.domains.Pp;
import com.rjxx.taxeasy.service.JyxxsqService;
import com.rjxx.taxeasy.service.KplsService;
import com.rjxx.taxeasy.service.PpService;
import com.rjxx.utils.StringUtils;
import com.rjxx.utils.alipay.AlipayUtils;
import com.rjxx.utils.weixin.WeixinUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title:归集卡包类
 * </p>
 */
@Service
public class ImputationCardUtil {

	@Autowired
	private KplsService kplsService;

	@Autowired
	private DataOperate dataOperate;

	@Autowired
	private JyxxsqService jyxxsqService;

	@Autowired
	private PpService ppService;

	@Value("$pdf.save-path:}")
	private String pdf_file_url;



	/**
	 * 截取pdf_file_url
	 * @param pdf_url
	 * @return string
	 *
	 * */
	private String getPdf_file_url(String pdf_url){
		int pos = pdf_url.lastIndexOf("/", pdf_url.length() - 2);
		pdf_url = pdf_url.substring(0, pos);
		return pdf_url;
	}

	/**
	 * <br>
	 * 方法说明：将发票归集到卡包或发票管家
	 * 输入参数： <br>
	 * 返回类型：
	 */
	public boolean invoice2card(int kplsh){
		// 发票主数据
		Kpls kpls = kplsService.findOne(kplsh);
		// 发票明细部分
		List<Kpspmx> kpspmxList = dataOperate.getPDFSpmx(kplsh);
		//调用重载的invoice2card方法
		return invoice2card(kpls,kpspmxList);
	}

	/**
	 * <br>
	 * 方法说明：将发票归集到卡包或发票管家
	 * 输入参数： <br>
	 * 返回类型：
	 */
	public boolean invoice2card(Kpls kpls){
		// 发票主数据
		//Kpls kpls = kplsService.findOne(kpls.getKplsh());
		// 发票明细部分
		List<Kpspmx> kpspmxList = dataOperate.getPDFSpmx(kpls.getKplsh());
		//调用重载的invoice2card方法
		return invoice2card(kpls,kpspmxList);
	}


	/**
	 * <br>
	 * 方法说明：将发票归集到卡包或发票管家
	 * 输入参数： <br>
	 * 返回类型：
	 */
	public boolean invoice2card(Jyxxsq jyxxsq,Kpls kpls){
		// 发票主数据
		//Kpls kpls = kplsService.findOne(kpls.getKplsh());
		// 发票明细部分
		List<Kpspmx> kpspmxList = dataOperate.getPDFSpmx(kpls.getKplsh());
		//调用重载的invoice2card方法
		return invoice2card(jyxxsq,kpls,kpspmxList);
	}

	/**
	 * <br>
	 * 方法说明：将发票归集到卡包或发票管家
	 * 输入参数： <br>
	 * 返回类型：
	 */
	public boolean invoice2card(Kpls kpls, List<Kpspmx> kpspmxList) {
		int kplsh = kpls.getKplsh();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("kplsh",kplsh);
		Jyxxsq jyxxsq = jyxxsqService.findSjlyAndOpenidByMap(params);
		//交易信息数据存在
		if(null != jyxxsq && !jyxxsq.equals("")){
			//数据来源5表示支付宝
			if(jyxxsq.getSjly().equals("5")){
				AlipayUtils alipayUtils = new AlipayUtils();
				Pp pp = ppService.findOnePpByGsdmSkpid(kpls.getSkpid(), kpls.getGsdm());
				if (pp == null || StringUtils.isBlank(pp.getAliMShortName()) || StringUtils.isBlank(pp.getAliSubMShortName()) || StringUtils.isBlank(jyxxsq.getOpenid())) {
					return false;
				}else{
					try {
						alipayUtils.syncInvoiceAlipay(jyxxsq.getOpenid(),kpls,kpspmxList,pp.getAliMShortName(),pp.getAliSubMShortName());
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
				}
				return true;

			}else if(jyxxsq.getSjly().equals("4")){ //数据来源4表示微信
				WeixinUtils weinxinUtil = new WeixinUtils();
				String result = weinxinUtil.fpInsertCardBox(jyxxsq.getDdh(),kpls.getPdfurl()+getPdf_file_url(pdf_file_url),kpspmxList,kpls);
			   	 //存入卡包失败
			     if(null == result){
			    	return false;
				}else{
			    	return true;
				}
			}else{
				return false;
			}
		}

		return true;
	}

	/**
	 * <br>
	 * 方法说明：将发票归集到卡包或发票管家
	 * 输入参数： <br>
	 * 返回类型：
	 */
	public boolean invoice2card(Jyxxsq jyxxsq,Kpls kpls, List<Kpspmx> kpspmxList) {
		if(null != jyxxsq && !jyxxsq.equals("")){
			//数据来源5表示支付宝
			if(jyxxsq.getSjly().equals("5")){

				AlipayUtils alipayUtils = new AlipayUtils();
				Pp pp = ppService.findOnePpByGsdmSkpid(kpls.getSkpid(), kpls.getGsdm());
				if (pp == null || StringUtils.isBlank(pp.getAliMShortName()) || StringUtils.isBlank(pp.getAliSubMShortName()) || StringUtils.isBlank(jyxxsq.getOpenid())) {
					return false;
				}else{
					try {
						alipayUtils.syncInvoiceAlipay(jyxxsq.getOpenid(),kpls,kpspmxList,pp.getAliMShortName(),pp.getAliSubMShortName());
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
				}
				return true;
			}else if(jyxxsq.getSjly().equals("4")){ //数据来源4表示微信
				WeixinUtils weinxinUtil = new WeixinUtils();
				String result = weinxinUtil.fpInsertCardBox(jyxxsq.getDdh(),kpls.getPdfurl()+getPdf_file_url(pdf_file_url),kpspmxList,kpls);
				//存入卡包失败
				if(null == result){
					return false;
				}else{
					return true;
				}
			}else{
				return false;
			}
		}

		return true;
	}

	/**
	 * <br>
	 * 方法说明：主方法，用于测试 <br>
	 * 输入参数： <br>
	 * 返回类型：
	 */
	public static void main(String[] args) {
			ImputationCardUtil tt = new ImputationCardUtil();
		try {
			String result =  tt.getPdf_file_url("/usr/local/e-invoice-file/e-invoice-file/");
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
