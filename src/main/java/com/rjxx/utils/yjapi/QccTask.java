package com.rjxx.utils.yjapi;

import com.rjxx.taxeasy.domains.Qympk;
import com.rjxx.taxeasy.service.QympkService;
import com.rjxx.utils.weixin.WeixinUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-11-30.
 */
public class QccTask implements Runnable {
    private List<Qympk> qympkList;

    private QympkService qympkService;

    private static Logger logger = LoggerFactory.getLogger(WeixinUtils.class);

    @Override
    public void run() {
        //保存信息
        try {
            if(qympkList.size()>0){
                for(int i =0;i<qympkList.size();i++){
                    Qympk qympk = qympkList.get(i);
                    Map map = new HashMap();
                    map.put("dwmc",qympk.getDwmc());
                    map.put("nsrsbh",qympk.getNsrsbh());
                    List<Qympk> allByParams = qympkService.findAll(map);
                    if(allByParams.size()>0){
                        continue;
                    }
                    //if((qympk.getDwmc()!=null&&!qympk.getDwmc().equals(""))&&(qympk.getNsrsbh()!=null&&!qympk.getNsrsbh().equals(""))){
                    qympk.setYxbz("1");
                    qympkService.saveQympk(qympk);
                    // }else {
                    //     return;
                    // }


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Qympk> getQympkList() {
        return qympkList;
    }

    public void setQympkList(List<Qympk> qympkList) {
        this.qympkList = qympkList;
    }

    public QympkService getQympkService() {
        return qympkService;
    }

    public void setQympkService(QympkService qympkService) {
        this.qympkService = qympkService;
    }
}
