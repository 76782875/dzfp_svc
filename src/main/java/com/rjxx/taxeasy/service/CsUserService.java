package com.rjxx.taxeasy.service;

import com.rjxx.taxeasy.dao.CszbMapper;
import com.rjxx.taxeasy.dao.XfJpaDao;
import com.rjxx.taxeasy.domains.Csb;
import com.rjxx.taxeasy.domains.Cszb;
import com.rjxx.taxeasy.domains.Xf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyahui on 2017/11/23 0023.
 */
@Service
public class CsUserService {

    @Autowired
    private CszbMapper cszbMapper;
    @Autowired
    private CsbService csbService;
    @Autowired
    private XfJpaDao xfJpaDao;

    /**
     *  获取有这个参数的销方,或者获取有这个参数而且参数值等于value的销方
     *  如果获取所有有这个参数的销方，则设置value为空
     *  如果获取参数值等于某个值得销方，则传入value
     */
    public List<Xf> getXfsByCsm(String csm,String value){
        Map map = new HashMap<>();
        map.put("csm", csm);
        Csb csb = csbService.findOneByParams(map);
        Map hashMap = new HashMap();
        hashMap.put("csid", csb.getId());
        List<Cszb> cszbs = cszbMapper.findAllByParams(hashMap);
        List<Xf> xfs = new ArrayList<>();
        for(Cszb cszb:cszbs) {
            if (value != null) {
                if (value.equals(cszb.getCsz())) {
                    Integer xfid = cszb.getXfid();
                    if (xfid == null) {
                        String gsdm = cszb.getGsdm();
                        xfs.addAll(xfJpaDao.findAllByGsdm(gsdm));
                    } else {
                        xfs.add(xfJpaDao.findOneById(xfid));
                    }
                }
            }else{
                Integer xfid = cszb.getXfid();
                if (xfid == null) {
                    String gsdm = cszb.getGsdm();
                    xfs.addAll(xfJpaDao.findAllByGsdm(gsdm));
                } else {
                    xfs.add(xfJpaDao.findOneById(xfid));
                }
            }
        }
        return xfs;
    }
}
