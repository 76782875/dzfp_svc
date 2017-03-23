package com.rjxx.taxeasy.dao;

import java.util.List;
import java.util.Map;

import com.rjxx.comm.mybatis.MybatisRepository;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.domains.Jyls;
import com.rjxx.taxeasy.domains.Kpls;
import com.rjxx.taxeasy.vo.Fpcxvo;
import com.rjxx.taxeasy.vo.FptjVo;
import com.rjxx.taxeasy.vo.KplsVO3;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 17 14:37:57 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
@MybatisRepository
public interface KplsMapper {

    public Kpls findOneByParams(Map params);
    //查询发票是否存在
    public List<Kpls> findFpExist(Map params);
    
    public List<Fpcxvo> findAllByParams(Map params);

    public List<Fpcxvo> findByPage(Pagination pagination);
    
    public void update(Map params);
    
    //更新换开标志
    public void updateHkbz(Map params);
    
    public List<Kpls> printSingle(Map params);
    
    public List<Fpcxvo> printmany(Map params);
    
    //查询红冲数据
    public List<Fpcxvo> findKhcfpByPage(Pagination pagination);
    //红虫后更新发票状态
    public void updateFpczlx(Map params);
    
    public List<Kpls> findAllByKpls(Kpls kpls);
    
    //查询明细合计金额
    public Kpls findHjje(Map params);
    
    //更新主表合计金额
    public void updateHjje(Map params);
    
    //红冲查询相差月份
    public Fpcxvo selectMonth(Map params);
    
    //查寻发票换开数据
    public List<Fpcxvo> findFphkByPage(Pagination pagination);
    
    //发票重开数据查询
    public List<Fpcxvo> findFpcksqKpls(Map params);
    
    //发票重打数据查询
	public List<Fpcxvo> findKcdfpByPage(Pagination pagination);
	
	//发票重打数据查询1
	public List<Fpcxvo> findKcdfpByPage1(Pagination pagination);


    
    /*发票换开申请数据查询*/
    public List<Fpcxvo> findHkfpsqByPage(Pagination pagination);
    
    public List<Fpcxvo> fpgdcxdb(Map params); 
    
    public List<Kpls> findKplsByDjh(Map params);
    
    public List<Kpls> findKplsByPms(Map params);
    
    public List<Kpls> findListByPagination(Pagination pagination);
    
    public List<KplsVO3> findList2ByPagination(Map map);

    
    public List<Kpls> findByDjh(Kpls kpls);

    public List<Kpls> findAll(Map params);
    
    //更新调用接口返回数据
    public void updateReturnMes(Map params);
    
    //查询需要生成pdf文件的记录
    public List<Kpls> findKplsNoPdf();
    
    public Integer findCountByDjh(Kpls kpls);

    public List<Kpls> findAllNeedRegeneratePdfKpls(Map params);
	public List<Fpcxvo> findKhcfpByPage1(Pagination pagination);
	public List<Fpcxvo> findKzffpByPage(Pagination pagination);
	public List<Fpcxvo> findKzffpByPage1(Pagination pagination);

    /**
     * 获取发票待办统计结果
     *
     * @param kpdid
     * @return
     */
    public List<FptjVo> findFpdbtjjgByKpdid(int kpdid);
    

}

