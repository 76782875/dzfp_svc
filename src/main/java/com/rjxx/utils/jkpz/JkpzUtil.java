package com.rjxx.utils.jkpz;

import com.rjxx.taxeasy.dao.SkpJpaDao;
import com.rjxx.taxeasy.dao.XfJpaDao;
import com.rjxx.taxeasy.domains.Skp;
import com.rjxx.taxeasy.domains.Xf;
import com.rjxx.utils.alipay.AlipayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
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

    private  static Logger logger = LoggerFactory.getLogger(AlipayUtils.class);

    public  char getRandomLetter(){
        String chars = "abcdefghijklmnopqrstuvwxyz";
        return chars.charAt(new Random().nextInt(26));
    }

    public String setDefSeriaNum(){
        String seriaNum = "JYL"+System.currentTimeMillis()+getRandomLetter();
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
        Xf xf = null;
        if(StringUtils.isNotBlank(xfsh)){
            xf = xfJpaDao.findOneByXfshAndGsdm(gsdm,xfsh);
        }else{
            xf=xfJpaDao.findOneByGsdm(gsdm);
        }
        return xf;
    }

    /**
     * @param gsdm 公司代码
     * @param xfsh 销方税号
     * 如果消息对象中没有clientNo,则认为该销方下只有一个开票点，
     * 从公司代码和销方税号确定唯一销方，再去找唯一一个开票点，如果找到多个，抛错
     * @return
     */
    public Skp defaultKpd(String gsdm,String xfsh){
        Xf xf = xfJpaDao.findOneByXfshAndGsdm(gsdm,xfsh);
        return skpJpaDao.findOneByGsdmAndXfsh(gsdm, xf.getId());
    }
}
