package com.rjxx.taxeasy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.KplsJpaDao;
import com.rjxx.taxeasy.dao.KplsMapper;
import com.rjxx.taxeasy.domains.Jyls;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.vo.Fpcxvo;
import com.rjxx.taxeasy.vo.KplsVO3;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 17 14:37:57 GMT+08:00 2016
 *
 * @ZhangBing
 */
@Service
public class KplsService {

    @Autowired
    private KplsJpaDao kplsJpaDao;

    @Autowired
    private KplsMapper kplsMapper;

    public Kpls findOne(int id) {
        return kplsJpaDao.findOne(id);
    }

    public List<Kpls> findFpExist(Map params) {
        return kplsMapper.findFpExist(params);
    }

    public void save(Kpls kpls) {
        kplsJpaDao.save(kpls);
    }

    public void save(List<Kpls> kplsList) {
        kplsJpaDao.save(kplsList);
    }

    public Kpls findOneByParams(Map params) {
        return kplsMapper.findOneByParams(params);
    }

    public List<Fpcxvo> findAllByParams(Map params) {
        return kplsMapper.findAllByParams(params);
    }

    public List<Fpcxvo> findByPage(Pagination pagination) {
        return kplsMapper.findByPage(pagination);
    }

    public void update(Map params) {
        kplsMapper.update(params);
    }

    //更新换开标志
    public void updateHkbz(Map params) {
        kplsMapper.updateHkbz(params);
    }

    public List<Kpls> printSingle(Map params) {
        return kplsMapper.printSingle(params);
    }

    //批量打印查询
    public List<Fpcxvo> printmany(Map params) {
        return kplsMapper.printmany(params);
    }

    //发票红冲数据查询
    public List<Fpcxvo> findKhcfpByPage(Pagination pagination) {
        return kplsMapper.findKhcfpByPage(pagination);
    }

    //红冲后更新发票状态
    public void updateFpczlx(Map params) {
        kplsMapper.updateFpczlx(params);
    }

    public List<Kpls> findAllByKpls(Kpls kpls) {
        return kplsMapper.findAllByKpls(kpls);
    }

    //查询明细合计金额
    public Kpls findHjje(Map params) {
        return kplsMapper.findHjje(params);
    }

    //更新主表的合计金额
    public void updateHjje(Map params) {
        kplsMapper.updateHjje(params);
    }

    //红冲·查询相差月份
    public Fpcxvo selectMonth(Map params) {
        return kplsMapper.selectMonth(params);
    }

    //查询发票换开数据
    public List<Fpcxvo> findFphkByPage(Pagination pagination) {
        return kplsMapper.findFphkByPage(pagination);
    }

    //发票重开数据查询
    public List<Fpcxvo> findFpcksqKpls(Map params) {
        return kplsMapper.findFpcksqKpls(params);
    }

    //发票换开申请数据查询
    public List<Fpcxvo> findHkfpsqByPage(Pagination pagination) {
        return kplsMapper.findHkfpsqByPage(pagination);
    }

    //发票归档查询
    public List<Fpcxvo> fpgdcxdb(Map params) {
        return kplsMapper.fpgdcxdb(params);
    }

    public List<Kpls> findKplsByDjh(Map params) {
        return kplsMapper.findKplsByDjh(params);
    }

    public List<Kpls> findKplsByPms(Map params) {
        return kplsMapper.findKplsByPms(params);
    }

    public List<Kpls> findListByPagination(Pagination pagination) {
        return kplsMapper.findListByPagination(pagination);
    }

    public List<KplsVO3> findList2ByPagination(Map map) {
        return kplsMapper.findList2ByPagination(map);
    }

    public List<Kpls> findByDjh(Kpls kpls) {
        return kplsMapper.findByDjh(kpls);
    }

    public List<Kpls> findAll(Map params) {
        return kplsMapper.findAll(params);
    }

    public List<Kpls> findAllNeedRegeneratePdfKpls(Map params) {
        return kplsMapper.findAllNeedRegeneratePdfKpls(params);
    }

    //更新调用接口返回数据
    public void updateReturnMes(Map params) {
        kplsMapper.updateReturnMes(params);
    }

    //查询需要生成pdf文件的记录
    public List<Kpls> findKplsNoPdf() {
        return kplsMapper.findKplsNoPdf();
    }

    public Integer findCountByDjh(Kpls kpls) {
        return kplsMapper.findCountByDjh(kpls);
    }
}

