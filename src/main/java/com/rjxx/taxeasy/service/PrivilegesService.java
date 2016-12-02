package com.rjxx.taxeasy.service;

import com.rjxx.taxeasy.dao.PrivilegesMapper;
import com.rjxx.taxeasy.domains.Privileges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/9.
 */
@Service
public class PrivilegesService {

    @Autowired
    private PrivilegesMapper privilegesMapper;

    /**
     * 根据角色ids查找权限
     *
     * @param params
     * @return
     */
    public List<Privileges> findByRoleIds(Map params) {
        return privilegesMapper.findByRoleIds(params);
    }
    public List<Privileges> findOneByParams(Map params) {
        return privilegesMapper.findOneByParams(params);
    }
    
}
