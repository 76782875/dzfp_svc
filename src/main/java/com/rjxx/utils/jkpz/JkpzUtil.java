package com.rjxx.utils.jkpz;

import com.rjxx.taxeasy.bizcomm.utils.GetLsvBz;
import com.rjxx.taxeasy.bizcomm.utils.GetXfxx;
import com.rjxx.taxeasy.bizcomm.utils.RemarkProcessingUtil;
import com.rjxx.taxeasy.dao.SkpJpaDao;
import com.rjxx.taxeasy.dao.XfJpaDao;
import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.service.CszbService;
import com.rjxx.taxeasy.service.SpvoService;
import com.rjxx.taxeasy.vo.JkpzVo;
import com.rjxx.taxeasy.vo.Spvo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author: zsq
 * @date: 2018/3/20 17:08
 * @describe: 接口配置类
 */
@Component
public class JkpzUtil {

    @Autowired
    private XfJpaDao xfJpaDao;
    @Autowired
    private SkpJpaDao skpJpaDao;
    @Autowired
    private CszbService cszbService;
    @Autowired
    private SpvoService spvoService;
    @Autowired
    private GetLsvBz getLsvBz;
    @Autowired
    private RemarkProcessingUtil remarkProcessingUtil;

    private static Logger logger = LoggerFactory.getLogger(JkpzUtil.class);

    public char getRandomLetter() {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        return chars.charAt(new Random().nextInt(26));
    }

    public String setDefSeriaNum(Map map) {
        Jyxxsq jyxxsq = (Jyxxsq) map.get("jyxxsq");
        jyxxsq.setJylsh("JY" + System.currentTimeMillis() + getRandomLetter());
        return null;
    }

    public String setDefOrderNo(Map map) {
        Jyxxsq jyxxsq = (Jyxxsq) map.get("jyxxsq");
        jyxxsq.setDdh("DD" + System.currentTimeMillis() + getRandomLetter());
        return null;
    }

    public String setDefOrderDate(Map map) {
        Jyxxsq jyxxsq = (Jyxxsq) map.get("jyxxsq");
        jyxxsq.setDdrq(new Date());
        return null;
    }

    public String getXfBySh(Map map) {
        Xf xf = (Xf) map.get("xf");
        Skp skp = (Skp) map.get("skp");
        Map xfxx = GetXfxx.getXfxx(xf, skp);
        Jyxxsq jyxxsq = (Jyxxsq) map.get("jyxxsq");
        jyxxsq.setXfmc(xf.getXfmc());
        jyxxsq.setXfsh(xf.getXfsh());
        jyxxsq.setXfdz((String) xfxx.get("xfdz"));
        jyxxsq.setXfdh((String) xfxx.get("xfdh"));
        jyxxsq.setXfyh((String) xfxx.get("xfyh"));
        jyxxsq.setXfyhzh((String) xfxx.get("xfyhzh"));
        return null;
    }

    public String defaultKpd(Map map) {
        Skp skp = (Skp) map.get("skp");
        Jyxxsq jyxxsq = (Jyxxsq) map.get("jyxxsq");
        jyxxsq.setKpddm(skp.getKpddm());
        return null;
    }


    public String getCszb(Map map) {
        Skp skp = (Skp) map.get("skp");
        Xf xf = (Xf) map.get("xf");
        Gsxx gsxx = (Gsxx) map.get("gsxx");
        JkpzVo jkpzVo= (JkpzVo) map.get("jkpzVo");
        Jyxxsq jyxxsq = (Jyxxsq) map.get("jyxxsq");
        String csm=jkpzVo.getCsm();
        Cszb cszb = cszbService.getSpbmbbh(gsxx.getGsdm(), xf.getId(), skp.getId(), csm);
        String value=cszb.getCsz();
        switch (csm){
            case "mrfpzldm":
                jyxxsq.setFpzldm(value);
                break;
            case "sfdyqd":
                jyxxsq.setSfdyqd(value);
                break;
            case "sfcp":
                jyxxsq.setSfcp(value);
                break;
            case "sfdy":
                jyxxsq.setSfdy(value);
                break;
            case "mrzsfs":
                jyxxsq.setZsfs(value);
                break;
            case "hsbz":
                jyxxsq.setHsbz(value);
                break;
            case "bzmb":
                remarkProcessingUtil.dealRemark(jyxxsq,value);
                break;
            case "spbmbbh":
                break;
            default:
                return "未知的参数";
        }
        return null;
    }

    public String getPerson(Map map) {
        Xf xf = (Xf) map.get("xf");
        Skp skp = (Skp) map.get("skp");
        Map xfxx = GetXfxx.getXfxx(xf, skp);
        Jyxxsq jyxxsq = (Jyxxsq) map.get("jyxxsq");
        jyxxsq.setSkr((String) xfxx.get("skr"));
        jyxxsq.setFhr((String) xfxx.get("fhr"));
        jyxxsq.setKpr((String) xfxx.get("kpr"));
        return null;
    }


    public Spvo defaultSp(String gsdm, Integer xfid, Integer skpid, String csm) {
        Spvo spvo = new Spvo();
        try {
            Cszb cszb = cszbService.getSpbmbbh(gsdm, xfid, skpid, csm);
            if (cszb != null) {
                String csz = cszb.getCsz();
                Map param = new HashMap();
                param.put("gsdm", gsdm);
                param.put("spdm", cszb);
                spvo = spvoService.findOneSpvo(param);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return spvo;
        }
        return spvo;
    }


    public Spvo getSpByid(String gsdm, Integer xfid, Integer skpid, String spdm) {
        Spvo spvo = new Spvo();
        try {
            Map param = new HashMap();
            param.put("gsdm", gsdm);
            param.put("spdm", spdm);
            spvo = spvoService.findOneSpvo(param);
        } catch (Exception e) {
            e.printStackTrace();
            return spvo;
        }
        return spvo;
    }

    public Spvo getSpByMc(String gsdm, Integer xfid, Integer skpid, String spmc) {
        Spvo spvo = new Spvo();
        try {
            Map param = new HashMap();
            param.put("gsdm", gsdm);
            param.put("spmc", spmc);
            spvo = spvoService.findOneSpvo(param);
        } catch (Exception e) {
            e.printStackTrace();
            return spvo;
        }
        return spvo;
    }

    public Spvo getYhBySp(String spbm, String gsdm) {
        Spvo spvo = new Spvo();
        try {
            Map param = new HashMap();
            param.put("gsdm", gsdm);
            param.put("spbm", spbm);
            spvo = spvoService.findOneSpvo(param);
        } catch (Exception e) {
            e.printStackTrace();
            return spvo;
        }
        return spvo;
    }

    public Map getYhByBmsl(String spbm, Double sl) {
        return getLsvBz.getLsvBz(sl, spbm);
    }

}
