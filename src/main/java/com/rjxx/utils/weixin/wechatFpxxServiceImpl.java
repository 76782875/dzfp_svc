package com.rjxx.utils.weixin;

import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.rjxx.taxeasy.dao.WxfpxxJpaDao;
import com.rjxx.taxeasy.domains.WxFpxx;
import com.rjxx.utils.alipay.AlipayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Administrator on 2017-09-26.
 */
@Service
public class wechatFpxxServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(wechatFpxxServiceImpl.class);

    @Autowired
    private WxfpxxJpaDao wxfpxxJpaDao;

    /**
     * 对上传给微信的订单号进行count
     * @param orderNo
     * @return
     */
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

    /**
     * 封装微信发票信息保存
     * @param tqm
     * @param gsdm
     * @param orderNo
     * @param q
     * @param wxType
     * @param opendid
     * @param userId
     * @param kplsh
     * @param request
     * @return
     */
    public boolean InFapxx( String tqm,String gsdm,String orderNo,String q,
                         String wxType,String opendid,String userId,String kplsh,HttpServletRequest request){
        if(null!= orderNo){
            WxFpxx wxFpxx = wxfpxxJpaDao.selsetByOrderNo(orderNo);
            if(wxType!=null &&wxType.equals("1")){
                // 申请发票
                if(wxFpxx==null){
                    if(WeixinUtils.isWeiXinBrowser(request)){
                        //微信
                        WxFpxx wFpxx = new WxFpxx();
                        wFpxx.setTqm(tqm);
                        wFpxx.setGsdm(gsdm);
                        wFpxx.setOrderNo(tqm);
                        wFpxx.setQ(q);
                        wFpxx.setWxtype(wxType);//1:申请开票 2、领取发票
                        wFpxx.setOpenId(opendid);
                        wFpxx.setLrsj(new Date());
                        wxfpxxJpaDao.save(wFpxx);
                    }else if(AlipayUtils.isAlipayBrowser(request)){
                        //支付宝
                        WxFpxx aFpxx = new WxFpxx();
                        aFpxx.setTqm(tqm);
                        aFpxx.setGsdm(gsdm);
                        aFpxx.setOrderNo(tqm);
                        aFpxx.setQ(q);
                        aFpxx.setUserid(userId);
                        aFpxx.setLrsj(new Date());
                        wxfpxxJpaDao.save(aFpxx);
                    }
                }else {
                    //第二次以上扫描
                    if(WeixinUtils.isWeiXinBrowser(request)){
                        wxFpxx.setTqm(tqm);
                        wxFpxx.setGsdm(gsdm);
                        wxFpxx.setQ(q);
                        wxFpxx.setOpenId(opendid);
                        wxFpxx.setOrderNo(tqm);
                        wxFpxx.setWxtype(wxType);//1:申请开票2：领取发票
                        wxFpxx.setXgsj(new Date());
                        if(wxFpxx.getCode()!=null||!"".equals(wxFpxx.getCode())){
                            String notNullCode= wxFpxx.getCode();
                            wxFpxx.setCode(notNullCode);
                        }
                        wxfpxxJpaDao.save(wxFpxx);
                    }else if(AlipayUtils.isAlipayBrowser(request)){
                        wxFpxx.setTqm(tqm);
                        wxFpxx.setGsdm(gsdm);
                        wxFpxx.setQ(q);
                        wxFpxx.setUserid(userId);
                        wxFpxx.setOrderNo(tqm);
                        wxFpxx.setXgsj(new Date());
                        if(wxFpxx.getCode()!=null||!"".equals(wxFpxx.getCode())){
                            String notNullCode= wxFpxx.getCode();
                            wxFpxx.setCode(notNullCode);
                        }
                        wxfpxxJpaDao.save(wxFpxx);
                    }
                }
            }else if(null!=wxType && wxType.equals("2")){
                //第一次扫描
                if(wxFpxx==null){
                    if(WeixinUtils.isWeiXinBrowser(request)){
                        //微信
                        WxFpxx wFpxx = new WxFpxx();
                        wFpxx.setTqm(tqm);
                        wFpxx.setGsdm(gsdm);
                        wFpxx.setOrderNo(tqm);
                        wFpxx.setQ(q);
                        wFpxx.setWxtype(wxType);//1:申请开票 2、领取发票
                        wFpxx.setOpenId(opendid);
                        wFpxx.setKplsh(kplsh);
                        wFpxx.setLrsj(new Date());
                        wxfpxxJpaDao.save(wFpxx);
                    }else if(AlipayUtils.isAlipayBrowser(request)){
                        //支付宝
                        WxFpxx aFpxx = new WxFpxx();
                        aFpxx.setTqm(tqm);
                        aFpxx.setGsdm(gsdm);
                        aFpxx.setOrderNo(tqm);
                        aFpxx.setQ(q);
                        aFpxx.setUserid(userId);
                        aFpxx.setKplsh(kplsh);
                        aFpxx.setLrsj(new Date());
                        wxfpxxJpaDao.save(aFpxx);
                    }
                }else {
                    //第二次以上扫描
                    if(WeixinUtils.isWeiXinBrowser(request)){
                        wxFpxx.setTqm(tqm);
                        wxFpxx.setGsdm(gsdm);
                        wxFpxx.setQ(q);
                        wxFpxx.setOpenId(opendid);
                        wxFpxx.setOrderNo(tqm);
                        wxFpxx.setWxtype(wxType);//1:申请开票2：领取发票
                        wxFpxx.setKplsh(kplsh);
                        wxFpxx.setXgsj(new Date());
                        if(wxFpxx.getCode()!=null||!"".equals(wxFpxx.getCode())){
                            String notNullCode= wxFpxx.getCode();
                            wxFpxx.setCode(notNullCode);
                        }
                        wxfpxxJpaDao.save(wxFpxx);
                    }else if(AlipayUtils.isAlipayBrowser(request)){
                        wxFpxx.setTqm(tqm);
                        wxFpxx.setGsdm(gsdm);
                        wxFpxx.setQ(q);
                        wxFpxx.setUserid(userId);
                        wxFpxx.setOrderNo(tqm);
                        wxFpxx.setKplsh(kplsh);
                        wxFpxx.setXgsj(new Date());
                        if(wxFpxx.getCode()!=null||!"".equals(wxFpxx.getCode())){
                            String notNullCode= wxFpxx.getCode();
                            wxFpxx.setCode(notNullCode);
                        }
                        wxfpxxJpaDao.save(wxFpxx);
                    }
                }
            }
        }else {
            return false;
        }
        return true;
    }
}
