package com.rjxx.taxeasy.bizcomm.utils;

import com.rjxx.taxeasy.dao.GfxxJpaDao;
import com.rjxx.taxeasy.domains.Gfxx;
import com.rjxx.utils.ChinaInitial;
import com.rjxx.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: zsq
 * @date: 2018/4/26 14:06
 * @describe:    已开发票的购方信息保存进入购方管理
 */
@Service
public class SaveGfxxUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GfxxJpaDao gfxxJpaDao;

    public boolean saveGfxx(Integer xfid,String gsdm, String gfmc, String gfsh, String gfdz, String gfdh,String gfyh,String gfyhzh ,String gfemail){
        try {
            if(StringUtils.isNotBlank(gfsh) && StringUtils.isNotBlank(gfmc)){
                logger.info("购方管理开始---------");
                Gfxx gfxx1 = gfxxJpaDao.findOneByGfmcAndGfsh(gfmc, gfsh);
                Date date = new Date();
                if(gfxx1!=null){
                    gfxx1.setGfdz(gfdz);
                    gfxx1.setGfdh(gfdh);
                    gfxx1.setGfyh(gfyh);
                    gfxx1.setGfyhzh(gfyhzh);
                    gfxx1.setXgry(1);
                    gfxx1.setXgsj(date);
                    gfxx1.setGsdm(gsdm);
                    gfxx1.setXfid(xfid);
                    gfxx1.setEmail(gfemail);
                    gfxxJpaDao.save(gfxx1);
                }else {
                    Gfxx gfxx = new Gfxx();
                    ChinaInitial chinain = new ChinaInitial();
                    String mcszmsx = chinain.getPYIndexStr(gfmc, false);//第二个参数代表是否大小写，ture大写，false小写。
                    gfxx.setGfmc(gfmc);
                    gfxx.setGfsh(gfsh);
                    gfxx.setGfdz(gfdz);
                    gfxx.setGfdh(gfdh);
                    gfxx.setGfyh(gfyh);
                    gfxx.setGfyhzh(gfyhzh);
                    gfxx.setMcszmsx(mcszmsx);
                    gfxx.setYxbz("1");
                    gfxx.setLrry(1);
                    gfxx.setLrsj(date);
                    gfxx.setXgry(1);
                    gfxx.setXgsj(date);
                    gfxx.setGsdm(gsdm);
                    gfxx.setXfid(xfid);
                    gfxx.setEmail(gfemail);
                    gfxxJpaDao.save(gfxx);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
