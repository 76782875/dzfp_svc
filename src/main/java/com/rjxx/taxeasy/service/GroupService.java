package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.GroupJpaDao;
import com.rjxx.taxeasy.dao.GroupMapper;
import com.rjxx.taxeasy.domains.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Oct 13 14:35:28 GMT+08:00 2016
 *
 * @ZhangBing
 */ 
@Service
public class GroupService {

    @Autowired
    private GroupJpaDao groupJpaDao;

    @Autowired
    private GroupMapper groupMapper;

    public Group findOne(int id) {
        return groupJpaDao.findOne(id);
    }

    public void save(Group group) {
        groupJpaDao.save(group);
    }

    public void save(List<Group> groupList) {
        groupJpaDao.save(groupList);
    }

    public void delete(List<Group> groupList) {
    	for (Group group : groupList) {
			group.setYxbz("0");
		}
        groupJpaDao.save(groupList);
    }

    public Group findOneByParams(Group group) {
        return groupMapper.findOneByParams(group);
    }

    public List<Group> findAllByParams(Group group) {
        return groupMapper.findAllByParams(group);
    }

    public List<Group> findByPage(Pagination pagination) {
        return groupMapper.findByPage(pagination);
    }

}

