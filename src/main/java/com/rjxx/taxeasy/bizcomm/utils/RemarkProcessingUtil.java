package com.rjxx.taxeasy.bizcomm.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	/**
	 * 备注处理方式，按着t_zffs中维护的bzclfs_dm模板处理备注。
	 */
	public List<Jyxxsq> dealRemark(List<Jyxxsq> jyxxsqList, List<Jyzfmx> jyzfmxList, String gsdm) {

		if (null != jyxxsqList && !jyxxsqList.isEmpty() && null != jyzfmxList && !jyzfmxList.isEmpty()) {
			String bz = "";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("gsdm", gsdm);
			List<ZffsVo> zffsVoList = zffsService.findAllByMap(params);
			for (int i = 0; i < jyxxsqList.size(); i++) {
				Jyxxsq jyxxsq = jyxxsqList.get(i);
				for (int j = 0; j < jyzfmxList.size(); j++) {
					Jyzfmx jyzfmx = jyzfmxList.get(j);
					if (jyxxsq.getDdh().equals(jyzfmx.getDdh())) {
						for (int t = 0; t < zffsVoList.size(); t++) {
							ZffsVo zffsVo = zffsVoList.get(t);
							ZffsVo zffsVo2 = new ZffsVo();//避免传入有相同的zffsdm
							if (zffsVo.getZffsDm().equals(jyzfmx.getZffsDm())) {
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
	 * @param Object
	 * @param String
	 * @return String
	 */
	public String getAndSetField(Object obj,/*Object obj2,*/ String params) {
		Field fields[] = obj.getClass().getDeclaredFields();// 获得对象所有属性
		Field field = null;
		String[] attr = params.split("\\$");
		for (int i = 0; i < fields.length; i++) {
			field = fields[i];
			field.setAccessible(true);// 修改访问权限
			for (int j = 0; j < attr.length; j++) {
				if (attr[j].equals(field.getName())) {
					try {
						// System.out.println(field.getName() + ":" +
						// field.get(obj));
						attr[j] = String.valueOf(field.get(obj));
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
    	Jyzfmx m = new Jyzfmx();
    	m.setZfje(1000.00);
    	/*Field fields[]=tt.getClass().getDeclaredFields();//获得对象所有属性
    	System.out.println(fields[0].getName());*/
      RemarkProcessingUtil util = new RemarkProcessingUtil();
      String t = util.getAndSetField(tt, "zffsMc$支付$zfje");
      System.out.println(t);
    }
}
