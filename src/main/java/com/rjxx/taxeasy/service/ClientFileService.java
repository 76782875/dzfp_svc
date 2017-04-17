package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.ClientFileJpaDao;
import com.rjxx.taxeasy.dao.ClientFileMapper;
import com.rjxx.taxeasy.domains.ClientFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Apr 17 09:28:47 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class ClientFileService {

    @Autowired
    private ClientFileJpaDao clientFileJpaDao;

    @Autowired
    private ClientFileMapper clientFileMapper;

    public ClientFile findOne(int id) {
        return clientFileJpaDao.findOne(id);
    }

    public void save(ClientFile clientFile) {
        clientFileJpaDao.save(clientFile);
    }

    public void save(List<ClientFile> clientFileList) {
        clientFileJpaDao.save(clientFileList);
    }

    public ClientFile findOneByParams(Map params) {
        return clientFileMapper.findOneByParams(params);
    }

    public List<ClientFile> findAllByParams(Map params) {
        return clientFileMapper.findAllByParams(params);
    }

    public List<ClientFile> findByPage(Pagination pagination) {
        return clientFileMapper.findByPage(pagination);
    }

}

