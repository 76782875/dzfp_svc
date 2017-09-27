package com.rjxx.utils.weixin;

import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.rjxx.taxeasy.dao.WxfpxxJpaDao;
import com.rjxx.taxeasy.domains.WxFpxx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017-09-26.
 */
@Service
public class wechatFpxxServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(wechatFpxxServiceImpl.class);

    @Autowired
    private WxfpxxJpaDao wxfpxxJpaDao;

    public String getweixinOrderNo(String orderNo){
        WxFpxx wxFpxx = wxfpxxJpaDao.selsetByOrderNo(orderNo);
        if(null!=wxFpxx){
            if(wxFpxx.getCount() == 0){
                logger.info("没有进行过计数的"+orderNo);
                wxFpxx.setWeixinOderno(orderNo);
                wxfpxxJpaDao.save(wxFpxx);
                return orderNo;
            }else {
                logger.info("进行过计数的"+orderNo);
                String weixinOrderno = orderNo +"-"+ wxFpxx.getCount();
                wxFpxx.setWeixinOderno(weixinOrderno);
                wxfpxxJpaDao.save(wxFpxx);
                return weixinOrderno;
            }
        }else {
            return  orderNo;
        }
    }
}
