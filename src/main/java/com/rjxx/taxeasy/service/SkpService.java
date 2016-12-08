package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.SkpJpaDao;
import com.rjxx.taxeasy.dao.SkpMapper;
import com.rjxx.taxeasy.domains.Group;
import com.rjxx.taxeasy.domains.Skp;
import com.rjxx.taxeasy.domains.Xf;
import com.rjxx.taxeasy.vo.SkpVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Fri Oct 14 08:55:04 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
@Service
public class SkpService {

    @Autowired
    private SkpJpaDao skpJpaDao;

    @Autowired
    private SkpMapper skpMapper;
    
    @Autowired
    private GroupService groupService;

    public Skp findOne(int id) {
        return skpJpaDao.findOne(id);
    }

    public void save(Skp skp) {
        skpJpaDao.save(skp);
    }

    @Transactional
    public void save(List<Skp> skpList) {
        skpJpaDao.save(skpList);
        if (!skpList.isEmpty()) {
			for (Skp skp : skpList) {
				Group group = new Group();
				group.setYxbz("1");
				group.setXfid(skp.getXfid());
				group.setYhid(skp.getLrry());
				group.setSkpid(skp.getId());
				group.setLrry(skp.getLrry());
				group.setXgry(skp.getLrry());
				group.setLrsj(new Date());
				group.setXgsj(new Date());
				groupService.save(group);
			}
		}
    }

    public Skp findOneByParams(Map skp) {
        return skpMapper.findOneByParams(skp);
    }

    public List<Skp> findAllByParams(Skp skp) {
        return skpMapper.findAllByParams(skp);
    }
    public List<Skp> findBySql(Skp skp) {
        return skpMapper.findBySql(skp);
    }
    public List<SkpVo> findByPage(Pagination pagination) {
        return skpMapper.findByPage(pagination);
    }
    
    public List<Skp> getSkpListByYhId(Integer yhId) {
        return skpMapper.getSkpListByYhId(yhId);
    }
    
    public List<Skp> getKpd(Map params){
    	return skpMapper.getKpd(params);
    }
    public List<Skp> findCsz(Skp params){
    	return skpMapper.findCsz(params);
    }
}

