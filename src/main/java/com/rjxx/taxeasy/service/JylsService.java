package com.rjxx.taxeasy.service;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.JylsJpaDao;
import com.rjxx.taxeasy.dao.JylsMapper;
import com.rjxx.taxeasy.domains.Jyls;
import com.rjxx.taxeasy.domains.Jyspmx;
import com.rjxx.taxeasy.vo.Fptqvo;
import com.rjxx.taxeasy.vo.YjfsxxVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 10 13:24:59 CST 2016
 *
 * @ZhangBing
 */
@Service
public class JylsService {

    @Autowired
    private JylsJpaDao jylsJpaDao;

    @Autowired
    private JylsMapper jylsMapper;

    @Autowired
    private JyspmxService jyspmxService;

    public Jyls findOne(int id) {
        return jylsJpaDao.findOne(id);
    }

    public void save(Jyls jyls) {
        jylsJpaDao.save(jyls);
    }

    public void save(List<Jyls> jylsList) {
        jylsJpaDao.save(jylsList);
    }

    public Jyls findOneByParams(Jyls jyls) {
        return jylsMapper.findOneByParams(jyls);
    }

    public List<Jyls> findAllByParams(Jyls jyls) {
        return jylsMapper.findAllByParams(jyls);
    }

    public List<Jyls> findByPage(Pagination pagination) {
        return jylsMapper.findByPage(pagination);
    }

    public List<Jyls> findByIdList(List<Integer> idList) {
        return (List<Jyls>) jylsJpaDao.findAll(idList);
    }
    public List<Jyls> findBySql(){
    	return jylsMapper.findBySql();
    }
    public List<Jyls> findByMapParams(Map params) {
        return jylsMapper.findByMapParams(params);
    }
    
    public void updateClzt(Map params){
    	jylsMapper.updateClzt(params);
    }
    
    public List<Fptqvo> fptqcx(Pagination pagination){
    	return jylsMapper.fptqcx(pagination);
    }
    /**
     * 根据提取码查找
     *
     * @param tqm
     * @param gsdm
     * @return
     */
    public Jyls findByTqm(String tqm, String gsdm) {
        Jyls params = new Jyls();
        params.setTqm(tqm);
        params.setGsdm(gsdm);
        return findOneByParams(params);
    }
    
    public List<Jyls> findAll(Map params){
    	return jylsMapper.findAll(params);
    }

    /**
     * 删除交易流水，包括明细
     *
     * @param djhList
     */
    @Transactional
    public void delByDjhList(List<Integer> djhList) {
        Iterable<Jyls> jylsIterable = jylsJpaDao.findAll(djhList);
        jylsJpaDao.delete(jylsIterable);
        List<Jyspmx> jyspmxList = jyspmxService.findByDjhList(djhList);
        jyspmxService.delete(jyspmxList);
    }

    /**
     * 交易流水与明细一对一
     *
     * @param jylsList
     * @param jyspmxList
     */
    @Transactional
    public void saveAll(List<Jyls> jylsList, List<Jyspmx> jyspmxList) {
        jylsJpaDao.save(jylsList);
        int i = 0;
        for (Jyspmx jyspmx : jyspmxList) {
            jyspmx.setDjh(jylsList.get(i).getDjh());
            i++;
        }
        jyspmxService.save(jyspmxList);
    }

    /**
     * 保存交易流水
     *
     * @param jyls
     * @param jyspmxList
     */
    @Transactional
    public void saveJyls(Jyls jyls, List<Jyspmx> jyspmxList) {
        save(jyls);
        int djh = jyls.getDjh();
        for (Jyspmx jyspmx : jyspmxList) {
            jyspmx.setDjh(djh);
        }
        jyspmxService.save(jyspmxList);
    }

    /**
     * 更新交易流水状态
     *
     * @param djhList
     * @param clztdm
     */
    public void updateJylsClzt(List<Integer> djhList, String clztdm) {
        List<Jyls> jylsList = findByIdList(djhList);
        for (Jyls jyls : jylsList) {
            jyls.setClztdm(clztdm);
            jyls.setXgsj(new Date());
        }
        save(jylsList);
    }
    //根据单据号查询jyls
    public Jyls findJylsByDjh(Map params){
    	return jylsMapper.findJylsByDjh(params);
    }
    public List<YjfsxxVo> findYjfs(Map params){
    	return jylsMapper.findYjfs(params);
    }

}

