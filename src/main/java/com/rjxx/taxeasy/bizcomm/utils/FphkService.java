package com.rjxx.taxeasy.bizcomm.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.taxeasy.domains.Cszb;
import com.rjxx.taxeasy.domains.Jyls;
import com.rjxx.taxeasy.domains.Jyspmx;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.domains.Kpspmx;
import com.rjxx.taxeasy.service.CszbService;
import com.rjxx.taxeasy.service.FpgzService;
import com.rjxx.taxeasy.service.GsxxService;
import com.rjxx.taxeasy.service.JylsService;
import com.rjxx.taxeasy.service.JyspmxService;
import com.rjxx.taxeasy.service.KplsService;
import com.rjxx.taxeasy.service.KpspmxService;
import com.rjxx.taxeasy.service.XfService;
import com.rjxx.taxeasy.vo.Kpspmxvo;
import com.rjxx.time.TimeUtil;

@Service
public class FphkService {
	
	 @Autowired private KplsService kplsService;
	 @Autowired private JylsService jylsService;
	 @Autowired private FpgzService fpgzService;
	 @Autowired private SkService skService;
	 @Autowired private JyspmxService jymxService;
	 @Autowired private GsxxService gsxxService;
	 @Autowired private KpspmxService kpspmxService;
	 @Autowired private DataOperte dc;
	 @Autowired private XfService xfService;
	 @Autowired private CszbService cszbService;
	 /**
	  * 发票换开红冲处理
	  * @param kplshList
	  * @param djh
	  * @param fpczlxdm
	  * @param yhid
	 * @throws Exception 
	  */
	 
	 public Map addhcjyls(Map map, List<Integer> kplshList, int yhid, String gsdm,int djh) throws Exception{
		 
			Map params = new HashMap<>();
			InvoiceResponse response=new InvoiceResponse();
			DecimalFormat df = new DecimalFormat("#.00");
			DecimalFormat df6 = new DecimalFormat("#.000000");
			Map  result=new HashMap();
			for(Integer kplsh:kplshList){
				Map map1=this.saveJyls(kplsh,yhid,map,"1");
				Kpls kpls2=(Kpls)map1.get("kpls2");
				Jyls jyls1=(Jyls)map1.get("jyls1");
				 Kpls kpls = kplsService.findOne(kplsh);
				  /*  Cszb cszb=new Cszb();
					cszb.setGsdm(gsdm);
					cszb.setXfid(kpls.getXfid());
					cszb.setKpdid(kpls.getSkpid());
					cszb.setCsid(15);
					Cszb cszb2 =(Cszb) cszbService.findsfzlkpByParams(cszb);
					if(cszb2.getCsz().equals("否")){*/
						kpls2.setFpztdm("04"); //正在开具
						kplsService.save(kpls2);
						//kpls.setFpztdm("09");//待红冲
						//kplsService.save(kpls);
						result.put("dhcbz", "1");//待红冲标志  1 待红冲 0直连红冲
						result.put("kplsh", kpls2.getKplsh());
				/*	}else if(cszb2.getCsz().equals("是")){
					     response = skService.callService(kpls2.getKplsh());
					     result.put("dhcbz", "0");//待红冲标志
						if ("0000".equals(response.getReturnCode())) {
							
							Map param2 = new HashMap<>();
							param2.put("kplsh", kpls2.getKplsh());
							param2.put("fpztdm", "02");
							
							kplsService.updateFpczlx(param2);
							
							result.put("hcbz", "0");
						}else{
							 dc.saveLog(djh, "92", "1", "", "调用红冲接口失败"+response.getReturnMessage(), 2, jyls1.getXfsh(), jyls1.getJylsh());
							 result.put("hcbz", "1");
							 result.put("hcMessage", response.getReturnMessage());
						}
			   }*/
		   }
			return result;
	}
	/**
	 * 发票换开操作
	 * @param map
	 * @param kplshList
	 * @param djh
	 * @param yhid
	 * @param gsdm
	 * @return
	 * @throws Exception
	 */
    public Map fphk(Map map,List<Integer> kplshList,int djh, int yhid,String gsdm) throws Exception{
    	
      Map resultMap=new HashMap();
      
      Map hcmap= this.addhcjyls( map,kplshList,yhid,gsdm, djh);//发票红冲操作
     
      String dhcbz=hcmap.get("dhcbz").toString(); //待红冲标志
      
      if(dhcbz.equals("1")){//待红冲标志为1时表示是待红冲数据 当红冲数据发票号码发票代码不为空时，表示红冲成功
    	  String kplsh=hcmap.get("kplsh").toString(); //待红冲标志
		  Kpls kpls = kplsService.findOne(Integer.valueOf(kplsh));
		  // if(null!=kpls.getFpdm()&&null!=kpls.getFphm()){
			  this.addfphkls(map, kplshList, yhid, gsdm, djh);
			  resultMap.put("dhkcz", "0");//待换开操作 成功
		  //}else{
			//  resultMap.put("dhkcz", "1");//待 换开操作 失败
		 /// }

      }else{
    	  resultMap.put("dhkcz", "1");
    	  
      }
      
      /*else if(dhcbz.equals("0")){
      String hcbz=hcmap.get("hcbz").toString();   //红冲标志
      if(hcbz.equals("0")){
    	  this.addfphkls(map, kplshList, yhid, gsdm, djh);
       
		     }else if(hcbz.equals("1")){
		    	  String hcMessage=hcmap.get("hcMessage").toString();
		   	      resultMap.put("hkcz", "1");//换开失败 换开中红冲步骤失败
		    	  resultMap.put("hkMessage", hcMessage);
		      }
      }*/
      return resultMap;
}
   /**
    * 保存流水
    * @param kplsh
    * @param yhid
    * @param map
    * @param czbz
    * @return
    */
   @Transactional
   public Map saveJyls(Integer kplsh,int yhid,Map map,String czbz){
	    DecimalFormat df = new DecimalFormat("#.00");
		DecimalFormat df6 = new DecimalFormat("#.000000");
	
		// 保存交易流水表
		Map param3 = new HashMap<>();
		param3.put("kplsh", kplsh);
		List<Kpls> kplsList = kplsService.printSingle(param3);//获取原开票流水信息
		
		Kpls kpls = kplsList.get(0);
		int djh = kpls.getDjh();
		Map param4 = new HashMap<>();
		param4.put("djh", djh);
		Jyls jyls= jylsService.findJylsByDjh(param4);//获取原交易流水信息
		
		String ddh = jyls.getDdh(); // 查询原交易流水得ddh
		
		
	    //保存交易流水
	    String gfbz =map.get("gfbz").toString();
		String gfemail =map.get("gfemail").toString();
		String gfdz =map.get("gfdz").toString();
		String gfdh =map.get("gfdh").toString();
		String tqm = map.get("tqm").toString();
		String dybz = map.get("dybz").toString();

		
		String fpzl =map.get("fpzl").toString();
		String hcjshj =map.get("hcjshj").toString();
		String gfsh =map.get("gfsh").toString();
		String gfyh =map.get("gfyh").toString();
		String gfzh = map.get("gfzh").toString();
		String gfmc = map.get("gfmc").toString();  
		String gsdm = map.get("gsdm").toString();
  
		String hjhcje = ""; 
		String[] hcje = hcjshj.split(",");
		for (int j = 0; j < hcje.length; j++) {
			hjhcje += hcje[j];
		}
		String hcjshjje = hjhcje; // 本次红冲金额的价税合计
		map.put("hcjshjje", hcjshjje);
		
		
		Jyls jyls1 = new Jyls();
		jyls1.setDdh(ddh);
		String jylsh = "JY" + new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
		jyls1.setJylsh(jylsh);
		jyls1.setJylssj(TimeUtil.getNowDate());
		
		if(czbz.equals("2")){//操作标志2为换开
			jyls1.setFpzldm(fpzl);//换开发票种类
			jyls1.setFpczlxdm("11");//发票操作类型//11 正常开具
			jyls1.setClztdm("00");//处理状态代码
			jyls1.setJshj(Double.valueOf(kpls.getJshj()));

		}else if(czbz.equals("1")){ //红冲
			jyls1.setFpzldm(kpls.getFpzldm());//换开发票种类
			jyls1.setFpczlxdm("13");//发票操作类型//13 换开
			jyls1.setClztdm("00");//处理状态代码
			
			jyls1.setJshj(-Double.valueOf(kpls.getJshj()));

		}
		
		jyls1.setXfid(kpls.getXfid());
		jyls1.setXfsh(kpls.getXfsh());
		jyls1.setXfmc(kpls.getXfmc());
		jyls1.setXfyh(kpls.getXfyh());
		jyls1.setXfyhzh(kpls.getXfyhzh());
		jyls1.setXflxr(kpls.getXflxr());
		jyls1.setXfdh(kpls.getXfdh());
		jyls1.setXfdz(kpls.getXfdz());
		jyls1.setGfid(kpls.getGfid());
		
		if(gfsh!=null&&!"".equals(gfsh)){
			jyls1.setGfsh(gfsh);//购方税号

		}else{
			jyls1.setGfsh(kpls.getGfsh());//购方税号

		}
		if(gfmc!=null&&!gfmc.equals("")){
			jyls1.setGfmc(gfmc);//购方名称

		}else{
			jyls1.setGfmc(kpls.getGfmc());//购方名称

		}
		if(gfyh!=null&&!"".equals(gfyh)){
			jyls1.setGfyh(gfyh);//购方银行
		}else{
			jyls1.setGfyh(kpls.getGfyh());//购方银行

		}
		if(gfzh!=null&&!"".equals(gfzh)){
			jyls1.setGfyhzh(gfzh);//购方银行账号

		}else{
			jyls1.setGfyhzh(kpls.getGfyhzh());//购方银行账号

			
		}
		jyls1.setGflxr(kpls.getGflxr());
		
		if(gfdh!=null&&!"".equals(gfdh)){
			jyls1.setGfdh(gfdh);  //购方电话

		}else{
			jyls1.setGfdh(kpls.getGfdh());

		}
		
		if(gfdz!=null&&!"".equals(gfdz)){
			jyls1.setGfdz(gfdz);  //购方地址

		}else{
			jyls1.setGfdz(kpls.getGfdz());

		}
		
		jyls1.setGfyb(kpls.getGfyb());
		
		if(gfemail!=null&&!"".equals(gfemail)){
			jyls1.setGfemail(gfemail);  //购方邮箱

		}else{
			jyls1.setGfemail(kpls.getGfemail());

		}
		
		if(gfbz!=null&&!"".equals(gfbz)){
			jyls1.setBz(gfbz);  //备注

		}else{
			jyls1.setBz(kpls.getBz());  //备注

		}
		jyls1.setSkr(kpls.getSkr());
		jyls1.setKpr(kpls.getKpr());
		jyls1.setFhr(kpls.getFhr());
		jyls1.setSsyf(kpls.getSsyf());
		jyls1.setYfpdm(kpls.getFpdm());
		jyls1.setYfphm(kpls.getFphm());
		jyls1.setHsbz("0");
		jyls1.setYkpjshj(0d);
		jyls1.setYxbz("1");
		jyls1.setGsdm(kpls.getGsdm());
		jyls1.setLrry(yhid);
		jyls1.setLrsj(TimeUtil.getNowDate());
		jyls1.setXgry(yhid);
		jyls1.setXgsj(TimeUtil.getNowDate());
		jyls1.setSkpid(kpls.getSkpid());
		jylsService.save(jyls1);
		
		//保存开票流水
		Kpls kpls2 = new Kpls();
		if(czbz.equals("1")){
	    djh = kpls.getDjh();
		kpls2.setDjh(djh);
		kpls2.setJylsh(jylsh);
		kpls2.setJylssj(jyls1.getJylssj());
		kpls2.setFpzldm(jyls1.getFpzldm());
		kpls2.setFpczlxdm(jyls1.getFpczlxdm());
		kpls2.setXfid(jyls1.getXfid());
		kpls2.setXfsh(jyls1.getXfsh());
		kpls2.setXfmc(jyls1.getXfmc());
		kpls2.setXfyh(jyls1.getXfyh());
		kpls2.setXfyhzh(jyls1.getXfyhzh());
		kpls2.setXflxr(jyls1.getXflxr());
		kpls2.setXfdh(jyls1.getXfdh());
		kpls2.setXfdz(jyls1.getXfdz());
		kpls2.setGfid(jyls1.getGfid());
		kpls2.setGfsh(jyls1.getGfsh());
		kpls2.setGfmc(jyls1.getGfmc());
		kpls2.setGfyh(jyls1.getGfyh());
		kpls2.setGfyhzh(jyls1.getGfyhzh());
		kpls2.setGflxr(jyls1.getGflxr());
		kpls2.setGfdh(jyls1.getGfdh());
		kpls2.setGfdz(jyls1.getGfdz());
		kpls2.setGfyb(jyls1.getGfyb());
		kpls2.setGfemail(jyls1.getGfemail());
		kpls2.setBz(jyls1.getBz());
		kpls2.setSkr(jyls1.getSkr());
		kpls2.setKpr(jyls1.getKpr());
		kpls2.setFhr(jyls1.getFhr());
		kpls2.setHztzdh(jyls1.getHztzdh());
		kpls2.setPrintflag(kpls.getPrintflag());
		/*if(dybz.equals("0")){//打印标志
			kpls2.setPrintflag("0");
		}else if(dybz.equals("2")){
			kpls2.setPrintflag("2");
		}*/
		kpls2.setHkFpdm(jyls1.getYfpdm());//原发票代码
		kpls2.setHkFphm(jyls1.getYfphm());//原发票号码
		kpls2.setJshj(jyls1.getJshj());
		if(czbz.equals("2")){
			kpls2.setHkbz("1");//换开标志

		}else if(czbz.equals("1")){
			kpls2.setHkbz("0");//换开标志

		}
		kpls2.setGsdm(jyls1.getGsdm());
		kpls2.setYxbz("1");
		kpls2.setLrsj(jyls1.getLrsj());
		kpls2.setXgsj(jyls1.getXgsj());
		kpls2.setSkpid(jyls1.getSkpid());
		kpls2.setLrry(yhid);
		kpls2.setXgry(yhid);
		kpls2.setFpztdm("00");
		kpls2.setHjje(kpls.getHjje());
		kpls2.setHjse(kpls.getHjse());
		
		if(czbz.equals("2")){
			kpls2.setJshj(Double.valueOf(df.format(Double.valueOf(hcjshjje))));

		}else if(czbz.equals("1")){
			kpls2.setJshj(-Double.valueOf(df.format(Double.valueOf(hcjshjje))));

		}
		
		kplsService.save(kpls2);
		}
		Map paramsMx = new HashMap<>();
		paramsMx.put("id", kplsh);
		List<Kpspmxvo> kpspmxList = kpspmxService.findAllByParams(paramsMx);//查询原开票流水对应的商品明细列表
		
		for(int i=0;i<kpspmxList.size();i++){
			Kpspmxvo mxItem=kpspmxList.get(i);
			if (Double.valueOf(kpls.getJshj()) != 0) {
				Jyspmx jymx = new Jyspmx();
				jymx.setDjh(jyls1.getDjh());
				jymx.setSpmxxh(Integer.parseInt(mxItem.getSpmxxh()));
				jymx.setSpdm(mxItem.getSpdm());
				jymx.setSpmc(mxItem.getSpmc());
				jymx.setSpggxh(mxItem.getSpggxh());
				jymx.setSpdw(mxItem.getSpdw());
				if(czbz.equals("2")){//换开
					jymx.setSps(Double.valueOf(mxItem.getSps()));
					jymx.setSpje(Double.valueOf(df6.format(mxItem.getSpje())));
					jymx.setSpse(Double.valueOf(df.format(mxItem.getSpse())));
					jymx.setJshj(Double.valueOf(mxItem.getJshj()));

				}else if(czbz.equals("1")){//红冲
					jymx.setSps(-Double.valueOf(mxItem.getSps()));
					jymx.setSpje(-Double.valueOf(df6.format(mxItem.getSpje())));
					jymx.setSpse(-Double.valueOf(df.format(mxItem.getSpse())));
					jymx.setJshj(-Double.valueOf(mxItem.getJshj()));
				}
				jymx.setSpdj(mxItem.getSpdj() == null ? null : mxItem.getSpdj());
				jymx.setSpsl(mxItem.getSpsl());
				jymx.setYkphj(Double.valueOf(0));
				jymx.setGsdm(gsdm);
				jymx.setLrsj(TimeUtil.getNowDate());
				jymx.setLrry(yhid);
				jymx.setXgsj(TimeUtil.getNowDate());
				jymx.setXgry(yhid);
				jymx.setFphxz("0");
				jymxService.save(jymx);
				if(czbz.equals("1")){
				Kpspmx kpspmx = new Kpspmx();
				kpspmx.setKplsh(kpls2.getKplsh());
				kpspmx.setDjh(jymx.getDjh());
				kpspmx.setSpmxxh(jymx.getSpmxxh());
				kpspmx.setFphxz(jymx.getFphxz());
				kpspmx.setSpdm(jymx.getSpdm());
				kpspmx.setSpmc(jymx.getSpmc());
				kpspmx.setSpggxh(jymx.getSpggxh());
				kpspmx.setSpdw(jymx.getSpdw());
			    if (jymx.getSpdj() != null) {
	                kpspmx.setSpdj(jymx.getSpdj().doubleValue());
	            }
	            kpspmx.setSpdw(jymx.getSpdw());
	            if (jymx.getSps() != null) {
	                kpspmx.setSps(jymx.getSps().doubleValue());
	            }
				kpspmx.setSpje(jymx.getSpje().doubleValue());
				kpspmx.setSpsl(jymx.getSpsl().doubleValue());
				kpspmx.setSpse(jymx.getSpse().doubleValue());
				kpspmx.setLrsj(jymx.getLrsj());
				kpspmx.setLrry(jymx.getLrry());
				kpspmx.setXgsj(jymx.getXgsj());
				kpspmx.setXgry(jymx.getXgry());
				if(czbz.equals("2")){//换开
					kpspmx.setKhcje(jymx.getJshj().doubleValue());
					kpspmx.setYhcje(0d);
				}else if(czbz.equals("1")){//红冲
					kpspmx.setKhcje(0d);
					kpspmx.setYhcje(-jymx.getJshj().doubleValue());
				}
				
				kpspmxService.save(kpspmx);
				}
			}
			
			
		}
		Map map1=new HashMap();
		map1.put("kpls2", kpls2);
		map1.put("jyls1", jyls1);
		return map1;
   }
    
    /**
     * 保存开票流水处理
     * @param map
     * @param kplshList
     * @param yhid
     * @param gsdm
     * @param djh
     * @return
     * @throws Exception
     */
	public Map addfphkls(Map map, List<Integer> kplshList, int yhid, String gsdm,int djh) throws Exception{
		
		InvoiceResponse response=null;
		DecimalFormat df = new DecimalFormat("#.00");
		DecimalFormat df6 = new DecimalFormat("#.000000");
		Map result=new HashMap();
		for(Integer kplsh:kplshList){
			Map map1=this.saveJyls(kplsh,yhid,map,"2");
			Kpls kpls2=(Kpls)map1.get("kpls2");
			Jyls jyls1=(Jyls)map1.get("jyls1");
		   /*  response = skService.callService(kpls2.getKplsh());
			if ("0000".equals(response.getReturnCode())) {
				result.put("hkbz", "0");
				Map param2 = new HashMap<>();
				param2.put("kplsh", kplsh);
				param2.put("fpztdm", "03");
				kplsService.updateFpczlx(param2);
			}else{
				//jyls1.setClztdm("02");
				dc.saveLog(djh, "92", "1", "", "调用换开接口失败"+response.getReturnMessage(), 2, jyls1.getXfsh(), jyls1.getJylsh());
				result.put("hkbz", "1");
				result.put("hkMessage", response.getReturnMessage());
			}*/
			result.put("bcbz", "0");//保存标志
		}
         return result; 
	}
}
