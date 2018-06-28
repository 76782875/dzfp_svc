package com.rjxx.taxeasy.bizcomm.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.rjxx.taxeasy.dao.GsxxJpaDao;
import com.rjxx.taxeasy.domains.Gsxx;
import com.rjxx.taxeasy.domains.Sm;
import com.rjxx.taxeasy.domains.Sp;
import com.rjxx.taxeasy.dto.GoodsDetail;
import com.rjxx.taxeasy.invoice.ResponeseUtils;
import com.rjxx.taxeasy.service.SmService;
import com.rjxx.taxeasy.service.SpService;
import com.rjxx.taxeasy.service.SpbmService;
import com.rjxx.taxeasy.vo.Spbm;
import com.rjxx.time.TimeUtil;
import com.rjxx.utils.RJCheckUtil;
import com.rjxx.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: zsq
 * @date: 2018/6/22 16:02
 * @describe:处理商品公共方法
 */
@Service
public class DealGoodsUtil {

    @Autowired
    protected GsxxJpaDao gsxxJpaDao ;
    @Autowired
    private SpService spService;
    @Autowired
    private SmService smService;
    @Autowired
    private SpbmService spbmService;

    private static Logger logger = LoggerFactory.getLogger(DealGoodsUtil.class);

    public String dealGoods(String OrderData){
        Map resultMap = new HashMap();
        try {
            HashMap<String, Object> jsonObject = null;
            try {
                jsonObject = JSON.parseObject(OrderData,LinkedHashMap.class, Feature.OrderedField);

            }catch (Exception e){
                resultMap.put("returnCode","9999");
                resultMap.put("returnMessage","JSON数据格式有误！");
                return JSON.toJSONString(resultMap);
            }
            String sign = String.valueOf(jsonObject.get("sign"));
            String appId = String.valueOf(jsonObject.get("appId"));
            JSONArray data = (JSONArray) jsonObject.get("data");
            Gsxx gsxx = gsxxJpaDao.findOneByAppid(appId);
            if(null == gsxx){
                resultMap.put("returnCode","9999");
                resultMap.put("returnMessage","公司信息没有维护！");
                return JSON.toJSONString(resultMap);
            }
            String check = RJCheckUtil.decodeXml(gsxx.getSecretKey(), JSON.toJSONString(data), sign);
            if ("0".equals(check)) {
                resultMap.put("returnCode","9999");
                resultMap.put("returnMessage","签名不通过,请重新上传！");
                return JSON.toJSONString(resultMap);
            }else{
                if(data.size() <= 0){
                    resultMap.put("returnCode","9999");
                    resultMap.put("returnMessage","商品信息不全，请查看文档！");
                    return JSON.toJSONString(resultMap);
                }
                List<Sp> splist = new ArrayList<>();
                List<Sm> smList = smService.findAll();
                String msgg = "";
                String msgdj = "";
                for(int i =0 ; i<data.size();i++){
                    JSONObject jo = data.getJSONObject(i);
                    Sp sp = new Sp();
                    sp.setGsdm(gsxx.getGsdm());
                    sp.setLrry(1);
                    sp.setXgry(1);
                    sp.setLrsj(TimeUtil.getNowDate());
                    sp.setXgsj(TimeUtil.getNowDate());
                    sp.setYxbz("1");
                    try {
                        sp.setSpdm((jo.getString("spdm") == null ||jo.getString("spdm").equals("")) ? null : jo.getString("spdm"));
                    } catch (Exception e) {
                        sp.setSpdm(null);
                    }
                    try {
                        sp.setSpmc((jo.getString("spmc") == null || jo.getString("spmc").equals(""))? null : jo.getString("spmc"));
                    } catch (Exception e) {
                        sp.setSpmc(null);
                    }
                    try {
                        for (Sm sm : smList) {
                            if (jo.getString("spsl") != null && String.valueOf(sm.getSl()).contains(jo.getString("spsl"))) {
                                sp.setSmid(sm.getId());
                                break;
                            } else if (jo.getString("spsl") != null && !String.valueOf(sm.getSl()).contains(jo.getString("spsl"))) {
                                sp.setSmid(0);
                            }
                        }
                    } catch (Exception e) {
                        sp.setSmid(0);
                    }

                    try {
                        sp.setSpggxh((jo.getString("spggxh") == null || jo.getString("spggxh").equals("") ) ? null : jo.getString("spggxh"));
                    } catch (Exception e) {
                        sp.setSpggxh(null);
                    }
                    try {
                        sp.setSpdw((jo.getString("spdw") == null ||jo.getString("spdw").equals(""))? null : jo.getString("spdw"));
                    } catch (Exception e) {
                        sp.setSpdw(null);
                    }
                    try {
                        //判断单价格式是否保留两位小数
                        if(jo.getString("spdj")!=null && !"".equals(jo.getString("spdj"))){
                            Pattern p=Pattern.compile("^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$");
                            Matcher m=p.matcher(jo.getString("spdj"));
                            boolean b=m.matches();
                            if(b){
                                int bitPos=jo.getString("spdj").indexOf(".");
                                int numOfBits=jo.getString("spdj").length()-bitPos-1;
                                System.out.println(numOfBits);
                                if(numOfBits!=2){
                                    msgg = "第" + (i+1 ) + "条商品单价格式不正确，请重新上传！";
                                    msgdj += msgg;
                                }
                            }else {
                                msgg = "第" + (i+1 ) + "条商品单价格式不正确，请重新上传！";
                                msgdj += msgg;
                            }
                        }
                        sp.setSpdj((jo.getString("spdj")== null ||jo.getString("spdj").equals(""))? null : Double.valueOf(jo.getString("spdj")));
                    } catch (Exception e) {
                        sp.setSpdj(null);
                    }

                    try {
                        sp.setSpbm((jo.getString("spbm") == null ||jo.getString("spbm").equals(""))? null :jo.getString("spbm"));
                    } catch (Exception e) {

                    }
                    splist.add(sp);
                }
                if(StringUtils.isNotBlank(msgdj)){
                    resultMap.put("returnCode","9999");
                    resultMap.put("returnMessage","商品上传失败，失败原因:"+msgdj);
                    return JSON.toJSONString(resultMap);
                }
                 String msg = check(splist, gsxx.getGsdm());
                logger.info("---"+msg);
                 if(StringUtils.isNotBlank(msg)){
                    resultMap.put("returnCode","9999");
                    resultMap.put("returnMessage","商品上传失败，失败原因:"+msg);
                    return JSON.toJSONString(resultMap);
                 }
            spService.save(splist);
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("returnCode","9999");
            resultMap.put("returnMessage","商品上传失败!");
            return JSON.toJSONString(resultMap);
        }
        resultMap.put("returnCode","0000");
        resultMap.put("returnMessage","商品上传成功!");
        return JSON.toJSONString(resultMap);
    }

    private String check(List<Sp> list,String gsdm){
        String msgg = "";
        String msg = "";
        Sp s = new Sp();
        s.setGsdm(gsdm);
        List<Sp> spList = spService.findAllByParams(s);
        List<Spbm> spbmList = spbmService.findAllByParams(new HashMap<>());
        for (int i = 0; i < list.size(); i++) {
            Sp sp = list.get(i);
            String spdm = sp.getSpdm();
            if (spdm == null || "".equals(spdm)) {
                msgg = "第" + (i+1) +"条商品代码不能为空，请重新上传！";
                msg += msgg;
            } else if (spdm.length() > 20) {
                msgg = "第" + (i +1)+ "条商品，商品代码超过20个字符，请重新上传！";
                msg += msgg;
            }
            String spmc = sp.getSpmc();
            if (spmc == null || "".equals(spmc)) {
                msgg = "第" + (i+1)  + "条商品名称不能为空，请重新上传！";
                msg += msgg;
            } else if (spmc.length() > 50) {
                msgg = "第" + (i+1)  + "条商品名称超过50个字符，请重新上传！";
                msg += msgg;
            }
            Integer slid = sp.getSmid();
            if (slid == null || "".equals(slid)) {
                msgg = "第" + (i+1 ) + "条商品税率不能为空，请重新上传！";
                msg += msgg;
            } else if (slid == 0) {
                msgg = "第" + (i+1 ) + "条商品税率不存在，请重新上传！";
                msg += msgg;
            }
            String ggxh = sp.getSpggxh();
            if (ggxh != null && ggxh.length() > 18) {
                msgg = "第" + (i+1) + "条商品规格型号超过18个字符，请重新上传！";
                msg += msgg;
            }
            String spdw = sp.getSpdw();
            if (spdw != null && spdw.length() > 10) {
                msgg = "第" + (i+1) + "条商品单位超过10个字符，请重新上传！";
                msg += msgg;
            }
           /* Double spdj = sp.getSpdj();
            if (spdj != null && String.valueOf(spdj).matches(reg)) {
                msgg = "第" + (i+1 ) + "条商品单价格式不正确，请重新上传！";
                msg += msgg;
            }*/

            // 判断商品代码是否存在
            for (int j = 0; j < spList.size(); j++) {
                Sp sk = spList.get(j);
                if (sk.getSpdm().equals(sp.getSpdm())) {
                    msgg = "第" + (i+1) + "条商品代码已存在，请重新上传！";
                    msg += msgg;
                }
            }
            boolean flag = false;
            String spbm = sp.getSpbm();
            if (spbm == null || "".equals(spbm)) {
                msgg = "第" + (i+1)  + "条商品和服务税收分类编码不能为空，请重新上传！";
                msg += msgg;
            }
            for (Spbm bm : spbmList) {
                if (bm.getSpbm().equals(spbm)) {
                    flag = true;
                }
            }
            if (flag) {
                msgg = "第" + (i+1) + "条商品和服务税收分类编码不存在，请重新上传！";
                msg += msgg;
            }
            // 判断excel税控盘号是否有重复
            for (int j = 0; j < list.size(); j++) {
                Sp sk = list.get(j);
                if (sk.getSpdm() != null && sk.getSpmc() != null && sk.getSpdm().equals(sp.getSpdm())
                        && sk.getSpmc().equals(sp.getSpmc()) && i != j) {
                    msgg = "第" + (i +1) + "条商品代码和第" + (j+1) + "条相同，请重新上传！";
                    msg += msgg;
                }
            }
        }
        return msg;
    }
}

