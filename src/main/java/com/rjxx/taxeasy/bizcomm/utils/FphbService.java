package com.rjxx.taxeasy.bizcomm.utils;

import com.alibaba.fastjson.JSON;
import com.rjxx.taxeasy.dao.JyxxsqHbJpaDao;
import com.rjxx.taxeasy.dao.JyxxsqJpaDao;
import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.dto.FphbData;
import com.rjxx.taxeasy.service.*;
import com.rjxx.taxeasy.vo.JymxsqVo;
import com.rjxx.taxeasy.vo.JyspmxDecimal2;
import com.rjxx.taxeasy.vo.JyxxsqVO;
import com.rjxx.time.TimeUtil;
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
    @Autowired
    private YhcljlService yhcljlService;
    @Autowired
    private JylsService jylsService;
    @Autowired
    private JyspmxService jyspmxService;

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
                    newjymxsq.setSpje(jymxsq.getSumjshj());
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
        try {
            if(sqlshList.isEmpty()){
                return false;
            }
            Date date = new Date();
            jyxxsqService.delBySqlshList2(sqlshList);
            for(int i = 0;i<sqlshList.size();i++){
                String sqlsh = sqlshList.get(i).toString();
                List<JyxxsqHb> hbList = jyxxsqHbJpaDao.findByNewsqlsh(Integer.valueOf(sqlsh));
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
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean fphbsave(List sqlshList,Integer yhid,String gsdm){
        try {
            if(sqlshList.isEmpty()){
                return false;
            }
            System.out.println(JSON.toJSONString(sqlshList));
            for (int i = 0;i<sqlshList.size();i++) {
                String sqlsh = (String) sqlshList.get(i);
                Map map = new HashMap();
                map.put("sqlsh",sqlsh);
                map.put("gsdm",gsdm);
                Jyxxsq jyxxsq = jyxxsqService.findOneByParams(map);
                jyxxsq.setZtbz("3");
                yhcljlService.saveYhcljl(yhid, "开票单审核");
                jyxxsqService.save(jyxxsq);

                List<Jymxsq>  jymxsqList = jymxsqService.findAllByParams(map);
                List list = new ArrayList();
                for(int j =0 ;j<jymxsqList.size();j++){
                    list.add(jymxsqList.get(j));
                }
                Jyls jyls = saveJyls(jyxxsq,list,yhid);
                saveKpspmx(jyls, list,yhid,jyxxsq.getGsdm());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

    /**
     * 保存交易流水
     *
     * @param
     * @return
     */
    public Jyls saveJyls(Jyxxsq jyxxsq, List jymxsqList,Integer yhid) throws Exception {
        Jyls jyls1 = new Jyls();
        jyls1.setDdh(jyxxsq.getDdh());
        jyls1.setSqlsh(jyxxsq.getSqlsh());
        jyls1.setJylsh(jyxxsq.getJylsh());
        jyls1.setJylssj(TimeUtil.getNowDate());
        jyls1.setFpzldm(jyxxsq.getFpzldm());
        jyls1.setFpczlxdm("11");
        jyls1.setXfid(jyxxsq.getXfid());
        jyls1.setXfsh(jyxxsq.getXfsh());
        jyls1.setXfmc(jyxxsq.getXfmc());
        jyls1.setXfyh(jyxxsq.getXfyh());
        jyls1.setTqm(jyxxsq.getTqm());
        jyls1.setXfyhzh(jyxxsq.getXfyhzh());
        jyls1.setXflxr(jyxxsq.getXflxr());
        jyls1.setXfdh(jyxxsq.getXfdh());
        jyls1.setXfdz(jyxxsq.getXfdz());
        jyls1.setGfid(jyxxsq.getGfid());
        jyls1.setGfsh(jyxxsq.getGfsh());
        jyls1.setGfmc(jyxxsq.getGfmc());
        jyls1.setGfyh(jyxxsq.getGfyh());
        jyls1.setGfyhzh(jyxxsq.getGfyhzh());
        jyls1.setGflxr(jyxxsq.getGflxr());
        jyls1.setGfdh(jyxxsq.getGfdh());
        jyls1.setGfdz(jyxxsq.getGfdz());
        jyls1.setGfyb(jyxxsq.getGfyb());
        jyls1.setGfemail(jyxxsq.getGfemail());
        jyls1.setClztdm("00");
        jyls1.setSfdyqd(jyxxsq.getSfdyqd());
        jyls1.setBz(jyxxsq.getBz());
        jyls1.setSkr(jyxxsq.getSkr());
        jyls1.setKpr(jyxxsq.getKpr());
        jyls1.setFhr(jyxxsq.getFhr());
        jyls1.setSsyf(jyxxsq.getSsyf());
        jyls1.setYfpdm(null);
        jyls1.setYfphm(null);
        jyls1.setSffsyj(jyxxsq.getSffsyj());
        jyls1.setHsbz(jyxxsq.getHsbz());
        jyls1.setZsfs(jyxxsq.getZsfs());
        double hjje = 0;
        double hjse = 0;
        for (int i =0;i<jymxsqList.size();i++) {
            Jymxsq jymxsq= (Jymxsq) jymxsqList.get(i);
            hjje += jymxsq.getSpje().doubleValue();
            hjse += jymxsq.getSpse().doubleValue();
        }
        jyls1.setJshj(hjje + hjse);
        jyls1.setYkpjshj(0d);
        jyls1.setYxbz("1");
        jyls1.setGsdm(jyxxsq.getGsdm());
        jyls1.setLrry(yhid);
        jyls1.setLrsj(TimeUtil.getNowDate());
        jyls1.setXgry(yhid);
        jyls1.setXgsj(TimeUtil.getNowDate());
        jyls1.setSkpid(jyxxsq.getSkpid());
        jyls1.setSqlsh(jyxxsq.getSqlsh());
        jyls1.setKhh(jyxxsq.getKhh());
        jyls1.setGfsjh(jyxxsq.getGfsjh());
        jyls1.setGfsjrdz(jyxxsq.getGfsjrdz());
        jylsService.save(jyls1);
        return jyls1;
    }

    public void saveKpspmx(Jyls jyls, List jymxsqList,Integer yhid ,String gsdm) throws Exception {
        int djh = jyls.getDjh();
        int i=1;
        for (int j =0;j<jymxsqList.size();j++) {
            Jymxsq mxItem = (Jymxsq) jymxsqList.get(j);
            Jyspmx jymx = new Jyspmx();
            jymx.setDjh(djh);
            jymx.setSpmxxh(i);
            jymx.setSpdm(mxItem.getSpdm());
            jymx.setSpmc(mxItem.getSpmc());
            jymx.setSpggxh(mxItem.getSpggxh());
            jymx.setSpdw(mxItem.getSpdw());
            jymx.setSps(mxItem.getSps() == null ? null : mxItem.getSps().doubleValue());
            jymx.setSpdj(mxItem.getSpdj() == null ? null : mxItem.getSpdj().doubleValue());
            jymx.setSpje(mxItem.getSpje() == null ? null : mxItem.getSpje().doubleValue());
            jymx.setSpsl(mxItem.getSpsl().doubleValue());
            jymx.setSpse(mxItem.getSpse() == null ? null : mxItem.getSpse().doubleValue());
            jymx.setJshj(mxItem.getJshj() == null ? null : mxItem.getJshj().doubleValue());
            jymx.setYkphj(0d);
            jymx.setGsdm(gsdm);
            jymx.setLrsj(TimeUtil.getNowDate());
            jymx.setLrry(yhid);
            jymx.setXgsj(TimeUtil.getNowDate());
            jymx.setXgry(yhid);
            jymx.setKce(mxItem.getKce() == null ? null : mxItem.getKce().doubleValue());
            jymx.setFphxz("0");
            jyspmxService.save(jymx);
            i++;
        }
    }
}
