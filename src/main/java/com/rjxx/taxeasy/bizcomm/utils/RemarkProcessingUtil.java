package com.rjxx.taxeasy.bizcomm.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

import com.rjxx.taxeasy.domains.Cszb;
import com.rjxx.taxeasy.service.CszbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.taxeasy.domains.Jyxxsq;
import com.rjxx.taxeasy.domains.Jyzfmx;
import com.rjxx.taxeasy.service.ZffsService;
import com.rjxx.taxeasy.vo.ZffsVo;

@Service
public class RemarkProcessingUtil {
    
	@Autowired
	private ZffsService zffsService;
	@Autowired
	private CszbService cszbService;

	/**
	 * 根据参数设置处理备注
	 */
	public List<Jyxxsq> dealRemark(List<Jyxxsq> jyxxsqList, String gsdm) throws Exception{

		if (null != jyxxsqList && !jyxxsqList.isEmpty()) {

			Map<String, Object> params = new HashMap<String, Object>();
			for (int i = 0; i < jyxxsqList.size(); i++) {
				String bz = "";
				Jyxxsq jyxxsq = jyxxsqList.get(i);
				Cszb cszb = cszbService.getSpbmbbh(gsdm,jyxxsq.getXfid(),jyxxsq.getSkpid(),"bzmb");
				if(null != cszb && null !=cszb.getCsz() && !cszb.getCsz().equals("")){
					if(cszb.getCsz().startsWith("A")){
						bz = getAndSetField(jyxxsq, cszb.getCsz()).substring(1)+";";
						bz = bz +" "+ (null ==jyxxsq.getBz()?"":jyxxsq.getBz());
					}else if(cszb.getCsz().startsWith("B")){
						bz = getAndSetField(jyxxsq, cszb.getCsz()).substring(1)+";";
						bz = (null ==jyxxsq.getBz()?"":jyxxsq.getBz())+" "+bz;
					}else{
						bz = getAndSetField(jyxxsq, cszb.getCsz())+";";
						bz = (null ==jyxxsq.getBz()?"":jyxxsq.getBz())+" "+bz;
					}

				}else{
					bz = (null ==jyxxsq.getBz()?"":jyxxsq.getBz());
				}
				jyxxsq.setBz(bz);
			}
		}

		return jyxxsqList;
	}


	/**
	 * 备注处理方式，按着t_zffs中维护的bzclfs_dm模板处理备注。
	 */
	public List<Jyxxsq> dealZfRemark(List<Jyxxsq> jyxxsqList, List<Jyzfmx> jyzfmxList, String gsdm) throws Exception{

		if (null != jyxxsqList && !jyxxsqList.isEmpty() && null != jyzfmxList && !jyzfmxList.isEmpty()) {
			String bz = "";
			Map<String, Object> params = new HashMap<String, Object>();
			List kpfsdmList = new ArrayList();
			kpfsdmList.add("01");
			kpfsdmList.add("02");
			params.put("gsdm", gsdm);
			params.put("kpfsList",kpfsdmList);
			List<ZffsVo> zffsVoList = zffsService.findAllByMap(params);
			for (int i = 0; i < jyxxsqList.size(); i++) {
				Jyxxsq jyxxsq = jyxxsqList.get(i);
				for (int j = 0; j < jyzfmxList.size(); j++) {
					Jyzfmx jyzfmx = jyzfmxList.get(j);
					if (jyxxsq.getDdh().equals(jyzfmx.getDdh())) {
						for (int t = 0; t < zffsVoList.size(); t++) {
							ZffsVo zffsVo = zffsVoList.get(t);
							ZffsVo zffsVo2 = new ZffsVo();//避免传入有相同的zffsdm
							if (null !=zffsVo.getBzclfsDm() && !zffsVo.getBzclfsDm().equals("") && zffsVo.getZffsDm().equals(jyzfmx.getZffsDm())) {
								zffsVo2.setZffsDm(zffsVo.getZffsDm());
								zffsVo2.setZfje(String.valueOf(jyzfmx.getZfje()));
								zffsVo2.setZffsMc(zffsVo.getZffsMc());
								zffsVo2.setBzclfsDm(zffsVo.getBzclfsDm());
								bz = bz + getAndSetField(zffsVo2, zffsVo2.getBzclfsDm())+";";

							}
						}
					}
				}
				bz = (null ==jyxxsq.getBz()?"":jyxxsq.getBz())+" "+bz;
				jyxxsq.setBz(bz);
			}
		}

		return jyxxsqList;
	}

	/**
	 * java反射机制，用对象中的属性值替换字符串中对应的参数，实现赋值操作
	 * 注：需要替换的参数字符串必须以$为分隔符
	 * @param obj
	 * @param params
	 * @return String
	 */
	public String getAndSetField(Object obj,/*Object obj2,*/ String params) throws Exception{
		Field fields[] = obj.getClass().getDeclaredFields();// 获得对象所有属性
		Field field = null;
		String[] attr = params.split("\\$");
		for (int i = 0; i < fields.length; i++) {
			field = fields[i];
			field.setAccessible(true);// 修改访问权限
			for (int j = 0; j < attr.length; j++) {
				if (attr[j].equals(field.getName())) {
					try {
						if (field.getGenericType().toString().equals(
								"class java.util.Date")) {
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String dateString = formatter.format(field.get(obj));
							attr[j] = String.valueOf(dateString);
						}else{
							attr[j] = String.valueOf(field.get(obj));
						}

					} catch (Exception e) {
						e.printStackTrace();
						return "";
					}
				}
			}
		}
		/*if(null !=obj2){
			Field fields2[] = obj2.getClass().getDeclaredFields();// 获得对象所有属性
			Field field2 = null;
			for (int i = 0; i < fields2.length; i++) {
				field2 = fields2[i];
				field2.setAccessible(true);// 修改访问权限
				for (int j = 0; j < attr.length; j++) {
					if (attr[j].equals(field2.getName())) {
						try {
							attr[j] = String.valueOf(field2.get(obj2));
						} catch (Exception e) {
							e.printStackTrace();
							return "";
						}
					}
				}
			}
		}*/
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < attr.length; i++) {
			sb.append(attr[i]);
		}
		return sb.toString();
	}
	
    public static void main(String[] args) {
    	ZffsVo tt = new ZffsVo();
    	tt.setZffsDm("01");
    	tt.setZffsMc("现金");
    	tt.setZfje("1000");
    	tt.setLrsj(new Date());
    	Jyzfmx m = new Jyzfmx();
    	m.setZfje(1000.00);
    	/*Field fields[]=tt.getClass().getDeclaredFields();//获得对象所有属性
    	System.out.println(fields[0].getName());*/
        RemarkProcessingUtil util = new RemarkProcessingUtil();
		String t="";
      try {
		  t= util.getAndSetField(tt, "zffsMc$支付$lrsj");
	  }catch (Exception e){
      	e.printStackTrace();
	  }

      System.out.println(t);
    }
}
