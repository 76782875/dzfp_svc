package com.rjxx.taxeasy.bizcomm.utils;

import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.service.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DealQueueUtil {

	@Autowired
	private  GetDataService getDataService;

	@Autowired
	private DlclbService dlclbService;

	@Autowired
	private TqmtqService tqmtqService;

	@Autowired
	private GsxxService gsxxService;

	@Autowired
	private JyxxsqService jyxxsqService;

	@Autowired
	private FpclService fpclService;

	@Autowired
	private  CszbService cszbService;

	@Autowired
	private  JylsService jylsService;

	@Autowired
	private  KplsService kplsService;

	@Autowired
	private SkService skService;
	/**
	 * 处理对应队列中不同业务类型数据的后台方法。
	 * 返回8888重新放入队列，其他不做处理
	 */
	public void dealQueueData(String key,int clcs,String dlmc,String gsdm){
		Map params = new HashMap();
		params.put("dlmc",dlmc);
		Dlclb dlclb = dlclbService.findOneByParams(params);
		String tmpResult = "";
		if(null != dlclb && !dlclb.equals("")){
			String clff = dlclb.getClff();
			tmpResult = execute(clff,key,gsdm);
		}
		//return tmpResult;
		if(tmpResult.equals("8888")){
			dlclbService.sendQueueMessage(dlclb.getDlmc(), key, clcs/*, ywkey*/);
		}
	}


	/**
	 *  根据某个对象的名称和方法去执行该方法
	 *  执行方法正确返回0000，出错则返回9999
	 *
	 */
	public  String execute(String methodName,String key) {
		return execute(methodName,key,"");
	}




	/**
     *  根据某个对象的名称和方法去执行该方法
     *  执行方法正确返回0000，出错则返回9999
     *
     */
	public String execute(String methodName,String key,String gsdm){
		String result = "9999";
		Method target = null;
		try {
			target = this.getClass().getMethod(methodName,new Class[]{String.class,String.class});
			result = (String)target.invoke(this,new Object[]{key,gsdm});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public String queueYcck(String key,String gsdm){
		int kplsh = Integer.valueOf(key);
		String result = "9999";
		Map params = new HashMap();
		params.put("kplsh", kplsh);
		Kpls kpls = kplsService.findOneByParams(params);
		try {
			skService.SkServerKP(kplsh);
			result ="0000";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 *	后台处理未开票数据匹配方法，
	 *  全家使用特有的方法，其他的统一处理
	 *
	 * */
	public String queueWsc(String key,String gsdm){
		String result = "9999";

		Tqmtq tqmtq = tqmtqService.findOne(Integer.valueOf(key));
		if(gsdm.equals("family") || gsdm.equals("Family")){

			Map resultMap = getDataService.getData(tqmtq.getDdh(), gsdm);
			if (null != resultMap && !resultMap.isEmpty()) {
				List<Jyxxsq> jyxxsqList = (List) resultMap.get("jyxxsqList");
				List<Jymxsq> jymxsqList = (List) resultMap.get("jymxsqList");
				List<Jyzfmx> jyzfmxList = (List) resultMap.get("jyzfmxList");
				Jyxxsq jyxxsq = jyxxsqList.get(0);

				if (null == tqmtq || tqmtq.equals("")) {
					//没有抽到数据重新放入队列
					result = "8888";
				} else {
					Map params = new HashMap();
					params.put("tqm", tqmtq.getDdh());
					params.put("gsdm", tqmtq.getGsdm());
					Jyls jyls = jylsService.findOne(params);
					//交易流水为空时，在进行处理，不为空直接消耗
						if(null == jyls || jyls.equals("")){
							String gfmc = String.valueOf(tqmtq.getGfmc());
							String gfsh = String.valueOf(tqmtq.getNsrsbh());
							String dz = String.valueOf(tqmtq.getDz());
							String dh = String.valueOf(tqmtq.getDh());
							String khh = String.valueOf(tqmtq.getKhh());
							String yhzh = String.valueOf(tqmtq.getKhhzh());
							String yx = String.valueOf(tqmtq.getGfemail());
							//String tqm = String.valueOf(tqmtq.getDdh());
							jyxxsq.setGfemail(gfmc);
							jyxxsq.setGfsh(gfsh);
							jyxxsq.setGfdz(dz);
							jyxxsq.setGfdh(dh);
							jyxxsq.setGfyh(khh);
							jyxxsq.setGfyhzh(yhzh);
							jyxxsq.setGfemail(yx);

							String xml = GetXmlUtil.getFpkjXml(jyxxsq, jymxsqList, jyzfmxList);
							Map parms = new HashMap();
							parms.put("gsdm", gsdm);
							Gsxx gsxx = gsxxService.findOneByParams(parms);
							String resultxml = HttpUtils.HttpUrlPost(xml, gsxx.getAppKey(), gsxx.getSecretKey());
							Document document = null;
							try {
								document = DocumentHelper.parseText(resultxml);
							} catch (DocumentException e) {
								e.printStackTrace();
							}

							Element root = document.getRootElement();
							List<Element> childElements = root.elements();
							Map xmlMap = new HashMap();
							for (Element child : childElements) {
								xmlMap.put(child.getName(), child.getText());
							}
							String returncode = (String) xmlMap.get("ReturnCode");
							if (returncode.equals("9999")) {
								return "9999";
							} else {
								//logger.info("-------返回值---------" + resultxml);
								return "0000";
							}
					}else{
							result = "0000";
						}
				}

			} else {
				//没有抽到数据重新放入队列
				result = "8888";
			}
		}else{
			if(null != tqmtq && !tqmtq.equals("")){
				Map params = new HashMap();
				params.put("tqm", tqmtq.getDdh());
				params.put("gsdm", tqmtq.getGsdm());
				params.put("je", tqmtq.getZje());
				Jyxxsq jyxxsq = jyxxsqService.findOneByParams(params);
				if(null != jyxxsq && !jyxxsq.equals("")){
					Map params2 = new HashMap();
					params.put("sqlsh", jyxxsq.getSqlsh());
					params.put("gsdm", jyxxsq.getGsdm());
					params.put("tqm", jyxxsq.getTqm());
					Jyls jyls = jylsService.findOne(params);
					if(null == jyls && jyls.equals("")){
						List<Jyxxsq> jyxxsqList = new ArrayList<Jyxxsq>();
						String gfmc = String.valueOf(tqmtq.getGfmc());
						String gfsh = String.valueOf(tqmtq.getNsrsbh());
						String dz = String.valueOf(tqmtq.getDz());
						String dh = String.valueOf(tqmtq.getDh());
						String khh = String.valueOf(tqmtq.getKhh());
						String yhzh = String.valueOf(tqmtq.getKhhzh());
						String yx = String.valueOf(tqmtq.getGfemail());
						//String tqm = String.valueOf(tqmtq.getDdh());
						jyxxsq.setGfemail(gfmc);
						jyxxsq.setGfsh(gfsh);
						jyxxsq.setGfdz(dz);
						jyxxsq.setGfdh(dh);
						jyxxsq.setGfyh(khh);
						jyxxsq.setGfyhzh(yhzh);
						jyxxsq.setGfemail(yx);
						jyxxsqList.add(jyxxsq);
						try {
							Cszb cszb2 = cszbService.getSpbmbbh(jyxxsq.getGsdm(), Integer.valueOf(jyxxsq.getXfid()), Integer.valueOf(jyxxsq.getSkpid()), "kpfs");
							fpclService.zjkp(jyxxsqList,cszb2.getCsz());
							result ="0000";
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else{
						result ="0000";
					}

				}else{
					//没有抽到数据重新放入队列
					result = "8888";
				}
			}else{
				//没有抽到数据重新放入队列
				result = "8888";
			}

		}

		return result;
	}



    public static void main(String[] args) {

		//DealQueueUtil pam = new DealQueueUtil();
		//System.out.println(pam.execute("ttt","12","ldyx"));
		String tmp= "09D103,00F103,Read";
		String tmp1 ="09D103:发票领购信息已用完";
		System.out.println();

    }
}
