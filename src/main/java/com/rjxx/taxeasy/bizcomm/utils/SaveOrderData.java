package com.rjxx.taxeasy.bizcomm.utils;

import com.rjxx.taxeasy.domains.Jymxsq;
import com.rjxx.taxeasy.domains.JymxsqCl;
import com.rjxx.taxeasy.domains.Jyxxsq;
import com.rjxx.taxeasy.domains.Jyzfmx;
import com.rjxx.taxeasy.service.JymxsqClService;
import com.rjxx.taxeasy.service.JymxsqService;
import com.rjxx.taxeasy.service.JyxxsqService;
import com.rjxx.taxeasy.service.JyzfmxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaveOrderData {

    @Autowired
    private JyxxsqService jyxxsqService;
    @Autowired
    private JymxsqService jymxsqService;

    @Autowired
    private JyzfmxService jyzfmxService;
    
    @Autowired
    private JymxsqClService jymxsqClService;
    /**
     * 校验并保存交易申请数据
     *
     * @author kk
     */
    @Transactional
    public String saveAllData(List<Jyxxsq> jyxxsqList, List<Jymxsq> jymxsqList,List<Jyzfmx> jyzfmxList,List<JymxsqCl> jymxsqClList) {
        String result = "";
        Jyxxsq jyxxsq = null;
        Jymxsq jymxsq = null;
        Jyzfmx jyzfmx = null;
        JymxsqCl jymxsqCl = null;
        String gsdm = jyxxsqList.get(0).getGsdm();
//        Jyxxsq yjyxxsq = new Jyxxsq();
        //try {
        for (int i = 0; i < jyxxsqList.size(); i++) {
            jyxxsq = jyxxsqList.get(i);
            //TODO 购方数据与交易数据分开上传????
//            Map params = new HashMap();
//            params.put("ddh", jyxxsq.getDdh());
//            params.put("gsdm", gsdm);
//            yjyxxsq = jyxxsqService.findOneByParams(params);
//            if (null != yjyxxsq && StringUtils.isNotBlank(yjyxxsq.getGfmc())) {
//                yjyxxsq.setSfcp(jyxxsq.getSfcp());
//                yjyxxsq.setSfdyqd(jyxxsq.getSfdyqd());
//                yjyxxsq.setZsfs(jyxxsq.getZsfs());
//                jyxxsqService.save(yjyxxsq);
//            } else {
            if (jyxxsq.getFpzldm().equals("0")) {
                jyxxsq.setFpzldm("01");
            } else if (jyxxsq.getFpzldm().equals("1")) {
                jyxxsq.setFpzldm("02");
            }
            jyxxsqService.saveJyxxsq(jyxxsq);
//            }
            // 保存jyxxsq数据
            for (int j = 0; j < jymxsqList.size(); j++) {
                jymxsq = jymxsqList.get(j);
                if (jyxxsq.getDdh().equals(jymxsq.getDdh())) {
                    jymxsq.setSqlsh(jyxxsq.getSqlsh());
                    // 保存明细数据
                    jymxsqService.save(jymxsq);
                }
                
            }
            for(int m=0;m<jymxsqClList.size();m++){
            	jymxsqCl = jymxsqClList.get(m);
            	if (jyxxsq.getDdh().equals(jymxsqCl.getDdh())) {
            		jymxsqCl.setSqlsh(jyxxsq.getSqlsh());
                    // 保存明细数据
            		jymxsqClService.save(jymxsqCl);
                }
            }
			if (null != jyzfmxList && jyzfmxList.size() > 0) {
				for (int k = 0; k < jyzfmxList.size(); k++) {
					jyzfmx = jyzfmxList.get(k);
					if (jyxxsq.getDdh().equals(jyzfmx.getDdh())) {
						jyzfmx.setSqlsh(jyzfmx.getSqlsh());
						// 保存明细数据
						jyzfmxService.save(jyzfmx);
					}
				}
			}
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
