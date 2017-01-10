package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.ClientLoginJpaDao;
import com.rjxx.taxeasy.dao.ClientLoginMapper;
import com.rjxx.taxeasy.domains.ClientLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Jan 05 14:36:48 CST 2017
 *
 * @ZhangBing
 */
@Service
public class ClientLoginService {

    @Autowired
    private ClientLoginJpaDao clientLoginJpaDao;

    @Autowired
    private ClientLoginMapper clientLoginMapper;

    public ClientLogin findOne(int id) {
        return clientLoginJpaDao.findOne(id);
    }

    public void save(ClientLogin clientLogin) {
        clientLoginJpaDao.save(clientLogin);
    }

    public void save(List<ClientLogin> clientLoginList) {
        clientLoginJpaDao.save(clientLoginList);
    }

    public ClientLogin findOneByParams(Map params) {
        return clientLoginMapper.findOneByParams(params);
    }

    public List<ClientLogin> findAllByParams(Map params) {
        return clientLoginMapper.findAllByParams(params);
    }

    public List<ClientLogin> findByPage(Pagination pagination) {
        return clientLoginMapper.findByPage(pagination);
    }

}

