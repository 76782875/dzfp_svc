package com.rjxx.taxeasy.bizcomm.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import com.rjxx.taxeasy.domains.Ckhk;
import com.rjxx.taxeasy.domains.Dzfplog;
import com.rjxx.taxeasy.domains.Jyls;
import com.rjxx.taxeasy.domains.Jyspmx;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.domains.Kpspmx;
import com.rjxx.taxeasy.service.CkhkService;
import com.rjxx.taxeasy.service.DzfplogService;
import com.rjxx.taxeasy.service.JylsService;
import com.rjxx.taxeasy.service.JyspmxService;
import com.rjxx.taxeasy.service.KplsService;
import com.rjxx.taxeasy.service.KpspmxService;

import time.TimeUtil;



@Service
public class DataOperte {
	
	@Autowired
	private KplsService kplsService;
	@Autowired
	private KpspmxService kpspmxService;
	@Autowired
	private JylsService jylsService;
	@Autowired
	private JyspmxService jymxService;
	@Autowired
	private DzfplogService logService;
	@Autowired
	private CkhkService ckService;
	
     
	public void addJyls(List<Integer> kplshList, int djh, String fpczlxdm,int yhid) throws TransactionException {
		Map params = new HashMap<>();
		DecimalFormat df=new DecimalFormat("#.00");		
		Jyls jyls=null;
		for(Integer kplsh:kplshList){
			try{
				params.put("kplsh", kplsh);
				Kpls ts = kplsService.findOneByParams(params);
	            if ("12".equals(fpczlxdm)) {
	                params.put("hkbz", "0");//现在红冲不走此方法
	            } else if ("13".equals(fpczlxdm)) {
	            	params.put("hkbz", "1");
	            	params.put("fpztdm", "03");
	            }
	            kplsService.updateHkbz(params);
	            params.put("djh", djh);
	            Jyls old = jylsService.findJylsByDjh(params);
	            jyls = new Jyls();
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
	            jyls.setHsbz(old.getHsbz());
	            jyls.setYfpdm(ts.getFpdm());
	            jyls.setYfphm(ts.getFphm());
	            jyls.setYkpjshj(0d);
	            jyls.setYxbz("1");
	            jyls.setGsdm(ts.getGsdm());
	            jyls.setLrry(yhid);
	            jyls.setLrsj(TimeUtil.getNowDate());
	            jyls.setXgry(yhid);
	            jyls.setXgsj(TimeUtil.getNowDate());
	            jyls.setSkpid(ts.getSkpid());
	            jylsService.save(jyls);
	            List<Kpspmx> mxList = kpspmxService.findMxList(params);
	            djh = jyls.getDjh();
	            if(mxList !=null){
	            	for(int i=0;i<mxList.size();i++){
	            		Kpspmx mxItem = mxList.get(i);
	            		Jyspmx jyspmx = new Jyspmx();
	                    jyspmx.setDjh(jyls.getDjh());
	                    jyspmx.setSpmxxh(mxItem.getSpmxxh());
	                    jyspmx.setSpdm(mxItem.getSpdm());
	                    jyspmx.setSpmc(mxItem.getSpmc());
	                    jyspmx.setSps(mxItem.getSps()==null?null:-mxItem.getSps());
	                    jyspmx.setSpdj(mxItem.getSpdj());
	                    jyspmx.setSpdw(mxItem.getSpdw());
	                    jyspmx.setSpggxh(mxItem.getSpggxh());
	                    jyspmx.setFphxz(mxItem.getFphxz());
	                    jyspmx.setSpje(-mxItem.getSpje());
	                    jyspmx.setSpse(-mxItem.getSpse());
	                    jyspmx.setSpsl(mxItem.getSpsl());
	                    jyspmx.setJshj(Double.valueOf(df.format(jyspmx.getSpje() + jyspmx.getSpse())));
	                    jyspmx.setYkphj(0d);
	                    jyspmx.setGsdm(mxItem.getGsdm());
	                    jyspmx.setLrsj(TimeUtil.getNowDate());
	                    jyspmx.setLrry(yhid);
	                    jyspmx.setXgsj(TimeUtil.getNowDate());
	                    jyspmx.setXgry(yhid);
	                    jymxService.save(jyspmx);
	            	}
	            }
			}catch(Exception e){
				 updateFlag(jyls, "92");
		         saveLog(jyls.getDjh(), "92", "1", "DataOperate：addJyls",
		                    "(服务端)保存电子发票平台数据失败" + e.getMessage(), 2, jyls.getXfsh(), jyls.getJylsh());
			}
		}  				
	}
	
	/**
     * 更新t_jyls表 处理状态
     *
     * @param jyls
     * @param clztdm
     */
	@Transactional
	public void updateFlag(Jyls jyls, String clztdm) {
        try {
        	Map params = new HashMap<>();
        	params.put("clztdm", clztdm);
        	params.put("xgsj", TimeUtil.getNowDate());
        	params.put("djh", jyls.getDjh());
        	jylsService.updateClzt(params);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                updateFlag(jyls, "92");
                saveLog(jyls.getDjh(), "92", "1", "DataOperate：updateFlag",
                        "(服务端)更新jyls表中clztdm失败" + e.getMessage(), 2, jyls.getXfsh(), jyls.getJylsh());
            } catch (Exception e1) {
                throw new RuntimeException(e1);
            }
        }
    }
	
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
	@Transactional
    public void saveLog(int djh, String clztdm, String cljgdm,
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
        	logService.save(dl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	/**
	 * 发票重开时保存负数据到交易流水
	 * 
	 * */
	@Transactional
	public void updateKpls(List<Integer> kplshList,int djh,int yhid) throws Exception{
		Map params = new HashMap<>();
		params.put("djh", djh);
		DecimalFormat df=new DecimalFormat("#.00");
		Jyls old = jylsService.findJylsByDjh(params);
		Ckhk ck = ckService.findOneByParams(params);
		if(kplshList !=null && kplshList.size()>0){
			for(Integer kplsh:kplshList){
				params.put("kplsh", kplsh);
				Kpls kpls = kplsService.findOneByParams(params);				
				Jyls jyls = new Jyls();        
		        jyls.setDdh(old.getDdh());
		        String jylsh = "JY" + new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
		        jyls.setJylsh(jylsh);
		        jyls.setJylssj(TimeUtil.getNowDate());
		        jyls.setFpzldm("12");
		        jyls.setClztdm("01");
		        jyls.setFpczlxdm("12");
		        jyls.setXfdh(kpls.getXfdh());
		        jyls.setXfdz(kpls.getXfdz());
		        jyls.setXflxr(kpls.getXflxr());
		        jyls.setXfmc(kpls.getXfmc());
		        jyls.setXfsh(kpls.getXfsh());
		        jyls.setXfyb(kpls.getXfyb());
		        jyls.setXfyh(kpls.getXfyh());
		        jyls.setXfyhzh(kpls.getXfyhzh());
		        jyls.setGfdh(kpls.getGfdh());
		        jyls.setGfdz(kpls.getGfdz());
		        jyls.setGfemail(kpls.getGfemail());
		        jyls.setGflxr(kpls.getGflxr());
		        jyls.setGfmc(kpls.getGfmc());
		        jyls.setGfsh(kpls.getGfsh());
		        jyls.setGfyb(kpls.getGfyb());
		        jyls.setGfyh(kpls.getGfyh());
		        jyls.setGfyhzh(kpls.getGfyhzh());
		        jyls.setSsyf(kpls.getSsyf());
		        jyls.setKpr(kpls.getKpr());
		        jyls.setFhr(kpls.getFhr());
		        jyls.setSkr(kpls.getSkr());
		        jyls.setBz(kpls.getBz());
		        jyls.setJshj(-kpls.getJshj());
		        jyls.setHsbz(old.getHsbz());
		        jyls.setYfpdm(kpls.getFpdm());
		        jyls.setYfphm(kpls.getFphm());
		        jyls.setYkpjshj(0d);
		        jyls.setYxbz("1");
		        jyls.setGsdm(kpls.getGsdm());
		        jyls.setLrry(yhid);
		        jyls.setLrsj(TimeUtil.getNowDate());
		        jyls.setXgry(yhid);
		        jyls.setXgsj(TimeUtil.getNowDate());
		        jyls.setSkpid(kpls.getSkpid());
		        jyls.setTqm(old.getTqm());
		        jylsService.save(jyls);
		        List<Kpspmx> mxList = kpspmxService.findMxList(params);
		        djh = jyls.getDjh();
		        if(mxList !=null){
		        	for(int i=0;i<mxList.size();i++){
		        		Kpspmx mxItem = mxList.get(i);
		        		Jyspmx jyspmx = new Jyspmx();
		                jyspmx.setDjh(jyls.getDjh());
		                jyspmx.setSpmxxh(mxItem.getSpmxxh());
		                jyspmx.setSpdm(mxItem.getSpdm());
		                jyspmx.setSpmc(mxItem.getSpmc());
		                jyspmx.setSps(mxItem.getSps()==null?null:-mxItem.getSps());
		                jyspmx.setSpdj(mxItem.getSpdj());
		                jyspmx.setSpdw(mxItem.getSpdw());
		                jyspmx.setSpggxh(mxItem.getSpggxh());
		                jyspmx.setFphxz(mxItem.getFphxz());
		                jyspmx.setSpje(-mxItem.getSpje());
		                jyspmx.setSpse(-mxItem.getSpse());
		                jyspmx.setSpsl(mxItem.getSpsl());
		                jyspmx.setJshj(Double.valueOf(df.format(jyspmx.getSpje() + jyspmx.getSpse())));
		                jyspmx.setYkphj(0d);
		                jyspmx.setGsdm(mxItem.getGsdm());
		                jyspmx.setLrsj(TimeUtil.getNowDate());
		                jyspmx.setLrry(yhid);
		                jyspmx.setXgsj(TimeUtil.getNowDate());
		                jyspmx.setXgry(yhid);
		                jymxService.save(jyspmx);
		        	}
		        }
		        //保存负交易流水结束后修改开票流水表状态
		        Map param2 = new HashMap<>();
				param2.put("kplsh", kplsh);
				param2.put("fpztdm", "06");  //06重新开具
				kplsService.updateFpczlx(param2);
				//重新保存一笔新的交易流水				
				Jyls ls = new Jyls();
				ls.setDdh(old.getDdh());
		        jylsh = "JY" + new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
		        ls.setJylsh(jylsh);
		        ls.setJylssj(TimeUtil.getNowDate());
		        ls.setFpzldm("12");
		        ls.setClztdm("01");
		        ls.setFpczlxdm("11");
		        ls.setXfdh(kpls.getXfdh());
		        ls.setXfdz(kpls.getXfdz());
		        ls.setXflxr(kpls.getXflxr());
		        ls.setXfmc(kpls.getXfmc());
		        ls.setXfsh(kpls.getXfsh());
		        ls.setXfyb(kpls.getXfyb());
		        ls.setXfyh(kpls.getXfyh());
		        ls.setXfyhzh(kpls.getXfyhzh());
		        ls.setGfdh(ck.getGfdh());
		        ls.setGfdz(ck.getGfdz());
		        ls.setGfemail(ck.getGfyx());
		        ls.setGfmc(ck.getGfmc());
		        ls.setGfsh(ck.getGfsh());
		        ls.setGfyb(kpls.getGfyb());
		        ls.setGfyh(ck.getGfyh());
		        ls.setGfyhzh(ck.getGfyhzh());
		        ls.setSsyf(kpls.getSsyf());
		        ls.setKpr(kpls.getKpr());
		        ls.setFhr(kpls.getFhr());
		        ls.setSkr(kpls.getSkr());
		        ls.setBz(kpls.getBz());
		        ls.setJshj(kpls.getJshj());
		        ls.setHsbz(old.getHsbz());        
		        ls.setYkpjshj(0d);
		        ls.setYxbz("1");
		        ls.setGsdm(kpls.getGsdm());
		        ls.setLrry(yhid);
		        ls.setLrsj(TimeUtil.getNowDate());
		        ls.setXgry(yhid);
		        ls.setXgsj(TimeUtil.getNowDate());
		        ls.setSkpid(kpls.getSkpid());
		        ls.setTqm(old.getTqm());
		        jylsService.save(ls);
		        mxList = kpspmxService.findMxList(params);
		        djh = ls.getDjh();
		        if(mxList !=null){
		        	for(int i=0;i<mxList.size();i++){
		        		Kpspmx mxItem = mxList.get(i);
		        		Jyspmx jyspmx = new Jyspmx();
		                jyspmx.setDjh(djh);
		                jyspmx.setSpmxxh(mxItem.getSpmxxh());
		                jyspmx.setSpdm(mxItem.getSpdm());
		                jyspmx.setSpmc(mxItem.getSpmc());
		                jyspmx.setSps(mxItem.getSps());
		                jyspmx.setSpdj(mxItem.getSpdj());
		                jyspmx.setSpdw(mxItem.getSpdw());
		                jyspmx.setSpggxh(mxItem.getSpggxh());
		                jyspmx.setFphxz(mxItem.getFphxz());
		                jyspmx.setSpje(mxItem.getSpje());
		                jyspmx.setSpse(mxItem.getSpse());
		                jyspmx.setSpsl(mxItem.getSpsl());
		                jyspmx.setJshj(Double.valueOf(df.format(jyspmx.getSpje() + jyspmx.getSpse())));
		                jyspmx.setYkphj(0d);
		                jyspmx.setGsdm(mxItem.getGsdm());
		                jyspmx.setLrsj(TimeUtil.getNowDate());
		                jyspmx.setLrry(yhid);
		                jyspmx.setXgsj(TimeUtil.getNowDate());
		                jyspmx.setXgry(yhid);
		                jymxService.save(jyspmx);
		        	}
		        }
			}
		}		
        //更新申请表的状态
        params.put("id", ck.getId());
        params.put("ztbz","1");
        ckService.updateZtbz(params);
	}
	
	
	/**
	 * 开具失败的发票重开操作
	 * 
	 * */
	@Transactional
	public void ckSaveJyls(int kplsh,int djh,int yhid) throws Exception{
		Map params = new HashMap<>();
		DecimalFormat df=new DecimalFormat("#.00");
		params.put("kplsh", kplsh);
		Kpls kpls = kplsService.findOneByParams(params);
		params.put("djh", djh);
		Jyls jyls = new Jyls();
        Jyls old = jylsService.findJylsByDjh(params);
        /*jyls.setDdh(old.getDdh());
        String jylsh = "JY" + new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
        jyls.setJylsh(jylsh);
        jyls.setJylssj(TimeUtil.getNowDate());
        jyls.setFpzldm("12");
        jyls.setClztdm("01");
        jyls.setFpczlxdm("12");
        jyls.setXfdh(kpls.getXfdh());
        jyls.setXfdz(kpls.getXfdz());
        jyls.setXflxr(kpls.getXflxr());
        jyls.setXfmc(kpls.getXfmc());
        jyls.setXfsh(kpls.getXfsh());
        jyls.setXfyb(kpls.getXfyb());
        jyls.setXfyh(kpls.getXfyh());
        jyls.setXfyhzh(kpls.getXfyhzh());
        jyls.setGfdh(kpls.getGfdh());
        jyls.setGfdz(kpls.getGfdz());
        jyls.setGfemail(kpls.getGfemail());
        jyls.setGflxr(kpls.getGflxr());
        jyls.setGfmc(kpls.getGfmc());
        jyls.setGfsh(kpls.getGfsh());
        jyls.setGfyb(kpls.getGfyb());
        jyls.setGfyh(kpls.getGfyh());
        jyls.setGfyhzh(kpls.getGfyhzh());
        jyls.setSsyf(kpls.getSsyf());
        jyls.setKpr(kpls.getKpr());
        jyls.setFhr(kpls.getFhr());
        jyls.setSkr(kpls.getSkr());
        jyls.setBz(kpls.getBz());
        jyls.setJshj(-kpls.getJshj());
        jyls.setHsbz(old.getHsbz());
        jyls.setYfpdm(kpls.getFpdm());
        jyls.setYfphm(kpls.getFphm());
        jyls.setYkpjshj(0d);
        jyls.setYxbz("1");
        jyls.setGsdm(kpls.getGsdm());
        jyls.setLrry(yhid);
        jyls.setLrsj(TimeUtil.getNowDate());
        jyls.setXgry(yhid);
        jyls.setXgsj(TimeUtil.getNowDate());
        jyls.setSkpid(kpls.getSkpid());
        jylsService.save(jyls);
        List<Kpspmx> mxList = kpspmxService.findMxList(params);
        djh = jyls.getDjh();
        if(mxList !=null){
        	for(int i=0;i<mxList.size();i++){
        		Kpspmx mxItem = mxList.get(i);
        		Jyspmx jyspmx = new Jyspmx();
                jyspmx.setDjh(jyls.getDjh());
                jyspmx.setSpmxxh(mxItem.getSpmxxh());
                jyspmx.setSpdm(mxItem.getSpdm());
                jyspmx.setSpmc(mxItem.getSpmc());
                jyspmx.setSps(mxItem.getSps()==null?null:-mxItem.getSps());
                jyspmx.setSpdj(mxItem.getSpdj());
                jyspmx.setSpdw(mxItem.getSpdw());
                jyspmx.setSpggxh(mxItem.getSpggxh());
                jyspmx.setFphxz(mxItem.getFphxz());
                jyspmx.setSpje(-mxItem.getSpje());
                jyspmx.setSpse(-mxItem.getSpse());
                jyspmx.setSpsl(mxItem.getSpsl());
                jyspmx.setJshj(Double.valueOf(df.format(jyspmx.getSpje() + jyspmx.getSpse())));
                jyspmx.setYkphj(0d);
                jyspmx.setGsdm(mxItem.getGsdm());
                jyspmx.setLrsj(TimeUtil.getNowDate());
                jyspmx.setLrry(yhid);
                jyspmx.setXgsj(TimeUtil.getNowDate());
                jyspmx.setXgry(yhid);
                jymxService.save(jyspmx);
        	}
        }*/       
		//重新保存一笔新的交易流水
		//Ckhk ck = ckService.findOneByParams(params);
		Jyls ls = new Jyls();
		ls.setDdh(old.getDdh());
        String jylsh = "JY" + new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
        ls.setJylsh(jylsh);
        ls.setJylssj(TimeUtil.getNowDate());
        ls.setFpzldm("12");
        ls.setClztdm("01");
        ls.setFpczlxdm(kpls.getFpczlxdm());
        ls.setXfdh(kpls.getXfdh());
        ls.setXfdz(kpls.getXfdz());
        ls.setXflxr(kpls.getXflxr());
        ls.setXfid(kpls.getXfid());
        ls.setXfmc(kpls.getXfmc());
        ls.setXfsh(kpls.getXfsh());
        ls.setXfyb(kpls.getXfyb());
        ls.setXfyh(kpls.getXfyh());
        ls.setXfyhzh(kpls.getXfyhzh());
        ls.setGfdh(kpls.getGfdh());
        ls.setGfdz(kpls.getGfdz());
        ls.setGfemail(kpls.getGfemail());
        ls.setGfmc(kpls.getGfmc());
        ls.setGfsh(kpls.getGfsh());
        ls.setGfyb(kpls.getGfyb());
        ls.setGfyh(kpls.getGfyh());
        ls.setGfyhzh(kpls.getGfyhzh());
        ls.setSsyf(kpls.getSsyf());
        ls.setKpr(kpls.getKpr());
        ls.setFhr(kpls.getFhr());
        ls.setSkr(kpls.getSkr());
        ls.setBz(kpls.getBz());
        ls.setJshj(kpls.getJshj());
        ls.setHsbz(old.getHsbz());        
        ls.setYkpjshj(0d);
        ls.setYxbz("1");
        ls.setGsdm(kpls.getGsdm());
        ls.setLrry(yhid);
        ls.setLrsj(TimeUtil.getNowDate());
        ls.setXgry(yhid);
        ls.setXgsj(TimeUtil.getNowDate());
        ls.setSkpid(kpls.getSkpid());
        ls.setTqm(old.getTqm());
        jylsService.save(ls);
        List<Kpspmx> mxList = kpspmxService.findMxList(params);
        djh = ls.getDjh();
        if(mxList !=null){
        	for(int i=0;i<mxList.size();i++){
        		Kpspmx mxItem = mxList.get(i);
        		Jyspmx jyspmx = new Jyspmx();
                jyspmx.setDjh(djh);
                jyspmx.setSpmxxh(mxItem.getSpmxxh());
                jyspmx.setSpdm(mxItem.getSpdm());
                jyspmx.setSpmc(mxItem.getSpmc());
                jyspmx.setSps(mxItem.getSps());
                jyspmx.setSpdj(mxItem.getSpdj());
                jyspmx.setSpdw(mxItem.getSpdw());
                jyspmx.setSpggxh(mxItem.getSpggxh());
                jyspmx.setFphxz(mxItem.getFphxz());
                jyspmx.setSpje(mxItem.getSpje());
                jyspmx.setSpse(mxItem.getSpse());
                jyspmx.setSpsl(mxItem.getSpsl());
                jyspmx.setJshj(Double.valueOf(df.format(jyspmx.getSpje() + jyspmx.getSpse())));
                jyspmx.setYkphj(0d);
                jyspmx.setGsdm(mxItem.getGsdm());
                jyspmx.setLrsj(TimeUtil.getNowDate());
                jyspmx.setLrry(yhid);
                jyspmx.setXgsj(TimeUtil.getNowDate());
                jyspmx.setXgry(yhid);
                jymxService.save(jyspmx);
        	}
        }
      //保存负交易流水结束后修改开票流水表状态
        Map param2 = new HashMap<>();
		param2.put("kplsh", kplsh);
		param2.put("fpztdm", "06");  //06重新开具
		kplsService.updateFpczlx(param2); 
	}
	
}
