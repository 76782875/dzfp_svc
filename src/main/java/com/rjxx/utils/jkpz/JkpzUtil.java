package com.rjxx.utils.jkpz;

import com.rjxx.taxeasy.bizcomm.utils.GetLsvBz;
import com.rjxx.taxeasy.dao.SkpJpaDao;
import com.rjxx.taxeasy.dao.XfJpaDao;
import com.rjxx.taxeasy.domains.Cszb;
import com.rjxx.taxeasy.domains.Skp;
import com.rjxx.taxeasy.domains.Xf;
import com.rjxx.taxeasy.service.CszbService;
import com.rjxx.taxeasy.service.SpvoService;
import com.rjxx.taxeasy.vo.Spvo;
import org.apache.commons.lang3.StringUtils;
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

    private  static Logger logger = LoggerFactory.getLogger(JkpzUtil.class);

    public  char getRandomLetter(){
        String chars = "abcdefghijklmnopqrstuvwxyz";
        return chars.charAt(new Random().nextInt(26));
    }

    public String setDefSeriaNum(){
        String seriaNum = "JY"+System.currentTimeMillis()+getRandomLetter();
        return seriaNum;
    }

    public String setDefOrderNo(){
        String orderNo = "DD"+System.currentTimeMillis()+getRandomLetter();
        return orderNo;
    }

    public Date setDefOrderDate(){
        return new Date();
    }

    /**
     *
     * @param xfsh 销方税号
     * 如果销方税号不为空，则取销方
     * 如果为空，则认为该公司下只有一个销方，如果找到多个，抛错
     * @return
     */
    public  Xf getXfBySh(String gsdm,String xfsh){
        Xf xf;
        if(StringUtils.isNotBlank(xfsh)){
            xf = xfJpaDao.findOneByXfshAndGsdm(gsdm,xfsh);
        }else{
            if(StringUtils.isNotBlank(gsdm)){
                xf=xfJpaDao.findOneByGsdm(gsdm);
            }else{
                throw new RuntimeException("缺少公司代码");
            }
        }
        return xf;
    }

    /**
     * @param gsdm 公司代码
     * @param xfsh 销方税号
     * 如果有开票点代码，则取开票点
     * 如果没有开票点代码,则认为该销方下只有一个开票点，
     * 从公司代码和销方税号确定唯一销方，再去找唯一一个开票点，如果找到多个，抛错
     * @return
     */
    public Skp defaultKpd(String kpddm,String gsdm,String xfsh){
        Skp skp;
        if(StringUtils.isNotBlank(kpddm) && StringUtils.isNotBlank(gsdm)){
            skp = skpJpaDao.findOneByKpddmAndGsdm(kpddm, gsdm);
        }else{
            if(StringUtils.isNotBlank(xfsh) && StringUtils.isNotBlank(gsdm)){
                Xf xf = xfJpaDao.findOneByXfshAndGsdm(gsdm,xfsh);
                skp = skpJpaDao.findOneByGsdmAndXfsh(gsdm, xf.getId());
            }else{
                throw new RuntimeException("缺少销方税号或公司代码");
            }
        }
        return skp;
    }


    public String getCszb(String gsdm,Integer xfid,Integer kpdid,String csm){
        Cszb cszb = cszbService.getSpbmbbh(gsdm, xfid, kpdid, csm);
        return  cszb.getCsz();
    }

    /**
     * 如果有三人信息，使用三人信息
     * 如果没有，看是否有开票点代码，从开票点中取
     * 如果没有也没有开票点代码，从销方中取,如果没有销方信息，抛错
     * @param kpr
     * @param skr
     * @param fhr
     * @param kpddm
     * @param xfid
     * @param gsdm
     * @return
     */
    public Map getPerson(String kpr,String skr,String fhr,String kpddm,Integer xfid,String gsdm){
        Map map = new HashMap();
        if (StringUtils.isNotBlank(kpr)) {
            map.put("kpr",kpr);
            map.put("skr",skr);
            map.put("fhr",fhr);
        }else{
            if(StringUtils.isNotBlank(kpddm) && StringUtils.isNotBlank(gsdm)){
                Skp skp = skpJpaDao.findOneByKpddmAndGsdm(kpddm, gsdm);
                map.put("kpr",skp.getKpr());
                map.put("skr",skp.getSkr());
                map.put("fhr",skp.getFhr());
            }else{
                if(xfid!=null && xfid!=0){
                    Xf xf = xfJpaDao.findOneById(xfid);
                    map.put("kpr",xf.getKpr());
                    map.put("skr",xf.getSkr());
                    map.put("fhr",xf.getFhr());
                }else{
                    throw new RuntimeException("缺少销方");
                }
            }
        }
        return map;
    }


    public Spvo defaultSp(String gsdm, Integer xfid , Integer skpid, String csm){
        Spvo spvo = new Spvo();
        try{
            Cszb cszb = cszbService.getSpbmbbh(gsdm,xfid,skpid,csm);
            if(cszb != null){
                String csz = cszb.getCsz();
                Map param = new HashMap();
                param.put("gsdm",gsdm);
                param.put("spdm",cszb);
                spvo = spvoService.findOneSpvo(param);
            }

        }catch (Exception e){
            e.printStackTrace();
            return spvo;
        }
        return spvo;
    }


    public Spvo getSpByid(String gsdm, Integer xfid , Integer skpid, String spdm){
        Spvo spvo = new Spvo();
        try{
                Map param = new HashMap();
                param.put("gsdm",gsdm);
                param.put("spdm",spdm);
                spvo = spvoService.findOneSpvo(param);
        }catch (Exception e){
            e.printStackTrace();
            return spvo;
        }
        return spvo;
    }

    public Spvo getSpByMc(String gsdm, Integer xfid , Integer skpid, String spmc){
        Spvo spvo = new Spvo();
        try{
            Map param = new HashMap();
            param.put("gsdm",gsdm);
            param.put("spmc",spmc);
            spvo = spvoService.findOneSpvo(param);
        }catch (Exception e){
            e.printStackTrace();
            return spvo;
        }
        return spvo;
    }

    public Spvo getYhBySp(String spbm,String gsdm){
        Spvo spvo = new Spvo();
        try{
            Map param = new HashMap();
            param.put("gsdm",gsdm);
            param.put("spbm",spbm);
            spvo = spvoService.findOneSpvo(param);
        }catch (Exception e){
            e.printStackTrace();
            return spvo;
        }
        return spvo;
    }

    public Map getYhByBmsl(String spbm,Double sl){
        return getLsvBz.getLsvBz(sl,spbm);
    }

}
