package com.rjxx.taxeasy.service.buildqr;

import com.alibaba.fastjson.JSON;
import com.rjxx.taxeasy.dao.GsxxJpaDao;
import com.rjxx.taxeasy.dao.SkpJpaDao;
import com.rjxx.taxeasy.dao.XfJpaDao;
import com.rjxx.taxeasy.dao.YhJpaDao;
import com.rjxx.taxeasy.dao.shouqianba.PayActivateRepository;
import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.domains.shouqianba.PayActivate;
import com.rjxx.taxeasy.dto.AdapterGet;
import com.rjxx.taxeasy.service.CszbService;
import com.rjxx.taxeasy.service.SkpService;
import com.rjxx.taxeasy.service.SpvoService;
import com.rjxx.taxeasy.vo.Spvo;
import com.rjxx.utils.Base64Util;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/4/24
 */
@Service
public class BuildQRService {

    private  static Logger logger = LoggerFactory.getLogger(BuildQRService.class);

    @Autowired
    private YhJpaDao yhJpaDao;
    @Autowired
    private SkpService skpService;
    @Autowired
    private GsxxJpaDao gsxxJpaDao;
    @Autowired
    private PayActivateRepository payActivateRepository;
    @Autowired
    private CszbService cszbService;
    @Autowired
    private XfJpaDao xfJpaDao;
    @Autowired
    private SpvoService spvoService;
    @Autowired
    private SkpJpaDao skpJpaDao;

//    public String login(String username, String password) {
//        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
//            String pass = yhJpaDao.findYhmmByDlyhid(username);
//            if (password.equals(pass)) {
//                Yh yh = yhJpaDao.findOne(yhJpaDao.findIdByDlyhid(username));
//                String roleids = yh.getRoleids();
//                List<Skp> skpListByYhId = skpService.getSkpListByYhId(yh.getId());
//                if(skpListByYhId.size()!=1){
//                    return "-2";
//                }
//                String kpddm = skpListByYhId.get(0).getKpddm();
//                String kpdmc = skpListByYhId.get(0).getKpdmc();
//                String gsdm = skpListByYhId.get(0).getGsdm();
//                String yhmc = yh.getYhmc();
//                Integer skpid = skpListByYhId.get(0).getId();
//                PayActivate payActivate = payActivateRepository.findOneBySkpid(skpid);
//                String terminalSn = "";
//                String terminalKey = "";
//                if(payActivate!=null){
//                   terminalSn = payActivate.getTerminalSn();
//                   terminalKey = payActivate.getTerminalKey();
//                }
//
//                Map map = new HashMap<>();
//                map.put("kpddm", kpddm);
//                map.put("kpdmc", kpdmc);
//                map.put("gsdm", gsdm);
//                map.put("yhmc", yhmc);
//                if(StringUtils.isNotBlank(terminalSn) && StringUtils.isNotBlank(terminalKey)){
//                    map.put("terminalSn", terminalSn);
//                    map.put("terminalKey", terminalKey);
//                    Cszb cszb = cszbService.getSpbmbbh(gsdm, skpListByYhId.get(0).getXfid(), skpid, "dyspbmb");
//                    if(cszb.getCsz()!=null){
//                        Map findSpvo = new HashMap();
//                        findSpvo.put("gsdm", gsdm);
//                        findSpvo.put("spdm", cszb.getCsz());
//                        Spvo spvo = spvoService.findOneSpvo(findSpvo);
//                        map.put("spmc", spvo.getSpmc());
//                    }else{
//                        return "-3";
//                    }
//                }
//                if("1".equals(roleids)){
//                    return "-1";
//                }
//                return JSON.toJSONString(map);
//            } else {
//                return "0";
//            }
//        } else {
//            return "0";
//        }
//    }

    /**
     * 公众号登录，根据参数控制是开票码还是支付码
     * @param username
     * @param password
     * @return
     */
    public Map login(String username, String password) {
        Map errorResult = new HashMap();
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            String pass = yhJpaDao.findYhmmByDlyhid(username);
            if (password.equals(pass)) {
                Map succResult = loginForYhid(yhJpaDao.findIdByDlyhid(username));
                return succResult;
            } else {
                errorResult.put("errorMsg", "用户名不存在或密码错误");
                return errorResult;
            }
        } else {
            errorResult.put("errorMsg", "用户名不存在或密码错误");
            return errorResult;
        }
    }

    public Map loginForYhid(Integer yhId) {
        Map errorResult = new HashMap();
        Yh yh = yhJpaDao.findOne(yhId);
        String roleids = yh.getRoleids();
        List<Skp> skpListByYhId = skpService.getSkpListByYhId(yh.getId());
        if (skpListByYhId.size() != 1) {
            errorResult.put("errorMsg", "该账户开票点权限有误");
            return errorResult;
        }
        String kpddm = skpListByYhId.get(0).getKpddm();
        String kpdmc = skpListByYhId.get(0).getKpdmc();
        String gsdm = skpListByYhId.get(0).getGsdm();
        Gsxx oneByGsdm = gsxxJpaDao.findOneByGsdm(gsdm);
        String gsmc = oneByGsdm.getGsdm();
        Integer yhmc = yh.getId();
        Integer skpid = skpListByYhId.get(0).getId();
        Integer xfid=skpListByYhId.get(0).getXfid();
        Cszb qRDefaultCs = cszbService.getSpbmbbh(gsdm, xfid, skpid, "QRDefault");
        String qrDefault = qRDefaultCs.getCsz();
        PayActivate payActivate = payActivateRepository.findOneBySkpid(skpid);
        String terminalSn = "";
        String terminalKey = "";
        if (payActivate != null) {
            terminalSn = payActivate.getTerminalSn();
            terminalKey = payActivate.getTerminalKey();
            if(StringUtils.isBlank(terminalSn) ||StringUtils.isBlank(terminalKey)){
                if("2".equals(qrDefault)){
                    errorResult.put("errorMsg", "终端激活未成功");
                    return errorResult;
                }
            }
        }else{
            if("2".equals(qrDefault)){
                errorResult.put("errorMsg", "终端未激活");
                return errorResult;
            }
        }
        Cszb qrIsSwitchedCs = cszbService.getSpbmbbh(gsdm, xfid, skpid, "QRIsSwitched");
        String qrIsSwitched = qrIsSwitchedCs.getCsz();
        Map succResult = new HashMap<>();
        succResult.put("qrDefault", qrDefault);
        succResult.put("qrIsSwitched",qrIsSwitched);
        succResult.put("userType", "b");
        succResult.put("kpddm", kpddm);
        succResult.put("kpdmc", kpdmc);
        succResult.put("gsdm", gsdm);
        succResult.put("gsmc", gsmc);
        succResult.put("yhmc", yhmc);
        if (StringUtils.isNotBlank(terminalSn) && StringUtils.isNotBlank(terminalKey)) {
            succResult.put("terminalSn", terminalSn);
            succResult.put("terminalKey", terminalKey);
            Cszb dyspbmbCs = cszbService.getSpbmbbh(gsdm, xfid, skpid, "dyspbmb");
            Map spvoMap = new HashMap();
            spvoMap.put("gsdm", gsdm);
            spvoMap.put("spdm", dyspbmbCs.getCsz());
            Spvo oneSpvo = spvoService.findOneSpvo(spvoMap);
            if (dyspbmbCs.getCsz() != null) {
                succResult.put("spmc", oneSpvo.getSpmc());
            } else {
                errorResult.put("errorMsg", "未获取到默认商品,请确认初始化信息");
                return errorResult;
            }
        }
        if ("1".equals(roleids)) {
            errorResult.put("errorMsg", "请使用非管理员账户登录");
            return errorResult;
        }
        return succResult;
    }

    /**
     * 静态码默认支付码，防止不登录无限开票
     * @param skpid
     * @return
     */
    public Map login(Integer skpid) {
        Map errorResult = new HashMap();
        Skp skp = skpJpaDao.findOneById(skpid);
        String kpddm = skp.getKpddm();
        String kpdmc = skp.getKpdmc();
        String gsdm = skp.getGsdm();
        Integer xfid = skp.getXfid();
        Gsxx gsxx = gsxxJpaDao.findOneByGsdm(gsdm);
        PayActivate payActivate = payActivateRepository.findOneBySkpid(skpid);
        String terminalSn = "";
        String terminalKey = "";
        if (payActivate != null) {
            terminalSn = payActivate.getTerminalSn();
            terminalKey = payActivate.getTerminalKey();
            if (StringUtils.isBlank(terminalSn) || StringUtils.isBlank(terminalKey)) {
                errorResult.put("errorMsg", "终端激活未成功");
                return errorResult;
            }
        } else {
            errorResult.put("errorMsg", "终端未激活");
            return errorResult;
        }
        Map succResult = new HashMap<>();
        succResult.put("userType", "c");
        succResult.put("kpddm", kpddm);
        succResult.put("kpdmc", kpdmc);
        succResult.put("gsdm", gsdm);
        succResult.put("gsjc", gsxx.getGsjc());
        succResult.put("terminalSn", terminalSn);
        succResult.put("terminalKey", terminalKey);
        Cszb dyspbmbCs = cszbService.getSpbmbbh(gsdm, xfid, skpid, "dyspbmb");
        if (dyspbmbCs.getCsz() != null) {
            Map findSpvo = new HashMap();
            findSpvo.put("gsdm", gsdm);
            findSpvo.put("spdm", dyspbmbCs.getCsz());
            Spvo spvo = spvoService.findOneSpvo(findSpvo);
            succResult.put("spmc", spvo.getSpmc());
        } else {
            errorResult.put("errorMsg", "未获取到默认商品,请确认初始化信息");
            return errorResult;
        }
        return succResult;
    }

    public String create(String gsdm, String orderNo, String orderTime,
                         String storeNo, String price) {
        try {
            AdapterGet adapterGet = new AdapterGet();
            adapterGet.setType("2");
            adapterGet.setOn(orderNo);
            adapterGet.setSn(storeNo);
            adapterGet.setOt(orderTime);
            adapterGet.setPr(price);
            String dataJson = JSON.toJSONString(adapterGet);
            logger.info("dataJson={}",dataJson);
            Gsxx gsxx = gsxxJpaDao.findOneByGsdm(gsdm);
            String key = gsxx.getSecretKey();
            String sign = DigestUtils.md5Hex("data=" + dataJson + "&key=" + key);
            String str = "data=" + dataJson + "&si=" + sign;
            String encode = null;
            try {
                encode = Base64Util.encode(str);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return encode;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
