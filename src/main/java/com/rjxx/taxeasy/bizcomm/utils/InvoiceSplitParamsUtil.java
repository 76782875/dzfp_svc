package com.rjxx.taxeasy.bizcomm.utils;


import com.rjxx.taxeasy.domains.Cszb;
import com.rjxx.taxeasy.domains.Jyxxsq;
import com.rjxx.taxeasy.domains.Skp;
import com.rjxx.taxeasy.domains.Xf;
import com.rjxx.taxeasy.service.CszbService;
import com.rjxx.taxeasy.service.SkpService;
import com.rjxx.taxeasy.service.XfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 封装分票所需参数
 * */
@Service
public class InvoiceSplitParamsUtil {

    @Autowired
    private SkpService skpService;
    @Autowired
    private XfService xfService;
    @Autowired
    private CszbService cszbService;
    /**
     * @param jyxxsq
     * @return
     *
     * 获取对应票种的开票限额，分票金额，分票行数，清单标志等
     * 开票限额，分票金额选择对应的开票点设置的值，如果开票点没进行设置，则选取销方。
     * 分票行数使用参数默认值（纸票8，电票36），如有特殊要求设置参数字表即可
     * 清单标志使用jyxxsq中所传的值。如果没进行设置，则选取参数默认值（否），清单可以参数进行设置
     * 但是首选jyxxsq中的sfdyqd值。
     * 是否强制分票默认false（否），参数设置有值则选择参数值
     * 是否整数分票默认false（否），参数设置有值则选择参数值
     *
     */
      public Map<String,Object> getInvoiceSplitParams(Jyxxsq jyxxsq){

          Map<String,Object> result = new HashMap<String,Object>();
          try {
              //取最大限额
              double zdje = 999.99d;
              double fpje = 999.99d;
              int fphs1 = 8;//纸票分票行数
              int fphs2 = 36;//电票分票行数
              int fphs3 = 6;//卷票分票行数
              String hsbz = "0"; //含税标志默认不含税
              boolean flag = false;
              boolean spzsfp = false;//是否按商品整数分票
              boolean sfqzfp = false;//是否进行强制分票，强制分票含义：开票金额超分票金额未超开票限额，进行分票。
              Map skpMap = new HashMap();
              skpMap.put("kpddm", jyxxsq.getKpddm());
              skpMap.put("gsdm", jyxxsq.getGsdm());
              Skp skp = skpService.findOneByParams(skpMap);
              Xf x = new Xf();
              x.setGsdm(jyxxsq.getGsdm());
              x.setXfsh(jyxxsq.getXfsh());
              Xf xf = xfService.findOneByParams(x);
              /**
               * 取税控盘的开票限额
               */
              if (skp != null) {
                  if ("01".equals(jyxxsq.getFpzldm())) {
                      zdje = skp.getZpmax();
                      fpje = skp.getZpfz();
                  } else if ("02".equals(jyxxsq.getFpzldm())) {
                      zdje = skp.getPpmax();
                      fpje = skp.getPpfz();
                  } else if ("12".equals(jyxxsq.getFpzldm())) {
                      zdje = skp.getDpmax();
                      fpje = skp.getFpfz();
                  } else if ("03".equals(jyxxsq.getFpzldm())) {
                      zdje = skp.getDpmax();
                      fpje = skp.getFpfz();
                  }
                  flag = true;
              }
              /**
               * 如果取不到税控盘的限额，就取销方的限额
               */
              if (!flag) {
                  if ("01".equals(jyxxsq.getFpzldm())) {
                      zdje = xf.getZpzdje();
                      fpje = xf.getZpfpje();
                  } else if ("02".equals(jyxxsq.getFpzldm())) {
                      zdje = xf.getPpzdje();
                      fpje = xf.getPpfpje();
                  } else if ("12".equals(jyxxsq.getFpzldm())) {
                      zdje = xf.getDzpzdje();
                      fpje = xf.getDzpfpje();
                  } else if ("03".equals(jyxxsq.getFpzldm())) {
                      zdje = xf.getDzpzdje();
                      fpje = xf.getDzpfpje();
                  }
              }
              //获取纸票分票行数。系统默认为8行
              Cszb cszb = cszbService.getSpbmbbh(jyxxsq.getGsdm(), jyxxsq.getXfid(), jyxxsq.getSkpid(), "zzphs");
              if (null != cszb && !cszb.equals("")) {
                  fphs1 = Integer.valueOf(cszb.getCsz());
              }
              //获取电子票分票行数。系统默认36行
              Cszb cszb2 = cszbService.getSpbmbbh(jyxxsq.getGsdm(), jyxxsq.getXfid(), jyxxsq.getSkpid(), "dzphs");
              if (null != cszb2 && !cszb2.equals("")) {
                  fphs2 = Integer.valueOf(cszb2.getCsz());
              }
              //获取是否打印清单。系统默认不打印
              Cszb cszb3 = cszbService.getSpbmbbh(jyxxsq.getGsdm(), jyxxsq.getXfid(), jyxxsq.getSkpid(), "sfdyqd");
              String qdbz = "否";//清单标志
              if (null != cszb3 && !cszb3.equals("")) {
                  qdbz = cszb3.getCsz();
              }
              /**
               * 清单标志，行数无限大
               */
              if (jyxxsq.getSfdyqd() != null && jyxxsq.getSfdyqd().equals("1")) {
                  fphs1 = 99999;
                  fphs2 = 99999;
              } else {
                  if (qdbz.equals("是")) {
                      fphs1 = 99999;
                      fphs2 = 99999;
                  }
              }
              //防止分票金额不设置
              if (0 == fpje) {
                  fpje = zdje;
              }
              //获取是否整数分票.系统默认否
              Cszb cszb4 = cszbService.getSpbmbbh(jyxxsq.getGsdm(), jyxxsq.getXfid(), jyxxsq.getSkpid(), "sfzsfp");
              if (null != cszb4 && !cszb4.equals("")) {
                  spzsfp = cszb4.getCsz().equals("否") ? false : true;
              }
              result.put("fphs1", fphs1);
              result.put("fphs2", fphs2);
              result.put("fphs3", fphs3);
              result.put("hsbz", hsbz);
              result.put("spzsfp", spzsfp);
              result.put("sfqzfp", sfqzfp);
              result.put("zdje", zdje);
              result.put("fpje", fpje);
          }catch (Exception e){
              e.printStackTrace();
          }
          return result;
      }
}
