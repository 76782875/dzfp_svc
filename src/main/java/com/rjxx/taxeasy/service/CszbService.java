package com.rjxx.taxeasy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.CszbJpaDao;
import com.rjxx.taxeasy.dao.CszbMapper;
import com.rjxx.taxeasy.domains.Csb;
import com.rjxx.taxeasy.domains.Cszb;
import com.rjxx.taxeasy.vo.CsbVo;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Nov 02 13:27:26 CST 2016
 *
 * @ZhangBing
 */ 
@Service
public class CszbService {

    @Autowired
    private CszbJpaDao cszbJpaDao;

    @Autowired
    private CszbMapper cszbMapper;
    @Autowired
    private CsbService csbService;
    public Cszb findOne(int id) {
        return cszbJpaDao.findOne(id);
    }

    public void save(Cszb cszb) {
        cszbJpaDao.save(cszb);
    }

    public void save(List<Cszb> cszbList) {
        cszbJpaDao.save(cszbList);
    }

    public Cszb findOneByParams(Cszb params) {
        return cszbMapper.findOneByParams(params);
    }

    public List<Cszb> findAllByParams(Map params) {
        return cszbMapper.findAllByParams(params);
    }

    public List<CsbVo> findByPage(Pagination pagination) {
        return cszbMapper.findByPage(pagination);
    }
    
    //去商品编码版本号
    public Cszb getSpbmbbh(String gsdm,Integer xfid,Integer kpdid,String csm){
    	Map params = new HashMap<>();
		params.put("gsdm", gsdm);
		/*params.put("xfid", xfid);
		params.put("kpdid", kpdid);*/
		params.put("csm", csm);
		List<Cszb> list = new ArrayList<>();
		list =  cszbMapper.findAllByParams(params);
		if (list.size() == 1) {
			return list.get(0);
		}
		if (list.size() > 0) {
			for (Cszb cszb : list) {
				if (null != kpdid && kpdid.equals(cszb.getKpdid())) {
					return cszb;
				}
			}
			for (Cszb cszb : list) {
				if (null != xfid && xfid.equals(cszb.getXfid()) && null == cszb.getKpdid()) {
					return cszb;
				}
			}
			for (Cszb cszb : list) {
				if (null != gsdm && gsdm.equals(cszb.getGsdm()) && null == cszb.getKpdid() && null == cszb.getXfid()) {
					return cszb;
				}
			}
		}
		Csb csb = csbService.findOneByParams(params);
		Cszb cszb = new Cszb();
		cszb.setCsz(csb.getMrz());
		return cszb;
    }

	public Cszb findsfzlkpByParams(Cszb cszb) {
		// TODO Auto-generated method stub
		return cszbMapper.findsfzlkpByParams(cszb);
	}

}

