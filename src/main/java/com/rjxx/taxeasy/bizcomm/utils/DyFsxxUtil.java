package com.rjxx.taxeasy.bizcomm.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rjxx.taxeasy.domains.DyWddy;
import com.rjxx.taxeasy.service.DyWddyService;
/**
 * 发送订阅信息公共方法
 * */
@Service
public class DyFsxxUtil {
	@Autowired
	private DyWddyService wddyService;
	
	/**
	 * 获取发送方式
	 * @param dyzldm,订阅种类代码
	 * */
	public Map<Integer,List<String>> getFsfs(String dyzldm){
		//根据dyzldm查询yhid及订阅方式
		Map params = new HashMap<>();
		params.put("dyzldm", dyzldm);
		List<DyWddy> dyyhList =  wddyService.findDyzlYh(params);
		Map<Integer,List<String>> map = new HashMap<>();
		for(DyWddy item:dyyhList){			
			Integer yhid = item.getYhid();
			params.put("yhid", yhid);
			List<DyWddy> dyfsList = wddyService.findAllByParams(params);
			List<String> list = new ArrayList<String>();
			for(DyWddy dyfs :dyfsList){
				String fs = dyfs.getDyfsdm();
				list.add(fs);
			}
			map.put(yhid, list);
		}
		return map;
	}	

}
