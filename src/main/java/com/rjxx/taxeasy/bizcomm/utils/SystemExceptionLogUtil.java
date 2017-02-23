package com.rjxx.taxeasy.bizcomm.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.taxeasy.domains.XtYclog;
import com.rjxx.taxeasy.service.XtYclogService;

@Service
public class SystemExceptionLogUtil {

	@Autowired
	private XtYclogService xtyclogservice;

	/**
	 * 保存异常信息
	 * 
	 */
	public void dealExceptionLog(String ycdj, Exception ex, String ycff, String sjh, String yx) {
		Map resultMap = new HashMap();
		resultMap = getMethod(ex);
		Map params = new HashMap();
		params.put("ycxx", resultMap.get("ycxx").toString());
		params.put("yclm", resultMap.get("yclm").toString());
		params.put("ycff", ycff);
		XtYclog xtyclog2 = xtyclogservice.findOneByParams(params);
		if (null == xtyclog2 || xtyclog2.equals("")) {
			XtYclog xtyclog = new XtYclog();
			xtyclog.setYcdj(ycdj);
			xtyclog.setYclm(resultMap.get("yclm").toString());
			xtyclog.setYcxx(resultMap.get("ycxx").toString());
			xtyclog.setYcff(ycff);
			xtyclog.setYcjsrsjh(sjh);
			xtyclog.setYcjsryx(yx);
			xtyclog.setFsbz("0");
			xtyclog.setLrsj(new Date());
			xtyclogservice.save(xtyclog);
		}
	}

	/**
	 * 获取异常详细信息和异常类名
	 */
	private Map getMethod(Exception ex) {
		Map result = new HashMap();
		String ycxx = ex.getClass().getName().toString();
		String yclm = ex.getClass().getSimpleName().toString();
		result.put("ycxx", ycxx);
		result.put("yclm", yclm);
		return result;
	}

	public static void main(String args[]) {
		SystemExceptionLogUtil t = new SystemExceptionLogUtil();
	}
}
