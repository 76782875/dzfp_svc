package com.rjxx.taxeasy.service;

import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.DrPzJpaDao;
import com.rjxx.taxeasy.dao.DrPzMapper;
import com.rjxx.taxeasy.domains.DrPz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Mon Oct 10 13:16:39 CST 2016
 *
 * @ZhangBing
 */
@Service
public class DrPzService {

    @Autowired
    private DrPzJpaDao drPzJpaDao;

    @Autowired
    private DrPzMapper drPzMapper;

    public DrPz findOne(int id) {
        return drPzJpaDao.findOne(id);
    }

    public void save(DrPz drPz) {
        drPzJpaDao.save(drPz);
    }

    public void save(List<DrPz> drPzList) {
        drPzJpaDao.save(drPzList);
    }

    public DrPz findOneByParams(DrPz drPz) {
        return drPzMapper.findOneByParams(drPz);
    }

    public List<DrPz> findAllByParams(DrPz drPz) {
        return drPzMapper.findAllByParams(drPz);
    }

    public List<DrPz> findByPage(Pagination pagination) {
        return drPzMapper.findByPage(pagination);
    }

    /**
     * 先删除后保存
     *
     * @param yhid
     * @param drPzList
     */
    @Transactional
    public void deleteAndSave(int mbid, List<DrPz> drPzList) {
        //先查找
        DrPz params = new DrPz();
        params.setMbid(mbid);;
        List<DrPz> deleteList = findAllByParams(params);
        if (!deleteList.isEmpty()) {
            //再删除
            drPzJpaDao.delete(deleteList);
		}
        save(drPzList);
    }
    
    public void deleteAll(List<DrPz> list){
    	drPzJpaDao.delete(list);
    }

}

