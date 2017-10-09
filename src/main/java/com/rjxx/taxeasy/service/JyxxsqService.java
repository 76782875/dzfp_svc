package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.JyxxsqJpaDao;
import com.rjxx.taxeasy.dao.JyxxsqMapper;
import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.vo.Jylsvo;
import com.rjxx.taxeasy.vo.JymxsqVo;
import com.rjxx.taxeasy.vo.JyspmxVo;
import com.rjxx.taxeasy.vo.JyxxsqVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Wed Jan 04 13:33:08 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class JyxxsqService {

    @Autowired
    private JyxxsqJpaDao jyxxsqJpaDao;

    @Autowired
    private JyxxsqMapper jyxxsqMapper;

    @Autowired
    private JymxsqService jymxsqservice;
    
    @Autowired
    private JymxsqClService jymxsqClService;
    
    @Autowired
    private JyzfmxService jyzfmxService;
    
    public Jyxxsq findOne(int id) {
        return jyxxsqJpaDao.findOne(id);
    }

    public void save(Jyxxsq jyxxsq) {
        jyxxsqJpaDao.save(jyxxsq);
    }

    public void save(List<Jyxxsq> jyxxsqList) {
        jyxxsqJpaDao.save(jyxxsqList);
    }

    public Jyxxsq findOneByParams(Map params) {
        return jyxxsqMapper.findOneByParams(params);
    }

	public Jyxxsq findOneByTqmAndJshj(Map params) {
		return jyxxsqMapper.findOneByTqmAndJshj(params);
	}

	public List<Jyxxsq> findAllByTqms(Map params) {
        return jyxxsqMapper.findAllByTqms(params);
    }
    
    public List<Jyxxsq> findAllByJylshs(Map params) {
        return jyxxsqMapper.findAllByJylshs(params);
    }

	public Jyxxsq findSjlyAndOpenidByMap(Map params) {
		return jyxxsqMapper.findSjlyAndOpenidByMap(params);
	}

    public List<Jyxxsq> findAllByDdhs(Map params) {
        return jyxxsqMapper.findAllByDdhs(params);
    } 
    
    public List<JyxxsqVO> findByPage(Pagination pagination) {
        return jyxxsqMapper.findByPage(pagination);
    }
    
    public List<Jyxxsq> findByPage1(Pagination pagination) {
        return jyxxsqMapper.findByPage1(pagination);
    } 
    
    public List<Jyxxsq> findByMapParams(Map params) {
        return jyxxsqMapper.findByMapParams(params);
    } 
    
    public Xf findXfExistByKpd(Map params) {
        return jyxxsqMapper.findXfExistByKpd(params);
    }

	public Xf findXfExistByXfsh(Map params) {
		return jyxxsqMapper.findXfExistByXfsh(params);
	}

    public void saveJyxxsq(Jyxxsq jyxxsq){
    	jyxxsqMapper.saveJyxxsq(jyxxsq);
    }
    
    public void addJyxxsqBatch(List<Jyxxsq> Jyxxsqs){
    	jyxxsqMapper.addJyxxsqBatch(Jyxxsqs);
    }
    
    public List<JyxxsqVO> findYscByPage(Pagination pagination){
    	return jyxxsqMapper.findYscByPage(pagination);
    }

    public void updateGfxx(Map params){
    	jyxxsqMapper.updateGfxx(params);
	}
    /**
	 * 删除交易流水，包括明细
	 *
	 * @param sqlshList
	 */
	@Transactional
	public void delBySqlshList(List<Integer> sqlshList) {
		Iterable<Jyxxsq> jylsIterable = jyxxsqJpaDao.findAll(sqlshList);
		jyxxsqJpaDao.delete(jylsIterable);
		List<Jymxsq> jymxsqList = jymxsqservice.findBySqlshList(sqlshList);
		jymxsqservice.delete(jymxsqList);
	}
	
	  /**
		 * 删除交易流水，包括明细以及处理明细
		 *
		 * @param sqlshList
		 */
		@Transactional
		public void delBySqlshList2(List<Integer> sqlshList) {
			Iterable<Jyxxsq> jylsIterable = jyxxsqJpaDao.findAll(sqlshList);
			jyxxsqJpaDao.delete(jylsIterable);
			List<Jymxsq> jymxsqList = jymxsqservice.findBySqlshList(sqlshList);
			
			List<JymxsqCl> jymxsqCLList = jymxsqClService.findBySqlshList(sqlshList);

			jymxsqservice.delete(jymxsqList);
			
			jymxsqClService.delete(jymxsqCLList);
		}
	
	/**
	 * 更新jyxxsq状态
	 *
	 * @param sqlshList
	 * @param clztdm
	 */
	public void updateJyxxsqZtzt(List<Integer> sqlshList, String ztbz) {
		List<Jyxxsq> jylsIterable = (List<Jyxxsq>) jyxxsqJpaDao.findAll(sqlshList);
		for (Jyxxsq jyxxsq : jylsIterable) {
			jyxxsq.setZtbz(ztbz);
			jyxxsq.setXgsj(new Date());
		}
		save(jylsIterable);
	}
	
	/**
	 * 保存交易信息申请
	 *
	 * @param jyxxsq
	 * @param jymxsqList
	 */
	@Transactional
	public Integer saveJyxxsq(Jyxxsq jyxxsq, List<Jymxsq> jymxsqList) {
		save(jyxxsq);
		int sqlsh = jyxxsq.getSqlsh();
		for (Jymxsq Jymxsq : jymxsqList) {
			Jymxsq.setSqlsh(sqlsh);
		}
		jymxsqservice.save(jymxsqList);
		return sqlsh;
	}
	
	
	/**
	 * 保存交易信息申请
	 *
	 * @param jyxxsq
	 * @param jymxsqList
	 */
	@Transactional
	public Integer saveJyxxsq(Jyxxsq jyxxsq, List<Jymxsq> jymxsqList,List<JymxsqCl> jymxsqClList,List<Jyzfmx> jyzfmxList) {
		save(jyxxsq);
		int sqlsh = jyxxsq.getSqlsh();
		for (Jymxsq Jymxsq : jymxsqList) {
			Jymxsq.setSqlsh(sqlsh);
		}
		if(null != jymxsqClList && !jymxsqClList.isEmpty()){
			for (JymxsqCl jymxsqCl : jymxsqClList) {
				jymxsqCl.setSqlsh(sqlsh);
			}
			jymxsqClService.save(jymxsqClList);
		}
		if(null != jyzfmxList && !jyzfmxList.isEmpty()){
			for (Jyzfmx jyzfmx : jyzfmxList) {
				jyzfmx.setSqlsh(sqlsh);
			}
			jyzfmxService.save(jyzfmxList);
		}
		jymxsqservice.save(jymxsqList);
		return sqlsh;
	}
	
	/**
	 * 交易信息申请与明细一对一
	 *
	 * @param jyxxsqList
	 * @param jymxsqList
	 */
	@Transactional
	public void saveAll(List<Jyxxsq> jyxxsqList, List<JymxsqVo> jymxsqList) {
		jyxxsqJpaDao.save(jyxxsqList);
		List<Jymxsq> mxList = new ArrayList<>();
		Jymxsq mx = null;
		for (Jyxxsq jyxxsq : jyxxsqList) {
			for (JymxsqVo vo : jymxsqList) {
				if (jyxxsq.getDdh().equals(vo.getDdh())) {
					mx = getMx(vo);
					mx.setSqlsh(jyxxsq.getSqlsh());
					mxList.add(mx);
				}
			}
		}
		jymxsqservice.save(mxList);
	}
	
	
	/**
	 * 交易信息申请与明细一对一
	 *
	 * @param jyxxsqList
	 * @param jymxsqList
	 * @param jymxsqClList
	 * @param jyzfmxList
	 */
	@Transactional
	public void saveAll(List<Jyxxsq> jyxxsqList, List<Jymxsq> jymxsqList,List<JymxsqCl> jymxsqClList,List<Jyzfmx> jyzfmxList) {
		//jyxxsqJpaDao.save(jyxxsqList);
		addJyxxsqBatch(jyxxsqList);
		List<Jymxsq> mxList = new ArrayList<>();
		Jymxsq mx = null;
		for (Jyxxsq jyxxsq : jyxxsqList) {
			for (Jymxsq vo : jymxsqList) {
				if (jyxxsq.getDdh().equals(vo.getDdh())) {
					//mx = getMx(vo);
					vo.setSqlsh(jyxxsq.getSqlsh());
					//mxList.add(mx);
				}
			}
			
			if(null != jymxsqClList && !jymxsqClList.isEmpty()){
				for (JymxsqCl vo : jymxsqClList) {
					if (jyxxsq.getDdh().equals(vo.getDdh())) {
						vo.setSqlsh(jyxxsq.getSqlsh());
					}
				}
				
			}
			
			if (null != jyzfmxList && !jyzfmxList.isEmpty()) {
				for (Jyzfmx vo : jyzfmxList) {
					if (jyxxsq.getDdh().equals(vo.getDdh())) {
						vo.setSqlsh(jyxxsq.getSqlsh());
					}
				}
			}
		}
		
		//jymxsqservice.save(jymxsqList);
		jymxsqservice.addJymxsqBatch(jymxsqList);
		if(null != jymxsqClList && !jymxsqClList.isEmpty()){
			//jymxsqClService.save(jymxsqClList);
			System.out.println("jymxsqClList开始保存时间"+new Date());
			jymxsqClService.addJymxsqClBatch(jymxsqClList);
			System.out.println("jymxsqClList结束保存时间"+new Date());
		}
		
		if(null != jyzfmxList && !jyzfmxList.isEmpty()){
			//jyzfmxService.save(jyzfmxList);
			jyzfmxService.addJyzfmxBatch(jyzfmxList);
		}
	}
	
	private Jymxsq getMx(JymxsqVo vo) {
		Jymxsq mx = new Jymxsq(vo.getSpmxxh(), vo.getFphxz(), vo.getSpdm(), vo.getSpmc(), vo.getSpggxh(), vo.getSpdw(),
				vo.getSps(), vo.getSpdj(), vo.getSpje(), vo.getSpsl(), vo.getSpse(), vo.getJshj(),vo.getHzkpxh(), vo.getLrsj(), vo.getLrry(), vo.getXgsj(), vo.getXgry(), vo.getGsdm(), vo.getXfid(),
				vo.getSkpid(),vo.getYxbz(),vo.getKkjje(),vo.getYkjje());
		return mx;
	}

	public List<JyxxsqVO> findBykplscxPage(Pagination pagination) {
		// TODO Auto-generated method stub
		 return jyxxsqMapper.findBykplscxPage(pagination);
	}

	public Skp findskpExistByXfid(Map tt) {
		return jyxxsqMapper.findskpExistByXfid(tt);
	}

	public Jyxxsq findOneByJylsh(Map jyxxsqMap) {
		return jyxxsqMapper.findOneByJylsh(jyxxsqMap);
	}
}

