package com.rjxx.taxeasy.service;

import com.rjxx.taxeasy.dao.CszbJpaDao;
import com.rjxx.taxeasy.dao.XfJpaDao;
import com.rjxx.taxeasy.domains.Csb;
import com.rjxx.taxeasy.domains.Cszb;
import com.rjxx.taxeasy.domains.Xf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private CszbJpaDao cszbJpaDao;
    @Autowired
    private CsbService csbService;
    @Autowired
    private XfJpaDao xfJpaDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取有这个参数而且参数值等于value的销方
     */
    public List<Xf> getXfsByCsm(String csm, String value) {
        if (value == null) {
            logger.error("value不能为空");
            return null;
        }
        Map map = new HashMap<>();
        map.put("csm", csm);
        Csb csb = csbService.findOneByParams(map);
        List<Cszb> cszbs = cszbJpaDao.findByCsid(csb.getId());
        List<Xf> xfs = new ArrayList<>();
        for (Cszb cszb : cszbs) {
            if (value.equals(cszb.getCsz())) {
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
