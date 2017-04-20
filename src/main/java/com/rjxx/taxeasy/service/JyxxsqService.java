package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.JyxxsqJpaDao;
import com.rjxx.taxeasy.dao.JyxxsqMapper;
import com.rjxx.taxeasy.domains.Jyls;
import com.rjxx.taxeasy.domains.Jymxsq;
import com.rjxx.taxeasy.domains.Jyspmx;
import com.rjxx.taxeasy.domains.Jyxxsq;
import com.rjxx.taxeasy.domains.Xf;
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

    public List<Jyxxsq> findAllByTqms(Map params) {
        return jyxxsqMapper.findAllByTqms(params);
    }
    
    public List<Jyxxsq> findAllByJylshs(Map params) {
        return jyxxsqMapper.findAllByJylshs(params);
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

    public void saveJyxxsq(Jyxxsq jyxxsq){
    	jyxxsqMapper.saveJyxxsq(jyxxsq);
    }
    
    
    public List<JyxxsqVO> findYscByPage(Pagination pagination){
    	return jyxxsqMapper.findYscByPage(pagination);
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
	 * 保存交易流水
	 *
	 * @param jyls
	 * @param jyspmxList
	 */
	@Transactional
	public void saveJyxxsq(Jyxxsq jyxxsq, List<Jymxsq> jymxsqList) {
		save(jyxxsq);
		int sqlsh = jyxxsq.getSqlsh();
		for (Jymxsq Jymxsq : jymxsqList) {
			Jymxsq.setSqlsh(sqlsh);
		}
		jymxsqservice.save(jymxsqList);
	}
	
	
	/**
	 * 交易流水与明细一对一
	 *
	 * @param jylsList
	 * @param jyspmxList
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
	
	
	private Jymxsq getMx(JymxsqVo vo) {
		Jymxsq mx = new Jymxsq(vo.getSpmxxh(), vo.getFphxz(), vo.getSpdm(), vo.getSpmc(), vo.getSpggxh(), vo.getSpdw(),
				vo.getSps(), vo.getSpdj(), vo.getSpje(), vo.getSpsl(), vo.getSpse(), vo.getJshj(),vo.getHzkpxh(), vo.getLrsj(), vo.getLrry(), vo.getXgsj(), vo.getXgry(), vo.getGsdm(), vo.getXfid(),
				vo.getSkpid(),vo.getYxbz(),vo.getKkjje(),vo.getYkjje(),vo.getSpbz());
		return mx;
	}

	public List<JyxxsqVO> findBykplscxPage(Pagination pagination) {
		// TODO Auto-generated method stub
		 return jyxxsqMapper.findBykplscxPage(pagination);
	}

}

