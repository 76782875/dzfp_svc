package com.rjxx.taxeasy.service.shouqianba;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rjxx.taxeasy.dao.shouqianba.*;
import com.rjxx.taxeasy.domains.shouqianba.PayCode;
import com.rjxx.taxeasy.domains.shouqianba.PayIn;
import com.rjxx.taxeasy.domains.shouqianba.PayOut;
import com.rjxx.taxeasy.domains.shouqianba.PayRecord;
import com.rjxx.taxeasy.dto.shouqianba.PayResult;
import com.rjxx.taxeasy.task.PayTask;
import com.rjxx.utils.shouqianba.PayUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangyahui
 * @email wangyahui@datarj.com
 * @company 上海容津信息技术有限公司
 * @date 2018/6/7
 */
@Service
public class PayService {

    @Autowired
    private PayInRepository payInRepository;
    @Autowired
    private PayActivateRepository payActivateRepository;
    @Autowired
    private PayOutRepository payOutRepository;
    @Autowired
    private PayRecordRepository payRecordRepository;
    @Autowired
    private PayCodeRepository payCodeRepository;
    @Autowired
    private PayPaymentsRepository payPaymentsRepository;

    private static Logger logger = LoggerFactory.getLogger(PayService.class);

    /**
     * 支付并记录支付流水
     *
     * @param terminal_sn  终端号
     * @param terminal_key 终端秘钥
     * @param total_amount 金额
     * @param subject      交易概述
     * @param operator     操作员
     * @param return_url   返回URL
     * @param orderNo      原订单号
     * @return 支付URL
     */
    public Map payIn(String terminal_sn, String terminal_key, String total_amount,
                     String subject, String operator, String return_url,
                     String orderNo, String gsdm,String storeNo) {
        Map errorResult = new HashMap();
        Map reflectMap = new HashMap();
        reflectMap.put("gsdm", gsdm);
        reflectMap.put("orderNo", orderNo);
        reflectMap.put("storeNo", storeNo);
        String reflect = JSON.toJSONString(reflectMap);
        String payTotal = null;
        try {
            payTotal = new BigDecimal(total_amount).multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString();
        } catch (Exception e) {
            e.printStackTrace();
            errorResult.put("errorMsg", "TOTAL_AMOUNT_ERROR");
            return errorResult;
        }
        String client_sn = "";
        List<PayIn> allByOrderNo = payInRepository.findAllByOrderNo(orderNo);
        if (allByOrderNo.isEmpty()) {
            client_sn = gsdm + orderNo;
            PayOut payOut = new PayOut();
            payOut.setLrsj(new Date());
            payOut.setXgsj(new Date());
            payOut.setGsdm(gsdm);
            payOut.setClientSn(client_sn);
            payOut.setOrderNo(orderNo);
            payOut.setTerminalSn(terminal_sn);
            payOut.setSubject(subject);
            payOut.setTotalAmount(total_amount);
            payOut.setReflect(reflect);
            payOut.setOperator(operator);
            payOut.setStoreNo(storeNo);
            payOutRepository.save(payOut);
        } else {
            PayOut oneByGsdmAndOrderNo = payOutRepository.findOneByGsdmAndOrderNo(gsdm, orderNo);
            String orderStatus = oneByGsdmAndOrderNo.getOrderStatus();
            PayCode paycode = payCodeRepository.findOneByCode(orderStatus);
            if(paycode==null){
                client_sn = oneByGsdmAndOrderNo.getClientSn();
            }else{
                String filter = paycode.getFilter();
                String isRePay = filter.substring(1, 2);
                //如果允许重新发起请求
                if ("1".equals(isRePay)) {
                    client_sn = gsdm + "-" + orderNo + "-" + allByOrderNo.size();
                } else {
                    client_sn = oneByGsdmAndOrderNo.getClientSn();
                }
            }
        }
        PayIn payIn = new PayIn();
        payIn.setLrsj(new Date());
        payIn.setGsdm(gsdm);
        payIn.setOrderNo(orderNo);
        payIn.setReturnUrl(return_url);
        payIn.setOperator(operator);
        payIn.setSubject(subject);
        payIn.setTotalAmount(total_amount);
        payIn.setTerminalSn(terminal_sn);
        payIn.setReflect(reflect);
        payIn.setClientSn(client_sn);
        payIn.setStoreNo(storeNo);
        Map succResult = null;
        try {
            succResult = PayUtil.payIn(terminal_sn, terminal_key, client_sn, payTotal,
                    subject, operator, return_url, reflect);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String sign = (String) succResult.get("sign");
        payIn.setSign(sign);
        payInRepository.save(payIn);
        return succResult;
    }

    public Map receive(PayResult payResult) {
        Map result = new HashMap();
        logger.info("支付返回={}", payResult.toString());
        String reflect = payResult.getReflect();
        JSONObject reflectObject = JSON.parseObject(reflect);
        String gsdm = reflectObject.getString("gsdm");
        String orderNo = reflectObject.getString("orderNo");
        String storeNo = reflectObject.getString("storeNo");
        String is_success = payResult.getIs_success();
        String terminal_sn = payResult.getTerminal_sn();
        String client_sn = payResult.getClient_sn();
        String sn = payResult.getSn();//收钱吧订单号
        String error_code = payResult.getError_code();
        String error_message = null;
        if(StringUtils.isNotBlank(payResult.getError_message())){
            error_message = URLDecoder.decode(payResult.getError_message());
        }
        String operator = payResult.getOperator();
        String result_code = payResult.getResult_code();
        String result_message = payResult.getResult_message();
        String trade_no = payResult.getTrade_no();
        String total_amount = payResult.getTotal_amount();
        String sign = payResult.getSign();
        String status = payResult.getStatus();
        String subject = payResult.getSubject();

        if ("T".equals(is_success) && "SUCCESS".equals(status)) {
                PayTask payTask = new PayTask();
                payTask.setClientSn(client_sn);
                payTask.setGsdm(gsdm);
                payTask.setOrderNo(orderNo);
                payTask.setStoreNo(storeNo);
                payTask.setSn(sn);
                payTask.setReflect(reflect);
                payTask.setSign(sign);
                payTask.setTerminalSn(terminal_sn);
                payTask.setPayActivateRepository(payActivateRepository);
                payTask.setPayCodeRepository(payCodeRepository);
                payTask.setPayOutRepository(payOutRepository);
                payTask.setPayRecordRepository(payRecordRepository);
                payTask.setPayPaymentsRepository(payPaymentsRepository);
                Thread t = new Thread(payTask);
                t.start();
                result.put("gsdm",gsdm);
                result.put("orderNo",orderNo);
        } else {
            //创建记录表对象
            PayRecord payRecord = new PayRecord();
            payRecord.setLrsj(new Date());
            payRecord.setErrorCode(error_code);
            payRecord.setErrorMessage(error_message);
            payRecord.setSn(sn);
            payRecord.setTradeNo(trade_no);
            payRecord.setStatus(status);
            payRecord.setReqType("2");//0查询 1撤单 2失败
            payRecord.setStoreNo(storeNo);
            payRecord.setIsSuccess(is_success);
            payRecordRepository.save(payRecord);

            //更新主表
            PayOut oldOutCancel = payOutRepository.findOneByGsdmAndOrderNo(gsdm, orderNo);
            oldOutCancel.setXgsj(new Date());
            oldOutCancel.setErrorMessage(error_message);
            oldOutCancel.setErrorCode(error_code);
            oldOutCancel.setIsSuccess(is_success);
            oldOutCancel.setStatus(status);
            oldOutCancel.setResultMessage(result_message);
            oldOutCancel.setResultCode(result_code);
            payOutRepository.save(oldOutCancel);

            if (StringUtils.isNotBlank(error_code)) {
                result.put("errorMsg", error_message);
            } else {
                if("FAIL".equals(status)){
                    result.put("errorMsg", "支付已取消（"+result_message+")");
                }else{
                    result.put("errorMsg", "NO_MESSAGE_FOR_PAY_RESULT");
                }
            }
        }
        return result;
    }

    public Map getPayOut(String gsdm, String orderNo) {
        PayOut payOut = payOutRepository.findOneByGsdmAndOrderNo(gsdm, orderNo);
        if(payOut==null){
            return null;
        }
        String orderStatus = payOut.getOrderStatus();
        String errorCode = payOut.getErrorCode();
        String errorMessage = payOut.getErrorMessage();
        String totalAmount = payOut.getTotalAmount();
        String storeNo = payOut.getStoreNo();
        String tradeNo = payOut.getTradeNo();
        String paywayName = payOut.getPaywayName();
        String finishTime = payOut.getFinishTime();
        List<String> finalCodeList = payCodeRepository.findByIsFinal();
        PayCode oneByCode = payCodeRepository.findOneByCode(orderStatus);
        String msg = oneByCode.getMsg();
        String finalStatus = "0";
        if (finalCodeList.contains(orderStatus)) {
            if("PAID".equals(orderStatus)){
                finalStatus = "1";
            }else{
                finalStatus = "2";
            }
        }
        Map map = new HashMap();
        map.put("finalStatus", finalStatus);
        map.put("errorCode", errorCode);
        map.put("errorMessage", errorMessage);
        map.put("orderStatus", orderStatus);
        map.put("payMsg", msg);
        map.put("totalAmount", totalAmount);
        map.put("storeNo", storeNo);
        map.put("tradeNo", tradeNo);
        map.put("paywayName", paywayName);
        map.put("finishTime", finishTime);
        return map;
    }
}
