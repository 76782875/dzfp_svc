package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rjxx.taxeasy.domains.Dzfplog;
import com.rjxx.taxeasy.domains.Jyls;
import com.rjxx.taxeasy.domains.Jyspmx;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.domains.Kpspmx;
import com.rjxx.taxeasy.domains.Skp;
import com.rjxx.taxeasy.domains.Xf;
import com.rjxx.taxeasy.service.DzfplogService;
import com.rjxx.taxeasy.service.JylsService;
import com.rjxx.taxeasy.service.JyspmxService;
import com.rjxx.taxeasy.service.KplsService;
import com.rjxx.taxeasy.service.KpspmxService;
import com.rjxx.taxeasy.service.SkpService;
import com.rjxx.taxeasy.service.XfService;
import com.rjxx.taxeasy.vo.JyspmxDecimal;

import time.TimeUtil;


/**
 * @author k
 */
@Service
public class DataOperate {

	@Autowired
	private static DzfplogService logService;
	@Autowired
	private static JylsService jylsService;
	@Autowired
	private static JyspmxService jyspmxService;
	@Autowired
	private static KpspmxService kpspmxSerivce;
	@Autowired
	private static KplsService kplsSerivce;
	@Autowired
	private static XfService xfSerivce;
	@Autowired
	private static SkpService skpService;

    /**
     * 保存日志记录
     *
     * @param djh
     * @param clztdm
     * @param cljgdm
     * @param ffcs
     * @param ycms
     * @param lrry
     */
    public static void saveLog(int djh, String clztdm, String cljgdm,
                               String ffcs, String ycms, int lrry, String xfsh, String jylsh) {

        Dzfplog dl = new Dzfplog();
        dl.setDjh(djh);
        dl.setClztdm(clztdm);
        dl.setCljgdm(cljgdm);
        dl.setFfcs(ffcs);
        dl.setYcms(ycms);
        dl.setLrsj(TimeUtil.getNowDate());
        dl.setLrry(lrry);
        dl.setXfsh(xfsh);
        dl.setJylsh(jylsh);
        try {
            logService.save(dl);;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到需要开具发票的数据 条件：有效标志=1 ;发票种类代码=电子发票=1% ;发票操作类型代码=发票开具=11； 价税合计-已开票价税合计>0;
     * 处理状态=数据已存库=01.
     *
     * @return
     */
    public static List<Jyls> getNeedToKP() {
        List<Jyls> jylss = null;
        String sql = "SELECT * FROM t_jyls t WHERE t.yxbz = '1' AND t.fpczlxdm LIKE '1%'  AND "
                + "t.fpzldm = '12' AND t.jshj - IFNULL(t.ykpjshj,0) <> 0 and t.clztdm = '01' order by djh";
        try {
            jylss = jylsService.findAll(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jylss;
    }

    // 明细表
    public static List<JyspmxDecimal> getNeedToKP2(Jyls jyls) {
        List<JyspmxDecimal> jyspmxs = null;
//        String sql = "SELECT * FROM t_jyspmx t WHERE t.djh= " + jyls.getDjh() + " and t.jshj - IFNULL(t.ykphj,0) <> 0 order by spmxxh";
        try {
        	Map<String, Object> params = new HashMap<>();
        	params.put("djh", jyls.getDjh());
            jyspmxs = jyspmxService.getNeedToKP2(params);
        } catch (Exception e) {
            e.printStackTrace();
            DataOperate.updateFlag(jyls, "92");
            DataOperate.saveLog(jyls.getDjh(), "92", "1", "DataOperate：getNeedToKP2",
                    "(服务端)获取djh=" + jyls.getDjh() + "的明细数据失败,明细条数=" + jyspmxs.size(), 2, jyls.getXfsh(), jyls.getJylsh());
        }
        return jyspmxs;

    }

    /**
     * pdf所需明细数据
     *
     * @param kplsh
     * @return
     */
    public static List<Kpspmx> getPDFSpmx(int kplsh) {
        List<Kpspmx> t_kpspmxes = null;
//        String sql = "SELECT * FROM t_kpspmx t WHERE t.kplsh= " +
//                kplsh + " order by t.spmxxh";
        try {
        	Map<String, Object> params = new HashMap<>();
        	params.put("kplsh", kplsh);
            t_kpspmxes = kpspmxSerivce.findMxList(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t_kpspmxes;

    }

    /**
     * 更新t_jyls表 处理状态
     *
     * @param jyls
     * @param clztdm
     */
    public static void updateFlag(Jyls jyls, String clztdm) {
        try {
            jyls.setClztdm(clztdm);
            jyls.setXgsj(TimeUtil.getNowDate());
            jylsService.save(jyls);;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                DataOperate.updateFlag(jyls, "92");
                DataOperate.saveLog(jyls.getDjh(), "92", "1", "DataOperate：updateFlag",
                        "(服务端)更新jyls表中clztdm失败" + e.getMessage(), 2, jyls.getXfsh(), jyls.getJylsh());
            } catch (Exception e1) {
                throw new RuntimeException(e1);
            }
        }
    }

    /**
     * 更新jyls表及明细表的ykjshj
     *
     * @param jyls
     * @throws Exception
     * @throws TransactionException
     */
    @Transactional
    public static void updateYkpjshj(Jyls jyls) {
        try {
            jyls.setYkpjshj(jyls.getJshj());
            jyls.setXgry(2);
            jyls.setXgsj(TimeUtil.getNowDate());
            jylsService.save(jyls);;
            Jyspmx spmx = new Jyspmx();
            spmx.setDjh(jyls.getDjh());
            List<Jyspmx> mxLists = jyspmxService.findAllByParams(spmx);

            for (int i = 0; i < mxLists.size(); i++) {
                Jyspmx mx = mxLists.get(i);
                mx.setYkphj(mx.getJshj());
                mx.setXgsj(TimeUtil.getNowDate());
                mx.setXgry(2);
                jyspmxService.save(mx);
//                mx.execute("update t_jyspmx set ykphj=?,xgsj=?,xgry=? where djh=" + jyls.getDjh() + " and spmxxh=" + mx.getSpmxxh(),
//                        new Object[]{mx.getJshj(), TimeUtil.getNowDate(), 2});

            }
        } catch (Exception e) {
            e.printStackTrace();
            DataOperate.updateFlag(jyls, "92");
            DataOperate.saveLog(jyls.getDjh(), "92", "1", "DataOperate：updateYkpjshj",
                    "(服务端)开票成功后更新jyls及jyspmx表'已开票价税合计'失败" + e.getMessage(), 2, jyls.getXfsh(), jyls.getJylsh());
            
        }
    }

    /**
     * 更新kpls表中pdf路径
     *
     * @param map
     * @param jyls
     * @param kpls
     * @param map
     */
    public static void updatePDFUrl(Map<String, Object> map, Jyls jyls, Kpls kpls) {
        try {
            kpls.setPdfurl((String) map.get("outputFile"));
            kpls.setQmc((String) map.get("signData"));
            //20161123 kzx 修改更新二维码字段 begin
            //kpls.setEwm((String) map.get("qrcode"));
            kpls.setEwm((String) map.get("qrcode"));
            //20161123 kzx 修改更新二维码字段 end
            kpls.setXgsj(TimeUtil.getNowDate());
            kplsSerivce.save(kpls);
        } catch (Exception e) {
            e.printStackTrace();
            // DataOperate.updateFlag(jyls, "92");
            DataOperate.saveLog(jyls.getDjh(), jyls.getClztdm(), "1", "DataOperate：updatePDFUrl",
                    "(服务端)开票成功后更新kpls表中的pdfurl和签名值失败" + e.getMessage(), jyls.getXgry(), jyls.getXfsh(), jyls.getJylsh());
        }
    }

    /**
     * 红冲/换开 更新蓝字kpls表中hzyfpdm、hzyfphm
     *
     * @param hcKpls
     * @throws Exception
     */
    public static void updateHc(Kpls hcKpls) throws Exception {
        //kpls:蓝字开票流水
    	Map<String, Object> params = new HashMap<>();
    	params.put("fphm", hcKpls.getFphm());
    	params.put("fpdm", hcKpls.getFpdm());
        Kpls kpls = kplsSerivce.findFpExist(params).get(0);
        try {
            kpls.setHzyfpdm(hcKpls.getFpdm());
            kpls.setHzyfphm(hcKpls.getFphm());
            if ("12".equals(hcKpls.getFpczlxdm())) {
                kpls.setHkbz("0");
            } else if ("13".equals(hcKpls.getFpczlxdm())) {
                kpls.setHkbz("1");
            }
            kpls.setXgsj(TimeUtil.getNowDate());
            kplsSerivce.save(kpls);
        } catch (Exception e) {
            e.printStackTrace();
            // DataOperate.updateFlag(jyls, "92");
            DataOperate.saveLog(kpls.getDjh(), "92", "1", "DataOperate：updateHc",
                    "(服务端)开票成功后更新蓝字kpspmx表'红冲日期'失败" + e.getMessage(), 2/*jyls.getXfsh(),jyls.getJylsh()*/, null, null);
        }

    }

    /**
     * 在t_kpls及t_kpspmx 表中增加记录
     *
     * @param map
     * @param jyls
     * @param kpls
     * @throws TransactionException
     */
    public static void updateKpls(Map<String, Object> map, Jyls jyls, Kpls kpls) {
        // 保存已开发票结果主表
        try {
            String fpdm = (String) map.get("FP_DM");
            String fphm = (String) map.get("FP_HM");
            String czlx = kpls.getFpczlxdm();
            kpls.setFpdm(fpdm);
            kpls.setFphm(fphm);
            if ("13".equals(czlx)) {
                kpls.setHkbz("1");
            } else {
                kpls.setHkbz("0");
            }
            //kpls.setEwm((String) map.get("EWM"));
            kpls.setSksbm((String) map.get("JQBH"));
            kpls.setMwq((String) map.get("FP_MW"));
            kpls.setJym((String) map.get("JYM"));
            String kprq = (String) map.get("KPRQ");
            kpls.setKprq(TimeUtil.getSysDateInDate(kprq, null));
            kpls.setFpztdm("00");
            kplsSerivce.save(kpls);
        } catch (Exception e) {
            e.printStackTrace();
            DataOperate.updateFlag(jyls, "92");
            DataOperate.saveLog(kpls.getDjh(), "92", "1", "DataOperate：addKpls",
                    "(服务端)解析out队列后数据入库失败" + e.getMessage(), 2, jyls.getXfsh(), jyls.getJylsh());
        }                

    }
    
    /**
     * kpls修改fpzt及错误原因
     * 
     * */
    public static void updateKplsFpzt(Kpls kpls,String errorMsg,String fpztdm) {
    	kpls.setFpztdm(fpztdm);
    	kpls.setErrorReason(errorMsg);
    	try {
			kplsSerivce.save(kpls);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    /**
     * 换开 更新kpls表中hkbz
     *
     * @param kplsh
     */
    //在发送换开请求时更新
    public static void updateHk(String kplsh, int yhid) {
        try {
        	Map<String, Object> params = new HashMap<>();
        	params.put("kplsh", kplsh);
            Kpls kpls = kplsSerivce.findOneByParams(params);
            kpls.setHkbz("1");
            kpls.setXgry(yhid);
            kpls.setXgsj(TimeUtil.getNowDate());
            kplsSerivce.save(kpls);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过xfmc来从t_kpd表中获取信息
     *
     * @param xfmc
     * @return
     */
    public static Skp getKpd(String xfmc) {
        Skp skp = new Skp();
        try {
        	Xf xf = new Xf();
        	xf.setXfmc(xfmc);
        	int id = xfSerivce.findOneByParams(xf).getId();
        	Skp params = new Skp();
        	params.setXfid(id);
        	skp = skpService.findOneByParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skp;

    }

    /**
     * 保存红冲或换开请求数据
     *
     * @param djh
     * @return
     */
    @Transactional
    public static void addJyls(int djh, String fpczlxdm, String fpdm, String fphm, int yhid) {
        // 保存待开发票结果主表
        Jyls jyls = new Jyls();
        try {
        	Map<String, Object> params = new HashMap<>();
        	params.put("fpdm", fpdm);
        	params.put("fphm", fphm);
            List<Kpls> kplsList = kplsSerivce.findFpExist(params);
            if (kplsList == null || kplsList.isEmpty()) {
                throw new Exception(fphm + "," + fphm + "对应的记录不存在");
            }
            Kpls ts = kplsList.get(0);
            int kplsh = ts.getKplsh();
            if ("12".equals(fpczlxdm)) {
                ts.setHkbz("0");
            } else if ("13".equals(fpczlxdm)) {
                ts.setHkbz("1");
            }
            kplsSerivce.save(ts);
            Jyls old = jylsService.findOne(djh);
            jyls.setDdh(old.getDdh());
            String jylsh = "JY" + new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
            jyls.setJylsh(jylsh);
            jyls.setJylssj(TimeUtil.getNowDate());
            jyls.setFpzldm("12");
            jyls.setClztdm("01");
            jyls.setFpczlxdm(fpczlxdm);
            jyls.setXfdh(ts.getXfdh());
            jyls.setXfdz(ts.getXfdz());
            jyls.setXflxr(ts.getXflxr());
            jyls.setXfmc(ts.getXfmc());
            jyls.setXfsh(ts.getXfsh());
            jyls.setXfyb(ts.getXfyb());
            jyls.setXfyh(ts.getXfyh());
            jyls.setXfyhzh(ts.getXfyhzh());

            jyls.setGfdh(ts.getGfdh());
            jyls.setGfdz(ts.getGfdz());
            jyls.setGfemail(ts.getGfemail());
            jyls.setGflxr(ts.getGflxr());
            jyls.setGfmc(ts.getGfmc());
            jyls.setGfsh(ts.getGfsh());
            jyls.setGfyb(ts.getGfyb());
            jyls.setGfyh(ts.getGfyh());
            jyls.setGfyhzh(ts.getGfyhzh());
            jyls.setSsyf(ts.getSsyf());
            jyls.setKpr(ts.getKpr());
            jyls.setFhr(ts.getFhr());
            jyls.setSkr(ts.getSkr());
            jyls.setBz(ts.getBz());
            jyls.setJshj(-ts.getJshj());
            jyls.setHsbz("0");
            jyls.setYfpdm(fpdm);
            jyls.setYfphm(fphm);
            jyls.setYkpjshj(0d);
            jyls.setYxbz("1");
            jyls.setGsdm(ts.getGsdm());
            jyls.setLrry(yhid);
            jyls.setLrsj(TimeUtil.getNowDate());
            jyls.setXgry(yhid);
            jyls.setXgsj(TimeUtil.getNowDate());
            jylsService.save(jyls);;
            Jyspmx jyspmx;
            params = new HashMap<>();
            params.put("kplsh", kplsh);
            List<Kpspmx> kpspmxes = kpspmxSerivce.findMxList(params);
            for (int i = 0; i < kpspmxes.size(); i++) {
                Kpspmx tx = kpspmxes.get(i);
                jyspmx = new Jyspmx();
                jyspmx.setDjh(jyls.getDjh());
                jyspmx.setSpmxxh(tx.getSpmxxh());
                jyspmx.setSpdm(tx.getSpdm());
                jyspmx.setSpmc(tx.getSpmc());
                jyspmx.setSps(tx.getSps()==null?null:-tx.getSps());
                jyspmx.setSpdj(tx.getSpdj());
                jyspmx.setSpdw(tx.getSpdw());
                jyspmx.setSpggxh(tx.getSpggxh());
                jyspmx.setFphxz(tx.getFphxz());
                jyspmx.setSpje(-tx.getSpje());
                jyspmx.setSpse(-tx.getSpse());
                jyspmx.setSpsl(tx.getSpsl());
                jyspmx.setJshj(jyspmx.getSpje() + jyspmx.getSpse());
                jyspmx.setYkphj(0d);
                jyspmx.setGsdm(tx.getGsdm());
                jyspmx.setLrsj(TimeUtil.getNowDate());
                jyspmx.setLrry(yhid);
                jyspmx.setXgsj(TimeUtil.getNowDate());
                jyspmx.setXgry(yhid);
                jyspmxService.save(jyspmx);
            }
        } catch (Exception e) {
            DataOperate.updateFlag(jyls, "92");
            DataOperate.saveLog(jyls.getDjh(), "92", "1", "DataOperate：addJyls",
                    "(服务端)保存电子发票平台数据失败" + e.getMessage(), 2, jyls.getXfsh(), jyls.getJylsh());
           

        }
    }
}
