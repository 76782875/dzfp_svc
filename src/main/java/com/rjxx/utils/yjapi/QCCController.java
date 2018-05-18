package com.rjxx.utils.yjapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wangyahui on 2017/11/27 0027.
 * 企查查API
 */
@RestController
@RequestMapping("/companyInfo")
@CrossOrigin
public class QCCController{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private QCCUtils qccUtils;

    @RequestMapping(value = "/getNames", method = RequestMethod.POST)
    public String getNames(@RequestParam("name")String name) {
        logger.info("输入的参数为："+name);
        String result =qccUtils.getQccSearch(name);
//        String result = qccUtils.getTycByName(name);
        logger.info("输出为："+result);
        return result;
    }

    @RequestMapping(value = "/single", method = RequestMethod.POST)
    public String getMsg(@RequestParam("name")String name){
        logger.info("输入的参数为："+name);
        String taxno = qccUtils.getTycById(null, name);
        logger.info("输出为："+taxno);
        return taxno;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String getName(@RequestParam("name")String name){
        logger.info("输入的参数为："+name);
        String result = qccUtils.getTycByName(name);
        logger.info("输出为："+result);
        return result;
    }
}
