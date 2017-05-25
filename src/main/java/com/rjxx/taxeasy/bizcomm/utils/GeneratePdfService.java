package com.rjxx.taxeasy.bizcomm.utils;

import com.rjxx.comm.utils.ApplicationContextUtils;
import com.rjxx.taxeasy.bizcomm.utils.pdf.PdfDocumentGenerator;
import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.service.*;
import com.rjxx.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017-05-11.
 */
@Service
public class GeneratePdfService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JylsService jylsService;

    @Autowired
    private KplsService kplsService;

    @Autowired
    private PdfDocumentGenerator pdg;

    @Autowired
    private CszbService cszbService;

    @Autowired
    private DataOperate dc;

    @Autowired
    private SendalEmail se;

    @Autowired
    private SaveMessage saveMsg;

    @Autowired
    private GsxxService gsxxService;

    @Autowired
    private YjmbService yjmbService;
    @Autowired
    private JyxxsqService jyxxsqService;

    /**
     * 销方省份名称
     */
    private ConcurrentHashMap<Integer, String> xfSfMap = new ConcurrentHashMap<>();

    /**
     * 是否发送短信
     */
    private ConcurrentHashMap<Integer, Boolean> sffsdxMap = new ConcurrentHashMap<>();

    public void generatePdf(int kplsh) {
        Kpls kpls = kplsService.findOne(kplsh);
        int djh = kpls.getDjh();
        Jyls jyls = jylsService.findOne(djh);
        Map jyxxsqMap=new HashMap();
        jyxxsqMap.put("gsdm",kpls.getGsdm());
        jyxxsqMap.put("jylsh",jyls.getJylsh());
        Jyxxsq jyxxsq=jyxxsqService.findOneByJylsh(jyxxsqMap);
        try {
            int xfid = jyls.getXfid();
            String sfmc = xfSfMap.get(xfid);
            if (StringUtils.isBlank(sfmc)) {
                //获取省份名称
                sfmc = kplsService.findSfmcByXfid(xfid);
                xfSfMap.put(xfid, sfmc);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("SFMC", sfmc);
            map.put("FP_DM", kpls.getFpdm());
            map.put("FP_HM", kpls.getFphm());
            map.put("KPRQ", kpls.getKprq());
            map.put("JQBH", kpls.getSksbm());
            map.put("JYM", kpls.getJym());
            map.put("FP_MW", kpls.getMwq());
            map.put("EWM", kpls.getFpEwm());
            if (pdg.GeneratPDF(map, jyls, kpls)) {
                dc.updatePDFUrl(map, jyls, kpls);// 生成pdf的路径更新入库
                dc.updateFlag(jyls, "91");
                //发送email
                if ("1".equals(jyls.getSffsyj()) && jyls.getGfemail() != null && !"".equals(jyls.getGfemail())) {
                    Kpls ls = new Kpls();
                    ls.setDjh(djh);
                    List<Kpls> lslist = kplsService.findAllByKpls(ls);
                    List<String> pdfUrlList = new ArrayList<>();
                    for (Kpls kpls1 : lslist) {
                        pdfUrlList.add(kpls1.getPdfurl());
                    }
                    GetYjnr getYjnr = new GetYjnr();
                    Map gsxxmap=new HashMap();
                    gsxxmap.put("gsdm",kpls.getGsdm());
                    Gsxx gsxx=gsxxService.findOneByGsdm(gsxxmap);
                    Integer yjmbDm=gsxx.getYjmbDm();
                    Yjmb yjmb=yjmbService.findOne(yjmbDm);
                    String yjmbcontent=yjmb.getYjmbNr();
                    Map csmap=new HashMap();
                    csmap.put("ddh",jyls.getDdh());
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    csmap.put("ddrq",sdf.format(jyxxsq.getDdrq()));
                    csmap.put("pdfurls",pdfUrlList);
                    csmap.put("xfmc",jyls.getXfmc());
                    String content = getYjnr.getFpkjYj(csmap,yjmbcontent);
                    try {
                        se.sendEmail(String.valueOf(kpls.getDjh()), kpls.getGsdm(), kpls.getGfemail(), "发票开具成功发送邮件", String.valueOf(kpls.getDjh()), content, "电子发票");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("邮件发送失败" + e.getMessage());
                    }

                    //发送手机短信
                    Boolean sffsdx = sffsdxMap.get(jyls.getSkpid());
                    if (sffsdx == null) {
                        Cszb cszb = cszbService.getSpbmbbh(jyls.getGsdm(), jyls.getXfid(), jyls.getSkpid(), "sfktdx");
                        String dxfsFlag = cszb.getCsz();
                        if ("是".equals(dxfsFlag)) {
                            sffsdxMap.put(jyls.getSkpid(), true);
                            sffsdx = true;
                        } else {
                            sffsdxMap.put(jyls.getSkpid(), false);
                            sffsdx = false;
                        }
                    }
                    if (sffsdx) {
                        Cszb zb = cszbService.getSpbmbbh(jyls.getGsdm(), jyls.getXfid(), jyls.getSkpid(), "sfzdfs");
                        String zdfsFlag = zb.getCsz();
                        if ("是".equals(zdfsFlag)) {
                            String sjhm = jyls.getGfsjh();
                            Map<String, String> rep = new HashMap();
                            rep.put("jshj", jyls.getJshj() + "");
                            rep.put("tqm", jyls.getTqm());
                            if (sjhm != null && !"".equals(sjhm)) {
                                try {
                                    saveMsg.saveMessage(jyls.getGsdm(), djh, sjhm, rep, "SMS_34725005", "泰易电子发票");
                                    Map param3 = new HashMap<>();
                                    param3.put("djh", djh);
                                    param3.put("dxzt", '1');
                                    jylsService.updateDxbz(param3);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    System.out.println("发送短信失败" + e.getMessage());
                                }

                            }
                        } else {
                            Map param1 = new HashMap<>();
                            param1.put("djh", djh);
                            param1.put("dxzt", '2');
                            jylsService.updateDxbz(param1);
                        }
                    } else {
                        Map param2 = new HashMap<>();
                        param2.put("djh", djh);
                        param2.put("dxzt", '2');
                        jylsService.updateDxbz(param2);
                    }
                }
                dc.saveLog(djh, "91", "0", "正常开具", "", 1, jyls.getXfsh(), jyls.getJylsh());
            } else {
                dc.updateFlag(jyls, "92");
                dc.saveLog(djh, "21", "1", "PdfDocumentGenerator：GeneratPDF", "生成pdf失败,服务异常",
                        1, jyls.getXfsh(), jyls.getJylsh());
                dc.updateKplsFpzt(kpls, "开具成功，但生成pdf失败,服务异常", "05");
            }
        } catch (Exception e) {
            dc.updateFlag(jyls, "92");
            dc.saveLog(djh, "21", "1", "PdfDocumentGenerator：GeneratPDF", "生成pdf失败,服务异常",
                    1, jyls.getXfsh(), jyls.getJylsh());
            dc.updateKplsFpzt(kpls, "开具成功，但生成pdf失败,服务异常", "05");
            logger.error("生成pdf出现异常：" + kplsh, e);
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        GeneratePdfService generatePdfService= ApplicationContextUtils.getBean(GeneratePdfService.class);
        generatePdfService.generatePdf( 14688);
    }
}
