package com.rjxx.taxeasy.bizcomm.utils;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rjxx.taxeasy.dao.CszbJpaDao;
import com.rjxx.taxeasy.dao.FpkcJpaDao;
import com.rjxx.taxeasy.dao.XfJpaDao;
import com.rjxx.taxeasy.domains.Csb;
import com.rjxx.taxeasy.domains.Cszb;
import com.rjxx.taxeasy.domains.Fpkc;
import com.rjxx.taxeasy.domains.Xf;
import com.rjxx.taxeasy.invoice.ResponeseUtils;
import com.rjxx.taxeasy.service.CsbService;
import com.rjxx.taxeasy.service.FpkcService;
import com.rjxx.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zsq
 * @date: 2018/5/16 18:36
 * @describe:
 */
@Service
public class UploadFpckxxService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private XfJpaDao xfJpaDao;
    @Autowired
    private CsbService csbService;
    @Autowired
    private CszbJpaDao cszbJpaDao;
    @Autowired
    private FpkcJpaDao fpkcJpaDao;
    @Autowired
    private FpkcService fpkcService;

    public String UploadFpckxx(String data)throws Exception{
        try {
            JSONObject upData = JSON.parseObject(data);
            String spbmbbh = upData.getString("spbmbbh");//商品编码版本号
            String xfid = upData.getString("xfid");//销方id
            String skpid = upData.getString("skpid");//税控盘id
            JSONArray kcxx = upData.getJSONArray("kcxx");//库存信息
            if(StringUtils.isBlank(xfid)||StringUtils.isBlank(skpid)){
                return ResponeseUtils.error("销方或税控盘信息为空");
            }
            Xf xf = xfJpaDao.findOne(Integer.valueOf(xfid));
            if(xf==null){
                return ResponeseUtils.error("未查询到销方信息");
            }
            Date date = new Date();
            if(StringUtils.isNotBlank(spbmbbh)){
                Map map = new HashMap();
                map.put("csm","spbmbbh");
                Csb csb = csbService.findOneByParams(map);
                Cszb spbmbbh1 = null;
                try {
                    spbmbbh1 = cszbJpaDao.findOneByCsidAndGsdmAndXfAndSkp(csb.getId(),xf.getGsdm(),Integer.valueOf(xfid),Integer.valueOf(skpid));
                    if(spbmbbh1==null){
                        logger.info("没有查到商品编码版本号,商品编码版本"+spbmbbh+",插入商品编码版本，销方为"+xf.getId());
                        Cszb cszb = new Cszb();
                        cszb.setGsdm(xf.getGsdm());
                        cszb.setCsid(csb.getId());
                        cszb.setXfid(Integer.valueOf(xfid));
                        cszb.setKpdid(Integer.valueOf(skpid));
                        cszb.setYxbz("1");
                        cszb.setCsz(spbmbbh);
                        cszb.setLrry(1);
                        cszb.setXgry(1);
                        cszb.setLrsj(date);
                        cszb.setXgsj(date);
                        cszbJpaDao.save(cszb);
                    }else {
                        logger.info("查到商品编码版本号");
                        if(!spbmbbh1.getCsz().equals(spbmbbh)){
                            logger.info("商品编码版本号不一致，原版本号"+spbmbbh1+"，更新版本号"+spbmbbh);
                            spbmbbh1.setCsz(spbmbbh);
                            spbmbbh1.setYxbz("1");
                            spbmbbh1.setXgry(1);
                            spbmbbh1.setXgsj(date);
                            cszbJpaDao.save(spbmbbh1);
                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    logger.info("查询商品编码版本失败");
                }
            }
            //发票库存信息
            for (int i = 0; i < kcxx.size(); i++) {
                JSONObject o = (JSONObject) kcxx.get(i);
                String fpzldm = o.getString("fpzldm");
                String syfs = o.getString("syfs");
                Map map = new HashMap();
                map.put("gsdm",xf.getGsdm());
                map.put("xfid",xfid);
                map.put("skpid",skpid);
                map.put("fpzldm",fpzldm);
                Fpkc fpkc1 = fpkcService.findOneByParams(map);
                if(fpkc1!=null){
                    logger.info("发票库存已存在，更新库存量");
                    fpkc1.setXgry(1);
                    fpkc1.setXgsj(date);
                    fpkc1.setYxbz("1");
                    fpkc1.setFpkcl(Integer.valueOf(syfs));
                    fpkcJpaDao.save(fpkc1);
                }else {
                    Fpkc fpkc  = new Fpkc();
                    logger.info("发票库存不存在，保存库存量");
                    fpkc.setGsdm(xf.getGsdm());
                    fpkc.setXfid(Integer.valueOf(xfid));
                    fpkc.setSkpid(Integer.valueOf(skpid));
                    fpkc.setFpzldm(fpzldm);
                    fpkc.setFpkcl(Integer.valueOf(syfs));
                    fpkc.setYxbz("1");
                    fpkc.setLrsj(date);
                    fpkc.setLrry(1);
                    fpkc.setXgsj(date);
                    fpkc.setXgry(1);
                    fpkcJpaDao.save(fpkc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponeseUtils.error("数据上传失败");
        }
        return ResponeseUtils.success("数据上传成功");
    }
}
