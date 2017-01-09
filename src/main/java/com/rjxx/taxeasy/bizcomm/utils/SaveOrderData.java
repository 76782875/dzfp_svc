package com.rjxx.taxeasy.bizcomm.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rjxx.taxeasy.domains.Jymxsq;
import com.rjxx.taxeasy.domains.Jyxxsq;
import com.rjxx.taxeasy.service.JymxsqService;
import com.rjxx.taxeasy.service.JyxxsqService;

@Service
public class SaveOrderData {

	@Autowired
	private JyxxsqService jyxxsqService;
	@Autowired
	private JymxsqService jymxsqService;

	/**
	 * 校验并保存交易申请数据
	 *
	 * @author kk
	 */
	public String saveAllData(List<Jyxxsq> jyxxsqList, List<Jymxsq> jymxsqList) {
		String result = "";
		Jyxxsq jyxxsq = new Jyxxsq();
		Jymxsq jymxsq = new Jymxsq();
		String gsdm = jyxxsqList.get(0).getGsdm();
		Jyxxsq yjyxxsq = new Jyxxsq();
		try {
			for (int i = 0; i < jyxxsqList.size(); i++) {
				jyxxsq = jyxxsqList.get(i);
				Map params = new HashMap();
				params.put("ddh", jyxxsq.getDdh());
				params.put("gsdm", gsdm);
				yjyxxsq = jyxxsqService.findOneByParams(params);
				if (null != yjyxxsq && !yjyxxsq.equals("")) {
					if (yjyxxsq.getDdh().equals(jyxxsq.getDdh())) {
						yjyxxsq.setSfcp(jyxxsq.getSfcp());
						yjyxxsq.setSfdyqd(jyxxsq.getSfdyqd());
						yjyxxsq.setZsfs(jyxxsq.getZsfs());
						jyxxsqService.save(yjyxxsq);
					}
				} else {
					jyxxsqService.save(jyxxsq);
				}
				// 保存jyxxsq数据

				for (int j = 0; j < jymxsqList.size(); j++) {
					jymxsq = jymxsqList.get(j);
					if (null != yjyxxsq && !yjyxxsq.equals("")) {
						if (jyxxsq.getDdh().equals(yjyxxsq.getDdh())) {
							jymxsq.setSqlsh(yjyxxsq.getSqlsh());
							// 保存明细数据
							jymxsqService.save(jymxsq);
						}
					} else {
						if (jymxsq.getDdh().equals(jyxxsq.getDdh())) {
						jymxsq.setSqlsh(jyxxsq.getSqlsh());
						// 保存明细数据
						jymxsqService.save(jymxsq);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "保存信息失败!";
			return result;
		}
		return result;
	}

	public String saveBuyerData(List<Jyxxsq> jyxxsqList) {
		String result = "";
		String gsdm = jyxxsqList.get(0).getGsdm();
		try {
			Jyxxsq jyxxsq = new Jyxxsq();
			Jyxxsq yjyxxsq = new Jyxxsq();
			for (int i = 0; i < jyxxsqList.size(); i++) {
				jyxxsq = jyxxsqList.get(i);
				Map params = new HashMap();
				params.put("ddh", jyxxsq.getDdh());
				params.put("gsdm", gsdm);
				yjyxxsq = jyxxsqService.findOneByParams(params);
				// 如果明细数据已上传，则更新以前的jyxxsq表中的数据
				if (null != yjyxxsq && !yjyxxsq.equals("")) {
					yjyxxsq.setGfsh(jyxxsq.getGfsh());
					yjyxxsq.setGfmc(jyxxsq.getGfmc());
					yjyxxsq.setGfyh(jyxxsq.getGfyh());
					yjyxxsq.setGfyhzh(jyxxsq.getGfyhzh());
					yjyxxsq.setGfdz(jyxxsq.getGfdz());
					yjyxxsq.setGfdh(jyxxsq.getGfdh());
					yjyxxsq.setGfemail(jyxxsq.getGfemail());
					yjyxxsq.setSffsyj(jyxxsq.getSffsyj());
					yjyxxsq.setGflxr(jyxxsq.getGflxr());
					yjyxxsq.setTqm(jyxxsq.getTqm());
					yjyxxsq.setGfsjrdz(jyxxsq.getGfsjrdz());
					yjyxxsq.setGfyb(jyxxsq.getGfyb());
					// yjyxxsq.setDdh(jyxxsq.getDdh());
					jyxxsqService.save(yjyxxsq);
				} else {
					// 如果没有上传，这只保存jyxxsq数据表
					jyxxsqService.save(jyxxsq);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = "保存购方数据失败!";
			return result;
		}
		return result;
	}
}
