package com.rjxx.taxeasy.bizcomm.utils;

import com.alibaba.dubbo.config.annotation.Service;
import com.rjxx.taxeasy.dao.PpJpaDao;
import com.rjxx.taxeasy.dao.ShortLinkJpaDao;
import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.service.KplsService;
import com.rjxx.taxeasy.service.SkpService;
import com.rjxx.utils.dwz.ShortUrlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zsq
 * @date: 2018/7/25 9:23
 * @describe:短网址url
 */
@Service
public class ShortUrlService {

    private static Logger logger = LoggerFactory.getLogger(ShortUrlService.class);

    @Autowired
    private KplsService kplsService;
    @Autowired
    private SkpService skpService;
    @Autowired
    private PpJpaDao ppJpaDao;
    @Autowired
    private ShortLinkJpaDao shortLinkJpaDao;

    public Map shortParam(Jyls jyls){
        Map parms=new HashMap();
        Kpls ls = new Kpls();
        ls.setDjh(jyls.getDjh());
        List<Kpls> listkpls = kplsService.findAllByKpls(ls);
        Map skpMap = new HashMap();
        skpMap.put("kpdid",jyls.getSkpid());
        skpMap.put("gsdm",jyls.getGsdm());
        Skp skp = skpService.findOneByParams(skpMap);
        Pp pp = null;
        if(skp.getPid()!=null && skp.getPid()!=-1 &&skp.getPid()!=0){
            pp = ppJpaDao.findOneById(skp.getPid());
        }else{
            pp = ppJpaDao.findOneByPpdm("rjxx");
        }
        //参数转为短链接
        try {
            String dlj= ShortUrlUtil.shortUrl("q="+listkpls.get(0).getSerialorder());//生成短链接
            parms.put("ppmc",pp.getPpmc());
            parms.put("param",dlj);
            ShortLink shortLink = new ShortLink();
            shortLink.setShortLink(dlj);
            shortLink.setNormalLink("q="+listkpls.get(0).getSerialorder());
            shortLink.setType("01");//开票
            shortLink.setCreator("1");
            shortLink.setCreateDate(new Date());
            shortLink.setModifier("1");
            shortLink.setModifyDate(new Date());
            shortLink.setUseMark("1");
            shortLink.setCount(1);
            shortLinkJpaDao.save(shortLink);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            logger.info("------短链接保存出现异常：---------");
            String dlj1=ShortUrlUtil.shortUrl("q="+listkpls.get(0).getSerialorder());
            logger.info("重新生成shortLink 1"+dlj1);
            ShortLink shortLink1 = shortLinkJpaDao.findOneByShortLink(dlj1);
            if(shortLink1!=null){
                //查询到数据重新生成shortLink
                dlj1 = ShortUrlUtil.shortUrl("q="+listkpls.get(0).getSerialorder());//生成短链接
                logger.info("继续生成shortLink 2"+dlj1);
            }
            parms.put("ppmc",pp.getPpmc());
            parms.put("param",dlj1);
            ShortLink shortLink = new ShortLink();
            shortLink.setShortLink(dlj1);
            shortLink.setNormalLink("q="+listkpls.get(0).getSerialorder());
            shortLink.setType("01");//开票
            shortLink.setCreator("1");
            shortLink.setCreateDate(new Date());
            shortLink.setModifier("1");
            shortLink.setModifyDate(new Date());
            shortLink.setUseMark("1");
            shortLink.setCount(1);
            shortLinkJpaDao.save(shortLink);
        }
        return parms;
    }
}
