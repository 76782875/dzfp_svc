package com.rjxx.taxeasy.bizcomm.utils;

import com.rjxx.taxeasy.dao.JyxxsqHbJpaDao;
import com.rjxx.taxeasy.dao.JyxxsqJpaDao;
import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.dto.FphbData;
import com.rjxx.taxeasy.service.JymxsqService;
import com.rjxx.taxeasy.service.JyxxsqService;
import com.rjxx.taxeasy.vo.JymxsqVo;
import com.rjxx.taxeasy.vo.JyxxsqVO;
import com.rjxx.utils.BeanConvertUtils;
import com.rjxx.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: zsq
 * @date: 2018/4/12 15:47
 * @describe: 发票合并处理
 */
@Service
public class FphbService {

    @Autowired
    private JyxxsqService jyxxsqService;
    @Autowired
    private JyxxsqJpaDao jyxxsqJpaDao;
    @Autowired
    private JymxsqService jymxsqService;
    @Autowired
    private JyxxsqHbJpaDao jyxxsqHbJpaDao;
    @Autowired
    private DiscountDealUtil discountDealUtil;

    public List fphbcl(FphbData data){
        List result = new ArrayList();
        try {
            Date date = new Date();
            Map map = new HashMap();
            map.put("ztbz","6");//状态标志6表示未处理，5表示已部分处理
            map.put("bfzt","5");
            map.put("gsdm","zsq");
            map.put("sqlshList",data.getSqlshList());
            List<JyxxsqVO> listKpddm = jyxxsqService.findBySqlsh(map);
            if(listKpddm.isEmpty()){
                return null;
            }
            List resultList = new ArrayList();
            for (JyxxsqVO jyxxsq : listKpddm) {
                //合并电票
//                if(jyxxsq.getFpzldm().equals("12")){}
                Jyxxsq newJyxxsq = saveNewJyxxsq(jyxxsq,date,data.getGfmc(),data.getGfsh(),data.getGfyh(),data.getGfyhzh()
                        ,data.getGfdz(),data.getGfdh(),data.getGfemali());
                String sqlshCount = jyxxsq.getSqlshCount();
                String[] sq = sqlshCount.split(",");
                List hbsqlshlist = new ArrayList();
                for (String s : sq) {
                    Map map1 = new HashMap();
                    map1.put("sqlsh",s);
                    map1.put("gsdm" , newJyxxsq.getGsdm());
                    Jyxxsq jyxxsq1 = jyxxsqService.findOneByJylsh(map1);
                    jyxxsq1.setZtbz("3");
                    jyxxsqJpaDao.save(jyxxsq1);
                    hbsqlshlist.add(s);
                }
                Map jymxsqMap = new HashMap();
                jymxsqMap.put("gsdm" ,"zsq");
                jymxsqMap.put("sqlshList",hbsqlshlist);
                List<JymxsqVo> jymxsqList = jymxsqService.findBySqlsh(jymxsqMap);

                List<Jymxsq> newjymxsqList = new ArrayList<>();
                int spmxxh = 1;
                for (JymxsqVo jymxsq : jymxsqList) {
                    Jymxsq newjymxsq = new Jymxsq();
                    newjymxsq.setHsbz("1");
                    newjymxsq.setSpmxxh(spmxxh);
                    newjymxsq.setFphxz(jymxsq.getFphxz());
                    newjymxsq.setSpdm(jymxsq.getSpdm());
                    newjymxsq.setSpmc(jymxsq.getSpmc());
                    newjymxsq.setSpggxh(jymxsq.getSpggxh());
                    newjymxsq.setSpzxbm(jymxsq.getSpzxbm());
                    newjymxsq.setSpdw(jymxsq.getSpdw());
                    newjymxsq.setSps(null);
                    newjymxsq.setSpdj(null);
                    newjymxsq.setSpje(jymxsq.getSumspje());
                    newjymxsq.setSpsl(jymxsq.getSpsl());
                    newjymxsq.setSpse(0d);
                    newjymxsq.setJshj(jymxsq.getSumjshj());
                    newjymxsq.setGsdm(jymxsq.getGsdm());
                    newjymxsq.setYhzcbs(jymxsq.getYhzcbs());
                    newjymxsq.setYhzcmc(jymxsq.getYhzcmc());
                    newjymxsq.setLslbz(jymxsq.getLslbz());
                    newjymxsq.setKkjje(jymxsq.getSumkkjje());
                    newjymxsq.setYkjje(0d);
                    newjymxsq.setSkpid(jymxsq.getSkpid());
                    newjymxsq.setXfid(jymxsq.getXfid());
                    newjymxsq.setYxbz("1");
                    newjymxsq.setSpbz(jymxsq.getSpbz());
                    newjymxsq.setLrry(1);
                    newjymxsq.setLrsj(date);
                    newjymxsq.setXgsj(date);
                    newjymxsq.setXgry(1);
                    spmxxh++;
                    newjymxsqList.add(newjymxsq);
                }
                List<JymxsqCl> jymxsqClList = new ArrayList<JymxsqCl>();
                List<Jymxsq> jymxsqTempList = new ArrayList<Jymxsq>();
                jymxsqTempList = BeanConvertUtils.convertList(newjymxsqList, Jymxsq.class);
                jymxsqClList = discountDealUtil.dealDiscount(jymxsqTempList, 0d, newJyxxsq.getJshj(),jyxxsq.getHsbz());
                //保存 t_jyxxsq, t_jymxsq, t_jymxsq_cl
                Integer saveJyxxsq = jyxxsqService.saveJyxxsq(newJyxxsq, newjymxsqList, jymxsqClList, new ArrayList<Jyzfmx>());
                //保存t_jyxxsq_hb
                for (String s : sq) {
                    JyxxsqHb jyxxsqHb = new JyxxsqHb();
                    //保存交易信息合并表
                    jyxxsqHb.setOldsqlsh(Integer.valueOf(s));
                    jyxxsqHb.setNewsqlsh(saveJyxxsq);
                    jyxxsqHb.setYxbz("1");
                    jyxxsqHb.setLrry(1);
                    jyxxsqHb.setLrsj(date);
                    jyxxsqHb.setXgry(1);
                    jyxxsqHb.setXgsj(date);
                    jyxxsqHbJpaDao.save(jyxxsqHb);
                }
                resultList.add(newJyxxsq);
            }
            result=resultList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return  result;
    }


    public boolean fphbCancle(List sqlshList,String gsdm,Integer yhid){
        if(sqlshList.isEmpty()){
            return false;
        }
        Date date = new Date();
        for(int i = 0;i<sqlshList.size();i++){
            String sqlsh = sqlshList.get(i).toString();
            Map map = new HashMap();
            map.put("gsdm",gsdm);
            map.put("sqlsh",sqlsh);
            Jyxxsq jyxxsq = jyxxsqService.findOneByParams(map);
            List<JyxxsqHb> hbList = jyxxsqHbJpaDao.findByNewsqlsh(jyxxsq.getSqlsh());
            for(int j =0; j<hbList.size();j++){
                Integer sqlsh1= hbList.get(j).getOldsqlsh();
                Map map1 = new HashMap();
                map1.put("gsdm",gsdm);
                map1.put("sqlsh",sqlsh1);
                Jyxxsq jyxxsq1 = jyxxsqService.findOneByParams(map1);
                jyxxsq1.setZtbz("6");
                jyxxsq1.setXgry(yhid);
                jyxxsq1.setXgsj(date);
                jyxxsqJpaDao.save(jyxxsq1);
            }
            jyxxsq.setYxbz("0");
            jyxxsq.setXgry(yhid);
            jyxxsq.setXgsj(date);
            jyxxsqJpaDao.save(jyxxsq);
//            jyxxsqJpaDao.delete(jyxxsq);
        }
        return true;
    }


    //生成订单号
    public char getRandomLetter() {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        return chars.charAt(new Random().nextInt(26));
    }

    public Jyxxsq saveNewJyxxsq(JyxxsqVO jyxxsq,Date date,String gfmc,String gfsh,String gfyh,
                                String gfyhzh,String gfdz,String gfdh,String gfemail){
        Jyxxsq jyxxsqNew = new Jyxxsq();
        jyxxsqNew.setKpddm(jyxxsq.getKpddm());
        jyxxsqNew.setJylsh("JY" + new SimpleDateFormat("yyyyMMddHHmmssSS").format(date));
        jyxxsqNew.setDdrq(date);
        jyxxsqNew.setDdh("DD" + System.currentTimeMillis() + getRandomLetter());
        jyxxsqNew.setFpzldm(jyxxsq.getFpzldm());
        jyxxsqNew.setFpczlxdm("11");
        jyxxsqNew.setXfid(jyxxsq.getXfid());
        jyxxsqNew.setXfsh(jyxxsq.getXfsh());
        jyxxsqNew.setXfmc(jyxxsq.getXfmc());
        jyxxsqNew.setXfyh(jyxxsq.getXfyh());
        jyxxsqNew.setXfyhzh(jyxxsq.getXfyhzh());
        jyxxsqNew.setXflxr(jyxxsq.getXflxr());
        jyxxsqNew.setXfdz(jyxxsq.getXfdz());
        jyxxsqNew.setXfyb(jyxxsq.getXfyb());
        jyxxsqNew.setXfdh(jyxxsq.getXfdh());
        jyxxsqNew.setGfmc(gfmc);
        jyxxsqNew.setGfsh(gfsh);
        if(StringUtils.isNotBlank(gfsh)){
            jyxxsqNew.setGflx("1");
        }else {
            jyxxsqNew.setGflx("0");
        }
        jyxxsqNew.setGfyh(gfyh);
        jyxxsqNew.setGfyhzh(gfyhzh);
        jyxxsqNew.setGfdz(gfdz);
        jyxxsqNew.setGfdh(gfdh);
        jyxxsqNew.setGfemail(gfemail);
        if(StringUtils.isNotBlank(gfemail)){
            jyxxsqNew.setSffsyj("1");
        }else {
            jyxxsqNew.setSffsyj("0");
        }
        jyxxsqNew.setClztdm("00");
        jyxxsqNew.setKpr(jyxxsq.getKpr());
        jyxxsqNew.setSkr(jyxxsq.getSkr());
        jyxxsqNew.setFhr(jyxxsq.getFhr());
        jyxxsqNew.setZsfs(jyxxsq.getZsfs());
        jyxxsqNew.setSsyf(jyxxsq.getSsyf());
        jyxxsqNew.setHsbz(jyxxsq.getHsbz());
        jyxxsqNew.setJshj(jyxxsq.getJshjSum());
        jyxxsqNew.setYkpjshj(jyxxsq.getYkpjshj());
        jyxxsqNew.setYxbz("1");
        jyxxsqNew.setLrsj(date);
        jyxxsqNew.setLrry(1);
        jyxxsqNew.setXgry(1);
        jyxxsqNew.setXgsj(date);
        jyxxsqNew.setGsdm("zsq");
        jyxxsqNew.setTqm(jyxxsq.getTqm());
        jyxxsqNew.setSkpid(jyxxsq.getSkpid());
        jyxxsqNew.setSjly("0");
        jyxxsqNew.setZtbz("6");
        jyxxsqNew.setSfdyqd(jyxxsq.getSfdyqd());
//        Jyxxsq jyxxsqSave = jyxxsqJpaDao.save(jyxxsqNew);
        return jyxxsqNew;
    }
}
