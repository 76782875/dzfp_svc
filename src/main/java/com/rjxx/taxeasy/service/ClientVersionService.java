package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.ClientVersionJpaDao;
import com.rjxx.taxeasy.dao.ClientVersionMapper;
import com.rjxx.taxeasy.domains.ClientVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Mar 20 09:20:17 CST 2017
 *
 * @ZhangBing
 */
@Service
public class ClientVersionService {

    @Autowired
    private ClientVersionJpaDao clientVersionJpaDao;

    @Autowired
    private ClientVersionMapper clientVersionMapper;

    public ClientVersion findOne(int id) {
        return clientVersionJpaDao.findOne(id);
    }

    public void save(ClientVersion clientVersion) {
        clientVersionJpaDao.save(clientVersion);
    }

    public void save(List<ClientVersion> clientVersionList) {
        clientVersionJpaDao.save(clientVersionList);
    }

    public ClientVersion findOneByParams(Map params) {
        return clientVersionMapper.findOneByParams(params);
    }

    public List<ClientVersion> findAllByParams(Map params) {
        return clientVersionMapper.findAllByParams(params);
    }

    public List<ClientVersion> findByPage(Pagination pagination) {
        return clientVersionMapper.findByPage(pagination);
    }

}

