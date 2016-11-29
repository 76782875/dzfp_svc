package com.rjxx.taxeasy.service;

import com.rjxx.taxeasy.dao.PrivilegeTypesJpaDao;
import com.rjxx.taxeasy.domains.PrivilegeTypes;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/9.
 */
@Service
public class PrivilegeTypesService {

    @Autowired
    private PrivilegeTypesJpaDao privilegeTypesJpaDao;

    public List<PrivilegeTypes> findAll() {
        List<PrivilegeTypes> list = new ArrayList<>();
        Iterable<PrivilegeTypes> iterable = privilegeTypesJpaDao.findAll();
        for (PrivilegeTypes tmp : iterable) {
            list.add(tmp);
        }
        return list;
    }

}
