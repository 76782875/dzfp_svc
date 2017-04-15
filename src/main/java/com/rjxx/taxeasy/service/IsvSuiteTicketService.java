package com.rjxx.taxeasy.service;

import com.alibaba.fastjson.JSON;
import com.rjxx.comm.mybatis.Pagination;
import com.rjxx.taxeasy.dao.IsvSuiteTicketJpaDao;
import com.rjxx.taxeasy.dao.IsvSuiteTicketMapper;
import com.rjxx.taxeasy.domains.IsvSuiteTicket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 由GenJavaCode类自动生成
 * <p>
 * Thu Apr 13 17:44:31 CST 2017
 *
 * @ZhangBing
 */ 
@Service
public class IsvSuiteTicketService {
    
	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
    @Autowired
    private IsvSuiteTicketJpaDao isvSuiteTicketJpaDao;

    @Autowired
    private IsvSuiteTicketMapper isvSuiteTicketMapper;

    public IsvSuiteTicket findOne(int id) {
        return isvSuiteTicketJpaDao.findOne(id);
    }

    public void save(IsvSuiteTicket isvSuiteTicket) {
        isvSuiteTicketJpaDao.save(isvSuiteTicket);
    }

    public void save(List<IsvSuiteTicket> isvSuiteTicketList) {
        isvSuiteTicketJpaDao.save(isvSuiteTicketList);
    }

    public IsvSuiteTicket findOneByParams(Map params) {
        return isvSuiteTicketMapper.findOneByParams(params);
    }

    public List<IsvSuiteTicket> findAllByParams(Map params) {
        return isvSuiteTicketMapper.findAllByParams(params);
    }

    public List<IsvSuiteTicket> findByPage(Pagination pagination) {
        return isvSuiteTicketMapper.findByPage(pagination);
    }
    /**
     * 保存或者更新ticket
     * @param isvsuiteticket
     * @return
     */
	public boolean saveOrUpdateSuiteTicket(IsvSuiteTicket isvsuiteticket) {
		// TODO Auto-generated method stub
		
		isvsuiteticket.setGmtCreate(new Date());
		isvsuiteticket.setGmtModified(new Date());
		logger.info("suiteTicket:{}",JSON.toJSONString(isvsuiteticket));
		try{
			this.save(isvsuiteticket);
			return true;
		}catch(Exception e){
			return false;
		}
		
		
	}

}

