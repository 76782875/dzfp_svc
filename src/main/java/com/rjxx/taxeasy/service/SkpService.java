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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

	/*public List<Skp> getSkpListByYhId(int yhid) {
		Map<String, Object> params = new HashMap<>();
		Group g = new Group();
		g.setYhid(yhid);
		List<Group> groupList = groupService.findAllByParams(g);
		List<Xf> xfs = new ArrayList<>();
		List<Skp> skps = new ArrayList<>();
		Xf xf = null;
		Skp skp = null;
		for (Group gp : groupList) {
			boolean flag = false;
			for (Group gr : groupList) {
				if (gp.getXfid().equals(gr.getXfid()) && gr.getSkpid() != null) {
					flag = true;
					skp = new Skp();
					boolean tag = false;
					skp.setId(gr.getSkpid());
					if (!skps.isEmpty()) {
						for (Skp s : skps) {
							if (s.getId().equals(skp.getId())) {
								tag = true;
								break;
							}
						}
					}
					if (tag) {
						continue;
					}
					skps.add(skp);
					continue;
				}
			}
			if (!flag) {
				xf = new Xf();
				xf.setId(gp.getXfid());
				xfs.add(xf);
			}

		}
		if (xfs.size() > 0) {
			params.put("xfs", xfs);
		}
		if (skps.size() > 0) {
			params.put("skps", skps);
		}
		return skpMapper.getSkpListByYhId(params);
	}*/

	public List<Skp> getSkpListByYhId(int yhid) {
		Map params  = new HashMap();
		params.put("yhid",yhid);

		return skpMapper.getSkpListByYhId(params);
	}
	public List<Skp> getKpd(Map params) {
		return skpMapper.getKpd(params);
	}

	public List<Skp> findCsz(Skp params) {
		return skpMapper.findCsz(params);
	}
	
	public SkpVo findXfSkp(Map params){
		return skpMapper.findXfSkp(params);
	}

	public SkpVo findXfSkpNum(Map map) {
		// TODO Auto-generated method stub
		return skpMapper.findXfSkpNum(map);
	}

    public List<Skp> findSkpbySkph(Map parms) {
		return skpMapper.findSkpbySkph(parms);
    }
}
