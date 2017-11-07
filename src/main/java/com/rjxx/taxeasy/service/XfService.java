package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.XfJpaDao;
import com.rjxx.taxeasy.dao.XfMapper;
import com.rjxx.taxeasy.domains.Group;
import com.rjxx.taxeasy.domains.Skp;
import com.rjxx.taxeasy.domains.Xf;
import com.rjxx.taxeasy.vo.XfKzVo;
import com.rjxx.taxeasy.vo.XfVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/9.
 */
@Service
public class XfService {

    @Autowired
    private XfJpaDao xfJpaDao;

    @Autowired
    private XfMapper xfMapper;

    @Autowired
    private GroupService groupService;

    @Autowired
    private SkpService skpService;

    public Xf findOne(Integer id) {
        return xfJpaDao.findOne(id);
    }

    @Transactional
    public void saveNew(Xf xf) {
        //保存销方
        xfJpaDao.save(xf);
        //保存默认税控盘
//        Skp skp = new Skp();
//        skp.setGsdm(xf.getGsdm());
//        skp.setXfid(xf.getId());
//        skp.setBz("系统自动生成");
//        skp.setKpdmc("默认");
//        skp.setSkph("默认");
//        skp.setLrry(xf.getLrry());
//        skp.setLrsj(new Date());
//        skp.setXgry(xf.getLrry());
//        skp.setXgsj(new Date());
//        skp.setYxbz("1");
//        skp.setKplx("01,02,12");
//        skpService.save(skp);
        Group g = new Group();
        g.setYhid(xf.getLrry());
        g.setXfid(xf.getId());
//        g.setSkpid(skp.getId());
        g.setYxbz("1");
        g.setLrry(xf.getLrry());
        g.setXgry(xf.getLrry());
        g.setLrsj(new Date());
        g.setXgsj(new Date());
        groupService.save(g);
    }

    @Transactional
    public void save(Iterable<Xf> xfList) {
        xfJpaDao.save(xfList);
        Group g = null;
        for (Xf xf : xfList) {
            g = new Group();
            g.setYhid(xf.getLrry());
            g.setXfid(xf.getId());
            g.setYxbz("1");
            g.setLrry(xf.getLrry());
            g.setXgry(xf.getLrry());
            g.setLrsj(new Date());
            g.setXgsj(new Date());
            groupService.save(g);
        }
    }

    public void update(Xf xf) {
        xfJpaDao.save(xf);
    }

    @Transactional
    public void delete(Xf xf) {
        xfJpaDao.save(xf);
        Group g = new Group();
        g.setXfid(xf.getId());
        List<Group> list = groupService.findAllByParams(g);
        if (!list.isEmpty()) {
            for (Group group : list) {
                group.setYxbz("0");
                g.setXgry(xf.getLrry());
                g.setXgsj(new Date());
                groupService.save(group);
            }
        }
    }

    /**
     * 根据yhid查找该用户下的所有管理的销方
     *
     * @param yhId
     * @return
     */
    public List<Xf> getXfListByYhId(int yhId) {
        return xfMapper.getXfListByYhId(yhId);
    }

    public Xf findOneByParams(Xf xf) {
        return xfMapper.findOneByParams(xf);
    }

    public List<Xf> findAllByParams(Xf xf) {
        return xfMapper.findAllByParams(xf);
    }

    /**
     * 根据销方id获取销方信息
     *
     * @param map
     * @return
     */
    public List<Xf> findAllByMap(Map<String, Object> map) {
        return xfMapper.findAllByMap(map);
    }

    public List<Xf> findByPage(Pagination pagination) {
        return xfMapper.findByPage(pagination);
    }

    public List<XfVo> findByPages(Pagination pagination) {
        return xfMapper.findByPages(pagination);
    }

	public XfVo findAllByXfxx(Map map) {
		return xfMapper.findAllByXfxx(map);
	}

    public List<XfKzVo> findXfkzListByXfid(Map<String, Object> map) {
        return xfMapper.findXfkzListByXfid(map);
    }


}
